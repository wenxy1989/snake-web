package com.snake.system.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
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
     * @throws DaoException
     */
    public List<Function> getFunctionsByRoleId(Long roleId)throws DaoException;

    /**
     * 获得根菜单
     * @return
     * @throws DaoException
     */
    public List<Function> getRootList()throws DaoException;

    /**
     *获取可访问菜单的角色信息
     * @return
     * @throws DaoException
     */
    public List<Long> getRoleIdsByFunctionId(Long functionId) throws DaoException;

    /**
     * 添加角色的菜单访问权限
     * @param functionId
     * @param roleId
     * @throws DaoException
     */
    public void addFunctionRole(Long functionId, Long roleId) throws DaoException;

    /**
     * 移除角色的菜单访问权限
     * @param functionId
     * @param roleId
     * @throws DaoException
     */
    public void removeFunctionRole(Long functionId, Long roleId) throws DaoException;

    /**
     * 获得父级功能id获得子功能数量
     * @param parentId
     * @return
     * @throws DaoException
     */
    public Integer getFunctionCount(Long parentId)throws DaoException;

    /**
     * 获得所有角色关联的功能
     * @return
     * @throws DaoException
     */
    public List<RoleUrl> getRoleFunction()throws DaoException;
    /**
     * 删除菜单对应的权限记录
     * @param id
     * @throws com.base.exception.ServiceException
     */
    public void deleteFunctionRole(Long id) throws DaoException;

    public Integer getMaxOrderByParentId(Long parentId) throws DaoException;
}
