package com.snake.template.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.base.util.HashMaps;
import com.snake.template.dao.ITemplateDao;
import com.snake.template.model.TemplateConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("templateService")
public class TemplateService extends BasicService<TemplateConfig> implements ITemplateService {
	
	@Resource(name="templateDao")
	private ITemplateDao dao;

	@Override
	public ITemplateDao getDao() {
		return dao;
	}

	public List<TemplateConfig> getListByType(String type) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("type_",type);
            return getDao().find(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

	public List<TemplateConfig> getListByCode(String code) throws ServiceException {
		try {
			return getDao().selectListByCode(code);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TemplateConfig> getListByFrameId(Long groupId) throws ServiceException {
		try {
			return getDao().find(HashMaps.build(String.class,Object.class).add("frame_id",groupId));
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
