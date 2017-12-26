package com.module.config.model;

import com.base.common.model.AbstractModel;
import com.base.utils.StringUtils;
import com.module.config.ParseSQL;

import java.io.Serializable;

/**
 * 属性 Attr
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 16:56:38
 */
public class Attr extends AbstractModel implements ParseSQL {

    public static final String USE_TYPE_SAVE = "save";
    public static final String USE_TYPE_ONE_TO_ONE = "onetoone";
    public static final String USE_TYPE_ONE_TO_MANY = "onetomany";
    public static final String USE_TYPE_TYPE_CODE = "typecode";


    public static final String PAGE_STYLE_TEXT = "text";
    public static final String PAGE_STYLE_SELECT = "select";
    public static final String PAGE_STYLE_CHECKBOX = "checkbox";
    public static final String PAGE_STYLE_RADIO = "radio";

	public String parseSQL() {
		StringBuffer sb = new StringBuffer(this.getColumn());
		sb.append(" ").append(this.dataType);
		if(null != this.dataLength){
			sb.append("(").append(this.dataLength).append(") ");
		}else{
			sb.append(" ");
		}
		if(null != this.allowBlank){
			if(this.allowBlank == 0){
				sb.append("not ");
			}
			sb.append("null ").append(",");
		}
		return sb.toString();
	}
	
	private Long objId;//外键对应
	private String code;//属性英文名
	private String column;//属性对应数据库字段名
	private String javaType;//java类型
	private String dataType;//属性数据类型，默认按照java与mysql对应规则
	private Integer dataLength;//属性数据长度，值域待完善例如[0-10]
	private Integer allowBlank;//是否可以为空，1可以为空0不能为空
	private Integer autoIncrement;//自增，0否，1是
	private String useType;//用途，主键、外键、类型代码、存储可见内容
	private String pageStyle;//页面样式，单选、多选、下拉、文本框、文本域
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public String getCode() {
		if(StringUtils.isEmpty(this.code)){
			return StringUtils.columnToAttribute(this.column);
		}else{
			return this.code;
		}
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getColumn() {
		if(StringUtils.isEmpty(this.column)){
			return StringUtils.attributeToColumn(this.code);
		}else{
			return this.column;
		}
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getDataType() {
		if(StringUtils.isEmpty(dataType) && StringUtils.notEmpty(this.javaType)){
			dataType = ColumnType.getMysqlByJava(this.javaType);
		}
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Integer getDataLength() {
		return dataLength;
	}
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}
	public Integer getAllowBlank() {
		if(this.allowBlank == null){
			this.allowBlank = 0;
		}
		return allowBlank;
	}
	public Integer getAutoIncrement() {
		if(this.autoIncrement == null){
			this.autoIncrement = 0;
		}
		return autoIncrement;
	}
	public void setAutoIncrement(Integer autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	public void setAllowBlank(Integer allowBlank) {
		this.allowBlank = allowBlank;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getPageStyle() {
		return pageStyle;
	}
	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
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
	
	public String getJavaType(){
		if(StringUtils.isEmpty(javaType)){
			javaType = ColumnType.getJavaByMysql(this.dataType);
		}
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

}