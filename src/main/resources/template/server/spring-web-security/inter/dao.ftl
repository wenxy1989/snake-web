package com.school.book.dao;

import com.base.exception.DaoException;
import com.base.mvc.MybatisBasicDao;
import com.school.book.model.${model.javaName?cap_first};
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* version.${now}
*/
@Repository("${model.javaName?uncap_first}Dao")
public class ${model.javaName?cap_first}Dao extends MybatisBasicDao<${model.javaName?cap_first}> implements I${model.javaName?cap_first}Dao {

    public ${model.javaName?cap_first}Dao() {
        super(${model.javaName?cap_first}.class);
    }
<#list parameters as p>
    <#if p.keyType==1>

    public ${model.javaName?cap_first} selectOneBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws DaoException{
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("${p.code}_",${p.code});
        return selectOne(map);
    }
    <#elseif obj.keyType==2>

    public List<${model.javaName?cap_first}> selectListBy${p.code?cap_first}(${p.type?cap_first} ${p.code}) throws DaoException{
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("${p.code}_",${p.code});
        return selectList(map);
    }
    </#if>
</#list>
}
