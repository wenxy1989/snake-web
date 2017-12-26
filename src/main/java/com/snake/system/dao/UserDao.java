package com.snake.system.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.system.model.User;
import org.springframework.stereotype.Repository;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Repository("userDao")
public class UserDao extends MybatisBasicDao<User> implements IUserDao {
    public UserDao(){
        super(User.class);
    }

}
