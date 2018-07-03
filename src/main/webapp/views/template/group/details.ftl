<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function writeCode() {
        $.post("${request.contextPath}/template/group/writeCode", {id:${group.id}}, function (result) {
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
                <@com.security url="/template/group/writeCode">
                <a href="javascript:writeCode()">将代码写到本地</a>
                </@com.security>
            </div>
        </div>

        <div class="am-g">

            <div class="am-tabs" data-am-tabs="{noSwipe: 1}">
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                <@com.security url="/template/group/writeCode">
                    <li><a href="#div_api_controller_java" class="am-active">${group.model?cap_first}APIController.java</a></li>
                    <li><a href="#div_api_controller_tests_java" class="am-active">${group.model?cap_first}APIControllerTests.java</a></li>
                </@com.security>
                </ul>


                <div class="am-tabs-bd">
                    <div class="am-tab-panel am-fade am-form am-in am-active" id="div_api_controller_java">
                    <pre>
                        <textarea style="height: 100%;">
                        <#include "/freemarker/model/api_controller.ftl"/>
                        </textarea>
                    </pre>
                    </div>
                    <div class="am-tab-panel am-fade am-form am-in" id="div_api_controller_tests_java">
                    <pre>
                        <textarea style="height: 100%;">
                            <#include "/freemarker/model/api_controller_tests.ftl"/>
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
