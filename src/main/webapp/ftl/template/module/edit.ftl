<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>模块  - 修改</title>
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
	<form id="frm" action="${basePath }/module/edit.ftl" method="POST">
		<input type="hidden" name="id" value="${object.id }">
		<input type="hidden" name="createTime" value="${object.createTime }">
		<input type="hidden" name="status" value="${object.status }">
	  	<input title="请填写名称" type="text" name="name" class="l_input" value="${object.name }">
	  	<input title="请填写标题" type="text" name="title" class="l_input" value="${object.title }">
	  	<input title="请填写类名" type="text" name="className" class="l_input" value="${object.className }">
	  	<a href="#" onclick="doSubmit()">确认更新</a>
	  	<a href="javascript:history.go(-1)">返回</a>
	</form>
  </body>
</html>