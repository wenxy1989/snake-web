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
                <small>修改</small>
            </div>
        </div>

        <div class="am-g">

            <div class="am-u-sm-12 am-u-md-10">
                <form class="am-form am-form-horizontal" method="post" action="${request.contextPath}/template/info/edit">

                    <input type="hidden" name="id" value="${object.id}">
                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">名称 / Name</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="name" placeholder="名称 / Name" value="${object.name}" required>
                            <small>模板文件名</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">分组 / Group</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="group" placeholder="分组 / Group" value="${object.group}" required>
                            <small>模板分组/模板所在文件夹</small>
                        </div>
                    </div>

                    <@t_com.selectGroup/>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">类型 / Type</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="type" placeholder="类型 / Type" value="${object.type}" required>
                            <small>模板类型/生成文件类型/包含技术</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">更新类型 / UpdateType</label>

                        <div class="am-u-sm-9">
                            <select name="updateType" data-am-selected="{btnSize: 'sm'}" required>
                                <option value="0"<#if parameter.updateType==0> selected="selected" </#if>>每次更新</option>
                                <option value="1"<#if parameter.updateType==1> selected="selected" </#if>>没有时创建</option>
                            </select>
                            <small>更新策略</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">生成路径模板</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="savePathModel" placeholder="生成路径模板" value="${object.savePathModel}" required>
                            <small>生成路径模板，按照此规则设定生成的路径</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">生成文件模板</label>

                        <div class="am-u-sm-9">
                            <input type="text" name="saveFileModel" placeholder="生成文件模板" value="${object.saveFileModel}" required>
                            <small>生成文件模板，按照此规则设定生成的文件名</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <label class="am-u-sm-3 am-form-label">备注 / Remark</label>

                        <div class="am-u-sm-9">
                            <textarea rows="5" name="remark" placeholder="备注 / Remark">${object.remark}</textarea>
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
