package com.module.template.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.template.model.Attribute;

@Service("attributeService")
public class AttributeService extends AbstractService<Attribute> implements IAttributeService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	public List<Attribute> getListByModuleId(Long moduleId) {
		String hql = "from Attribute a where a.moduleId=?";
		return this.dao.find(hql, moduleId);
	}

}
