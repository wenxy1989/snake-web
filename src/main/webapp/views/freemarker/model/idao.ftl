package com.school.book.dao;

import com.base.exception.DaoException;
import com.base.mvc.IBasicDao;
import com.school.book.model.${model.code?cap_first};

import java.util.List;
/**
* version.${now?string("yyyy.MM.dd")}
*/
public interface I${model.code?cap_first}Dao extends IBasicDao<${model.code?cap_first}>{
<#list parameters as obj>
    <#if obj.keyType==1>

    public ${model.code?cap_first} selectOneBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws DaoException;
    <#elseif obj.keyType==2>

    public List<${model.code?cap_first}> selectListBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws DaoException;
    </#if>
</#list>
}
