<#import "/inter/common.ftl" as i_com/>
<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function toDetails(id) {
        window.open("${request.contextPath}/inter/url/${applicationId}/details?id=" + id, "_blank");
    }
    function toAdd() {
        window.location.href = '${request.contextPath}/inter/url/${applicationId}/toAdd?groupId=${groupId}';
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/inter/url/${applicationId}/toEdit?id=" + id;
    }
    function uploadList(id) {
        window.open("${request.contextPath}/inter/upload/"+id+"/page");
    }
    function resultList(id) {
        window.open("${request.contextPath}/inter/result/"+id+"/list");
    }
    function toModel() {
        window.location.href = "${request.contextPath}/inter/model/${applicationId}/page";
    }
    function doSubmit(id) {
        myConfirm(" do Submit ?", "", function () {
            window.location.href = "${request.contextPath}/inter/url/${applicationId}/submit?id=" + id;
        });
    }
    function doPublish(id) {
        myConfirm(" do Publish ?", "", function () {
            window.location.href = "${request.contextPath}/inter/url/${applicationId}/publish?id=" + id;
        });
    }
    function doReturn(id) {
        myConfirm(" do Retrun ?", "", function () {
            window.location.href = "${request.contextPath}/inter/url/${applicationId}/return?id=" + id;
        });
    }
    function toDelete(id) {
        myConfirm(" delete ?", "删除后无法恢复", function () {
            window.location.href = "${request.contextPath}/inter/url/${applicationId}/delete?id=" + id;
        });
    }
    function writeCode() {
        $.post("${request.contextPath}/inter/url/${applicationId}/writeCode", function (result) {
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
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">接口</strong> /
                <small>列表</small>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toAdd()">
                            <span class="am-icon-plus"> 新增</span>
                        </button>
                    </div>
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toModel()">
                            <span class="am-icon-expand">数据模型管理</span>
                        </button>
                    </div>
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="writeCode()">
                            <span class="am-icon-print">生成代码</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-9">
                <form method="post" action="${request.contextPath}/inter/url/${applicationId}/page">
                    <input type="hidden" name="groupId" value="${groupId}">

                    <div class="am-u-sm-12 am-u-md-2">
                        <input type="text" name="name" value="${name}" class="am-form-field" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-2">
                        <input type="text" name="url" value="${url}" class="am-form-field" placeholder="接口方法 / 编码">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
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
                            <th class="am-hide-sm-up">ID</th>
                            <th class="table-main">分组</th>
                            <th class="table-main">名称</th>
                            <th class="table-main">方法</th>
                            <th class="table-main">类型</th>
                            <th class="table-main">上传类型</th>
                            <th class="table-main">结果类型</th>
                            <th class="table-main">处理逻辑</th>
                            <th class="table-main">状态</th>
                            <th class="am-hide-sm-up">备注</th>
                            <th class="am-hide-sm-up">创建时间</th>
                            <th class="table-main">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="am-hide-sm-up">${obj.id}</td>
                            <td class="table-main"><@i_com.groupName groupId=obj.groupId/></td>
                            <td class="table-main"><a href="javascript:toDetails(${obj.id})">${obj.name}</a></td>
                            <td class="table-main">${obj.url}</td>
                            <td class="table-main"><#if obj.type == 0>GET<#elseif obj.type == 1>POST</#if></td>
                            <td class="table-main"><#switch obj.uploadType><#case 1>Json<#break><#case 2>Json
                                Array<#break><#case 3>java.lang<#break><#case 4>java.lang Array<#break></#switch></td>
                            <td class="table-main"><#switch obj.resultType><#case 1>Json<#break><#case 2>Json
                                Array<#break><#case 3>java.lang<#break><#case 4>java.lang Array<#break></#switch></td>
                            <td class="table-main">${obj.logic}</td>
                            <td class="table-main"><#switch obj.status><#case 0>草稿<#break><#case 1>已提交<#break><#case 2>
                                审核退回<#break><#case 3>已发布<#break></#switch></td>
                            <td class="am-hide-sm-up">${obj.remark}</td>
                            <td class="am-hide-sm-up">${obj.createdTime}</td>
                            <td class="table-main">
                                <div class="am-btn-toolbar am-btn-group am-btn-group-xs">
                                    <#if obj.status == 0 || obj.status == 2>
                                        <a href="javascript:uploadList(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">请求参数</span>
                                        </a>
                                        <a href="javascript:resultList(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">返回参数</span>
                                        </a>
                                        <#--<a href="javascript:doSubmit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">提交</span>
                                        </a>-->
                                    <#elseif obj.status == 1>
                                        <a href="javascript:doPublish(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">发布</span>
                                        </a>
                                        <a href="javascript:doReturn(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">重议</span>
                                        </a>
                                    </#if>
                                    <#if obj.status == 0>
                                        <a href="javascript:toEdit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
                                        <#--<@com.security url="/inter/url/${applicationId}/delete">
                                            <a href="javascript:toDelete(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                                <span class="am-icon-trash-o">删除</span>
                                            </a>
                                        </@com.security>-->
                                    </#if>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                    <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/inter/url/${applicationId}/page" page=page />
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
