package com.web.${app.code}.entity;
/**
 * ${model.code?cap_first}
 * @author {author}
 * @version 1.00 ,Date: ${now}
 */
public class ${model.code?cap_first }{

	<#if parameters?exists>
	<#list parameters as attribute>
	private ${attribute.type} ${attribute.code};//${attribute.name}
	</#list>
	</#if>

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

}