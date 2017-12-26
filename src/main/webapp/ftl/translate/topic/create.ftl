<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>讨论议题 - 添加</title>
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
	<form id="frm" action="${basePath }/topic/create.ftl" method="POST">
	  	<input title="请填写名称" id="web_name" type="text" name="name" value="用户名称">
	  	<input title="请填写标题" id="web_url" type="text" name="title" value="标题">
	  	<a href="#" onclick="doSubmit()">添加</a><a href="javascript:history.go(-1)">返回</a>
	</form>
  </body>
</html>