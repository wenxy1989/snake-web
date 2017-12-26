<html>
<#include "/head.ftl"/>
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
                <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/user/updateMyInfo">
                    <input type="hidden" name="id" value="${role.id}">
                    <div class="am-form-group">
                        <label for="user-name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="userName" placeholder="姓名 / Name" value="${user.userName}">
                            <small>输入你的名字，让我们记住你。</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-email" class="am-u-sm-3 am-form-label">邮箱 / Email</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="email" placeholder="输入你的邮箱 / Email" value="${user.email}">
                            <small>邮箱你懂得...</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-phone" class="am-u-sm-3 am-form-label">电话 / Phone</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="phone" placeholder="输入你的电话号码 / Phone" value="${user.Phone}">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label for="user-intro" class="am-u-sm-3 am-form-label">简介 / Intro</label>
                        <div class="am-u-sm-9">
                            <textarea class="" rows="5" id="user-intro" placeholder="输入个人简介">${user.remark}</textarea>
                            <small>250字以内写出你的一生...</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="submit" class="am-btn am-btn-primary">
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
