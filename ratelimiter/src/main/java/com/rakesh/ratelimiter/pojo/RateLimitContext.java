package com.rakesh.ratelimiter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * RateLimiterContext hold call information
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateLimitContext {
    private String clientId;
    private String method;
    private String api;
}
