<#assign resourceObject = "com.snake.template.freemarker.TemplateMethodModelEx"?new()>
<#assign template_frame_list = resourceObject("resource","template_frame_list")>
<#macro frameName frameId=0>${resourceObject("get","template_frame",frameId).name}</#macro>
<#macro frameModel frameId=0>${resourceObject("get","template_frame",frameId).model?uncap_first}</#macro>
<#macro modelReplace template="model">${resourceObject("replace_model",template,'Model')}</#macro>
<#macro selectFrame name="frameId" value=0>
<div class="am-form-group">
    <label for="user-phone" class="am-u-sm-3 am-form-label">模板分组</label>
    <div class="am-u-sm-9">
        <select name="${name}" data-am-selected="{btnSize: 'sm'}">
            <option value="0"<#if value=0> selected</#if>>未选择</option>
            <#list template_frame_list as each>
            <option value="${each.id}"<#if value=each.id> selected</#if>>${each.name}</option>
            </#list>
        </select>
        <small>生成代码框架</small>
    </div>
</div>
</#macro>