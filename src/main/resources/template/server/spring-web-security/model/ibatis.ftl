<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.web.${app.code}.entity.${model.javaName?cap_first}">

    <resultMap type="com.web.${app.code}.entity.${model.javaName?cap_first}" id="${model.javaName}ResultMap">
        <result property="id" column="id_"/>
        <#list parameters as p>
        <result property="${p.code}" column="${p.code}"/>
        </#list>
        <result property="updatedTime" column="updated_time"/>
        <result property="createdTime" column="created_time"/>
        <result property="creatorId" column="creator_id"/>
    </resultMap>

    <insert id="insert" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}">
        INSERT INTO ${model.javaName}
        (
        <#list parameters as p>
        ${p.code},
        </#list>
        updated_time,
        created_time,
        creator_id
        )
        VALUES
        (
        <#list parameters as p>
        #${"{"}${p.code},jdbcType=${p.columnType?upper_case}},
        </#list>
        now(),
        now(),
        #${"{"}creatorId,jdbcType=BIGINT}
        )
    </insert>

    <update id="update" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}">
        update ${model.javaName}
        <set>
            <#list parameters as p>
                <if test="${p.code} != null">
                    ${p.code} = #${"{"}${p.code}},
                </if>
            </#list>
            updated_time = now()
        </set>
        where id_=#${"{"}id}
    </update>

    <delete id="delete" parameterType="java.lang.Long">
        delete from ${model.javaName} where id_=#${"{"}value},
    </delete>

    <select id="selectOne" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}" resultMap="${model.javaName}ResultMap">
        select * from ${model.javaName}
        <where>
        <#list parameters as p>
            <if test="${p.code} != null">
                and ${p.code} = #${"{"}${p.code}},
            </if>
        </#list>
        </where>
        limit 0, 1
    </select>

    <select id="selectList" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}"
            resultMap="${model.javaName}ResultMap">
        select * from ${model.javaName}
        <where>
            <#list parameters as p>
                <if test="${p.code} != null">
                    and ${p.code} = #${"{"}${p.code}},
                </if>
            </#list>
        </where>
    </select>

    <select id="selectCount" parameterType="com.web.${app.code}.entity.${model.javaName?cap_first}" resultType="java.lang.Integer">
        select count(1) from ${model.javaName}
        <where>
            <#list parameters as p>
                <if test="${p.code} != null">
                    and ${p.code} = #${"{"}${p.code}},
                </if>
            </#list>
        </where>
    </select>

    <select id="selectListByPage" parameterType="com.web.${app.code}.config.Page" resultMap="${model.javaName}ResultMap">
        select * from ${model.javaName}
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
        select count(1) from ${model.javaName}
        <if test="where != null">
            where $${"{"}where}
        </if>
        <if test="order != null">
            order $${"{"}order}
        </if>
    </select>

    <#--<#list parameters as p>
    <#if p.useType == 'onetoone'>
    <select id="getObjectBy${p.code?cap_first}" parameterType="java.lang.${p.javaType}" resultMap="${model.javaName}ResultMap">
        select * from ${model.javaName} where ${p.code}=${"#"}{${p.code?uncap_first}}
    </select>

    <delete id="deleteBy${p.code?cap_first}" parameterType="java.lang.${p.javaType}">
        delete from ${model.javaName} where ${p.code}=${"#"}{${p.code?uncap_first}}
    </delete>
    <#elseif p.useType == 'onetomany'>
    <select id="getListBy${p.code?cap_first}" parameterType="java.lang.${p.javaType}" resultMap="${model.javaName}ResultMap">
        select * from ${model.javaName} where ${p.code}=${"#"}{${p.code?uncap_first}}
    </select>

    <delete id="deleteBy${p.code?cap_first}" parameterType="java.lang.${p.javaType}">
        delete from ${model.javaName} where ${p.code}=${"#"}{${p.code?uncap_first}}
    </delete>
    </#if>
    </#list>-->

</mapper>
