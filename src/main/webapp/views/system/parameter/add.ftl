<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统参数</strong> / <small>添加</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-10">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/sys/parameter/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name" required>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">编码 / Code</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="code" placeholder="编码 / Code" required>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">整形数字 / Integer</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="intValue" placeholder="整形数字 / Integer">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">长整形数字 / Long</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="longValue" placeholder="长整形数字 / Long">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">双精度浮点 / Double</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="doubleValue" placeholder="双精度浮点 / Double">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">长字符串 / String</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="stringValue" placeholder="长字符串 / String">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">布尔 / Boolean</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="booleanValue" placeholder="布尔 / Boolean">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">字符 / Character</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="charValue" placeholder="字符 / Character">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / Remark</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="remark" placeholder="备注 / Remark"></textarea>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <div class="am-u-sm-9 am-u-sm-push-3">
                                <button type="submit" class="am-btn am-btn-primary">保存</button>
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
