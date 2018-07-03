package com.school.book.service.test;

import com.base.model.Criteria;
import com.school.book.data.${model.code?cap_first}TestDataProvider;
import com.school.book.model.${model.code?cap_first};
import com.school.book.service.I${model.code?cap_first}Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
** ${model.name} : ${model.remark}
** create at ${model.createdTime} by user id ${model.creatorId}
*/
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration({"classpath*:**spring-config.xml"})
public class ${model.code?cap_first}ServiceTests extends AbstractServiceTests<${model.code?cap_first}> {

    private I${model.code?cap_first}Service service;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
    MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        service = (I${model.code?cap_first}Service) wac.getBean("${model.code?uncap_first}Service");
    }

    public I${model.code?cap_first}Service getService() {
        return service;
    }

    @Override
    public ${model.code?cap_first}TestDataProvider getDataProvider() {
        return new ${model.code?cap_first}TestDataProvider();
    }

    public void insertTest() throws Exception {
        ${model.code?cap_first} object = getDataProvider().createObject(TEST_PARAMETER_ID);
        super.create(object);
    }

    public void insertListTest() throws Exception {
        List list = new ArrayList();
        ${model.code?cap_first} object1 = getDataProvider().createObject(TEST_PARAMETER_ID);
        list.add(object1);
        ${model.code?cap_first} object2 = getDataProvider().createObject(TEST_PARAMETER_ID2);
        list.add(object2);
        super.createList(list);
    }

    public void deleteByIdTest() throws Exception {
        Object id = TEST_PARAMETER_ID;
        super.deleteById(id);
    }

    public void updateByIdTest() throws Exception {
        ${model.code?cap_first} object = getDataProvider().createObject(TEST_PARAMETER_ID);
        super.updateById(object);
    }

    public void selectCountTest() throws Exception {
        Criteria c = getDataProvider().getCriteria();
        super.selectCount(c);
    }

    public void selectTotalCountTest() throws Exception {
        super.getTotalCount();
    }

    public void selectMapCountTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Map<String, Object> map = getDataProvider().getQueryMap();
        super.selectMapCount(map);
    }

    public void selectObjectTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Long key = TEST_PARAMETER_ID;
        ${model.code?cap_first} result = super.findObject(key);
        <#list parameters as p>
        Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
    }

    public void findOneByObjectTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        ${model.code?cap_first} result = super.findOne(resource);
        <#list parameters as p>
        Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
    }

    public void selectOneByMapTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Map<String, Object> map = getDataProvider().getSelectMap();
        ${model.code?cap_first} result = super.selectOne(map);
        <#list parameters as p>
        Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
    }

    public void selectAllTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        List<${model.code?cap_first}> list = super.getAll();
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
        <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
        }
    }

    public void selectSomeListTest() {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        int first = 0;
        int limit = 10;
        List<${model.code?cap_first}> list = super.getList(first, limit);
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
        <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
        }
    }

    public void selectListByObjectTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        List<${model.code?cap_first}> list = super.findList(resource);
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
        <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
        }
    }

    public void selectListTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Criteria c = getDataProvider().getCriteria();
        List<${model.code?cap_first}> list = super.selectList(c);
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
        <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
        }
    }

    public void selectListByMapTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Map<String, Object> map = getDataProvider().getSelectMap();
        List<${model.code?cap_first}> list = super.selectList(map);
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
        <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
        }
    }

    public void selectListByLikeTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Map<String, Object> map = getDataProvider().getSelectMap();
        List<${model.code?cap_first}> list = super.selectListByLike(map);
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
        <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
        }
    }

    public void selectListByInTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Map<String, Object> map = getDataProvider().getSelectMap();
        List<${model.code?cap_first}> list = super.selectListByIn(map);
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
        <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
        }
    }

    public void selectMapListTest() throws Exception {
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        Map<String, Object> map = getDataProvider().getQueryMap();
        List<Map<String, Object>> list = super.selectMapList(map);
        Assert.assertNotNull(list);
        for(Map<String, Object> result : list){
        <#list parameters as p>
        <#if p.type=="Double">
            Assert.assertEquals(resource.get${p.code?cap_first}().equals(((BigDecimal)result.get("${p.code?uncap_first}")).doubleValue()),true);
        <#else>
            Assert.assertEquals(resource.get${p.code?cap_first}(),result.get("${p.code?uncap_first}"));
        </#if>
        </#list>
        }
    }

<#list parameters as obj>
    <#if obj.keyType==1>

    public void getObjectBy${obj.code?cap_first}Test() throws Exception{
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        ${model.code?cap_first} result = getService().getObjectBy${obj.code?cap_first}(resource.get${obj.code?cap_first}());
        <#list parameters as p>
        Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
        </#list>
    }
    <#elseif obj.keyType==2>

    public void getListBy${obj.code?cap_first}Test() throws Exception{
        ${model.code?cap_first} resource = getDataProvider().createObject(TEST_PARAMETER_ID);
        List<${model.code?cap_first}> list = getService().getListBy${obj.code?cap_first}(resource.get${obj.code?cap_first}());
        Assert.assertNotNull(list);
        for(${model.code?cap_first} result : list){
            <#list parameters as p>
            Assert.assertEquals(result.get${p.code?cap_first}(),resource.get${p.code?cap_first}());
            </#list>
        }
    }
    </#if>
</#list>

    @Test
    public void basicTest() throws Exception{
        deleteById(TEST_PARAMETER_ID);

        insertTest();

        deleteList();

        insertListTest();

        updateByIdTest();

        selectObjectTest();

        findOneByObjectTest();

        selectOneByMapTest();

        selectSomeListTest();

        selectAllTest();

        selectListByObjectTest();

        selectListByMapTest();

        selectListTest();

        selectListByInTest();

        selectListByLikeTest();

        selectMapListTest();

        selectTotalCountTest();

        selectCountTest();

        selectMapCountTest();
    <#list parameters as obj>
        <#if obj.keyType==1>

        getObjectBy${obj.code?cap_first}Test();
        <#elseif obj.keyType==2>

        getListBy${obj.code?cap_first}Test();
        </#if>
    </#list>

        deleteById(TEST_PARAMETER_ID);

        deleteList();
    }

}
