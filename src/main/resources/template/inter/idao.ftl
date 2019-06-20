package com.school.book.dao;

import com.base.exception.DaoException;
import com.base.mvc.IBasicDao;
import com.school.book.model.${model.javaName?cap_first};

import java.util.List;
/**
* version.${now}
*/
public interface I${model.javaName?cap_first}Dao extends IBasicDao<${model.javaName?cap_first}>{
<#list parameters as p>
    <#if p.keyType==1>

    public ${model.javaName?cap_first} selectOneBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws DaoException;
    <#elseif p.keyType==2>

    public List<${model.javaName?cap_first}> selectListBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws DaoException;
    </#if>
</#list>
}
