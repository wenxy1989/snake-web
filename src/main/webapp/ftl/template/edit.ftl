<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>自动生成代码模板  - 修改</title>
	<script type="text/javascript">
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
	<form id="frm" action="${basePath }/template/edit.ftl" method="POST">
	<input type="hidden" name="id" value="${object.id }">
	<input type="hidden" name="createTime" value="${object.createTime }">
	<input type="hidden" name="status" value="${object.status }">
	<table>
	<tr>
	  	<td>名        称：</td><td><input title="请填写名称" type="text" name="name" value="${object.name}"></td>
	</tr>
	<tr>
	  	<td>标        题：</td><td><input title="请填写标题" type="text" name="title" value="${object.title}"></td>
	</tr>
	<tr>
	  	<td>框架类型：</td><td><input title="请填写框架类型" type="text" name="templateType" value="${object.templateType}"></td>
	</tr>
	<tr>
	  	<td>模  板  名：</td><td><input title="请填写模板名称" type="text" name="templateName" value="${object.templateName}"></td>
	</tr>
	<!--tr>
	  	<td>模   块 名：</td><td><input title="请填写模块名称" type="text" name="moduleName" value="${object.moduleName}"></td>
	</tr-->
	<tr>
	  	<td>输出路径：</td><td><input title="请填写模块路径模板" type="text" name="savePathModel" value="${object.savePathModel}"></td>
	</tr>
	<tr>
	  	<td>输出文件：</td><td><input title="请填写模块文件名模板" type="text" name="saveFileModel" value="${object.saveFileModel}"></td>
	</tr>
	<tr>
	  	<td><a href="#" onclick="doSubmit()">确认更新</a></td><td><a href="javascript:history.go(-1)">返回</a></td>
	</tr>
	</table>
	</form>
  </body>
</html>