package com.module.system.model;

import java.io.Serializable;

import com.base.common.model.AbstractModel;
import com.base.utils.TimeTools;

public class Parameter extends AbstractModel {
	
	public static final String KEY_UNIQUE_ID = "unique_id";
	
	private String key;
	private String stringValue;
	private Long longValue;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}
	
	public static Parameter getUniqueIdParameter(String name){
		Parameter parameter = new Parameter();
		parameter.setKey(name);
		parameter.setLongValue(1l);
		parameter.setName("系统主键");
		parameter.setTitle("系统内唯一的长整形主键");
		parameter.setCreateTime(TimeTools.now());
		return parameter;
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
