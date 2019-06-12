package com.web.${application.code}.mapper;

import com.web.${application.code}.entity.${model.code?cap_first};
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* ${model.name}
* create by wenxy at ${now}
*/
@Repository
public interface ${model.code?cap_first}Mapper {

  int selectCount(${model.code?cap_first} example);

  List<${model.code?cap_first}> selectList(${model.code?cap_first} example);

  ${model.code?cap_first} selectOne(${model.code?cap_first} example);

  void insert(${model.code?cap_first} example);

  void delete(Object key);

  void update(${model.code?cap_first} example);

}