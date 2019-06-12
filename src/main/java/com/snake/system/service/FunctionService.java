package com.snake.system.service;

import com.base.dao.IBasicDao;
import com.base.service.BasicService;
import com.snake.system.dao.IFunctionDao;
import com.snake.system.model.Function;
import com.snake.system.model.RoleUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Service
public class FunctionService extends BasicService<Function> implements IFunctionService {
    @Autowired
    @Qualifier("functionDao")
    protected IFunctionDao dao;

    @Override
    public IBasicDao<Function> getDao() {
        return this.dao;
    }

    /**
     * 根据角色id,获取角色的所有菜单权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<Function> getFunctionsByRoleId(Long roleId) throws Exception {
       try {
            return this.dao.getFunctionsByRoleId(roleId);
       }catch (Exception e){
           throw new Exception("get functions by role id error",e);
       }
    }

    public List<Function> getFunctionTree() throws Exception {
        try {
            List<Function> list= this.dao.getRootList();
            for(Function levelOne:list){
                List<Function> levelTwoList =  getChildren(levelOne.getId());
                if(null != levelTwoList){
                    for(Function levelTwo:levelTwoList){
                        List<Function> levelThreeList = getChildren(levelTwo.getId());
                        levelTwo.setChildren(levelThreeList);
                    }
                    levelOne.setChildren(levelTwoList);
                }
            }
            return list;
        } catch (Exception e) {
            throw new Exception("get function tree error",e);
        }
    }

    private List<Function> getChildren(Long parentId) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("parent_id",parentId);
        map.put("type_",1);
        return this.dao.find(map);
    }

    public List<Function> getList() throws Exception {
        try {
            return this.dao.getAll();
        }catch (Exception e){
            throw new Exception("获取菜单列表失败",e);
        }
    }

    public Function getObjectByCode(String code) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code_",code);
            return this.dao.findOne(map);
        }catch (Exception e){
            throw new Exception("根据idCard获取菜单列表失败",e);
        }
    }

    public List<Long> getRoleIdsByFunctionId(Long id) throws Exception{
        try {
            return this.dao.getRoleIdsByFunctionId(id);
        }catch (Exception e){
            throw new Exception("根据菜单id获取角色列表失败",e);
        }
    }

    public void addFunctionRole(Long functionId, Long roleId) throws Exception {
        try {
            this.dao.addFunctionRole(functionId,roleId);
        }catch (Exception e){
            throw new Exception("添加角色菜单访问权限失败",e);
        }
    }

    public void removeFunctionRole(Long functionId, Long roleId) throws Exception {
        try {
            this.dao.removeFunctionRole(functionId, roleId);
        }catch (Exception e){
            throw new Exception("移除角色菜单访问权限失败",e);
        }
    }

    /**
     * 获得父级功能id获得子功能数量
     * @param parentId
     * @return
     * @throws Exception
     */
    public Integer getFunctionCount(Long parentId) throws Exception {
        try {
            return this.dao.getFunctionCount(parentId);
        }catch (Exception e){
            throw new Exception("get function count by parent id error",e);
        }
    }

    /**
     * 获得所有角色关联的功能
     * @return
     * @throws Exception
     */
    public List<RoleUrl> getRoleFunction() throws Exception {
        try {
            return this.dao.getRoleFunction();
        }catch (Exception e){
            throw new Exception("get role function error",e);
        }
    }

    public void deleteFunctionRole(Long id) throws Exception {
        try {
            this.dao.deleteFunctionRole(id);
        }catch (Exception e){
            throw new Exception("delete function role error",e);
        }
    }

    public Integer getMaxOrderByParentId(Long parentId) throws Exception {
        try {
            return this.dao.getMaxOrderByParentId(parentId);
        }catch (Exception e){
            throw new Exception("getMaxOrderByParentId error",e);
        }
    }

}
