<html xmlns="http://www.w3.org/1999/html">
<#include "/head.ftl"/>
<body>
<script type="text/javascript">
    function toInit(){
        window.location.href = '${request.contextPath}/image/initDir';
    }
    function toDetails(){
        window.location.href = "${request.contextPath}/image/details";
    }
</script>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">图片</strong> / <small>列表</small></div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <a class="am-btn am-btn-default" onclick="toInit()">
                            <span class="am-icon-refresh">初始化目录</span>
                        </a>
                        <a class="am-btn am-btn-default" onclick="toDetails()">
                            <span class="am-icon-save">详细</span>
                        </a>
                    </div>
                </div>
            </div>
            <form action="${request.contextPath}/image/page" method="post">
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-form-group">
                    <select name="ext" data-am-selected="{btnSize: 'sm'}">
                        <option value="">select</option>
                        <option value=".jpg">jpg</option>
                        <option value=".png">png</option>
                        <option value=".jpeg">jpeg</option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-input-group am-input-group-sm">
                    <input type="text" name="name" class="am-form-field">
                    <span class="am-input-group-btn">
                        <button class="am-btn am-btn-default" type="submit">搜索</button>
                    </span>
                </div>
            </div>
            </form>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">

            <#if page?? && page.content>
                <#--<ul class="am-avg-sm-2 am-avg-md-4 am-avg-lg-6 am-margin gallery-list">-->
                <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default" data-am-gallery="{ pureview: true }">
                    <#list page.content as obj>
                        <#--<li>
                            <a href="#">
                                <img class="am-img-thumbnail am-img-bdrs" src="${request.contextPath}/fileServlet?type=image&imageId=${obj.id}" alt=""/>
                                <div class="gallery-title">${obj.name}</div>
                                <div class="gallery-desc">${obj.createdTime}</div>
                            </a>
                        </li>-->
                        <li>
                            <div class="am-gallery-item">
                                <a href="${request.contextPath}/image/details?start=${(page.number-1)*page.size+obj_index}">
                                    <img src="${request.contextPath}/fileServlet?type=image&imageId=${obj.id}" alt="${obj.name}" />
                                    <h3 class="am-gallery-title">${obj.name}</h3>
                                    <div class="am-gallery-desc">${obj.createdTime}</div>
                                </a>
                            </div>
                        </li>
                    </#list>
                </ul>
                <div class="am-cf">
                    <#import "/common/pager.ftl" as pager>
                        <@pager.guid pageUrl="/image/page" page=page />
                </div>
            </#if>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
