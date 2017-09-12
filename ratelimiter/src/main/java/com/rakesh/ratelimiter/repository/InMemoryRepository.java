package com.rakesh.ratelimiter.repository;


import com.rakesh.ratelimiter.conf.pojo.Rate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-Memory Repository Implementation
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class InMemoryRepository extends AbstractRateLimiter {

    private Map<String, Rate> repository = new ConcurrentHashMap<>();

    @Override
    protected Rate getRate(String key) {
        return this.repository.get(key);
    }

    @Override
    protected void saveRate(Rate rate) {
        this.repository.put(rate.getKey(), rate);
    }
}
