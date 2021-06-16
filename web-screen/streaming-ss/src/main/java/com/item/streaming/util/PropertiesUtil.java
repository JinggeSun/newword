package com.item.streaming.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件工具类
 * @author zcm
 */
public class PropertiesUtil {

    /**
     * 加载配置文件
     * @param propertyFile
     * @return
     */
    public static Properties loadProperties(String propertyFile){
        Properties properties = new Properties();
        try {
            InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyFile);
            if(is == null){
                is = PropertiesUtil.class.getClassLoader().getResourceAsStream("properties/" + propertyFile);
            }
            properties.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 获取配置文件的值
     * @param propertyFile
     * @param key
     * @return
     */
    public static String getValue(String propertyFile, String key) {
        Properties properties = loadProperties(propertyFile);
        return properties.getProperty(key);
    }

}
