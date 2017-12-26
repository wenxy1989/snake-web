package com.module.system.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.system.model.Role;


public interface IRoleService extends BaseService<Role> {

	public List<Role> getListByUserId(Long userId);

}
