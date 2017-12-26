<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">完整语句</strong> / <small>修改</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                </div>

                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/book/paragraph/edit">
                        <input type="hidden" name="id" value="${obj.id}">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">内容 / Name</label>
                            <div class="am-u-sm-9">
                                <textarea class="am-text-success am-active" rows="5" name="name" placeholder="内容 / Name">${obj.name}</textarea>
                                <small>完整语句内容</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">内容 / Content</label>
                            <div class="am-u-sm-9">
                                <textarea class="am-text-xxl" name="content" rows="6" placeholder="内容 / Content">${obj.content}</textarea>
                                <small>完整语句内容</small>
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
<script type="text/javascript">
    $(document).ready(function(){
        <#if obj.type??>$("select[name='type']").val(${obj.type});</#if>
    });
</script>
</body>
</html>
