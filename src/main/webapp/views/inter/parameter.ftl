<#import "/inter/common.ftl" as i_com/>
<#assign parameterObject ="com.snake.inter.model.Parameter"?new()>
<#macro paremterForm parameter=parameterObject>
<div class="am-form-group">
    <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>

    <div class="am-u-sm-9">
        <input type="text" name="name" placeholder="名称 / Name" value="${parameter.name}" required>
        <small>参数名称，便于使用者理解接口功能</small>
    </div>
</div>

<div class="am-form-group">
    <label for="user-email" class="am-u-sm-3 am-form-label">代码 / Code</label>

    <div class="am-u-sm-9">
        <input type="text" name="code" placeholder="代码层参数名称 / Code" value="${parameter.code}" required>
        <small>代码层参数名称</small>
    </div>
</div>

<@i_com.selectType value=parameter.type example=parameter.example />

<div class="am-form-group">
    <label for="user-phone" class="am-u-sm-3 am-form-label">规则 / Regex</label>

    <div class="am-u-sm-9">
        <input type="text" name="regex" placeholder="规则 / Regex" value="${parameter.regex}" required>
        <small>参数要符合的正则表达式</small>
    </div>
</div>

<div class="am-form-group">
    <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / Remark</label>

    <div class="am-u-sm-9">
        <textarea class="" rows="5" name="remark" placeholder="备注 / Remark">${parameter.remark}</textarea>
    </div>
</div>
</#macro>