<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<script type="text/javascript" src=""></script>
    <!-- Bootstrap core CSS -->
    <link href="${basePath }/js/bootstrap-3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="${basePath }/js/bootstrap-3.2.0/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <!-- <link href="${basePath }/js/bootstrap-3.2.0/theme.css" rel="stylesheet"> -->
<script src="${basePath }/js/jquery/jquery-1.9.1.min.js"></script>
<script src="${basePath }/js/bootstrap-3.2.0/js/bootstrap.min.js"></script>