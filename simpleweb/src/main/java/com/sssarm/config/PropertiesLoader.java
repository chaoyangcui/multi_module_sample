package com.sssarm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cuiguiyang on 2017/3/12 12:46.
 * Desc
 */
@Configuration
public class PropertiesLoader {

    @Bean
    public Properties properties() {
        final String propertyPath = "/config/project.properties";
        Properties properties = new Properties();
        InputStream is = getClass().getResourceAsStream(propertyPath);
        try {
            if (is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
