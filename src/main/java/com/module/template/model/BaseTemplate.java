package com.module.template.model;

import com.base.common.model.AbstractModel;

import java.io.Serializable;

public class BaseTemplate extends AbstractModel{
	
	private String templateType;//模板框架类型
	private String moduleName;//中文：模块名称
	private String templateName;//模板名，带后缀
	private String savePathModel;//模块路径模板
	private String saveFileModel;//模块文件名模板

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSavePathModel() {
		return savePathModel;
	}

	public void setSavePathModel(String savePathModel) {
		this.savePathModel = savePathModel;
	}

	public String getSaveFileModel() {
		return saveFileModel;
	}

	public void setSaveFileModel(String saveFileModel) {
		this.saveFileModel = saveFileModel;
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