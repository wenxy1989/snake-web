package com.snake.resource.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/8/11.
 */
public interface IResourceDao<T> {

    void initStaticObjects();

    Map<Object,T> getMap();
    List<T> getList();

    T get(Object id);

    void add(T object);

    void replace(T object);

    void remove(T object);

}
