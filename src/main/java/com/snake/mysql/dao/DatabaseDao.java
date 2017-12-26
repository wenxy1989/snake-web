package com.snake.mysql.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.mysql.dao.IDatabaseDao;
import com.snake.mysql.model.Database;
import org.springframework.stereotype.Repository;

/**
 * Created by wenxy on 2016/11/29.
 */
@Repository("databaseDao")
public class DatabaseDao extends MybatisBasicDao<Database> implements IDatabaseDao {

    public DatabaseDao() {
        super(Database.class);
    }

}
