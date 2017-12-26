package com.module.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.system.model.Role;

@Service("roleService")
public class RoleService extends AbstractService<Role> implements IRoleService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	public List<Role> getListByUserId(Long userId) {
		return dao.find("select r from Role r,Auth a where a.roleId=r.id and a.userId=?",userId);
	}
	
}
