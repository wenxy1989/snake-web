<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#import "../../common/context.ftl" as context>
	<#include "../../common/header.ftl" encoding="utf-8">
	<#assign basePath=context.basePath>
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
	<div class="s_header">
	    <a href="javascript:history.go(-1)">返回上页&nbsp;&nbsp;&nbsp;&nbsp;</a>
	    <a href="${basePath }/j_spring_security_logout">退出登录&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</div>
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
          <p class="L_Btn"><span><a href="${basePath }/register.do" >注册</a></span></p>
	    </div>
	    <div class="b_02"></div>
	    <div class="b_03"></div>
        <div class="b_04"></div>
	    <div class="b_05"></div>
	</div>
	</form>
	</body>
</html>