<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">书签</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-3 am-fr">
                <div class="am-input-group am-input-group-sm">
                    <input type="text" class="am-form-field">
                    <span class="am-input-group-btn">
                      <button class="am-btn am-btn-default" type="button">搜索</button>
                    </span>
                </div>
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
                            <th class="table-date">记录时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td class="am-hide-sm-only">
                                <a href="${request.contextPath}${obj.url}">${obj.name}</a>
                            </td>
                            <td class="am-hide-sm-only">${obj.createdTime}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="${request.contextPath}/book/mark/toEdit?id=${obj.id}" class="am-btn am-btn-default am-btn-xs am-text-secondary">
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
                <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/book/mark/page" page=page />
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
<script type="text/javascript">
    function toDelete(id){
        if(confirm(" delete ?")){
            window.location.href = "${request.contextPath}/book/mark/delete?id="+id;
        }
    }
    function toEdit(id){
        window.location.href = "${request.contextPath}/book/mark/toEdit?id="+id;
    }
</script>
</body>
</html>
