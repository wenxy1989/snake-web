<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- sql mapper ${model.javaName?uncap_first} version.${now} -->
<mapper namespace="com.school.book.model.${model.javaName?cap_first}">

    <resultMap type="com.school.book.model.${model.javaName?cap_first}" id="resultMap">
        <result property="id" column="id_"/>
    <#list parameters as p>
        <result property="${p.code?uncap_first}" column="${p.code?uncap_first}_"/>
    </#list>
        <result property="deleted" column="deleted_"/>
        <result property="createUserId" column="create_user_id"/>
        <result property="createUser" column="create_user"/>
        <result property="createTime" column="create_time"/>
        <result property="updateUserId" column="update_user_id"/>
        <result property="updateUser" column="update_user"/>
        <result property="updateTime" column="update_time"/>
        <result property="extendOne" column="extend_one"/>
        <result property="extendTwo" column="status_two"/>
        <result property="extendThree" column="status_three"/>
        <result property="extendFour" column="status_four"/>
    </resultMap>

    <insert id="insertObject" parameterType="com.school.book.model.${model.javaName?cap_first}">
        INSERT INTO table_${model.tableName}(
        <if test="id != null">
        id_,
        </if>
    <#list parameters as p>
        ${p.code?uncap_first}_,
    </#list>
        deleted_,
        create_user_id,
        create_user,
        create_time,
        update_user_id,
        update_user,
        update_time,
        extend_one,
        extend_two,
        extend_three,
        extend_four
        )
        VALUES(
        <if test="id != null">
        ${"#"}{id},
        </if>
        <#list parameters as p>
        ${"#"}{${p.code?uncap_first}},
        </#list>
        ${"#"}{deleted},
        ${"#"}{createUserId},
        ${"#"}{createUser},
        now(),
        ${"#"}{updateUserId},
        ${"#"}{updateUser},
        now(),
        ${"#"}{extendOne},
        ${"#"}{extendTwo},
        ${"#"}{extendThree},
        ${"#"}{extendFour}
        )
        <selectKey resultType="java.lang.Long" order="AFTER" keyProperty="id">
            select last_insert_id() as id
        </selectKey>
    </insert>

    <insert id="insertList" useGeneratedKeys="true" parameterType="java.util.List">
        INSERT INTO table_${model.tableName}(
        id_,
    <#list parameters as p>
        ${p.code?uncap_first}_,
    </#list>
        deleted_,
        create_user_id,
        create_user,
        create_time,
        update_user_id,
        update_user,
        update_time,
        extend_one,
        extend_two,
        extend_three,
        extend_four
        )
        VALUES
        <foreach collection="list" item="each" index="index" separator=",">
            (
            ${"#"}{each.id},
            <#list parameters as p>
            ${"#"}{each.${p.code?uncap_first}},
            </#list>
            ${"#"}{each.deleted},
            ${"#"}{each.createUserId},
            ${"#"}{each.createUser},
            now(),
            ${"#"}{each.updateUserId},
            ${"#"}{each.updateUser},
            now(),
            ${"#"}{each.extendOne},
            ${"#"}{each.extendTwo},
            ${"#"}{each.extendThree},
            ${"#"}{each.extendFour}
            )
        </foreach>
    </insert>

    <delete id="clean">
        TRUNCATE TABLE table_${model.tableName}
    </delete>

    <delete id="deleteById" parameterType="java.lang.Object">
        delete from table_${model.tableName} where id_ = ${"#"}{id}
    </delete>

    <update id="updateById" parameterType="com.school.book.model.${model.javaName?cap_first}">
        update table_${model.tableName}
        <set>
        <#list parameters as p>
            <if test="${p.code?uncap_first} != null">
                ${p.code?uncap_first}_ = ${"#"}{${p.code?uncap_first}},
            </if>
        </#list>
            <if test="deleted != null">
                deleted_ = ${"#"}{deleted},
            </if>
            <if test="updateUserId != null">
                update_user_id = ${"#"}{updateUserId},
            </if>
            <if test="updateUser != null">
                update_user = ${"#"}{updateUser},
            </if>
            <if test="extendOne != null">
                extend_one = ${"#"}{extendOne},
            </if>
            <if test="extendTwo != null">
                extend_two = ${"#"}{extendTwo},
            </if>
            <if test="extendThree != null">
                extend_three = ${"#"}{extendThree},
            </if>
            <if test="extendFour != null">
                extend_four = ${"#"}{extendFour},
            </if>
            update_time = now()
        </set>
        where id_=${"#"}{id}
    </update>

    <select id="selectObject" resultMap="resultMap" parameterType="java.lang.Object">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list> deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName} where id_ = ${"#"}{id}
    </select>

    <select id="selectOneByObject" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
        where 1=1
        <if test="id != null">
            and id_ = ${"#"}{id}
        </if>
        <#list parameters as p>
        <if test="${p.code?uncap_first} != null">
            and ${p.code?uncap_first}_ = ${"#"}{${p.code?uncap_first}}
        </if>
        </#list>
        <if test="deleted != null">
            and deleted_ = ${"#"}{deleted}
        </if>
        <if test="createUserId != null">
            and create_user_id = ${"#"}{createUserId}
        </if>
        <if test="createUser != null">
            and create_user = ${"#"}{createUser}
        </if>
        <if test="createTime != null">
            and create_time = ${"#"}{createTime}
        </if>
        <if test="updateUserId != null">
            and update_user_id = ${"#"}{updateUserId}
        </if>
        <if test="updateUser != null">
            and update_user = ${"#"}{updateUser}
        </if>
        <if test="updateTime != null">
            and update_time = ${"#"}{updateTime}
        </if>
        <if test="extendOne != null">
            and extend_one = ${"#"}{extendOne}
        </if>
        <if test="extendTwo != null">
            and extend_two = ${"#"}{extendTwo}
        </if>
        <if test="extendThree != null">
            and extend_three = ${"#"}{extendThree}
        </if>
        <if test="extendFour != null">
            and extend_four = ${"#"}{extendFour}
        </if>
        limit 0,1
    </select>

    <select id="selectOneByMap" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
        where 1=1 and
        <foreach item="param" index="key" collection="list" open="" separator="AND" close="">
        ${"$"}{param.name} = ${"#"}{param.value}
        </foreach>
        limit 0, 1
    </select>

    <select id="selectListByObject" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
        where 1=1
        <if test="id != null">
            and id_ = ${"#"}{id}
        </if>
    <#list parameters as p>
        <if test="${p.code?uncap_first} != null">
            and ${p.code?uncap_first}_ = ${"#"}{${p.code?uncap_first}}
        </if>
    </#list>
        <if test="deleted != null">
            and deleted_ = ${"#"}{deleted}
        </if>
        <if test="createUserId != null">
            and create_user_id = ${"#"}{createUserId}
        </if>
        <if test="createUser != null">
            and create_user = ${"#"}{createUser}
        </if>
        <if test="createTime != null">
            and create_time = ${"#"}{createTime}
        </if>
        <if test="updateUserId != null">
            and update_user_id = ${"#"}{updateUserId}
        </if>
        <if test="updateUser != null">
            and update_user = ${"#"}{updateUser}
        </if>
        <if test="updateTime != null">
            and update_time = ${"#"}{updateTime}
        </if>
        <if test="extendOne != null">
            and extend_one = ${"#"}{extendOne}
        </if>
        <if test="extendTwo != null">
            and extend_two = ${"#"}{extendTwo}
        </if>
        <if test="extendThree != null">
            and extend_three = ${"#"}{extendThree}
        </if>
        <if test="extendFour != null">
            and extend_four = ${"#"}{extendFour}
        </if>
    </select>

    <select id="selectListByMap" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
        where 1=1 and
        <foreach item="param" index="key" collection="list" open="" separator="AND" close="">
        ${"$"}{param.name} = ${"#"}{param.value}
        </foreach>
    </select>

    <select id="selectListByIn" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
        where 1=1 and
        <foreach item="param" index="key" collection="list" open="" separator="AND" close="">
        ${"$"}{param.name} IN (${"#"}{param.value})
        </foreach>
    </select>

    <select id="selectListByLike" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
        where 1=1 and
        <foreach item="param" index="key" collection="list" open="" separator="AND" close="">
        ${"$"}{param.name} LIKE ${"#"}{param.value}
        </foreach>
    </select>

    <select id="selectAll" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
    </select>

    <select id="selectTotalCount" resultType="int">
        select count(1) from table_${model.tableName}
    </select>

    <select id="selectSome" parameterType="java.util.Map" resultMap="resultMap">
        select id_,<#list parameters as p>${p.code?uncap_first}_,</#list>deleted_,create_user,create_time,update_user,update_time,extend_one,extend_two,extend_three,extend_four
        from table_${model.tableName}
        <if test="null != limit and limit > 0">
            limit ${"$"}{limit} offset ${"$"}{offset}
        </if>
    </select>

    <select id="selectCountByCriteria" parameterType="java.util.Map" resultType="int">
        select count(1) from table_${model.tableName} where 1=1
        <if test="whereClause != null">
        ${"$"}{whereClause}
        </if>
    </select>

    <select id="selectListByCriteria" parameterType="java.util.Map" resultMap="resultMap">
        select ${"$"}{fieldsClause} from table_${model.tableName} where 1=1
        <if test="whereClause != null">
        ${"$"}{whereClause}
        </if>
        <if test="orderClause != null">
        ${"$"}{orderClause}
        </if>
        <if test="null != limit and limit > 0">
            limit ${"$"}{limit} offset ${"$"}{offset}
        </if>
    </select>

    <select id="selectMapCount" parameterType="java.util.Map" resultType="java.lang.Integer">
        select count(0) count_ from table_${model.tableName} where 1=1
        <if test="id != null">
            and id_ = ${"#"}{id}
        </if>
    <#list parameters as p>
        <if test="${p.code?uncap_first} != null">
            and ${p.code?uncap_first}_ = ${"#"}{${p.code?uncap_first}}
        </if>
    </#list>
        <if test="deleted != null">
            and deleted_ = ${"#"}{deleted}
        </if>
        <if test="createUserId != null">
            and create_user_id = ${"#"}{createUserId}
        </if>
        <if test="createUser != null">
            and create_user = ${"#"}{createUser}
        </if>
        <if test="createTime != null">
            and create_time = ${"#"}{createTime}
        </if>
        <if test="updateUserId != null">
            and update_user_id = ${"#"}{updateUserId}
        </if>
        <if test="updateUser != null">
            and update_user = ${"#"}{updateUser}
        </if>
        <if test="updateTime != null">
            and update_time = ${"#"}{updateTime}
        </if>
        <if test="extendOne != null">
            and extend_one = ${"#"}{extendOne}
        </if>
        <if test="extendTwo != null">
            and extend_two = ${"#"}{extendTwo}
        </if>
        <if test="extendThree != null">
            and extend_three = ${"#"}{extendThree}
        </if>
        <if test="extendFour != null">
            and extend_four = ${"#"}{extendFour}
        </if>
    </select>

    <select id="selectMapList" parameterType="java.util.Map" resultType="java.util.Map">
        select id_ as id,<#list parameters as p>${p.code?uncap_first}_ as ${p.code?uncap_first},</#list>deleted_ as deleted,create_user as createUser,create_time as createTime,update_user as updateUser,update_time as updateTime,extend_one as extendOne,extend_two as extendTwo,extend_three as extendThree,extend_four as extendFour
        from table_${model.tableName}
        where 1=1
        <if test="id != null">
            and id_ = ${"#"}{id}
        </if>
    <#list parameters as p>
        <if test="${p.code?uncap_first} != null">
            and ${p.code?uncap_first}_ = ${"#"}{${p.code?uncap_first}}
        </if>
    </#list>
        <if test="deleted != null">
            and deleted_ = ${"#"}{deleted}
        </if>
        <if test="createUserId != null">
            and create_user_id = ${"#"}{createUserId}
        </if>
        <if test="createUser != null">
            and create_user = ${"#"}{createUser}
        </if>
        <if test="createTime != null">
            and create_time = ${"#"}{createTime}
        </if>
        <if test="updateUserId != null">
            and update_user_id = ${"#"}{updateUserId}
        </if>
        <if test="updateUser != null">
            and update_user = ${"#"}{updateUser}
        </if>
        <if test="updateTime != null">
            and update_time = ${"#"}{updateTime}
        </if>
        <if test="extendOne != null">
            and extend_one = ${"#"}{extendOne}
        </if>
        <if test="extendTwo != null">
            and extend_two = ${"#"}{extendTwo}
        </if>
        <if test="extendThree != null">
            and extend_three = ${"#"}{extendThree}
        </if>
        <if test="extendFour != null">
            and extend_four = ${"#"}{extendFour}
        </if>
        <if test="orderClause != null">
        ${"$"}{orderClause}
        </if>
        <if test="null != limit and limit>0 and null != offset">
            limit ${"$"}{limit} offset ${"$"}{offset}
        </if>
    </select>

</mapper>