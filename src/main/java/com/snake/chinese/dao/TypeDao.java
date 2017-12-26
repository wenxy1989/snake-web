package com.snake.chinese.dao;

import com.base.exception.DaoException;
import com.snake.chinese.model.Type;
import com.snake.resource.dao.AbstractResourceDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("typeDao")
public class TypeDao extends AbstractResourceDao<Type> implements ITypeDao {

    public TypeDao() {
        super(Type.class);
    }

    private static Map<Object,Type> map;

    public Map<Object, Type> getMap() {
        if(null == map){
            map = createMap();
        }
        return map;
    }

    public static Collection<Type> getStaticList(){
        return map.values();
    }

    public List<Type> getListByWordId(Long wordId) throws DaoException {
        return this.sqlSession.selectList(Type.class.getName()+".getListByWordId",wordId);
    }

    public void create(Type object) throws DaoException {
        super.create(object);
    }
}
