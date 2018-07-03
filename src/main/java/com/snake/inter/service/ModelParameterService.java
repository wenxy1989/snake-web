package com.snake.inter.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.inter.dao.IModelParameterDao;
import com.snake.inter.model.ModelParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class ModelParameterService extends BasicService<ModelParameter> implements IModelParameterService {

    @Autowired
    @Qualifier("modelParameterDao")
    private IModelParameterDao dao;

    @Override
    public IModelParameterDao getDao() {
        return dao;
    }

    public ModelParameter getObject(Long modelId,String code) throws ServiceException {
        ModelParameter object = null;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("model_id",modelId);
        map.put("code_",code);
        try{
            object = getDao().findOne(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return object;
    }

    public List<ModelParameter> getListByModelId(Long modelId) throws ServiceException {
        try{
            return getDao().getListByModelId(modelId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}