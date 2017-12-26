package com.snake.book.model;

/**
 * 书籍text
 * Created by wen on 2015/8/11.
 */
public class Book {

    private Long id;
    private String name;
    private String author;//作者
    private String introduction;//简介
    private String file;//文件路径
    private String createdTime;

    private Boolean hadPhrase = false;//
    private Boolean hadWord = false;

    public Book(){}

    public Book(String value) {
        this.name = value;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getHadPhrase() {
        return hadPhrase;
    }

    public void setHadPhrase(Boolean hadPhrase) {
        this.hadPhrase = hadPhrase;
    }

    public Boolean getHadWord() {
        return hadWord;
    }

    public void setHadWord(Boolean hadWord) {
        this.hadWord = hadWord;
    }
}
