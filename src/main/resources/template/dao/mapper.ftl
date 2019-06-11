package com.web.${application.code}.mapper;

import com.web.${application.code}.mapper.${model.code?cap_first};
import java.util.List;

/**
* ${model.name}
* create by wenxy at ${now}
*/
public interface ${model.code?cap_first}Mapper {

  int selectCount(Map<String,Object> map);

  int selectCount(${model.code?cap_first} example);

  List<${model.code?cap_first}> selectList(Map<String,Object> map);

  ${model.code?cap_first} selectOne(Map<String,Object> map);

  List<${model.code?cap_first}> selectList(${model.code?cap_first} example);

  ${model.code?cap_first} selectOne(${model.code?cap_first} example);

  void insert(${model.code?cap_first} example);

  void delete(Object key);

  void update(${model.code?cap_first} example);

}