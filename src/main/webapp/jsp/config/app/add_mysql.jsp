<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-semantic.jsp"%>
<title>数据库</title>
<script type="text/javascript">
$(document).ready(function(){
	$('.ui.form').form(
		{
			url:{
				identifier:'url',
				rules:[{type:'empty',prompt:'name can not empty'},
				       {type:'length[2]',prompt:'name at least 2 characters'}]
			},
			port:{
				identifier:'port',
				rules:[{type:'empty',prompt:'code can not empty'},
				       {type:'length[2]',prompt:'code at least 2 characters'}]
			},
			loginName:{
				identifier:'loginName',
				rules:[{type:'empty',prompt:'code can not empty'},
				       {type:'length[2]',prompt:'code at least 2 characters'}]
			},
			password:{
				identifier:'password',
				rules:[{type:'empty',prompt:'code can not empty'},
				       {type:'length[2]',prompt:'code at least 2 characters'}]
			}
		},
		{inline : true,on : 'blur',onSuccess:function(){frm.submit();}});
});
</script>
</head>
<body>
<div class="ui form segment container">
<form id="frm" action="${basePath }/app/addMysql.do" method="POST">
<div class="three fields">
<div class="field">
<input title="please input url" type="text" name="url" placeholder="url">
</div>
<div class="field">
<input title="please input port" type="text" name="port" value="3306" placeholder="port">
</div>
<div class="field">
<input title="please input database" type="text" name="database" placeholder="database">
</div>
</div>
<div class="two fields">
<div class="field">
<input title="please input loginName" type="text" name="loginName" placeholder="loginName">
</div>
<div class="field">
<input title="please input password" type="password" name="password" placeholder="password">
</div>
</div>
<div class="field">
<div class="ui submit blue button" >Save</div>
<div onclick="history.go(-1)" class="ui grey button">back</div>
</div>
</form>
</div>
</body>
</html>