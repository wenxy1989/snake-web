package com.snake.template.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.template.dao.ITemplateDao;
import com.snake.template.model.Template;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("templateService")
public class TemplateService extends BasicService<Template> implements ITemplateService {
	
	@Resource(name="templateDao")
	private ITemplateDao dao;

	@Override
	public ITemplateDao getDao() {
		return dao;
	}

	public List<Template> getListByType(String type) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("type_",type);
            return getDao().find(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

	public List<Template> getListByCode(String code) throws ServiceException {
		try {
			return getDao().selectListByCode(code);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
