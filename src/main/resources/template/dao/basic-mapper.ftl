package com.web.${app.code}.config;

import com.web.${app.code}.config.Page;

import java.util.List;
import java.util.Map;

/**
* ${model.name}
* create by wenxy at ${now}
*/
public interface BasicMapper<T> {

  int selectCount(T obj);

  List<T> selectList(T obj);

  int selectCountByPage(Page page);

  List<T> selectListByPage(Page page);

  T selectOne(T obj);

  void insert(T obj);

  void delete(Object key);

  void update(T obj);

}