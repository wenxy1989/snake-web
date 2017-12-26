package com.snake.book.service;

import com.base.service.BasicService;
import com.snake.book.dao.IMarkDao;
import com.snake.book.model.Mark;
import com.snake.book.service.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("markService")
public class MarkService extends BasicService<Mark> implements IMarkService {

    @Autowired
    @Qualifier("markDao")
    private IMarkDao markDao;

    @Override
    public IMarkDao getDao() {
        return markDao;
    }
}
