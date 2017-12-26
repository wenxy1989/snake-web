package com.snake.media.model;

/**
 * Created by wen on 2015/5/1.
 */
public class Video {

    private Long id;
    private String name;
    private String filePath;
    private String createdTime;
    private String ext;


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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getExt() {
        if(null != this.name){
            if(this.name.contains(".")) {
                this.ext = this.name.substring(this.name.lastIndexOf(".")+1);
            }
        }
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
