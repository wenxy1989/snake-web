package com.snake.mysql.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.mysql.model.Column;

/**
 * Created by wenxy on 2016/11/29.
 */
public interface IColumnDao extends IBasicDao<Column> {
    void deleteByTableId(Long tableId) throws DaoException;
}
