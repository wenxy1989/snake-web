package com.snake.resource.dao;

import com.base.dao.MybatisBasicDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HP on 2016/12/8.
 */
public abstract class AbstractResourceDao<T extends MapObject> extends MybatisBasicDao<T> implements IResourceDao<T> {

    public AbstractResourceDao(Class<T> modelClass) {
        super(modelClass);
    }

    public Map<Object, T> createMap() {
        return new HashMap<Object, T>();
    }

    public void initStaticObjects(){
        try {
            List<T> all = getAll();
            if(null != all && all.size() > 0){
                for(T object : all){
                    add(object);
                }
            }
        } catch (Exception e) {
            logger.error("load all error",e);
        }
    }

    public List<T> getList(){
        return new ArrayList<T>(getMap().values());
    }

    public T get(Object id) {
        return getMap().get(id);
    }

    public void add(T object) {
        getMap().put(object.getId(), object);
    }

    public void replace(T object) {
        getMap().put(object.getId(), object);
    }

    public void remove(T object) {
        getMap().remove(object.getId());
    }

    @Override
    public void create(T object) throws Exception {
        super.create(object);
        add(object);
    }

    @Override
    public void update(T object) throws Exception {
        super.update(object);
        replace(object);
    }

    @Override
    public void delete(Object id) throws Exception {
        super.delete(id);
        T object = get(id);
        remove(object);
    }

    @Override
    public void batchInsert(List<T> list) throws Exception {
        super.batchInsert(list);
        if(null != list && list.size() > 0){
            for(T object : list){
                add(object);
            }
        }
    }
}
