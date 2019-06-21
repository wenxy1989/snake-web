package com.${application?uncap_first}.${model.javaName?uncap_first}.service;

import com.${application?uncap_first}.${model.javaName?uncap_first}.model.${model.javaName?cap_first};
import com.${application?uncap_first}.${model.javaName?uncap_first}.dao.I${model.javaName?cap_first}Dao;

import com.base.dao.IBasicDao;
import com.base.service.BasicService;
import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import org.cometd.annotation.Service;

import javax.annotation.Resource;

import java.util.List;

@Service("${model.javaName?uncap_first}Service")
public class ${model.javaName?cap_first}Service extends BasicService<${model.javaName?cap_first}> implements I${model.javaName?cap_first}Service {

	@Resource(name="${model.javaName?uncap_first}Dao")
	private I${model.javaName?cap_first}Dao ${model.javaName?uncap_first}Dao;

    @Override
    public IBasicDao<${model.javaName?cap_first}> getDao() {
        return ${model.javaName?uncap_first}Dao;
    }

    public List<${model.javaName?cap_first}> getAll() throws ServiceException {
    	try{
        	return ${model.javaName?uncap_first}Dao.getAll();
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

    public void create(${model.javaName?cap_first} ${model.javaName?uncap_first}) throws ServiceException {
    	try{
        	${model.javaName?uncap_first}Dao.create(${model.javaName?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

    public void update(${model.javaName?cap_first} ${model.javaName?uncap_first}) throws ServiceException {
    	try{
        	${model.javaName?uncap_first}Dao.update(${model.javaName?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return ${model.javaName?cap_first}
	 */
    public ${model.javaName?cap_first} getObjectBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		return ${model.javaName?uncap_first}Dao.getObjectBy${attribute.code?cap_first}(${attribute.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		${model.javaName?uncap_first}Dao.deleteBy${attribute.code?cap_first}(${attribute.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }
    <#elseif attribute.useType == 'onetomany'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return List<${model.javaName?cap_first}>
	 */
    public List<${model.javaName?cap_first}> getListBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		return ${model.javaName?uncap_first}Dao.getListBy${attribute.code?cap_first}(${attribute.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		${model.javaName?uncap_first}Dao.deleteBy${attribute.code?cap_first}(${attribute.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }
	</#if>
	</#list>
    
    <#if actions?exists>
	<#list actions as action>
    <#if action.actionSql?exists>
    public ${action.returnType} ${action.code}(${action.paramNames}){
    	String hql = "${action.actionSql}";
    	<#if action.returnType!='void'>return </#if>dao.find(hql,${action.paramNames});
    }
    </#if>
    </#list>
    </#if>

}
