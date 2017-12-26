<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function toDetails(){
        window.location.href='${request.contextPath}/mysql/database/details';
    }
    function toAdd(){
        window.location.href='${request.contextPath}/mysql/database/toAdd';
    }
    function toDelete(id){
        if(confirm(" delete ?")){
            window.location.href = "${request.contextPath}/mysql/database/delete?id="+id;
        }
    }
    function toEdit(id){
        window.location.href = "${request.contextPath}/mysql/database/toEdit?id="+id;
    }
    function toTable(id){
        window.location.href='${request.contextPath}/mysql/table/'+id+'/page';
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">MYSQL数据库</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-4">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="toAdd()">
                            <span class="am-icon-plus"> 新增</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8">
                <form method="post" action="${request.contextPath}/mysql/database/page">
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="name" value="${name}" class="am-form-field" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="code" value="${code}" class="am-form-field" placeholder="代码 / Code">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="type" value="${type}" class="am-form-field" placeholder="类型 / Type">
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
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
                            <th class="table-title">名称</th>
                            <th class="table-mian">编码</th>
                            <th class="table-mian">地址</th>
                            <th class="table-mian">端口</th>
                            <th class="table-mian">用户名</th>
                            <th class="table-mian">密码</th>
                            <th class="table-mian">备注</th>
                            <th class="table-date">创建时间</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td>${obj.id}</td>
                            <td class="table-mian"><a href="void(0)" onclick="toDetails()">${obj.name}</a></td>
                            <td class="table-mian">${obj.code}</td>
                            <td class="table-mian">${obj.address}</td>
                            <td class="table-mian">${obj.port}</td>
                            <td class="table-mian">${obj.username}</td>
                            <td class="table-mian">${obj.password}</td>
                            <td class="table-mian">${obj.remark}</td>
                            <td class="table-date">${obj.createdTime}</td>
                            <td class="table-set">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toTable(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-code-fork">表结构</span>
                                        </a>
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
                    <@pager.guid pageUrl="/mysql/database/page" page=page />
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
