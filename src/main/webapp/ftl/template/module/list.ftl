<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>模块  - 列表</title>
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
	function exportCode(id){
		$.post("${basePath }/module/export_code.ftl",{'id':id});
	}
	</script>
	</head>
	<body>
	<#if objectList?exists>
    <div id="accordion_div">
        <#list objectList as each>
    	<h3 title="${each.createTime}">${each.name }</h3>
    	<div>
    	<p>
    	${each.title}
    	</p>
    	<p>
    	module class name : ${each.className}
    	</p>
    	<p>
    	<a href="${basePath }/attribute/list.ftl?moduleId=${each.id}" target="_blank">属性</a>
    	<a href="${basePath }/action/list.ftl?moduleId=${each.id}" target="_blank">动作</a>
    	<a href="${basePath }/module/toEdit.ftl?id=${each.id}">修改</a>
    	<a href="javascript:exportCode(${each.id })">export code file</a>
    	<a href="${basePath }/module/delete.ftl?id=${each.id}">删除</a>
    	</p>
    	</div>
    	</#list>
	</div>
	</#if>
    <div class="i_c">
    	<a href="${basePath }/module/toCreate.ftl">新建</a>
	</div>
</body>
</html>