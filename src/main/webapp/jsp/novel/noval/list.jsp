<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>文学小说 - 列表</title>
<meta charset="utf-8" />
<script type="text/javascript">
	function doDelete(id) {
		if (confirm("确认删除？")) {
			window.location.href = "${basePath }/novel/delete.do?id=" + id;
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
				<li title="${each.createTime }" class="unborder" >
					<span class="left">${each.name }</span>
					<span class="right">
						<span>
						<a href="javascript:doDelete(${each.id })">删除</a>
						</span>
						<span>
						<a href="${basePath }/novel/toEdit.do?id=${each.id }">编辑</a>
						</span>
					</span>
				</li>
				<li title="${each.createTime }" class="unborder">
					<p style="text-indent:2em;">${each.title }</p>
				</li>
				<li title="${each.createTime }" <c:if test="${s.last }">class="unborder"</c:if>>
					<span>
					<a href="${basePath }/game/to_select_character.do" >进入游戏</a>
					<a href="${basePath }/element/list.do?belongId=${each.id }" target="_blank">要素</a>
					</span>
					<c:forEach items="${each.elements }" var="e">
					<span>
					<a href="${basePath }/${e.path }?novelId=${each.id }" target="_blank">${e.name }</a>
					</span>
					</c:forEach>
				</li>
				</c:forEach>
			</ul>
			<!-- <div class="page">
				<a href="#">下一页</a><span>|</span><a href="#">最后一页</a><span>|</span>1/32
			</div> -->
		</div>
		<div class="b_02"></div>
		<div class="b_03"></div>
		<div class="b_04"></div>
		<div class="b_05"></div>
	</div>
	</c:if>
	
	<div class="i_c">
		<div class="b_05"></div>
		<div class="b_04"></div>
		<div class="b_03"></div>
		<div class="b_02"></div>
		<div class="i_cont">
			<ul class="r_con03">
				<li class="unborder"><a href="${basePath }/novel/toCreate.do">新建</a></li>
			</ul>
		</div>
		<div class="b_02"></div>
		<div class="b_03"></div>
		<div class="b_04"></div>
		<div class="b_05"></div>
	</div>
</body>
</html>