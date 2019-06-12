package com.snake.inter.dao;
import com.snake.inter.model.Group;
import com.snake.resource.dao.AbstractResourceDao;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("groupDao")
public class GroupDao extends AbstractResourceDao<Group> implements IGroupDao {

    public GroupDao() {
        super(Group.class);
    }

    private static Map<Object,Group> map = new HashMap<Object, Group>();

    public Map<Object, Group> getMap() {
        return map;
    }

    public static Map<Object, Group> getFinalMap() {
        final Map<Object,Group> finalMap = map;
        return finalMap;
    }

    public static Group getFinalObject(Object id){
        final Group object = map.get(id);
        return object;
    }

    public static List<Group> getFinalList() {
        final List<Group> list = new ArrayList<Group>(map.values());
        return list;
    }

    public List<Group> getListByApplicationId(Long applicationId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("application_id", applicationId);
        return find(map);
    }
}