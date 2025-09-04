package com.yys.web.utils;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author 28142
 * @description
 * @date 2025/8/26 17:20
 */
@Component
public class RateLimiterUtil {

    @Autowired
    private RedissonClient redissonClient;

    public boolean allowed(String key, int limit, int timeWindow) {
        // 限流key
        String redisKey = "limiter:" + key;
        RScoredSortedSet<Long> zset = redissonClient.getScoredSortedSet(redisKey);

        // 滑动窗口起始时间
        long now = Instant.now().toEpochMilli();
        long windowStart = now - timeWindow * 1000L;

        // 删除旧数据
        zset.removeRangeByScore(0, true, windowStart, true);
        if (zset.size() >= limit) {
            return false;
        }

        // 将当前时间加入滑动窗口
        zset.add(now, now);

        // 如果是第一次请求，设置过期时间
        if (zset.remainTimeToLive() == -1) {
            zset.expire(timeWindow + 1, TimeUnit.SECONDS);
        }

        return true;
    }
}
