<html>
<#include "/head.ftl"/>
<script type="text/javascript">
    $(document).ready(function(){
        $.post("${request.contextPath}/role/loadMyList",function(result){
            if(result !== undefined && result.length > 0){
                var _options = "";
                for(var i=0;i< result.length;i++){
                    _options += "<option value='"+result[i].id+"'>"+result[i].name+"</option>";
                }
                $("select[name='roleId']").html(_options);
            }
        });
    });
</script>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户</strong> / <small>添加</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-10">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/user/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">登录名称 / LoginName</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="loginName" placeholder="登录名称 / LoginName" required>
                                <small>登录名称，登录时的校验名称</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">登录密码 / LoginPwd</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="loginPwd" placeholder="登录名 / LoginPwd" required>
                                <small>登录密码，登录时的校验密码，与登录名配合使用</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="userName" placeholder="姓名 / Name" required>
                                <small>输入你的名字，让我们记住你。</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">角色 / Role</label>
                            <div class="am-u-sm-9">
                                <select name="roleId"></select>
                                <small>用户角色</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">邮箱 / Email</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="email" placeholder="输入你的邮箱 / Email">
                                <small>邮箱你懂得...</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">电话 / Phone</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="phone" placeholder="输入你的电话号码 / Phone">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">简介 / Intro</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" id="user-intro" placeholder="输入个人简介"></textarea>
                                <small>250字以内</small>
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
