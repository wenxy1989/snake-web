package com.snake.system.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.snake.system.model.Role;
import com.snake.system.model.User;

public interface IUserService extends BaseService<User> {

	public List<Role> getRolesByUser(User user);

}
