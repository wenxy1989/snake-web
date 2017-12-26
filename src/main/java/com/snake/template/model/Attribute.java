package com.snake.template.model;


import com.snake.inter.model.ModelParameter;

/**
 * 属性模块实体类 Attribute
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 10:39:20
 */
public class Attribute extends BasicModel {

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

}