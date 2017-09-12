package com.rakesh.ratelimiter.repository;


import com.rakesh.ratelimiter.conf.pojo.Client;
import com.rakesh.ratelimiter.conf.pojo.Rate;
import com.rakesh.ratelimiter.pojo.RateLimitContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Aerospike Repository Implementation
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */


public class AerospikeRepository implements RateLimiter {

    @Override
    public Map<String, Rate> accept(Client client, RateLimitContext context) {
        //TODO implementation for distributed system
        return new HashMap<String, Rate>();
    }
}
