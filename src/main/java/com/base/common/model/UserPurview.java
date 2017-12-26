package com.base.common.model;

public interface UserPurview {
	
	/**
	 * 个人设置权限类型
	 * @author xue
	 *
	 */
	public static enum PurviewType {
		PURVIEW_MIN,//最小权限
		PURVIEW_MAX,//最大权限
		PURVIEW_SOURCE,//以开放权限为准
		PURVIEW_USER//以用户设置为准
	}
	
	public Long getRight();
	
	public PurviewType getPurviewType();

}
