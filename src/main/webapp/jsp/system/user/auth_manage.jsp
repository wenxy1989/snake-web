<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>用户角色管理</title>
<script type="text/javascript">
$(document).ready(function(){
	$.post("${basePath}/auth/ajax_list_by_userid.do",{'userId':'${userId }'},function(data){
		for(var i in data){
			$("#"+data[i].roleId+"_checkbox").attr('checked','checked');
		}
	});
});
function changeRole(flag,roleId){
	var result = false;
	if(flag && confirm("确认添加此角色？")){
		$.ajax({type: "POST", async: false, url: "${basePath}/auth/ajax_add.do", data: {'userId':'${userId }','roleId':roleId}, success: function(msg){ result = (msg == "success");}});
		return result;
	}else if(!flag && confirm("确认移除此角色？")){
		$.ajax({type: "POST", async: false, url: "${basePath}/auth/ajax_remove.do", data: {'userId':'${userId }','roleId':roleId}, success: function(msg){ result = (msg == "success");}});
		return result;
	}
	return false;
}
</script>
</head>
<body>
	<c:if test="${not empty roles }">
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            <c:forEach var="each" items="${roles }" varStatus="s">
            	<li title="${each.createTime }"<c:if test="${s.last }">class="unborder"</c:if>>
            		<span class="left">
            		<input id="${each.id }_checkbox" onclick="return changeRole(this.checked,'${each.id }');" type="checkbox">
            		<span>${each.name }</span>
            		</span>
            		<span class="right">
            			<span>${each.title }</span>
            		</span>
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