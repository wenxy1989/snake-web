<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>网址—添加</title>
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
	<form id="frm" action="${basePath }/menu/create.do" method="POST">
	<input type="hidden" name="parentId" value="${parentId }">
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
	  	<p><input title="请填写名称" type="text" name="name" class="l_input" value="网站名称"></p>
	  	<p><input title="请填写网址" type="text" name="url" class="l_input" value="网站网址"></p>
	  	<p><textarea title="请填写标注" name="title" class="l_text" rows="5">${object.title }</textarea></p>
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