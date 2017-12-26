package com.snake.book.model;

/**
 * 主体：完整的语句、段落
 * Created by wen on 2015/8/11.
 */
public class Paragraph {

    private Long id;
    /**
     * 长度小于等于500的存在着
     */
    private String name;
    /**
     * 长度大于500的存储在这里
     */
    private String content;
    private Long bookId;
    private String createdTime;

    public Paragraph(){}

    public Paragraph(String value) {
        if(null != value) {
            if(value.length() <= 500) {
                this.name = value;
            }else{
                this.content = value;
            }
        }
    }

    public String getCreatedTime() {
        return createdTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getValue(){
        String _value = null;
        if(null != this.name) {
            _value = name;
        }else if(null != this.content) {
            _value = content;
        }
        return _value;
    }
}
