package com.snake.system.dao;

import com.base.exception.DaoException;
import com.snake.resource.dao.AbstractResourceDao;
import com.snake.system.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
@Repository("roleDao")
public class RoleDao extends AbstractResourceDao<Role> implements IRoleDao {
    public RoleDao(){
        super(Role.class);
    }

    private static Map<Object,Role> map;

    public Map<Object, Role> getMap() {
        if(null == map){
            map = createMap();
        }
        return map;
    }

    public static Collection<Role> getStaticList(){
        return map.values();
    }

    public void changePwd(Role role) throws DaoException {
        this.sqlSession.update(Role.class.getName() + ".changePwd", role);
    }

    public List<Long> getFunctionIdsById(Long roleId) throws DaoException {
        return this.sqlSession.selectList(Role.class.getName() + ".getFunctionIdsById", roleId);
    }

    public void deleteRoleFunctionById(Long id) throws DaoException {
        this.sqlSession.delete(Role.class.getName() + ".deleteRoleFunctionById", id);
    }
}
