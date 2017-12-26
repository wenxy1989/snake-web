<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">

<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700|Open+Sans+Condensed:700,300,300italic|Open+Sans:400,300italic,400italic,600,600italic,700,700italic,800,800italic|PT+Sans:400,400italic,700,700italic' rel='stylesheet' type='text/css' />
<link href="${basePath }/css/model/bootstrap.css" rel="stylesheet" />
<link href="${basePath }/css/model/bootstrap-responsive.css" rel="stylesheet" />
<link href="${basePath }/css/model/docs.css" rel="stylesheet" />
<link href="${basePath }/assets/js/google-code-prettify/prettify.css" rel="stylesheet" />
<link href="${basePath }/css/model/responsiveslides.css" rel="stylesheet" />
<link rel="stylesheet" href="${basePath }/css/model/prettyPhoto.css" type='text/css' />
<link rel="stylesheet" href="${basePath }/assets/build/mediaelementplayer.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath }/css/model/slide-in.css" />
<!--[if lt IE 9]><link rel="stylesheet" type="text/css" media="screen" href="${basePath }/css/model/slide-in.ie.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" type="text/css" href="${basePath }/css/model/ie.css" /><![endif]-->
<link href="${basePath }/css/model/style.css" rel="stylesheet" />

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- fav and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico" />
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png" />
<link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${basePath }/js/jquery.js"></script>