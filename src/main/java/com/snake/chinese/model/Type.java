package com.snake.chinese.model;

import com.snake.resource.dao.MapObject;

/**
 * 词语类型/不同词语类型再语句中可以作为不同的语句成分
 * Created by wen on 2015/8/11.
 */
public class Type implements MapObject {

    private Long id;
    private String name;
    private Integer type;
    private String createdTime;

    public Type(){}

    public Type(String value) {
        this.name = value;
    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
