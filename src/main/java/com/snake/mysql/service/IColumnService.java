package com.snake.mysql.service;


import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.mysql.model.Column;

import java.util.List;


public interface IColumnService extends IBasicService<Column> {

	public List<Column> getListByTableId(Long id) throws ServiceException;

	void deleteByTableId(Long tableId) throws ServiceException;
}