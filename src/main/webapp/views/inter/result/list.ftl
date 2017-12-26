<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function toAdd() {
        window.location.href = '${request.contextPath}/inter/result/${urlId}/toAdd?urlId=${urlId}';
    }
    function toSelect(id) {
        var title = "选择结果参数";
        var url = "${request.contextPath}/inter/parameter/select";
        openModal(title, url);
    }
    function selectParameter(parameterId){
        $.post("${request.contextPath}/inter/result/${urlId}/addParameter",{urlId:${url.id},parameterId:parameterId},function(result){
            if(result == 'success'){
                myAlert("添加结果参数到接口成功！");
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
        $.post("${request.contextPath}/inter/result/${urlId}/importModelParameters",{urlId:${url.id},modelId:modelId},function(result){
            if(result == 'success'){
                myAlert("导入模型属性到接口成功！");
                window.location.reload()
            }
        });
    }
    function toEdit(id) {
        window.location.href = "${request.contextPath}/inter/result/${urlId}/toEdit?urlId=${urlId}&id=" + id;
    }
    function toDelete(id) {
        if(confirm("确认移除?")){
            window.location.href = "${request.contextPath}/inter/result/${urlId}/delete?urlId=${urlId}&id=" + id;
        }
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">${url.name}</strong>/<small>接口结果参数</small></div>
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
        </div>
    <#macro objectExample array=[] >{<#list array as each>\"${each.code}\":<#if each.type == "String">\"${each.example}\"<#else>${each.example}</#if><#if each_has_next>,</#if></#list>}</#macro>
    <#macro arrayExample array=[]><@objectExample array=array/>,<@objectExample array=array/></#macro>

        <div class="am-g">
            <div>
                <pre>
                <#if list?? && list?size gt 0>
                    <span>示例：</span>"{<#list list as each>\"${each.code}\":<#if each.type == "String">\"${each.example}\"<#elseif each.type == "Object"><@objectExample array=parameters /><#elseif each.type == "Array"><@arrayExample array=parameters/><#else>${each.example}</#if><#if each_has_next>,</#if></#list>}"
                </#if>
                </pre>
            </div>
            <div class="am-u-sm-12">
            <#if list?? && list?size gt 0>
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-id">ID</th>
                        <th class="table-title">名称</th>
                        <th class="table-main">JAVA代码</th>
                        <th class="table-main">JAVA类型</th>
                        <th class="table-main">集合</th>
                        <th class="table-main">示例</th>
                        <th class="table-main">允许为空</th>
                        <th class="table-main">备注</th>
                        <th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list list as obj>
                        <tr>
                            <td class="table-id">${obj.id}</td>
                            <td class="table-title">${obj.name}</td>
                            <td class="table-main">${obj.code}</td>
                            <td class="table-main">${obj.type}</td>
                            <td class="table-main"><#if obj.isArray>是<#else>否</#if></td>
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
