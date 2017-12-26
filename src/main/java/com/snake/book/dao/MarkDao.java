package com.snake.book.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.book.dao.IMarkDao;
import com.snake.book.model.Mark;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("markDao")
public class MarkDao extends MybatisBasicDao<Mark> implements IMarkDao {

    public MarkDao() {
        super(Mark.class);
    }
}
