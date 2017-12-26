<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>新建角色</title>
	<script type="text/javascript">
	function doSubmit(){
		if($("input[name='name']").val().trim()==""){
			alert("人物名称不能为空！");
			return;
		}
		if($("textarea[name='title']").val().trim()==""){
			alert("标题（标志）不能为空！");
			return;
		}
		$("#frm").submit();	
	}
	</script>
  </head> 
  <body>
	<form id="frm" action="${basePath }/game/create_character.do" method="POST">
	<input type="hidden" name="novalId" value="${object.novalId }">
	<div class="i_c">
		<div class="b_05"></div>
        <div class="b_04"></div>
	    <div class="b_03"></div>
        <div class="b_02"></div>
	    <div class="login">
	  	<p><input title="请填写人物名称" type="text" name="name" class="l_input" value="请填写人物名称"></p>
	  	<p><textarea title="请填写标注" name="title" class="l_text" rows="5">${object.title }</textarea></p>
	  	<p>
	  		<select name="raceId">
	  			<option value="">-- 请选择 --</option>
		  		<c:forEach items="${races }" var="race">
	  			<option value="${race.id }">${race.name }</option>
		  		</c:forEach>
	  		</select>
	  	</p>
	  	<p>
	  		<select name="familyId">
	  			<option value="">-- 请选择 --</option>
		  		<c:forEach items="${familys }" var="family">
	  			<option value="${family.id }">${family.name }</option>
		  		</c:forEach>
	  		</select>
	  	</p>
	  	<p class="L_Btn"><span><a href="#" onclick="doSubmit()">添加</a></span></p><br>
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