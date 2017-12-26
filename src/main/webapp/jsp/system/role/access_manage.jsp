<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>角色管理 - 访问菜单管理</title>
<script type="text/javascript">
$(document).ready(function(){
	$.post("${basePath}/access/ajax_list_by_roleid.do",{'roleId':'${roleId }'},function(data){
		for(var i in data){
			$("#"+data[i].menuId+"_checkbox").attr('checked','checked');
		}
	});
});
function changeUrl(flag,menuId){
	var result = false;
	if(flag && confirm("确认添加此地址访问权限？")){
		$.ajax({type: "POST", async: false, url: "${basePath}/access/ajax_add.do", data: {'menuId':menuId,'roleId':'${roleId }'}, success: function(msg){ result = (msg == "success");}});
		return result;
	}else if(!flag && confirm("确认移除此地址访问权限？")){
		$.ajax({type: "POST", async: false, url: "${basePath}/access/ajax_remove.do", data: {'menuId':menuId,'roleId':'${roleId }'}, success: function(msg){ result = (msg == "success");}});
		return result;
	}
	return false;
}
</script>
</head>
<body>
	<c:if test="${not empty menus }">
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            <c:forEach var="each" items="${menus }" varStatus="s">
            	<li title="${each.createTime }" class="unborder" >
            		<span class="left">
            		<input id="${each.id }_checkbox" onclick="return changeUrl(this.checked,'${each.id }');" type="checkbox">
            		<span>${each.name }</span>
            		</span>
            		<span class="right">
            			<span>${each.url }</span>
            		</span>
            	</li>
            	<li <c:if test="${s.last }">class="unborder"</c:if>>
            		<p style="text-indent:2em;">${each.title }</p>
            	</li>
            </c:forEach>
            </ul>
        </div>
        <div class="b_02"></div>
        <div class="b_03"></div>
        <div class="b_04"></div>
        <div class="b_05"></div>
	</div>
	</c:if>
</body>
</html>