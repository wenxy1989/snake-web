package com.snake.chinese.dao;

import com.base.exception.DaoException;
import com.snake.chinese.model.Structure;
import com.snake.resource.dao.AbstractResourceDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("structureDao")
public class StructureDao extends AbstractResourceDao<Structure> implements IStructureDao {

    public StructureDao() {
        super(Structure.class);
    }

    private static Map<Object,Structure> map;

    public Map<Object, Structure> getMap() {
        if(null == map){
            map = createMap();
        }
        return map;
    }

    public static Collection<Structure> getStaticList(){
        return map.values();
    }

    public void create(Structure object) throws DaoException {
        super.create(object);
    }
}
