package com.snake.system.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.system.model.Parameter;
import org.springframework.stereotype.Repository;

/**
 * User: wenxy
 * Date: 2016-11-2 18:21:48
 */
@Repository("sysParameterDao")
public class ParameterDao extends MybatisBasicDao<Parameter> implements IParameterDao {
    public ParameterDao(){
        super(Parameter.class);
    }

}
