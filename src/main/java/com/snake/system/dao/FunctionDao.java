package com.snake.system.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.system.model.Function;
import com.snake.system.model.RoleUrl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Repository("functionDao")
public class FunctionDao extends MybatisBasicDao<Function> implements IFunctionDao {
    public FunctionDao(){
        super(Function.class);
    }

    public Integer getMaxOrderByParentId(Long parentId) throws Exception {
        Integer order = this.sqlSession.selectOne(Function.class.getName() + ".selectMaxOrderByParentId", parentId);
        return order == null ? 0 : order;
    }

    @Override
    public void create(Function object) throws Exception {
        Integer order = getMaxOrderByParentId(object.getParentId());
        if(null == object.getOrder()) {
            object.setOrder(order + 1);
        }
        super.create(object);
    }

    /**
     * 根据角色id,获取角色的所有菜单权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<Function> getFunctionsByRoleId(Long roleId) throws Exception {
        return this.sqlSession.selectList(Function.class.getName()+".getFunctionsByRoleId",roleId);
    }

    /**
     * 获得菜单树节点对象
     * @return
     * @throws Exception
     */
    public List<Function> getRootList() throws Exception {
        return this.sqlSession.selectList(Function.class.getName()+".getRootList");
    }

    public List<Long> getRoleIdsByFunctionId(Long functionId) throws Exception{
        return this.sqlSession.selectList(Function.class.getName()+".getRoleIdsByFunctionId",functionId);
    }

    public void addFunctionRole(Long functionId, Long roleId) throws Exception {
        Map<String,Long> map = new HashMap<String, Long>();
        map.put("functionId",functionId);
        map.put("roleId",roleId);
        this.sqlSession.insert(Function.class.getName() + ".addFunctionRole", map);
    }

    public void removeFunctionRole(Long functionId, Long roleId) throws Exception {
        Map<String,Long> map = new HashMap<String, Long>();
        map.put("functionId",functionId);
        map.put("roleId",roleId);
        this.sqlSession.delete(Function.class.getName()+".removeFunctionRole",map);
    }

    /**
     * 获得父级功能id获得子功能数量
     * @param parentId
     * @return
     * @throws Exception
     */
    public Integer getFunctionCount(Long parentId) throws Exception {
        return (Integer)this.sqlSession.selectOne(Function.class.getName() + ".getFunctionCount",parentId);
    }

    /**
     * 获得所有角色关联的功能
     * @return
     * @throws Exception
     */
    public List<RoleUrl> getRoleFunction() throws Exception {
        return this.sqlSession.selectList(Function.class.getName()+".getRoleFunction");
    }

    public void deleteFunctionRole(Long id) throws Exception {
        this.sqlSession.delete(Function.class.getName()+".deleteFunctionRole",id);
    }
}
