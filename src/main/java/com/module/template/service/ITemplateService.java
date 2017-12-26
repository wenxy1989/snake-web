package com.module.template.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.template.model.BaseTemplate;


public interface ITemplateService extends BaseService<BaseTemplate> {

	/**
	 * 得到base框架对应的模板类型集合
	 * @param string
	 * @return
	 */
	public List<BaseTemplate> getListByType(String string);

}
