package com.snake.resource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;


//@Component("javaColumnProperties")
//@PropertySource(value = {"classpath:mysql-java.properties"/*, "file:/etc/config.properties"*/}, name = "mysql-java.properties")
public class JavaColumnProperties {


    private Properties properties;

    public JavaColumnProperties() throws IOException {
        Resource resource = new ClassPathResource("/mysql-java.properties");
        properties = PropertiesLoaderUtils.loadProperties(resource);
    }

    public String getColumnType(String javaType) {
        return properties.getProperty("java." + javaType);
    }

    public String getJavaType(String columnType) {
        return properties.getProperty("mysql." + columnType);
    }

}
