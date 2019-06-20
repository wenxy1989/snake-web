package com.school.book.data;

import com.alibaba.fastjson.JSONObject;
import com.base.model.Condition;
import com.base.model.Criteria;
import com.base.model.SimpleCriteria;
import com.school.book.model.${model.javaName?cap_first};

import java.util.*;

/**
** ${model.name} : ${model.remark}
** create at ${model.createdTime} by user id ${model.creatorId}
** test data provider
*/
public class ${model.javaName?cap_first}TestDataProvider extends AbstractTestDataProvider<${model.javaName?cap_first}> {

    public static final Long TEST_PARAMETER_ID = 1l;
    public static final Long TEST_PARAMETER_ID2 = 10l;
    public static final Integer TEST_EXTEND_VALUE = 10;

    @Override
    public String getVersion() {
        return "${model.javaName?cap_first}Controller.version.${now}";
    }

    @Override
    public ${model.javaName?cap_first} createObject() {
        ${model.javaName?cap_first} object = new ${model.javaName?cap_first}();
    <#list parameters as p>
        <#if p.type == "String">
        object.set${p.code?cap_first}("${p.example}");//${p.name}${p.remark}
        <#elseif p.type == "Long">
        object.set${p.code?cap_first}(${p.example}l);//${p.name}${p.remark}
        <#else>
        object.set${p.code?cap_first}(${p.example});//${p.name}${p.remark}
        </#if>
        </#list>
        return object;
    }

    @Override
    public ${model.javaName?cap_first} createObject(Long id) {
        ${model.javaName?cap_first} object = new ${model.javaName?cap_first}();
        object.setId(id);
    <#list parameters as p>
        <#if p.type == "String">
        object.set${p.code?cap_first}("${p.example}");//${p.name}${p.remark}
        <#elseif p.type == "Long">
        object.set${p.code?cap_first}(${p.example}l);//${p.name}${p.remark}
        <#else>
        object.set${p.code?cap_first}(${p.example});//${p.name}${p.remark}
        </#if>
    </#list>
        object.setExtendOne(TEST_EXTEND_VALUE);
        object.setExtendTwo(TEST_EXTEND_VALUE);
        object.setExtendThree(TEST_EXTEND_VALUE);
        object.setExtendFour(TEST_EXTEND_VALUE);
        return object;
    }
<#--
    public JSONObject getJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("id",TEST_PARAMETER_ID);
    <#list parameters as p>
        <#if p.type == "String">
        obj.put("${p.code?uncap_first}","${p.example}");//${p.name}${p.remark}
        <#elseif p.type == "Long">
        obj.put("${p.code?uncap_first}",${p.example}l);//${p.name}${p.remark}
        <#else>
        obj.put("${p.code?uncap_first}",${p.example});//${p.name}${p.remark}
        </#if>
    </#list>
        obj.put("extendOne",TEST_EXTEND_VALUE);
        obj.put("extendTwo",TEST_EXTEND_VALUE);
        obj.put("extendThree",TEST_EXTEND_VALUE);
        obj.put("extendFour",TEST_EXTEND_VALUE);
        return obj;
    }-->

    public List<${model.javaName?cap_first}> createList() {
        List<${model.javaName?cap_first}> list = new ArrayList<${model.javaName?cap_first}>();
        list.add(createObject(TEST_PARAMETER_ID));
        <#--list.add(createObject(TEST_PARAMETER_ID2));-->
        return list;
    }

    public Criteria getCriteria() {
        Criteria c = new SimpleCriteria();
        c.addCondition(0, new Condition("id_", "=", TEST_PARAMETER_ID));
        c.addCondition(0, new Condition("extend_one", "=", TEST_EXTEND_VALUE));
        c.addCondition(0, new Condition("extend_two", "=", TEST_EXTEND_VALUE));
        c.addCondition(0, new Condition("extend_three", "=", TEST_EXTEND_VALUE));
        c.addCondition(0, new Condition("extend_four", "=", TEST_EXTEND_VALUE));
    <#list parameters as p>
        <#if p.type == "String">
        c.addCondition(0, new Condition("${p.code?uncap_first}_", "=", "${p.example}"));
        <#else>
        c.addCondition(0, new Condition("${p.code?uncap_first}_", "=", ${p.example}));
        </#if>
    </#list>
        c.addOrder(0, "id_ asc");
        return c;
    }

    public Map<String, Object> getSelectMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id_", TEST_PARAMETER_ID);
        map.put("extend_one", TEST_EXTEND_VALUE);
        map.put("extend_two", TEST_EXTEND_VALUE);
        map.put("extend_three", TEST_EXTEND_VALUE);
        map.put("extend_four", TEST_EXTEND_VALUE);
    <#list parameters as p>
        <#if p.type == "String">
        map.put("${p.code?uncap_first}_", "${p.example}");
        <#else>
        map.put("${p.code?uncap_first}_", ${p.example});
        </#if>
    </#list>
        return map;
    }

    public Map<String, Object> getQueryMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", TEST_PARAMETER_ID);
        map.put("extendOne", TEST_EXTEND_VALUE);
        map.put("extendTwo", TEST_EXTEND_VALUE);
        map.put("extendThree", TEST_EXTEND_VALUE);
        map.put("extendFour", TEST_EXTEND_VALUE);
    <#list parameters as p>
        <#if p.type == "String">
        map.put("${p.code?uncap_first}", "${p.example}");
        <#else>
        map.put("${p.code?uncap_first}", ${p.example});
        </#if>
    </#list>
        return map;
    }

    public Set<Long> getDeleteIdSet(){
        Set<Long> set = new HashSet<Long>();
        set.add(TEST_PARAMETER_ID);
        set.add(TEST_PARAMETER_ID2);
        return set;
    }
}
