<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.web.${app.code}.mapper.${model.javaName?cap_first}Mapper">

    <resultMap type="com.web.${app.code}.entity.${model.javaName?cap_first}" id="${model.javaName}ResultMap">
        <#list parameters as p>
        <result property="${p.javaName}" column="${p.columnName}"/>
        </#list>
    </resultMap>

    <insert id="insert" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}">
        INSERT INTO ${model.tableName}
        (
        <#list parameters as p>
        ${p.columnName}<#if p_has_next>,</#if>
        </#list>
        )
        VALUES
        (
        <#list parameters as p>
        #${"{"}${p.javaName},jdbcType=${typeProperties("java-mybatis",p.type)?upper_case}}<#if p_has_next>,</#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}">
        update ${model.tableName}
        <set>
        <#list parameters as p>
            <if test="${p.javaName} != null">
            ${p.columnName} = #${"{"}${p.javaName}},
            </if>
        </#list>
        </set>
        where id_=#${"{"}id}
    </update>

    <delete id="delete" parameterType="java.lang.Long">
        delete from ${model.tableName} where id_=#${"{"}value}
    </delete>

    <sql id="whereByType">
        <where>
            <#list parameters as p>
                <if test="${p.javaName} != null">
                    and ${p.columnName} = #${"{"}${p.javaName}}
                </if>
            </#list>
        </where>
    </sql>

    <sql id="whereByParamType">
        <where>
            <if test="param != null">
                <#list parameters as p>
                    <if test="param.${p.javaName} != null">
                        and ${p.columnName} = #${"{param."}${p.javaName}}
                    </if>
                </#list>
            </if>
            <if test="where != null">
                and $${"{"}where}
            </if>
        </where>
    </sql>

    <select id="selectOne" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}" resultMap="${model.javaName}ResultMap">
        select * from ${model.tableName}
        <include refid="whereByType"/>
        limit 0, 1
    </select>

    <select id="selectList" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}"
            resultMap="${model.javaName}ResultMap">
        select * from ${model.tableName}
        <include refid="whereByType"/>
    </select>

    <select id="selectCount" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}" resultType="java.lang.Integer">
        select count(1) from ${model.tableName}
        <include refid="whereByType"/>
    </select>

    <select id="selectListByPage" parameterType="com.web.${app.code}.config.Page" resultMap="${model.javaName}ResultMap">
        select * from ${model.tableName}
        <include refid="whereByParamType"/>
        <if test="order != null">
            order $${"{"}order}
        </if>
        <if test="limit > 0">
            limit $${"{"}limit} offset $${"{"}offset}
        </if>
    </select>

    <select id="selectCountByPage" parameterType="com.web.${app.code}.config.Page" resultType="java.lang.Integer">
        select count(1) from ${model.tableName}
        <include refid="whereByParamType"/>
    </select>

    <#--<#list parameters as p>
    <#if attribute.useType == 'onetoone'>
    <select id="getObjectBy${p.javaName?cap_first}" parameterType="java.lang.${p.javaType}" resultMap="${model.javaName}ResultMap">
        select * from ${model.tableName} where ${p.columnName}=${"#"}{${p.javaName?uncap_first}}
    </select>

    <delete id="deleteBy${p.javaName?cap_first}" parameterType="java.lang.${p.javaType}">
        delete from ${model.tableName} where ${p.columnName}=${"#"}{${p.javaName?uncap_first}}
    </delete>
    <#elseif p.useType == 'onetomany'>
    <select id="getListBy${p.javaName?cap_first}" parameterType="java.lang.${p.javaType}" resultMap="${model.javaName}ResultMap">
        select * from ${model.tableName} where ${p.columnName}=${"#"}{${p.javaName?uncap_first}}
    </select>

    <delete id="deleteBy${p.javaName?cap_first}" parameterType="java.lang.${p.javaType}">
        delete from ${model.tableName} where ${p.columnName}=${"#"}{${p.javaName?uncap_first}}
    </delete>
    </#if>
    </#list>-->

</mapper>
