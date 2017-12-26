package com.module.node.model;

import java.io.Serializable;

import com.base.common.model.AbstractSourcePurview;

public class NodeGroup extends AbstractSourcePurview {
	
	private Long right;//开放权限

	public Long getRight() {
		return right;
	}

	public void setRight(Long right) {
		this.right = right;
	}

	@Override
	public Long getOpenRight() {
		return right;
	}

	@Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueIdName() {
		// TODO Auto-generated method stub
		return null;
	}

}
