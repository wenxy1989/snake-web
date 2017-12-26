<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-semantic.jsp"%>
<title>unique - edit</title>
<script type="text/javascript">
	function checkForm(){
		var validator = new Validator("frm");
		var form = $("#frm");
		if(validator.required("name")){
			form.find("[name='name']").next("div").addClass("hidden");
		}else{
			form.find("[name='name']").next("div").removeClass("hidden");
		}
		validator.isPass() && $("#frm").submit();
	}
</script>
</head> 
<body>
<div class="ui form segment container">
<form id="frm" action="${basePath }/uniq/create.do" method="POST">
<input type="hidden" name="objId" value="${obj.id }">
<div class="field">
<label>Attribute name</label>
<input title="please input name" type="text" name="name" placeholder="name">
<div class="ui pointing above red label hidden">name can't been null</div>
</div>
<div class="field">
<label>Attribute content</label>
<input title="please input content" type="text" name="content" placeholder="content">
</div>
<div class="field">
<div onclick="checkForm()" class="ui blue button">Create</div>
<div onclick="history.go(-1)" class="ui grey button">back</div>
</div>
</form>
</div>
</body>
</html>