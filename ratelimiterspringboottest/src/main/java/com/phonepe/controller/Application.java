package com.phonepe.controller;

import com.phonepe.ratelimiter.conf.reader.YamlConfReader;
import com.phonepe.ratelimiter.interceptor.SpringBootInterceptor;
import com.phonepe.ratelimiter.repository.InMemoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 *
 * @author Rakesh Rathi
 * @since  2017-09-11
 */

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Register SpringBootInterceptor of RateLimiter framework
     *
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SpringBootInterceptor(new YamlConfReader(),new InMemoryRepository()));
    }
}