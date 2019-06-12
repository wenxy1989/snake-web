package com.snake.inter.service;
import com.base.service.IBasicService;
import com.snake.inter.model.Group;

import java.util.List;

public interface IGroupService extends IBasicService<Group> {

    public Group getObjectByModel(String model) throws Exception;

    public List<Group> getListByApplicationId(Long applicationId) throws Exception;
}