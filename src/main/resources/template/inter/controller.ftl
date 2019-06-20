package com.school.book.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.exception.ServiceException;
import com.base.model.Condition;
import com.base.model.Criteria;
import com.base.model.SimpleCriteria;
import com.base.mvc.BasicController;
import com.school.book.model.${model.javaName?cap_first};
import com.school.book.service.I${model.javaName?cap_first}Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
** ${model.name} : ${model.remark}
** create at ${model.createdTime} by user id ${model.creatorId}
**/
@Controller
@RequestMapping("/default/${model.javaName?uncap_first}/")
public class Default${model.javaName?cap_first}Controller extends BasicController {

    @Resource(name = "${model.javaName?uncap_first}Service")
    private I${model.javaName?cap_first}Service ${model.javaName?uncap_first}Service;

    @ResponseBody
    @RequestMapping(value = "version")
    public Object version() {
        return "${model.javaName?cap_first}Controller.version.${now}";
    }

    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Object create(@RequestBody ${model.javaName?cap_first} obj) {
        String result = RESULT_ERROR;
        try {
            obj.setCreateUser(getLoginName());
            obj.setCreateUserId(getLoginUserId());
            ${model.javaName?uncap_first}Service.create(obj);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller create error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Object delete(@RequestBody ${model.javaName?cap_first} obj) {
        String result = RESULT_ERROR;
        Long id = obj.getId();
        try {
            ${model.javaName?uncap_first}Service.deleteById(id);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller delete error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "logic/delete", method = RequestMethod.POST)
    public Object logicDelete(@RequestBody ${model.javaName?cap_first} obj) {
        String result = RESULT_ERROR;
        try {
            obj.setDeleted(1);
            obj.setUpdateUser(getLoginName());
            obj.setUpdateUserId(getLoginUserId());
            ${model.javaName?uncap_first}Service.updateById(obj);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller logic delete error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object update(@RequestBody ${model.javaName?cap_first} obj) {
        String result = RESULT_ERROR;
        try {
            obj.setUpdateUser(getLoginName());
            obj.setUpdateUserId(getLoginUserId());
            ${model.javaName?uncap_first}Service.updateById(obj);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller update error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public Object get(@RequestBody JSONObject json) {
        Long id = json.getLong("id");
        ${model.javaName?cap_first} result = null;
        try {
            result = ${model.javaName?uncap_first}Service.findObject(id);
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller get error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "search/equals", method = RequestMethod.POST)
    public Object searchEquals(@RequestBody JSONObject json) {
        String id = json.getString("id");
        <#list parameters as p>
        String ${p.code?uncap_first} = json.getString("${p.code?uncap_first}");
        </#list>
        List<${model.javaName?cap_first}> list = null;
        try {
            ${model.javaName?cap_first} ${model.javaName?uncap_first} = new ${model.javaName?cap_first}();
            if(StringUtils.isNotBlank(id)){
                ${model.javaName?uncap_first}.setId(Long.valueOf(id));
            }
            <#list parameters as p>
            if(StringUtils.isNotBlank(${p.code?uncap_first})){
            <#if p.type == "String">
                ${model.javaName?uncap_first}.set${p.code?cap_first}(${p.code?uncap_first});
            <#else>
                ${p.type} _value = ${p.type}.valueOf(${p.code?uncap_first});
                ${model.javaName?uncap_first}.set${p.code?cap_first}(_value);
            </#if>
            }
            </#list>
            list = ${model.javaName?uncap_first}Service.findList(${model.javaName?uncap_first});
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller search equals list error",e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "search/count", method = RequestMethod.POST)
    public Object searchCount(@RequestBody JSONObject json) {
        Integer count = 0;
        try {
            String id = json.getString("id");
            <#list parameters as p>
            String ${p.code?uncap_first} = json.getString("${p.code?uncap_first}");
            </#list>
            Criteria criteria = new SimpleCriteria();
                if(StringUtils.isNotBlank(id)){
                criteria.addCondition(0,new Condition("id_","=",Long.valueOf(id)));
            }
            <#list parameters as p>
            if(StringUtils.isNotBlank(${p.code?uncap_first})){
                criteria.addCondition(0,new Condition("${p.code?uncap_first}_","like","%"+${p.code?uncap_first}+"%"));
            }
            </#list>
            criteria.addCondition(0,new Condition("deleted_","=",0));
            count = ${model.javaName?uncap_first}Service.selectCount(criteria);
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller search count error",e);
        }
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "search/list", method = RequestMethod.POST)
    public Object searchList(@RequestBody JSONObject json) {
        List<${model.javaName?cap_first}> list = null;
        try {
            String id = json.getString("id");
            <#list parameters as p>
            String ${p.code?uncap_first} = json.getString("${p.code?uncap_first}");
            </#list>
            Integer pageNo = json.getInteger("pageNo");
            Integer fetchSize = json.getInteger("fetchSize");
            Criteria criteria = new SimpleCriteria();
            if(null != pageNo && pageNo > 0){
                criteria.setFetchSize(fetchSize == null ? FETCH_SIZE : fetchSize);
            }
            if(null != fetchSize && fetchSize > 0){
                criteria.setPageNo(pageNo == null ? 1 : pageNo);
            }
            if(StringUtils.isNotBlank(id)){
                criteria.addCondition(0,new Condition("id_","=",Long.valueOf(id)));
            }
            <#list parameters as p>
            if(StringUtils.isNotBlank(${p.code?uncap_first})){
                criteria.addCondition(0,new Condition("${p.code?uncap_first}_","like","%"+${p.code?uncap_first}+"%"));
            }
            </#list>
            criteria.addCondition(0,new Condition("deleted_","=",0));
            list = ${model.javaName?uncap_first}Service.selectList(criteria);
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller search list error",e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "query/count", method = RequestMethod.POST)
    public Object queryCount(@RequestBody HashMap<String,Object> obj) {
        Integer count = 0;
        try {
            obj.put("deleted_", 0);
            count = ${model.javaName?uncap_first}Service.selectMapCount(obj);
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller query count error",e);
        }
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "query/list", method = RequestMethod.POST)
    public Object queryList(@RequestBody HashMap<String,Object> obj) {
        List<Map<String,Object>> list = null;
        try {
            if(null != obj.get("pageNo") && null != obj.get("fetchSize")) {
                Integer pageNo = Integer.valueOf(obj.get("pageNo") + "");
                Integer fetchSize = Integer.valueOf(obj.get("fetchSize") + "");
                Integer offset = (pageNo - 1) * fetchSize;
                obj.put("pageNo", pageNo);
                obj.put("limit", fetchSize);
                obj.put("offset", offset);
            }
            obj.put("deleted_", 0);
            list = ${model.javaName?uncap_first}Service.selectMapList(obj);
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller query list error",e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "search/page", method = RequestMethod.POST)
    public Object searchPage(@RequestBody JSONObject json) {
        Page<${model.javaName?cap_first}> page = null;
        try {
            String id = json.getString("id");
            <#list parameters as p>
            String ${p.code?uncap_first} = json.getString("${p.code?uncap_first}");
            </#list>
            Integer pageNo = json.getInteger("pageNo");
            Integer fetchSize = json.getInteger("fetchSize");
            Criteria criteria = new SimpleCriteria();
            criteria.setFetchSize(fetchSize == null ? FETCH_SIZE : fetchSize);
            criteria.setPageNo(pageNo == null ? 1 : pageNo);
            if(StringUtils.isNotBlank(id)){
            criteria.addCondition(0,new Condition("id_","=",Long.valueOf(id)));
            }
            <#list parameters as p>
            if(StringUtils.isNotBlank(${p.code?uncap_first})){
                criteria.addCondition(0,new Condition("${p.code?uncap_first}_","like","%"+${p.code?uncap_first}+"%"));
            }
            </#list>
            criteria.addCondition(0,new Condition("deleted_","=",0));
            page = ${model.javaName?uncap_first}Service.selectPage(criteria);
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller search page error",e);
        }
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "query/page", method = RequestMethod.POST)
    public Object queryPage(@RequestBody HashMap<String,Object> obj) {
        Page<Map<String,Object>> page = null;
        try {
            Integer pageNo = obj.get("pageNo") == null ? 1 : Integer.valueOf(obj.get("pageNo") + "");
            Integer fetchSize = obj.get("fetchSize") == null ? FETCH_SIZE : Integer.valueOf(obj.get("fetchSize") + "");
            Integer offset = (pageNo-1)*fetchSize;
            obj.put("pageNo",pageNo);
            obj.put("limit",fetchSize);
            obj.put("offset", offset);
            obj.put("deleted_", 0);
            page = ${model.javaName?uncap_first}Service.selectMapPage(obj);
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller query page error",e);
        }
        return page;
    }
<#list parameters as p>
    <#if p.keyType==1>

    @ResponseBody
    @RequestMapping(value = "getBy${p.code?cap_first}", method = RequestMethod.POST)
    public Object getBy${p.code?cap_first}(@RequestBody JSONObject json) {
        ${model.javaName?cap_first} object = null;
        try {
            ${p.type?cap_first} ${p.code} = json.get${p.type?cap_first}("${p.code}");
            object = ${model.javaName?uncap_first}Service.getObjectBy${p.code?cap_first}(${p.code});
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller getBy${p.code?cap_first} error",e);
        }
        return object;
    }
    <#elseif p.keyType==2>

    @ResponseBody
    @RequestMapping(value = "getListBy${p.code?cap_first}", method = RequestMethod.POST)
    public Object getListBy${p.code?cap_first}(@RequestBody JSONObject json) {
        List<${model.javaName?cap_first}> list = null;
        try {
            ${p.type?cap_first} ${p.code} = json.get${p.type?cap_first}("${p.code}");
            list = ${model.javaName?uncap_first}Service.getListBy${p.code?cap_first}(${p.code});
        } catch (ServiceException e) {
            logger.error("${model.javaName?uncap_first}Controller getListBy${p.code?cap_first} error",e);
        }
        return list;
    }
    </#if>
</#list>

}