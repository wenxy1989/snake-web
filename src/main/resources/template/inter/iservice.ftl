package com.school.book.service;


import com.base.exception.ServiceException;
import com.base.mvc.IBasicService;
import com.school.book.model.${model.code?cap_first};

import java.util.List;
/**
* version.${now}
*/
public interface I${model.code?cap_first}Service extends IBasicService<${model.code?cap_first}> {
<#list parameters as obj>
    <#if obj.keyType==1>

    public ${model.code?cap_first} getObjectBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws ServiceException;
    <#elseif obj.keyType==2>

    public List<${model.code?cap_first}> getListBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws ServiceException;
    </#if>
</#list>

}
