package com.demo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {
    private static Log log = LogFactory.getLog(PropertiesUtil.class);
    public static Properties getProperties(String path) {
        Properties prop = null;
        InputStream inStream = null;
        try {
            prop = new Properties();
            inStream = new ClassPathResource(path).getInputStream();
            prop.load(new InputStreamReader(inStream, "utf-8"));
            inStream.close();
        } catch (Exception e) {
            log.warn("获取properties文件失败", e);
        }
        return prop;
    }

}
