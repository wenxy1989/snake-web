<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>登录</title>
	<script type="text/javascript">
	function doSubmit(){
		if($("input[name='j_username']").val().trim()==""){
			alert("用户名不能为空！");
			return;
		}
		if($("input[name='j_password']").val().trim()==""){
			alert("密码名不能为空！");
			return;
		}
		$("#frm").submit();	
	}
	</script>
	</head>
	<body style="margin:0 auto">
	
	<form id="frm" action="${basePath }/j_spring_security_check" method="post">
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
          <p><input type='text' name='j_username'  class="l_input"></p>
		  <p><input type='password' name='j_password'  class="l_input"></p>
          <p class="L_Btn"><span><a href="#" onclick="doSubmit()">登陆</a></span></p><br>
          <p class="L_Btn"><span><a href="${basePath }/user/register.do" >注册</a></span></p>
	    </div>
	    <div class="b_02"></div>
	    <div class="b_03"></div>
        <div class="b_04"></div>
	    <div class="b_05"></div>
	</div>
	</form>
	</body>
</html>