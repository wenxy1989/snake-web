package com.snake.chinese.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.chinese.model.Word;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IWordDao extends IBasicDao<Word> {
    void batchInsert(List<Word> list) throws DaoException;

    void deleteByBookId(Long id) throws DaoException;
}
