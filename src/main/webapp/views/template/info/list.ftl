<html>
<#import "/template/common.ftl" as t_com/>
<#include "/head.ftl"/>
<script type="text/javascript">
    function toAdd() {
        window.location.href = '${request.contextPath}/template/info/toAdd';
    }
    function toFrame() {
        window.location.href = '${request.contextPath}/template/frame/page';
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/template/info/toEdit?id=" + id;
    }
    function toDelete(id) {
        myConfirm("确认移除?", "", function () {
            window.location.href = "${request.contextPath}/template/info/delete?id=" + id;
        });
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong class="am-text-primary am-text-lg">模板信息</strong>/
                <small>列表</small>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-4">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toAdd()">
                            <span class="am-icon-plus">新增</span>
                        </button>
                        <button type="button" class="am-btn am-btn-primary" onclick="toFrame()">
                            <span class="am-icon-magnet">分组管理</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8">
                <form method="post" action="${request.contextPath}/template/frame/page">
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="name" value="${name}" class="am-form-field" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="group" value="${group}" class="am-form-field" placeholder="分组 / Group">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="type" value="${type}" class="am-form-field" placeholder="类型 / Type">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <button class="am-btn am-btn-default" type="submit">搜索</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
            <#if page?? && page.content?size gt 0>
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-id">ID</th>
                        <th class="table-main">模板位置</th>
                        <th class="table-main">类型</th>
                        <th class="table-main">更新类型</th>
                        <th class="table-main">保存路径</th>
                        <th class="table-main">备注</th>
                        <th class="table-main">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="table-id">${obj.id}</td>
                            <td class="table-main">classpath:template/${obj.group}/${obj.name}.ftl</td>
                            <td class="table-main">${obj.type}</td>
                            <td class="table-main"><#if obj.updateType==0>每次更新<#else>没有时创建</#if></td>
                            <td class="table-main">classpath:<@t_com.modelReplace template="${obj.savePathModel}" />/<@t_com.modelReplace template="${obj.saveFileModel}" /></td>
                            <td class="table-main">${obj.remark}</td>
                            <td class="table-main">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toEdit(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-edit">修改</span>
                                        </a>
                                        <a href="javascript:toDelete(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-danger">
                                            <span class="am-icon-trash-o">移除</span>
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>

                <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/template/frame/page" page=page />
                </div>
            </#if>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
