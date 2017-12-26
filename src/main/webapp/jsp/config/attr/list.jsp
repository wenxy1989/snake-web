<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-bootstrap.jsp"%>
<title>attribute - list</title>
<script type="text/javascript">
function doDelete(id){
	if(confirm("delete?")){
		window.location.href = "${basePath }/attr/delete.do?id=" + id;
	}
}
</script>
<body>
	<c:import url="../menubar.jsp"/>
    <div class="container theme-showcase">
	<c:if test="${not empty objectList }">
	<table class="table table-striped">
	<tr>
	<th>#</th>
	<th>name</th>
	<th>code</th>
	<th>javaType</th>
	<th>createdTime</th>
	<th>operation</th>
	</tr>
    <c:forEach var="obj" items="${objectList }" varStatus="s">
	<tr>
	<td>${obj.id }</td>
	<td>${obj.name }</td>
	<td>${obj.code }</td>
	<td>${obj.javaType }</td>
	<td>${obj.createTime }</td>
	<td>
	<a href="${basePath }/attr/toEdit.do?id=${obj.id }">edit</a>
	<a href="#" onclick="doDelete(${obj.id })">delete</a>
	</td>
	</tr>
    </c:forEach>
	</table>
	</c:if>
	<a href="${basePath }/attr/toCreate.do?objId=${obj.id}" class="btn btn-lg btn-success">NEW</a>
	</div>
</body>
</html>