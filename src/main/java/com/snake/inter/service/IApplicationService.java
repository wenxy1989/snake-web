package com.snake.inter.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.inter.model.Application;

/**
 * Created by wenxy on 2016/11/30.
 */
public interface IApplicationService extends IBasicService<Application> {


    public Application getObjectByCode(String code) throws ServiceException;

    public Application getDetails(Long id) throws ServiceException;
}
