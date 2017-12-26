<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>用户管理 - 列表</title>
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
            		<span class="left">${each.name }</span>
            		<span class="right">
            			<span><a href="${basePath }/user/auth_manage.do?userId=${each.id }">角色管理</a></span>
            			<span><a href="${basePath }/user/toEdit.do?id=${each.id }">编辑</a></span>
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
        	<li><a href="${basePath }/user/toCreate.do">新建</a></li>
			</ul>
		</div>
	</div>
</body>
</html>