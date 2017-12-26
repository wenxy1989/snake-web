package com.module.template.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.template.model.BaseTemplate;

@Service("templateService")
public class TemplateService extends AbstractService<BaseTemplate> implements ITemplateService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	public List<BaseTemplate> getListByType(String type) {
		String hql = "from BaseTemplate b where b.templateType=?";
		return this.dao.find(hql, type);
	}

}
