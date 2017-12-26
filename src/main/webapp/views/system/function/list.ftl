<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">功能菜单</strong> /
                <small>列表</small>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="toAdd(${parentId})">
                            <span class="am-icon-plus">新增</span>
                        </button>
                    <#if parentId gt 0>
                        <button type="button" class="am-btn am-btn-default" onclick="history.go(-1)">
                            <span class="am-icon-reorder">返回</span>
                        </button>
                    </#if>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-6">
                <form method="post" action="${request.contextPath}/function/page" class="am-form-group">
                    <input type="hidden" name="parentId" value="${parentId}">
                    <div class="am-u-sm-12 am-u-md-3">
                        <input name="name" type="text" class="am-form-field" value="${name}" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <input name="url" type="text" class="am-form-field" value="${url}" placeholder="访问相对地址 / Url">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <select name="type" data-am-selected="{btnSize: 'sm'}">
                            <option value="0"<#if type==0> selected="selected"</#if>>全部</option>
                            <option value="1"<#if type==1> selected="selected"</#if>>菜单</option>
                            <option value="2"<#if type==2> selected="selected"</#if>>功能</option>
                        </select>
                    </div>
                    <div class="am-u-sm-12 am-u-md-2">
                    <span class="am-input-group-btn">
                        <button class="am-btn am-btn-default" type="submit">搜索</button>
                    </span>
                    </div>
                </form>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <#if function??>
                    <tr>
                        <th class="table-id">${function.id}</th>
                        <th class="table-id">${function.order}</th>
                        <th class="table-title">${function.name}</th>
                        <th class="table-main">${function.url}</th>
                        <th class="table-type"><#if function.type == 1>菜单<#elseif obj.type == 2>功能</#if></th>
                        <th class="table-date">${function.createdTime}</th>
                        <th class="table-set">--</th>
                    </tr>
                    </#if>
                    <tr>
                        <th class="table-id">ID</th>
                        <th class="table-id">ORDER</th>
                        <th class="table-title">名称</th>
                        <th class="table-main">地址</th>
                        <th class="table-type">类型</th>
                        <th class="table-date">创建时间</th>
                        <th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list page.content as obj>
                    <tr>
                        <td class="table-id">${obj.id}</td>
                        <td class="table-id">${obj.order}</td>
                        <td class="table-title"><a href="${request.contextPath}/function/page?parentId=${obj.id}">${obj.name}</a></td>
                        <td class="table-main">${obj.url}</td>
                        <td class="table-type"><#if obj.type == 1>菜单<#elseif obj.type == 2>功能</#if></td>
                        <td class="table-date">${obj.createdTime}</td>
                        <td class="table-set">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:toAdd(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                        <span class="am-icon-pencil-square-o">添加子菜单</span>
                                    </a>
                                    <a href="javascript:toEdit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                        <span class="am-icon-pencil-square-o">编辑</span>
                                    </a>
                                <#--<button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>-->
                                    <a href="javascript:toDelete(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                        <span class="am-icon-trash-o">删除</span>
                                    </a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            <#import "/common/pager.ftl" as pager>
            <@pager.guid pageUrl="/function/page" page=page />
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
<script type="text/javascript">
    function toAdd(parentId) {
        window.location.href = "${request.contextPath}/function/toAdd?parentId=" + parentId;
    }
    function toDelete(id) {
        if (confirm(" delete ?")) {
            window.location.href = "${request.contextPath}/function/delete?id=" + id;
        }
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/function/toEdit?id=" + id;
    }
</script>
</body>
</html>
