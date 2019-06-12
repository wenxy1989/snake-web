package com.snake.inter.service;
import com.base.service.IBasicService;
import com.snake.inter.model.Result;

import java.util.List;

public interface IResultService extends IBasicService<Result> {
    public List<Result> getListByUrlId(Long urlId) throws Exception;
    public Result getObjectByCode(String code) throws Exception;
}