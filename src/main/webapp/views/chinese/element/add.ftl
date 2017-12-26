<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">句子元素/句子成分</strong> / <small>添加</small></div>
            </div>

            <hr/>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                </div>

                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/book/element/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name" value="${obj.name}">
                                <small>句子元素/句子成分名称</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-type" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                                <select name="type" data-am-selected="{btnSize: 'sm'}">
                                    <#--实词-->
                                    <option value="1">名词</option>
                                    <option value="2">动词</option>
                                    <option value="3">形容词</option>
                                    <option value="4">数词</option>
                                    <option value="5">量词</option>
                                    <option value="6">代词</option>
                                    <#--虚词-->
                                    <option value="-1">副词</option>
                                    <option value="-2">介词</option>
                                    <option value="-3">连词</option>
                                    <option value="-4">助词</option>
                                    <option value="-5">叹词</option>
                                    <option value="-6">拟声词</option>
                                </select>
                                <small>句子元素/句子成分类型</small>
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
