package com.snake.mysql.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.mysql.model.Table;

/**
 * Created by wenxy on 2016/11/29.
 */
public interface ITableDao extends IBasicDao<Table> {

    public void deleteByDatabaseId(Long tableId) throws DaoException;

}
