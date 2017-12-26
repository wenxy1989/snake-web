<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>角色访问权限管理</title>
	<script type="text/javascript">
	$(document).ready(function(){
		$.post("${basePath}/access/load_role_accesses.do",{'roleCode':'${role.code }'},function(data){
			for(var i in data){
				$("#"+data[i].urlId+"_checkbox").attr('checked','checked');
			}
		});
	});
	function changeUrl(flag,urlId){
		var result = false;
		if(flag && confirm("确认添加此地址访问权限？")){
			$.ajax({type: "POST", async: false, url: "${basePath}/access/add_role_access.do", data: {'urlId':urlId,'roleCode':'${role.code }'}, success: function(msg){ result = (msg == "success");}});
			return result;
		}else if(!flag && confirm("确认移除此地址访问权限？")){
			$.ajax({type: "POST", async: false, url: "${basePath}/access/remove_role_access.do", data: {'urlId':urlId,'roleCode':'${role.code }'}, success: function(msg){ result = (msg == "success");}});
			return result;
		}
		return false;
	}
	</script>
	</head>
	<body>
	<#if urls?exists>
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            <#list urls as each>
            	<li title="${each.createTime }" class="unborder" >
            		<span class="left">
            		<input id="${each.id }_checkbox" onclick="return changeUrl(this.checked,'${each.id }');" type="checkbox">
            		<span>${each.name }</span>
            		</span>
            		<span class="right">
            			<span>${each.url }</span>
            		</span>
            	</li>
            	<li <#if !each_has_next>class="unborder"</#if>>
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
</body>
</html>