<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC	"-//Hibernate/Hibernate Mapping DTD 2.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>

	<class name="com.model.${model.codeCode}.${className}" table="template_${model.codeCode}">
		<id name="id" column="ID_">
			<generator class="increment"/>
		</id>
		<property name="name" column="NAME_"/>
		<#if attributes?exists>
		<#list attributes as attribute>
		<property name="${attribute.code?lower_case}" column="${attribute.code?upper_case}"/>
		</#list>
		</#if>
		<property name="title" column="TITLE_"/>
		<property name="createTime" column="CREATE_TIME"/>
		<property name="createId" column="CREATE_ID"/>
		<property name="status" column="STATUS_"/>
	</class>

</hibernate-mapping>