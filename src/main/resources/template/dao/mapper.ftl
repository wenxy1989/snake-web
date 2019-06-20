package com.web.${app.code}.mapper;


import com.web.${app.code}.config.BasicMapper;
import com.web.${app.code}.entity.${model.javaName?cap_first};

import org.springframework.stereotype.Repository;


/**
* ${model.name}
* create by wenxy at ${now}
*/
@Repository
public interface ${model.javaName?cap_first}Mapper extends BasicMapper<${model.javaName?cap_first}> {

}