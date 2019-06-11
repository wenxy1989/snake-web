package com.snake.inter.dao;

import com.base.exception.DaoException;
import com.snake.inter.model.Model;
import com.snake.resource.dao.AbstractResourceDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("modelDao")
public class ModelDao extends AbstractResourceDao<Model> implements IModelDao {

    public ModelDao() {
        super(Model.class);
    }

    private static Map<Object, Model> map = new HashMap<Object, Model>();

    public Map<Object, Model> getMap() {
        return map;
    }

    public static Map<Object, Model> getFinalMap() {
        final Map<Object, Model> finalMap = map;
        return finalMap;
    }

    public static Model getFinalObject(Object id) {
        final Model object = map.get(id);
        return object;
    }

    public static List<Model> getFinalList() {
        final List<Model> list = new ArrayList<Model>(map.values());
        return list;
    }

    public List<Model> getListByApplicationId(Long applicationId,Integer status) throws DaoException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("application_id", applicationId);
        map.put("status_", status);
        return find(map);
    }
}