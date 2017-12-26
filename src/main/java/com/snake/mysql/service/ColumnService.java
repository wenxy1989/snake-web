package com.snake.mysql.service;

import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.mysql.dao.IColumnDao;
import com.snake.mysql.model.Column;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ColumnService extends BasicService<Column> implements IColumnService {
	
	@Resource(name="columnDao")
	private IColumnDao dao;

	@Override
	public IColumnDao getDao() {
		return dao;
	}

	public List<Column> getListByTableId(Long tableId) throws ServiceException {
		List<Column> list = null;
		try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("table_id",tableId);
			list = getDao().find(map);
		}catch(Exception e){
            throw new ServiceException(e);
		}
		return list;
	}

	public void deleteByTableId(Long tableId) throws ServiceException {
		try{
			getDao().deleteByTableId(tableId);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}
}
