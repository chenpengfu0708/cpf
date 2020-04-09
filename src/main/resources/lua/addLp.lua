--@author cpf
-- 令牌桶key
local lpKey = KEYS[1]

--限流控制:令牌桶最大值为5，每10秒会放进去2个令牌（不超过最大值），通过定时任务调用addLp.lua脚本增加令牌
local maxLp = tonumber(5) -- 令牌桶最多5个令牌（与initLp.lua脚本对应）
local addNum = tonumber(1) --每次新增令牌数
local sumLp = tonumber(0)
-- 如果令牌不存在，构建一个初始化令牌桶
local lp = redis.call("exists", lpKey)
if lp ~= 0 then
    local maxLpString = redis.call("get", lpKey)
    sumLp = tonumber(maxLpString) + addNum
    if sumLp > maxLp then
        sumLp = maxLp
    end
    redis.call("setex", lpKey, 60, sumLp)
else
    sumLp = maxLp
    redis.call("setex", lpKey, 60, maxLp)
end
return {sumLp}
