package com.snake.chinese.model;

import com.snake.resource.dao.MapObject;

import java.util.List;

/**
 * Created by wen on 2015/8/12.
 * 语法结构
 * 例如：主-谓-宾
 */
public class Structure implements MapObject{
    private Long id;
    private String name;
    private Integer type;
    private String content;
    private String remark;
    private String createdTime;
    private char[] values;
    
    private List<Element> elements;

    public Structure(){}

    public Structure(List<Element> elements) {
        this.elements = elements;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public char[] getValues() {
        if(null != this.name){
            this.values = this.name.toCharArray();
        }
        return values;
    }

    public void setValues(char[] values) {
        this.values = values;
    }
}
