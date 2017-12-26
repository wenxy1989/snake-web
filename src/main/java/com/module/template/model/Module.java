package com.module.template.model;

import java.io.Serializable;
import java.util.Set;

import com.base.common.model.AbstractModel;

public class Module extends AbstractModel {
	
	private String className;//模块实体类名
	private Set<BaseTemplate> templates;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<BaseTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<BaseTemplate> templates) {
		this.templates = templates;
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
