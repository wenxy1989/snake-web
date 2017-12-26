package com.module.node.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.node.model.Node;

public interface INodeService extends BaseService<Node> {

	/**
	 * 根据id去子元素列表
	 * @param id
	 * @return
	 * @
	 */
	public List<Node> getNodeListByParentId(Long id);

}
