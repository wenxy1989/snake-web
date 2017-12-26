package com.snake.inter.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.inter.dao.IUploadDao;
import com.snake.inter.model.Upload;
import com.snake.inter.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class UploadService extends BasicService<Upload> implements IUploadService {

    @Autowired
    @Qualifier("uploadDao")
    private IUploadDao uploadDao;

    @Override
    public IUploadDao getDao() {
        return uploadDao;
    }

    public List<Upload> getListByUrlId(Long urlId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("url_id",urlId);
            return uploadDao.find(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public Upload getObjectByCode(String code) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code_",code);
            return uploadDao.findOne(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}