package com.snake.inter.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.model.Group;

import java.util.List;

public interface IGroupDao extends IBasicDao<Group> {
    public List<Group> getListByApplicationId(Long applicationId) throws DaoException;
}