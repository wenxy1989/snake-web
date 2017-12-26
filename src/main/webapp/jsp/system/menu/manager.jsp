<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>菜单管理<m:my id="my" name="wenxueyong" cssStyle="" click=""/></title>
<script type="text/javascript">
	function doDelete(id) {
		if (confirm("确认删除？")) {
			window.location.href = "${basePath }/menu/delete.do?id=" + id;
		}
	}
</script>
<link rel="stylesheet" type="text/css" href="${basePath }/libs/jquery-ui/css/smoothness/jquery-ui-1.9.2.custom.css">
<link rel="stylesheet" type="text/css" href="${basePath }/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="${basePath }/libs/jquery-ui/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="${basePath }/js/jquery.ztree.js"></script>
<SCRIPT type="text/javascript">
	var zTree,selectedMenuId;
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
			//zTree.selectNode(zTree.getNodeByParam("id", 11));
		});

	});
	
	function zTreeOnClick(event,treeId,treeNode){
		selectedMenuId = treeNode.id;
		var menuFrame = document.getElementById("menuFrame");
		menuFrame.src = "${basePath }/menu/details.do?id="+selectedMenuId;
		/* if (!treeNode.isParent) {
			window.open("${basePath }"+treeNode.url, "_blank");
		} */
	}
</script>
</head>
<body style="width:100%;height:90%;">
	<div style="width:30%;height:100%;float:left;">
		<ul id="menuTree" class="ztree" style="width:100%;height:100%;overflow:auto;"></ul>
	</div>
	<div style="width:70%;height:100%;float:left;">
		<div><a href="${basePath }/menu/list.do">菜单管理</a></div>
		<iframe id="menuFrame" style="width:100%;height:100%;"></iframe>
	</div>
</body>
</html>