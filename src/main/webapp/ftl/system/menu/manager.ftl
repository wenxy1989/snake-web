<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>菜单管理</title>
	<script type="text/javascript">
	function doDelete(id) {
		if (confirm("确认删除？")) {
			window.location.href = "${basePath }/menu/delete.do?id=" + id;
		}
	}
	</script>
	<script type="text/javascript" src="${basePath }/js/jquery.ztree.js"></script>
	<link rel="stylesheet" type="text/css" href="${basePath }/css/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript">
	var zTree;
	var setting = {
		view : {
			dblClickExpand : false,
			showLine : true,
			selectedMulti : false
		},
		data : {
			key :{
				url : false
			},
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : "0"
			}
		},
		callback : {
			/* beforeClick : function(treeId, treeNode,flag) {
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					return true;
				}
			}, */
			onClick : zTreeOnClick
		}
	};
	$(document).ready(function() {
		$.post("${basePath }/menu/load_all.do",{},function(data){
			var t = $("#menuTree");
			t = $.fn.zTree.init(t, setting, data);
			zTree = $.fn.zTree.getZTreeObj("menuTree");
			zTree.selectNode(zTree.getNodeByParam("id", 11));
		});

	});
	function zTreeOnClick(event,treeId,treeNode){
		if (!treeNode.isParent) {
			window.open("${basePath }"+treeNode.url, "_blank");
		}
	}
	</script>
</head>
<body>
	<div>
		<ul id="menuTree" class="ztree" style="width:260px;overflow:auto;"></ul>
	</div>
</body>
</html>