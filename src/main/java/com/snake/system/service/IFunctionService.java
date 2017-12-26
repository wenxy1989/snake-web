package com.snake.system.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.system.model.Function;
import com.snake.system.model.RoleUrl;

import java.util.List;


/**
 * User: wenxy
 * Date: 2014-5-14
 */
public interface IFunctionService extends IBasicService<Function> {
    /**
     * 根据角色id,获取角色的所有菜单权限
     * @param roleId
     * @return
     * @throws ServiceException
     */
    public List<Function> getFunctionsByRoleId(Long roleId)throws ServiceException;

    /**
     * 获得菜单树
     * @return
     * @throws ServiceException
     */
    public List<Function> getFunctionTree()throws ServiceException;

    public List<Function> getList() throws ServiceException;

    public Function getObjectByCode(String code) throws ServiceException;

    /**
     * 获取可访问菜单的角色信息
     * @return
     * @throws ServiceException
     */
    public List<Long> getRoleIdsByFunctionId(Long id) throws ServiceException;

    /**
     * 添加角色的菜单访问权限
     * @param functionId
     * @param roleId
     * @throws ServiceException
     */
    public void addFunctionRole(Long functionId, Long roleId) throws ServiceException;

    /**
     * 移除角色的菜单访问权限
     * @param functionId
     * @param roleId
     * @throws ServiceException
     */
    public void removeFunctionRole(Long functionId, Long roleId) throws ServiceException;

    /**
     * 获得父级功能id获得子功能数量
     * @param parentId
     * @return
     * @throws ServiceException
     */
    public Integer getFunctionCount(Long parentId)throws ServiceException;

    /**
     * 获得所有角色关联的功能
     * @return
     * @throws ServiceException
     */
    public List<RoleUrl> getRoleFunction()throws ServiceException;

    /**
     * 删除菜单对应的权限记录
     * @param id
     * @throws ServiceException
     */
    public void deleteFunctionRole(Long id)throws ServiceException;

    public Integer getMaxOrderByParentId(Long parentId)throws ServiceException;
}
