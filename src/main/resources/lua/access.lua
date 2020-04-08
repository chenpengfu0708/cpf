--[[
    --对应java入参，都为key
    String transactionNo, String requestUrl, String businessType, String channelType, String channelUser
    -- 返回
    {幂等触发 , limitResult[1],limitResult[2],limitResult[3], 权限,重复请求}
]] --
-- transactionNo  事务编号，用于进行幂等控制
local transactionNo = KEYS[1]

--local channelUser = KEYS[2]

--local passWord = KEYS[3]

--    requestBodyHash  数据hash，用于比较是否是同一个请求，防止重复发送
local requestBodyHash = KEYS[2]

--重复发送处理
if requestBodyHash and requestBodyHash ~= "" then
    local reSendFlag = redis.call("exists", requestBodyHash)
    if reSendFlag ~= 0 then
        return { 1, 0, 1 }
    else
        redis.call("setex", requestBodyHash ,5 , 1)
    end
end

--用户密码校对
--local psKey = "channel:user:" .. channelUser
--local userPassword = redis.call("hget", psKey , "pwd" )
-- if userPassword ~= passWord then
--     return { 0, 0, 0, 0, 1 , 0 }
-- end
--[[
幂等触发
1 代表触发
0 代表不触发
]] --

local endFlag = redis.call("exists", transactionNo)
if endFlag ~= 0 then
    return { 1, 0, 0 }
end

-- 当限流没有配置时，使用下面值
--[[
redis 中的结构
type      :  hash
key       :  limit:{key}
hash-key  :  initTtl,rate,burst
]] --

--local initTtl = 1
--local rate = 10000
--local burst = 0



-- 组合对应的限流代码
-- 先精确到接口，如果没有再直接控制账号
-- local detailLimitKey = "limit:" .. channelUser .. ":" .. channelType
-- local user_limitKey = "limit:" .. channelUser
-- --精确到接口
-- local limits = redis.call("HGETALL", detailLimitKey)
-- local limitKey = detailLimitKey .. ":time"
-- -- 接口没有配置，到账号
-- if not limits or #limits == 0 then
--     limits = redis.call("HGETALL", user_limitKey)
--     limitKey = user_limitKey .. ":time"
-- end
--
-- if limits and #limits > 0 then
--     if limits.initTtl then
--         initTtl = limits.initTtl;
--     end
--     if limits.rate then
--         rate = limits.rate
--     end
--     if limits.burst then
--         burst = limits.burst
--     end
-- end
--[[
local currentTime = redis.call("incr", limitKey)
if currentTime == 1 then
    redis.call("expire", limitKey, initTtl)
end
]] --

--[[
limitResult 结构
第一位是否触发限流 true 为触发
第二位 当为false 时，返回剩余次数，当为true时，返回可等待次
第三位 为false 时，返回1（无实际意义） ，为true ,返回需要等待时间
]] --
--[[
local limitResult = {}
if currentTime > (rate + burst) then
    --limitResult {false, 0, 0}
    limitResult[1] = 1
    limitResult[2] = 0
    limitResult[3] = 0
elseif currentTime <= rate then
    --return {true, rate - currentTime, 1}
    limitResult[1] = 0
    limitResult[2] = rate - currentTime
    limitResult[3] = 1
elseif currentTime > rate and currentTime <= (rate + burst) then
    local ttl = redis.call("ttl", limitKey)
    --return {false, currentTime - rate, ttl}
    limitResult[1] = 1
    limitResult[2] = (rate + burst) - currentTime
    limitResult[3] = ttl
end

if limitResult[1] == 1 then
    return { endFlag, limitResult[1], limitResult[2], limitResult[3], 1 ,0 }
end
]] --

--[[
返回 true 代表触发 权限控制
下面逻辑为权限控制 ,
不支持url正则匹配，因此直接使用 businessType 处理
权限
redis 中的结构
type      :  hash
key       :  access:{channelUser}
hash-key  :  comcode:businessType
value     :  有值即为有效
]] --

--[[
local accessKey = "channel:role"
-- 接口权限
-- local apiAccessFlag = redis.call("HEXISTS", accessKey .. ":api:".. channelUser, businessType)
local apiAccessFlag = 1
-- 产品权限
local productAccessFlag = redis.call("HEXISTS", accessKey .. ":product:" .. channelUser ,productType )

if apiAccessFlag == 0 or (productAccessFlag ==0 and productType ~= nil and productType ~= "") then
    return { endFlag, limitResult[1], limitResult[2], limitResult[3], 1 , 0}
else
    return { endFlag, limitResult[1], limitResult[2], limitResult[3], 0 , 0 }
end
]] --

