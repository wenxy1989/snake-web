package com.model.${moduleCode};

import java.io.Serializable;

import com.base.common.model.AbstractModel;
/**
 * ${moduleName}实体类 ${className}
 * @author ${author}
 * @version 1.00 ,Date: ${date?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${className } extends AbstractModel{

	<#if attributes?exists>
	<#list attributes as attribute>
	private ${attribute.type} ${attribute.code?lower_case};//${attribute.name}
	</#list>
	</#if>

	<#if attributes?exists>
	<#list attributes as attribute>
	/**
	 * ${attribute.name}
	 * get value of ${attribute.code}
	 * @return type ${attribute.type}
	 **/
	public ${attribute.type} get${attribute.code}(){
		return this.${attribute.code?lower_case};
	}
	
	/**
	 * ${attribute.name}
	 * set value of ${attribute.code}
	 * @param type ${attribute.type}
	 **/
	public void set${attribute.code}(${attribute.type} ${attribute.code?lower_case}){
		this.${attribute.code?lower_case} = ${attribute.code?lower_case};
	}
	</#list>
	</#if>

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