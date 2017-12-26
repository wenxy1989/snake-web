<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>网址-修改</title>
	<link rel="stylesheet" type="text/css" href="${basePath }/libs/jquery-ui/css/smoothness/jquery-ui-1.9.2.custom.css">
	<script type="text/javascript" src="${basePath }/libs/jquery-ui/js/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript">
	function doSubmit(){
		if($("input[name='name']").val().trim()==""){
			alert("网站名称不能为空！");
			return;
		}
		if($("input[name='url']").val().trim()==""){
			alert("网站网址不能为空！");
			return;
		}
		$("#frm").submit();	
	}
	</script>
  </head> 
  <body>
	<form id="frm" action="${basePath }/menu/edit.do" method="POST">
	<input type="hidden" name="id" value="${object.id }">
	<input type="hidden" name="parentId" value="${object.parentId }">
	<input type="hidden" name="createTime" value="${object.createTime }">
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
	  	<p><input title="请填写名称" type="text" class="l_input" name="name" value="${object.name }"></p>
	  	<p><input title="请填写网址" type="text" class="l_input" name="url" value="${object.url }"></p>
	  	<p><input title="请填写标注" type="text" class="l_input" name="title" value="${object.title }"></p>
	  	<p class="L_Btn"><span><a href="#" onclick="doSubmit()">确认更新</a></span></p><br>
	  	<p class="L_Btn"><span><a href="${basePath }/menu/toCreate.do?parentId=${object.id }">添加子菜单</a></span></p><br>
	  	<p class="L_Btn"><span><a href="${basePath }/menu/toCreate.do?parentId=0">添加根菜单</a></span></p><br>
	  	<p class="L_Btn"><span><a href="${basePath }/menu/delete.do?id=${object.id}">删除</a></span></p><br>
	  	<p class="L_Btn"><span><a href="javascript:history.go(-1)">返回</a></span></p>
	  	</div>
	    <div class="b_02"></div>
	    <div class="b_03"></div>
        <div class="b_04"></div>
	    <div class="b_05"></div>
	</div>
	</form>
  </body>
</html>