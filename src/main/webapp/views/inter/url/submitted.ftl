<#import "/inter/common.ftl" as i_com/>
<#macro parametersJson array type=1><#if type == 1>{<#list array as each>${each.exampleJsonString}<#if each_has_next>,</#if></#list>}<#elseif type == 2>[{<#list array as each>${each.exampleJsonString}<#if each_has_next>,</#if></#list>},{<#list array as each>${each.exampleJsonString}<#if each_has_next>,</#if></#list>}]<#elseif type == 3>${array[0].exampleValue}<#elseif type == 4>[${array[0].exampleValue},${array[0].exampleValue}]</#if></#macro>
<#macro Uri url>/inter/<@i_com.groupModel groupId=url.groupId/>/${url.url}</#macro>
<#macro interDoc url active=false>
<div class="am-tab-panel am-fade am-in<#if active> am-active</#if>" id="div_inter_${url.url}">
    <table class="am-table am-table-striped am-table-hover table-main">
        <tbody>
        <tr>
            <td class="table-main">接口名称</td>
            <td class="table-main">${url.name}</td>
        </tr>
        <tr>
            <td class="table-main">访问地址</td>
            <td class="table-main"><@Uri url=url/></td>
        </tr>
        <tr>
            <td class="table-main">请求方法</td>
            <td class="table-main"><#switch url.type><#case 0>GET<#break><#case 1>POST<#break></#switch></td>
        </tr>
        <tr>
            <td class="table-main">处理逻辑</td>
            <td class="table-main">${url.logic}</td>
        </tr>
        <tr>
            <td class="table-main">接口备注</td>
            <td class="table-main">${url.remark}</td>
        </tr>
        </tbody>
    </table>
    <#if url.uploadParameters?? && url.uploadParameters?size gt 0>
    <span>上传数据参数:</span>
    <table class="am-table am-table-striped am-table-hover table-main">
        <thead>
        <tr>
            <th class="table-main">名称</th>
            <th class="table-main">代码</th>
            <th class="table-main">类型</th>
            <th class="table-main">集合</th>
            <th class="table-main">长度</th>
            <th class="table-main">必填</th>
            <th class="table-main">规则</th>
            <th class="table-main">示例</th>
            <th class="table-main">备注</th>
        </tr>
        </thead>
        <tbody>
            <#list url.uploadParameters as each>
            <tr>
                <td class="table-main">${each.name}</td>
                <td class="table-main">${each.code}</td>
                <td class="table-main">${each.type}</td>
                <td class="table-main"><#if each.isArray>是<#else>否</#if></td>
                <td class="table-main">${each.length}</td>
                <td class="table-main"><#if each.allowBlank>否<#else>是</#if></td>
                <td class="table-main">${each.regex}</td>
                <td class="table-main"><#if each.example?length gt 30>${each.example[0..25]}
                    ...<#else>${each.example}</#if></td>
                <td class="table-main">${each.remark}</td>
            </tr>
            </#list>
        </tbody>
    </table>
    <span>jquery请求示例:</span>
                        <pre>
                              $.ajax({
                                type:"post",
                                contentType:"application/json",
                                url:baseUrl + '<@Uri url=url/>',
                            <#if url.uploadParameters?? && url.uploadParameters?size gt 0>
                                data:'<@parametersJson array=url.uploadParameters type=url.uploadType/>',
                            </#if>
                                success:function(result){
                                    alert(result);
                                },
                                dataType:'json'
                            });
                        </pre>
    </#if>
    <#if url.resultParameters??>
        <span>返回结果参数:</span>
        <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
            <tr>
                <th class="table-main">名称</th>
                <th class="table-main">代码</th>
                <th class="table-main">类型</th>
                <th class="table-main">集合</th>
                <th class="table-main">长度</th>
                <th class="table-main">可以为空</th>
                <th class="table-main">示例</th>
                <th class="table-main">描述</th>
            </tr>
            </thead>
            <tbody>
                <#list url.resultParameters as each>
                <tr>
                    <td class="table-main">${each.name}</td>
                    <td class="table-main">${each.code}</td>
                    <td class="table-main">${each.type}</td>
                    <td class="table-main"><#if each.isArray>是<#else>否</#if></td>
                    <td class="table-main">${each.length}</td>
                    <td class="table-main"><#if each.allowBlank>是<#else>否</#if></td>
                    <td class="table-main">${each.example}</td>
                    <td class="table-main">${each.remark}</td>
                </tr>
                </#list>
            </tbody>
        </table>
        <span>数据示例</span>
                    <pre>
                        <#if url.resultParameters?? && url.resultParameters?size gt 0>
                            '<@parametersJson array=url.resultParameters type=url.resultType/>'
                        </#if>
                    </pre>
    </#if>
</div>
</#macro>
<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong class="am-text-primary am-text-lg">已提交接口</strong> /
                <small>文档</small>
            </div>
        </div>

        <div class="am-g">

            <div class="am-tabs" data-am-tabs="{noSwipe: 1}">
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                <#list urlList as each>
                    <li<#if each_index==0> class="am-active"</#if>><a href="#div_inter_${each.url}">${each.name}</a>
                    </li>
                </#list>
                </ul>


                <div class="am-tabs-bd am-form">
                <#list urlList as each>
                <#if each_index==0>
                    <@interDoc url=each active=true/>
                <#else>
                    <@interDoc url=each/>
                </#if>
                </#list>
                </div>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>