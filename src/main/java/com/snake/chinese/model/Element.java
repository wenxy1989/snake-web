package com.snake.chinese.model;

import com.snake.resource.dao.MapObject;

import java.util.List;

/**
 * 句子元素、成分
 * Created by wen on 2015/8/11.
 */
public class Element implements MapObject {

    //todo 分析后的结果存储

    private Long id;
    private String name;
    private Integer type;
    private String createdTime;

    private Word word;
    private List<Type> types;

    public Element(){}

    public Element(String value) {
        this.name = value;
    }

    public Element(Word word, String element) {
        this.word = word;
        this.name = element;
    }

    public Element(Long id, String value) {
        this.id = id;
        this.name = value;
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

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
