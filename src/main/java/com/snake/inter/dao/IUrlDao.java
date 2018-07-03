package com.snake.inter.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.model.Url;

import java.util.List;

public interface IUrlDao extends IBasicDao<Url> {
    public List<Url> getListByApplicationId(Long applicationId) throws DaoException;

    public List<Url> getListByGroupId(Long groupId) throws DaoException;
}