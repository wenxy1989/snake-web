<%@ page language="java" import="org.springframework.security.core.Authentication,org.springframework.security.core.context.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../../../view_header.jsp"%>
<%
	SecurityContext ctx = SecurityContextHolder.getContext();              
	Authentication auth = ctx.getAuthentication();
	Object user = auth.getPrincipal();
%>
<html>
<head>
<meta charset="utf-8" />
<title>首页展示</title>
<!-- Mobile Specific Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript">
function checkSite(which){
	$(".selected").removeClass("selected");
	$(which).addClass("selected");
}
</script>
</head>
<body>
	<header class="header">
		<div class="container">
		<div class="row">
			<section style="padding-top: 10px">
				<div class="span12">
					<div id="filters" class="<!-- pull-right --> clearfix">
					<ul class="filter-data" data-option-key="filter">
					<c:forEach items="${categorys }" var="category" varStatus="status">
					<li><a href="${basePath }/index.view?categoryId=${category.id }">${category.name }</a></li>
					</c:forEach>
					<%if(null == user || "anonymousUser".equals(user)){%>
					<li><a href="${basePath }/user/login.do">登陆</a></li>
			        <%}else{%>
					<li><a href="${basePath }/j_spring_security_logout">退出</a></li>
			        <li><sec:authentication property="name"/></li>
					<%}%>
					</ul>
					</div>
				</div>
			</section>
		</div>
		</div>
	</header>

	<!-- Slogan================================================== -->
	<div class="container">
		<!-- <div class="row slider-cont">
			<section>
				<div class="span12 promo-slogan">
					<h1>
						Hello and welcome to <span>justi</span> , a web design studio.
						We're a group of experienced marketers, designers and developers
					</h1>
				</div>
			</section>
		</div> -->
		<div class="row">
			<div class="span12">
				<div class="row">
					<section style="padding-top: 10px">
						<div class="span12">

							<!-- right blank -->
							<!-- <div class="pull-right">
								<ul class="filter-change pull-right" style="margin-left: 0;">
									<li id="change-small"><a href="#change-small"
										class="change-select"><i class="icon-th-list icon-white"></i></a>
										<a href="#change-small"><i
											class="icon-th-large icon-white"></i></a></li>
								</ul>
							</div> -->
							<!-- main links -->
							<div id="filters" class="<!-- pull-right --> clearfix">
								<ul class="filter-data" data-option-key="filter">
									<c:forEach items="${websites }" var="web" varStatus="status">
									<li>
									<c:if test="${status.first }">
									<a href="${basePath }${web.url }" title="${web.title }"  onclick="checkSite(this)" data-option-value="*" target="_blank">${web.name }</a>
									</c:if>
									<c:if test="${not status.first }">
									<a href="${basePath }${web.url }" title="${web.title }" data-option-value="*" onclick="checkSite(this)" target="_blank">${web.name }</a>
									</c:if>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</section>
				</div>
				<!-- bottom -->
				<div class="row">
					<section>
						<div class="span12 divider-strip">
							<h4>首页 - 展示</h4>
							<span class="strip-block">
							<em>想点哪里，点哪里</em>
							</span>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
</body>
</html>