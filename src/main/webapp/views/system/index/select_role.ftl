<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function selectRole(roleId){
        window.location.href = "${request.contextPath}/sys/index/function/page?type=${type}&objectId="+roleId;
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">选择角色</strong> / <small>设置首页</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-id">ID</th>
                        <th class="table-title">名称</th>
                        <th class="table-type">代码</th>
                        <th class="table-author">备注</th>
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
                        <td class="am-hide-sm-only">${obj.remark}</td>
                        <td class="am-hide-sm-only">${obj.createdTime}</td>
                        <td>
                            <a href="javascript:selectRole(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                <span class="am-icon-paperclip"> 选择</span>
                            </a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            <#import "/common/pager.ftl" as pager>
            <@pager.guid pageUrl="/sys/index/role/page" page=page />
            </div>
        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
