package com.module.node.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.node.model.NodeGroup;

@Service("nodeGroupService")
public class NodeGroupService extends AbstractService<NodeGroup> implements INodeGroupService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

}
