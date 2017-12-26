<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">语句结构</strong> / <small>添加</small></div>
            </div>

            <hr/>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                </div>

                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/book/structure/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name" value="${obj.name}">
                                <small>语句结构名称</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-type" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                                <select name="type" data-am-selected="{btnSize: 'sm'}">
                                    <option value="1">完整语句</option>
                                    <option value="2">词组短语</option>
                                </select>
                                <small>语句结构大类</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <label for="user-type" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                            <#list book_word_type_list as type>
                                <#if type_index%6 == 0>
                                <div>
                                <span class="am-input-group-btn">
                                </#if>
                                <button class="am-btn am-btn-default" type="button">${type.name}</button>
                                <#if (type_index+1)%6 == 0>
                                </span>
                                </div>
                                </#if>
                            </#list>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">内容 / Content</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="content" placeholder="内容 / Content" value="${obj.content}">
                                <small>语句结构内容</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">备注 / Remark</label>
                            <div class="am-u-sm-9">
                                <textarea type="text" name="remark" placeholder="备注 / Remark">${obj.remark}</textarea>
                                <small>语句结构备注</small>
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
