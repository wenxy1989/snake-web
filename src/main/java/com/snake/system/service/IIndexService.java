package com.snake.system.service;
import com.base.service.IBasicService;
import com.snake.system.model.Index;

/**
 * User: wenxy
 * Date: 2014-5-14
 */
public interface IIndexService extends IBasicService<Index> {
    Index getObjectByUserId(Long userId) throws Exception;

    Index getObjectByRoleId(Long roleId) throws Exception;
}
