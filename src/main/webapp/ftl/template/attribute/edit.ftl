<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>属性模块 — 修改</title>
	<style>
	body {
	text-aligh:center;
	font-size: 62.5%;
	font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
	}
	</style>
	<script type="text/javascript">
	$(document).ready(function(){
		$("a").button();
	});
	function doSubmit(){
		if($("input[name='name']").val().trim()==""){
			alert("名称不能为空！");
			return;
		}
		$("#frm").submit();	
	}
	</script>
  </head> 
  <body>
	<form id="frm" action="${basePath }/attribute/edit.ftl" method="POST">
	<input type="hidden" name="createTime" value="${object.createTime }" >
	<input type="hidden" name="id" value="${object.id}">
	<input type="hidden" name="moduleId" value="${object.moduleId}">
	<table>
	<tr>
	  	<td><label>名称</label></td><td><input title="请填写名称" type="text" name="name" value="${object.name}"></td>
	</tr>
	<tr>
	  	<td><label>标题</label></td><td><input title="请填写标题" type="text" name="title" value="${object.title}"></td>
	</tr>
	<tr>
	  	<td><label>代码</label></td><td><input title="请填写代码" type="text" name="code" value="${object.code}"></td>
	</tr>
	<tr>
	  	<td><label>类型</label></td><td><input title="请填写类型" type="text" name="type" value="${object.type}"></td>
	</tr>
	<tr>
	  	<td><a href="#" onclick="doSubmit()">确认更行</a></td><td><a href="javascript:history.go(-1)">返回</a></td>
	</tr>
	</form>
  </body>
</html>