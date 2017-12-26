<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>系统参数 - 列表</title>
	<script type="text/javascript">
	function doDelete(code){
		if(confirm("确认删除？")){
			window.location.href = "${basePath }/character/delete.do?code=" + code;
		}
	}
	</script>
	</head>
	<body>
	<#if objectList?exists>
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            <#list objectList as each>
            	<li title="${each.createTime }" <#if each_has_next>class="unborder"</#if>>
            		<span class="left">${each.name }</span>
            		<span class="right">
            			<span>${each.title }</span>
            			<span>${each.code }</span>
            			<span><a href="${basePath }/parameter/toEdit.do?code=${each.code }">编辑</a></span>
            			<span><a href="javascript:doDelete('${each.code }')">删除</a></span>
            		</span>
            	</li>
            </#list>
            </ul>
        </div>
        <div class="b_02"></div>
        <div class="b_03"></div>
        <div class="b_04"></div>
        <div class="b_05"></div>
	</div>
	</#if>
    <div class="i_c">
        <div class="i_cont">
        	<ul class="r_con03">
        	<li>
        	<span><a href="${basePath }/parameter/toCreate.do">新建</a></span>
			</li>
			</ul>
		</div>
	</div>
</body>
</html>