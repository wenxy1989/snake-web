<#macro guid pageUrl page=1 type="normal">
<#if page?? && page.totalElements gt 0>
<div class="am-g">
    <#if type=="normal">
    <div class="am-u-sm-12 am-u-md-3 am-fl">
        <div class="am-input-group am-input-group-sm am-pagination am-fl">
            <span class="am-active">每页${page.size}条,共${page.totalElements}条${page.totalPages}页</span>
        </div>
    </div>
    </#if>
    <div class="am-u-sm-12 am-u-md-7">
        <ul class="am-pagination am-fr">
            <li <#if page.number==1> class="am-disabled"<#else> class="am-active"</#if>>
                <a href="javascript:void(0)" onclick="toPage(1)">«</a>
            </li>
            <#if page.number-2 gt 0>
                <li>
                    <a href="javascript:void(0)" onclick="toPage(${page.number-2})">${page.number-2}</a>
                </li>
            </#if>
            <#if page.number-1 gt 0>
                <li>
                    <a href="javascript:void(0)" onclick="toPage(${page.number-1})">上一页</a>
                </li>
            </#if>
            <li class="am-disabled">
                <span>${page.number}</span>
            </li>
            <#if page.number lt page.totalPages>
                <li>
                    <a href="javascript:void(0)" onclick="toPage(${page.number+1})">下一页</a>
                </li>
            </#if>
            <#if page.number+1 lt page.totalPages>
                <li>
                    <a href="javascript:void(0)" onclick="toPage(${page.number+2})">${page.number+2}</a>
                </li>
            </#if>
            <li<#if page.number lt page.totalPages> class="am-active"<#else> class="am-disabled"</#if>>
                <a href="javascript:void(0)" onclick="toPage(${page.totalPages})">»</a>
            </li>
        </ul>
    </div>
    <#if type=="normal">
    <div class="am-u-sm-12 am-u-md-2 am-fr">
        <div class="am-input-group am-input-group-sm am-pagination am-fl">
            <input type="number" id="input_jump_page" value="${page.number}" class="am-form-field">
        <span class="am-input-group-btn">
        <a href="javascript:void(0)" onclick="jumpPage()" class="am-btn am-btn-default">跳转</a>
        </span>
        </div>
    </div>
    </#if>
    <div>
        <form id="form_to_page" method="post" action="${request.contextPath}${pageUrl}" class="am-hide">
            <input id="input_to_page" name="pageNo" type="hidden"/>
            <#list RequestParameters?keys as key>
                <#if (key!="pageNo" && key !="ope_result" && RequestParameters[key]??)>
                    <input type="hidden" name="${key}" value="${RequestParameters[key]}"/>
                </#if>
            </#list>
            <button id="button_jump_page_submit" type="submit" class="am-hide"></button>
        </form>
        <script type="text/javascript">
            function toPage(pageNo){
                document.getElementById("input_to_page").value = pageNo;
                document.getElementById("button_jump_page_submit").click();
//                var _form = $("#form_to_page");
//                alert(_form.html());
//                _form.submit();
//                $("#form_to_page").trigger("submit");
            }
            function jumpPage(){
                var pageNo = document.getElementById("input_jump_page").value;
                if(0 < pageNo && pageNo < ${page.totalPages}){
                    toPage(pageNo);
                }
            }
            function nextPage(){
                var pageNo = ${page.number};
                if(pageNo < ${page.totalPages}){
                    toPage(++pageNo);
                }
            }
            function beforePage(){
                var pageNo = ${page.number};
                if(pageNo > 1){
                    toPage(--pageNo);
                }
            }
            /*window.onkeydown=function(event){
                if(event){
                    if(event.keyCode == 37){
                        beforePage();
                    }else if(event.keyCode == 39){
                        nextPage();
                    }
                }
            };*/
        </script>
    </div>
</div>
</#if>
</#macro>