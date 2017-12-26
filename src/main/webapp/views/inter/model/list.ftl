<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    $(document).ready(function(){
        var ope_result = "${ope_result}";
        if(notBlank(ope_result) && ope_result.indexOf(",") > 0){
            var operates = ope_result.split(",");
            var result = operates[0];
            var timeMils = operates[1];
            var nowMils = new Date().getTime();
            if(Math.abs(timeMils - nowMils) < 1000){
                myAlert(result);
            }
        }
    });
    function toAdd(){
        window.location.href='${request.contextPath}/inter/model/${applicationId}/toAdd';
    }
    function toEdit(id){
        window.location.href = "${request.contextPath}/inter/model/${applicationId}/toEdit?id="+id;
    }
    function toDelete(id){
        myConfirm(" delete ?","删除后无法恢复",function(){
            window.location.href = "${request.contextPath}/inter/model/${applicationId}/delete?id="+id;
        });
    }
    function toDetails(id){
        window.open("${request.contextPath}/inter/model/${applicationId}/details?id="+id,"_blank");
    }
    function toModelInter(id){
        window.open("${request.contextPath}/inter/model/${applicationId}/inter?id="+id,"_blank");
    }
    function toInterUrl(){
        window.location.href ="${request.contextPath}/inter/url/${applicationId}/page";
    }
    function parameterList(id){
        window.open("${request.contextPath}/inter/model/parameter/page?modelId="+id);
    }
    function doSubmit(id){
        myConfirm(" do Submit ?","",function(){
            window.location.href = "${request.contextPath}/inter/model/${applicationId}/submit?id="+id;
        });
    }
    function doPublish(id){
        myConfirm(" do Publish ?","",function(){
            window.location.href = "${request.contextPath}/inter/model/${applicationId}/publish?id="+id;
        });
    }
    function doReturn(id){
        myConfirm(" do Retrun ?","",function(){
            window.location.href = "${request.contextPath}/inter/model/${applicationId}/return?id="+id;
        });
    }
    function writeCode() {
        $.post("${request.contextPath}/inter/model/${applicationId}/writeCode", function (result) {
            myAlert(result);
        });
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据模型</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-2">
                <div class="am-btn-toolbar">
            <@com.security url="/inter/model/${applicationId}/add">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toAdd()">
                            <span class="am-icon-plus"> 新增</span>
                        </button>
                    </div>
            </@com.security>
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toInterUrl()">
                            <span class="am-icon-expand">接口管理</span>
                        </button>
                    </div>
            <@com.security url="/inter/model/wirteCode">
                <div class="am-btn-group am-btn-group-xs">
                    <button type="button" class="am-btn am-btn-primary" onclick="writeCode()">
                        <span class="am-icon-print">生成代码</span>
                    </button>
                </div>
            </@com.security>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-9">
                <form method="post" action="${request.contextPath}/inter/model/${applicationId}/page">
                    <div class="am-u-sm-12 am-u-md-2">
                        <input type="text" name="name" value="${name}" class="am-form-field" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-2">
                        <input type="text" name="url" value="${url}" class="am-form-field" placeholder="访问地址 / Url">
                    </div>
                    <div class="am-u-sm-12 am-u-md-2">
                        <select name="status" data-am-selected="{btnSize: 'sm'}" class="am-form-field">
                            <option value="0"<#if status==0> selected=selected</#if>>草稿</option>
                            <option value="1"<#if status==1> selected=selected</#if>>已提交</option>
                            <option value="2"<#if status==2> selected=selected</#if>>审核退回</option>
                            <option value="3"<#if status==3> selected=selected</#if>>已发布</option>
                        </select>
                    </div>
                    <div class="am-u-sm-12 am-u-md-1">
                        <label class="am-radio">
                            <input type="radio" name="mine" value="1" data-am-ucheck<#if mine==1> checked</#if>> 我的
                        </label>
                    </div>
                    <div class="am-u-sm-12 am-u-md-1">
                        <label class="am-radio">
                            <input type="radio" name="mine" value="0" data-am-ucheck<#if mine==0> checked</#if>> 全部
                        </label>
                    </div>
                    <div class="am-u-sm-12 am-u-md-2">
                        <button class="am-btn am-btn-default" type="submit">搜索</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <form class="am-form">
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-main">ID</th>
                            <th class="table-main">名称</th>
                            <th class="table-main">模型代码</th>
                            <th class="table-main">状态</th>
                            <th class="table-main">备注</th>
                            <th class="table-main">创建时间</th>
                            <th class="table-main">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="table-main">${obj.id}</td>
                            <td class="table-main"><a href="javascript:toDetails(${obj.id})"><span class="am-icon-code"></span>${obj.name}</a></td>
                            <td class="table-main">${obj.code}</td>
                            <td class="table-main"><#switch obj.status><#case 0>草稿<#break><#case 1>已提交<#break><#case 2>审核退回<#break><#case 3>已发布<#break></#switch></td>
                            <td class="table-main">${obj.remark}</td>
                            <td class="table-main">${obj.createdTime}</td>
                            <td class="table-main">
                                <div class="am-btn-toolbar am-btn-group am-btn-group-xs">
                                    <#if obj.status == 0 || obj.status == 2>
                                        <a href="javascript:parameterList(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-code-fork">模型属性</span>
                                        </a>
                                        <a href="javascript:toModelInter(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-code">默认接口</span>
                                        </a>
                                        <#--<a href="javascript:doSubmit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">提交</span>
                                        </a>-->
                                    <#elseif obj.status == 1>
                                        <@com.security url="/inter/model/${applicationId}/publish">
                                        <a href="javascript:doPublish(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">发布</span>
                                        </a>
                                        </@com.security>
                                        <@com.security url="/inter/model/${applicationId}/return">
                                        <a href="javascript:doReturn(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">重议</span>
                                        </a>
                                        </@com.security>
                                    </#if>
                                    <#if obj.status == 0>
                                        <@com.security url="/inter/model/${applicationId}/edit">
                                        <a href="javascript:toEdit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
                                        </@com.security>
                                        <@com.security url="/inter/model/${applicationId}/delete">
                                        <a href="javascript:toDelete(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                            <span class="am-icon-trash-o">删除</span>
                                        </a>
                                        </@com.security>
                                    </#if>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                    <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/inter/model/${applicationId}/page" page=page />
                    </div>
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
