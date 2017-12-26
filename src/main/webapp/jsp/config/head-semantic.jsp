<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${basePath }/ico/favicon.ico">
<link rel="stylesheet" href="${basePath }/js/semantic/css/semantic.min.css">
<script type="text/javascript" src="${basePath }/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${basePath }/js/semantic/js/semantic.min.js"></script>
<script type="text/javascript" src="${basePath }/js/validator.js"></script>