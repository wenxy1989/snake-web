package com.snake.inter.model;

import com.base.Constants;

import java.util.List;

/**
 * 参数定义
 * Created by wenxy on 2016/3/27.
 */
public class Parameter{

    protected Long id;
    protected String name;//for example 学校
    protected String code;//for example school
    protected String type;//for example String
    protected Boolean isArray;//for example false,type is array
    protected Integer length;//for example String length is 12,length
    protected Boolean allowBlank;//for example false
    protected String regex;//for example /*xax**/ string matches
    protected String example;//for example 学校
    protected String remark;
    protected String createdTime;
    protected Long creatorId;

    private ModelObject modelObject;

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

    public Integer getLength() {
        if(null == length && org.apache.commons.lang.StringUtils.isNotBlank(this.type)){
            this.length = BaseType.getType(this.type).getLength();
        }
        return length;
    }

    public void setLength(Integer length) {
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

    public String getMysqlType() {
        String result = null;
        BaseType type = BaseType.getType(getType());
        if(null != type){
            result = type.getMysqlType();
        }
        return result;
    }

    public Parameter clone(){
        Parameter obj = new Parameter();
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
    }

    public <R extends Parameter> R cloneParameter(R result){
        result.setName(this.name);
        result.setCode(this.code);
        result.setAllowBlank(this.allowBlank);
        result.setType(this.type);
        result.setIsArray(this.isArray);
        result.setLength(this.length);
        result.setRegex(this.regex);
        result.setExample(this.example);
        result.setRemark(this.remark);
        return result;
    }

    private String addMarks(String value,String symbol){
        return symbol + value + symbol;
    }

    public String getExampleValue(){
        String value = null;
        if("String".equals(this.type) || null == BaseType.getType(this.type)){
            value = addMarks(this.example,Constants.SYMBOL_QUOTE);
        }else{
            value = this.example;
        }
        return value;
    }

    public String getExampleValueJsonString(){
        StringBuffer sb = new StringBuffer();
        if(this.isArray) {
            sb.append("[");
            sb.append(getExampleValue());
            sb.append(Constants.SYMBOL_COMMA);
            sb.append(getExampleValue());
            sb.append("]");
        }else{
            sb.append(getExampleValue());
        }
        return sb.toString();
    }

    public String getExampleJsonString(){
        StringBuffer sb = new StringBuffer();
        sb.append(Constants.SYMBOL_QUOTE).append(code).append(Constants.SYMBOL_QUOTE);
        sb.append(Constants.SYMBOL_COLON);
        sb.append(getExampleValueJsonString());
        return sb.toString();
    }

    public static String getExampleJsonString(List<? extends Parameter> parameterList){
        StringBuffer sb = new StringBuffer("{");
        if(null != parameterList && parameterList.size() > 0) {
            for (int i=0;i<parameterList.size();i++) {
                Parameter parameter = parameterList.get(i);
                sb.append(parameter.getExampleJsonString());
                if(i<parameterList.size()-1){
                    sb.append(Constants.SYMBOL_COMMA);
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

}
