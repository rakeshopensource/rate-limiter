package com.rakesh.ratelimiter.interceptor;


import com.rakesh.ratelimiter.conf.pojo.Clients;
import com.rakesh.ratelimiter.conf.pojo.Rate;
import com.rakesh.ratelimiter.conf.reader.Configuration;
import com.rakesh.ratelimiter.conf.reader.ConfigurationReader;
import com.rakesh.ratelimiter.conf.reader.YamlConfReader;
import com.rakesh.ratelimiter.pojo.RateLimitContext;
import com.rakesh.ratelimiter.repository.InMemoryRepository;
import com.rakesh.ratelimiter.repository.RateLimiter;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

@AllArgsConstructor
public class RateLimiterInterceptor {

    static Logger LOGGER = LoggerFactory.getLogger(RateLimiterInterceptor.class);

    private ConfigurationReader configurationReader;
    private RateLimiter rateLimiter;

    public boolean hasPermission(RateLimitContext context ) throws Exception{
        AtomicBoolean hasPermission = new AtomicBoolean(true);
        Configuration cfg = Configuration.getInstance(configurationReader);
        Clients clients = cfg.getClients();
        LOGGER.info("Configuration : {}" , clients);
        Map<String, Rate> rateMap = rateLimiter.accept(clients.getClients().get(context.getClientId()), context);
        rateMap.forEach((k,v) ->  { if(v.getLimit()<0) {  hasPermission.set(false); } });
        LOGGER.info("client-id : {} has permission : {}" , context.getClientId(), hasPermission);
        return hasPermission.get();
    }

    public RateLimitContext getRateLimitContext(HttpServletRequest request) {
        RateLimitContext context = new RateLimitContext();
        context.setClientId(request.getHeader("client-id"));
        context.setApi(request.getServletPath());
        context.setMethod(request.getMethod().toLowerCase());

        LOGGER.info("RateLimitContext : {}" , context);

        return  context;
    }

    public RateLimiterInterceptor() {
        this.configurationReader = new YamlConfReader();
        this.rateLimiter = new InMemoryRepository();
    }

    public RateLimiterInterceptor(ConfigurationReader configurationReader) {
        this.configurationReader = configurationReader;
    }

    public RateLimiterInterceptor(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }


}
