package com.snake.chinese.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.chinese.model.relation.StructureElement;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("structureElementDao")
public class StructureElementDao extends MybatisBasicDao<StructureElement> implements IStructureElementDao {

    public StructureElementDao() {
        super(StructureElement.class);
    }

    public void removeObject(StructureElement obj) throws DaoException {
        this.sqlSession.delete(StructureElement.class.getName() + ".removeObject", obj);
    }

    public void create(StructureElement object) throws DaoException {
        super.create(object);
    }
}
