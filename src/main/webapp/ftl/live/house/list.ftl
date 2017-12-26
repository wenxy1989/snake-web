<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>房屋信息模块 - 列表</title>
	<style>
	body {
	text-aligh:center;
	font-size: 62.5%;
	font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
	}
	</style>
	<script type="text/javascript">
	$(document).ready(function(){
	$("#accordion_div").accordion();
	$("a").button();
	});
	</script>
	</head>
	<body>
	<#if objectList?exists>
    <div id="accordion_div">
    	<#list objectList as each>
    	<h3 title="${each.createTime}">${each.name }</h3>
    	<div>
        <p>title is : ${each.title }</p>
        <p>price is : ${each.price }</p>
        <p>type is : ${each.type }</p>
        <p>area is : ${each.area }</p>
        <p>position is : ${each.position }</p>
    	<p>
    	<a href="${basePath }/house/toEdit.ftl?id=${each.id}">修改</a>
    	<a href="${basePath }/house/delete.ftl?id=${each.id}">删除</a>
    	</p>
    	</div>
    	</#list>
	</div>
	</#if>
    <div><a href="${basePath }/house/toCreate.ftl">新建</a></div>
</body>
</html>