package com.snake.chinese.model;

import java.util.List;

/**
 * 单字词/多字词
 * Created by wen on 2015/8/10.
 */
public class Word {

    private Long id;
    private String word;
    private Long typeId;
    private String createdTime;
    private Boolean verified;

    private Integer count;

    private Long bookId;
    private char[] values;

    private Type type;
    private List<Type> types;
    private List<Long> typeIds;

    public Word(){}

    public Word(String value) {
        this.word = value;
    }

    public Word(Long id, String value) {
        this.id = id;
        this.word = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public char[] getValues() {
        if(null != this.word){
            this.values = this.word.toCharArray();
        }
        return values;
    }

    public List<Long> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<Long> typeIds) {
        this.typeIds = typeIds;
    }

    public void setValues(char[] values) {
        this.values = values;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
