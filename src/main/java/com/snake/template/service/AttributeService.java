package com.snake.template.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.template.dao.IAttributeDao;
import com.snake.template.model.Attribute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("attributeService")
public class AttributeService extends BasicService<Attribute> implements IAttributeService {
	
	@Resource(name="attributeDao")
	private IAttributeDao dao;

	@Override
	public IAttributeDao getDao() {
		return dao;
	}

	public List<Attribute> getListByModuleId(Long moduleId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("module_id",moduleId);
            return getDao().find(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

}
