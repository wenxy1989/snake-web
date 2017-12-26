<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function doQuery(){
        if(notBlank($("input[name='params']").val())){
            $("#form-query").submit();
        }
    }
    function selectOption(id) {
        window.parent.selectParameter(id);
    }
</script>
<body>
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">

        <div class="am-g">
            <form id="form-query" method="post" action="${request.contextPath}/inter/parameter/select">
                <div class="am-u-sm-12 am-u-md-2">
                    <input type="text" name="params" value="${params}" class="am-form-field" placeholder="名称/代码/类型" onchange="doQuery()">
                </div>
            </form>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <form class="am-form">
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="am-hide-sm-only">ID</th>
                            <th class="am-hide-sm-only">名称</th>
                            <th class="table-type">代码</th>
                            <th class="table-author">类型</th>
                            <th class="table-author">长度</th>
                            <th class="table-author">规则</th>
                            <th class="am-hide-sm-only">备注</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="am-hide-sm-only">${obj.id}</td>
                            <td class="am-hide-sm-only"><a href="#">${obj.name}</a></td>
                            <td>${obj.code}</td>
                            <td>${obj.type}</td>
                            <td>${obj.length}</td>
                            <td>${obj.regex}</td>
                            <td class="am-hide-sm-only">${obj.remark}</td>
                            <td class="am-hide-sm-only">${obj.createdTime}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:selectOption(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">选择</span>
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
                    <@pager.guid pageUrl="/inter/parameter/page" page=page type="select" />
                    </div>
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
</body>
</html>