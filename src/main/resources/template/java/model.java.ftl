package com.${application}.${model.code}.model;
/**
 * ${model.code}实体类 ${model.code?cap_first}
 * @author {author}
 * @version 1.00 ,Date: ${now}
 */
public class ${model.code?cap_first }{

	private Long id;
	<#if attributes?exists>
	<#list attributes as attribute>
	private ${attribute.javaType} ${attribute.code};//${attribute.name}
	</#list>
	</#if>
	private String createdTime;
	private Long creatorId;

	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	<#if attributes?exists>
	<#list attributes as attribute>
	/**
	 * ${attribute.name}
	 * get value of ${attribute.code}
	 * @return ${attribute.code} ${attribute.javaType}
	 **/
	public ${attribute.javaType} get${attribute.code?cap_first}(){
		return this.${attribute.code};
	}
	
	/**
	 * ${attribute.name}
	 * set value of ${attribute.code}
	 * @param ${attribute.code}
	 **/
	public void set${attribute.code?cap_first}(${attribute.javaType} ${attribute.code}){
		this.${attribute.code} = ${attribute.code};
	}
	</#list>
	</#if>

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