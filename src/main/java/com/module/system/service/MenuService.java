package com.module.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.system.model.Menu;

@Service("menuService")
public class MenuService extends AbstractService<Menu> implements IMenuService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}
	
}
