<html xmlns="http://www.w3.org/1999/html">
<#include "/head.ftl"/>
<body>
<script type="text/javascript">
    function toInit(){
        window.location.href='${request.contextPath}/video/initDir';
    }
</script>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">视频</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="toInit()">
                            <span class="am-icon-refresh">初始化目录</span>
                        </button>
                    </div>
                </div>
            </div>
            <form action="${request.contextPath}/video/page" method="post">
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-form-group">
                    <select name="ext" data-am-selected="{btnSize: 'sm'}">
                        <option value=""></option>
                        <option value=".flv">flv</option>
                        <option value=".mp4">mp4</option>
                        <option value=".rmvb">rmvb</option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-input-group am-input-group-sm">
                    <input name="name" type="text" class="am-form-field">
                    <span class="am-input-group-btn">
                        <button class="am-btn am-btn-default" type="submit">搜索</button>
                    </span>
                </div>
            </div>
            </form>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <form class="am-form">
                <#if page?? && page.totalElements gt 0>
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-id">ID</th>
                            <th class="table-title">名称</th>
                            <th class="table-author">路径</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <#if page?? && page.content>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td>
                                <a href="${request.contextPath}/video/play?id=${obj.id}" target="_blank">${obj.name}</a>
                            </td>
                            <td>${obj.filePath}<br>${request.contextPath}/videoServlet?videoId=${obj.id}</td>
                            <td class="am-hide-sm-only">${obj.createdTime}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="${request.contextPath}/video/sewise?id=${obj.id}" target="_blank">
                                            sewise
                                        </a>
                                        <a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="${request.contextPath}/video/toEdit?id=${obj.id}">
                                            <span class="am-icon-pencil-square-o"></span>
                                            编辑
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                        </#if>
                    </table>
                    <div class="am-cf">
                        <#import "/common/pager.ftl" as pager>
                        <@pager.guid pageUrl="/image/page" page=page />
                    </div>
                    </#if>
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
