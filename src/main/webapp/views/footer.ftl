<div class="am-modal am-modal-no-btn" tabindex="-1" id="div-modal">
    <div class="am-modal-dialog">
        <div class="am-modal-hd am-btn am-btn-primary am-btn-block">
            <label id="label-modal-title">Modal 标题</label>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd">
            <iframe id="iframe-modal-content" class="am-modal-bd" width="100%" height="550"></iframe>
        </div>
        <div class="am-btn-group">
            <span class="am-modal-btn" data-am-modal-cancel>关闭</span>
            <#--<span class="am-modal-btn" data-am-modal-confirm>提交</span>-->
        </div>
    </div>
</div>

<div class="am-modal am-modal-confirm" tabindex="-1" id="div-confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" id="div-confirm-title">Amaze UI</div>
        <div class="am-modal-bd" id="div-confirm-content"></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>

<div class="am-modal am-modal-alert" tabindex="-1" id="div-alert">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" id="div-alert-title">Amaze UI</div>
        <div class="am-modal-bd" id="div-alert-content">
            Hello world！
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn"data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<script>
    function myAlert(content,arg2,arg3){
        content = isEmpty(content) ? "确认继续操作" : content;
        var title = null;
        var onConfirm = null;
        if(isEmpty(arg2)){
            if(typeof arg2 == 'function'){
                onConfirm = arg2;
            }else if(typeof arg2 == 'string'){
                title = arg2
            }
        }
        if(isEmpty(arg3)){
            if(typeof arg3 == 'function'){
                onConfirm = arg3;
            }else if(typeof arg3 == 'string'){
                title = arg3
            }
        }

        title = isEmpty(title) ? "操作提示" : title;
        $("#div-alert-title").html(title);
        $("#div-alert-content").html(content);
        $('#div-alert').modal({
            relatedTarget: this,
            onConfirm: onConfirm
        });
    }
    function myConfirm(title,content,onConfirm,onCancel){
        title = isEmpty(title) ? "操作提示" : title;
        content = isEmpty(content) ? "确认继续操作" : content;
        $("#div-confirm-title").html(title);
        $("#div-confirm-content").html(content);
        $('#div-confirm').modal({
            relatedTarget: this,
            onConfirm: onConfirm,
            // closeOnConfirm: false,
            onCancel: onCancel
        });
    }
    function openModal(title, url, onConfirm, onCancel) {
        $("#label-modal-title").html(title);
        $("#iframe-modal-content").attr("src", url);
        $("#div-modal").modal({
            closeViaDimmer: 0,
            relatedTarget: this,
            onConfirm: function (e) {
                try {onConfirm();} catch (e) {}
            },
            onCancel: function (e) {
                try {onCancel();} catch (e) {}
            }
        });
    }
    function closeModal(){
        $("#div-modal").modal('close');
    }
</script>
<footer>
    <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>