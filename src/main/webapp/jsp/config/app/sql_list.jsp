<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-bootstrap.jsp"%>
<title>application - sql-list</title>
<body>
	<c:import url="../menubar.jsp"/>
    <div class="container theme-showcase">
    <div class="input-group">
    	<span class="input-group-addon">@</span>
		<input type="text" class="form-control" placeholder="name">
	</div>
	<c:if test="${not empty sqlList }">
	<table class="table table-striped">
    <c:forEach var="each" items="${sqlList }" varStatus="s">
	<tr>
	<td>${each }</td>
	</tr>
    </c:forEach>
	</table>
	</c:if>
	</div>
</body>
</html>