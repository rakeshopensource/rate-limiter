package com.rakesh.ratelimiter.conf.reader;


import com.rakesh.ratelimiter.conf.pojo.Clients;

/**
 * Interface to read different supported configuration file types (JSON, YAML, XML)
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public interface ConfigurationReader {
    public abstract Clients getConfiguration(String fileName) throws Exception;
}
