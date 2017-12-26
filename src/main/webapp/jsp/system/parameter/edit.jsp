<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>系统参数 — 修改</title>
	<script type="text/javascript">
	function doSubmit(){
		if($("input[name='name']").val().trim()==""){
			alert("名称不能为空！");
			return;
		}
		if($("input[name='key']").val().trim()==""){
			alert("键值不能为空！");
			return;
		}
		$("#frm").submit();	
	}
	</script>
  </head> 
  <body>
	<form id="frm" action="${basePath }/parameter/edit.do" method="POST">
	<input type="hidden" name="createTime" value="${object.createTime }" >
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
	  	<p><input title="请填写名称" type="text" name="name" class="l_input" value="${object.name }"></p>
	  	<p><input title="请填写键值" type="text" name="code" class="l_input" value="${object.key }"></p>
	  	<p><textarea title="请填写标注" name="title" class="l_text" rows="5">${object.title }</textarea></p>
	  	<p class="L_Btn"><span><a href="#" onclick="doSubmit()">确认更新</a></span></p><br>
	  	<p class="L_Btn"><span><a href="javascript:history.go(-1)">返回</a></span></p>
	  	</div>
	    <div class="b_02"></div>
	    <div class="b_03"></div>
        <div class="b_04"></div>
	    <div class="b_05"></div>
	</div>
	</form>
  </body>
</html>