package com.snake.inter.service;
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

    public Group getObjectByModel(String model) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("model_",model);
            return dao.findOne(map);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<Group> getListByApplicationId(Long applicationId) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("application_id",applicationId);
            return dao.find(map);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}