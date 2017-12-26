package com.module.node.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.node.model.NodePurview;

@Service("nodePurviewService")
public class NodePurviewService extends AbstractService<NodePurview> implements INodePurviewService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

}
