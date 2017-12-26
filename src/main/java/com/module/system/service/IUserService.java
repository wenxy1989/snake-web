package com.module.system.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.system.model.Role;
import com.module.system.model.User;

public interface IUserService extends BaseService<User> {

	public List<Role> getRolesByUser(User user);

}
