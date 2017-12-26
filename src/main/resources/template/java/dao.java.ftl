package com.${application?uncap_first}.${module?uncap_first}.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first};

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("${module?uncap_first}Dao")
public class ${module?cap_first}Dao extends MybatisBasicDao<${module?cap_first}> implements I${module?cap_first}Dao {

    public ${module?cap_first}Dao() {
        super(${module?cap_first}.class);
    }

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return ${module?cap_first}
	 */
    public ${module?cap_first} getObjectBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException{
    	try{
    		return sqlSession.selectOne("com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first}.getObjectBy${attribute.code?cap_first}",${attribute.code?uncap_first});
    	}catch(Exception e){
    		throw new DaoException(e);
    	}
    }
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException{
    	try{
    		sqlSession.delete("com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first}.deleteBy${attribute.code?cap_first}",${attribute.code?uncap_first});
    	}catch(Exception e){
    		throw new DaoException(e);
    	}
    }
    <#elseif attribute.useType == 'onetomany'>
	/**
	 *@param ${attribute.code?uncap_first} 
	 *@return List<${module?cap_first}>
	 */
    public List<${module?cap_first}> getListBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException{
    	try{
    		return sqlSession.selectList("com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first}.getListBy${attribute.code?cap_first}",${attribute.code?uncap_first});
    	}catch(Exception e){
    		throw new DaoException(e);
    	}
    }
    
    /**
	 *@param ${attribute.code?uncap_first} 
	 *@return void
	 */
    public void deleteBy${attribute.code?cap_first}(${attribute.javaType} ${attribute.code?uncap_first}) throws DaoException{
    	try{
    		sqlSession.delete("com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first}.deleteBy${attribute.code?cap_first}",${attribute.code?uncap_first});
    	}catch(Exception e){
    		throw new DaoException(e);
    	}
    }
	</#if>
	</#list>

    <#if actions?exists>
	<#list actions as action>
    <#if action.actionSql?exists>
	/**
	 *${action.name}
	 */
    public ${action.returnType} ${action.code}(${action.paramNames}) throws DaoException{
    	try{
    		sqlSession.selectList("com.${application?uncap_first}.${module?uncap_first}.model.${module?cap_first}.${action.code}",${action.paramNames});
    	}catch(Exception e){
    		throw new DaoException(e);
    	}
    }
    </#if>
    </#list>
    </#if>

}