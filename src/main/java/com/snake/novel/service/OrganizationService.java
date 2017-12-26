package com.snake.novel.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.snake.novel.model.Organization;

@Service("organizationService")
public class OrganizationService extends AbstractService<Organization> implements IOrganizationService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

}
