package com.snake.chinese.dao;

import com.base.exception.DaoException;
import com.snake.chinese.model.Element;
import com.snake.chinese.model.relation.ElementType;
import com.snake.resource.dao.AbstractResourceDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("elementDao")
public class ElementDao extends AbstractResourceDao<Element> implements IElementDao {

    public ElementDao() {
        super(Element.class);
    }

    private static Map<Object,Element> map;

    public Map<Object, Element> getMap() {
        if(null == map){
            map = createMap();
        }
        return map;
    }

    public static Collection<Element> getStaticList(){
        return map.values();
    }

    public List<ElementType> getAllElementType() throws DaoException {
        return this.sqlSession.selectList(Element.class.getName()+".getAllElementType");
    }

    public void create(Element object) throws DaoException {
        super.create(object);
    }
}
