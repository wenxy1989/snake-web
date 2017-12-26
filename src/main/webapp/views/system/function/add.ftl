<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户</strong> / <small>添加</small></div>
            </div>

            <hr/>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                </div>



                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/function/add">
                        <input type="hidden" name="parentId" value="${parentId}">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">菜单名 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="菜单名称 / Name" value="${obj.name}">
                                <small>菜单显示内容</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">代码 / Code</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="code" placeholder="代码 / Code" value="${obj.code}">
                                <small>还不知道用不用</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                                <select name="type" data-am-selected="{btnSize: 'sm'}">
                                    <option value="1">菜单</option>
                                    <option value="2">功能</option>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">访问相对地址 / Url</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="url" placeholder="访问相对地址 / Url" value="${obj.url}">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">序号 / Order</label>
                            <div class="am-u-sm-9">
                                <input type="number" name="order" placeholder="由小到大排序 / Order" value="${obj.order}">
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
