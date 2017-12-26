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
	<c:if test="${not empty appList }">
	<table class="table table-striped">
	<tr>
	<th>database</th>
	<th>catalog</th>
	<th>operation</th>
	</tr>
    <c:forEach var="each" items="${appList }" varStatus="s">
	<tr>
	<td>jdbc:mysql://${each.url}:${each.port}/${each.database}</td>
	<td>${each.name }</td>
	<td>
	<a href="${basePath }/app/initMysql.do?id=${each.id}database=${each.database }">delete</a>
	<a href="${basePath }/app/initMysql.do?id=${each.id}database=${each.database }">initMysql</a>
	</td>
	</tr>
    </c:forEach>
	</table>
	</c:if>
	<a href="${basePath }/app/toCreate.do" class="btn btn-lg btn-success">NEW</a>
	</div>
</body>
</html>