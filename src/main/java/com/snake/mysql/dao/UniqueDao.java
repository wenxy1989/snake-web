package com.snake.mysql.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.mysql.dao.IUniqueDao;
import com.snake.mysql.model.Unique;
import org.springframework.stereotype.Repository;

/**
 * Created by wenxy on 2016/11/29.
 */
@Repository("uniqueDao")
public class UniqueDao extends MybatisBasicDao<Unique> implements IUniqueDao {

    public UniqueDao() {
        super(Unique.class);
    }

}
