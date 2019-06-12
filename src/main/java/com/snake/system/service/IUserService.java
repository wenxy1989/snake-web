package com.snake.system.service;
import com.base.service.IBasicService;
import com.snake.system.model.User;


/**
 * User: wenxy
 * Date: 2014-5-14
 */
public interface IUserService extends IBasicService<User> {

    public User getUserByEmail(String email) throws Exception;

    public User getUserByMobile(String mobile) throws Exception;

    public User getUserByLoginName(String loginName) throws Exception;
}
