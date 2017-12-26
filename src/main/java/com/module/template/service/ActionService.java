package com.module.template.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.template.model.Action;

@Service("actionService")
public class ActionService extends AbstractService<Action> implements IActionService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	public List<Action> getListByModuleId(Long moduleId) {
		String hql = "from Action a where a.moduleId=?";
		return this.dao.find(hql, moduleId);
	}

}
