package com.snake.inter.dao;

import com.base.dao.IBasicDao;
import com.snake.inter.model.Url;

import java.util.List;

public interface IUrlDao extends IBasicDao<Url> {
    public List<Url> getListByApplicationId(Long applicationId) throws Exception;

    public List<Url> getListByGroupId(Long groupId) throws Exception;
}