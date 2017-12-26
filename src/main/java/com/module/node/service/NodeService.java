package com.module.node.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.node.model.Node;

@Service("nodeService")
public class NodeService extends AbstractService<Node> implements INodeService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Node> getNodeListByParentId(Long id)  {
		List<Node> list = null;
		try{
			String hql = "from Node n n.parentId=?";
			list = dao.find(hql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
