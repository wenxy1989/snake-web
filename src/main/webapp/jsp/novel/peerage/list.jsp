<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>数据模板 - 列表</title>
<!-- Mobile Specific Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript">
function doDelete(id,novelId){
	if(confirm("确认删除？")){
		window.location.href = "${basePath }/peerage/delete.do?id=" + id + "&novelId="+novelId;
	}
}
</script>
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
            			<span><a href="javascript:doDelete(${each.id },${each.novelId })">删除</a></span>
            			<span><a href="${basePath }/peerage/toEdit.do?id=${each.id }">编辑</a></span>
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
        	<li><a href="${basePath }/peerage/toCreate.do?${object.urlBelong }">新建</a></li>
			</ul>
		</div>
	</div>
</body>
</html>