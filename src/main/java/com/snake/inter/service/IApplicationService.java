package com.snake.inter.service;
import com.base.service.IBasicService;
import com.snake.inter.model.Application;

/**
 * Created by wenxy on 2016/11/30.
 */
public interface IApplicationService extends IBasicService<Application> {


    Application getObjectByCode(String code) throws Exception;

    void write(Long id, Long frameId) throws Exception;
}
