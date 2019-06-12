package com.snake.inter.service;
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

    public Url getObjectByUrl(String url) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("url_",url);
            return urlDao.findOne(map);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<Url> getListByGroupId(Long groupId) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("group_id",groupId);
            return urlDao.find(map);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<Url> getListByApplicationId(Long applicationId) throws Exception {
        try {
            return urlDao.getListByApplicationId(applicationId);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}