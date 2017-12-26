<html xmlns="http://www.w3.org/1999/html">
<#include "/head.ftl"/>
<body>
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
                        <button type="button" class="am-btn am-btn-default" onclick="window.location.href='${request.contextPath}/image/initDir'"><span class="am-icon-plus"></span>初始化目录</button>
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
                    </div>
                </div>
            </div>
            <form action="${request.contextPath}/image/details" method="post">
                <div class="am-u-sm-12 am-u-md-3">
                    <div class="am-form-group">
                        <select name="ext" data-am-selected="{btnSize: 'sm'}">
                            <option value=""></option>
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
                <#--<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default" data-am-gallery="{ pureview: true }">-->
                    <#list page.content as obj>
                        <#--<li>
                            <div class="am-gallery-item">
                                <a href="#">
                                    <img src="${request.contextPath}/fileServlet?type=image&imageId=${obj.id}" alt="${obj.name}" />
                                    <h3 class="am-gallery-title">${obj.name}</h3>
                                    <div class="am-gallery-desc">${obj.createdTime}</div>
                                </a>
                            </div>
                        </li>-->
                    <#if obj_index == 0>
                        <div id="demo-view" data-backend-compiled="">
                            <figure data-am-widget="figure" class="am am-figure am-figure-one am-no-layout" data-am-figure="{  pureview: 'auto' }">
                            <img src="${request.contextPath}/fileServlet?type=image&imageId=${obj.id}" data-rel="${request.contextPath}/fileServlet?type=image&imageId=${obj.id}" alt="${obj.name}">
                            <figcaption class="am-figure-capition-btm">${obj.name}</figcaption>
                            </figure>
                        </div>
                    </#if>
                    </#list>
                <#--</ul>-->
            </#if>
                <form class="am-form">
                <#if page?? && page.totalElements gt 0>
                    <div class="am-cf">
                        共 ${page.totalElements} 条记录
                        <div class="am-fr">
                            <ul class="am-pagination">
                                <#if page.start gt 1>
                                    <li><a href="${request.contextPath}/image/details?start=0">«</a></li>
                                </#if>
                                <#if page.start gt 3>
                                    <li><a href="${request.contextPath}/image/details?start=${page.start-4}">${page.start-3}</a></li>
                                </#if>
                                <#if page.start gt 2>
                                    <li><a href="${request.contextPath}/image/details?start=${page.start-3}">${page.start-2}</a></li>
                                </#if>
                                <#if page.start gt 1>
                                    <li><a href="${request.contextPath}/image/details?start=${page.start-2}">${page.start-1}</a></li>
                                </#if>
                                <#if page.start gt 0>
                                    <li><a href="${request.contextPath}/image/details?start=${page.start-1}">${page.start}</a></li>
                                </#if>
                                <#list page.content as obj>
                                    <li<#if obj_index==0> class="am-active"</#if>><a href="${request.contextPath}/image/details?start=${page.start+obj_index}">${page.start+obj_index+1}</a></li>
                                </#list>
                                <#if page.start < page.totalElements-1>
                                <li><a href="${request.contextPath}/image/details?start=${page.totalElements-1}">»</a></li>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </#if>
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
