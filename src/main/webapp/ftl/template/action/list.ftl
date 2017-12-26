<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>动作模块 - 列表</title>
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
        <p>return type is : ${each.returnType }</p>
        <p>param types is : ${each.paramTypes }</p>
        <p>param names is : ${each.paramNames }</p>
        <p>action sql is : ${each.actionSql }</p>
    	<p>
    	<a href="${basePath }/action/toEdit.ftl?id=${each.id}">修改</a>
    	<a href="${basePath }/action/delete.ftl?id=${each.id}&moduleId=${each.moduleId}">删除</a>
    	</p>
    	</div>
    	</#list>
	</div>
	</#if>
    <div><a href="${basePath }/action/toCreate.ftl?moduleId=${moduleId}">新建</a></div>
</body>
</html>