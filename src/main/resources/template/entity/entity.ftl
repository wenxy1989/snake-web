package com.web.${application.code}.entity;
/**
 * ${model.code?cap_first}
 * @author {author}
 * @version 1.00 ,Date: {now?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${model.code?cap_first }{

	<#if attributes?exists>
	<#list attributes as attribute>
	private ${attribute.javaType} ${attribute.code};//${attribute.name}
	</#list>
	</#if>

	<#if attributes?exists>
	<#list attributes as attribute>
	public ${attribute.javaType} get${attribute.code?cap_first}(){
		return this.${attribute.code};
	}

	public void set${attribute.code?cap_first}(${attribute.javaType} ${attribute.code}){
		this.${attribute.code} = ${attribute.code};
	}
	</#list>
	</#if>

}