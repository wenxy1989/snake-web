<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>角色管理 - 列表</title>
<script type="text/javascript">
function doDelete(id){
	if(confirm("确认删除？")){
		window.location.href = "${basePath }/role/delete.do?id=" + id;
	}
}
</script>
</head>
<body>
	<c:if test="${not empty objectList }">
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            <c:forEach var="each" items="${objectList }" varStatus="s">
            	<li title="${each.createTime }" class="unborder">
            		<span class="left">
            			<span>${each.code }</span>
            			<span>${each.name }</span>
            		</span>
            		<span class="right">
            			<span><a href="${basePath }/role/access_manage.do?roleId=${each.id }">访问管理</a></span>
            			<span><a href="${basePath }/role/toEdit.do?code=${each.id }">编辑</a></span>
            			<span><a href="javascript:doDelete('${each.id }')">删除</a></span>
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
    <div class="i_c">
        <div class="i_cont">
        	<ul class="r_con03">
        	<li>
        	<span><a href="${basePath }/role/toCreate.do">新建</a></span>
			</li>
			</ul>
		</div>
	</div>
</body>
</html>