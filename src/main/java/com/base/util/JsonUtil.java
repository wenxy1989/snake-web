package com.base.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by wanquan on 2016/10/18.
 */
public class JsonUtil {
    public static final String NO_DATA = "{\"data\":null}";
    public static final String NO_RESULT = "{\"result\":null}";
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        //转换json时，如果对象中属性值为null，则不生成该属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /***
     * @param json
     * @return 当解析失败返回null
     * @Description: 给定json字符串获得json对象
     */
    public static JsonNode josn2Object(String json) {
        try {
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /***
     * @param obj
     * @return 当解析失败返回{datas:null}
     * @Description: 给定java对象生成对应json
     */
    public static String parseJson(Object obj) {

        if (obj == null) {
            return NO_DATA;
        }

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return NO_DATA;
        }
    }

    /***
     * @param obj
     * @param root
     * @return 当解析失败返回{datas:null}
     * @Description: 给定java对象生成对应json,可以指定一个json的root名
     */
    public static String parseJson(Object obj, String root) {
        if (obj == null) {
            return NO_DATA;
        }

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("{\"");
            sb.append(root);
            sb.append("\":");
            sb.append(mapper.writeValueAsString(obj));
            sb.append("}");
            return sb.toString();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return NO_DATA;
        }
    }

    /***
     * @param json
     * @param var
     * @return 若传入var为null，则默认变量名为datas
     * @Description:将json字符串包装成jsonp，例如var data={}方式
     */
    public static String wrapperJsonp(String json, String var) {
        if (var == null || var.trim().equals("")) {
            var = "datas";
        }
        return new StringBuilder().append("var ").append(var).append("=").append(json).toString();
    }

    /**
     * @param json
     * @param c 必须包含无参构造器
     * @param <T> 返回类型
     * @return 将json 转换成 指定类型
     */
    public static <T> T readValue(String json, Class<T> c) {
        try {
            return mapper.readValue(json, c);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取泛型的Collection Type   如 List<User> list = readJson(json, List.class, User.class);
     *
     * @param jsonStr         json字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T readJson(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return mapper.readValue(jsonStr, javaType);
    }

}
