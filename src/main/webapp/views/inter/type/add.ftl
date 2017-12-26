<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">接口参数</strong> / <small>添加</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-10">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/inter/parameter/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name">
                                <small>参数名称，便于使用者理解接口功能</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">代码 / Code</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="code" placeholder="代码层参数名称 / Code">
                                <small>代码层参数名称</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="type" placeholder="类型 / Type">
                                <small>参数类型，使用定义的字符串标示</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">长度 / Length</label>
                            <div class="am-u-sm-9">
                                <input type="number" name="length" placeholder="长度 / Length">
                                <small>参数传递及存储长度</small>
                            </div>
                        </div>

                        <#--<div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">可以为空 / AllowBlank</label>
                            <div class="am-u-sm-9">
                                <select name="allowBlank" data-am-selected="{btnSize: 'sm'}">
                                    <option value="0">是</option>
                                    <option value="1">否</option>
                                </select>
                                <small>是否可以为空</small>
                            </div>
                        </div>-->

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">规则 / Regex</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="regex" placeholder="规则 / Regex">
                                <small>参数要符合的正则表达式</small>
                            </div>
                        </div>

                        <#--<div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">位置 / Position</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="position" placeholder="位置 / Position">
                                <small>参数传递的位置</small>
                            </div>
                        </div>-->

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / Remark</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="remark" placeholder="备注 / Remark"></textarea>
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
