package com.snake.inter.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.inter.model.Application;
import org.springframework.stereotype.Repository;

@Repository("applicationDao")
public class ApplicationDao extends MybatisBasicDao<Application> implements IApplicationDao {

    public ApplicationDao() {
        super(Application.class);
    }
}