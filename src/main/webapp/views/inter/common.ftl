<#assign resourceObject = "com.snake.inter.freemarker.InterMethodModelEx"?new()>
<#assign inter_group_list = resourceObject("resource","inter_group_list")>
<#assign inter_model_list = resourceObject("resource","inter_model_list")>
<#assign inter_group_map = resourceObject("resource","inter_group_map")>
<#assign inter_model_map = resourceObject("resource","inter_model_map")>
<#macro groupName groupId=0>${resourceObject("get","inter_group",groupId).name}</#macro>
<#macro groupModel groupId=0>${resourceObject("get","inter_group",groupId).model?uncap_first}</#macro>
<#macro selectTypeFunction name="type" hasExample=true type="String" applicationId=0>
<script type="text/javascript">
    var javaTypeMysqlLength = {
        String: 20,
        Integer: 11,
        Double: 11,
        Float: 6,
        Long: 11,
        Short: 5,
        Boolean: 1,
        Byte: 20,
        Object: 500,
        Array: 1000,
        Character: 1
    };
    $(document).ready(function () {
        $("select[name='type']").change(function () {
            var type = $(this).val();
            if (isEmpty(javaTypeMysqlLength[type])) {
                selectModel(type);
            } else {
                $("input[name='length']").val(javaTypeMysqlLength[type]);
                if (type == 'String' || type == "Char") {
                    $("input[name='example']").attr("type", "text");
                } else {
                    $("input[name='example']").attr("type", "number");
                }
                $("input[name='example']").removeAttr("disabled").show();
                $("textarea[name='example']").attr("disabled", true).hide();
            }
        });
    });
    function selectModel(code) {
        $.post("${request.contextPath}/inter/model/loadExampleJson", {code: code}, function (data) {
            if (data) {
                $("input[name='type']").val(data.code);
                $("input[name='example']").attr("disabled", true).hide();
                $("textarea[name='example']").val(data.example).show().removeAttr("disabled");
                closeModal();
            }
        });
    }
</script>
</#macro>

<#macro selectType value="String" type="normal" example="" length=10 applicationId=1>
<div class="am-form-group">
    <label for="user-phone" class="am-u-sm-3 am-form-label">类型 / Type</label>

    <div class="am-u-sm-9">
        <select name="type" data-am-selected="{btnSize: 'sm'}">
            <option value="String"<#if value="String"> selected</#if>>String</option>
            <option value="Boolean"<#if value="Boolean"> selected</#if>>Boolean</option>
            <option value="Integer"<#if value="Integer"> selected</#if>>Integer</option>
            <option value="Double"<#if value="Double"> selected</#if>>Double</option>
            <option value="Long"<#if value="Long"> selected</#if>>Long</option>
        <#--<option value="Short"<#if value="Short"> selected</#if>>Short</option>-->
        <#--<option value="Float"<#if value="Float"> selected</#if>>Float</option>-->
        <#--<option value="Character"<#if value="Character"> selected</#if>>Character</option>-->
        <#--<option value="Byte"<#if value="Byte"> selected</#if>>Byte</option>-->
            <#list inter_model_list as each>
                <#if each.applicationId=applicationId>
                    <option value="${each.code}"<#if value=each.code> selected</#if>>${each.name}(${each.code})</option>
                </#if>
            </#list>
        </select>
        <small>参数类型，使用定义的字符串标示</small>
    </div>
</div>

<div class="am-form-group">
    <label for="user-phone" class="am-u-sm-3 am-form-label">长度 / Length</label>
    <div class="am-u-sm-9">
        <input type="number" name="length" placeholder="长度 / Length" value="${length}" required>
        <small>参数传递及存储长度</small>
    </div>
</div>

<div class="am-form-group">
    <label for="user-phone" class="am-u-sm-3 am-form-label">示例 / Example</label>

    <div class="am-u-sm-9">
        <#if example?contains("\"")>
            <textarea rows="5" name="example" placeholder="示例 / Example" required>${example}</textarea>
            <input type="text" name="example" placeholder="示例 / Example" value="" disabled style="display: none">
        <#else>
            <textarea rows="5" name="example" placeholder="示例 / Example" disabled style="display: none"></textarea>
            <input type="text" name="example" placeholder="示例 / Example" value="${example}" required>
        </#if>
        <small>示例，定义属性的示例</small>
    </div>
</div>
</#macro>

<#macro selectFrame name="groupId" value=0 appId=1>
<div class="am-form-group">
    <label for="user-phone" class="am-u-sm-3 am-form-label">接口分组</label>

    <div class="am-u-sm-9">
        <select name="${name}" data-am-selected="{btnSize: 'sm'}">
            <option value="0"<#if value=0> selected</#if>>未选择</option>
            <#list inter_group_list as each>
            <option value="${each.id}"<#if value=each.id> selected</#if>>${each.name}<#if each.model?? && each.model?length gt 0>(${each.model})</#if></option>
            </#list>
        </select>
        <small>接口分组</small>
    </div>
</div>
</#macro>