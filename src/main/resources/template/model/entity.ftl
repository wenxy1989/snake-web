package com.web.${app.code}.entity;
/**
 * ${model.javaName?cap_first}
 * @author {author}
 * @version 1.00 ,Date: ${now}
 */
public class ${model.javaName?cap_first }{

	private Long id;
	<#if parameters?exists>
	<#list parameters as p>
	private ${p.type} ${p.code};//${p.name}
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
	<#list parameters as p>
	public ${p.type} get${p.code?cap_first}(){
		return this.${p.code};
	}

	public void set${p.code?cap_first}(${p.type} ${p.code}){
		this.${p.code} = ${p.code};
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