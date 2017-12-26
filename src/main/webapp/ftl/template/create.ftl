<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>自动生成代码模板  - 添加</title>
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
	<form id="frm" action="${basePath }/template/create.ftl" method="POST">
	<table>
	<tr>
	  	<td>名        称：</td><td><input title="请填写名称" type="text" name="name" value="模板名称"></td>
	</tr>
	<tr>
	  	<td>标        题：</td><td><input title="请填写标题" type="text" name="title" value="模板标题"></td>
	</tr>
	<tr>
	  	<td>框架类型：</td><td><input title="请填写框架类型" type="text" name="templateType" value="base_template"></td>
	</tr>
	<tr>
	  	<td>模  板  名：</td><td><input title="请填写模板名称" type="text" name="templateName" value="*.java.ftl"></td>
	</tr>
	<!--tr>
	  	<td>模   块 名：</td><td><input title="请填写模块名称" type="text" name="moduleName" value="test"></td>
	</tr-->
	<tr>
	  	<td>输出路径：</td><td><input title="请填写模块路径模板" type="text" name="savePathModel" value="com.module.@{moduleName}.test"></td>
	</tr>
	<tr>
	  	<td>输出文件：</td><td><input title="请填写模块文件名模板" type="text" name="saveFileModel" value="@{moduleName}.java"></td>
	</tr>
	<tr>
	  	<td><a href="#" onclick="doSubmit()">添加</a></td><td><a href="javascript:history.go(-1)">返回</a></td>
	</tr>
	</table>
	</form>
  </body>
</html>