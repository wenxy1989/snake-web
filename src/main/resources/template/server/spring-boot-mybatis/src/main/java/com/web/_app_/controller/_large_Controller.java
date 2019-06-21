package com.web.${app.code}.controller;

import com.web.${app.code}.config.ResponseBodyResult;
import com.web.${app.code}.entity.${model.javaName?cap_first};
import com.web.${app.code}.mapper.${model.javaName?cap_first}Mapper;
import com.web.${app.code}.config.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
<#--import org.springframework.web.bind.annotation.PathVariable;-->
import org.springframework.web.bind.annotation.ResponseBody;

<#--import javax.servlet.http.HttpServletRequest;-->
<#--import javax.servlet.http.HttpServletResponse;-->
import java.util.List;

@Controller
@RequestMapping("/${app.code}/${model.javaName}/")
public class ${model.javaName?cap_first}Controller {

    @Autowired
    private ${model.javaName?cap_first}Mapper ${model.javaName}Mapper;

    @ResponseBody
    @RequestMapping("list")
    public Object list(@RequestBody ${model.javaName?cap_first} obj) {
<#list parameters as p>
    <#if p.keyType?? && p.keyType == 2 >
        if(obj == null || obj.get${p.javaName?cap_first}() == null){
            return ResponseBodyResult.Error.parameter;
        }
    </#if>
</#list>
        return this.${model.javaName}Mapper.selectList(obj);
    }

    @ResponseBody
    @RequestMapping("page")
    public Object page(@RequestParam(value = "number", defaultValue = "1") int number, @RequestParam(value = "size", defaultValue = "0") int size, @RequestBody ${model.javaName?cap_first} param) {
    <#list parameters as p>
        <#if p.keyType?? && p.keyType == 2 >
        if(param.get${p.javaName?cap_first}() == null){
            return ResponseBodyResult.Error.parameter;
        }
        </#if>
    </#list>
        Page page = Page.newInstance(number,size);
        page.setParam(param);
        List<${model.javaName?cap_first}> list = this.${model.javaName}Mapper.selectListByPage(page);
        int total = this.${model.javaName}Mapper.selectCountByPage(page);
        page.build(total,list);
        return page;
    }
<#list parameters as p>
    <#if p.keyType?? && p.keyType == 1 ><#-- objectByCode -->

    @ResponseBody
    @RequestMapping("objectBy${p.javaName?cap_first}")
    public Object objectBy${p.javaName?cap_first}(${p.type} ${p.javaName}) {
        ${model.javaName?cap_first} example = new ${model.javaName?cap_first}();
        example.set${p.javaName?cap_first}(${p.javaName});
        return this.${model.javaName}Mapper.selectOne(example);
    }
    </#if>
</#list>

    @ResponseBody
    @RequestMapping("create")
    public Object create(@RequestBody ${model.javaName?cap_first} obj) {
      this.${model.javaName}Mapper.insert(obj);
      return null;
    }

    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody ${model.javaName?cap_first} obj) {
      this.${model.javaName}Mapper.update(obj);
      return null;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Long id) {
      this.${model.javaName}Mapper.delete(id);
      return null;
    }

}
