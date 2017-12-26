<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function selectUser(userId){
        window.location.href = "${request.contextPath}/sys/index/function/page?type=${type}&objectId="+userId;
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">选择用户设置首页</strong> / <small>列表</small></div>
            <div class="am-u-sm-12 am-u-md-3">
                <form method="post" action="${request.contextPath}/sys/index/user/page">
                <div class="am-input-group am-input-group-sm">
                    <input type="text" name="name" class="am-form-field" value="${name}">
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
                            <a href="javascript:selectUser(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                <span class="am-icon-paperclip">选择</span>
                            </a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            <#import "/common/pager.ftl" as pager>
            <@pager.guid pageUrl="/sys/index/user/page" page=page />
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
