<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-bootstrap.jsp"%>
<title>application - list</title>
<script type="text/javascript">
function doDelete(id){
	if(confirm("delete?")){
		window.location.href = "${basePath }/app/delete.do?id=" + id;
	}
}
function printFiles(id){
	$.post("${basePath }/app/createFile.do",{'id':id},function(){
		alert("finished print files!");
	});
}
</script>
<body>
	<c:import url="../menubar.jsp"/>
    <div class="container theme-showcase">
    <div class="input-group">
    	<span class="input-group-addon">@</span>
		<input type="text" class="form-control" placeholder="name">
	</div>
	<c:if test="${not empty objectList }">
	<table class="table table-striped">
	<tr>
	<th>#</th>
	<th>name</th>
	<th>code</th>
	<th>database</th>
	<th>title</th>
	<th>createdTime</th>
	<th>operation</th>
	</tr>
    <c:forEach var="each" items="${objectList }" varStatus="s">
	<tr>
	<td>${each.id }</td>
	<td>${each.name }</td>
	<td>${each.code }</td>
	<td><c:if test="${each.database != null}">jdbc:mysql://${each.url}:${each.port}/${each.database}</c:if></td>
	<td>
	<c:if test="${fn:length(each.title) > 20}">${fn:substring(each.title, 0, 20)}...</c:if>
	<c:if test="${fn:length(each.title) <= 20}">${each.title }</c:if>
	</td>
	<td>${each.createTime }</td>
	<td>
	<a href="${basePath }/obj/list.do?applicationId=${each.id }">tables</a>
	<a href="${basePath }/app/toEdit.do?id=${each.id }">edit</a>
	<a href="${basePath }/app/doParseSQL.do?applicationId=${each.id }">print sql</a>
	<a href="javascript:printFiles(${each.id })">create file</a>
	<a href="#" onclick="doDelete(${each.id })">delete</a>
	</td>
	</tr>
    </c:forEach>
	</table>
	</c:if>
	<a href="${basePath }/app/toCreate.do" class="btn btn-lg btn-success">NEW APP</a>
	<a href="${basePath }/app/toAdd.do" class="btn btn-lg btn-success">ADD MYSQL</a>
	</div>
</body>
</html>