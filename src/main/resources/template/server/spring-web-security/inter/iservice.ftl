package com.school.book.service;


import com.base.exception.ServiceException;
import com.base.mvc.IBasicService;
import com.school.book.model.${model.javaName?cap_first};

import java.util.List;
/**
* version.${now}
*/
public interface I${model.javaName?cap_first}Service extends IBasicService<${model.javaName?cap_first}> {
<#list parameters as p>
    <#if p.keyType==1>

    public ${model.javaName?cap_first} getObjectBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws ServiceException;
    <#elseif p.keyType==2>

    public List<${model.javaName?cap_first}> getListBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws ServiceException;
    </#if>
</#list>

}
