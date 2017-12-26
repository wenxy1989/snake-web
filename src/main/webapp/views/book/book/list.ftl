<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">电子书籍</strong> /
                <small>列表</small>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="toAdd()">
                            <span class="am-icon-plus">新增</span>
                        </button>
                        <button type="button" class="am-btn am-btn-default" onclick="toMark()">
                            <span class="am-icon-map-marker">我的书签</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-form-group">
                    <select data-am-selected="{btnSize: 'sm'}">
                        <option value="option1">名称</option>
                        <option value="option2">类型</option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-3">
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
                            <th class="table-main">名称</th>
                            <th class="table-main">作者</th>
                            <th class="table-main">简介</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td><a href="${request.contentPath}/book/paragraph/page?bookId=${obj.id}">${obj.name}</a>
                            </td>
                            <td class="table-main">${obj.author}</td>
                            <td class="table-main"><#if obj.introduction?? && obj.introduction?length gt 30>${obj.introduction[0..25]}<#else>${obj.introduction}</#if></td>
                            <td class="table-main">${obj.createdTime}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toEdit(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
                                        <a href="javascript:toDelete(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">删除</span>
                                        </a>
                                        <a href="javascript:parseBook(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">拆分词组</span>
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                <#import "/common/pager.ftl" as pager>
                <@pager.guid pageUrl="/book/book/page" page=page />
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
<script type="text/javascript">
    function toDelete(id) {
        myConfirm("提示", "确认删除这本书？", function () {
            window.location.href = "${request.contextPath}/book/book/delete?id=" + id;
        });
    }
    function parseBook(id) {
        myConfirm("提示", "确认重新拆分词组？", function () {
            $.post("${request.contextPath}/book/book/parseWord", {id: id}, function (result) {
                myAlert("拆分词组" + result);
            });
        });
    }
    function toAdd() {
        window.location.href = "${request.contextPath}/book/book/toAdd";
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/book/book/toEdit?id="+id;
    }
    function toParagraph(id) {
        window.location.href = "${request.contextPath}/book/paragraph/page?bookId=" + id;
    }
    function toMark() {
        window.location.href = "${request.contextPath}/book/mark/page";
    }
</script>
</body>
</html>
