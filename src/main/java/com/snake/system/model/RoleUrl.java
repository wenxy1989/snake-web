package com.snake.system.model;

/**
 * 角色与功能url数据关联模型
 * @author suxl
 * @date 2014-6-12
 */
public class RoleUrl {
    private Long roleId;
    private Long functionId;
    private String roleCode;//角色代码
    private String url;//功能url

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
