package com.test.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream(CONFIG_FILE);
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static String getBaseUrl() {
        return getProperty("base.url", "https://www.ajio.com/");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "20"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("pageload.timeout", "30"));
    }

    public static boolean getHeadlessMode() {
        return Boolean.parseBoolean(getProperty("headless.mode", "false"));
    }

    private static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
