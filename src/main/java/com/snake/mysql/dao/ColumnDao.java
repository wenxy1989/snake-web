package com.snake.mysql.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.mysql.dao.IColumnDao;
import com.snake.mysql.model.Column;
import org.springframework.stereotype.Repository;

/**
 * Created by wenxy on 2016/11/29.
 */
@Repository("columnDao")
public class ColumnDao extends MybatisBasicDao<Column> implements IColumnDao {

    public ColumnDao() {
        super(Column.class);
    }

    public void deleteByTableId(Long tableId) throws DaoException {
        try {
            this.sqlSession.delete(Column.class.getName() + ".deleteByTableId", tableId);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }
}
