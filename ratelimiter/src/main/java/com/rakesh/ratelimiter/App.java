package com.rakesh.ratelimiter;

import com.rakesh.ratelimiter.conf.pojo.*;
import com.rakesh.ratelimiter.conf.pojo.Clients;
import com.rakesh.ratelimiter.conf.reader.Configuration;
import com.rakesh.ratelimiter.conf.reader.YamlConfReader;
import com.rakesh.ratelimiter.pojo.RateLimitContext;
import com.rakesh.ratelimiter.repository.InMemoryRepository;
import com.rakesh.ratelimiter.repository.RateLimiter;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.rakesh.ratelimiter.conf.pojo.Rate;
import org.slf4j.*;


/**
 * App is for standalone testing of Rate Limiter framework
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class App {

    static Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        try {

            AtomicBoolean hasPermission = new AtomicBoolean(true);
            RateLimitContext context = new RateLimitContext("dot.org","/catalog","get");
            LOGGER.info("RateLimitContext {}" , context);
            Configuration cfg = Configuration.getInstance(new YamlConfReader());
            Clients clients = cfg.getClients();
            LOGGER.info("Configuration {}" , clients);
            RateLimiter rateLimiter = new InMemoryRepository();
            Map<String, Rate> rateMap = rateLimiter.accept(clients.getClients().get(context.getClientId()), context);
            rateMap = rateLimiter.accept(clients.getClients().get(context.getClientId()), context);
            rateMap = rateLimiter.accept(clients.getClients().get(context.getClientId()), context);
            rateMap = rateLimiter.accept(clients.getClients().get(context.getClientId()), context);

            rateMap.forEach((k,v) ->  { if(v.getLimit()<0) {  hasPermission.set(false); } });

            if(hasPermission.get()) {
                System.out.println("Have Permission to make more request");
            }else {
                System.out.println("403 Forbidden");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
