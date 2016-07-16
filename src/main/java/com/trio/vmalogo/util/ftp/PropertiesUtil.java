package com.trio.vmalogo.util.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties properties = null;

    private static void setResource(String path) {
        properties = new Properties();
        InputStream is = PropertiesUtil.class.getResourceAsStream(path);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) setResource("/config.properties");
        return properties.getProperty(key);
    }
}
