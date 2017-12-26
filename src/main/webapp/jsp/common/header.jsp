<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ taglib uri="http://java.mytag.com/jsp/jstl/source" prefix="m"%>--%>
<%
	StringBuffer sb = new StringBuffer(request.getScheme());
	sb.append("://");
	sb.append(request.getServerName());
	sb.append(":");
	sb.append(request.getServerPort());
	sb.append(request.getContextPath());
	String basePath = sb.toString();
	request.setAttribute("basePath", basePath);
%>
<!-- Mobile Specific Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link rel="stylesheet" type="text/css" href="${basePath }/css/css.css">
<script type="text/javascript" src="${basePath }/js/jquery.js"></script>
<div class="s_header">
    <a href="javascript:history.go(-1)">返回上页&nbsp;&nbsp;&nbsp;&nbsp;</a>
    <a href="<%=basePath %>/j_spring_security_logout">&nbsp;&nbsp;&nbsp;&nbsp;退出登录</a>
</div>