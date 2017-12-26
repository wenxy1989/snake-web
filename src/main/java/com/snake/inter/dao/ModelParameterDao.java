package com.snake.inter.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.dao.IModelParameterDao;
import com.snake.inter.model.ModelParameter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("modelParameterDao")
public class ModelParameterDao extends MybatisBasicDao<ModelParameter> implements IModelParameterDao {

    public ModelParameterDao() {
        super(ModelParameter.class);
    }
    public List<ModelParameter> getListByModelId(Long modelId) throws DaoException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("model_id", modelId);
        return find(map);
    }
}