package com.snake.chinese.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.chinese.model.Type;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface ITypeService extends IBasicService<Type> {
    List<Type> getListByWordId(Long wordId) throws ServiceException;
}
