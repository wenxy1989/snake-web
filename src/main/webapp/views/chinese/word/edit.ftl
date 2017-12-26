<html>
<#include "/head.ftl"/>
<body>
    <#include "/header.ftl"/>
    <div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">词组</strong> / <small>修改</small></div>
            </div>

            <div class="am-cf am-container">
                <form id="form_type" class="am-form am-form-horizontal" method="post" action="${request.contextPath}/book/word/edit">
                    <input type="hidden" name="id" value="${obj.id}">
                    <input type="hidden" name="typeId" value="${obj.typeId}">

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="submit" class="am-btn am-btn-primary">保存</button>
                            <button onclick="history.go(-1)" class="am-btn am-btn-primary">返回</button>
                        </div>
                    </div>
                    <div class="am-form-group">
                        <label for="user-name" class="am-u-sm-3 am-form-label">词组 / Word</label>
                        <div class="am-u-sm-9">
                            <input type="text" name="word" placeholder="词组 / Word" value="${obj.word}">
                            <small>词组显示内容</small>
                        </div>
                    </div>
                    <div class="am-form-group">
                        <label for="user-name" class="am-u-sm-3 am-form-label">当前类型 / Word</label>
                        <div class="am-u-sm-9">
                        <span><#list book_type_list as type>
                        <#if type.id == obj.typeId>${type.name}<#break></#if>
                        </#list>
                        </span>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <table class="am-table am-table-bordered am-table-striped am-table-hover">
                        <#list book_type_list as type>
                            <tr>
                                <td class="am-center"><a href="#" onclick="updateType(${type.id})">更改为此类型</a></td>
                                <td>${type.name}</td>
                                <td class="am-center"><a href="#" onclick="addType(${type.id})">添加此类型</a></td>
                            </tr>
                        </#list>
                        </table>
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
        <!-- content end -->
    </div>
    <#include "/footer.ftl"/>
<script type="text/javascript">
    function addType(){

    }
    function updateType(typeId){
        $("input[name='typeId']").val(typeId);
        $("#form_type").submit();
    }
</script>
</body>
</html>
