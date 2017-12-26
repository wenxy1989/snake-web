package com.snake.system.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
public class User implements UserDetails {
    private Long id;//用户ID
    private String userName;//用户姓名
    private String loginName;//登录名
    private String loginPwd;//登录密码
    private Long roleId;//角色ID
    private Integer status;//状态,0-禁用 1-正常
    private String createdTime;//创建时间
    private Long creatorId;//创建用户ID

    private Role role;//角色对象
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public String getUsername() {
        return this.userName;
    }

    public String getPassword() {
        return this.loginPwd;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getAuthorities() {
        return this.roles;
    }
}
