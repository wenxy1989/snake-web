package com.snake.mysql.service;


import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.mysql.model.Table;

import java.util.List;


public interface ITableService extends IBasicService<Table> {

	public List<Table> getListByApplicationId(Long databaseId) throws ServiceException;

	public void deleteByDatabaseId(Long databaseId) throws ServiceException;


}