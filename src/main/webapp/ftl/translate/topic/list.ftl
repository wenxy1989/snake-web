<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>讨论议题 - 列表</title>
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
    	<p>
    	${each.title}
    	</p>
    	</div>
    	</#list>
	</div>
	</#if>
    <div class="i_c">
    	<a href="${basePath }/topic/toCreate.ftl">新建</a>
	</div>
</body>
</html>