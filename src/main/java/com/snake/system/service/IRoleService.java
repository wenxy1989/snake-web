package com.snake.system.service;
import com.base.service.IBasicService;
import com.snake.system.model.Role;

import java.util.List;


/**
 * User: wenxy
 * Date: 2014-5-14
 */
public interface IRoleService extends IBasicService<Role> {
    public void changePwd(Role role) throws Exception;

    public List<Role> getList() throws Exception;

    public Role getObjectByCode(String code) throws Exception;

    /**
     * 根据角色id获取的菜单ids
     * @param id
     * @return
     * @throws Exception
     */
    public List<Long> loadFunctionIdsById(Long id) throws Exception;

    /**
     * 删除角色菜单关联数据
     * @param id
     * @throws Exception
     */
    public void deleteRoleFunctionById(Long id) throws Exception;

    public void testTran(Long id)throws Exception;

    public List<Role> getListByUserId(Long userId) throws Exception;
}
