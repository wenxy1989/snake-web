package com.snake.inter.service;

import com.base.service.BasicService;
import com.snake.inter.dao.IParameterDao;
import com.snake.inter.model.Parameter;
import com.snake.inter.service.IParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class ParameterService extends BasicService<Parameter> implements IParameterService {

    @Autowired
    @Qualifier("parameterDao")
    private IParameterDao parameterDao;

    @Override
    public IParameterDao getDao() {
        return parameterDao;
    }

    public Parameter getObjectByCode(String code) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code_", code);
            return parameterDao.findOne(map);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Parameter findOne(Parameter object) throws Exception {
        Parameter parameter = object.clone();
        parameter.setRemark(null);
        return super.findOne(parameter);
    }
}