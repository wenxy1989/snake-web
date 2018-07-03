package com.snake.template.service;

import com.base.service.BasicService;
import com.snake.template.dao.IGroupDao;
import com.snake.template.model.Group;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("t_groupService")
public class GroupService extends BasicService<Group> implements IGroupService {
	
	@Resource(name = "t_groupDao")
	private IGroupDao dao;

	@Override
	public IGroupDao getDao() {
		return dao;
	}
}
