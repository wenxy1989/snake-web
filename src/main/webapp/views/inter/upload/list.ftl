<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function reloadWindow(){
        window.location.reload();
    }
    function toAdd() {
        window.location.href = '${request.contextPath}/inter/upload/${urlId}/toAdd?urlId=${urlId}';
    }
    function toSelect(id) {
        var title = "选择上传参数";
        var url = "${request.contextPath}/inter/parameter/select";
        openModal(title, url,reloadWindow,reloadWindow);
    }
    function selectParameter(parameterId){
        $.post("${request.contextPath}/inter/upload/${urlId}/addParameter",{urlId:${url.id},parameterId:parameterId},function(result){
            if(result == 'success'){
                myAlert("添加上传参数到接口成功！");
                window.location.reload()
            }
        });
    }
    function toImportModelParameter(){
        var title = "选择模型";
        var url = "${request.contextPath}/inter/model/select";
        openModal(title, url);
    }
    function selectModel(modelId){
        $.post("${request.contextPath}/inter/upload/${urlId}/importModelParameters",{urlId:${url.id},modelId:modelId},function(result){
            if(result == 'success'){
                myAlert("导入模型属性到接口成功！");
                window.location.reload()
            }
        });
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/inter/upload/${urlId}/toEdit?urlId=${urlId}&id=" + id;
    }
    function toDelete(id) {
        if(confirm("确认移除?")){
            window.location.href = "${request.contextPath}/inter/upload/${urlId}/delete?urlId=${urlId}&id=" + id;
        }
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">${url.name}</strong>/<small>接口上传参数</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-4">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-primary" onclick="toSelect()">
                            <span class="am-icon-plus">去选择现有参数</span>
                        </button>
                        <button type="button" class="am-btn am-btn-primary" onclick="toImportModelParameter()">
                            <span class="am-icon-plus">导入模型属性</span>
                        </button>
                        <button type="button" class="am-btn am-btn-primary" onclick="toAdd()">
                            <span class="am-icon-plus">新增自定义参数</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8">
                <form method="post" action="${request.contextPath}/inter/upload/${urlId}/page">
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
                        <th class="am-hide-sm-only">名称</th>
                        <th class="table-main">JAVA代码</th>
                        <th class="table-main">JAVA类型</th>
                        <th class="table-main">集合</th>
                        <th class="table-main">长度</th>
                        <th class="table-main">规则</th>
                        <th class="table-main">示例</th>
                        <th class="table-main">允许为空</th>
                        <th class="table-main">备注</th>
                        <th class="table-main">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list page.content as obj>
                        <tr>
                            <td class="table-id">${obj.id}</td>
                            <td class="am-hide-sm-only">${obj.name}</td>
                            <td class="table-main">${obj.code}</td>
                            <td class="table-main">${obj.type}</td>
                            <td class="table-main"><#if obj.isArray>是<#else>否</#if></td>
                            <td class="table-main">${obj.length}</td>
                            <td class="table-main">${obj.regex}</td>
                            <td class="table-main"><#if obj.example?length gt 30>${obj.example[0..25]}...<#else>${obj.example}</#if></td>
                            <td class="table-main"><#if obj.allowBlank>是<#else>否</#if></td>
                            <td class="table-main">${obj.remark}</td>
                            <td class="table-main">
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

                <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                    <@pager.guid pageUrl="/inter/upload/${urlId}/page" page=page />
                </div>
            <#else>
                没有选择的上传参数,去<a href="#" onclick="javascript:toSelect()">添加</a>
            </#if>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
