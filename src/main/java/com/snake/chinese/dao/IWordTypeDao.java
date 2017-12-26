package com.snake.chinese.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.chinese.model.relation.WordType;

/**
 * Created by wen on 2015/5/1.
 */
public interface IWordTypeDao extends IBasicDao<WordType> {
    void deleteObject(WordType wordType) throws DaoException;
}
