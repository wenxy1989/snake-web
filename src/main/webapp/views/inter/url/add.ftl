<#import "/inter/common.ftl" as i_com/>
<html>
<#include "/head.ftl"/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

        <!-- content start -->
        <div class="admin-content">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">接口</strong> / <small>添加</small></div>
            </div>

            <div class="am-g">

                <div class="am-u-sm-12 am-u-md-10">
                    <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/inter/url/${applicationId}/add">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">名称 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="name" placeholder="名称 / Name" required>
                                <small>接口名称，便于使用者理解接口功能</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label">接口方法  / 编码</label>
                            <div class="am-u-sm-9">
                                <input type="text" name="url" placeholder="输入完整的网络访问地址 / Url" required>
                                <small>输入完整的网络访问地址</small>
                            </div>
                        </div>

                        <@i_com.selectFrame />

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">类型 / Type</label>
                            <div class="am-u-sm-9">
                                <select name="type" data-am-selected="{btnSize: 'sm'}">
                                    <option value="0">GET</option>
                                    <option value="1" selected>POST</option>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">上传数据类型 / UploadType</label>
                            <div class="am-u-sm-9">
                                <select name="uploadType" data-am-selected="{btnSize: 'sm'}">
                                    <option value="1">Json</option>
                                    <option value="2">Json Array</option>
                                    <option value="3">java.lang</option>
                                    <option value="4">java.lang Array</option>
                                </select>
                                <small>仅当上传参数属性唯一时可以选择 java.lang和java.lang Array</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">结果数据类型 / ResultType</label>
                            <div class="am-u-sm-9">
                                <select name="resultType" data-am-selected="{btnSize: 'sm'}">
                                    <option value="1">Json</option>
                                    <option value="2">Json Array</option>
                                    <option value="3">java.lang</option>
                                    <option value="4">java.lang Array</option>
                                </select>
                                <small>仅当上传参数属性唯一时可以选择 java.lang和java.lang Array</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">处理逻辑 / Logic</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="logic" placeholder="处理逻辑 / Logic" required></textarea>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">备注 / Remark</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" name="remark" placeholder="备注 / Remark"></textarea>
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
