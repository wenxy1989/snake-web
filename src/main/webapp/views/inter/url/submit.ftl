<#import "/inter/common.ftl" as i_com/>
<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">接口</strong> / <small>提交</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-10">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/inter/url/${applicationId}/submit">
                        <input type="hidden" name="id" value="${url.id}">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name" value="${url.name}">
                                <small>接口名称，便于使用者理解接口功能</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">接口方法  / 编码</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="url" placeholder="输入完整的网络访问地址 / Url" value="${url.url}">
                                <small>输入完整的网络访问地址</small>
                            </div>
                        </div>

                    <@i_com.selectGroup value=url.groupId />

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                                <select name="type" data-am-selected="{btnSize: 'sm'}"  value="${url.type}">
                                    <option value="0">GET</option>
                                    <option value="1">POST</option>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">上传数据类型 / UploadType</label>
                            <div class="am-u-sm-9">
                                <select name="uploadType" data-am-selected="{btnSize: 'sm'}">
                                    <option value="1"<#if url.uploadType==1> selected </#if>>Json</option>
                                    <option value="2"<#if url.uploadType==2> selected </#if>>Json Array</option>
                                    <option value="3"<#if url.uploadType==3> selected </#if>>java.lang</option>
                                    <option value="4"<#if url.uploadType==4> selected </#if>>java.lang Array</option>
                                </select>
                                <small>仅当上传参数属性唯一时可以选择 java.lang和java.lang Array</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">结果数据类型 / ResultType</label>
                            <div class="am-u-sm-9">
                                <select name="resultType" data-am-selected="{btnSize: 'sm'}">
                                    <option value="1"<#if url.resultType==1> selected </#if>>Json</option>
                                    <option value="2"<#if url.resultType==2> selected </#if>>Json Array</option>
                                    <option value="3"<#if url.resultType==3> selected </#if>>java.lang</option>
                                    <option value="4"<#if url.resultType==4> selected </#if>>java.lang Array</option>
                                </select>
                                <small>仅当上传参数属性唯一时可以选择 java.lang和java.lang Array</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">上传数据样例 / uploadExample</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="uploadExample" placeholder="上传数据样例 / uploadExample">${url.uploadExample}</textarea>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">结果数据样例 / resultExample</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="resultExample" placeholder="结果数据样例 / resultExample">${url.resultExample}</textarea>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">处理逻辑 / Logic</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="logic" placeholder="处理逻辑 / Logic">${url.logic}</textarea>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / Remark</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="remark" placeholder="备注 / Remark">${url.remark}</textarea>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <div class="am-u-sm-9 am-u-sm-push-3">
                                <button type="submit" class="am-btn am-btn-primary">保存</button>
                                <button type="button" onclick="history.go(-1);return false;" class="am-btn am-btn-active">返回</button>
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
