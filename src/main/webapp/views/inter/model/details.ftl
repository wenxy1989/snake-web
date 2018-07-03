<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function writeCode() {
        $.post("${request.contextPath}/inter/model/writeCode", {id:${model.id}}, function (result) {
            myAlert(result);
        });
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
                <@com.security url="/inter/model/writeCode">
                <a href="javascript:writeCode()">生成代码</a>
                </@com.security>
            </div>
        </div>

        <div class="am-g">

            <div class="am-tabs" data-am-tabs="{noSwipe: 1}">
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                    <li><a href="#div_${model.code?uncap_first}_java" class="am-active">${model.code?cap_first}.java</a></li>
                    <li><a href="#div_model_java">model.java</a></li>
                <@com.security url="/inter/model/writeCode">
                    <li><a href="#div_create_sql">${model.code?uncap_first}_create.sql</a></li>
                    <li><a href="#div_mapper_xml">${model.code?cap_first}Mapper.xml</a></li>
                    <li><a href="#div_dao_java">${model.code?cap_first}Dao.java</a></li>
                    <li><a href="#div_service_java">${model.code?cap_first}Service.java</a></li>
                    <li><a href="#div_controller_java">${model.code?cap_first}Controller.java</a></li>
                    <li><a href="#div_controller_tests_java">${model.code?cap_first}ControllerTests.java</a></li>
                </@com.security>
                </ul>


                <div class="am-tabs-bd">
                    <div class="am-tab-panel am-fade am-form am-in am-active" id="div_${model.code?uncap_first}_java">
                    <pre>
                        <textarea style="height: 100%;">
                        <#include "/freemarker/model/model.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form am-in" id="div_model_java">
                    <pre>
                        <textarea style="height: 100%;">
                            <#include "/freemarker/model/modeljava.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_controller_java">
                    <pre>
                        <textarea style="height: 100%;">
                        <#include "/freemarker/model/controller.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_controller_tests_java">
                    <pre>
                        <textarea style="height: 100%;">
                    <#include "/freemarker/model/controller_tests.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_dao_java">
                    <pre>
                        <textarea style="height: 100%;">
                    <#include "/freemarker/model/dao.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_service_java">
                    <pre>
                        <textarea style="height: 100%;">
                    <#include "/freemarker/model/service.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_mapper_xml">
                    <pre>
                        <textarea style="height: 100%;">
                    <#include "/freemarker/model/sql_mapper.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form" id="div_create_sql">
                    <pre>
                        <textarea style="height: 100%;">
                    <#include "/freemarker/model/mysql.ftl"/>
                        </textarea>
                    </pre>
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
