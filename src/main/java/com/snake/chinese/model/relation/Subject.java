package com.snake.chinese.model.relation;

/**
 * 主体：人物、思想。
 * 语句中主语、谓语的内容
 * 不同的主体可以对应不同的谓语(动词)
 * Created by wen on 2015/8/11.
 */
public class Subject {

    private Long id;
    private String name;
    private Integer type;
    private String createdTime;

    public Subject(){}

    public Subject(String value) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
