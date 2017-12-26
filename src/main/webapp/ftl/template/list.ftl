<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>自动生成代码模板  - 列表</title>
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
		$.post("${basePath }/template/export_code.ftl",{'id':id});
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
    	type : ${each.templateType}
    	</p>
    	<!--p>
    	module name : ${each.moduleName}
    	</p-->
    	<p>
    	template name : ${each.templateName}
    	</p>
    	<p>
    	save path model : ${each.savePathModel}
    	</p>
    	<p>
    	save file model : ${each.saveFileModel}
    	</p>
    	<p>
    	<a href="${basePath }/template/toEdit.ftl?id=${each.id}">修改</a>
    	<a href="${basePath }/template/delete.ftl?id=${each.id}">删除</a>
    	</p>
    	</div>
    	</#list>
	</div>
	</#if>
    <div class="i_c">
    	<a href="${basePath }/template/toCreate.ftl">新建</a>
	</div>
</body>
</html>