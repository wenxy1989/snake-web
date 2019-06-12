package com.snake.system.dao;

import com.base.dao.IBasicDao;
import com.snake.system.model.Role;

import java.util.List;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
public interface IRoleDao extends IBasicDao<Role> {
    public void changePwd(Role role) throws Exception;

    /**
     * 根据角色id获取关联的菜单ids
     * @param id
     * @return
     * @throws Exception
     */
    public List<Long> getFunctionIdsById(Long id) throws Exception;

    /**
     * 根据id删除角色菜单关联数据
     * @param id
     * @throws Exception
     */
    public void deleteRoleFunctionById(Long id) throws Exception;

}
