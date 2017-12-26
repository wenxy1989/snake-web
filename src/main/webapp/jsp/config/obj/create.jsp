<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-semantic.jsp"%>
<title>object - create</title>
<script type="text/javascript">
$(document).ready(function(){
	$('.ui.form').form(
		{
			name:{
				identifier:'name',
				rules:[{type:'empty',prompt:'name can not empty'},
				       {type:'length[2]',prompt:'name at least 2 characters'},
				       {type:'maxLength[25]',prompt:'name mostly 25 characters'}]
			},
			code:{
				identifier:'code',
				rules:[{type:'empty',prompt:'code can not empty'},
				       {type:'length[2]',prompt:'code at least 2 characters'},
				       {type:'maxLength[25]',prompt:'code mostly 25 characters'}]
			},
			database:{
				identifier:'table',
				rules:[{type:'maxLength[25]',prompt:'table mostly 25 characters'}]
			},
			title:{
				identifier:'title',
				rules:[{type:'maxLength[500]',prompt:'title mostly 500 characters'}]
			}
		},
		{inline : true,on : 'blur',onSuccess:function(){frm.submit();}});
});
</script>
</head> 
<body>
<div class="ui form segment container">
<form id="frm" action="${basePath }/obj/create.do" method="POST">
<input type="hidden" name="applicationId" value="${app.id }">
<div class="field">
<label>Name</label>
<input title="please input name" type="text" name="name" placeholder="name">
</div>
<div class="two fields">
<div class="field">
<label>Code</label>
<input title="please input code" type="text" name="code" placeholder="code">
</div>
<div class="field">
<label>Table</label>
<input title="please input table" type="text" name="table" placeholder="table">
</div>
</div>
<div class="field">
<label>Title</label>
<textarea title="please input title" name="title" placeholder="title"></textarea>
</div>
<div class="field">
<div class="ui blue button submit">Submit</div>
<div onclick="history.go(-1)" class="ui grey button">back</div>
</div>
</form>
</div>
</body>
</html>