package com.base.mvc;

import com.base.util.JsonUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * 数据传输对象(DateTransferObject)<br>
 * 建议在参数传递过程中尽量使用Dto来传递<br>
 * 
 * @author XiongChun
 * @since 2009-06-23
 * @see Dto
 * @see Serializable
 */
public class BaseDto extends HashMap implements Dto, Serializable {
	
	public BaseDto(){}
	
	public BaseDto(String key, Object value){
		put(key, value);
	}
	
	public BaseDto(Boolean success){
		setSuccess(success);
	}
	
	public BaseDto(Boolean success, String msg){
		setSuccess(success);
		setMsg(msg);
	}

	public BigDecimal getAsBigDecimal(String key) {
		Object obj = get(key);
		if (obj != null)
			return (BigDecimal) obj;
		else
			return null;
	}

	public Date getAsDate(String key) {
		Object obj = get(key);
		if (obj != null)
			return (Date) obj;
		else
			return null;
	}

	public Integer getAsInteger(String key) {
		Object obj = get(key);
		if (obj != null)
			return (Integer) obj;
		else
			return null;
	}

	public Long getAsLong(String key) {
		Object obj = get(key);
		if (obj != null)
			return (Long) obj;
		else
			return null;
	}

	public String getAsString(String key) {
		Object obj = get(key);
		if (obj != null)
			return (String) obj;
		else
			return "";
	}
	
	public List getAsList(String key){
		return (List)get(key);
	}

	public Timestamp getAsTimestamp(String key) {
		Object obj = get(key);
		if (obj != null)
			return (Timestamp) obj;
		else
			return null;
	}
	
	public Boolean getAsBoolean(String key){
		Object obj = get(key);
		if (obj != null)
			return (Boolean) obj;
		else
			return null;
	}

	public void setDefaultAList(List pList) {
		put("defaultAList", pList);
	}

	public void setDefaultBList(List pList) {
		put("defaultBList", pList);
	}

	public List getDefaultAList() {
		return (List) get("defaultAList");
	}

	public List getDefaultBList() {
		return (List) get("defaultBList");
	}
	
	public void setDefaultJson(String jsonString){
    	put("defaultJsonString", jsonString);
    }
    
    public String getDefaultJson(){
    	return getAsString("defaultJsonString");
    }

	public String toJson() {
		String strJson = null;
		strJson = JsonUtil.parseJson(this);
		return strJson;
	}
	
	public String toJson(String pFormat){
		String strJson = null;
		strJson = JsonUtil.parseJson(this, pFormat);
		return strJson;
	}
	
	public void setSuccess(Boolean pSuccess){
		put("success", pSuccess);
		if (pSuccess) {
			//put("bflag", "1");
		}else {
			//put("bflag", "0");
		}
		
	}
	
	public Boolean getSuccess(){
		return getAsBoolean("success");
	}
	
	public void setMsg(String pMsg){
		put("msg", pMsg);
	}
	
	public String getMsg(){
		return getAsString("msg");
	}
	
	public void println(){
		System.out.println(this);
	}
}
