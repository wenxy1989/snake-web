<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head-semantic.jsp"%>
<title>attribute - edit</title>
<script type="text/javascript">
	$(document).ready(function(){
		$('.ui.dropdown').dropdown();
		$('#div_java_type.ui.dropdown').dropdown({onChange:function(value,text){changeJava(value);}});
		$('.ui.checkbox').checkbox();
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
				column:{
					identifier:'column',
					rules:[{type:'maxLength[25]',prompt:'column mostly 25 characters'}]
				},
				javaType:{
					identifier:'javaType',
					rules:[{type:'empty',prompt:'javaType can not empty'}]
				},
				dataType:{
					identifier:'dataType',
					rules:[{type:'maxLength[25]',prompt:'dataType mostly 25 characters'}]
				},
				title:{
					identifier:'title',
					rules:[{type:'maxLength[500]',prompt:'title mostly 500 characters'}]
				}
			},
			{inline : true,on : 'blur',onSuccess:function(){frm.submit();}}
		);
	});
	var javaConfig = {
			"Long":{"mysql":"bigint"},
			"String":{"mysql":"varchar","length":25},
			"Double":{"mysql":"double"},
			"Float":{"mysql":"float"},
			"Integer":{"mysql":"integer"},
			"Character":{"mysql":"char","length":1},
			"Byte":{"mysql":"byte","length":1},
			"Boolean":{"mysql":"boolean"}
			};
	function changeJava(java){
		$("input[name='dataType']").val(javaConfig[java].mysql);
		$("input[name='dataLength']").val(javaConfig[java].length);
	}
</script>
</head> 
<body>
<div class="ui form segment container">
<form id="frm" action="${basePath }/attr/create.do" method="POST">
<input type="hidden" name="objId" value="${obj.id }">
<div class="field">
<input title="please input name" type="text" name="name" placeholder="name" value="${object.name }">
</div>
<div class="two fields">
<div class="field">
<input title="please input code" type="text" name="code" placeholder="code" value="${object.code }">
</div>
<div class="field">
<input title="please input column name" type="text" name="column" placeholder="column name" value="${object.column }">
</div>
</div>
<div class="four fields">
<div class="field">
<div id="div_java_type" class="ui selection dropdown">
  <input type="hidden" name="javaType" value="Long">
  <div class="text" data-value="Long"></div>
  <i class="dropdown icon"></i>
  <div class="menu">
      <div class="item" data-value="">--select--</div>
      <div class="item" data-value="Long">Long</div>
      <div class="item" data-value="String">String</div>
      <div class="item" data-value="Double">Double</div>
      <div class="item" data-value="Float">Float</div>
      <div class="item" data-value="Integer">Integer</div>
      <div class="item" data-value="Character">Character</div>
      <div class="item" data-value="Byte">Byte</div>
      <div class="item" data-value="Boolean">Boolean</div>
  </div>
</div>
</div>
<div class="field">
<input title="please input data length" type="text" name="dataLength" placeholder="object data max length" value="${object.dataLength }">
</div>
<div class="field">
<input title="please input data type" type="text" name="dataType" placeholder="object data type" value="${object.dataType }">
</div>
</div>
<div class="three fields">
<div class="field">
<div class="ui toggle checkbox">
<input type="checkbox" name="allowBlank" value="1" checked="checked">
<label>allowBlank</label>
</div>
</div>
<div class="field">
<div class="ui toggle checkbox">
<input type="checkbox" name="autoIncrement" value="1">
<label>autoIncrement</label>
</div>
</div>
<div class="field">
<div class="ui selection dropdown">
  <input type="hidden" name="useType" value="save">
  <div class="text" data-value="save"></div>
  <i class="dropdown icon"></i>
  <div class="menu">
      <div class="item" data-value="">--请选择--</div>
      <div class="item" data-value="save">存储</div>
      <div class="item" data-value="onetoone">一对一</div>
      <div class="item" data-value="onetomany">一对多</div>
      <div class="item" data-value="typecode">类型码</div>
  </div>
</div>
</div>
</div>
<div class="field">
<div class="ui selection dropdown">
  <input type="hidden" name="pageStyle" value="text">
  <div class="text" data-value="text"></div>
  <i class="dropdown icon"></i>
  <div class="menu">
      <div class="item" data-value="">--请选择--</div>
      <div class="item" data-value="text">文本</div>
      <div class="item" data-value="select">下拉框</div>
      <div class="item" data-value="checkbox">复选框</div>
      <div class="item" data-value="radio">单选框</div>
  </div>
</div>
</div>
<div class="field">
<textarea title="please input title"  name="title" placeholder="title">${object.title }</textarea>
</div>
<div class="field">
<div class="ui submit blue button" >Update</div>
<div onclick="history.go(-1)" class="ui grey button">back</div>
</div>
</form>
</div>
</body>
</html>