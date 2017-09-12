package com.rakesh.ratelimiter.conf.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.rakesh.ratelimiter.conf.pojo.Clients;


import java.io.File;


/**
 * YAML configuration reader (default)
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class YamlConfReader implements ConfigurationReader {

    public static final String fileExtension = ".yaml";

    @Override
    public Clients getConfiguration(String fileName) throws Exception {
        String fName = fileName + fileExtension;
        File file = new File(getClass().getClassLoader().getResource(fName).getFile());
        Object obj = new ObjectMapper(new YAMLFactory()).readValue(file, Object.class);
        return new Gson().fromJson(new ObjectMapper().writeValueAsString(obj), Clients.class);
    }
}
