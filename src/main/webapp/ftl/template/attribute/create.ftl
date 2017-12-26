<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>属性模块 — 添加</title>
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
	<form id="frm" action="${basePath }/attribute/create.ftl" method="POST">
	<input type="hidden" name="moduleId" value="${moduleId}">
	<table>
	<tr>
	  	<td><label>名称</label></td><td><input title="请填写名称" type="text" name="name" value="名称"></td>
	</tr>
	<tr>
	  	<td><label>标题</label></td><td><input title="请填写标题" type="text" name="title" value="标题"></td>
	</tr>
	<tr>
	  	<td><label>代码</label></td><td><input title="请填写代码" type="text" name="code" value="Code"></td>
	</tr>
	<tr>
	  	<td><label>类型</label></td><td><input title="请填写类型" type="text" name="type" value="String"></td>
	</tr>
	<tr>
	  	<td><a href="#" onclick="doSubmit()">添加</a></td><td><a href="javascript:history.go(-1)">返回</a></td>
	</tr>
	</table>
	</form>
  </body>
</html>