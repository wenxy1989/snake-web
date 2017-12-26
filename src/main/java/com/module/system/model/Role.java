package com.module.system.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import com.base.common.model.AbstractModel;

/**
 * 系统角色
 * @author xue
 *
 */
public class Role extends AbstractModel implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;//角色编码，一般为ROLE_*
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public String getAuthority() {
		return this.code;
	}

}
