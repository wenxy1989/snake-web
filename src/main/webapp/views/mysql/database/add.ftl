<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">MYSQL数据库信息</strong> / <small>添加</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-10">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/mysql/database/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">中文名称</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="中文名称" required>
                                <small>MYSQL数据库中文名称</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">编码</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="code" placeholder="编码" required>
                                <small>MYSQL数据库编码</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">地址</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="address" placeholder="地址" required>
                                <small>MYSQL数据库地址</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">端口</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="port" placeholder="端口" required>
                                <small>MYSQL数据库端口</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">用户名</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="username" placeholder="用户名" required>
                                <small>MYSQL数据库用户名</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">密码</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="password" placeholder="密码" required>
                                <small>MYSQL数据库密码</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / 描述</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="remark" placeholder="备注 / 描述" required></textarea>
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
