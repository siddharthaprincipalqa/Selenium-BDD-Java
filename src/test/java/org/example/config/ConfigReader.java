package org.example.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    static {
        try(InputStream inputStream =
                    ConfigReader.class.getClassLoader()
                            .getResourceAsStream("config.properties")){
            if(inputStream == null){
                throw new IllegalStateException("config file not found!");
            }
            properties.load(inputStream);
        }catch(Exception e){
            throw new IllegalStateException("unable to load the configuration", e);
        }
    }
    public static String getProperty(String key){
        String value = properties.getProperty(key);
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(key + " is empty!");
        }
        return value.trim();
    }
}
