package com.rakesh.ratelimiter.repository;

import com.rakesh.ratelimiter.conf.pojo.Client;
import com.rakesh.ratelimiter.conf.pojo.Rate;
import com.rakesh.ratelimiter.pojo.RateLimitContext;


import java.util.Map;

/**
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public interface RateLimiter {
    public Map<String, Rate> accept(Client client, RateLimitContext context);
}
