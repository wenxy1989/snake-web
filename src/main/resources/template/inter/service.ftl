package com.school.book.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.mvc.BasicService;
import com.school.book.dao.I${model.code?cap_first}Dao;
import com.school.book.model.${model.code?cap_first};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
/**
* version.${now}
*/
public class ${model.code?cap_first}Service extends BasicService<${model.code?cap_first}> implements I${model.code?cap_first}Service {

    @Autowired
    @Qualifier("${model.code?uncap_first}Dao")
    private I${model.code?cap_first}Dao dao;

    @Override
    public I${model.code?cap_first}Dao getDao() {
        return this.dao;
    }
<#list parameters as obj>
    <#if obj.keyType==1>

    public ${model.code?cap_first} getObjectBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws ServiceException{
        try{
            return getDao().selectOneBy${obj.code?cap_first}(${obj.code});
        }catch(DaoException e){
            throw new ServiceException(e);
        }
    }
    <#elseif obj.keyType==2>

    public List<${model.code?cap_first}> getListBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws ServiceException{
        try{
            return getDao().selectListBy${obj.code?cap_first}(${obj.code});
        }catch(DaoException e){
            throw new ServiceException(e);
        }
    }
    </#if>
</#list>
}
