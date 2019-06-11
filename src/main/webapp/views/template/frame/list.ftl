<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    $(document).ready(function(){
        var ope_result = "${ope_result}";
        if(notBlank(ope_result) && ope_result.indexOf(",") > 0){
            var operates = ope_result.split(",");
            var result = operates[0];
            var timeMils = operates[1];
            var nowMils = new Date().getTime();
            if(Math.abs(timeMils - nowMils) < 1000){
                myAlert(result);
            }
        }
    });
    function toAdd(){
        window.location.href = '${request.contextPath}/template/frame/toAdd';
    }
    function toEdit(id){
        window.location.href = "${request.contextPath}/template/frame/toEdit?id="+id;
    }
    function toDelete(id){
        myConfirm(" delete ?","删除后无法恢复",function(){
            window.location.href = "${request.contextPath}/template/frame/delete?id="+id;
        });
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">模板分组</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
        <@com.security url="/template/frame/add">
            <div class="am-u-sm-12 am-u-md-1">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toAdd()">
                            <span class="am-icon-plus"> 新增</span>
                        </button>
                    </div>
                </div>
            </div>
        </@com.security>
            <div class="am-u-sm-12 am-u-md-11">
                <form method="post" action="${request.contextPath}/template/frame/page">
                    <div class="am-u-sm-12 am-u-md-3">
                        <input type="text" name="name" value="${name}" class="am-form-field" placeholder="名称 / Name">
                    </div>
                    <div class="am-u-sm-12 am-u-md-2">
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
                            <th class="table-main">ID</th>
                            <th class="table-main">名称</th>
                            <th class="table-main">备注</th>
                            <th class="table-main">创建时间</th>
                            <th class="table-main">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="table-main">${obj.id}</td>
                            <td class="table-main"><a href="javascript:void(0)" onclick="toDetails(${obj.id})" >${obj.name}</a></td>
                            <td class="table-main">${obj.remark}</td>
                            <td class="table-main">${obj.createdTime}</td>
                            <td class="table-main">
                                <div class="am-btn-toolbar am-btn-group am-btn-group-xs">
                                        <@com.security url="/template/frame/edit">
                                        <a href="javascript:toEdit(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">编辑</span>
                                        </a>
                                        </@com.security>
                                        <@com.security url="/template/frame/delete">
                                        <a href="javascript:toDelete(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                            <span class="am-icon-trash-o">删除</span>
                                        </a>
                                        </@com.security>
                                </div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                    <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/template/frame/page" page=page />
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
