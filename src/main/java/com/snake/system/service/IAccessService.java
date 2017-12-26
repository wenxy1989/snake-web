package com.snake.system.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.snake.system.model.Access;


public interface IAccessService extends BaseService<Access> {

	public void deleteByRoleIdAndMenuId(Long roleId, Long menuId);

	public List<Access> getListByRoleId(Long roleId);

}
