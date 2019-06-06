package com.snake.template.model;


/**
 * 属性模块实体类 Attribute
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 10:39:20
 */
public class Action extends BasicModel {

	private String code;
	private Long moduleId;
	private String returnType;
	private String paramTypes;
	private String paramNames;
	private String actionSql;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(String paramTypes) {
		this.paramTypes = paramTypes;
	}

	public String getParamNames() {
		return paramNames;
	}

	public void setParamNames(String paramNames) {
		this.paramNames = paramNames;
	}

	public String getActionSql() {
		return actionSql;
	}

	public void setActionSql(String actionSql) {
		this.actionSql = actionSql;
	}

}