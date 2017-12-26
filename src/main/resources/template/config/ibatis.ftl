<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.${application}.${module}.model.${module?cap_first}">

	<resultMap type="com.${application}.${module}.model.${module?cap_first}" id="${module}ResultMap">
  		<result property="id" column="id_"/>
		<#list attributes as attribute>
  		<result property="${attribute.code}" column="${attribute.column}"/>
  		</#list>
  		<result property="createdTime" column="created_time"/>
  		<result property="creatorId" column="creator_id"/>
	</resultMap>

    <select id="selectAll" resultMap="${module}ResultMap">
        select * from ${obj.table}
    </select>

    <select id="selectSome" parameterType="java.util.Map" resultMap="${module}ResultMap">
        select * from ${obj.table} limit #${"{"}offset},#${"{"}limit}
    </select>

    <insert id="insert" parameterType="com.${application}.${module}.model.${module?cap_first}">
        INSERT INTO ${obj.table}(
     <#list attributes as attribute>
        ${attribute.column},
     </#list>
        created_time,
        creator_id)
        VALUES(
    <#list attributes as attribute>
        #${"{"}${attribute.code},jdbcType=${attribute.dataType}},
    </#list>
        #${"{"}createdTime,jdbcType=VARCHAR},
        #${"{"}creatorId,jdbcType=BIGINT}
        )
    </insert>

    <update id="update" parameterType="com.${application}.${module}.model.${module?cap_first}">
        update ${obj.table}
        <set>
        <#list attributes as attribute>
            <if test="${attribute.code} != null">
            ${attribute.column} = #${"{"}${attribute.code}},
            </if>
        </#list>
            <if test="createdTime != null">
            created_time = #${"{"}createdTime},
            </if>
            <if test="creatorId != null">
            creator_id = #${"{"}creatorId}
            </if>
        </set>
        where id_=#${"{"}id}
    </update>

    <select id="findOneByMap" resultMap="${module}ResultMap">
        select * from ${obj.table} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} = #${"{"}param.value}
        </foreach>
        limit 0, 1
    </select>

    <select id="findOneByObject" parameterType="com.${application}.${module}.model.${module?cap_first}" resultMap="${module}ResultMap">
        select * from ${obj.table}
        <where>
            <if test="id != null">
                and id_=#${"{"}id}
            </if>
            <#list attributes as attribute>
            <if test="${attribute.code} != null">
                and ${attribute.column}=#${"{"}${attribute.code}}
            </if>
            </#list>
            <if test="createdTime != null">
                and created_time=#${"{"}createdTime}
            </if>
            <if test="creatorId != null">
                and creator_id=#${"{"}creatorId}
            </if>
        </where>
        limit 0, 1
    </select>

    <select id="findByMap" resultMap="${module}ResultMap">
        select * from  ${obj.table} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} = #${"{"}param.value}
        </foreach>
    </select>

    <select id="findByObject" parameterType="com.${application}.${module}.model.${module?cap_first}" resultMap="${module}ResultMap">
        select * from ${obj.table}
        <where>
        <#list attributes as attribute>
            <if test="${attribute.code} != null">
                and ${attribute.column}=#${"{"}${attribute.code}}
            </if>
        </#list>
            <if test="createdTime != null">
                and created_time=#${"{"}createdTime}
            </if>
            <if test="creatorId != null">
                and creator_id=#${"{"}creatorId}
            </if>
        </where>
    </select>

    <select id="findByIn" resultMap="${module}ResultMap">
        select * from ${obj.table} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} IN (#${"{"}param.value})
        </foreach>
    </select>

    <select id="findByLike" resultMap="${module}ResultMap">
        select * from ${obj.table} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} LIKE #${"{"}param.value}
        </foreach>
    </select>

    <select id="getTotalCount" resultType="int">
        select count(1) from ${obj.table}
    </select>

    <select id="getCount" parameterType="java.util.Map" resultType="int">
        select count(1) from ${obj.table} where 1=1
        <if test="whereClause != null">
        ${"$"}${"{"}whereClause}
        </if>
    </select>

    <select id="query" parameterType="java.util.Map" resultMap="${module}ResultMap">
        select ${"$"}${"{"}fieldsClause} from ${obj.table} where 1=1
        <if test="whereClause != null">
        ${"$"}${"{"}whereClause}
        </if>
        <if test="orderClause != null">
        ${"$"}${"{"}orderClause}
        </if>
        limit ${"$"}${"{"}limit} offset ${"$"}${"{"}offset}
    </select>

	<#list attributes as attribute>
	<#if attribute.useType == 'onetoone'>
    <select id="getObjectBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}" resultMap="${module}ResultMap">
        select * from ${obj.table} where ${attribute.column}=${"#"}{${attribute.code?uncap_first}}
    </select>
    
    <delete id="deleteBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}">
        delete from ${obj.table} where ${attribute.column}=${"#"}{${attribute.code?uncap_first}}
    </delete>
    <#elseif attribute.useType == 'onetomany'>
    <select id="getListBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}" resultMap="${module}ResultMap">
        select * from ${obj.table} where ${attribute.column}=${"#"}{${attribute.code?uncap_first}}
    </select>
    
    <delete id="deleteBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}">
        delete from ${obj.table} where ${attribute.column}=${"#"}{${attribute.code?uncap_first}}
    </delete>
	</#if>
	</#list>

</mapper>
