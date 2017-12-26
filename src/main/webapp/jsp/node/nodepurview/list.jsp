<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>笔记分组用户权限 - 列表</title>
<script type="text/javascript">
function doDelete(id){
	if(confirm("确认删除？")){
		window.location.href = "${basePath }/nodepurview/delete.do?id=" + id;
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
            	<li title="${each.createTime }"<c:if test="${s.last }">class="unborder"</c:if>>
            		<span class="left">${each.name }</span>
            		<span class="right">
            			<span>${each.title }</span>
            			<span><a href="javascript:doDelete(${each.id })">删除</a></span>
            			<span><a href="${basePath }/nodepurview/toEdit.do?id=${each.id }">编辑</a></span>
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
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
        	<li class="unborder"><a href="${basePath }/nodepurview/toCreate.do">新建</a></li>
            </ul>
        </div>
        <div class="b_02"></div>
        <div class="b_03"></div>
        <div class="b_04"></div>
        <div class="b_05"></div>
	</div>
</body>
</html>