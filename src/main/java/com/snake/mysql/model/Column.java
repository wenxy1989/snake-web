package com.snake.mysql.model;

import com.snake.mysql.ParseSQL;
import org.apache.commons.lang.StringUtils;

/**
 * 列
 * @author wenxy
 * @version 1.00 ,Date: 2016-12-1 11:10:28
 */
public class Column implements ParseSQL {

    public static final String USE_TYPE_SAVE = "save";
    public static final String USE_TYPE_ONE_TO_ONE = "onetoone";
    public static final String USE_TYPE_ONE_TO_MANY = "onetomany";
    public static final String USE_TYPE_TYPE_CODE = "typecode";


    public static final String PAGE_STYLE_TEXT = "text";
    public static final String PAGE_STYLE_SELECT = "select";
    public static final String PAGE_STYLE_CHECKBOX = "checkbox";
    public static final String PAGE_STYLE_RADIO = "radio";
    private Long id;
    private Long tableId;//外键对应
    private String name;//属性对应数据库字段名
    private String code;//属性英文名
    private Boolean allowBlank;//自增，0否，1是
    private Boolean autoIncrement;//自增，0否，1是
    private String dataType;//属性数据类型，默认按照java与mysql对应规则
    private String type;//属性数据类型，默认按照java与mysql对应规则
    private String defaultValue;
    private String comment;
    private Integer orderPosition;
    private String createdTime;
    private Long creatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        if(StringUtils.isBlank(this.name)){
            this.name = this.code;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getAllowBlank() {
        return allowBlank;
    }

    public void setAllowBlank(Boolean allowBlank) {
        this.allowBlank = allowBlank;
    }

    public Boolean getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(Integer orderPosition) {
        this.orderPosition = orderPosition;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String parseSQL() {
        StringBuffer sb = new StringBuffer(this.getCode());
        sb.append(" ").append(getType());
        if (!getAllowBlank()) {
            sb.append("not ");
        }
        sb.append("null ").append(",");
        return sb.toString();
    }
}