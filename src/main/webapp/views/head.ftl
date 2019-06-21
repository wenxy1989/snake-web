<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
<#--    <link rel="stylesheet" href="${request.contextPath}/assets/css/amazeui.min.css"/>-->
<#--    <link rel="stylesheet" href="${request.contextPath}/assets/css/admin.css">-->
    <link rel="stylesheet" href="${request.contextPath}/libs/amazeui-2.7.2/css/admin.css"/>
    <link rel="stylesheet" href="${request.contextPath}/libs/amazeui-2.7.2/css/amazeui.css"/>
    <!--[if lt IE 9]>
    <script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="assets/js/polyfill/rem.min.js"></script>
    <script src="assets/js/polyfill/respond.min.js"></script>
    <script src="assets/js/amazeui.legacy.js"></script>
    <![endif]-->

    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="${request.contextPath}/libs/jquery.min.js"></script>
    <#--<script src="${request.contextPath}/libs/jquery/jquery-1.10.2.js"></script>-->
    <script src="${request.contextPath}/assets/js/amazeui.min.js"></script>
    <!--<![endif]-->
    <script src="${request.contextPath}/assets/js/app.js"></script>
    <script type="text/javascript">
        function isEmpty(str){
            return !str || str == null || str == undefined || str == "";
        }
        function notBlank(str){
            return str && str.length > 0 && str.trim().length > 0;
        }
        function toLogin(){
            window.location.href="${request.contextPath}/";
        }
        var is_root_flag_number = 0;
        function isRoot(){
            is_root_flag_number ++;
            //parent is self, so it is root
            var is_root = (is_root_flag_number == parent.is_root_flag_number);
            //if it is not root(not itself),it has parent and parent has isRoot
            is_root = is_root ? is_root : (!parent || !parent.isRoot);
            return is_root;
        }
    </script>
</head>