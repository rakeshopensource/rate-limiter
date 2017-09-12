package com.rakesh.ratelimiter.conf.reader;


import com.rakesh.ratelimiter.conf.pojo.Clients;

/**
 * Singleton class to load configuration from configuration file
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class Configuration {

    private static final String fileName = "request_limiter";
    private static Configuration _instance;
    private Clients clients;

    private Configuration() {

    }

    /**
     * Default Reader - YAML configuration Reader
     *
     * @return
     * @throws Exception
     */

    public static Configuration getInstance() throws Exception {
        if (_instance == null) {
            synchronized (Configuration.class) {
                if (_instance == null) {
                    _instance = new Configuration();
                    _instance.clients = new YamlConfReader().getConfiguration(fileName);
                }
            }
        }
        return _instance;
    }


    /**
     * @param configurationReader
     * @return
     * @throws Exception
     */

    public static Configuration getInstance(ConfigurationReader configurationReader) throws Exception {
        if (_instance == null) {
            synchronized (Configuration.class) {
                if (_instance == null) {
                    _instance = new Configuration();
                    _instance.clients = configurationReader.getConfiguration(fileName);
                }
            }
        }
        return _instance;
    }

    //TODO return immutable clients
    public Clients getClients() {
        return this.clients;
    }
}