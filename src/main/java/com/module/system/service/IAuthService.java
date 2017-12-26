package com.module.system.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.system.model.Auth;


public interface IAuthService extends BaseService<Auth> {

	public List<Auth> getListByUserId(Long userId);

	public void deleteByUserIdAndRoleId(Long userId, Long roleId);

}
