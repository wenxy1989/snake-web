<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="view_header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>错误页面</title>
<!-- Mobile Specific Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
</head>
<body>
	<header class="header">
		<div class="container">
			<div class="row">
				<!-- logo -->
				<div class="span4">
					<a href="index.html" class="logo">
					<img src="assets/img/logo.png" alt="" />
					</a>
				</div>
				<!-- menu -->
				<div class="span8">
					<nav>
						<ul class="right">
						<c:forEach items="${categorys }" var="category" varStatus="status">
							<li <c:if test="${category.id == categoryId or (empty categoryId and status.first)}">class="current"</c:if> title="${category.title }">
								<a href="${basePath }/website.view?categoryId=${category.id }">${category.name }</a>
								<span class="indicator"></span>
							</li>
						</c:forEach>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</header>

	<!-- Slogan================================================== -->
	<div class="container">
		<div class="row slider-cont">
			<section>
				<div class="span12 promo-slogan">
					<h1>
						<span><a href="${basePath }/user/login.do">登陆</a></span>  您访问的页面不存在！
					</h1>
				</div>
			</section>
		</div>
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
									<a href="${web.url }" title="${web.title }"  onclick="checkSite(this)" data-option-value="*" target="_blank">${web.name }</a>
									</c:if>
									<c:if test="${not status.first }">
									<a href="${web.url }" title="${web.title }" data-option-value="*" onclick="checkSite(this)" target="_blank">${web.name }</a>
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
							<h4>黄骅网址导航</h4>
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