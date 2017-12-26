package com.module.template.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.template.model.Attribute;


public interface IAttributeService extends BaseService<Attribute> {

	/**
	 * 根据模块id获取模块的属性集合
	 * @param moduleId
	 * @return
	 */
	public List<Attribute> getListByModuleId(Long moduleId);

}
