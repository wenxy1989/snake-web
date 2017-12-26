<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">词组</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" class="am-btn am-btn-default" onclick="toAdd()">
                            <span class="am-icon-plus">新增</span>
                        </button>
                    </div>
                </div>
            </div>
            <form method="post" action="${request.contextPath}/book/word/page">
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-form-group">
                    <select name="order" data-am-selected="{btnSize: 'sm'}">
                        <option value="id_"<#if order == "id_" || !order??> selected="selected"</#if>>ID</option>
                        <option value="count_"<#if order == "count_"> selected="selected"</#if>>出现次数</option>
                        <option value="type_id"<#if order == "type_id"> selected="selected"</#if>>词语类型</option>
                    </select>
                    <select name="typeId" data-am-selected="{btnSize: 'sm'}">
                        <option value="">词组类型</option>
                    <#list book_type_list as type>
                        <option value="${type.id}"<#if typeId == type.id> selected="selected"</#if>>${type.name}</option>
                    </#list>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-input-group am-input-group-sm">
                    <input type="text" class="am-form-field" name="value" value="${value}">
                    <span class="am-input-group-btn">
                      <button class="am-btn am-btn-default" type="submit">搜索</button>
                    </span>
                </div>
            </div>
            </form>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-id">ID</th>
                        <th class="table-id">BOOK_ID</th>
                        <th class="table-title">词组</th>
                        <th class="table-author">类型</th>
                        <th class="table-author">次数</th>
                        <th class="table">已验证</th>
                        <th class="table-date">创建时间</th>
                        <th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list page.content as obj>
                    <tr>
                        <td>${obj.id}</td>
                        <td>${obj.bookId}</td>
                        <td><a href="#">${obj.word}</a></td>
                        <td class="am-hide-sm-only">
                            <#list book_word_type_list as type>
                                <#if type.id == obj.typeId>
                            ${type.name}
                                <#break>
                            </#if>
                                </#list>
                        </td>
                        <td class="am-hide-sm-only">${obj.count}</td>
                        <td class="am-hide-sm-only"><#if obj.verified>是<#else>否</#if></td>
                        <td class="am-hide-sm-only">${obj.createdTime}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <#if !obj.verified>
                                        <a href="javascript:doVerify(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                            <span class="am-icon-pencil-square-o">认证</span>
                                        </a>
                                    </#if>
                                    <a href="${request.contextPath}/book/word/toEdit?id=${obj.id}" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                        <span class="am-icon-pencil-square-o">编辑</span>
                                    </a>
                                    <a href="javascript:toDelete(${obj.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                        <span class="am-icon-trash-o">删除</span>
                                    </a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            <#import "/common/pager.ftl" as pager>
            <@pager.guid pageUrl="/book/word/page" page=page />
            </div>
        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
<script type="text/javascript">
    function toAdd(){
        window.location.href="${request.contextPath}/book/word/toAdd";
    }
    function toDelete(id){
        if(confirm(" delete ?")){
            window.location.href = "${request.contextPath}/book/word/delete?id="+id;
        }
    }
    function toEdit(id){
        window.location.href = "${request.contextPath}/book/word/toEdit?id="+id;
    }
    function doVerify(id){
        $.post("${request.contextPath}/book/word/verify",{id:id},function(result){
            alert(result);
        });
    }
</script>
</body>
</html>
