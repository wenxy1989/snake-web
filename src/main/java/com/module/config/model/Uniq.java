package com.module.config.model;

import java.io.Serializable;

import com.base.common.model.AbstractModel;
import com.module.config.ParseSQL;
/**
 * 唯一校验索引 Uniq
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 16:56:38
 */
public class Uniq extends AbstractModel implements ParseSQL {

	public String parseSQL() {
		StringBuffer sb = new StringBuffer();
		sb.append("unique ");
		if(null != this.getName()){
			sb.append(this.getName());
		}
		sb.append("(").append(this.content).append("),");
		return sb.toString();
	}
	
	private Long objId;
	private String code;
	private String content;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	@Override
	public String getUniqueIdName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}

}