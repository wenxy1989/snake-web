package com.snake.inter.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.inter.model.Model;

import java.util.List;

public interface IModelService extends IBasicService<Model> {
    public Model getObjectByCode(String code) throws ServiceException;

    public List<Model> getListByApplicationId(Long applicationId) throws ServiceException;
}