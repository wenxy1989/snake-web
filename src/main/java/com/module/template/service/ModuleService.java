package com.module.template.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.template.model.Module;

@Service("moduleService")
public class ModuleService extends AbstractService<Module> implements IModuleService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

}
