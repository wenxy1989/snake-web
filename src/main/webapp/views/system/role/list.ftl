<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function toAdd(){
        window.location.href='${request.contextPath}/role/toAdd';
    }
    function toEdit(id){
        window.location.href='${request.contextPath}/role/toEdit?id='+id;
    }
    function toDelete(id){
        if(confirm("delete it ?")){
            window.location.href='${request.contextPath}/role/delete?id='+id;
        }
    }
    function toFunctionManager(id){
        <#--window.location.href='${request.contextPath}/role/permissionsManager?rolId='+id;-->
        window.location.href='${request.contextPath}/role/functionAuthorize?id='+id;
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">角色</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="toAdd()">
                            <span class="am-icon-plus"> 新增</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-6">
                <form method="post" action="${request.contextPath}/role/page">
                    <div class="am-u-sm-12 am-u-md-5">
                        <input type="text" name="name" value="${name}" class="am-form-field" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-5">
                        <input type="text" name="code" value="${code}" class="am-form-field" placeholder="代码 / Code">
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
                            <th class="table-id">ID</th>
                            <th class="table-title">名称</th>
                            <th class="table-type">代码</th>
                            <th class="table-author">状态</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td><a href="#">${obj.name}</a></td>
                            <td>${obj.code}</td>
                            <td>${obj.status}</td>
                            <td>${obj.createdTime}</td>
                            <td>
                                <#if obj.id == 1>
                                <div class="am-btn-toolbar">
                                    <span class="am-icon-shield">管理员角色</span>
                                </div>
                                <#else>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toEdit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
                                        <a href="javascript:toDelete(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-danger">
                                            <span class="am-icon-trash-o">删除</span>
                                        </a>
                                        <a href="javascript:toFunctionManager(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-primary">
                                            <span class="am-icon-cog">权限管理</span>
                                        </a>
                                    </div>
                                </div>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                    <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/role/page" page=page />
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
