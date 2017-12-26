package com.model.${moduleCode}.service;

import com.base.common.service.BaseService;
import com.model.${moduleCode}.${className};


public interface I${className}Service extends BaseService<${className}> {

    <#if actions?exists>
	<#list actions as action>
    <#if action.actionSql?exists>
	/**
	 *${action.name}
	 */
    public ${action.returnType} ${action.code}(${action.paramNames});
    </#if>
    </#list>
    </#if>

}