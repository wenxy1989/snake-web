package com.model.${moduleCode}.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.model.${moduleCode}.${className};

@Service("${moduleCode}Service")
public class ${className}Service extends AbstractService<${className}> implements I${className}Service {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

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
