<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp"%>
<html>
<head>
<title>选择用户角色</title>
<script type="text/javascript">
function doDelete(id,novalId){
	if(confirm("确认删除？")){
		window.location.href = "${basePath }/character/delete.do?id=" + id + "&novalId="+novalId;
	}
}
</script>
</head>
<body>
	<c:if test="${not empty characters }">
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            <c:forEach var="each" items="${characters }" varStatus="s">
            	<li title="${each.createTime }"<c:if test="${s.last }">class="unborder"</c:if>>
            		<span class="left">${each.name }</span>
            		<span class="right">
            			<span>${each.title }</span>
            			<span><a href="${basePath }/game/select_character.do?id=${each.id }">选用</a></span>
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
    <div class="i_c">
        <div class="i_cont">
        	<ul class="r_con03">
        	<li><a href="${basePath }/game/to_create_character.do">新建角色</a>	</li>
			</ul>
		</div>
	</div>
</body>
</html>