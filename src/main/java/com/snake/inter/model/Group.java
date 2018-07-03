package com.snake.inter.model;

import com.snake.resource.dao.MapObject;

import java.util.List;

/**
 * inter group like java model
 * Created by HP on 2016/10/13.
 */
public class Group implements MapObject {

    private Long id;
    private Long applicationId;
    private String name;
    private String model;
    private String remark;
    private Integer status;
    private String createdTime;
    private Long creatorId;
    private List<Url> urlList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Url> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<Url> urlList) {
        this.urlList = urlList;
    }
}
