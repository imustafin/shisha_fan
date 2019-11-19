package com.project.shishafan.utils;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 10.11.2019
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties properties(){
        return properties;
    }

}
