package com.ini;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/30 15:57
 * Desc    Setting | Editor | File and Code Templates
 */
public class Ini {

    public static void main(String[] args) {
        HierarchicalINIConfiguration iniConfiguration = new HierarchicalINIConfiguration();
        InputStream inputStream = new Ini().loadIniInputStream();
        try {
            iniConfiguration.load(inputStream);
            System.out.println(iniConfiguration.getSections());
            System.out.println(iniConfiguration.getSection("title").getRoot().getName());
            Iterator iterator = iniConfiguration.getSection("title").getKeys();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignore) {
            }
        }
    }

    public InputStream loadIniInputStream() {
        URL url = getClass().getResource("/config/config.ini");
        InputStream inputStream = getClass().getResourceAsStream("/config/config.ini");
        try {
            url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

}
