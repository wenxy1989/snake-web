package com.snake.inter.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.model.Result;

import java.util.List;

public interface IResultDao extends IBasicDao<Result> {
    public List getListByUrlId(Long id) throws DaoException;
}