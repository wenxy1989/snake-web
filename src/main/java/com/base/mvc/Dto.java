package com.base.mvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface Dto extends Map {

	public Integer getAsInteger(String pStr);

	public Long getAsLong(String pStr);

	public String getAsString(String pStr);

	public BigDecimal getAsBigDecimal(String pStr);

	public Date getAsDate(String pStr);

	public List getAsList(String key);

	public Timestamp getAsTimestamp(String pStr);
	
	public Boolean getAsBoolean(String key);

	public void setDefaultAList(List pList);

	public void setDefaultBList(List pList);

	public List getDefaultAList();

	public List getDefaultBList();

	public void setDefaultJson(String jsonString);

	public String getDefaultJson();

	public String toJson();
	
	public void println();

	public String toJson(String pFormat);
	
	public void setSuccess(Boolean pSuccess);
	
	public Boolean getSuccess();
	
	public void setMsg(String pMsg);

	public String getMsg();
	
	
	
}
