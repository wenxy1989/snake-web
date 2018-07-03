package com.snake.inter.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.inter.model.Group;

import java.util.List;

public interface IGroupService extends IBasicService<Group> {

    public Group getObjectByModel(String model) throws ServiceException;

    public List<Group> getListByApplicationId(Long applicationId) throws ServiceException;
}