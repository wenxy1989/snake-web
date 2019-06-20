drop database if exists ${app.database};
create database ${app.database};
use ${app.database};
<#list app.objs as module>
drop table if exists ${model.tableName};
create table ${model.tableName}(
id_ bigint PRIMARY KEY AUTO_INCREMENT comment '主键',
<#list module.attrs as attribute>
${attribute.column} ${attribute.dataType}<#if attribute.dataLength??>(${attribute.dataLength})</#if> <#if attribute.autoIncrement?? && attribute.autoIncrement==1>PRIMARY KEY AUTO_INCREMENT </#if> <#if attribute.allowBlank?? && attribute.allowBlank==0>not null </#if><#if attribute.name??> comment'${attribute.name}'</#if>,
</#list>
created_time varchar(19) comment '创建时间',
creator_id bigint comment '创建人id'
<#if module.uniqs?? && module.uniqs?size gt 0>,
<#list module.uniqs as unque>
${unque.name} (${attribute.content})<#if unque_has_next>,</#if>
</#list>
</#if>
)engine=innodb charset=utf8 comment='${model.tableName}';
</#list>