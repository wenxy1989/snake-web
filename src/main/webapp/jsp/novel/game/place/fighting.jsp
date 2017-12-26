<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/header.jsp"%>
<html>
<head>
<title>战斗</title>
<style type="text/css">
td {margin:5px;padding:5px;}
</style>
</head>
<body>
    <div class="i_c">
    	<div class="b_05"></div>
        <div class="b_04"></div>
        <div class="b_03"></div>
        <div class="b_02"></div>
        <div class="i_cont">
        	<ul class="r_con03">
            	<li class="unborder" >
            	<table style="width:100%">
            	<tbody>
            	<tr>
            	<td style="width:50%">
            	<table>
            		<tbody>
            			<tr>
            				<td colspan="2">
            				<p style="text-indent:2em;">${currentCharater.name }</p>
            				</td>
            			</tr>
            			<tr>
            				<td>${currentCharater.race.name }</td>
            				<td>${currentCharater.family.name }</td>
            			</tr>
            			<tr>
            				<td>${currentCharater.longth }</td>
            				<td>${currentCharater.width }</td>
            			</tr>
            			<tr>
            				<td>${currentCharater.height }</td>
            				<td>${currentCharater.weight }</td>
            			</tr>
            			<tr>
            				<td>${currentCharater.energy }</td>
            				<td>${currentCharater.spirit }</td>
            			</tr>
            			<tr>
            				<td>${currentCharater.hardness }</td>
            			</tr>
            			<tr>
            				<td>生命值</td>
            				<td>${currentCharater.live }</td>
            			</tr>
            			<tr>
            				<td>攻击力</td>
            				<td>${currentCharater.atk }</td>
            			</tr>
            			<tr>
            				<td>护甲</td>
            				<td>${currentCharater.armor }</td>
            			</tr>
            		</tbody>
            	</table>
            	</td>
            	<td style="width:50%">
            	<table>
            		<tbody>
            			<tr>
            				<td colspan="2">
            				<p style="text-indent:2em;">${enemy.name }</p>
            				</td>
            			</tr>
            			<tr>
            				<td>${enemy.race.name }</td>
            				<td>${enemy.family.name }</td>
            			</tr>
            			<tr>
            				<td>${enemy.longth }</td>
            				<td>${enemy.width }</td>
            			</tr>
            			<tr>
            				<td>${enemy.height }</td>
            				<td>${enemy.weight }</td>
            			</tr>
            			<tr>
            				<td>${enemy.energy }</td>
            				<td>${enemy.spirit }</td>
            			</tr>
            			<tr>
            				<td>${enemy.hardness }</td>
            			</tr>
            			<tr>
            				<td>生命值</td>
            				<td>${enemy.live }</td>
            			</tr>
            			<tr>
            				<td>攻击力</td>
            				<td>${enemy.atk }</td>
            			</tr>
            			<tr>
            				<td>护甲</td>
            				<td>${enemy.armor }</td>
            			</tr>
            		</tbody>
            	</table>
            	</td>
            	</tr>
            	<tr>
            		<td colspan="2"><a href="${basePath }/game/do_action.do">攻击</a></td>
            	</tr>
            	</tbody>
            	</table>
            	</li>
            </ul>
        </div>
        <div class="b_02"></div>
        <div class="b_03"></div>
        <div class="b_04"></div>
        <div class="b_05"></div>
	</div>
</body>
</html>