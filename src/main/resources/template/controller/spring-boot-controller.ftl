package com.web.${app.code}.controller;

import com.web.glucose_db.config.ResponseBodyResult;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/${app.code}/${model.code}/")
public class ${model.code?cap_first}Controller {

    @Autowired
    private ${model.code?cap_first}Mapper ${model.code}Mapper;

    @ResponseBody
    @RequestMapping("list")
    public Object list(@RequestBody ${model.code?cap_first} obj) {
<#list parameters as p>
    <#if p.keyType?? && p.keyType == 2 >
        if(obj == null || obj.get${p.code?cap_first}() == null){
            return ResponseBodyResult.Error.parameter;
        }
    </#if>
</#list>
        return this.${model.code}Mapper.selectList(obj);
    }

    @ResponseBody
    @RequestMapping("page")
    public Object page(@RequestParam(value = "number", defaultValue = "1") int number, @RequestParam(value = "size", defaultValue = "0") int size, @RequestBody ${model.code?cap_first} param) {
    <#list parameters as p>
        <#if p.keyType?? && p.keyType == 2 >
        if(param.get${p.code?cap_first}() == null){
            return ResponseBodyResult.Error.parameter;
        }
        </#if>
    </#list>
        Page page = Page.newInstance(number,size);
        page.setParam(param);
        List<${model.code?cap_first}> list = this.${model.code}Mapper.selectListByPage(page);
        int total = this.${model.code}Mapper.selectCountByPage(page);
        page.build(total,list);
        return page;
    }
<#list parameters as obj>
    <#if obj.keyType?? && obj.keyType == 1 ><#-- objectByCode -->

    @ResponseBody
    @RequestMapping("objectBy${obj.code?cap_first}")
    public Object objectBy${obj.code?cap_first}(${obj.type} ${obj.code}) {
        ${model.code?cap_first} example = new ${model.code?cap_first}();
        example.set${obj.code?cap_first}(${obj.code});
        return this.${model.code}Mapper.selectOne(example);
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
