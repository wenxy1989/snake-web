package com.snake.inter.model;

import com.snake.mysql.model.Column;

/**
 * Created by HP on 2016/10/13.
 */
public class ModelParameter extends Parameter {

    private Long modelId;
    private Integer keyType;//0-非关键值1-唯一可查/2-一对多

    public ModelParameter() {
    }

    public ModelParameter(String name, String code, String type, Long length, String example, String regex, String remark, Integer keyType) {
        setName(name);
        setCode(code);
        setType(type);
        setLength(length);
        setExample(example);
        setRegex(regex);
        setRemark(remark);
        setKeyType(keyType);
        setIsArray(false);
        setAllowBlank(false);
    }

    private static String columnName(String comment,int length){
        return comment.length() > length ? comment.contains(":") ? comment.split(":")[0] : comment : comment;
    }

    public static ModelParameter build(Column column) {
        ModelParameter parameter = new ModelParameter();
        parameter.setName(columnName(column.getComment(),20));
        parameter.setCode(column.getName());
        if (null != column.getCharacterLength() && column.getCharacterLength() <= Integer.MAX_VALUE) {
            parameter.setLength(column.getCharacterLength());
        }
        parameter.setIsArray(false);
        parameter.setType(column.getDataType());
        parameter.setAllowBlank(column.getNullable());
        return parameter;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }
}
