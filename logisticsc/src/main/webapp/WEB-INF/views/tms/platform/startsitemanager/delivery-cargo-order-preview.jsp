<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提货单</title>
</head>
<body>
	<div style="text-align: right;">
		<button class="button" onclick="printPage()">打印</button>
	</div>
	<div id="printBox">
		<div style="font-size: 34px; font-weight: bold; text-align: center; font-family: '微软雅黑'">
			<span>${ladingOrder.companyName}</span>
		</div>
		<div style="width: 1080px; margin: auto; border: 2px solid black;font-family: '微软雅黑';">
			<div style="border-bottom: 1px solid black;">
				<table style="width: 100%; font-size: 20px;" border="0">
					<tr>
						<td style="text-align: right; width: 100px;">运单号：</td>
						<td style="width: 200px;">${ladingOrder.wayBillNumber}</td>
						<td style="text-align: right; width: 100px;">订单号：</td>
						<td style="width: 200px;">${ladingOrder.orderNumber}</td>
						<td style="text-align: right; width: 110px;">出发网点：</td>
						<td>${ladingOrder.startOutletsName}</td>
						<td style="text-align: right; width: 110px;">到达网点：</td>
						<td>${ladingOrder.targetOutletsName}</td>
					</tr>
					<tr>
						<td style="text-align: right;">目的地：</td>
						<td colspan="7">${ladingOrder.targetProvinceName} ${ladingOrder.targetCityName} ${ladingOrder.targetCountyName}</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black; font-size: 20px;">
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<td style="text-align: right; width: 100px;">发货人：</td>
						<td style="width: 200px;">${ladingOrder.consignor}</td>
						<td style="text-align: right; width: 100px;">发货电话：</td>
						<td style="width: 200px;">${ladingOrder.consignorMobile}</td>
						<td style="text-align: right; width: 110px;">发货单位：</td>
						<td colspan="3">${ladingOrder.consignorCompany}</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100px;">发货地址：</td>
						<td colspan="7">${ladingOrder.consignorAddress}</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100px;">收货人：</td>
						<td style="width: 200px;">${ladingOrder.consignee}</td>
						<td style="text-align: right; width: 100px;">收货电话：</td>
						<td style="width: 200px;">${ladingOrder.consigneeMobile}</td>
						<td style="text-align: right; width: 100px;">收货单位：</td>
						<td colspan="3">${ladingOrder.consigneeCompany}</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100px;">收货地址：</td>
						<td colspan="7">${ladingOrder.consigneeAddress}</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black; font-size: 20px;">
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<td style="text-align: right; width: 100px;">司机姓名：</td>
						<td style="width: 200px;">${ladingOrder.driverName}</td>
						<td style="text-align: right; width: 100px;">手机号：</td>
						<td style="width: 200px;">${ladingOrder.driverMobile}</td>
						<td style="text-align: right; width: 110px;">车牌号：</td>
						<td colspan="3">${ladingOrder.licensePlateNo}</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">货物信息</p>
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<th>货物名称</th>
						<th>品牌</th>
						<th>型号</th>
						<th>包装</th>
						<th>件数</th>
						<th>套数</th>
						<th>重量(t)</th>
						<th>体积(m³)</th>
					</tr>
					<c:forEach items="${ladingOrderCargoInfos}" var="ladingOrderCargoInfo">
						<tr>
							<td style="text-align: center;">${ladingOrderCargoInfo.name}</td>
							<td style="text-align: center;">${ladingOrderCargoInfo.brand}</td>
							<td style="text-align: center;">${ladingOrderCargoInfo.model}</td>
							<td style="text-align: center;">${ladingOrderCargoInfo.packageTypeName}</td>
							<td style="text-align: center;">${ladingOrderCargoInfo.number}</td>
							<td style="text-align: center;">${ladingOrderCargoInfo.setNumber}</td>
							<td style="text-align: center;">${ladingOrderCargoInfo.totalWeight}</td>
							<td style="text-align: center;">${ladingOrderCargoInfo.totalVolume}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">服务项目</p>
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<td style="text-align: right;">支付方式：</td>
						<td>${ladingOrder.payMethodName}</td>
						<td style="text-align: right;">代收货款：</td>
						<td>${ladingOrder.agencyFund}元</td>
						<td style="text-align: right;">垫付货款：</td>
						<td>${ladingOrder.advanceCost}元</td>
						<td style="text-align: right;">等款放货：</td>
						<c:if test="${ladingOrder.isWaitPay == 0}">
							<td>否</td>
						</c:if>
						<c:if test="${ladingOrder.isWaitPay == 1}">
							<td>是</td>
						</c:if>
						<td style="text-align: right;">投保金额：</td>
						<td>${ladingOrder.insuranceMoney}元</td>
					</tr>
					<tr>
						<td style="text-align: right;">发货方式：</td>
						<td>上门取货</td>
						<td style="text-align: right;">收货方式：</td>
						<c:if test="${ladingOrder.receiveType == 0}">
							<td>自提</td>
						</c:if>
						<c:if test="${ladingOrder.receiveType == 1}">
							<td>送货上门</td>
						</c:if>
						<td style="text-align: right;">回单/收条：</td>
						<td>${ladingOrder.receiptSlipNum}</td>
						<td style="text-align: right;">发票类型：</td>
						<c:if test="${ladingOrder.receiptType == 0}">
							<td>不需要</td>
						</c:if>
						<c:if test="${ladingOrder.receiptType == 1}">
							<td>普通发票</td>
						</c:if>
						<c:if test="${ladingOrder.receiptType == 2}">
							<td>增值税发票</td>
						</c:if>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">费用信息</p>
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<td style="text-align: right;" width="100px">提货费：</td>
						<td>${ladingOrder.takeCargoCost}元</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">备注信息</p>
				<div style="width: 100%; font-size: 20px; padding: 2px 5px;">${ladingOrder.remark}</div>
			</div>
			<div>
				<table style="width: 100%;font-size: 20px;">
					<tr>
						<td style="text-align: right;">预约提货时间：</td>
						<td><fmt:formatDate value="${ladingOrder.deliveryDate}" pattern="yyyy-MM-dd"/> ${ladingOrder.deliveryStartTime} - ${ladingOrder.deliveryEndTime}</td>
						<td style="text-align: right;">派车时间：</td>
						<td><fmt:formatDate value="${ladingOrder.ladingOrderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td style="text-align: right;">派车网点：</td>
						<td>${ladingOrder.startOutletsName}</td>
						<td style="text-align: right;">开单员：</td>
						<td>${ladingOrder.ladingOrderPerson}</td>
					</tr>
				</table>
				<div style="text-align: center; margin: 3px auto;">
					专线营运系统
				</div>
			</div>
		</div>
		
	</div>
	<!-- 增值服务配置 数据 -->
	<input type="hidden" id="collectionDeliveryRatio" value="${additionalServer.collectionDeliveryRatio}">
	<input type="hidden" id="lineInsuranceRatio" value="${additionalServer.lineInsuranceRatio}">
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery.PrintArea.js"></script>
	
	<script type="text/javascript">
	function printPage(){
		$('#printBox').printArea();
	}
	</script>
</body>
</html>