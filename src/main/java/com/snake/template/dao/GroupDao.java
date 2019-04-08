package com.snake.template.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.resource.dao.AbstractResourceDao;
import com.snake.template.model.Group;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2018/7/3.
 */
@Repository("t_groupDao")
public class GroupDao extends AbstractResourceDao<Group> implements IGroupDao {

    private static Map<Object, Group> map = new HashMap<Object, Group>();

    public GroupDao() {
        super(Group.class);
    }

    public final static Object getFinalList() {
        return map.values();
    }

    public final static Object getFinalObject(Long id) {
        return map.get(id);
    }

    @Override
    public Map<Object, Group> getMap() {
        return map;
    }
}
