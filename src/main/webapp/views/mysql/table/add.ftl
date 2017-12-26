<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表结构信息</strong> / <small>添加</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-10">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/mysql/table/${databaseId}/add">
                        <input type="hidden" name="databaseId" value="${databaseId}">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name" required>
                                <small>表结构名称</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">编码 / Code</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="code" placeholder="编码 / Code" required>
                                <small>表结构编码</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / Comment</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="comment" placeholder="备注 / Comment" required></textarea>
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
