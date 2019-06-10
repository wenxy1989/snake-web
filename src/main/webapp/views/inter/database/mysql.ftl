<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据库信息</strong> /
                <small>导入</small>
            </div>
        </div>

        <div class="am-g">

            <div class="am-u-sm-12 am-u-md-10">
                <form id="mysqlForm" class="am-form am-form-horizontal">
                    <div class="am-form-group">
                        <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="name" placeholder="名称 / Name" required>
                            <small>应用名称</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-email" class="am-u-sm-3 am-form-label">用户名</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="username" placeholder="root" value="root" required>
                            <small>用户名</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-phone" class="am-u-sm-3 am-form-label">密码</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="password" placeholder="密码" required>
                            <small>密码</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-phone" class="am-u-sm-3 am-form-label">数据库链接</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="url" placeholder="数据库链接" required>
                            <small>数据库链接</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-phone" class="am-u-sm-3 am-form-label">数据库名称</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="database" placeholder="数据库名称" required>
                            <small>数据库名称</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="button" onclick="loadMysqlInfo()" class="am-btn am-btn-primary">提交</button>
                            <button type="button" onclick="history.go(-1);return false;" class="am-btn am-btn-active">
                                返回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function loadMysqlInfo() {
            var elements = document.getElementById('mysqlForm').elements
            var data = {}
            for (var i = 0; i < elements.length; i++) {
                elements[i].name && (data[elements[i].name] = elements[i].value)
            }
            $.ajax({
                url: "${request.contextPath}/database/mysql/info",
                type: 'post',
                async: false,
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (result) {
                    if(result == 'success'){
                        window.location.href = "${request.contextPath}/inter/application/page"
                    }
                }
            })
        }
    </script>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
