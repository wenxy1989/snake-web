package com.snake.inter.model;

import com.base.Constants;
import com.snake.mysql.model.Column;
import com.snake.resource.dao.JavaColumnProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.List;

/**
 * 参数定义
 * Created by wenxy on 2016/3/27.
 */
public class Parameter {

    protected Long id;
    protected String name;//for example 学校
    protected String code;//for example school
    protected String type;//for example String
    protected Boolean isArray;//for example false,type is array
    protected Long length;//for example String length is 12,length
    protected Boolean allowBlank;//for example false
    protected String regex;//for example /*xax**/ string matches
    protected String example;//for example 学校
    protected String remark;
    protected String createdTime;
    protected Long creatorId;
    protected String columnName;
    protected String columnType;
    protected String columnComment;

    private ModelObject modelObject;

    private static JavaColumnProperties javaColumnProperties;

    static {
        try {
            javaColumnProperties = new JavaColumnProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String columnName(String comment) {
        return comment.contains(":") ? comment.split(":")[0] : comment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        if (StringUtils.isBlank(this.code)) {
            this.code = columnName;
        }
    }

    public String getColumnType() {
        if (StringUtils.isBlank(this.columnType)) {
            this.columnType = javaColumnProperties.getColumnType(this.type);
        }
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
        if (StringUtils.isBlank(this.type)) {
            this.type = javaColumnProperties.getJavaType(columnType);
        }
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
        if (StringUtils.isBlank(this.name)) {
            this.name = columnName(columnComment);
        }
        if (StringUtils.isBlank(this.remark)) {
            this.remark = columnComment;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsArray() {
        return isArray;
    }

    public void setIsArray(Boolean isArray) {
        this.isArray = isArray;
    }

    public Long getLength() {
        return this.length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getExample() {
        return example;
    }

    public ModelObject getModelObject() {
        return modelObject;
    }

    public void setModelObject(ModelObject modelObject) {
        this.modelObject = modelObject;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public Parameter clone() throws CloneNotSupportedException {
        return this.clone(Parameter.class);
    }

    public static <R extends Parameter> R build(Class<R> clazz, Column column) throws CloneNotSupportedException {
        try {
            R parameter = clazz.newInstance();
            parameter.setColumnComment(column.getComment());
            parameter.setColumnName(column.getName());
            if (null != column.getCharacterLength() && column.getCharacterLength() <= Integer.MAX_VALUE) {
                parameter.setLength(column.getCharacterLength());
            }
            parameter.setIsArray(false);
            parameter.setColumnType(column.getDataType());
            parameter.setAllowBlank(column.getNullable());
            return parameter;
        } catch (IllegalAccessException e) {
            throw new CloneNotSupportedException(e.getMessage());
        } catch (InstantiationException e) {
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    public <R extends Parameter> R clone(Class<R> clazz) throws CloneNotSupportedException {
        try {
            R obj = clazz.newInstance();
            obj.setName(this.name);
            obj.setCode(this.code);
            obj.setAllowBlank(this.allowBlank);
            obj.setType(this.type);
            obj.setIsArray(this.isArray);
            obj.setLength(this.length);
            obj.setRegex(this.regex);
            obj.setExample(this.example);
            obj.setRemark(this.remark);
            return obj;
        } catch (IllegalAccessException e) {
            throw new CloneNotSupportedException(e.getMessage());
        } catch (InstantiationException e) {
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    private String addMarks(String value, String symbol) {
        return symbol + value + symbol;
    }

    public String getExampleValue() {
        return "String".equals(this.type) ? addMarks(this.example, Constants.SYMBOL_QUOTE) : this.example;
    }

    public String getExampleValueJsonString() {
        StringBuffer sb = new StringBuffer();
        if (this.isArray) {
            sb.append("[");
            sb.append(getExampleValue());
            sb.append(Constants.SYMBOL_COMMA);
            sb.append(getExampleValue());
            sb.append("]");
        } else {
            sb.append(getExampleValue());
        }
        return sb.toString();
    }

    public String getExampleJsonString() {
        StringBuffer sb = new StringBuffer();
        sb.append(Constants.SYMBOL_QUOTE).append(code).append(Constants.SYMBOL_QUOTE);
        sb.append(Constants.SYMBOL_COLON);
        sb.append(getExampleValueJsonString());
        return sb.toString();
    }

    public static String getExampleJsonString(List<? extends Parameter> parameterList) {
        StringBuffer sb = new StringBuffer("{");
        if (null != parameterList && parameterList.size() > 0) {
            for (int i = 0; i < parameterList.size(); i++) {
                Parameter parameter = parameterList.get(i);
                sb.append(parameter.getExampleJsonString());
                if (i < parameterList.size() - 1) {
                    sb.append(Constants.SYMBOL_COMMA);
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

}
