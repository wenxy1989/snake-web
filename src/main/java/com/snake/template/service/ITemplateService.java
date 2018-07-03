package com.snake.template.service;


import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.template.model.Template;

import java.util.List;


public interface ITemplateService extends IBasicService<Template> {

	/**
	 * 得到base框架对应的模板类型集合
	 * @param string
	 * @return
	 */
	public List<Template> getListByType(String string) throws ServiceException;

	public List<Template> getListByCode(String code)throws ServiceException;
}
