<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运单</title>
</head>
<body>
	<div style="text-align: right;">
		<button class="button" onclick="printPage()">打印</button>
	</div>
	<div id="printBox">
		<div style="font-size: 34px; font-weight: bold; text-align: center; font-family: '微软雅黑'">
			<span>${wayBillOrder.companyName}</span>
		</div>
		<div style="width: 1080px; margin: auto; border: 2px solid black;font-family: '微软雅黑';">
			<div style="border-bottom: 1px solid black;">
				<table style="width: 100%; font-size: 20px; line-height: 1.6em;" border="0">
					<tr>
						<td style="text-align: right; width: 100px;">运单号：</td>
						<td style="width: 200px;">${wayBillOrder.wayBillNumber}</td>
						<td style="text-align: right; width: 100px;">订单号：</td>
						<td style="width: 200px;">${wayBillOrder.orderNumber}</td>
						<td style="text-align: right; width: 110px;">出发网点：</td>
						<td>${wayBillOrder.startOutletsName}</td>
						<td style="text-align: right; width: 110px;">到达网点：</td>
						<td>${wayBillOrder.targetOutletsName}</td>
					</tr>
					<tr>
						<td style="text-align: right;">目的地：</td>
						<td colspan="7">${wayBillOrder.targetProvinceName} ${wayBillOrder.targetCityName} ${wayBillOrder.targetCountyName}</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black; font-size: 20px;">
				<table style="width: 100%; font-size: 20px; line-height: 1.6em;">
					<tr>
						<td style="text-align: right; width: 100px;">发货人：</td>
						<td style="width: 200px;">${wayBillOrder.consignor}</td>
						<td style="text-align: right; width: 100px;">发货电话：</td>
						<td style="width: 200px;">${wayBillOrder.consignorMobile}</td>
						<td style="text-align: right; width: 110px;">发货单位：</td>
						<td colspan="3">${wayBillOrder.consignorCompany}</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100px;">发货地址：</td>
						<td colspan="7">${wayBillOrder.consignorAddress}</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100px;">收货人：</td>
						<td style="width: 200px;">${wayBillOrder.consignee}</td>
						<td style="text-align: right; width: 100px;">收货电话：</td>
						<td style="width: 200px;">${wayBillOrder.consigneeMobile}</td>
						<td style="text-align: right; width: 100px;">收货单位：</td>
						<td colspan="3">${wayBillOrder.consigneeCompany}</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100px;">收货地址：</td>
						<td colspan="7">${wayBillOrder.consigneeAddress}</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">货物信息</p>
				<table style="width: 100%; font-size: 20px; line-height: 1.6em;">
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
					<c:forEach items="${wayBillOrderCargoInfos}" var="wayBillOrderCargoInfo">
						<tr>
							<td style="text-align: center;">${wayBillOrderCargoInfo.name}</td>
							<td style="text-align: center;">${wayBillOrderCargoInfo.brand}</td>
							<td style="text-align: center;">${wayBillOrderCargoInfo.model}</td>
							<td style="text-align: center;">${wayBillOrderCargoInfo.packageTypeName}</td>
							<td style="text-align: center;">${wayBillOrderCargoInfo.number}</td>
							<td style="text-align: center;">${wayBillOrderCargoInfo.setNumber}</td>
							<td style="text-align: center;">${wayBillOrderCargoInfo.totalWeight}</td>
							<td style="text-align: center;">${wayBillOrderCargoInfo.totalVolume}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">服务项目</p>
				<table style="width: 100%; font-size: 20px; line-height: 1.6em;">
					<tr>
						<td style="text-align: right;">支付方式：</td>
						<td>${wayBillOrder.payMethodName}</td>
						<td style="text-align: right;">代收货款：</td>
						<td>${wayBillOrder.agencyFund}元</td>
						<td style="text-align: right;">垫付货款：</td>
						<td>${wayBillOrder.advanceCost}元</td>
						<td style="text-align: right;">等款放货：</td>
						<c:if test="${wayBillOrder.isWaitPay == 0}">
							<td>否</td>
						</c:if>
						<c:if test="${wayBillOrder.isWaitPay == 1}">
							<td>是</td>
						</c:if>
						<td style="text-align: right;">投保金额：</td>
						<td>${wayBillOrder.insuranceMoney}元</td>
					</tr>
					<tr>
						<td style="text-align: right;">发货方式：</td>
						<c:if test="${wayBillOrder.sendType == 0}">
							<td>自送网点</td>
						</c:if>
						<c:if test="${wayBillOrder.sendType == 1}">
							<td>上门取货</td>
						</c:if>
						<td style="text-align: right;">收货方式：</td>
						<c:if test="${wayBillOrder.receiveType == 0}">
							<td>自提</td>
						</c:if>
						<c:if test="${wayBillOrder.receiveType == 1}">
							<td>送货上门</td>
						</c:if>
						<td style="text-align: right;">回单/收条：</td>
						<td>${wayBillOrder.receiptSlipNum}</td>
						<td style="text-align: right;">发票类型：</td>
						<c:if test="${wayBillOrder.receiptType == 0}">
							<td>不需要</td>
						</c:if>
						<c:if test="${wayBillOrder.receiptType == 1}">
							<td>普通发票</td>
						</c:if>
						<c:if test="${wayBillOrder.receiptType == 2}">
							<td>增值税发票</td>
						</c:if>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">费用信息</p>
				<table style="width: 100%; font-size: 20px; line-height: 1.6em;">
					<tr>
						<td style="text-align: right;">基础运费：</td>
						<td>${wayBillOrder.freight}元</td>
						<td style="text-align: right;">手续费：</td>
						<td>${wayBillOrder.agencyFundPoundage}元</td>
						<td style="text-align: right;">保险费：</td>
						<td>${wayBillOrder.insurance}元</td>
						<td style="text-align: right;">提货费：</td>
						<td>${wayBillOrder.takeCargoCost}元</td>
					</tr>
					<tr>
						<td style="text-align: right;">装卸费：</td>
						<td>${wayBillOrder.loadUnloadCost}元</td>
						<td style="text-align: right;">中转费：</td>
						<td>${wayBillOrder.transferCost}元</td>
						<td style="text-align: right;">其他费用：</td>
						<td>${wayBillOrder.otherCost}元</td>
						<td style="text-align: right;">合计：</td>
						<td>${wayBillOrder.total}元</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; font-weight: bold; margin: 5px 0px 0px 5px;">备注信息</p>
				<div style="width: 100%; font-size: 20px; padding: 2px 5px;">${wayBillOrder.remark}</div>
			</div>
			<div style="border-bottom: 1px solid black;">
				<div style="width: 100%; font-size: 20px; padding: 2px 5px;">
					托运人注意事项：
					1、托运货物必须包装良好；
					2、不得在托运货物内夹带危险、易燃易爆、有毒等禁运物品，查到后一切损失由托运方承担；
					3、托运人必须参加保价运输，如有损坏丢失属本部范围内未保价物品，按托运费5倍赔偿；易碎易损物品不保价；
					4、凡因托运方疏忽造成货物发错需返回的，本部不保证返回途中的质量。
					5、如有代收货款，收货人直接打款给托运人的，另如有因质量问题，厂方同意客户延迟付货款的，则本部不承担任何责任，此单据作废。此单据有效期三个月，超时此单作废；此单不代表欠条，最终解释权归本货运部。
				</div>
			</div>
			<div>
				<table style="width: 100%;font-size: 20px; line-height: 1.6em;">
					<tr>
						<td style="text-align: right;">受理时间：</td>
						<td><fmt:formatDate value="${wayBillOrder.receiveOrderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td style="text-align: right;">开单时间：</td>
						<td><fmt:formatDate value="${wayBillOrder.wayBillOrderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td style="text-align: right;">开单网点：</td>
						<td>${wayBillOrder.startOutletsName}</td>
						<td style="text-align: right;">开单员：</td>
						<td>${wayBillOrder.wayBillPerson}</td>
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