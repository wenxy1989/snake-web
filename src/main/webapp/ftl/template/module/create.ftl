<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>模块  - 添加</title>
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
	<form id="frm" action="${basePath }/module/create.ftl" method="POST">
	  	<input title="请填写名称" type="text" name="name" value="模块名称">
	  	<input title="请填写标题" type="text" name="title" value="模块标题">
	  	<input title="请填类名" type="text" name="className" value="Class">
	  	<a href="#" onclick="doSubmit()">添加</a><a href="javascript:history.go(-1)">返回</a>
	</form>
  </body>
</html>