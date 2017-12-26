<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>讨论议题 - 修改</title>
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
	<form id="frm" action="${basePath }/topic/edit.ftl" method="POST">
	<input type="hidden" name="password" value="${object.password }">
	<input type="hidden" name="createTime" value="${object.createTime }">
	<input type="hidden" name="status" value="${object.status }">
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
	  	<p><input title="请填写名称" type="text" name="name" class="l_input" value="${object.name }"></p>
	  	<p><input title="请填写标题" type="text" name="title" class="l_input" value="${object.title }"></p>
	  	<p class="L_Btn"><span><a href="#" onclick="doSubmit()">确认更新</a></span></p><br>
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