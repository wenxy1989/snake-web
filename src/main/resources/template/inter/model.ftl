package com.school.book.model;

import com.base.model.Model;
/**
** ${model.name} : ${model.remark}
** create at ${model.createdTime} by user id ${model.creatorId}
** version.${now?string("yyyy.MM.dd")}
**/
public class ${model.code?cap_first} extends Model{
    <#list parameters as obj>
    private ${obj.type?cap_first} ${obj.code?uncap_first};//<#if obj.length??>length ${obj.length}</#if> ${obj.name} ${obj.remark}<#if obj.createdTime??> create at ${obj.createdTime}</#if><#if obj.creatorId??> by user id ${obj.creatorId}</#if>
    </#list>
    <#list parameters as obj>

    public void set${obj.code?cap_first}(${obj.type?cap_first} ${obj.code?uncap_first}){
        this.${obj.code?uncap_first} = ${obj.code?uncap_first};
    }

    public ${obj.type?cap_first} get${obj.code?cap_first}(){
        return this.${obj.code?uncap_first};
    }
    </#list>
}