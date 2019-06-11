package com.web.${application.code}.controller;

import com.web.${application.code}.entity.${model.code?cap_first};
import com.web.${application.code}.mapper.${model.code?cap_first}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/${application.code}/${model.code}/")
public class ${model.code?cap_first}Controller {

@Autowired
private ${model.code?cap_first}Mapper ${model.code}Mapper;

@ResponseBody
@RequestMapping("page")
public Object page() {
return this.${model.code}Mapper.select(null);
}

@ResponseBody
@RequestMapping("create")
public Object create(@RequestBody ${model.code?cap_first} obj) {
return this.${model.code}Mapper.insert(obj);
}

@ResponseBody
@RequestMapping("update")
public Object update(@RequestBody ${model.code?cap_first} obj) {
return this.${model.code}Mapper.update(obj);
}

@ResponseBody
@RequestMapping("delete")
public Object delete(Long id) {
return this.${model.code}Mapper.delete(id);
}

}
