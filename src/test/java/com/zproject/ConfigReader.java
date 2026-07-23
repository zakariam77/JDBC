package com.zproject;

import org.openqa.selenium.devtools.v146.io.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;

        static {
            try {
                properties = new Properties();

                properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties"));
            } catch (IOException e) {
                throw new RuntimeException("config file cannot be opened");
            }
        }
        public static String getProperty(String key){
            return properties.getProperty(key);
        }
    }

