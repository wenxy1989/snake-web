<html>
<#include "/head.ftl"/>
<#import "/template/common.ftl" as t_com/>
<@i_com.selectTypeFunction/>
<body>
<#include "/header.ftl"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong class="am-text-primary am-text-lg">模板信息</strong> /
                <small>添加</small>
            </div>
        </div>

        <div class="am-g">

            <div class="am-u-sm-12 am-u-md-10">
                <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/template/info/add">

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">分组 / Group</label>

                        <div class="am-u-sm-9">
                            <select name="group" data-am-selected="{btnSize: 'sm'}" required>
                                <option value="application">application</option>
                                <option value="config">config</option>
                                <option value="controller">controller</option>
                                <option value="dao">dao</option>
                                <option value="entity">entity</option>
                                <option value="inter">inter</option>
                                <option value="java">java</option>
                                <option value="page">page</option>
                                <option value="sql">sql</option>
                            </select>
                            <small>模板分组/模板所在文件夹</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">名称 / Name</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="name" placeholder="名称 / Name" required>
                            <small>模板文件名</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">生成路径模板</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="savePathModel" placeholder="生成路径模板" required>
                            <small>生成路径模板，按照此规则设定生成的路径</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">生成文件模板</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="saveFileModel" placeholder="生成文件模板" required>
                            <small>生成文件模板，按照此规则设定生成的文件名</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">更新类型 / UpdateType</label>

                        <div class="am-u-sm-9">
                            <select name="updateType" data-am-selected="{btnSize: 'sm'}" required>
                                <option value="0" selected="selected">每次更新</option>
                                <option value="1">没有时创建</option>
                            </select>
                            <small>更新策略</small>
                        </div>
                    </div>

                    <@t_com.selectFrame/>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">类型 / Type</label>

                        <div class="am-u-sm-9">
                            <select name="type" data-am-selected="{btnSize: 'sm'}" required>
                                <option value="application">application</option>
                                <option value="model">model</option>
                            </select>
                            <small>模板文件类型:application-应用相关,model-模块相关</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">备注 / Remark</label>

                        <div class="am-u-sm-9">
                            <textarea rows="5" name="remark" placeholder="备注 / Remark"></textarea>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="submit" class="am-btn am-btn-primary">保存</button>
                            <button type="button" onclick="history.go(-1);return false;" class="am-btn am-btn-active">
                                返回
                            </button>
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
