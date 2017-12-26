<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据列信息</strong> / <small>修改</small></div>
        </div>

        <div class="am-g">

            <div class="am-u-sm-12 am-u-md-10">
                <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/mysql/column/${tableId}/edit">
                    <input type="hidden" name="id" value="${column.id}">
                    <input type="hidden" name="tableId" value="${tableId}">
                    <div class="am-form-group">
                        <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="name" placeholder="名称 / Name" value="${column.name}" required>
                            <small>数据列名称</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">编码 / Code</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="code" placeholder="编码 / Code" value="${column.code}" required>
                            <small>数据列编码</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">类型 / Type</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="type" placeholder="类型 / Type" value="${column.type}" required>
                            <small>数据列类型</small>
                        </div>
                        <label class="am-u-sm-3 am-form-label">是否自增 / AutoIncrement</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="autoIncrement" placeholder="是否自增 / AutoIncrement" value="${column.autoIncrement}" required>
                            <small>数据列是否自增</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">可以为空 / AllowBlank</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="allowBlank" placeholder="可以为空 / AllowBlank" value="${column.allowBlank}" required>
                            <small>数据列是否可以为空</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">默认值</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="defaultValue" placeholder="默认值" value="${column.defaultValue}" required>
                            <small>数据列默认值</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">排序/位置</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="orderPosition" placeholder="排序/位置" value="${column.orderPosition}" required>
                            <small>数据列排序/位置</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / Comment</label>
                        <div class="am-u-sm-9">
                            <textarea class="" rows="5" name="comment" placeholder="备注 / Comment" required>${column.comment}</textarea>
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
