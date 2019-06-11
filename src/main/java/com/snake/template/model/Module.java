package com.snake.template.model;


import java.util.Set;

public class Module extends BasicModel {
	
	private String className;//模块实体类名
	private Set<TemplateConfig> templates;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<TemplateConfig> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<TemplateConfig> templates) {
		this.templates = templates;
	}

}
