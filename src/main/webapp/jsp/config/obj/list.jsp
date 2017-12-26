<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-bootstrap.jsp"%>
<title>object - list</title>
<script type="text/javascript">
function doDelete(id){
	if(confirm("delete?")){
		window.location.href = "${basePath }/obj/delete.do?id=" + id;
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
	<th>table</th>
	<th>title</th>
	<th>createdTime</th>
	<th>operation</th>
	</tr>
    <c:forEach var="obj" items="${objectList }" varStatus="s">
	<tr>
	<td>${obj.id }</td>
	<td>${obj.name }</td>
	<td>${obj.code }</td>
	<td>${obj.table }</td>
	<td>
	<c:if test="${fn:length(obj.title) > 20}">${fn:substring(obj.title, 0, 20)}...</c:if>
	<c:if test="${fn:length(obj.title) <= 20}">${obj.title }</c:if>
	</td>
	<td>${obj.createTime }</td>
	<td>
	<a href="${basePath }/obj/initTable.do?id=${obj.id }">initTable</a>
	<a href="${basePath }/attr/list.do?objId=${obj.id }">attribute</a>
	<a href="${basePath }/uniq/list.do?objId=${obj.id }">unique</a>
	<a href="${basePath }/obj/toEdit.do?id=${obj.id }">edit</a>
	<a href="#" onclick="doDelete(${obj.id })">delete</a>
	</td>
	</tr>
    </c:forEach>
	</table>
	</c:if>
	<a href="${basePath }/obj/toCreate.do?applicationId=${app.id}" class="btn btn-lg btn-success">NEW</a>
	</div>
</body>
</html>