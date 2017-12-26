package com.snake.chinese.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.chinese.model.Type;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface ITypeDao extends IBasicDao<Type> {
    List<Type> getListByWordId(Long wordId) throws DaoException;
}
