package com.base.common.model;

import com.base.common.model.UserPurview.PurviewType;

public abstract class AbstractSourcePurview extends AbstractModel {
	
	public static final Long PURVIEW_NODE = 0L;//不开放
	public static final Long PURVIEW_VIEW = 1L;//所有人可查看
	public static final Long PURVIEW_ADD = 2L;//所有人可添加添加成员
	//以下权限只针对个人用户
	public static final Long PURVIEW_UPDATE = 3L;//所有人可以删除成员
	public static final Long PURVIEW_GIVE = 4L;//添加不大于本身等级的用户访问权限
	public static final Long PURVIEW_ALTER = 5L;//修改开放权限
	public static final Long PURVIEW_DELETE = 6L;//可以删除此组
	
	
	public abstract Long getOpenRight();
	
	/**
	 * 查看权限
	 * @param source 资源开放权限设置
	 * @param purview 对应资源的个人设置
	 * @return right
	 */
	public Long getPurview(UserPurview user){
		Long sourceRight = null,userRight = null, right = null;
		PurviewType type = null;
		if(user != null ){
			userRight = user.getRight();
			type = user.getPurviewType();
		}
		sourceRight = this.getOpenRight();
		if(sourceRight == null){
			sourceRight = AbstractSourcePurview.PURVIEW_NODE;
		}
		if(userRight == null){
			userRight = AbstractSourcePurview.PURVIEW_NODE;
		}
		if(null != type){
			type = PurviewType.PURVIEW_MAX;
		}
		if(type == PurviewType.PURVIEW_MAX){
			right = Math.max(sourceRight, userRight);
		}else if(type == PurviewType.PURVIEW_MIN){
			right = Math.min(sourceRight, userRight);
		}else if(type == PurviewType.PURVIEW_USER){
			right = userRight;
		}else if(type == PurviewType.PURVIEW_SOURCE){
			right = userRight;
		}
		return right;
	}

}
