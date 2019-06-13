<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.web.${app.code}.mapper.${model.code?cap_first}Mapper">

    <resultMap type="com.web.${app.code}.entity.${model.code?cap_first}" id="${model.code}ResultMap">
        <#list parameters as attribute>
        <result property="${attribute.code}" column="${attribute.code}"/>
        </#list>
    </resultMap>

    <insert id="insert" parameterType="com.web.${app.code}.entity.${model.code?cap_first}">
        INSERT INTO ${model.code}
        (
        <#list parameters as obj>
        ${obj.code}<#if obj_has_next>,</#if>
        </#list>
        )
        VALUES
        (
        <#list parameters as obj>
        #${"{"}${obj.code},jdbcType=${typeProperties("java-mybatis",obj.type)?upper_case}}<#if obj_has_next>,</#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="com.web.${app.code}.entity.${model.code?cap_first}">
        update ${model.code}
        <set>
        <#list parameters as attribute>
            <if test="${attribute.code} != null">
            ${attribute.code} = #${"{"}${attribute.code}},
            </if>
        </#list>
        </set>
        where id_=#${"{"}id}
    </update>

    <delete id="delete" parameterType="java.lang.Long">
        delete from ${model.code} where id_=#${"{"}value}
    </delete>

    <select id="selectOne" parameterType="com.web.${app.code}.entity.${model.code?cap_first}" resultMap="${model.code}ResultMap">
        select * from ${model.code}
        <where>
        <#list parameters as attribute>
            <if test="${attribute.code} != null">
                and ${attribute.code} = #${"{"}${attribute.code}},
            </if>
        </#list>
        </where>
        limit 0, 1
    </select>

    <select id="selectList" parameterType="com.web.${app.code}.entity.${model.code?cap_first}"
            resultMap="${model.code}ResultMap">
        select * from ${model.code}
        <where>
        <#list parameters as attribute>
            <if test="${attribute.code} != null">
                and ${attribute.code} = #${"{"}${attribute.code}},
            </if>
        </#list>
        </where>
    </select>

    <select id="selectCount" parameterType="com.web.${app.code}.entity.${model.code?cap_first}" resultType="java.lang.Integer">
        select count(1) from ${model.code}
        <where>
        <#list parameters as attribute>
            <if test="${attribute.code} != null">
                and ${attribute.code} = #${"{"}${attribute.code}},
            </if>
        </#list>
        </where>
    </select>

    <select id="selectListByPage" parameterType="com.web.${app.code}.config.Page" resultMap="${model.code}ResultMap">
        select * from ${model.code}
        <if test="where != null">
            where $${"{"}where}
        </if>
        <if test="order != null">
            order $${"{"}order}
        </if>
        <if test="limit > 0">
            limit $${"{"}limit} offset $${"{"}offset}
        </if>
    </select>

    <select id="selectCountByPage" parameterType="com.web.${app.code}.config.Page" resultType="java.lang.Integer">
        select count(1) from ${model.code}
        <if test="where != null">
            where $${"{"}where}
        </if>
        <if test="order != null">
            order $${"{"}order}
        </if>
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
