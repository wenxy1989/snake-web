<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function selectOption(id) {
        window.parent.selectModel(id);
    }
</script>
<body>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-g">
            <form id="form-query" method="post" action="${request.contextPath}/inter/model/${applicationId}/select">
                <div class="am-u-sm-12 am-u-md-6">
                    <input type="text" name="params" value="${params}" class="am-form-field" placeholder="名称/代码">
                </div>
                <div class="am-u-sm-12 am-u-md-6">
                    <button class="am-btn am-btn-default" type="submit">搜索</button>
                </div>
            </form>
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
                            <th class="table-main">备注</th>
                            <th class="table-main">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="table-main">${obj.id}</td>
                            <td class="table-main">${obj.name}</td>
                            <td class="table-main">${obj.code}</td>
                            <td class="table-main"><#if obj.remark?length gt 20>${obj.remark[0..15]}<#else>${obj.remark}</#if></td>
                            <td class="table-main">
                                <div class="am-btn-toolbar am-btn-group am-btn-group-xs">
                                    <a href="javascript:selectOption(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                        <span class="am-icon-pencil-square-o">选择</span>
                                    </a>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                    <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/inter/model/${applicationId}/select" page=page type="select" />
                    </div>
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
</body>
</html>
