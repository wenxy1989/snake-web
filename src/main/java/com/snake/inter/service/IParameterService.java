package com.snake.inter.service;
import com.base.service.IBasicService;
import com.snake.inter.model.Parameter;

import java.util.List;

public interface IParameterService extends IBasicService<Parameter> {
    public Parameter getObjectByCode(String code) throws Exception;
}