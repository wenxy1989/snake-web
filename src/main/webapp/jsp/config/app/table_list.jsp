<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-bootstrap.jsp"%>
<title>application - list</title>
<script type="text/javascript">
function doDelete(id){
	if(confirm("delete?")){
		window.location.href = "${basePath }/obj/delete.do?applicationId=${app.id}&id=" + id;
	}
}
</script>
<body>
	<c:import url="../menubar.jsp"/>
    <div class="container theme-showcase">
    <div class="input-group">
    	<span class="input-group-addon">@</span>
		<input type="text" class="form-control" placeholder="name">
	</div>
	<c:if test="${not empty list }">
	<table class="table table-striped">
	<tr>
	<th>name</th>
	<th>table</th>
	<th>createdTime</th>
	<th>operation</th>
	</tr>
    <c:forEach var="each" items="${list }" varStatus="s">
	<tr>
	<td>${each.name }</td>
	<td>${each.table }</td>
	<td>${each.createTime }</td>
	<td>
	<a href="javascript:void(0)" onclick="doDelete(${each.id})">delete</a>
	</td>
	</tr>
    </c:forEach>
	</table>
	</c:if>
	<a href="${basePath }/app/initMysql.do" class="btn btn-lg btn-success">init mysql</a>
	</div>
</body>
</html>