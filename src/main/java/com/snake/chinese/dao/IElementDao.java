package com.snake.chinese.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.chinese.model.Element;
import com.snake.chinese.model.relation.ElementType;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IElementDao extends IBasicDao<Element> {
    List<ElementType> getAllElementType() throws DaoException;
}
