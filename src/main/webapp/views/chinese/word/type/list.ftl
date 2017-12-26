<html>
<#include "/head.ftl"/>
<body>
<script type="text/javascript">
    function toAdd() {
        window.location.href = "${request.contextPath}/book/word/type/toAdd";
    }
    function toDelete(id) {
        if (confirm(" delete ?")) {
            window.location.href = "${request.contextPath}/book/word/type/delete?id=" + id;
        }
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/book/word/type/toEdit?id=" + id;
    }
</script>
<#include "/header.ftl"/>
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">词组类型</strong> /
                <small>列表</small>
            </div>
        </div>

        <div class="am-g">
            <form method="POST" action="${request.contextPath}/book/word/type/page">
                <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <button type="button" class="am-btn am-btn-default" onclick="toAdd()">
                                <span class="am-icon-plus">新增</span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3 am-hide-sm-only">
                    <div class="am-form-group">
                        <select data-am-selected="{btnSize: 'sm'}">
                            <option value="option1">名称</option>
                            <option value="option2">类型</option>
                        </select>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3">
                    <div class="am-input-group am-input-group-sm">
                        <input type="text" name="name" class="am-form-field" value="${name}">
                          <span class="am-input-group-btn">
                            <button class="am-btn am-btn-default" type="submit">搜索</button>
                          </span>
                    </div>
                </div>
            </form>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-id">ID</th>
                            <th class="table-title">名称</th>
                            <th class="table-check am-hide-sm-only">类型</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td>${obj.name}</td>
                            <td class="am-hide-sm-only">${obj.type}</td>
                            <td>${obj.createdTime}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="${request.contextPath}/book/word/type/toEdit?id=${obj.id}"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
            <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/book/word/type/page" page=page />
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
