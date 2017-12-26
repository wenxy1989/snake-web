package com.snake.chinese.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.chinese.model.relation.StructureElement;

/**
 * Created by wen on 2015/5/1.
 */
public interface IStructureElementDao extends IBasicDao<StructureElement> {
    void removeObject(StructureElement obj) throws DaoException;
}
