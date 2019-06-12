package com.snake.inter.service;
import com.base.service.IBasicService;
import com.snake.inter.model.Model;

import java.util.List;

public interface IModelService extends IBasicService<Model> {
    public Model getObjectByCode(String code) throws Exception;

    public List<Model> getListByApplicationId(Long applicationId,Integer status) throws Exception;
}