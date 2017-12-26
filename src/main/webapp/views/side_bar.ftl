<#assign MyJavaObject = "com.snake.system.freemarker.SystemMethodModelEx"?new()>
<#assign functionList = MyJavaObject("functionList")>
<#assign book_type_list = MyJavaObject("book_type_list")>
<#assign book_element_list = MyJavaObject("book_element_list")>
<#assign book_structure_list = MyJavaObject("book_structure_list")>
<#assign book_structure_list = MyJavaObject("book_word_type_list")>
<!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
            <#list functionList as function>
                <li>
                    <a href="${request.contextPath}${function.url}"><span class="am-icon-home"></span>${function.name}</a>
                    <#if function.children??>
                        <ul class="am-list am-collapse admin-sidebar-sub am-in">
                            <#list function.children as child>
                                <li>
                                    <a href="${request.contextPath}${child.url}" class="am-cf">
                                        <span class="am-icon-check"></span>${child.name}<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                                    </a>
                                    <#if child.children??>
                                        <ul class="am-list am-collapse admin-sidebar-sub am-in">
                                            <#list child.children as grandChild>
                                                <li>
                                                    <a href="${request.contextPath}${grandChild.url}" class="am-cf">
                                                        <span class="am-icon-check"></span>${grandChild.name}<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                                                    </a>
                                                </li>
                                            </#list>
                                        </ul>
                                    </#if>
                                </li>
                            </#list>
                        </ul>
                    </#if>
                </li>
            </#list>
            </ul>
        </div>
    </div>