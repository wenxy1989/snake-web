package com.${application?uncap_first}.${model.javaName?uncap_first}.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.${application?uncap_first}.${model.javaName?uncap_first}.model.${model.javaName?cap_first};

import java.util.List;

public interface I${model.javaName?cap_first}Dao extends IBasicDao<${model.javaName?cap_first}> {

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return ${model.javaName?cap_first}
	 */
    public ${model.javaName?cap_first} getObjectBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException;
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException;
    <#elseif attribute.useType == 'onetomany'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return List<${model.javaName?cap_first}>
	 */
    public List<${model.javaName?cap_first}> getListBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException;
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException;
	</#if>
	</#list>

    <#if actions?exists>
	<#list actions as action>
    <#if action.actionSql?exists>
	/**
	 *${action.name}
	 */
    public ${action.returnType} ${action.code}(${action.paramNames}) throws DaoException;
    </#if>
    </#list>
    </#if>

}