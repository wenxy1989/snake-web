package com.snake.template.service;

import com.base.service.BasicService;
import com.snake.template.dao.IFrameDao;
import com.snake.template.model.Frame;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("frameService")
public class FrameService extends BasicService<Frame> implements IFrameService {
	
	@Resource(name = "frameDao")
	private IFrameDao dao;

	@Override
	public IFrameDao getDao() {
		return dao;
	}
}
