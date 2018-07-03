package com.snake.template.model;

import com.snake.inter.model.ModelParameter;
import org.apache.ibatis.annotations.Update;

public class Template{

	private Long id;
	private String group;//中文：模块名称
	private String name;//模板名，带后缀
	private String type;//模板框架类型
	private Integer updateType;//0-每次更新1-没有则创建
	private String savePathModel;//模块路径模板
	private String saveFileModel;//模块文件名模板
	private String remark;//中文：模块名称
	private String createdTime;
	private Long creatorId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUpdateType() {
		return updateType;
	}

	public void setUpdateType(Integer updateType) {
		this.updateType = updateType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
}