package com.snake.mysql.service;


import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.mysql.model.Unique;

import java.util.List;


public interface IUniqueService extends IBasicService<Unique> {

	public List<Unique> getListByTableId(Long id) throws ServiceException;

}