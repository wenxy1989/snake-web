<#macro tablename code>table_${code?lower_case}</#macro>
<#macro columnname code>${code?lower_case}_</#macro>
<#macro indexname code>index_${code?lower_case}</#macro>
/** ${model.name} **/
drop table if exists table_${model.code?lower_case};
create table <@tablename code=model.code/>(
id_ bigint(20) not null auto_increment,
<#list parameters as obj>
<#if obj.mysqlType??>
<#if obj.type == "Double" || obj.type == "Float">
<@columnname code=obj.code?uncap_first/> ${obj.mysqlType}<#if obj.length??>(${obj.length?string("########")},2)</#if><#if obj.name??> comment '${obj.name}'</#if>,
<#else>
<@columnname code=obj.code?uncap_first/> ${obj.mysqlType}<#if obj.length??>(${obj.length?string("########")})</#if><#if obj.name??> comment '${obj.name}'</#if>,
</#if>
</#if>
</#list>
deleted_ int(1),
create_user_id bigint,
create_user varchar(50),
create_time varchar(19),
update_user_id bigint,
update_user varchar(50),
update_time varchar(19),
extend_one int(1),
extend_two int(1),
extend_three int(1),
extend_four int(1),
primary key (id_)
)engine=innodb charset=utf8 comment='${model.name}';
<#list parameters as obj>
    <#if obj.keyType??>
        <#if obj.keyType == 1>
CREATE UNIQUE INDEX <@indexname code=obj.code?lower_case/> ON <@tablename code=model.code/>(<@columnname code=obj.code?uncap_first/>);
        <#elseif obj.keyType == 2>
CREATE INDEX <@indexname code=obj.code?lower_case/> ON <@tablename code=model.code/>(<@columnname code=obj.code?uncap_first/>);
        </#if>
    </#if>
</#list>

