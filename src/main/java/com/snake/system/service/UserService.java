package com.snake.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.snake.system.model.Role;
import com.snake.system.model.User;

public class UserService extends AbstractService<User> implements UserDetailsService,IUserService {
	
	@Resource(name="dao")
	private Dao dao;
	
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	@Override
	public Dao getDao(){
		return this.dao;
	}

	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = null;
		List<User> users = dao.find("from User u where u.loginname=?",username);
		if(users !=null && users.size() > 0){
			user = users.get(0);
			List<Role> roleList = (List<Role>) dao.find("select r from Role r,Auth a where a.userId =? and r.id=a.roleId",user.getId());
			user.setRoles(roleList);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRolesByUser(User user) {
		return dao.loadAll(Role.class);
	}
}