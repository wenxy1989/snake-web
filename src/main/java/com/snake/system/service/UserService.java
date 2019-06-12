package com.snake.system.service;
import com.base.service.BasicService;
import com.snake.system.dao.IUserDao;
import com.snake.system.model.User;
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
public class UserService extends BasicService<User> implements IUserService {
    @Autowired
    @Qualifier("userDao")
    protected IUserDao dao;

    @Override
    public IUserDao getDao() {
        return this.dao;
    }

    public User getUserByLoginName(String loginName) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("login_name",loginName);
            return dao.findOne(map);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public User getUserByEmail(String email) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("email_",email);
            return dao.findOne(map);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public User getUserByMobile(String mobile) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("mobile_",mobile);
            return dao.findOne(map);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

}
