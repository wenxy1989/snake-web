package com.school.book.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.exception.ServiceException;
import com.base.model.Condition;
import com.base.model.Criteria;
import com.base.model.SimpleCriteria;
import com.base.mvc.BasicController;
import com.school.book.model.${model.code?cap_first};
import com.school.book.service.I${model.code?cap_first}Service;
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
** ${group.name} : ${group.remark}
** create at ${group.createdTime} by user id ${group.creatorId}
**/
@Controller
@RequestMapping("/${model.code?uncap_first}")
public class ${model.code?cap_first}Controller extends BasicController {

    @Resource(name = "${model.code?uncap_first}Service")
    private I${model.code?cap_first}Service ${model.code?uncap_first}Service;

    @ResponseBody
    @RequestMapping(value = "/version")
    public Object version() {
        return "version.${now?string("yyyy.MM.dd")}";
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody ${model.code?cap_first} obj) {
        String result = RESULT_ERROR;
        try {
            obj.setCreateUser(getLoginName());
            ${model.code?uncap_first}Service.create(obj);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller create error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody ${model.code?cap_first} obj) {
        String result = RESULT_ERROR;
        Long id = obj.getId();
        try {
            ${model.code?uncap_first}Service.deleteById(id);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller delete error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/logic/delete", method = RequestMethod.POST)
    public Object logicDelete(@RequestBody ${model.code?cap_first} obj) {
        String result = RESULT_ERROR;
        try {
            obj.setDeleted(1);
            obj.setUpdateUser(getLoginName());
            ${model.code?uncap_first}Service.updateById(obj);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller logic delete error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody ${model.code?cap_first} obj) {
        String result = RESULT_ERROR;
        try {
            obj.setUpdateUser(getLoginName());
            ${model.code?uncap_first}Service.updateById(obj);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller update error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Object get(@RequestBody JSONObject obj) {
        Long id = obj.getLong("id");
        ${model.code?cap_first} result = null;
        try {
            result = ${model.code?uncap_first}Service.findObject(id);
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller get error",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/search/equals", method = RequestMethod.POST)
    public Object searchEquals(@RequestBody JSONObject obj) {
        String id = obj.getString("id");
        <#list parameters as obj>
        String ${obj.code?uncap_first} = obj.getString("${obj.code?uncap_first}");
        </#list>
        List<${model.code?cap_first}> list = null;
        try {
            ${model.code?cap_first} ${model.code?uncap_first} = new ${model.code?cap_first}();
            if(StringUtils.isNotBlank(id)){
                ${model.code?uncap_first}.setId(Long.valueOf(id));
            }
            <#list parameters as obj>
            if(StringUtils.isNotBlank(${obj.code?uncap_first})){
            <#if obj.type == "String">
                ${model.code?uncap_first}.set${obj.code?cap_first}(${obj.code?uncap_first});
            <#else>
                ${obj.type} _value = ${obj.type}.valueOf(${obj.code?uncap_first});
                ${model.code?uncap_first}.set${obj.code?cap_first}(_value);
            </#if>
            }
            </#list>
            list = ${model.code?uncap_first}Service.findList(${model.code?uncap_first});
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller search equals list error",e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/search/count", method = RequestMethod.POST)
    public Object searchCount(@RequestBody JSONObject obj) {
        Integer count = 0;
        try {
            String id = obj.getString("id");
            <#list parameters as obj>
            String ${obj.code?uncap_first} = obj.getString("${obj.code?uncap_first}");
            </#list>
            Criteria criteria = new SimpleCriteria();
                if(StringUtils.isNotBlank(id)){
                criteria.addCondition(0,new Condition("id_","=",Long.valueOf(id)));
            }
            <#list parameters as obj>
            if(StringUtils.isNotBlank(${obj.code?uncap_first})){
                criteria.addCondition(0,new Condition("${obj.code?uncap_first}_","like","%"+${obj.code?uncap_first}+"%"));
            }
            </#list>
            criteria.addCondition(0,new Condition("deleted_","=",0));
            count = ${model.code?uncap_first}Service.selectCount(criteria);
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller search count error",e);
        }
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "/search/list", method = RequestMethod.POST)
    public Object searchList(@RequestBody JSONObject obj) {
        List<${model.code?cap_first}> list = null;
        try {
            String id = obj.getString("id");
            <#list parameters as obj>
            String ${obj.code?uncap_first} = obj.getString("${obj.code?uncap_first}");
            </#list>
            Integer pageNo = obj.getInteger("pageNo");
            Integer fetchSize = obj.getInteger("fetchSize");
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
            <#list parameters as obj>
            if(StringUtils.isNotBlank(${obj.code?uncap_first})){
                criteria.addCondition(0,new Condition("${obj.code?uncap_first}_","like","%"+${obj.code?uncap_first}+"%"));
            }
            </#list>
            criteria.addCondition(0,new Condition("deleted_","=",0));
            list = ${model.code?uncap_first}Service.selectList(criteria);
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller search list error",e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/query/count", method = RequestMethod.POST)
    public Object queryCount(@RequestBody HashMap<String,Object> obj) {
        Integer count = 0;
        try {
            obj.put("deleted_", 0);
            count = ${model.code?uncap_first}Service.selectMapCount(obj);
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller query count error",e);
        }
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "/query/list", method = RequestMethod.POST)
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
            list = ${model.code?uncap_first}Service.selectMapList(obj);
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller query list error",e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/search/page", method = RequestMethod.POST)
    public Object searchPage(@RequestBody JSONObject obj) {
        Page<${model.code?cap_first}> page = null;
        try {
            String id = obj.getString("id");
            <#list parameters as obj>
            String ${obj.code?uncap_first} = obj.getString("${obj.code?uncap_first}");
            </#list>
            Integer pageNo = obj.getInteger("pageNo");
            Integer fetchSize = obj.getInteger("fetchSize");
            Criteria criteria = new SimpleCriteria();
            criteria.setFetchSize(fetchSize == null ? FETCH_SIZE : fetchSize);
            criteria.setPageNo(pageNo == null ? 1 : pageNo);
            if(StringUtils.isNotBlank(id)){
            criteria.addCondition(0,new Condition("id_","=",Long.valueOf(id)));
            }
            <#list parameters as obj>
            if(StringUtils.isNotBlank(${obj.code?uncap_first})){
                criteria.addCondition(0,new Condition("${obj.code?uncap_first}_","like","%"+${obj.code?uncap_first}+"%"));
            }
            </#list>
            criteria.addCondition(0,new Condition("deleted_","=",0));
            page = ${model.code?uncap_first}Service.selectPage(criteria);
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller search page error",e);
        }
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/query/page", method = RequestMethod.POST)
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
            page = ${model.code?uncap_first}Service.selectMapPage(obj);
        } catch (ServiceException e) {
            logger.error("${model.code?uncap_first}Controller query page error",e);
        }
        return page;
    }

}