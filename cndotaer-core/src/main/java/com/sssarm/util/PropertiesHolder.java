package com.sssarm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/7/26 21:55
 * Desc  : 描述信息
 */
public class PropertiesHolder {
    private static final String PROPERTIES_FILE_PATH = "/db.properties";
    private static final String DEFAULT_VALUE = "";
    private static Properties properties;

    private PropertiesHolder() {
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(PROPERTIES_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getClass().getResource(""));
        System.out.println(getClass().getResource("/"));
    }
    private static final class PropertiesHolderHandler {
        private static PropertiesHolder instance = new PropertiesHolder();
    }
    public static PropertiesHolder getInstance() {
        return PropertiesHolderHandler.instance;
    }

    public String getProperty(final String key) {
        return properties.getProperty(key, DEFAULT_VALUE);
    }

    public static void main(String[] args) {
        System.out.println(PropertiesHolder.getInstance().getProperty("db.url"));
    }
}
