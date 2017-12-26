package com.module.template.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.template.model.Action;


public interface IActionService extends BaseService<Action> {

	/**
	 * 根据模块id获取模块的属性集合
	 * @param moduleId
	 * @return
	 */
	public List<Action> getListByModuleId(Long moduleId);

}
