package com.snake.system.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.system.model.Role;

import java.util.List;


/**
 * User: wenxy
 * Date: 2014-5-14
 */
public interface IRoleService extends IBasicService<Role> {
    public void changePwd(Role role) throws ServiceException;

    public List<Role> getList() throws ServiceException;

    public Role getObjectByCode(String code) throws ServiceException;

    /**
     * 根据角色id获取的菜单ids
     * @param id
     * @return
     * @throws ServiceException
     */
    public List<Long> loadFunctionIdsById(Long id) throws ServiceException;

    /**
     * 删除角色菜单关联数据
     * @param id
     * @throws ServiceException
     */
    public void deleteRoleFunctionById(Long id) throws ServiceException;

    public void testTran(Long id)throws ServiceException;

    public List<Role> getListByUserId(Long userId) throws ServiceException;
}
