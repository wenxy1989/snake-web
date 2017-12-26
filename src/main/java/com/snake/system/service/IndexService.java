package com.snake.system.service;

import com.base.Constants;
import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.system.dao.IIndexDao;
import com.snake.system.model.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Service
public class IndexService extends BasicService<Index> implements IIndexService {
    @Autowired
    @Qualifier("indexDao")
    protected IIndexDao dao;

    @Override
    public IBasicDao<Index> getDao() {
        return this.dao;
    }

    public Index getObjectByRoleId(Long roleId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("type_", Constants.INDEX_TYPE_ROLE);
            map.put("object_id",roleId);
            return this.getDao().findOne(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public Index getObjectByUserId(Long userId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("type_", Constants.INDEX_TYPE_USER);
            map.put("object_id",userId);
            return this.getDao().findOne(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
