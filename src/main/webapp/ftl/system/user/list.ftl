<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
<title>网站用户 - 列表</title>
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
            	<li title="${each.createTime }" class="unborder">
            		<span class="left">${each.name }</span>
            		<span class="right">
            			<span><a href="${basePath }/user/role_manage.do?name=${each.name }">角色管理</a></span>
            			<span><a href="${basePath }/toEdit.do?name=${each.name }">编辑</a></span>
            		</span>
            	</li>
            	<li <#if each_has_next>class="unborder"</#if>>
            		<p style="text-indent:2em;">${each.title }</p>
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
        	<li><a href="${basePath }/toCreate.do">新建</a></li>
			</ul>
		</div>
	</div>
</body>
</html>