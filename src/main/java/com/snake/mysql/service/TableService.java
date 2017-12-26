package com.snake.mysql.service;

import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.mysql.dao.ITableDao;
import com.snake.mysql.model.Table;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableService extends BasicService<Table> implements ITableService {
	
	@Resource(name= "tableDao")
	private ITableDao dao;

	@Override
	public ITableDao getDao() {
		return dao;
	}

	public List<Table> getListByApplicationId(Long databaseId) throws ServiceException {
		List<Table> list = null;
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("database_id",databaseId);
            list = getDao().find(map);
        }catch(Exception e){
            throw new ServiceException(e);
        }
		return list;
	}

	public void deleteByDatabaseId(Long databaseId) throws ServiceException {
		try{
			getDao().deleteByDatabaseId(databaseId);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}

}
