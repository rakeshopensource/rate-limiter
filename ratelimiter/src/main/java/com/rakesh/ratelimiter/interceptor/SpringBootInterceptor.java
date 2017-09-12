package com.rakesh.ratelimiter.interceptor;

import com.rakesh.ratelimiter.conf.reader.ConfigurationReader;
import com.rakesh.ratelimiter.pojo.RateLimitContext;
import com.rakesh.ratelimiter.repository.RateLimiter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This helps intercept call in Spring boot application.
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class SpringBootInterceptor extends RateLimiterInterceptor implements HandlerInterceptor {

    static Logger LOGGER = LoggerFactory.getLogger(SpringBootInterceptor.class);
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Calling preHandle .....");
        RateLimitContext context = getRateLimitContext(request);
        boolean hasPermission = hasPermission(context);
        if(!hasPermission){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        return hasPermission;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public SpringBootInterceptor() {
        super();
    }

    public SpringBootInterceptor(ConfigurationReader configurationReader) {
        super(configurationReader);
    }

    public SpringBootInterceptor(RateLimiter rateLimiter) {
        super(rateLimiter);
    }

    public SpringBootInterceptor(ConfigurationReader configurationReader, RateLimiter rateLimiter) {
        super(configurationReader,rateLimiter);
    }
}