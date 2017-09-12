package com.rakesh.ratelimiter.conf.reader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.rakesh.ratelimiter.conf.pojo.Clients;

import java.io.File;
import java.io.FileReader;


/**
 * JSON configuration reader
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */
public class JsonConfReader implements ConfigurationReader {

    public static final String fileExtension = ".json";

    @Override
    public Clients getConfiguration(String fileName) throws Exception {
        String fName = fileName + fileExtension;
        File file = new File(getClass().getClassLoader().getResource(fName).getFile());
        return new Gson().fromJson(new JsonReader(new FileReader(file)), Clients.class);
    }
}
