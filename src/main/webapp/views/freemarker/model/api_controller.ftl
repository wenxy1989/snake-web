package com.school.inter.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.exception.ServiceException;
import com.base.mvc.BasicController;
//import com.school.inter.service.I${group.model?cap_first}APIService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
** ${group.name} : ${group.remark}
** create at ${group.createdTime} by user id ${group.creatorId}
**/
@Controller
@RequestMapping("/api/${group.model?uncap_first}/")
public class ${group.model?cap_first}APIController extends BasicController {

    <#--@Resource(name = "${group.model?uncap_first}APIService")-->
    <#--private I${group.model?cap_first}APIService ${group.model?uncap_first}APIService;-->

    @ResponseBody
    @RequestMapping(value = "version")
    public Object version() {
        return "${group.model?cap_first}APIController.version.${now?string("yyyy.MM.dd")}";
    }
<#list urlList as each>

    @ResponseBody
    <#--@RequestMapping(value = "${each.url}", method = RequestMethod.<#switch each.type><#case 0>GET<#break><#case 1>POST<#break><#case 2>PUT<#break><#case 3>DELETE<#break><#case 4>STREAM<#break></#switch>)-->
    @RequestMapping(value = "${each.url}", method = RequestMethod.POST)
    public Object ${each.url}(@RequestBody JSONObject obj) {
        JSONObject result = new JSONObject();
        try {
        <#list each.uploadParameters as obj>
            ${obj.type?cap_first} ${obj.code?uncap_first} = obj.get${obj.type?cap_first}("${obj.code?uncap_first}");//${obj.name}${obj.remark}
        </#list>
        //result = ${group.model?cap_first}ApiService.${each.url}(obj);
        <#list each.resultParameters as obj>
        <#if obj.mysqlType??>
            <#if obj.type == "String">
            result.put("${obj.code?uncap_first}","${obj.example}");//${obj.name}${obj.remark}
            <#elseif obj.type == "Long">
            result.put("${obj.code?uncap_first}",${obj.example}l);//${obj.name}${obj.remark}
            <#else>
            result.put("${obj.code?uncap_first}",${obj.example});//${obj.name}${obj.remark}
            </#if>
        <#else>
            JSONObject ${obj.code?uncap_first}Json = JSONObject.parseObject("${obj.example?replace("\"","\\\"")}");
            result.put("${obj.code?uncap_first}",${obj.code?uncap_first}Json);//${obj.name}${obj.remark}
        </#if>
        </#list>
            throw new ServiceException();//todo finally remove
        } catch (ServiceException e) {
            logger.error("${group.model?uncap_first}ApiController ${each.url} error",e);
        }
        return result;
    }
</#list>

}