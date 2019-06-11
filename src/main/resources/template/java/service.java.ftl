package com.${application?uncap_first}.${model.code?uncap_first}.service;

import com.${application?uncap_first}.${model.code?uncap_first}.model.${model.code?cap_first};
import com.${application?uncap_first}.${model.code?uncap_first}.dao.I${model.code?cap_first}Dao;

import com.base.dao.IBasicDao;
import com.base.service.BasicService;
import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import org.cometd.annotation.Service;

import javax.annotation.Resource;

import java.util.List;

@Service("${model.code?uncap_first}Service")
public class ${model.code?cap_first}Service extends BasicService<${model.code?cap_first}> implements I${model.code?cap_first}Service {

	@Resource(name="${model.code?uncap_first}Dao")
	private I${model.code?cap_first}Dao ${model.code?uncap_first}Dao;

    @Override
    public IBasicDao<${model.code?cap_first}> getDao() {
        return ${model.code?uncap_first}Dao;
    }

    public List<${model.code?cap_first}> getAll() throws ServiceException {
    	try{
        	return ${model.code?uncap_first}Dao.getAll();
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

    public void create(${model.code?cap_first} ${model.code?uncap_first}) throws ServiceException {
    	try{
        	${model.code?uncap_first}Dao.create(${model.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

    public void update(${model.code?cap_first} ${model.code?uncap_first}) throws ServiceException {
    	try{
        	${model.code?uncap_first}Dao.update(${model.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return ${model.code?cap_first}
	 */
    public ${model.code?cap_first} getObjectBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		return ${model.code?uncap_first}Dao.getObjectBy${attribute.code?cap_first}(${attribute.code?uncap_first});
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
    		${model.code?uncap_first}Dao.deleteBy${attribute.code?cap_first}(${attribute.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }
    <#elseif attribute.useType == 'onetomany'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return List<${model.code?cap_first}>
	 */
    public List<${model.code?cap_first}> getListBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		return ${model.code?uncap_first}Dao.getListBy${attribute.code?cap_first}(${attribute.code?uncap_first});
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
    		${model.code?uncap_first}Dao.deleteBy${attribute.code?cap_first}(${attribute.code?uncap_first});
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
