package com.snake.system.service;

import com.base.dao.IBasicDao;
import com.base.service.BasicService;
import com.snake.system.dao.IRoleDao;
import com.snake.system.model.Role;
import com.snake.system.dao.IUserDao;
import com.snake.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Service
public class RoleService extends BasicService<Role> implements IRoleService {
    @Autowired
    @Qualifier("roleDao")
    protected IRoleDao dao;
    @Autowired
    @Qualifier("userDao")
    protected IUserDao userDao;

    @Override
    public IBasicDao<Role> getDao() {
        return this.dao;
    }

    public void changePwd(Role role) throws Exception {
        try{
            dao.changePwd(role);
        }catch(Exception e){
            throw new Exception("修改密码失败",e);
        }
    }

    public List<Role> getList() throws Exception {
        try {
            return this.dao.getAll();
        }catch (Exception e){
            throw new Exception("获取用户列表失败",e);
        }
    }

    public Role getObjectByCode(String code) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code_",code);
            return this.dao.findOne(map);
        }catch (Exception e){
            throw new Exception("根据idCard获取用户列表失败",e);
        }
    }

    public List<Long> loadFunctionIdsById(Long id) throws Exception {
        try {
            return this.dao.getFunctionIdsById(id);
        }catch (Exception e){
            throw new Exception("根据角色id获取的菜单ids失败",e);
        }
    }

    public void deleteRoleFunctionById(Long id) throws Exception {
        try {
            this.dao.deleteRoleFunctionById(id);
        }catch (Exception e){
            throw new Exception("根据角色id删除角色菜单信息失败",e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor ={Throwable.class })
    public void testTran(Long id)throws Exception{
        try{
            this.dao.delete(id);
            User user = new User();
            user.setRoleId(new Long(2));
            user.setLoginName("test100");
            user.setUserName("test100");
            user.setLoginPwd("123456");
            user.setStatus(1);
            user.setCreatorId(new Long(2));
            user.setCreatedTime("2014-09-10 10:20:00");
            this.userDao.create(user);
        }catch(Exception e){
            throw new Exception("事务测试失败.",e);
        }
    }

    public List<Role> getListByUserId(Long userId) throws Exception {
        try {
            return this.dao.getAll();//todo
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
