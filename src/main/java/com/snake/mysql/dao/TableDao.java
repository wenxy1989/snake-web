package com.snake.mysql.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.mysql.dao.ITableDao;
import com.snake.mysql.model.Table;
import org.springframework.stereotype.Repository;

/**
 * Created by wenxy on 2016/11/29.
 */
@Repository("tableDao")
public class TableDao extends MybatisBasicDao<Table> implements ITableDao {

    public TableDao() {
        super(Table.class);
    }

    public void deleteByDatabaseId(Long tableId) throws DaoException {
        try {
            this.sqlSession.delete(Table.class.getName() + ".deleteByDatabaseId", tableId);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

}
