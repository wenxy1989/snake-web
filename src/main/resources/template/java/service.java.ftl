package com.${application?uncap_first}.${module?uncap_first}.service;

import com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first};
import com.${application?uncap_first}.${module?uncap_first}.dao.I${module?cap_first}Dao;

import com.base.dao.IBasicDao;
import com.base.service.BasicService;
import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import org.cometd.annotation.Service;

import javax.annotation.Resource;

import java.util.List;

@Service("${module?uncap_first}Service")
public class ${module?cap_first}Service extends BasicService<${module?cap_first}> implements I${module?cap_first}Service {

	@Resource(name="${module?uncap_first}Dao")
	private I${module?cap_first}Dao ${module?uncap_first}Dao;

    @Override
    public IBasicDao<${module?cap_first}> getDao() {
        return ${module?uncap_first}Dao;
    }

    public List<${module?cap_first}> getAll() throws ServiceException {
    	try{
        	return ${module?uncap_first}Dao.getAll();
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

    public void create(${module?cap_first} ${module?uncap_first}) throws ServiceException {
    	try{
        	${module?uncap_first}Dao.create(${module?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

    public void update(${module?cap_first} ${module?uncap_first}) throws ServiceException {
    	try{
        	${module?uncap_first}Dao.update(${module?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return ${module?cap_first}
	 */
    public ${module?cap_first} getObjectBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		return ${module?uncap_first}Dao.getObjectBy${attribute.code?cap_first}(${attribute.code?uncap_first});
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
    		${module?uncap_first}Dao.deleteBy${attribute.code?cap_first}(${attribute.code?uncap_first});
        }catch(DaoException e){
        	throw new ServiceException(e);
        }
    }
    <#elseif attribute.useType == 'onetomany'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return List<${module?cap_first}>
	 */
    public List<${module?cap_first}> getListBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws ServiceException{
    	try{
    		return ${module?uncap_first}Dao.getListBy${attribute.code?cap_first}(${attribute.code?uncap_first});
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
    		${module?uncap_first}Dao.deleteBy${attribute.code?cap_first}(${attribute.code?uncap_first});
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
