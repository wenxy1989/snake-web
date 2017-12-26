package com.snake.mysql.service;

import com.base.service.IBasicService;
import com.snake.mysql.model.Database;


public interface IDatabaseService extends IBasicService<Database> {

	public void createFile(Database database);

}