package com.snake.system.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.system.model.Index;
import org.springframework.stereotype.Repository;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Repository("indexDao")
public class IndexDao extends MybatisBasicDao<Index> implements IIndexDao {
    public IndexDao() {
        super(Index.class);
    }
}
