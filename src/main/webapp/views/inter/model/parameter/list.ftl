<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function toAdd() {
        window.location.href = '${request.contextPath}/inter/model/parameter/toAdd?modelId=${modelId}';
    }
    function toSelect(id) {
        var title = "选择结果参数";
        var url = "${request.contextPath}/inter/parameter/select";
        openModal(title, url);
    }
    function selectParameter(parameterId) {
        $.post("${request.contextPath}/inter/model/parameter/addParameter",
                {
                    modelId:${model.id},
                    parameterId: parameterId
                }, function (result) {
                    if (result == 'success') {
                        myAlert("添加参数到模型成功！");
                        window.location.reload();
                    }
                });
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/inter/model/parameter/toEdit?id=" + id;
    }
    function toDelete(id) {
        myConfirm("确认移除?", "", function () {
            window.location.href = "${request.contextPath}/inter/model/parameter/delete?modelId=${modelId}&id=" + id;
        });
    }
    function writeCode() {
        $.post("${request.contextPath}/inter/model/writeCode", {id:${modelId}}, function (result) {
            myAlert(result);
        });
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">${model.name}模型(${model.code})</strong>/
                <small>属性列表</small>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-4">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toSelect()">
                            <span class="am-icon-plus">去选择现有参数</span>
                        </button>
                        <button type="button" class="am-btn am-btn-primary" onclick="toAdd()">
                            <span class="am-icon-plus">新增自定义参数</span>
                        </button>
                    <@com.security url="/inter/model/writeCode">
                        <button type="button" class="am-btn am-btn-primary" onclick="writeCode()">
                            <span class="am-icon-download">生成代码</span>
                        </button>
                    </@com.security>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8">
                <form method="post" action="${request.contextPath}/inter/model/parameter/page">
                    <input type="hidden" name="modelId" value="${modelId}">
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
            <#if page?? && page.content?size gt 0>
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-id">ID</th>
                        <th class="table-title">名称</th>
                        <th class="table-main">代码</th>
                        <th class="table-main">数据关系</th>
                        <th class="table-main">类型</th>
                        <th class="table-main">集合</th>
                        <th class="table-main">长度</th>
                        <th class="table-main">示例</th>
                        <th class="table-main">允许为空</th>
                        <th class="table-main">备注</th>
                        <th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="table-id">${obj.id}</td>
                            <td class="table-title">${obj.name}</td>
                            <td class="table-main">${obj.code}</td>
                            <td class="table-main"><#if obj.keyType==0>无<#elseif obj.keyType==1>唯一<#elseif obj.keyType==2>依赖<#elseif obj.keyType==3>域标示<#elseif obj.keyType==4>标示</#if></td>
                            <td class="table-main">${obj.type}</td>
                            <td class="table-main"><#if obj.isArray>是<#else>否</#if></td>
                            <td class="table-main">${obj.length}</td>
                            <td class="table-main"><#if obj.example?length gt 30>${obj.example[0..25]}...<#else>${obj.example}</#if></td>
                            <td class="table-main"><#if obj.allowBlank>是<#else>否</#if></td>
                            <td class="table-main">${obj.remark}</td>
                            <td class="table-set">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toEdit(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-edit">修改</span>
                                        </a>
                                        <a href="javascript:toDelete(${obj.id})"
                                           class="am-btn am-btn-default am-btn-xs am-text-danger">
                                            <span class="am-icon-trash-o">移除</span>
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            <#else>
                没有选择的结果参数,去<a href="#" onclick="javascript:toSelect()">添加</a>
            </#if>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
