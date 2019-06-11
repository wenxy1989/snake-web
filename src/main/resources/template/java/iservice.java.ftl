package com.${application?uncap_first}.${model.code?uncap_first}.service;

import com.base.service.IBasicService;
import com.base.exception.ServiceException;
import com.${application?uncap_first}.${model.code?uncap_first}.model.${model.code?cap_first};

import java.util.List;

public interface I${model.code?cap_first}Service extends IBasicService<${model.code?cap_first}> {

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return ${model.code?cap_first}
	 */
    public ${model.code?cap_first} getObjectBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException;
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException;
    <#elseif attribute.useType == 'onetomany'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return List<${model.code?cap_first}>
	 */
    public List<${model.code?cap_first}> getListBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException;
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException;
	</#if>
	</#list>

    <#if actions?exists>
	<#list actions as action>
    <#if action.actionSql?exists>
	/**
	 *${action.name}
	 */
    public ${action.returnType} ${action.code}(${action.paramNames}) throws ServiceException;
    </#if>
    </#list>
    </#if>

}