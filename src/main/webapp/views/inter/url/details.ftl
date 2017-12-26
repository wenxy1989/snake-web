<#macro parametersJson array>{<#list array as each>${each.exampleJsonString}<#if each_has_next>,</#if></#list>}</#macro>
<#macro realURL source>'http://112.74.20.2:8087/extend/<@i_com.groupModel groupId=source.groupId/>/${source.url}'</#macro>
<#import "/inter/common.ftl" as i_com>
<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function writeCode() {
        $.post("${request.contextPath}/inter/url/writeCode", {id:${url.id}}, function (result) {
            myAlert(result);
        });
    }
    function testExample(){
        $.ajax({
            type:"post",
            contentType:"application/json",
            url:<@realURL source=url/>,
    <#if uploads?? && uploads?size gt 0>
            data:'<@parametersJson array=uploads/>',
    </#if>
            success:function(result){
                if(result && result != ""){
                    $("#pre_test_result").html("<span>"+result+"</span>");
                }else{
                    $("#pre_test_result").html("服务器异常情况，请联系管理员");
                }
            },
            dataType:'text'
            }
        );
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong class="am-text-primary am-text-lg">模型</strong> /
                <small>样例代码</small>
                <#--<@com.security url="/inter/url/writeCode">
                <a href="javascript:writeCode()">生成代码</a>
                </@com.security>-->
            </div>
        </div>

        <div class="am-g">

            <div class="am-tabs" data-am-tabs="{noSwipe: 1}">
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                    <li class="am-active"><a href="#div_inter_descript">${url.name}</a></li>
                    <li><a href="#div_request_parameters">请求参数</a></li>
                    <li><a href="#div_response_parameters">返回参数</a></li>
                    <@com.security url="/inter/url/writeCode">
                    <li><a href="#div_java_controller">controller处理示例</a></li>
                    <li><a href="#div_java_controller_test">controllerTest示例</a></li>
                    <li><a href="#div_mysql_mapper">mybits脚本示例</a></li>
                    </@com.security>
                </ul>


                <div class="am-tabs-bd am-form">
                    <div class="am-tab-panel am-fade am-in am-active" id="div_inter_descript">
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <tbody>
                            <tr>
                                <td class="table-main">接口名称</td>
                                <td class="table-main">${url.name}</td>
                            </tr>
                            <tr>
                                <td class="table-main">访问地址</td>
                                <td class="table-main"><@realURL source=url/></td>
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
                        <span>jquery示例</span>
                        <a href="javascript:testExample()">测试</a>
                        <pre>
                      $.ajax({
                        type:"post",
                        contentType:"application/json",
                        url:<@realURL source=url/>,
                    <#if uploads?? && uploads?size gt 0>
                        data:'<@parametersJson array=uploads/>',
                    </#if>
                        success:function(result){
                          alert(result);
                        },
                        dataType:'json'
                      });
                        </pre>
                        <span>jquery测试结果：</span>
                        <pre id="pre_test_result">

                        </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-in am-active" id="div_java_controller">
                        <pre>
                            @ResponseBody
                            @RequestMapping(value = "${url.url}", method = RequestMethod.<#if url.type==0>GET<#else>POST</#if>)
                            public Object ${url.url?replace("/","")}(@RequestBody Map&lt;String,Object&gt; obj){
                                Map<String,Object> result = null;
                            <#--<#list uploads as each>
                                <#if each.type == "String">
                                ${each.type} ${each.code} = String.valueOf(obj.get("${each.code}"));
                                <#else>
                                ${each.type} ${each.code} = obj.get("${each.code}");
                                </#if>
                            </#list>-->
                                /**
                                Integer pageNo = obj.get("pageNo") == null ? 1 : Integer.valueOf(obj.get("pageNo") + "");
                                Integer fetchSize = obj.get("fetchSize") == null ? FETCH_SIZE : Integer.valueOf(obj.get("fetchSize") + "");
                                Integer offset = (pageNo-1)*fetchSize;
                                obj.put("pageNo",pageNo);
                                obj.put("limit",fetchSize);
                                obj.put("offset", offset);
                                **/
                                /**
                                if(null != obj.get("pageNo") && null != obj.get("fetchSize")) {
                                    Integer pageNo = Integer.valueOf(obj.get("pageNo") + "");
                                    Integer fetchSize = Integer.valueOf(obj.get("fetchSize") + "");
                                    Integer offset = (pageNo - 1) * fetchSize;
                                    obj.put("pageNo", pageNo);
                                    obj.put("limit", fetchSize);
                                    obj.put("offset", offset);
                                }
                                **/
                                obj.put("deleted_", 0);
                                try{
                                    result = Service.selectMapList(obj);
                                }catch(ServiceException e){
                                    logger.error("Controller ${url.url?replace("/"," ")} error");
                                }
                            }
                        </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-in am-active" id="div_java_controller_test">
                        <pre>
    @Test
    public void ${url.url?replace("/","")}Test() throws Exception {
        JSONObject obj = new JSONObject();
<#list uploads as obj>
    <#if obj.type == "String">
        obj.put("${obj.code}","${obj.example}");
    <#else>
        obj.put("${obj.code}",${obj.example});
    </#if>
</#list>
        mockMvc.perform(
                            post(getBaseUri() + "${url.url}")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(obj.toJSONString())
                        )
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(RESULT_SUCCESS_CONTENT_TYPE))<#if !results?? || results?size lt 1>;</#if>
        <#if results?? && results?size gt 0>
                        .andExpect(jsonPath("$").exists())
            <#if url.resultType ==1><#-- resultType 1-value or 2-obj(default) or 3-list todo-->
                <#if result[0].type == "String">
                        .andExpect(content().string("${result[0].example}"));
                <#else>
                        .andExpect(content().string(${result[0].example}));
                </#if>
            <#elseif url.resultType == 2>
                <#list results as obj>
                    <#if obj.type == "String">
                        .andExpect(jsonPath("$.${obj.code}").value("${obj.example}"))<#if !obj_has_next>;</#if>
                    <#else>
                        .andExpect(jsonPath("$.${obj.code}").value(${obj.example}))<#if !obj_has_next>;</#if>
                    </#if>
                </#list>
            <#else>
                        .andExpect(jsonPath("$").isArray())
                <#list results as obj>
                    <#if obj.type == "String">
                        .andExpect(jsonPath("$[0].${obj.code}").value("${obj.example}"))<#if !obj_has_next>;</#if>
                    <#else>
                        .andExpect(jsonPath("$[0].${obj.code}").value(${obj.example}))<#if !obj_has_next>;</#if>
                    </#if>
                </#list>
            </#if>
        </#if>
    }
                        </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-in am-active" id="div_mysql_mapper">
                        <pre>

    &lt;select id="selectMapList" parameterType="java.util.Map" resultType="java.util.Map"&gt;
        select <#list results as obj>${obj.code?uncap_first}_ as ${obj.code?uncap_first},</#list>deleted_ as deleted,create_user as createUser,create_time as createTime,update_user as updateUser,update_time as updateTime,extend_one as extendOne,extend_two as extendTwo,extend_three as extendThree,extend_four as extendFour
        from table_${model.code?uncap_first}
        where 1=1
    <#list uploads as obj>
        &lt;if test="${obj.code?uncap_first} != null"&gt;
            and ${obj.code?uncap_first}_ = ${"#"}{${obj.code?uncap_first}}
        &lt;/if&gt;
    </#list>
        &lt;if test="deleted != null"&gt;
            and deleted_ = ${"#"}{deleted}
        &lt;/if&gt;
        &lt;if test="createUser != null"&gt;
            and create_user = ${"#"}{createUser}
        &lt;/if&gt;
        &lt;if test="createTime != null"&gt;
            and create_time = ${"#"}{createTime}
        &lt;/if&gt;
        &lt;if test="updateUser != null"&gt;
            and update_user = ${"#"}{updateUser}
        &lt;/if&gt;
        &lt;if test="updateTime != null"&gt;
            and update_time = ${"#"}{updateTime}
        &lt;/if&gt;
        &lt;if test="extendOne != null"&gt;
            and extend_one = ${"#"}{extendOne}
        &lt;/if&gt;
        &lt;if test="extendTwo != null"&gt;
            and extend_two = ${"#"}{extendTwo}
        &lt;/if&gt;
        &lt;if test="extendThree != null"&gt;
            and extend_three = ${"#"}{extendThree}
        &lt;/if&gt;
        &lt;if test="extendFour != null"&gt;
            and extend_four = ${"#"}{extendFour}
        &lt;/if&gt;
        &lt;if test="orderClause != null"&gt;
        ${"$"}{orderClause}
        &lt;/if&gt;
        &lt;if test="null != limit and limit&gt;0 and null != offset"&gt;
            limit ${"$"}{limit} offset ${"$"}{offset}
        &lt;/if&gt;
    &lt;/select&gt;
                        </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-in am-active" id="div_request_parameters">
                    <#if uploads?? && uploads?size gt 0>
                        <span>示例:</span>
                        <pre>
                            '<@parametersJson array=uploads/>'
                        </pre>
                    </#if>
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
                            <#list uploads as each>
                            <tr>
                                <td class="table-main">${each.name}</td>
                                <td class="table-main">${each.code}</td>
                                <td class="table-main">${each.type}</td>
                                <td class="table-main"><#if each.isArray>是<#else>否</#if></td>
                                <td class="table-main">${each.length}</td>
                                <td class="table-main"><#if each.allowBlank>否<#else>是</#if></td>
                                <td class="table-main">${each.regex}</td>
                                <td class="table-main"><#if each.example?length gt 30>${each.example[0..25]}...<#else>${each.example}</#if></td>
                                <td class="table-main">${each.remark}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>

                    <div class="am-tab-panel am-fade am-in am-active" id="div_response_parameters">
                    <#if results??>
                        <span>示例</span>
                    <pre>
                        <#if results?? && results?size gt 0>
                            '<@parametersJson array=results/>'
                        </#if>
                    </pre>
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
                                <#list results as each>
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
                    </#if>
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