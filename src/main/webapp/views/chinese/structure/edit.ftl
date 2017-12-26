<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">语句结构</strong> / <small>修改</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-10">
                    <div class="am-form-group">
                        <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/book/structure/edit">
                            <input type="hidden" name="id" value="${obj.id}">
                            <div class="am-input-group am-input-group-sm">
                                <input type="text" name="name" placeholder="名称 / Name" value="${obj.name}" class="am-form-field">
                                <span class="am-input-group-btn">
                                <select name="type" data-am-selected="{btnSize: 'sm'}" class="am-form-field">
                                    <option value="1"<#if obj.type==1> selected="selected"</#if>>完整语句</option>
                                    <option value="2"<#if obj.type==2> selected="selected"</#if>>词组短语</option>
                                </select>
                                <button type="submit" class="am-btn am-btn-primary">保存</button>
                                <button onclick="history.go(-1)" class="am-btn am-btn-primary">返回</button>
                                </span>
                            </div>
                        </form>
                    </div>
                    <div class="am-input-group">
                        <div class="am-input-group-btn">
                        </div>
                    </div>
                    <div class="am-input-group am-pagination">
                        <div class="am-input-group-btn">
                            <span id="span_element_list" class="am-input-group-btn">
                        <#list elements?sort_by("order") as each>
                            <button id="button_element_id${each.elementId}" class="am-btn am-btn-success am-icon-remove" onclick="removeElement(${each.elementId})">${each.element.name}(${each.order})</button>
                        </#list>
                            </span>
                        </div>
                    </div>
                    <div class="am-input-group">
                        <div class="am-input-group am-input-group-sm">
                        <input type="number" id="input_element_order" class="am-form-field" placeholder="order" value="0">
                        <span class="am-input-group-btn">
                        <#list book_element_list as element>
                            <button class="am-btn am-btn-primary" onclick="addElement(${element.id},'${element.name}')">
                                <span class="am-icon-plus">${element.name}</span>
                            </button>
                        </#list>
                        </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- content end -->
</div>
<#include "/footer.ftl"/>
<script type="text/javascript">
    function removeElement(elementId){
        $.post("${request.contextPath}/book/structure/removeElement", {
            elementId: elementId,
            structureId:${obj.id}
        }, function (result) {
            if("success"==result){
                $("#button_element_id"+elementId).remove();
            }
        });
    }
    function addElement(elementId,elementName) {
        var order = $("#input_element_order").val();
        if (order > 0) {
            $.post("${request.contextPath}/book/structure/addElement", {
                elementId: elementId,
                structureId:${obj.id},
                order: order
            }, function (result) {
                if("success"==result){
                    var button_html = "<button id='button_element_id";
                    button_html += elementId;
                    button_html += "' class='am-btn am-btn-success am-icon-remove' onclick='removeElement(";
                    button_html += elementId;
                    button_html += ")'>";
                    button_html += elementName+"("+order+")";
                    button_html += "</button>";
                    $("#span_element_list").append(button_html);
                }
            });
        }
    }
</script>
</body>
</html>
