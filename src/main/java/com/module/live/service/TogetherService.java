package com.module.live.service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.live.model.Together;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("togetherService")
public class TogetherService extends AbstractService<Together> implements ITogetherService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

}
