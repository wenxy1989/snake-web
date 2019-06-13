package com.web.${app.code}.entity;
/**
 * ${model.code?cap_first}
 * @author {author}
 * @version 1.00 ,Date: ${now}
 */
public class ${model.code?cap_first }{

	private Long id;
	<#if parameters?exists>
	<#list parameters as attribute>
	private ${attribute.type} ${attribute.code};//${attribute.name}
	</#list>
	</#if>
    private String updatedTime;
    private String createdTime;
    private Long creatorId;

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	<#if parameters?exists>
	<#list parameters as attribute>
	public ${attribute.type} get${attribute.code?cap_first}(){
		return this.${attribute.code};
	}

	public void set${attribute.code?cap_first}(${attribute.type} ${attribute.code}){
		this.${attribute.code} = ${attribute.code};
	}
	</#list>
	</#if>

	public String getUpdatedTime(){
		return this.updatedTime;
	}

	public void setUpdatedTime(String updatedTime){
		this.updatedTime = updatedTime;
	}
	public String getCreatedTime(){
		return this.createdTime;
	}

	public void setCreatedTime(String createdTime){
		this.createdTime = createdTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}

}