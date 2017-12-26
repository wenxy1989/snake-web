package com.snake.system.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.snake.system.model.Role;


public interface IRoleService extends BaseService<Role> {

	public List<Role> getListByUserId(Long userId);

}
