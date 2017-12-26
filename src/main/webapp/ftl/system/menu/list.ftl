<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>系统菜单 - 访问列表</title>
	<script type="text/javascript">
	function doDelete(id){
		if(confirm("确认删除？")){
			window.location.href = "${basePath }/menu/delete.do?id=" + id ;
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
            	<li title="${each.createTime }" <#if !each_has_next>class="unborder"</#if>>
            		<span class="left"><a href="${basePath }${each.url }" target="_blank">${each.name }</a></span>
            		<span class="right">
            			<span>${each.title }</span>
            			<span><a href="${basePath }/menu/toEdit.do?id=${each.id }">编辑</a></span>
            			<span><a href="javascript:doDelete(${each.id })">删除</a></span>
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
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
        	<li class="unborder">
        	<span><a href="${basePath }/menu/toCreate.do?parentId=${parentId }">新建</a></span>
        	<span style="float:right;"><a href="${basePath }/category/list.do">返回分类</a></span>
			</li>
            </ul>
        </div>
        <div class="b_02"></div>
        <div class="b_03"></div>
        <div class="b_04"></div>
        <div class="b_05"></div>
	</div>
</body>
</html>