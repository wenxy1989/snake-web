package com.snake.template.service;
import com.base.service.IBasicService;
import com.snake.template.model.Attribute;

import java.util.List;


public interface IAttributeService extends IBasicService<Attribute> {

	/**
	 * 根据模块id获取模块的属性集合
	 * @param moduleId
	 * @return
	 */
	public List<Attribute> getListByModuleId(Long moduleId) throws Exception;

}
