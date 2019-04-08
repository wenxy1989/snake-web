<#assign resourceObject = "com.snake.template.freemarker.TemplateMethodModelEx"?new()>
<#assign template_group_list = resourceObject("resource","template_group_list")>
<#macro groupName groupId=0>${resourceObject("get","template_group",groupId).name}</#macro>
<#macro groupModel groupId=0>${resourceObject("get","template_group",groupId).model?uncap_first}</#macro>

<#macro selectGroup name="groupId" value=0>
<div class="am-form-group">
    <label for="user-phone" class="am-u-sm-3 am-form-label">模板分组</label>
    <div class="am-u-sm-9">
        <select name="${name}" data-am-selected="{btnSize: 'sm'}">
            <option value="0"<#if value=0> selected</#if>>未选择</option>
            <#list template_group_list as each>
            <option value="${each.id}"<#if value=each.id> selected</#if>>${each.name}</option>
            </#list>
        </select>
        <small>模板分组</small>
    </div>
</div>
</#macro>