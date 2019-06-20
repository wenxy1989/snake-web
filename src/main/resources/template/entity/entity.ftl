package com.web.${app.code}.entity;
/**
 * ${model.javaName?cap_first}
 * @author {author}
 * @version 1.00 ,Date: ${now}
 */
public class ${model.javaName?cap_first }{

	<#if parameters?exists>
	<#list parameters as p>
	private ${p.type} ${p.javaName};//${p.name}
	</#list>
	</#if>
	<#if parameters?exists>
	<#list parameters as p>

	public ${p.type} get${p.javaName?cap_first}(){
		return this.${p.javaName};
	}

	public void set${p.javaName?cap_first}(${p.type} ${p.javaName}){
		this.${p.javaName} = ${p.javaName};
	}
	</#list>
	</#if>

}