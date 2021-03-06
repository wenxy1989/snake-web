package com.snake.inter.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.model.Model;

import java.util.List;

public interface IModelDao extends IBasicDao<Model> {
    public List<Model> getListByApplicationId(Long applicationId) throws DaoException;
}