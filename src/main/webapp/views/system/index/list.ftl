<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf am-u-md-3"><strong class="am-text-primary am-text-lg">首页设置</strong> / <small>列表</small></div>
            <div class="am-u-md-6">
                <form method="post" action="">
                <div class="am-input-group am-input-group-sm">
                    <input type="text" class="am-form-field">
                    <span class="am-input-group-btn">
                        <button class="am-btn am-btn-default" type="submit">搜索</button>
                        <a class="am-btn am-btn-default" href="javascript:toSetUser()">设置用户首页</a>
                        <a class="am-btn am-btn-default" href="javascript:toSetRole()">设置角色首页</a>
                    </span>
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
                            <th class="table-title">地址</th>
                            <th class="table-title">目标类型</th>
                            <th class="table-title">目标ID</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td>${obj.name}</td>
                            <td>${obj.url}</td>
                            <td><#switch obj.type><#case 1>角色<#break><#case 2>用户<#break></#switch></td>
                            <td>${obj.objectId}</td>
                            <td class="am-hide-sm-only">${obj.createdTime}</td>
                            <td>
                                <a href="javascript:toDelete(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                    <span class="am-icon-trash-o">删除</span>
                                </a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/sys/index/page" page=page />
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
<script type="text/javascript">
    function toSetUser(){
        window.location.href="${request.contextPath}/sys/index/user/page";
    }
    function toSetRole(){
        window.location.href="${request.contextPath}/sys/index/role/page";
    }
    function toDelete(id){
        if(confirm(" delete ?")){
            window.location.href = "${request.contextPath}/sys/index/delete?id="+id;
        }
    }
</script>
</body>
</html>
