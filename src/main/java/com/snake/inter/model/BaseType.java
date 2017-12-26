package com.snake.inter.model;

import org.apache.commons.lang.StringUtils;

/**
 * Created by HP on 2016/11/1.
 */
public enum  BaseType {

    STRING("String","varchar",20),
    BOOLEAN("Boolean","int",1),
    BYTE("Byte","byte",8),
    CHAR("Charactor","char",8),
    SHORT("Short","decimal",6),
    INTEGER("Integer","int",11),
    FLOAT("FLoat","decimal",10),
    DOUBLE("Double","decimal",13),
    LONG("Long","bigint",20);

    private String type;
    private String mysqlType;
    private Integer length;

    private BaseType(String type,Integer length){
        this.type = type;
        this.length = length;
    }

    private BaseType(String type,String mysqlType,Integer length){
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

    public Integer getLength(){
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
