<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding am-g">
            <div class="am-u-sm-12 am-u-md-3 am-fl am-cf">
                <strong class="am-text-primary am-text-lg">完整语句</strong> / <small>列表</small>
            </div>
            <div class="am-u-sm-12 am-u-md-9 am-fr">
                <form method="post" action="${request.contextPath}/book/paragraph/page">
                    <div class="am-input-group am-input-group-sm">
                        <input type="hidden" name="bookId" value="${bookId}">
                        <input type="text" name="value" class="am-form-field" value="${value}">
                        <span class="am-input-group-btn">
                            <button class="am-btn am-btn-default" type="submit">搜索</button>
                            <button type="button" class="am-btn am-btn-default am-icon-plus" onclick="toAdd()">新增</button>
                            <button type="button" class="am-btn am-btn-default" onclick="toMark()">我的书签</button>
                            <button type="button" class="am-btn am-btn-default" onclick="toAddMark()">添加本页为书签</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>

        <div class="am-cf am-padding">
            <div class="am-list">
                <ul class="am-list">
                <#list page.content as obj>
                    <#if obj_index == 0>
                        <#if obj.value?length lt 50>
                            <div id="div_mark_name" class="am-hide">${obj.value}</div>
                        <#else>
                            <div id="div_mark_name" class="am-hide">${obj.value[0..49]}</div>
                        </#if>
                    </#if>
                    <li class="am-g am-list-item-text">
                        <div class="am-list-item-text">
                            <#if obj.name??>
                                <span id="span_paragraph_#{obj.id}" style="font-size: large;color: #222222">${obj.name?replace(value,"<a href='#'>"+value+"</a>")}</span>
                            </#if>
                            <#if obj.content??>
                                <span id="span_paragraph_#{obj.id}" style="font-size: large;color: #222222">${obj.content?replace(value,"<a> href='#'"+value+"</a>")}</span>
                            </#if>
                            <textarea id="textarea_paragraph_#{obj.id}" cols="100" rows="3" style="display: none;color: #222222;font-size: large;width: 100%;"></textarea>
                        </div>
                        <div class="am-list-news-more am-fl">
                        <span class="am-fr">${obj.createdTime}-id:${obj.id}</span>
                        </div>
                        <div class="am-list-news-more am-fr">
                            <a href="javascript:toSplit(${obj.id})">
                                <span class="am-icon-edit">拆分短语</span>
                            </a>
                            <a id="a_update_paragraph_${obj.id}" href="javascript:toUpdate(${obj.id})" style="display: none;">
                                <span class="am-icon-save">保存</span>
                            </a>
                            <a href="javascript:toEdit(${obj.id})" class="am-list-news-one">
                                <span class="am-icon-save">编辑</span>
                            </a>
                            <a href="javascript:toDelete(${obj.id})" class="am-list-news-more">
                                <span class="am-icon-trash-o">删除</span>
                            </a>
                        </div>
                    </li>
                </#list>
                </ul>
            </div>
        <#import "/common/pager.ftl" as pager>
            <@pager.guid pageUrl="/book/paragraph/page" page=page />
        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
<script type="text/javascript">
    function toAdd(){
        window.location.href="${request.contextPath}/book/paragraph/toAdd";
    }
    function toMark(){
        window.location.href = "${request.contextPath}/book/mark/page";
    }
    function toAddMark(){
        var _url = "${request.contextPath}/book/paragraph/page?bookId=#{bookId}&pageNo=${page.number}";
        var _name = $("#div_mark_name").html();
        $.post("${request.contextPath}/book/mark/create",{name:_name,url:_url}, function (result) {
            alert(result);
        });
    }
    function toDelete(id){
        if(confirm(" delete ?")){
            window.location.href = "${request.contextPath}/book/paragraph/delete?bookId=${bookId}&id="+id;
        }
    }
    function toEdit(id){
        var spanObj = $("#span_paragraph_"+id);
        var textObj = $("#textarea_paragraph_"+id);
        var aObj = $("#a_update_paragraph_"+id);
        if(textObj.is(":hidden")){
            var paragraph = spanObj.text();
            var word_width = 18;
            var word_size = textObj.width()/word_width;
            var row_size = paragraph.length/word_size;
            textObj.attr({rows:row_size+1});
            textObj.text(paragraph);
            spanObj.hide();
            textObj.show();
            aObj.show();
        }else{
            spanObj.show();
            textObj.hide();
            aObj.hide();
        }
    }
    function toUpdate(id){
        var spanObj = $("#span_paragraph_"+id);
        var textObj = $("#textarea_paragraph_"+id);
        var aObj = $("#a_update_paragraph_"+id);
        var paragraph = textObj.val();
        $.post("${request.contextPath}/book/paragraph/updateValue",{id:id,value:paragraph}, function (result) {
            if(result != 'success'){
                alert(result);
            }
        });
        spanObj.text(paragraph);
        textObj.hide();
        aObj.hide();
        spanObj.show();
    }
    function toSplit(id){
        $.post("${request.contextPath}/book/paragraph/split",{id:id}, function (result) {
            alert(result);
        });
    }
</script>
</body>
</html>
