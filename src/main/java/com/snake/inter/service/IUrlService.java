package com.snake.inter.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.inter.model.Url;

import java.util.List;

public interface IUrlService extends IBasicService<Url> {
    public Url getObjectByUrl(String url) throws ServiceException;

    public List<Url> getListByApplicationId(Long applicationId) throws ServiceException;

    public List<Url> getListByGroupId(Long groupId) throws ServiceException;
}