package com.rakesh.ratelimiter.util;

import java.util.function.Supplier;


/**
 * Rate Limiter Utils
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class RateLimiterUtils {
    public static <T> T get(Supplier<T> supplier, T defaultValue) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }
}
