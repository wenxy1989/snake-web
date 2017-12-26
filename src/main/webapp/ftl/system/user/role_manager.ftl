<!DOCTYPE html>
<html lang="en">
	<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8"/>
	<#assign basePath=context.basePath>
	<title>用户角色管理</title>
	<script type="text/javascript">
	$(document).ready(function(){
		$.post("${basePath}/auth/load_user_auths.do",{'username':'${user.username }'},function(data){
			for(var i in data){
				$("#"+data[i].roleCode+"_checkbox").attr('checked','checked');
			}
		});
	});
	function changeRole(flag,code){
		var result = false;
		if(flag && confirm("确认添加此角色？")){
			$.ajax({type: "POST", async: false, url: "${basePath}/auth/add_user_auth.do", data: {'username':'${user.username }','roleCode':code}, success: function(msg){ result = (msg == "success");}});
			return result;
		}else if(!flag && confirm("确认移除此角色？")){
			$.ajax({type: "POST", async: false, url: "${basePath}/auth/remove_user_auth.do", data: {'username':'${user.username }','roleCode':code}, success: function(msg){ result = (msg == "success");}});
			return result;
		}
		return false;
	}
	</script>
	</head>
	<body>
	<#if roles?exists>
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            <#list roles as each>
            	<li title="${each.createTime }"<#if !each_has_next>class="unborder"</#if>>
            		<span class="left">
            		<input id="${each.code }_checkbox" onclick="return changeRole(this.checked,'${each.code }');" type="checkbox">
            		<span>${each.name }</span>
            		</span>
            		<span class="right">
            			<span>${each.title }</span>
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
</body>
</html>