package com.snake.system.service;
import com.base.service.BasicService;
import com.snake.system.dao.IParameterDao;
import com.snake.system.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * User: wenxy
 * Date: 2016-11-2 18:23:47
 */
@Service
@Repository("sysParameterService")
public class ParameterService extends BasicService<Parameter> implements IParameterService {
    @Autowired
    @Qualifier("sysParameterDao")
    protected IParameterDao dao;

    @Override
    public IParameterDao getDao() {
        return dao;
    }

    public Parameter getObjectByCode(String code) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code_",code);
            return this.dao.findOne(map);
        }catch (Exception e){
            throw new Exception("根据idCard获取用户列表失败",e);
        }
    }
}
