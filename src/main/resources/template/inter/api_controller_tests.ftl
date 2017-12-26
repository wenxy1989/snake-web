package com.school.inter.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.school.book.controller.test.AbstractContextControllerTests;
import com.school.book.data.AbstractTestDataProvider;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
** ${group.name} : ${group.remark}
** create at ${group.createdTime} by user id ${group.creatorId}
**/
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration({"classpath*:**spring-config.xml"})
public class ${group.model?cap_first}APIControllerTests extends AbstractContextControllerTests {

    @Override
    public String getBaseUri() {
        return null;
    }

    @Override
    public AbstractTestDataProvider getDataProvider() {
        return null;
    }

    @Before
    public void init() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    public void versionTest() throws Exception {
        super.version("${group.model?cap_first}APIController.version.${now?string("yyyy.MM.dd")}");
    }
<#list urlList as each>

    @Test
    public void ${each.url}Test() throws Exception {
        JSONObject obj = new JSONObject();
    <#list each.uploadParameters as obj>
    <#if obj.mysqlType??>
        <#if obj.type == "String">
        obj.put("${obj.code?uncap_first}","${obj.example}");//${obj.name}${obj.remark}
        <#elseif obj.type == "Long">
        obj.put("${obj.code?uncap_first}",${obj.example}l);//${obj.name}${obj.remark}
        <#else>
        obj.put("${obj.code?uncap_first}",${obj.example});//${obj.name}${obj.remark}
        </#if>
    <#else>
        JSONObject ${obj.code?uncap_first} = JSONObject.parseObject("${obj.example}");
        obj.put("${obj.code?uncap_first}",${obj.code?uncap_first});//${obj.name}${obj.remark}
    </#if>
    </#list>
        JSONObject result = new JSONObject();
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
        mockMvc
            .perform(
                post("/api/${group.model?uncap_first}/${each.url}")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(obj.toJSONString())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(RESULT_SUCCESS_CONTENT_TYPE))<#if !(each.resultParameters?? && each.resultParameters?size gt 0)>;</#if>
        <#list each.resultParameters as obj>
        <#if obj.mysqlType??>
            <#if obj.type == "String">
            .andExpect(jsonPath("$.${obj.code?uncap_first}").value(result.getString("${obj.code?uncap_first}")))<#if !obj_has_next>;</#if>
            <#elseif obj.type == "Long">
            .andExpect(jsonPath("$.${obj.code?uncap_first}").value(result.getInteger("${obj.code?uncap_first}")))<#if !obj_has_next>;</#if>
            <#else>
            .andExpect(jsonPath("$.${obj.code?uncap_first}").value(result.get${obj.type?cap_first}("${obj.code?uncap_first}")))<#if !obj_has_next>;</#if>
            </#if>
        <#else>
            .andExpect(jsonPath("$.${obj.code?uncap_first}").exists())<#if !obj_has_next>;</#if>//todo
        //.andExpect(jsonPath("$.${obj.code?uncap_first}").value(result.getString("${obj.code?uncap_first}")))<#if !obj_has_next>;</#if>
        //.andExpect(jsonPath("$.${obj.code?uncap_first}").value(result.getJSONObject("${obj.code?uncap_first}").toString()))<#if !obj_has_next>;</#if>
        </#if>
        </#list>
    }
</#list>

    @Ignore
    @Test
    public void basicTest() throws Exception {
    }

}