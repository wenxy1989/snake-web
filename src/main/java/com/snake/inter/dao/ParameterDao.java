package com.snake.inter.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.inter.model.Parameter;
import org.springframework.stereotype.Repository;

@Repository("parameterDao")
public class ParameterDao extends MybatisBasicDao<Parameter> implements IParameterDao {

    public ParameterDao() {
        super(Parameter.class);
    }
}