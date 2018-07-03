package com.snake.template.service;

import com.base.service.BasicService;
import com.snake.template.dao.IModuleDao;
import com.snake.template.model.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("moduleService")
public class ModuleService extends BasicService<Module> implements IModuleService {
	
	@Resource(name="moduleDao")
	private IModuleDao dao;

	@Override
	public IModuleDao getDao() {
		return dao;
	}

}
