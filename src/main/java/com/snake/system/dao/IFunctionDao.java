package com.snake.system.dao;

import com.base.dao.IBasicDao;
import com.snake.system.model.Function;
import com.snake.system.model.RoleUrl;

import java.util.List;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
public interface IFunctionDao extends IBasicDao<Function> {
    /**
     * 根据角色id,获取角色的所有菜单权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<Function> getFunctionsByRoleId(Long roleId)throws Exception;

    /**
     * 获得根菜单
     * @return
     * @throws Exception
     */
    public List<Function> getRootList()throws Exception;

    /**
     *获取可访问菜单的角色信息
     * @return
     * @throws Exception
     */
    public List<Long> getRoleIdsByFunctionId(Long functionId) throws Exception;

    /**
     * 添加角色的菜单访问权限
     * @param functionId
     * @param roleId
     * @throws Exception
     */
    public void addFunctionRole(Long functionId, Long roleId) throws Exception;

    /**
     * 移除角色的菜单访问权限
     * @param functionId
     * @param roleId
     * @throws Exception
     */
    public void removeFunctionRole(Long functionId, Long roleId) throws Exception;

    /**
     * 获得父级功能id获得子功能数量
     * @param parentId
     * @return
     * @throws Exception
     */
    public Integer getFunctionCount(Long parentId)throws Exception;

    /**
     * 获得所有角色关联的功能
     * @return
     * @throws Exception
     */
    public List<RoleUrl> getRoleFunction()throws Exception;
    /**
     * 删除菜单对应的权限记录
     * @param id
     * @throws com.base.exception.Exception
     */
    public void deleteFunctionRole(Long id) throws Exception;

    public Integer getMaxOrderByParentId(Long parentId) throws Exception;
}
