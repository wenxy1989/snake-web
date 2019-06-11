<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/head-bootstrap.jsp"%>
<title>${obj.name} - list</title>
<script type="text/javascript">
function doDelete(id){
	if(confirm("delete?")){
		window.location.href = "${"$"}{basePath }/${model.code}/delete?id=" + id;
	}
}
</script>
<body>
	<c:import url="../../common/menubar.jsp"/>
    <div class="container theme-showcase">
    <div class="input-group">
    	<span class="input-group-addon">@</span>
		<input type="text" class="form-control" placeholder="name">
	</div>
	<c:if test="${"$"}{not empty list }">
	<table class="table table-striped">
	<tr>
	<th>#</th>
	<#list attributes as attribute>
	<th>${attribute.name}</th>
	</#list>
	<th>createdTime</th>
	</tr>
    <c:forEach var="obj" items="${"$"}{list }" varStatus="s">
	<tr>
	<#list attributes as attribute>
	<td>${"$"}{obj.${attribute.code} }</td>
	</#list>
	<td>${"$"}{obj.createdTime }</td>
	<td>
	<a href="${"$"}{basePath }/${model.code}/toEdit?id=${"$"}{obj.id }">edit</a>
	<a href="#" onclick="doDelete(${"$"}{obj.id })">delete</a>
	</td>
	</tr>
    </c:forEach>
	</table>
	</c:if>
	<a href="${"$"}{basePath }/${model.code}/toCreate" class="btn btn-lg btn-success">NEW</a>
	</div>
</body>
</html>