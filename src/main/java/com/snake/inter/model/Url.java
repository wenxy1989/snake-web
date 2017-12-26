package com.snake.inter.model;

import java.util.List;

/**
 * inter Object
 * default method is json
 * request parameter position is header
 * <p>
 * <title>enctype值和意义:</title>
 * <li>application/x-www-form-urlencoded 在发送前编码所有字符（默认）</li>
 * <li>multipart/form-data 不对字符编码。在使用包含文件上传控件的表单时，必须使用该值。</li>
 * <li>text/plain 空格转换为 "+" 加号，但不对特殊字符编码。</li>
 * </p>
 */
public class Url{
    private Long id;
    private Long applicationId;//应用id
    private Long groupId;//分组id
    private String name;//名称
    private String url;//访问地址
    private Integer type;//0-get,1-post,2-put,3-delete,4-stream
    private Integer position;//0-header,1-payload
    private String remark;//备注
    private String logic;//处理逻辑,例:一般查询/通过用户id获取用户参加过的活动信息并返回最近三个月内的活动信息/查找用户登录名含有'wxy'的用户信息并返回
    private String operate;//数据操作1-select,2-insert,3-update,4-delete
    private Integer uploadType;//返回数据类型:1-json,2-json array,3-value,4-value array
    private String uploadData;//上传数据:简单表示全部参数，例:name string(20) not null default('姓名') only
    private String uploadExample;//上传数据样例,例:{name:'wenxy',age:27,id:11}
    private Integer resultType;//返回数据类型:1-json,2-json array,3-value,4-value array
    private String resultData;//返回数据:简单表示全部参数,例:name string(20) not null default('姓名') only
    private String resultExample;//返回数据样例,例:[{name:'you',id:11,age:27},{name:'wen',id:12,age:27}]或{name:'liu',id:22,age:27}
    private String createdTime;
    private Long creatorId;
    private Integer status;//状态:0-草稿,1-提交,2-审核不通过,3-审核通过,4-有异议

    private List<Parameter> uploadParameters;
    private List<Parameter> resultParameters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    public String getUploadData() {
        return uploadData;
    }

    public void setUploadData(String uploadData) {
        this.uploadData = uploadData;
    }

    public String getUploadExample() {
        return uploadExample;
    }

    public void setUploadExample(String uploadExample) {
        this.uploadExample = uploadExample;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public String getResultExample() {
        return resultExample;
    }

    public void setResultExample(String resultExample) {
        this.resultExample = resultExample;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }

    public List<Parameter> getResultParameters() {
        return resultParameters;
    }

    public void setResultParameters(List<Parameter> resultParameters) {
        this.resultParameters = resultParameters;
    }

    public List<Parameter> getUploadParameters() {
        return uploadParameters;
    }

    public void setUploadParameters(List<Parameter> uploadParameters) {
        this.uploadParameters = uploadParameters;
    }

    public String getUploadJsonString(){
        return Parameter.getExampleJsonString(this.uploadParameters);
    }

    public String getResultJsonString(){
        if(null != this.resultParameters && this.resultParameters.size() > 0){
            return Parameter.getExampleJsonString(this.resultParameters);
        }else{
            return "{\"code\":0,\"message\":\"success\"}";
        }
    }

}