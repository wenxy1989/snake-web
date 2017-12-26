<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/header.jsp" %>
<html>
<head>
</head>
<body>
<div class="notice" style="height:100%;">
	<div style="width:30%;float:left;">
	<c:if test="${not empty node }">
	<div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul title="${node.createId }于${node.createTime }创建" class="r_con03">
            	<li class="unborder">
            	<span class="left">${node.name }</span>
            	<span class="right"><a href="${basePath }/node.toUpdate.do?node.id=${node.id}">修改</a></span>
            	</li>
            	<li class="unborder">
            	<span class="left">${node.explain }</span>
            	</li>
            	<li class="unborder">
            	<span class="right"><a href="${basePath }/node.toAdd.do?node.parentId=${node.id}">添加子节点</a></span>
            	</li>
            </ul>
        </div>
        <div class="b_02"></div>
        <div class="b_03"></div>
        <div class="b_04"></div>
        <div class="b_05"></div>
	</div>
	</c:if>
	</div>
	<div style="width:70%;height:100%;float:right;overflow-y:auto;">
	<c:if test="${not empty node.childs }">
	<c:forEach items="${node.childs }" var="child">
	<div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul title="${child.createId }于${child.createTime }创建" class="r_con03">
            	<li class="unborder">
            	<span class="left">${child.name }</span>
            	<span class="right"><a href="${basePath }/node.delete.do?node.id=${node.id}">删除</a></span>
            	</li>
            	<li class="unborder">
            	<span class="left">${child.explain }</span>
            	</li>
            </ul>
        </div>
        <div class="b_02"></div>
        <div class="b_03"></div>
        <div class="b_04"></div>
        <div class="b_05"></div>
	</div>
	</c:forEach>
	</c:if>
	</div>
</div>
</body>
</html>