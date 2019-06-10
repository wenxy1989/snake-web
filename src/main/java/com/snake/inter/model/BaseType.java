package com.snake.inter.model;

import org.apache.commons.lang.StringUtils;

/**
 * Created by HP on 2016/11/1.
 */
public enum  BaseType {

    STRING("String","varchar",20l),
    BOOLEAN("Boolean","int",1l),
    BYTE("Byte","byte",8l),
    CHAR("Character","char",8l),
    SHORT("Short","decimal",6l),
    INTEGER("Integer","int",11l),
    FLOAT("Float","decimal",10l),
    DOUBLE("Double","decimal",13l),
    LONG("Long","bigint",20l);

    private String type;
    private String mysqlType;
    private Long length;

    private BaseType(String type,Long length){
        this.type = type;
        this.length = length;
    }

    private BaseType(String type,String mysqlType,Long length){
        this.type = type;
        this.mysqlType = mysqlType;
        this.length = length;
    }

    public String getType(){
        return this.type;
    }

    public String getMysqlType() {
        return mysqlType;
    }

    public Long getLength(){
        return this.length;
    }

    public static BaseType getType(String type){
        BaseType obj = null;
        if(StringUtils.isNotBlank(type)) {
            if (type.equals(BaseType.BOOLEAN.getType())) {
                obj = BaseType.BOOLEAN;
            } else if (type.equals(BaseType.LONG.getType())) {
                obj = BaseType.LONG;
            } else if (type.equals(BaseType.BYTE.getType())) {
                obj = BaseType.BYTE;
            } else if (type.equals(BaseType.CHAR.getType())) {
                obj = BaseType.CHAR;
            } else if (type.equals(BaseType.SHORT.getType())) {
                obj = BaseType.SHORT;
            } else if (type.equals(BaseType.INTEGER.getType())) {
                obj = BaseType.INTEGER;
            } else if (type.equals(BaseType.FLOAT.getType())) {
                obj = BaseType.FLOAT;
            } else if (type.equals(BaseType.DOUBLE.getType())) {
                obj = BaseType.DOUBLE;
            } else if (type.equals(BaseType.STRING.getType())) {
                obj = BaseType.STRING;
            }
        }
        return obj;
    }

}
