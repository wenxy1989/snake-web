﻿<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<title>Sewise Player</title>
	<style type="text/css">
		body{
			margin: 0px;
			padding: 0px;
		}
	</style>
</head>
<body>
	<div style="width: 640px; height: 352px; margin:50px; padding: 20px; border:5px solid #999;">
		<script type="text/javascript" src="${request.contextPath}/sewise-player/player/sewise.player.min.js?server=vod&type=http&videourl=${request.contextPath}http://127.0.0.1/${video.filePath?replace("\\","/")?replace("M:/movie/","")}&sourceid=&autostart=true&starttime=0&lang=en_US&logo=http://onvod.sewise.com/libs/swfplayer/skin/images/logo.png&title=${video.name}&buffer=5&claritybutton=disable&skin=vodFlowPlayer&topbardisplay=disable"></script>
	</div>
	<div style="padding: 10px 10px;">注：如果当前浏览器不支持HTML5，将自动启用FLASH播放。</div>
	<div style="padding: 0px 10px;"><a href="https://github.com/jackzhang1204/sewise-player" target="_blank">Fock it on GitHub</a></div>
</body>
</html>