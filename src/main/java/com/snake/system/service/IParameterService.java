package com.snake.system.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.system.model.Parameter;


/**
 * User: wenxy
 * Date: 2016-11-2 18:22:45
 */
public interface IParameterService extends IBasicService<Parameter> {

    public Parameter getObjectByCode(String code) throws ServiceException;

}
