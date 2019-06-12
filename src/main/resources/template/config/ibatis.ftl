<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.web.${application.code}.entity.${model.code?cap_first}">

	<resultMap type="com.web.${application.code}.entity.${model.code?cap_first}" id="${model.code}ResultMap">
  		<result property="id" column="id_"/>
		<#list parameters as attribute>
  		<result property="${attribute.code}" column="${attribute.code}"/>
  		</#list>
  		<result property="createdTime" column="created_time"/>
  		<result property="creatorId" column="creator_id"/>
	</resultMap>

    <select id="selectAll" resultMap="${model.code}ResultMap">
        select * from ${model.code}
    </select>

    <select id="selectSome" parameterType="java.util.Map" resultMap="${model.code}ResultMap">
        select * from ${model.code} limit #${"{"}offset},#${"{"}limit}
    </select>

    <insert id="insert" parameterType="com.web.${application.code}.entity${model.code?cap_first}">
        INSERT INTO ${model.code}(
     <#list parameters as attribute>
        ${attribute.code},
     </#list>
        created_time,
        creator_id)
        VALUES(
    <#list parameters as attribute>
        #${"{"}${attribute.code},jdbcType=${attribute.columnType}},
    </#list>
        #${"{"}createdTime,jdbcType=VARCHAR},
        #${"{"}creatorId,jdbcType=BIGINT}
        )
    </insert>

    <update id="update" parameterType="com.web.${application.code}.entity${model.code?cap_first}">
        update ${model.code}
        <set>
        <#list parameters as attribute>
            <if test="${attribute.code} != null">
            ${attribute.code} = #${"{"}${attribute.code}},
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

    <select id="findOneByMap" resultMap="${model.code}ResultMap">
        select * from ${model.code} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} = #${"{"}param.value}
        </foreach>
        limit 0, 1
    </select>

    <select id="findOneByObject" parameterType="com.web.${application.code}.entity${model.code?cap_first}" resultMap="${model.code}ResultMap">
        select * from ${model.code}
        <where>
            <if test="id != null">
                and id_=#${"{"}id}
            </if>
            <#list parameters as attribute>
            <if test="${attribute.code} != null">
                and ${attribute.code}=#${"{"}${attribute.code}}
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

    <select id="findByMap" resultMap="${model.code}ResultMap">
        select * from  ${model.code} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} = #${"{"}param.value}
        </foreach>
    </select>

    <select id="findByObject" parameterType="com.web.${application.code}.entity${model.code?cap_first}" resultMap="${model.code}ResultMap">
        select * from ${model.code}
        <where>
        <#list parameters as attribute>
            <if test="${attribute.code} != null">
                and ${attribute.code}=#${"{"}${attribute.code}}
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

    <select id="findByIn" resultMap="${model.code}ResultMap">
        select * from ${model.code} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} IN (#${"{"}param.value})
        </foreach>
    </select>

    <select id="findByLike" resultMap="${model.code}ResultMap">
        select * from ${model.code} where 1=1 AND
        <foreach item="param" index="key" collection="list"  open="" separator="AND" close="">
            ${"$"}${"{"}param.name} LIKE #${"{"}param.value}
        </foreach>
    </select>

    <select id="getTotalCount" resultType="int">
        select count(1) from ${model.code}
    </select>

    <select id="getCount" parameterType="java.util.Map" resultType="int">
        select count(1) from ${model.code} where 1=1
        <if test="whereClause != null">
        ${"$"}${"{"}whereClause}
        </if>
    </select>

    <select id="query" parameterType="java.util.Map" resultMap="${model.code}ResultMap">
        select ${"$"}${"{"}fieldsClause} from ${model.code} where 1=1
        <if test="whereClause != null">
        ${"$"}${"{"}whereClause}
        </if>
        <if test="orderClause != null">
        ${"$"}${"{"}orderClause}
        </if>
        limit ${"$"}${"{"}limit} offset ${"$"}${"{"}offset}
    </select>

	<#--<#list parameters as attribute>
	<#if attribute.useType == 'onetoone'>
    <select id="getObjectBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}" resultMap="${model.code}ResultMap">
        select * from ${model.code} where ${attribute.code}=${"#"}{${attribute.code?uncap_first}}
    </select>

    <delete id="deleteBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}">
        delete from ${model.code} where ${attribute.code}=${"#"}{${attribute.code?uncap_first}}
    </delete>
    <#elseif attribute.useType == 'onetomany'>
    <select id="getListBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}" resultMap="${model.code}ResultMap">
        select * from ${model.code} where ${attribute.code}=${"#"}{${attribute.code?uncap_first}}
    </select>

    <delete id="deleteBy${attribute.code?cap_first}" parameterType="java.lang.${attribute.javaType}">
        delete from ${model.code} where ${attribute.code}=${"#"}{${attribute.code?uncap_first}}
    </delete>
	</#if>
	</#list>-->

</mapper>
