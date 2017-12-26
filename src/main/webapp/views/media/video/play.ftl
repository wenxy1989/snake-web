<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <style type="text/css">
        body {margin:0px; padding:20px;  margin-top:0px;font-size:12px; color:#313131; }
        div,ul,li,ol,dl,dt,dd,form,img,p{margin: 0; padding: 0; border: 0; }
        li{list-style-type:none;}
        h2,h4,h5{margin: 0; padding: 0; font-size:14px;}
        div.help { line-height:26px; font-size:14px;}
    </style>
    <!-- 我爱播放器(52player.com)/代码开始 -->
    <script type="text/javascript" src="${request.contextPath}/CuPlayer/images/swfobject.js"></script>
</head>
<body>
<div class="video" id="CuPlayer">
    <strong>我爱播放器(52player.com) 提示：您的Flash Player版本过低，请<a href="http://www.52player.com/">点此进行网页播放器升级</a>！</strong>
</div>
<script type="text/javascript">
    var so = new SWFObject("${request.contextPath}/CuPlayer/CuPlayerMiniV4.swf","CuPlayerV4","600","410","9","#000000");
    so.addParam("allowfullscreen","true");
    so.addParam("allowscriptaccess","always");
    so.addParam("wmode","opaque");
    so.addParam("quality","high");
    so.addParam("salign","lt");
    so.addVariable("CuPlayerSetFile","${request.contextPath}/CuPlayer/CuPlayerSetFile.xml"); //播放器配置文件地址,例SetFile.xml、SetFile.asp、SetFile.php、SetFile.aspx
    <#--so.addVariable("CuPlayerFile","${request.contextPath}/videoServlet?videoId=${video.id}"); //视频文件地址?-->
    so.addVariable("CuPlayerFile","${video.filePath}"); //视频文件地址?
    so.addVariable("CuPlayerImage","${request.contextPath}/CuPlayer/images/start.jpg");//视频略缩图,本图片文件必须正确
    so.addVariable("CuPlayerWidth","600"); //视频宽度
    so.addVariable("CuPlayerHeight","410"); //视频高度
    so.addVariable("CuPlayerAutoPlay","yes"); //是否自动播放
    so.addVariable("CuPlayerLogo","${request.contextPath}/CuPlayer/images/logo.png"); //Logo文件地址
    so.addVariable("CuPlayerPosition","bottom-right"); //Logo显示的位置
    so.write("CuPlayer");
</script>
<div class="am-cf admin-main">
    <button onclick="history.go(-1)">返回</button>
</div>
</body>
</html>
