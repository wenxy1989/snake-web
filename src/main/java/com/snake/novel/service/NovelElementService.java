package com.snake.novel.service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.snake.novel.model.NovelElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("elementService")
public class NovelElementService extends AbstractService<NovelElement> implements INovelElementService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public void setNovelElements(NovelElement element) {
		NovelElement obj = new NovelElement();
		element.setBelongId(element.getBelongId());
        element.setElements(dao.findByExample(element));
	}

}
