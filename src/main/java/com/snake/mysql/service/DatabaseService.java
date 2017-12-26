package com.snake.mysql.service;

import com.base.service.BasicService;
import com.snake.mysql.dao.IDatabaseDao;
import com.snake.mysql.model.Database;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("databaseService")
public class DatabaseService extends BasicService<Database> implements IDatabaseService {
	
	@Resource(name= "databaseDao")
	private IDatabaseDao databaseDao;

	@Override
	public IDatabaseDao getDao() {
		return databaseDao;
	}

	public void createFile(Database database) {
		String databaseName = database.getCode();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("database", database);
	}

}
