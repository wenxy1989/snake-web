package com.snake.system.model;

import java.io.Serializable;

import com.base.common.model.AbstractModel;

/**
 * table sys_role_menu
 * @author wen
 *
 */
public class Access extends AbstractModel {

	private Long menuId;
	private Long roleId;
	private Menu menu;
	private Role role;
	public Access(){}
	public Access(Role r,Menu m){
		this.role = r;
		this.menu = m;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
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
