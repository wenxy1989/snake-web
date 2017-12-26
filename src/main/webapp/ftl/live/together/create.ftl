<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
    <title>房屋合租模块 — 添加</title>
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
	<form id="frm" action="${basePath }/together/create.ftl" method="POST">
	<table>
	<tr>
	  	<td><label>名称</label></td><td><input title="请填写名称" type="text" name="name" value="名称"></td>
	</tr>
	<tr>
	  	<td><label>标题</label></td><td><input title="请填写标题" type="text" name="title" value="标题"></td>
	</tr>
	<tr>
	  	<td><label>意向居住地</label></td><td><input title="请填写意向居住地" type="text" name="address" value="Address"></td>
	</tr>
	<tr>
	  	<td><label>自身承受房租大概值</label></td><td><input title="请填写自身承受房租大概值" type="text" name="averageprice" value="AveragePrice"></td>
	</tr>
	<tr>
	  	<td><label>理想居住地十平米平均价格</label></td><td><input title="请填写理想居住地十平米平均价格" type="text" name="tenprice" value="TenPrice"></td>
	</tr>
	<tr>
	  	<td><label>性别限制</label></td><td><input title="请填写性别限制" type="text" name="sex" value="Sex"></td>
	</tr>
	<tr>
	  	<td><a href="#" onclick="doSubmit()">添加</a></td><td><a href="javascript:history.go(-1)">返回</a></td>
	</tr>
	</table>
	</form>
  </body>
</html>