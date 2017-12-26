<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/head-semantic.jsp"%>
<title>${obj.name} - edit</title>
<script type="text/javascript">
	${"$"}(document).ready(function(){
	<#list attributes as attribute>
	<#if attribute.pageStyle == "text">
	<#elseif attribute.pageStyle == "select">
		${"$"}('#div_${attribute.code?uncap_first}.ui.dropdown').dropdown({onChange:function(value,text){}});
	<#elseif attribute.pageStyle == "checkbox">
		${"$"}('#div_${attribute.code?uncap_first}.ui.checkbox').checkbox();
	<#elseif attribute.pageStyle == "radio">
		${"$"}('#div_${attribute.code?uncap_first}.ui.checkbox').radio();
	</#if>
	</#list>
		${"$"}('.ui.form').form(
			{
	<#list attributes as attribute>
			${attribute.code?uncap_first}:{
				identifier:'${attribute.code?uncap_first}',
				rules:[
	<#if attribute.allowBlank == 0>
					{type:'empty',prompt:'${attribute.code?uncap_first} can not empty'}<#if attribute.dataLength??>,</#if>
	</#if>
	<#if attribute.dataLength??>
					{type:'maxLength[${attribute.dataLength}]',prompt:'${attribute.code?uncap_first} mostly 25 characters'}
	</#if>
				]
			}<#if attribute_has_next>,</#if>
	</#list>
			},
			{inline : true,on : 'blur',onSuccess:function(){frm.submit();}});
	});
</script>
</head> 
<body>
<div class="ui form segment container">
<form id="frm" action="${"$"}{basePath }/${module?uncap_first}/edit" method="POST">
<input type="hidden" name="id" value="${"$"}{${module?uncap_first}.id }">
<#list attributes as attribute>
<div class="field">
<#if attribute.pageStyle == "text">
<label>${obj.name} ${attribute.name}</label>
<input title="please input ${attribute.code}" type="text" name="${attribute.code}" placeholder="${attribute.code}" value="${"$"}{${module}.${attribute.code} }">
<#elseif attribute.pageStyle == "select">
<div id="div_${attribute.code?uncap_first}" class="ui selection dropdown">
  <input type="hidden" name="${attribute.code?uncap_first}" value="${"$"}{${module?uncap_first}.${attribute.code?uncap_first} }">
  <div class="text" data-value="${"$"}{${module?uncap_first}.${attribute.code?uncap_first} }">${"$"}{${module?uncap_first}.${attribute.code?uncap_first} }</div>
  <i class="dropdown icon"></i>
  <div class="menu">
      <div class="item" data-value="">--select--</div>
  </div>
</div>
<#elseif attribute.pageStyle == "checkbox">
<div id="div_${attribute.code?uncap_first}" class="ui toggle checkbox">
<input type="checkbox" name="${attribute.code?uncap_first}" value="1"<c:if test="${"$"}{${module?uncap_first}.${attribute.code?uncap_first} == 1}"> checked="checked"</c:if>>
<label>${attribute.name}</label>
<#elseif attribute.pageStyle == "radio">
<div id="div_${attribute.code?uncap_first}" class="ui toggle radio">
<input type="checkbox" name="${attribute.code?uncap_first}" value="1"<c:if test="${"$"}{${module?uncap_first}.${attribute.code?uncap_first} == 1}"> checked="checked"</c:if>>
<label>${attribute.name}</label>
</#if>
</div>
</#list>
<div class="field">
<div class="ui submit blue button" >Update</div>
<div onclick="history.go(-1)" class="ui grey button">back</div>
</div>
</form>
</div>
</body>
</html>