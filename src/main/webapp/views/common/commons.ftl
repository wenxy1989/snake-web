<#assign myMethod = "com.snake.system.freemarker.SystemMethodModelEx"?new()>
<#assign functionList = myMethod("functionList")>
<#macro security url="/" id=0>
<#if url != "/">
    <#if myMethod("check_security",url,USER_SESSION.role.code)>
        <#nested>
    </#if>
<#elseif id != 0>
    <#if myMethod("check_security_id",id,USER_SESSION.role.id)>
        <#nested>
    </#if>
</#if>
</#macro>
<#macro menu>
    <#list functionList?sort_by("order") as function>
    <@security id=function.id>
    <#if function.type == 1>
    <li<#if function.children?? && function.children?size gt 0> class="am-dropdown" data-am-dropdown</#if>>
        <a href="${request.contextPath}${function.url}"<#if function.children??> data-am-dropdown-toggle class="am-dropdown-toggle"</#if>>${function.name}</a>
        <#if function.children?? && function.children?size gt 0>
            <ul class="am-dropdown-content">
                <#list function.children?sort_by("order") as child>
                    <@security url=child.url>
                    <#if child.type == 1>
                    <li>
                        <a href="${request.contextPath}${child.url}" class="am-cf">${child.name}</a>
                        <#if child.children??>
                            <ul class="am-list am-collapse admin-sidebar-sub am-in">
                                <#list child.children as grandChild>
                                    <@security url=grandChild.url>
                                    <#if grandChild.type == 1>
                                    <li>
                                        <a href="${request.contextPath}${grandChild.url}" class="am-cf">
                                            <span class="am-icon-check"></span>${grandChild.name}<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                                        </a>
                                    </li>
                                    </#if>
                                    </@security>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                    </#if>
                    </@security>
                </#list>
            </ul>
        </#if>
    </li>
    </#if>
    </@security>
    </#list>
</#macro>