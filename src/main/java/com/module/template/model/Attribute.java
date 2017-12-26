package com.module.template.model;

import java.io.Serializable;

import com.base.common.model.AbstractModel;
/**
 * 属性模块实体类 Attribute
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 10:39:20
 */
public class Attribute extends AbstractModel{

	private String code;
	private String type;
	private Long moduleId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
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