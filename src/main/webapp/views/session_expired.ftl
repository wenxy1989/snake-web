<html>
<#include "/head.ftl"/>
<script type="text/javascript">
function initPage(){
    if(isRoot()){
        window.setTimeout(function(){window.location.href="${request.contextPath}/";},2*1000);
    }else if(parent && parent.location && parent.location.href){
        parent.location.href = "${request.contextPath}/sessionExpired";
    }
}
</script>
<body onload="initPage()">
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">SESSION已过期</strong></div>
        </div>
        <hr/>
        <div class="am-u-sm-12 am-u-md-8">当前SESSION已过期,两秒后自动跳转到首页</div>
    </div>
</div>
<!-- content end -->
</div>
<#include "/footer.ftl"/>
</body>
</html>
