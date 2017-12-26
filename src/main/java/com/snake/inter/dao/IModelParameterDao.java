package com.snake.inter.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.model.ModelParameter;

import java.util.List;

public interface IModelParameterDao extends IBasicDao<ModelParameter> {
    public List<ModelParameter> getListByModelId(Long modelId) throws DaoException;
}