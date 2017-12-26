package com.snake.inter.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.inter.model.Result;
import com.snake.inter.dao.IResultDao;
import com.snake.inter.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class ResultService extends BasicService<Result> implements IResultService {

    @Autowired
    @Qualifier("resultDao")
    private IResultDao resultDao;

    @Override
    public IResultDao getDao() {
        return resultDao;
    }

    public List<Result> getListByUrlId(Long urlId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("url_id",urlId);
            return resultDao.find(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public Result getObjectByCode(String code) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code_",code);
            return resultDao.findOne(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}