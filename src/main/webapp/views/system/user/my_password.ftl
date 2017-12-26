<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    function checkForm(){
        myConfirm("确认修改密码？","密码修改后会自动跳转到登录页面，请重新登录",function(){
            $("#form_update").submit();
        });
        return false;
    }
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">我的信息</strong> / <small>${user.userName}</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-10">
                <form class="am-form am-form-horizontal" id="form_update" method="post" action="${request.contextPath}/user/updateMyPwd">
                    <div class="am-form-group">
                        <label for="user-name" class="am-u-sm-3 am-form-label">登录名称</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="LoginName" placeholder="登录名称" value="${user.loginName}" disabled="disabled">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-email" class="am-u-sm-3 am-form-label">旧密码</label>
                        <div class="am-u-sm-9">
                            <input type="password" name="oldPwd" placeholder="旧密码" value="">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-phone" class="am-u-sm-3 am-form-label">新密码</label>
                        <div class="am-u-sm-9">
                            <input type="password" name="newPwd" placeholder="新密码" value="">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="button" class="am-btn am-btn-primary" onclick="return checkForm()">
                                <span class="am-icon-save">保存</span>
                            </button>
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
