package com.snake.system.model;

import java.io.Serializable;

import com.base.common.model.AbstractModel;

/**
 * table sys_user_role
 * @author wen
 *
 */
public class Auth extends AbstractModel{
	
	private Long userId;
	private Long roleId;
	private User user;
	private Role role;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUniqueIdName() {
		// TODO Auto-generated method stub
		return null;
	}

}
