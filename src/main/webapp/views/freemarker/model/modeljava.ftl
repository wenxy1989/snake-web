
package com.base.model;

/**
 * Created by wenxy on 2016/10/30.
 */
public class Model implements IModel {
    private Long id;//默认属性
    private Integer deleted = 0;//默认属性0-未删除,1-删除
    private String createTime;//默认属性
    private String createUser;//默认属性
    private Long createUserId;//默认属性
    private String updateTime;//默认属性
    private String updateUser;//默认属性
    private Long updateUserId;//默认属性
    private Integer extendOne;//扩展属性1
    private Integer extendTwo;//扩展属性2
    private Integer extendThree;//扩展属性3
    private Integer extendFour;//扩展属性4

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getExtendOne() {
        return extendOne;
    }

    public void setExtendOne(Integer extendOne) {
        this.extendOne = extendOne;
    }

    public Integer getExtendTwo() {
        return extendTwo;
    }

    public void setExtendTwo(Integer extendTwo) {
        this.extendTwo = extendTwo;
    }

    public Integer getExtendThree() {
        return extendThree;
    }

    public void setExtendThree(Integer extendThree) {
        this.extendThree = extendThree;
    }

    public Integer getExtendFour() {
        return extendFour;
    }

    public void setExtendFour(Integer extendFour) {
        this.extendFour = extendFour;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}