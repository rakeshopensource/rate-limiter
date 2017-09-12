package com.rakesh.ratelimiter.conf.reader;


import com.rakesh.ratelimiter.conf.pojo.Clients;

/**
 * XML configuration reader
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class XmlConfReader implements ConfigurationReader {
    public static final String fileExtension = ".xml";

    @Override
    public Clients getConfiguration(String fileName) throws Exception {
        //TODO : Implementation
        return null;
    }
}
