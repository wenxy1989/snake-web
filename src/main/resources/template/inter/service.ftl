package com.school.book.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.mvc.BasicService;
import com.school.book.dao.I${model.javaName?cap_first}Dao;
import com.school.book.model.${model.javaName?cap_first};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
/**
* version.${now}
*/
public class ${model.javaName?cap_first}Service extends BasicService<${model.javaName?cap_first}> implements I${model.javaName?cap_first}Service {

    @Autowired
    @Qualifier("${model.javaName?uncap_first}Dao")
    private I${model.javaName?cap_first}Dao dao;

    @Override
    public I${model.javaName?cap_first}Dao getDao() {
        return this.dao;
    }
<#list parameters as p>
    <#if p.keyType==1>

    public ${model.javaName?cap_first} getObjectBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws ServiceException{
        try{
            return getDao().selectOneBy${p.code?cap_first}(${p.code});
        }catch(DaoException e){
            throw new ServiceException(e);
        }
    }
    <#elseif p.keyType==2>

    public List<${model.javaName?cap_first}> getListBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws ServiceException{
        try{
            return getDao().selectListBy${p.code?cap_first}(${p.code});
        }catch(DaoException e){
            throw new ServiceException(e);
        }
    }
    </#if>
</#list>
}
