package com.snake.template.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.template.model.Group;
import org.springframework.stereotype.Repository;

/**
 * Created by HP on 2018/7/3.
 */
@Repository("t_groupDao")
public class GroupDao extends MybatisBasicDao<Group> implements IGroupDao {

    public GroupDao() {
        super(Group.class);
    }

}
