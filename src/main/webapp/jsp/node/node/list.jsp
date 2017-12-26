<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>我的笔记-笔记列表</title>
<!-- Mobile Specific Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript">
function hiddenInput(name,value){
	return "<input type='hidden' name='"+name+"' value='"+value+"'>";
}
function postChilds(id){
	var frm = document.getElementById("form_"+id);
	frm.innerHTML += hiddenInput("id",id);
	//frm.innerHTML += hiddenInput("fieldId",fieldId);
	frm.submit();
}
function toCreate(){
	var frm = document.getElementById("object_form");
	frm.action = "${basePath }/node/toCreate.do";
	frm.submit();
}
function doDelete(id, parentId){
	if(confirm("确认删除？")){
		window.location.href = "${basePath }/node/delete.do?id=" + id + "&parentId=" + parentId;
	}
}
function toChilds(id){
	window.location.href = "${basePath }/node/childs.do?id=" + id;
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
            	<form id="form_${each.id }" action="${basePath }/node/childs.do" method="POST"></form>
            	<li title="${each.createTime }" class="unborder">
            		<span class="left" onclick="postChilds(${each.id},${each.fieldId })">${each.name }</span>
            		<span class="right">${each.createTime }</span>
            	</li>
            	<li title="${each.createTime }" class="unborder">
					<p style="text-indent:2em;">${each.explain }</p>
            	</li>
            	<li title="${each.createTime }" <c:if test="${s.last }">class="unborder"</c:if>>
            		<span class="left">
            			<a href="${basePath }/node/toEdit.do?id=${each.id }">编辑</a>
            		</span>
            		<span class="right">
            			<a href="javascript:doDelete(${each.id },${each.parentId })">删除</a>
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
	
	<form id="object_form" method="POST">
	<input type="hidden" name="parentId" value="${object.id }">
	<input type="hidden" name="fieldId" value="${object.fieldId }">
	</form>
	
	<div class="i_c">
		<div class="b_05"></div>
		<div class="b_04"></div>
		<div class="b_03"></div>
		<div class="b_02"></div>
		<div class="i_cont">
			<ul class="r_con03">
        	<li class="unborder">
        	<span class="left">
        	<a href="javascript:toCreate()">新建</a>
        	</span>
        	<c:if test="${not empty object.parentId or object.parentId > 0}">
        	<span class="right">
        	<a href="javascript:toChilds(${object.parentId })">上一层</a>
        	</span>
        	</c:if>
        	</li>
			</ul>
		</div>
		<div class="b_02"></div>
		<div class="b_03"></div>
		<div class="b_04"></div>
		<div class="b_05"></div>
	</div>
</body>
</html>