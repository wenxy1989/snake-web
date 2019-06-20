package com.school.book.model;

import com.base.model.Model;
/**
** ${model.name} : ${model.remark}
** create at ${model.createdTime} by user id ${model.creatorId}
** version.${now}
**/
public class ${model.javaName?cap_first} extends Model{
    <#list parameters as p>
    private ${p.type?cap_first} ${p.code?uncap_first};//<#if p.length??>length ${p.length}</#if> ${p.name} ${p.remark}<#if p.createdTime??> create at ${p.createdTime}</#if><#if p.creatorId??> by user id ${p.creatorId}</#if>
    </#list>
    <#list parameters as p>

    public void set${p.code?cap_first}(${p.type?cap_first} ${p.code?uncap_first}){
        this.${p.code?uncap_first} = ${p.code?uncap_first};
    }

    public ${p.type?cap_first} get${p.code?cap_first}(){
        return this.${p.code?uncap_first};
    }
    </#list>
}