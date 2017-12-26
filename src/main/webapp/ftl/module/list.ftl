<!DOCTYPE html>
<html lang="en">
	<head>
	<${'#'}import "../../common/context.ftl" as context>
	<${'#'}include "../../common/header.ftl" encoding="utf-8"/>
	<${'#'}assign basePath=context.basePath>
	<title>${moduleName} - 列表</title>
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
	<${'#'}if objectList?exists>
    <div id="accordion_div">
    	<${'#'}list objectList as each>
    	<h3 title="${'$'}{each.createTime}">${'$'}{each.name }</h3>
    	<div>
        <p>title is : ${'$'}{each.title }</p>
		<#if attributes?exists>
		<#list attributes as attribute>
        <p>${attribute.code?lower_case} is : ${'$'}{each.${attribute.code?lower_case} }</p>
		</#list>
		</#if>
    	<p>
    	<a href="${'$'}{basePath }/${moduleCode}/toEdit.ftl?id=${'$'}{each.id}">修改</a>
    	<a href="${'$'}{basePath }/${moduleCode}/delete.ftl?id=${'$'}{each.id}">删除</a>
		<#if actions?exists>
		<#list actions as action>
    	<a href="${'$'}{basePath }/${moduleCode}/${action.code?lower_case}.ftl">${action.name}</a>
		</#list>
		</#if>
    	</p>
    	</div>
    	</${'#'}list>
	</div>
	</${'#'}if>
    <div><a href="${'$'}{basePath }/${moduleCode}/toCreate.ftl">新建</a></div>
</body>
</html>