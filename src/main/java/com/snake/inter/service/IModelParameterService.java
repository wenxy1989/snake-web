package com.snake.inter.service;
import com.base.service.IBasicService;
import com.snake.inter.model.ModelParameter;

import java.util.List;

public interface IModelParameterService extends IBasicService<ModelParameter> {
    ModelParameter getObject(Long modelId,String code) throws Exception;

    List<ModelParameter> getListByModelId(Long id) throws Exception;
}