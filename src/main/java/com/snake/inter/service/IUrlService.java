package com.snake.inter.service;
import com.base.service.IBasicService;
import com.snake.inter.model.Url;

import java.util.List;

public interface IUrlService extends IBasicService<Url> {
    public Url getObjectByUrl(String url) throws Exception;

    public List<Url> getListByApplicationId(Long applicationId) throws Exception;

    public List<Url> getListByGroupId(Long groupId) throws Exception;
}