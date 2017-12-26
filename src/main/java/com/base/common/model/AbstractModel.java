package com.base.common.model;

import java.io.Serializable;

public abstract class AbstractModel {
	
	public abstract Serializable getObjectId();
	/**
	 * 唯一的id域名
	 * 相同域名的类的唯一的id不重复
	 * @return
	 */
	public abstract String getUniqueIdName();
	
	private Long id;
	private String name;
	private String title;
	private Long createId;
	private String createTime;
	private Integer status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public String getCreateTime() {
		/*if(StringUtils.isEmpty(this.createTime)){
			this.createTime = TimeTools.now();
		}*/
        //todo
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getBranchName(){
		return "model";
	}
	
	public StringBuffer getUrlCondition(){
		StringBuffer sb = new StringBuffer();
		if(null != id){
			sb.append("&id=");
			sb.append(id);
		}
		if(null != name && name.trim().length() > 0){
			sb.append("&name=");
			sb.append(name);
		}
		if(null != title && title.trim().length() > 0){
			sb.append("&title=");
			sb.append(title);
		}
		if(null != createTime && createTime.trim().length() > 0){
			sb.append("&createTime=");
			sb.append(createTime);
		}
		if(null != status){
			sb.append("&status=");
			sb.append(status);
		}
		return sb;
	}

}
