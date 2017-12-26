<html>
<#include "/head.ftl"/>
<link rel="stylesheet" href="${request.contextPath}/libs/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${request.contextPath}/libs/ztree/jquery.ztree-2.6.js"></script>
<script type="text/javascript">
    var zTree, selectNode = {};
    var functionIds = ",";//原始权限列表
    $(document).ready(function () {//设置选中菜单
        var setting = {
            root: {nodes: [{id: 1, pId: 0, name: '妊娠期血糖健康管理平台'}]},
            editable: true,
            edit_removeBtn: false,
            edit_renameBtn: false,
            dragCopy: false,
            dragMove: false,
            checkable: true,
            isSimpleData: true,
            treeNodeKey: "id",
            treeNodeParentKey: "parentId",
            callback: {
            }
        };
        var result = [//菜单数据
        <#list functions as obj>
            {
                id:${obj.id},
                parentId:${obj.parentId},
                name: '${obj.name}<#if obj.remark?? && obj.remark != "">(${obj.remark})</#if>'
            }<#if obj_has_next>,</#if>
        </#list>
        ];
        $.post("${request.contextPath}/role/loadFunctionIdsById?id=${role.id}", null, function (ids) {
            if (ids && ids.length > 0) {
                functionIds += ids.join(",") + ",";
            }
            if (null != result) {
                for (var i = 0; i < result.length; i++) {
                    if (ids.indexOf(result[i].id) >= 0) {
                        result[i].checked = true;
                    }
                }
                zTree = $("#ul_tree").zTree(setting, result);
                zTree.expandAll(false);
            }
        });
    });
    function checkFunctionId(event, treeId, treeNode) {
        if (treeNode.checked) {
            if (removeFunctionIds.indexOf("," + treeNode.id + ",") > 0) {//在删除列表中移除选中的id
                removeFunctionIds.replace("," + treeNode.id + ",", ",");
            } else {//在添加列表中添加选中的id
                addFunctionIds += treeNode.id + ",";
            }
        } else {
            if (addFunctionIds.indexOf("," + treeNode.id + ",") > 0) {//在添加列表中移除取消的id
                addFunctionIds.replace("," + treeNode.id + ",", ",");
            } else {//在移除列表中添加取消的id
                removeFunctionIds += treeNode.id + ",";
            }
        }
    }
    /**
     * 提交验证
     */
    function doSubmit() {
        if (confirm("确认提交授权设置？")) {
            var addFunctionIds = ",";
            var nodes = zTree.getCheckedNodes();
            for (var i in nodes) {
                var functionId = nodes[i].id;
                var key = "," + functionId + ",";
                if (functionIds.indexOf(key) > -1) {//原始列表已有的则在原始列表中移除
                    functionIds = functionIds.replace(new RegExp(key), ',');
                } else {//原始列表中没有的添加到新增列表中
                    addFunctionIds += functionId + ",";
                }
            }
            addFunctionIds = addFunctionIds.length > 1 ? addFunctionIds.substring(1, addFunctionIds.length - 1) : "";
            var removeFunctionIds = functionIds.length > 1 ? functionIds.substring(1, functionIds.length - 1) : "";//原始列表移除已有的权限后剩余的是移除的权限
            window.location.href = "${request.contextPath}/role/updateRoleFunction?id=${role.id}&addFunctionIds=" + addFunctionIds + "&removeFunctionIds=" + removeFunctionIds;
        }
    }
    function toBack() {
        window.location.href = "${request.contextPath}/role/page";
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong class="am-text-primary am-text-lg">角色权限</strong> /
                <small>设置</small>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="doSubmit()">
                            <span class="am-icon-save"> 提交授权</span>
                        </button>
                        <button type="button" class="am-btn am-btn-default" onclick="toBack()">
                            <span class="am-icon-undo"> 返回</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-6">
                <ul id="ul_tree" class="tree"></ul>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>