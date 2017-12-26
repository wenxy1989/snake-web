<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>属性模块 - 列表</title>
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
        <p>code is : ${each.code }</p>
        <p>type is : ${each.type }</p>
    	<p>
    	<a href="${basePath }/attribute/toEdit.ftl?id=${each.id}">修改</a>
    	<a href="${basePath }/attribute/delete.ftl?id=${each.id}&moduleId=${each.moduleId}">删除</a>
    	</p>
    	</div>
    	</#list>
	</div>
	</#if>
    <div><a href="${basePath }/attribute/toCreate.ftl?moduleId=${moduleId}">新建</a></div>
</body>
</html>