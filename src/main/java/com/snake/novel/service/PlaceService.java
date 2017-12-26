package com.snake.novel.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.snake.novel.model.Place;

@Service("placeService")
public class PlaceService extends AbstractService<Place> implements IPlaceService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

}
