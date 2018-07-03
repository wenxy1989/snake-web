package com.snake.inter.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.dao.IUrlDao;
import com.snake.inter.model.Url;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("urlDao")
public class UrlDao extends MybatisBasicDao<Url> implements IUrlDao {

    public UrlDao() {
        super(Url.class);
    }

    public List<Url> getListByApplicationId(Long applicationId) throws DaoException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("application_id", applicationId);
        return find(map);
    }

    public List<Url> getListByGroupId(Long groupId) throws DaoException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("group_id", groupId);
        return find(map);
    }
}