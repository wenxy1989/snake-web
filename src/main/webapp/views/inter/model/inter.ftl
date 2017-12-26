<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<#macro parameterJson>{<#list parameters as each>${each.exampleJsonString}<#if each_has_next>,</#if></#list>}</#macro>
<#macro commonResult>
<span>返回结果(java.lang.String)</span>
<table class="am-table am-table-striped am-table-hover table-main">
    <tr>
        <th class="table-id">值</th>
        <th class="table-id">详解</th>
    </tr>
    <tr>
        <td class="table-main">success</td>
        <td class="table-main">操作成功</td>
    </tr>
    <tr>
        <td class="table-main">error</td>
        <td class="table-main">操作失败</td>
    </tr>
</table>
</#macro>
<#macro parameterTable isEdit=false>
<table class="am-table am-table-striped am-table-hover table-main">
    <thead>
    <tr>
        <th class="table-id">名称</th>
        <th class="table-id">代码</th>
        <th class="table-id">类型</th>
        <th class="table-id">集合</th>
        <#if isEdit>
        <th class="table-id">长度</th>
        <th class="table-id">必填</th>
        <th class="table-id">规则</th>
        </#if>
        <th class="table-id">示例</th>
        <th class="table-id">备注</th>
    </tr>
    </thead>
    <tbody>
        <#list parameters as each>
        <tr>
            <td class="table-main">${each.name}</td>
            <td class="table-main">${each.code}</td>
            <td class="table-main">${each.type}</td>
            <td class="table-main"><#if each.isArray>是<#else>否</#if></td>
            <#if isEdit>
            <td class="table-main">${each.length}</td>
            <td class="table-main"><#if each.allowBlank>否<#else>是</#if></td>
            <td class="table-main">${each.regex}</td>
            </#if>
            <td class="table-main">${each.example}</td>
            <td class="table-main">${each.remark}</td>
        </tr>
        </#list>
    </tbody>
</table>
</#macro>
<#macro inter name="新建" uri="create" type="POST" uploadType="Json" resultType="java.lang.String" logic="新建对象,设置创建时间和创建者" remark="id可以选填(不推荐)">
<table class="am-table am-table-striped am-table-hover table-main">
    <tbody>
    <#--<tr>
        <td class="table-main">接口名称</td>
        <td class="table-main">${name}</td>
    </tr>-->
    <tr>
        <td class="table-main">相对服务器地址</td>
        <td class="table-main">/${model.code?uncap_first}/${uri}</td>
        <td class="table-main">请求方法</td>
        <td class="table-main">${type}</td>
    </tr>
    <tr>
        <td class="table-main">上传数据类型</td>
        <td class="table-main">${uploadType}</td>
        <td class="table-main">结果数据类型</td>
        <td class="table-main">${resultType}</td>
    </tr>
    <tr>
        <td class="table-main">处理逻辑</td>
        <td class="table-main" colspan="3">${logic}</td>
    </tr>
    <tr>
        <td class="table-main">接口备注</td>
        <td class="table-main" colspan="3">${remark}</td>
    </tr>
    </tbody>
</table>
</#macro>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong class="am-text-primary am-text-lg">${model.name}</strong> /
                <small>默认接口</small>
            </div>
        </div>

        <div class="am-g">

            <div class="am-tabs" data-am-tabs="{noSwipe: 1}">
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                    <li class="am-active"><a href="#div_inter_create">新建${model.name}</a></li>
                    <li><a href="#div_inter_delete">物理删除</a></li>
                    <li><a href="#div_inter_logic_delete">逻辑删除</a></li>
                    <li><a href="#div_inter_update">更新</a></li>
                    <li><a href="#div_inter_search_equals">等价查询</a></li>
                    <li><a href="#div_inter_search_count">相似数量查询</a></li>
                    <li><a href="#div_inter_search_list">相似查询</a></li>
                    <li><a href="#div_inter_search_page">相似分页查询</a></li>
                    <li><a href="#div_inter_query_count">自定义数量查询</a></li>
                    <li><a href="#div_inter_query_list">自定义查询</a></li>
                    <li><a href="#div_inter_query_page">自定义分页查询</a></li>
                </ul>


                <div class="am-tabs-bd">
                    <div class="am-tab-panel am-fade am-form am-in am-active" id="div_inter_create">
                        <@inter/>
                        <span>上传参数</span>
                        <@parameterTable isEdit=true/>
                        <span>上传数据(Json)示例:</span>
                        <pre>"<@parameterJson/>"</pre>
                        <@commonResult/>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_delete">
                    <@inter name="物理删除数据" uri="delete" type="POST" uploadType="Json" resultType="java.lang.Long" logic="物理删除上传id对应的数据" remark="使用值对的方式比较好<#--todo-->"/>
                        <span>上传参数(java.lang.Long)</span>
                    <@commonResult/>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_logic_delete">
                    <@inter name="逻辑删除数据" uri="logic/delete" type="POST" uploadType="Json" resultType="java.lang.Long" logic="逻辑删除上传id对应的数据" remark="使用值对的方式比较好<#--todo-->"/>
                        <span>上传参数(java.lang.Long)</span>
                    <@commonResult/>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_update">
                    <@inter name="更新数据" uri="update" type="POST" uploadType="Json" resultType="java.lang.String" logic="更新上传id对应的数据" remark="除id以外的属性，不为空就更新,id不能为空"/>
                        <span>上传参数</span>
                        <@parameterTable isEdit=true/>
                        <span>上传数据(Json)示例</span>
                        <pre>"<@parameterJson/>"</pre>
                    <@commonResult/>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_search_equals">
                    <@inter name="等价查询" uri="search/equals" type="POST" uploadType="Json" resultType="Json Array" logic="查找与上传属性相同的记录，任何都属性可以为空" remark="查询条件只包含对象中已有的属性"/>
                        <span>上传参数</span>
                    <@parameterTable/>
                        <span>上传数据(Json)示例</span>
                        <pre>"<@parameterJson/>"</pre>
                        <span>返回数据示例</span>
                        <pre>"[<@parameterJson/>]"</pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_search_count">
                    <@inter name="相似数量查询" uri="search/count" type="POST" uploadType="Json" resultType="java.lang.Integer" logic="查找与上传字符串属性相似的记录数量，任何都属性可以为空" remark="查询条件只包含对象中已有的属性"/>
                        <span>上传参数详解</span>
                    <@parameterTable/>
                        <span>上传数据(Json)</span>
                        <pre>"<@parameterJson/>"</pre>
                        <span>返回数据(java.lang.Integer)</span>
                        <pre>20</pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_search_list">
                    <@inter name="相似查询" uri="search/list" type="POST" uploadType="Json" resultType="Json Array" logic="查找与上传字符串属性相似的记录，任何都属性可以为空" remark="查询条件只包含对象中已有的属性"/>
                        <span>上传参数</span>
                    <@parameterTable/>
                        <span>上传数据(Json)示例</span>
                        <pre>"<@parameterJson/>"</pre>
                        <span>返回数据(Json Array)示例</span>
                        <pre>"[<@parameterJson/>]"</pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_search_page">
                    <@inter name="自定义分页查询" uri="search/page" type="POST" uploadType="Json" resultType="Json(内涵Json Array属性)" logic="查找与上传字符串属性相似的记录，任何都属性可以为空,默认查询第一页,每页10条记录" remark="查询条件只包含对象中已有的属性"/>
                        <span>上传参数</span>
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-id">名称</th>
                                <th class="table-id">代码</th>
                                <th class="table-id">类型</th>
                                <th class="table-id">示例</th>
                                <th class="table-id">备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list parameters as each>
                            <tr>
                                <td class="table-main">${each.name}</td>
                                <td class="table-main">${each.code}</td>
                                <td class="table-main">${each.type}</td>
                                <td class="table-main">${each.example}</td>
                                <td class="table-main">${each.remark}</td>
                            </tr>
                            </#list>
                            <tr>
                                <td class="table-main">页码</td>
                                <td class="table-main">pageNo</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">查询的页码</td>
                            </tr>
                            <tr>
                                <td class="table-main">接受数量</td>
                                <td class="table-main">fetchSize</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">10</td>
                                <td class="table-main">一页接受的数据条数</td>
                            </tr>
                            </tbody>
                        </table>
                        <span>上传数据(Json)示例</span>
                        <pre>"{\"pageNo\":1,\"fetchSize\":10,<#list parameters as each>\"${each.code}\":<#if each.type == "String">\"${each.example}\"<#else>${each.example}</#if>,</#list>}"</pre>
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-id">名称</th>
                                <th class="table-id">代码</th>
                                <th class="table-id">类型</th>
                                <th class="table-id">示例</th>
                                <th class="table-id">备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list parameters as each>
                            <tr>
                                <td class="table-main">${each.name}</td>
                                <td class="table-main">${each.code}</td>
                                <td class="table-main">${each.type}</td>
                                <td class="table-main">${each.example}</td>
                                <td class="table-main">${each.remark}</td>
                            </tr>
                            </#list>
                            <tr>
                                <td class="table-main">页码</td>
                                <td class="table-main">number</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">查询的页码</td>
                            </tr>
                            <tr>
                                <td class="table-main">接受数量</td>
                                <td class="table-main">size</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">10</td>
                                <td class="table-main">一页接受的数据条数</td>
                            </tr>
                            <tr>
                                <td class="table-main">记录总数</td>
                                <td class="table-main">totalElements</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">当前查询符合条件的记录总数</td>
                            </tr>
                            <tr>
                                <td class="table-main">总页数</td>
                                <td class="table-main">totalPages</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">按照当前分页的总页数</td>
                            </tr>
                            <tr>
                                <td class="table-main">排序字段</td>
                                <td class="table-main">sort</td>
                                <td class="table-main">String</td>
                                <td class="table-main">id_ asc</td>
                                <td class="table-main">数据查询排序方式</td>
                            </tr>
                            <tr>
                                <td class="table-main">最后一页</td>
                                <td class="table-main">lastPage</td>
                                <td class="table-main">Boolean</td>
                                <td class="table-main">true</td>
                                <td class="table-main">是否是最后一页</td>
                            </tr>
                            <tr>
                                <td class="table-main">第一页</td>
                                <td class="table-main">firstPage</td>
                                <td class="table-main">Boolean</td>
                                <td class="table-main">true</td>
                                <td class="table-main">是否是第一页</td>
                            </tr>
                            <tr>
                                <td class="table-main">本页条数</td>
                                <td class="table-main">numberOfElements</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">0</td>
                                <td class="table-main">本页包含数据条数</td>
                            </tr>
                            </tbody>
                        </table>
                        <span>返回数据(Json)示例</span>
                        <pre>"{content:[<@parameterJson/>],\"totalPages\":1,\"sort\":null,\"lastPage\":true,\"firstPage\":false,\"numberOfElements\":0,\"totalElements\":0,\"size\":10,\"number\":1}"</pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_query_count">
                    <@inter name="自定义数量查询" uri="query/count" type="POST" uploadType="Json" resultType="java.lang.Integer" logic="查找与上传属性相关的记录数量，任何都属性可以为空" remark="查询条件可以包含其他对象的属性"/>
                        <span>上传参数详解</span>
                    <@parameterTable/>
                        <span>上传数据(Json)</span>
                        <pre>
                        "<@parameterJson/>"
                        </pre>
                        <span>返回数据(java.lang.Integer)</span>
                        <pre>20</pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_query_list">
                    <@inter name="自定义查询" uri="query/list" type="POST" uploadType="Json" resultType="Json Array" logic="查找与上传字符串属性相似的记录，任何都属性可以为空" remark="查询条件可以包含其他对象的属性"/>
                        <span>上传参数</span>
                    <@parameterTable/>
                        <span>上传数据(Json)示例</span>
                        <pre>"<@parameterJson/>"/pre>
                        <span>返回数据(Json Array)示例</span>
                        <pre>"[<@parameterJson/>]"</pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_inter_query_page">
                    <@inter name="相似分页查询" uri="query/page" type="POST" uploadType="Json" resultType="Json(内涵Json Array属性)" logic="查找与上传字符串属性相似的记录，任何都属性可以为空,默认查询第一页,每页10条记录" remark="查询条件可以包含其他对象的属性"/>
                        <span>上传参数</span>
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-id">名称</th>
                                <th class="table-id">代码</th>
                                <th class="table-id">类型</th>
                                <th class="table-id">示例</th>
                                <th class="table-id">备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list parameters as each>
                            <tr>
                                <td class="table-main">${each.name}</td>
                                <td class="table-main">${each.code}</td>
                                <td class="table-main">${each.type}</td>
                                <td class="table-main">${each.example}</td>
                                <td class="table-main">${each.remark}</td>
                            </tr>
                            </#list>
                            <tr>
                                <td class="table-main">页码</td>
                                <td class="table-main">pageNo</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">查询的页码</td>
                            </tr>
                            <tr>
                                <td class="table-main">接受数量</td>
                                <td class="table-main">fetchSize</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">10</td>
                                <td class="table-main">一页接受的数据条数</td>
                            </tr>
                            </tbody>
                        </table>
                        <span>上传数据(Json)示例</span>
                        <pre>"{\"pageNo\":1,\"fetchSize\":10,<#list parameters as each>\"${each.code}\":<#if each.type == "String">\"${each.example}\"<#else>${each.example}</#if>,</#list>}"</pre>
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-id">名称</th>
                                <th class="table-id">代码</th>
                                <th class="table-id">类型</th>
                                <th class="table-id">示例</th>
                                <th class="table-id">备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list parameters as each>
                            <tr>
                                <td class="table-main">${each.name}</td>
                                <td class="table-main">${each.code}</td>
                                <td class="table-main">${each.type}</td>
                                <td class="table-main">${each.example}</td>
                                <td class="table-main">${each.remark}</td>
                            </tr>
                            </#list>
                            <tr>
                                <td class="table-main">页码</td>
                                <td class="table-main">number</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">查询的页码</td>
                            </tr>
                            <tr>
                                <td class="table-main">接受数量</td>
                                <td class="table-main">size</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">10</td>
                                <td class="table-main">一页接受的数据条数</td>
                            </tr>
                            <tr>
                                <td class="table-main">记录总数</td>
                                <td class="table-main">totalElements</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">当前查询符合条件的记录总数</td>
                            </tr>
                            <tr>
                                <td class="table-main">总页数</td>
                                <td class="table-main">totalPages</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">1</td>
                                <td class="table-main">按照当前分页的总页数</td>
                            </tr>
                            <tr>
                                <td class="table-main">排序字段</td>
                                <td class="table-main">sort</td>
                                <td class="table-main">String</td>
                                <td class="table-main">id_ asc</td>
                                <td class="table-main">数据查询排序方式</td>
                            </tr>
                            <tr>
                                <td class="table-main">最后一页</td>
                                <td class="table-main">lastPage</td>
                                <td class="table-main">Boolean</td>
                                <td class="table-main">true</td>
                                <td class="table-main">是否是最后一页</td>
                            </tr>
                            <tr>
                                <td class="table-main">第一页</td>
                                <td class="table-main">firstPage</td>
                                <td class="table-main">Boolean</td>
                                <td class="table-main">true</td>
                                <td class="table-main">是否是第一页</td>
                            </tr>
                            <tr>
                                <td class="table-main">本页条数</td>
                                <td class="table-main">numberOfElements</td>
                                <td class="table-main">Integer</td>
                                <td class="table-main">0</td>
                                <td class="table-main">本页包含数据条数</td>
                            </tr>
                            </tbody>
                        </table>
                        <span>返回数据(Json)示例</span>
                        <pre>"{content:[<@parameterJson/>],\"totalPages\":1,\"sort\":null,\"lastPage\":true,\"firstPage\":false,\"numberOfElements\":0,\"totalElements\":0,\"size\":10,\"number\":1}"</pre>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
