package com.snake.inter.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.inter.dao.IGroupDao;
import com.snake.inter.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("groupService")
public class GroupService extends BasicService<Group> implements IGroupService {

    @Autowired
    @Qualifier("groupDao")
    private IGroupDao dao;

    @Override
    public IGroupDao getDao() {
        return dao;
    }

    public Group getObjectByModel(String model) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("model_",model);
            return dao.findOne(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Group> getListByApplicationId(Long applicationId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("application_id",applicationId);
            return dao.find(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}