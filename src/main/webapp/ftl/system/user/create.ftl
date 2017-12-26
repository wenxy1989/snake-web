<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>网站用户 - 添加</title>
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
	<form id="frm" action="${basePath }/create.do" method="POST">
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
	  	<p><input title="请填写名称" id="web_name" type="text" name="name" class="l_input" value="用户名称"></p>
	  	<p><input title="请填写标题" id="web_url" type="text" name="title" class="l_input" value="标题"></p>
	  	<p class="L_Btn"><span><a href="#" onclick="doSubmit()">添加</a></span></p><br>
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