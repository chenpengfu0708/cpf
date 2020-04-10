--[[
实现思路：
    @author cpf
    1：通过过滤器拦截所有请求，目前测试暂时用同一个接口调用，直接走controller
    2：在redis中构建一个令牌桶，里面存放一定的令牌，每一个令牌可以允许一个请求进来
    3：后台跑一个定时任务，定时往令牌桶中存放令牌（总数量不超过最大令牌数量）
    关于return {result1, result2, result3}
    result1：用于表示是否相同请求-通过计算参数hash判断，后面可通过计算body的hash （0通过，1相同请求）
    result2：用于表示令牌状态，判断是否限流（-1表示没有令牌，其他数字表示令牌数量）
    result3：用于限制同一个ip一段时间内的允许请求次数（0通过，-1请求频繁）
             每次请求前先统计下该ip在缓存中的数量（通过keys查询出来计算），达到该时间内最大请求次数，不予放行，未达到插入一条记录到缓存中
]] --

local dataHash = KEYS[1] -- dataHash  数据hash，用于比较是否是同一个请求，防止重复发送
local lpKey = KEYS[2]  -- 令牌桶key
local ip = KEYS[3]  -- 请求ip地址  控制同一ip 10秒钟只能调用3次接口
local timestamp = KEYS[4] -- 当前时间戳
local thisIpKey = ip .. ":" .. timestamp


-- 1：相同请求处理
if dataHash and dataHash ~= "" then
    local reSendFlag = redis.call("exists", dataHash)
    if reSendFlag ~= 0 then
        return { 1, 0, 1 }
    else
        redis.call("setex", dataHash ,1 , 1)
    end
end


--2：查询ip请求总数
local keys = redis.call('keys', ip .. "*")  --模糊查询key
local sum = tonumber(0)                     --该ip在缓存中的请求记录数
local allowMaxNum = tonumber(3)             --最大的允许次数
local timing = tonumber(10)                 --请求限制的时段 10秒内

-- 获取 table 的长度的时候无论是使用 # 还是 table.getn 其都会在索引中断的地方停止计数，遍历累加计算总数
for iter, value in ipairs(keys) do
    sum = sum + 1
end
-- 10秒内请求次数达到3次以上，返回ip限制标志
if sum >= allowMaxNum then
    return {0, 0, -1}
end


--限流控制:令牌桶最大值为5，每10秒会放进去2个令牌（不超过最大值），通过定时任务调用addLp.lua脚本增加令牌
local maxLp = tonumber(5) -- 令牌桶最多5个令牌

-- 如果令牌不存在，构建一个初始化令牌桶,如果存在，且令牌数量大于1个，拿出一个令牌，否则返回限流标志
local lp = redis.call("exists", lpKey)
if lp ~= 0 then
    local maxLpString = redis.call("get", lpKey)
    maxLp = tonumber(maxLpString)
    if maxLp <= 0 then
        return {0, -1, 0}
    else
        maxLp = maxLp - 1
        redis.call("decr", lpKey)
        redis.call("expire", lpKey, 60) --调用时刷新key的有效时间
    end
else
    maxLp = maxLp - 1
    redis.call("setex", lpKey ,60 , maxLp - 1) -- 初始化令牌桶时，拿出一个给当前请求，所以只存 maxLp - 1 个进去
end
--能走到这一步证明该ip设定的时间内请求次数没达到3次以上，存一个该ip的请求到缓存中
redis.call("setex", thisIpKey, timing, 1)
return {0, maxLp, 0}  --返回剩余令牌数



