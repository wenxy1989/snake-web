package com.snake.chinese.dao;

import com.base.exception.DaoException;
import com.snake.chinese.model.relation.WordType;
import com.snake.resource.dao.AbstractResourceDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("wordTypeDao")
public class WordTypeDao extends AbstractResourceDao<WordType> implements IWordTypeDao {

    public WordTypeDao() {
        super(WordType.class);
    }

    private static Map<Object,WordType> map;

    public Map<Object, WordType> getMap() {
        if(null == map){
            map = createMap();
        }
        return map;
    }

    public static Collection<WordType> getStaticList(){
        return map.values();
    }

    public void deleteObject(WordType wordType) throws DaoException {
        this.sqlSession.delete(WordType.class.getName() + ".deleteObject", wordType);
    }

}
