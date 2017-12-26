<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">菜单</strong> / <small>修改</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                </div>

                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/book/book/edit">
                        <input type="hidden" name="id" value="${obj.id}">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name" value="${obj.name}">
                                <small>电子书籍名称</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-type" class="am-u-sm-3 am-form-label">作者 / Author</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="author" placeholder="作者 / Author" value="${obj.author}">
                                <small>书籍作者</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-type" class="am-u-sm-3 am-form-label">简介 / introduction</label>
                            <div class="am-u-sm-9">
                                <textarea name="introduction" placeholder="简介 / introduction">${obj.introduction}</textarea>
                                <small>书籍作者</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <div class="am-u-sm-9 am-u-sm-push-3">
                                <button type="submit" class="am-btn am-btn-primary">保存</button>
                                <button onclick="history.go(-1)" class="am-btn am-btn-primary">返回</button>
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
