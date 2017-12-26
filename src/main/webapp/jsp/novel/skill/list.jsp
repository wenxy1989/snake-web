<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>小说系统-技能列表</title>
<script type="text/javascript">
function doDelete(id,novelId){
	if(confirm("确认删除？")){
		window.location.href = "${basePath }/skill/delete.do?id=" + id + "&novelId="+novelId;
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
            			<span><a href="${basePath }/element/list.do?belongId=${each.id }" target="_blank">要素</a></span>
            			<c:forEach items="${each.elements }" var="e">
            			<span><a href="${basePath }/${e.path }?skillId=${each.id }" target="_blank">${e.name }</a></span>
            			</c:forEach>
            			<span><a href="javascript:doDelete(${each.id },${each.novelId })">删除</a></span>
            			<span><a href="${basePath }/skill/toEdit.do?id=${each.id }">编辑</a></span>
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
        	<li><a href="${basePath }/skill/toCreate.do?${object.urlBelong }">新建</a>	</li>
			</ul>
		</div>
	</div>
</body>
</html>