package com.snake.inter.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.inter.dao.IUrlDao;
import com.snake.inter.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class UrlService extends BasicService<Url> implements IUrlService {

    @Autowired
    @Qualifier("urlDao")
    private IUrlDao urlDao;

    @Override
    public IUrlDao getDao() {
        return urlDao;
    }

    public Url getObjectByUrl(String url) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("url_",url);
            return urlDao.findOne(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Url> getListByGroupId(Long groupId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("group_id",groupId);
            return urlDao.find(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Url> getListByApplicationId(Long applicationId) throws ServiceException {
        try {
            return urlDao.getListByApplicationId(applicationId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}