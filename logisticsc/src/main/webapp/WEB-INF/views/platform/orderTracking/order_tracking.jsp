<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<link href="${configProps['resources']}/platform/css/order-tracking.css" rel="stylesheet">
<script src="${configProps['resources']}/platform/js/orderTracking.js"></script>
<title>订单跟踪</title>
</head>
<body style="background:#f5f5f5;">
<jsp:include page="../top.jsp"></jsp:include>
	<div class="container-self" style="width:1200px;">
		<div class="con">
			<div class="order-query f">
				<p>订单查询</p>
				<div class="order-con">
					<input type="text" id="condition"/>
				</div>
				<button id="chaxun">查询</button>
			</div>
			<div class="ad f">
				<img src="/logisticsc/resources/platform/img/pic1.png" />
			</div>
		</div>
		<div class="flow-chart" id="liuchengtu" style="visibility:hidden">
			<table>
				<!-- <tr>
					<td>
						<div class="line f" id="yijujuexian1"></div>
						<div class="circle f" id="yijujueyuan"></div>
						<div class="line-ver f" id="yijujuexian2"></div>
						<div class="fon-deny">
							<span id="yijujue">已拒绝</span>
						</div>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr> -->
				<tr id="aaaaaaa">
					<!--  <td>
						<div class="circle circle1 f" id="daishouliyuan" style="z-index:999;"></div>
						<div class="fon-deny fon-shouli">
							<span id="tracking1">预约中</span>
						</div>
					</td>
					<td>
						<div class="line f" id="yishoulixian" style="left: -60xp;"></div>
						<div class="circle f" id="yishouliyuan"></div>
						<div class="fon-deny fon-shouli">
							<span id="tracking2">已受理</span>
						</div>
					</td>
					<td>
						<div class="line f" id="tihuoxian"></div>
						<div class="circle f" id="tihuoyuan"></div>
						<div class="fon-deny">
							<span id="tracking3">提货中</span>
						</div>
					</td>
					<td>
						<div class="line f" id="huowurukuxian"></div>
						<div class="circle f" id="huowurukuyuan"></div>
						<div class="fon-deny">
							<span id="tracking4">议价中</span>
						</div>
					</td>
					<td>
						<div class="line f" id="huowurukuxian"></div>
						<div class="circle f" id="huowurukuyuan"></div>
						<div class="fon-deny">
							<span id="tracking5">已开单入库</span>
						</div>
					</td>
					<td>
						<div class="line f" id="yunshuzhongxian"></div>
						<div class="circle f" id="yunshuzhongyuan"></div>
						<div class="fon-deny">
							<span id="tracking6">运输中</span>
						</div>
					</td>
					<td>
						<div class="line f" id="yidaodaxian"></div>
						<div class="circle f" id="yidaodayuan"></div>
						<div class="fon-deny">
							<span id="tracking7">已到站</span>
						</div>
					</td>
					<td>
						<div class="line f" id="songhuozhongxian"></div>
						<div class="circle f" id="songhuozhongyuan"></div>
						<div class="fon-deny">
							<span id="tracking8">送货中</span>
						</div>
					</td>
					<td>
						<div class="line f" id="yiqianshouxian"></div>
						<div class="circle f" id="yiqianshouyuan"></div>
						<div class="fon-deny">
							<span id="tracking9">已签收</span>
						</div>
					</td>
				</tr> -->
				<!-- <tr>
					<td>
						<div class="line-ver1 line-ver f" id="yizuofeixian1"></div>
						<div class="line f" id="yizuofeixian2"></div>
						<div class="circle f" id="yizuofeiyuan"></div>
						<div class="fon-deny">
							<span id="yizuofei">已作废</span>
						</div>
					</td>
					<td>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr> -->
			</table>
		</div>
		<div id="getOrder">
		</div>
		<div id="getOrderTracking">
		</div>
	</div>
	<input id="conditions" type="hidden" value='${condition}'/>
	  <div style="clear: both;"></div>
      <div class="footer">	
		 <jsp:include page="../bottom.jsp"></jsp:include>
	  </div>
	
</body>
</html>