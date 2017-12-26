package com.snake.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.snake.system.model.Auth;

@Service("authService")
public class AuthService extends AbstractService<Auth> implements IAuthService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	public List<Auth> getListByUserId(Long userId) {
		return dao.find("from Auth a where a.userId=?",userId);
	}

	public void deleteByUserIdAndRoleId(Long userId, Long roleId) {
		dao.bulkUpdate("delete from Auth a where a.userId=? and a.roleId=?", new Object[]{userId,roleId});
	}
	
}
