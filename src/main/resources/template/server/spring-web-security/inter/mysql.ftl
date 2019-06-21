<#macro tablename code>table_${code?lower_case}</#macro>
<#macro columnname code>${code?lower_case}_</#macro>
<#macro indexname code>index_${code?lower_case}</#macro>
/** ${model.name} **/
drop table if exists ${model.tableName};
create table ${model.tableName}(
id_ bigint(20) not null auto_increment,
<#list parameters as p>
<#if p.mysqlType??>
<#if p.type == "Double" || p.type == "Float">
<@columnname code=p.code?uncap_first/> ${p.mysqlType}<#if p.length??>(${p.length?string("########")},2)</#if><#if p.name??> comment '${p.name}'</#if>,
<#else>
<@columnname code=p.code?uncap_first/> ${p.mysqlType}<#if p.length??>(${p.length?string("########")})</#if><#if p.name??> comment '${p.name}'</#if>,
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
<#list parameters as p>
    <#if p.keyType??>
        <#if p.keyType == 1>
CREATE UNIQUE INDEX <@indexname code=p.code?lower_case/> ON ${model.tableName}(<@columnname code=p.code?uncap_first/>);
        <#elseif p.keyType == 2>
CREATE INDEX <@indexname code=p.code?lower_case/> ON ${model.tableName}(<@columnname code=p.code?uncap_first/>);
        </#if>
    </#if>
</#list>

