package com.snake.system.model;

import com.snake.resource.dao.MapObject;
import org.springframework.security.core.GrantedAuthority;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
public class Role implements GrantedAuthority,MapObject {
    private Long id;//ID
    private String code;//角色编码
    private String name;//角色名称
    private Integer status;//状态
    private String remark;//备注
    private String createdTime;//创建时间
    private Long creatorId;//创建人ID

    public Role(){}

    public Role(String code){
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getAuthority() {
        return this.code;
    }
}
