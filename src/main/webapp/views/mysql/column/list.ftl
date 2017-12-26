<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function toAdd() {
        window.location.href = '${request.contextPath}/mysql/column/${tableId}/toAdd';
    }
    function toDelete(id) {
        if (confirm(" delete ?")) {
            window.location.href = "${request.contextPath}/mysql/column/${tableId}/delete?id=" + id;
        }
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/mysql/column/${tableId}/toEdit?id=" + id;
    }
    function toTableImport() {
        myConfirm("提示", "确认导入${table.code}表数据列？", function () {
            window.location.href = "${request.contextPath}/mysql/column/${tableId}/importTable";
        });
    }
    function toClean() {
        myConfirm("提示", "确认清空数据列？", function () {
            window.location.href = "${request.contextPath}/mysql/column/${tableId}/clean";
        });
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">${table.name}(${table.code})表
                数据列</strong> /
                <small>列表</small>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-4">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="toAdd()">
                            <span class="am-icon-plus"> 新增</span>
                        </button>
                        <button type="button" class="am-btn am-btn-success" onclick="toTableImport()">
                            <span class="am-icon-download">从数据库导入</span>
                        </button>
                        <button type="button" class="am-btn am-btn-danger" onclick="toClean()">
                            <span class="am-icon-close">清空</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8">
                <form method="post" action="${request.contextPath}/mysql/column/${tableId}/page">
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="name" value="${name}" class="am-form-field" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="code" value="${code}" class="am-form-field" placeholder="代码 / Code">
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
                <form class="am-form">
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-id">ID</th>
                            <td class="table-main">位置</td>
                            <th class="table-title">名称</th>
                            <th class="table-main">编码</th>
                            <th class="table-main">类型</th>
                            <th class="table-main">可以为空</th>
                            <th class="table-main">默认值</th>
                            <th class="table-main">备注</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="table-id">${obj.id}</td>
                            <td class="table-main">${obj.orderPosition}</td>
                            <td class="table-title"><a href="#">${obj.name}</a></td>
                            <td class="table-main">${obj.code}</td>
                            <td class="table-main">${obj.type}<#if obj.autoIncrement>自增</#if></td>
                            <td class="table-main">${obj.allowBlank}</td>
                            <td class="table-main">${obj.defaultValue}</td>
                            <td class="table-main">${obj.comment}</td>
                            <td class="table-date">${obj.createdTime}</td>
                            <td class="table-set">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toEdit(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
                                        <a href="javascript:toDelete(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                            <span class="am-icon-trash-o">删除</span>
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
                    <@pager.guid pageUrl="/mysql/column/${tableId}/page" page=page />
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
