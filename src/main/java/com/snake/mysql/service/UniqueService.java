package com.snake.mysql.service;

import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.mysql.dao.IUniqueDao;
import com.snake.mysql.model.Unique;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UniqueService extends BasicService<Unique> implements IUniqueService {
	
	@Resource(name= "uniqueDao")
	private IUniqueDao dao;

	@Override
	public IUniqueDao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Unique> getListByTableId(Long tableId) throws ServiceException {
		List<Unique> list = null;
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("table_id",tableId);
            list = getDao().find(map);
        }catch(Exception e){
            throw new ServiceException(e);
        }
		return list;
	}

}
