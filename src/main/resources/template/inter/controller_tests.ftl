<#macro checkObject parameterList resultActions="result">
    <#list parameterList as obj>
        <#if obj.type == "String">
        ${resultActions}.andExpect(jsonPath("$.${obj.code?uncap_first}").value(json.getString("${obj.code?uncap_first}")));
        <#elseif obj.type == "Long">
        ${resultActions}.andExpect(jsonPath("$.${obj.code?uncap_first}").value(json.getInteger("${obj.code?uncap_first}")));
        <#else>
        ${resultActions}.andExpect(jsonPath("$.${obj.code?uncap_first}").value(json.get${obj.type?cap_first}("${obj.code?uncap_first}")));
        </#if>
    </#list>
</#macro>
<#macro checkList parameterList resultActions="result">
    <#list parameterList as obj>
        <#if obj.type == "String">
        ${resultActions}.andExpect(jsonPath("$[0].${obj.code?uncap_first}").value(json.getString("${obj.code?uncap_first}")));
        <#elseif obj.type == "Long">
        ${resultActions}.andExpect(jsonPath("$[0].${obj.code?uncap_first}").value(json.getInteger("${obj.code?uncap_first}")));
        <#else>
        ${resultActions}.andExpect(jsonPath("$[0].${obj.code?uncap_first}").value(json.get${obj.type?cap_first}("${obj.code?uncap_first}")));
        </#if>
    </#list>
</#macro>
<#macro checkPage parameterList resultActions="result">
    <#list parameterList as obj>
        <#if obj.type == "String">
        ${resultActions}.andExpect(jsonPath("$.content[0].${obj.code?uncap_first}").value(json.getString("${obj.code?uncap_first}")));
        <#elseif obj.type == "Long">
        ${resultActions}.andExpect(jsonPath("$.content[0].${obj.code?uncap_first}").value(json.getInteger("${obj.code?uncap_first}")));
        <#else>
        ${resultActions}.andExpect(jsonPath("$.content[0].${obj.code?uncap_first}").value(json.get${obj.type?cap_first}("${obj.code?uncap_first}")));
        </#if>
    </#list>
</#macro>
package com.school.book.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.base.exception.DaoException;
import com.school.book.data.${model.javaName?cap_first}TestDataProvider;
import com.school.book.model.${model.javaName?cap_first};
import com.base.mvc.IBasicDao;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
** ${model.name} : ${model.remark}
** create at ${model.createdTime} by user id ${model.creatorId}
**/
@RunWith(SpringJUnit4ClassRunner.class)
public class Default${model.javaName?cap_first}ControllerTests extends AbstractContextControllerTests<${model.javaName?cap_first}> {

    @Override
    public String getBaseUri() {
        return "/default/${model.javaName?uncap_first}/";
    }

    @Override
    public ${model.javaName?cap_first}TestDataProvider getDataProvider() {
        return new ${model.javaName?cap_first}TestDataProvider();
    }

    @Before
    public void init() throws DaoException {
        this.mockMvc = webAppContextSetup(this.wac).build();
        IBasicDao dao = (IBasicDao) wac.getBean("${model.javaName?uncap_first}Dao");
        dao.clean();
    }

    @Test
    public void versionTest() throws Exception {
        super.version(getDataProvider().getVersion());
    }

    @Ignore
    @Test
    public void createTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.create(obj);
    }

    @Ignore
    @Test
    public void updateTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.update(obj);
    }

    @Ignore
    @Test
    public void getObjectTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.getObject(TEST_PARAMETER_ID)
        <#list parameters as p>
            <#if p.type == "String">
            .andExpect(jsonPath("$.${p.code?uncap_first}").value(obj.getString("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#elseif p.type == "Long">
            .andExpect(jsonPath("$.${p.code?uncap_first}").value(obj.getInteger("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#else>
            .andExpect(jsonPath("$.${p.code?uncap_first}").value(obj.get${p.type?cap_first}("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            </#if>
        </#list>
    }

    @Ignore
    @Test
    public void searchEqualsTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.searchEquals(obj)
        <#list parameters as p>
            <#if p.type == "String">
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.getString("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#elseif p.type == "Long">
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.getInteger("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#else>
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.get${p.type?cap_first}("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            </#if>
        </#list>
    }

    @Ignore
    @Test
    public void searchCountTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.searchCount(obj);
    }

    @Ignore
    @Test
    public void searchListTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.searchList(obj)
        <#list parameters as p>
            <#if obj.type == "String">
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.getString("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#elseif obj.type == "Long">
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.getInteger("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#else>
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.get${p.type?cap_first}("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            </#if>
        </#list>
    }

    @Ignore
    @Test
    public void queryCountTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.queryCount(obj);
    }

    @Ignore
    @Test
    public void queryListTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.queryList(obj)
        <#list parameters as p>
            <#if p.type == "String">
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.getString("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#elseif p.type == "Long">
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.getInteger("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#else>
            .andExpect(jsonPath("$[0].${p.code?uncap_first}").value(obj.get${p.type?cap_first}("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            </#if>
        </#list>
    }

    @Ignore
    @Test
    public void searchPageTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.searchPage(obj)
        <#list parameters as p>
            <#if p.type == "String">
            .andExpect(jsonPath("$.content[0].${p.code?uncap_first}").value(obj.getString("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#elseif p.type == "Long">
            .andExpect(jsonPath("$.content[0].${p.code?uncap_first}").value(obj.getInteger("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#else>
            .andExpect(jsonPath("$.content[0].${p.code?uncap_first}").value(obj.get${p.type?cap_first}("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            </#if>
        </#list>
    }

    @Ignore
    @Test
    public void queryPageTest() throws Exception {
        JSONObject obj = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        super.queryPage(obj)
        <#list parameters as p>
            <#if p.type == "String">
            .andExpect(jsonPath("$.content[0].${p.code?uncap_first}").value(obj.getString("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#elseif p.type == "Long">
            .andExpect(jsonPath("$.content[0].${p.code?uncap_first}").value(obj.getInteger("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            <#else>
            .andExpect(jsonPath("$.content[0].${p.code?uncap_first}").value(obj.get${p.type?cap_first}("${p.code?uncap_first}")))<#if !p_has_next>;</#if>
            </#if>
        </#list>
    }

    @Ignore
    @Test
    public void logicDeleteTest() throws Exception {
        super.logicDelete(TEST_PARAMETER_ID);
    }

    @Ignore
    @Test
    public void deleteTest() throws Exception {
        super.delete(TEST_PARAMETER_ID);
    }
<#list parameters as p>
    <#if p.keyType==1>

    @Ignore
    @Test
    public void getBy${p.code?cap_first}Test() throws Exception {
    JSONObject json = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        ResultActions resultActions = getMockMvc()
            .perform(
                post(getBaseUri() + "getBy${p.code?cap_first}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(RESULT_SUCCESS_CONTENT_TYPE))
            .andExpect(jsonPath("$").exists());
        <@checkObject parameterList=parameters resultActions="resultActions"/>
    }
    <#elseif p.keyType==2>

    @Ignore
    @Test
    public void getListBy${p.code?cap_first}Test() throws Exception {
        JSONObject json = getDataProvider().createObjectJson(TEST_PARAMETER_ID);
        ResultActions resultActions = getMockMvc()
            .perform(
                post(getBaseUri() + "getListBy${p.code?cap_first}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(RESULT_SUCCESS_CONTENT_TYPE))
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$").isArray());
    <@checkList parameterList=parameters resultActions="resultActions"/>
    }
    </#if>
</#list>

    @Test
    public void basicTest() throws Exception {
        deleteTest();
        versionTest();
        createTest();
        updateTest();
        getObjectTest();
        searchEqualsTest();
        searchCountTest();
        searchListTest();
        //queryCountTest();
        //queryListTest();
        searchPageTest();
        //queryPageTest();
        logicDeleteTest();
<#list parameters as p>
    <#if p.keyType==1>
        getBy${p.code?cap_first}Test();
    <#elseif p.keyType==2>
        getListBy${p.code?cap_first}Test();
    </#if>
</#list>
        deleteTest();
    }

}