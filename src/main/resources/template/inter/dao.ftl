package com.school.book.dao;

import com.base.exception.DaoException;
import com.base.mvc.MybatisBasicDao;
import com.school.book.model.${model.code?cap_first};
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* version.${now?string("yyyy.MM.dd")}
*/
@Repository("${model.code?uncap_first}Dao")
public class ${model.code?cap_first}Dao extends MybatisBasicDao<${model.code?cap_first}> implements I${model.code?cap_first}Dao {

    public ${model.code?cap_first}Dao() {
        super(${model.code?cap_first}.class);
    }
<#list parameters as obj>
    <#if obj.keyType==1>

    public ${model.code?cap_first} selectOneBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws DaoException{
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("${obj.code}_",${obj.code});
        return selectOne(map);
    }
    <#elseif obj.keyType==2>

    public List<${model.code?cap_first}> selectListBy${obj.code?cap_first}(${obj.type?cap_first} ${obj.code}) throws DaoException{
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("${obj.code}_",${obj.code});
        return selectList(map);
    }
    </#if>
</#list>
}
