package com.snake.inter.model;

/**
 * Created by HP on 2016/10/13.
 */
public class ModelParameter extends Parameter {

    private Long modelId;
    private Integer keyType;//0-非关键值1-唯一可查/2-一对多

    public ModelParameter(){}

    public ModelParameter(String name,String code,String type,Integer length,String example,String regex,String remark,Integer keyType){
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
