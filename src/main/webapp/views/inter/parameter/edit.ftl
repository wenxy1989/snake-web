<html>
<#include "/head.ftl"/>
<#import "/inter/common.ftl" as i_com/>
<@i_com.selectTypeFunction/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">接口参数</strong> / <small>修改</small></div>
        </div>

        <div class="am-g">

            <div class="am-u-sm-12 am-u-md-10">
                <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/inter/parameter/edit">
                    <input type="hidden" name="id" value="${parameter.id}">
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

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="submit" class="am-btn am-btn-primary">保存</button>
                            <button type="button" onclick="history.go(-1);return false;" class="am-btn am-btn-active">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
