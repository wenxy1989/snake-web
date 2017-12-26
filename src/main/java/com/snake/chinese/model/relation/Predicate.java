package com.snake.chinese.model.relation;

/**
 * 谓语：动作、系动词等
 * Created by wen on 2015/8/11.
 */
public class Predicate {

    private Long id;
    private String name;
    private Integer type;
    private String createdTime;

    public Predicate(){}

    public Predicate(String value) {
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
