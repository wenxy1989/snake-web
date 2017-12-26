<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>人物角色 - 修改</title>
	<script type="text/javascript">
	function doSubmit(){
		if($("input[name='name']").val().trim()==""){
			alert("人物名称不能为空！");
			return;
		}
		$("#frm").submit();	
	}
	</script>
  </head> 
  <body>
	<form id="frm" action="${basePath }/character/edit.do" method="POST">
	<input type="hidden" name="id" value="${object.id }">
	<input type="hidden" name="novelId" value="${object.novelId }">
	<input type="hidden" name="raceId" value="${object.raceId }">
	<input type="hidden" name="worldId" value="${object.worldId }">
	<input type="hidden" name="familyId" value="${object.familyId }">
	<input type="hidden" name="createId" value="${object.createId }">
	<input type="hidden" name="userId" value="${object.userId }">
	<input type="hidden" name="createTime" value="${object.createTime }">
	<input type="hidden" name="title" value="${object.title }">
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
	  	<p><input title="请填写名称" type="text" name="name" class="l_input" value="${object.name }"></p>
	  	<p><input title="生命力" type="text" name="live" class="l_input" value="${object.live }"></p>
	  	<p><input title="攻击力" type="text" name="atk" class="l_input" value="${object.atk }"></p>
	  	<p><input title="护甲" type="text" name="armor" class="l_input" value="${object.armor }"></p>
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