package com.snake.inter.model;

import com.snake.mysql.model.Table;
import com.snake.resource.dao.MapObject;

import java.util.List;

/**
 * 参数类型
 * 可以自定义
 * 可以包含其他参数
 * todo
 * Created by wenxy on 2016/4/10.
 */
public class Model implements ModelObject,MapObject {

    private Long id;
    private Long applicationId;
    private String name;
    private String code;
    private String remark;
    private Integer status;
    private String createdTime;
    private Long creatorId;
    private Application application;
    private List<ModelParameter> parameterList;
    private List<Url> urlList;

    public static Model build(Table table) {
        Model model = new Model();
        model.setName(table.getComment());
        model.setCode(table.getName());
        model.setStatus(0);
        return model;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<ModelParameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<ModelParameter> parameterList) {
        this.parameterList = parameterList;
    }

    public String getExampleJsonString(){
        return Parameter.getExampleJsonString(this.parameterList);
    }
}
