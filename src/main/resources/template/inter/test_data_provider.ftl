package com.school.book.data;

import com.alibaba.fastjson.JSONObject;
import com.base.model.Condition;
import com.base.model.Criteria;
import com.base.model.SimpleCriteria;
import com.school.book.model.${model.code?cap_first};

import java.util.*;

/**
** ${model.name} : ${model.remark}
** create at ${model.createdTime} by user id ${model.creatorId}
** test data provider
*/
public class ${model.code?cap_first}TestDataProvider extends AbstractTestDataProvider<${model.code?cap_first}> {

    public static final Long TEST_PARAMETER_ID = 1l;
    public static final Long TEST_PARAMETER_ID2 = 10l;
    public static final Integer TEST_EXTEND_VALUE = 10;

    @Override
    public String getVersion() {
        return "${model.code?cap_first}Controller.version.${now}";
    }

    @Override
    public ${model.code?cap_first} createObject() {
        ${model.code?cap_first} object = new ${model.code?cap_first}();
    <#list parameters as obj>
        <#if obj.type == "String">
        object.set${obj.code?cap_first}("${obj.example}");//${obj.name}${obj.remark}
        <#elseif obj.type == "Long">
        object.set${obj.code?cap_first}(${obj.example}l);//${obj.name}${obj.remark}
        <#else>
        object.set${obj.code?cap_first}(${obj.example});//${obj.name}${obj.remark}
        </#if>
        </#list>
        return object;
    }

    @Override
    public ${model.code?cap_first} createObject(Long id) {
        ${model.code?cap_first} object = new ${model.code?cap_first}();
        object.setId(id);
    <#list parameters as obj>
        <#if obj.type == "String">
        object.set${obj.code?cap_first}("${obj.example}");//${obj.name}${obj.remark}
        <#elseif obj.type == "Long">
        object.set${obj.code?cap_first}(${obj.example}l);//${obj.name}${obj.remark}
        <#else>
        object.set${obj.code?cap_first}(${obj.example});//${obj.name}${obj.remark}
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
    <#list parameters as obj>
        <#if obj.type == "String">
        obj.put("${obj.code?uncap_first}","${obj.example}");//${obj.name}${obj.remark}
        <#elseif obj.type == "Long">
        obj.put("${obj.code?uncap_first}",${obj.example}l);//${obj.name}${obj.remark}
        <#else>
        obj.put("${obj.code?uncap_first}",${obj.example});//${obj.name}${obj.remark}
        </#if>
    </#list>
        obj.put("extendOne",TEST_EXTEND_VALUE);
        obj.put("extendTwo",TEST_EXTEND_VALUE);
        obj.put("extendThree",TEST_EXTEND_VALUE);
        obj.put("extendFour",TEST_EXTEND_VALUE);
        return obj;
    }-->

    public List<${model.code?cap_first}> createList() {
        List<${model.code?cap_first}> list = new ArrayList<${model.code?cap_first}>();
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
    <#list parameters as obj>
        <#if obj.type == "String">
        c.addCondition(0, new Condition("${obj.code?uncap_first}_", "=", "${obj.example}"));
        <#else>
        c.addCondition(0, new Condition("${obj.code?uncap_first}_", "=", ${obj.example}));
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
    <#list parameters as obj>
        <#if obj.type == "String">
        map.put("${obj.code?uncap_first}_", "${obj.example}");
        <#else>
        map.put("${obj.code?uncap_first}_", ${obj.example});
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
    <#list parameters as obj>
        <#if obj.type == "String">
        map.put("${obj.code?uncap_first}", "${obj.example}");
        <#else>
        map.put("${obj.code?uncap_first}", ${obj.example});
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
