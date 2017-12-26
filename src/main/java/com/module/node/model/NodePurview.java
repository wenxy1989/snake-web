package com.module.node.model;

import java.io.Serializable;

import com.base.common.model.AbstractModel;
import com.base.common.model.UserPurview;

public class NodePurview extends AbstractModel implements UserPurview {
	private static final String PURVIEW_MAX = "purview_max";
	private static final String PURVIEW_MIN = "purview_min";
	private static final String PURVIEW_SOURCE = "purview_source";
	private static final String PURVIEW_USER = "purview_user";
	private Long groupId;
	private Long right;//权限
	private String purviewType;//
	
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public void setRight(Long right) {
		this.right = right;
	}
	public void setPurviewType(String purviewType) {
		this.purviewType = purviewType;
	}
	@Override
	public Long getRight() {
		return right;
	}
	@Override
	public PurviewType getPurviewType() {
		PurviewType type = PurviewType.PURVIEW_MAX;
		if(PURVIEW_MAX.equals(this.purviewType)){
			type = PurviewType.PURVIEW_MAX;
		}else if(PURVIEW_MIN.equals(this.purviewType)){
			type = PurviewType.PURVIEW_MIN;
		}else if(PURVIEW_SOURCE.equals(this.purviewType)){
			type = PurviewType.PURVIEW_SOURCE;
		}else if(PURVIEW_USER.equals(this.purviewType)){
			type = PurviewType.PURVIEW_USER;
		}
		return type;
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
