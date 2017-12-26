<html>
<#include "/head.ftl"/>
<body>
<script type="text/javascript">
    function toAdd(){
        window.location.href='${request.contextPath}/user/toAdd';
    }
    function toEdit(id){
        window.location.href='${request.contextPath}/user/toEdit?id='+id;
    }
    function toDelete(id){
        if(confirm("delete it ?")){
            window.location.href='${request.contextPath}/user/delete?id='+id;
        }
    }
</script>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户</strong> / <small>列表</small></div>
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
                <form method="post" action="${request.contextPath}/user/page">
                <div class="am-u-sm-12 am-u-md-5">
                    <input type="text" name="userName" value="${userName}" class="am-form-field" placeholder="姓名 / User Name">
                </div>
                <div class="am-u-sm-12 am-u-md-5">
                    <input type="text" name="loginName" value="${loginName}" class="am-form-field" placeholder="登录名 / Login Name">
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
                            <th class="table-title">姓名</th>
                            <th class="table-type">登录名</th>
                            <th class="table-author">角色ID</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td><a href="#">${obj.userName}</a></td>
                            <td>${obj.loginName}</td>
                            <td class="am-hide-sm-only">${obj.roleId}</td>
                            <td class="am-hide-sm-only">${obj.createdTime}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toEdit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
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
                    <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                        <@pager.guid pageUrl="/user/page" page=page />
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
