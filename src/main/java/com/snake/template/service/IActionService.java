package com.snake.template.service;
import com.base.service.IBasicService;
import com.snake.template.model.Action;

import java.util.List;


public interface IActionService extends IBasicService<Action> {

	/**
	 * 根据模块id获取模块的属性集合
	 * @param moduleId
	 * @return
	 */
	public List<Action> getListByModuleId(Long moduleId) throws Exception;

}
