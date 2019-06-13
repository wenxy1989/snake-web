package com.snake.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;


//@Component("javaColumnProperties")
//@PropertySource(value = {"classpath:mysql-java.properties"/*, "file:/etc/config.properties"*/}, name = "mysql-java.properties")
public class TypeProperties implements TemplateMethodModelEx {


    private static Properties properties;

    public static Properties getProperties() {
        if (null == properties) {
            try {
                Resource resource = new ClassPathResource("/mysql-java.properties");
                properties = PropertiesLoaderUtils.loadProperties(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }


    public static String getValue(String... keys) {
        if (null != keys && keys.length > 0) {
            int index = 0;
            StringBuffer sb = new StringBuffer(keys[index]);
            if (keys.length > 1) {
                index++;
                for (; index < keys.length; index++) {
                    sb.append(".");
                    sb.append(keys[index]);
                }
            }
            return getProperties().getProperty(sb.toString());
        }
        return null;
    }

    public static String getColumnType(String javaType) {
        return getValue("java-mysql" + javaType);
    }

    public static String getJavaType(String columnType) {
        return getValue("mysql-java" + columnType);
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        String[] keys = new String[arguments.size()];
        for(int i=0;i<keys.length;i++){
            keys[i] = arguments.get(i).toString();
        }
        return getValue(keys);
    }
}
