package com.snake.inter.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.inter.model.Upload;

import java.util.List;

public interface IUploadService extends IBasicService<Upload> {
    public List<Upload> getListByUrlId(Long urlId) throws ServiceException;
    public Upload getObjectByCode(String code) throws ServiceException;
}