package com.web.${app.code}.controller;

import com.web.${app.code}.entity.${model.code?cap_first};
import com.web.${app.code}.mapper.${model.code?cap_first}Mapper;
import com.web.${app.code}.config.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/${app.code}/${model.code}/")
public class ${model.code?cap_first}Controller {

    @Autowired
    private ${model.code?cap_first}Mapper ${model.code}Mapper;

    @ResponseBody
    @RequestMapping("page")
    public Object page(@RequestParam(value = "number", defaultValue = "1") int number, @RequestParam(value = "size", defaultValue = "0") int size) {
      Page page = Page.newInstance(number,size);
      List<${model.code?cap_first}> list = this.${model.code}Mapper.selectListByPage(page);
      int total = this.${model.code}Mapper.selectCountByPage(page);
      page.build(total,list);
      return page;
    }
<#list parameters as obj>
    <#if obj.keyType?? && obj.keyType == 2 >

    @ResponseBody
    @RequestMapping("{${obj.code}}/page")
    public Object pageBy${obj.code?cap_first}(@PathVariable("${obj.code}")${obj.type} ${obj.code}, @RequestParam(value = "number", defaultValue = "1") int number, @RequestParam(value = "size", defaultValue = "0") int size) {
        Page page = Page.newInstance(number,size);
        page.addCondition("${obj.code}="+${obj.code});
        List<${model.code?cap_first}> list = this.${model.code}Mapper.selectListByPage(page);
        int total = this.${model.code}Mapper.selectCountByPage(page);
        page.build(total,list);
        return page;
    }
    </#if>
</#list>
<#list parameters as obj>
    <#if obj.keyType?? && obj.keyType == 1 >

    @ResponseBody
    @RequestMapping("objectBy${obj.code?cap_first}")
    public Object objectBy${obj.code?cap_first}(${obj.type} ${obj.code}) {
        ${model.code?cap_first} example = new ${model.code?cap_first}();
        example.set${obj.code?cap_first}(${obj.code});
        return this.${model.code}Mapper.selectOne(example);
    }
    </#if>
</#list>
<#list parameters as obj>
    <#if obj.keyType?? && (obj.keyType == 2 || obj.keyType == 3) >

    @ResponseBody
    @RequestMapping("listBy${obj.code?cap_first}")
    public Object listBy${obj.code?cap_first}(${obj.type} ${obj.code}) {
        ${model.code?cap_first} example = new ${model.code?cap_first}();
        example.set${obj.code?cap_first}(${obj.code});
        return this.${model.code}Mapper.selectList(example);
    }
    </#if>
</#list>

    @ResponseBody
    @RequestMapping("create")
    public Object create(@RequestBody ${model.code?cap_first} obj) {
      this.${model.code}Mapper.insert(obj);
      return null;
    }

    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody ${model.code?cap_first} obj) {
      this.${model.code}Mapper.update(obj);
      return null;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Long id) {
      this.${model.code}Mapper.delete(id);
      return null;
    }

}
