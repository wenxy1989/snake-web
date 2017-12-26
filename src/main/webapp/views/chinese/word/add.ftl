<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">词组</strong> / <small>添加</small></div>
            </div>

            <hr/>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                </div>

                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/book/word/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">词组 / Word</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="word" placeholder="词组 / Word" value="${obj.word}">
                                <small>词组显示内容</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                                <select name="typeId" data-am-selected="{btnSize: 'sm'}">
                                    <#list book_word_type_list as type>
                                    <option value="${type.id}">${type.name}</option>
                                    </#list>
                                </select>
                                <small>还不知道用不用</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                            <#list book_word_type_list as type>
                                <label for="input_type_${type.id}">${type.name}</label>
                                <input id="input_type_${type.id}" type="checkbox" value="${type.id}">
                            </#list>
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
