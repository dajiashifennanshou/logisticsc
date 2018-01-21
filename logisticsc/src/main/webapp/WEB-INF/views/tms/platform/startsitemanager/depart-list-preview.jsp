<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发车清单</title>
</head>
<body>
	<div style="text-align: right;">
		<button class="button" onclick="printPage()">打印</button>
	</div>
	<div id="printBox">
		<div style="font-size: 34px; font-weight: bold; text-align: center; font-family: '微软雅黑'">
			<span>${departList.companyName}</span>
		</div>
		<div style="width: 1200px; margin: auto; border: 1px solid black;font-family: '微软雅黑';">
			<div style="border-bottom: 1px solid black;">
				<table style="width: 100%; font-size: 20px;" border="0">
					<tr>
						<td style="text-align: right; width: 100px;">发车单号：</td>
						<td style="width: 200px;">${departList.departNumber}</td>
						<td style="text-align: right; width: 100px;">发车日期：</td>
						<td style="width: 200px;"><fmt:formatDate value="${departList.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td style="text-align: right; width: 110px;">出发网点：</td>
						<td>${departList.startOutletsName}</td>
						<td style="text-align: right; width: 110px;">到达网点：</td>
						<td>${departList.targetOutletsName}</td>
						<td style="text-align: right; width: 110px;">途经网点：</td>
						<td>${departList.wayOutletsName}</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black; font-size: 20px;">
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<td style="width: 100px;border-right: 1px solid black;" rowspan="2">车辆信息</td>
						<td style="text-align: right; width: 100px;">车牌号：</td>
						<td>${departList.vehicleNumber}</td>
						<td style="text-align: right; width: 100px;">挂车号：</td>
						<td>${departList.hangVehicleNumber}</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 110px;">司机姓名：</td>
						<td>${departList.driverName}</td>
						<td style="text-align: right; width: 110px;">司机电话：</td>
						<td>${departList.driverPhone}</td>
						<td style="text-align: right; width: 110px;">开户行：</td>
						<td>${departList.driverBankName}</td>
						<td style="text-align: right; width: 110px;">银行账号：</td>
						<td>${departList.driverBankNumber}</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black; font-size: 20px;">
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<td style="width: 100px;border-right: 1px solid black;">司机费用</td>
						<td style="text-align: right; width: 100px;">应付运费：</td>
						<td style="width: 200px;">${departList.shouldPayDriverCost}元</td>
						<td style="text-align: right; width: 100px;">现付运费：</td>
						<td style="width: 200px;">${departList.nowPayDriverCost}元</td>
						<td style="text-align: right; width: 110px;">到付运费：</td>
						<td colspan="3">${departList.arrivePayDriverCost}元</td>
						<td style="text-align: right; width: 110px;">回付运费：</td>
						<td colspan="3">${departList.backPayDriverCost}元</td>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black; font-size: 20px;">
				<table style="width: 100%; font-size: 20px;">
					<tr>
						<td style="width: 100px;border-right: 1px solid black;">整车货险</td>
						<td style="text-align: right; width: 100px;">保险金额：</td>
						<td style="width: 200px;">${departList.insuranceMoney}元</td>
						<td style="text-align: right; width: 100px;">是否年保：</td>
						<c:if test="${departList.isYearInsurance == 1}">
							<td>是</td>
						</c:if>
						<c:if test="${departList.isYearInsurance == 0}">
							<td>否</td>
						</c:if>
					</tr>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<table style="width: 100%; font-size: 20px; border: 0px; text-align: center;" border="1" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<td>序号</td>
							<td>运单号</td>
							<td>到达网点</td>
							<td>收货人</td>
							<td>收货电话</td>
							<td>货物名称</td>
							<td>品牌</td>
							<td>件数</td>
							<td>套数</td>
							<td>代收货款</td>
							<td>垫付货款</td>
							<td>总运费</td>
							<td>付款方式</td>
							<td>回单份数</td>
						</tr>
					</thead>
					<tbody id="wayBillOrderList">
					</tbody>
				</table>
			</div>
			<div style="border-bottom: 1px solid black;">
				<p style="font-size: 24px; margin: 5px 0px 0px 5px;">备注信息</p>
				<div style="width: 100%; font-size: 20px; padding: 2px 5px;">${departList.remark}</div>
			</div>
			<div>
				<table style="width: 100%;font-size: 20px;">
					<tr>
						<td style="width: 33%;">司机签字：</td>
						<td style="width: 33%;">发站负责人签字：</td>
						<td style="width: 33%;">到站负责人签字：</td>
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
	$(function(){
		loadWayBillOrderData();
	});
	
	function printPage(){
		$('#printBox').printArea();
	}
	
	// 加载运单数据
	function loadWayBillOrderData(){
		var wayBillOrders = ${wayBillOrders};
		
		var cargoNumberTotal = 0; //件数统计
		var agencyFundTotal = 0; //代收款统计
		var advanceCostTotal = 0; //垫付货款统计
		var totalTotal = 0; //总运费统计
		var receiptSlipNumTotal = 0; //回单份数统计
		
		var html = '';
		for(var i = 0; i < wayBillOrders.length; i++){
			var wayBillNumber = typeof(wayBillOrders[i].wayBillNumber) == 'undefined' ? '' : wayBillOrders[i].wayBillNumber;
			var targetOutletsName = typeof(wayBillOrders[i].targetOutletsName) == 'undefined' ? '' : wayBillOrders[i].targetOutletsName;
			var consignee = typeof(wayBillOrders[i].consignee) == 'undefined' ? '' : wayBillOrders[i].consignee;
			var consigneeMobile = typeof(wayBillOrders[i].consigneeMobile) == 'undefined' ? '' : wayBillOrders[i].consigneeMobile;
			var cargoName = typeof(wayBillOrders[i].cargoName) == 'undefined' ? '' : wayBillOrders[i].cargoName;
			var cargoBrand = typeof(wayBillOrders[i].cargoBrand) == 'undefined' ? '' : wayBillOrders[i].cargoBrand;
			var cargoNumber = typeof(wayBillOrders[i].cargoNumber) == 'undefined' ? '' : wayBillOrders[i].cargoNumber;
			var cargoSetNumber = typeof(wayBillOrders[i].cargoSetNumber) == 'undefined' ? '' : wayBillOrders[i].cargoSetNumber;
			var agencyFund = typeof(wayBillOrders[i].agencyFund) == 'undefined' ? '' : wayBillOrders[i].agencyFund;
			var advanceCost = typeof(wayBillOrders[i].advanceCost) == 'undefined' ? '' : wayBillOrders[i].advanceCost;
			var total = typeof(wayBillOrders[i].total) == 'undefined' ? '' : wayBillOrders[i].total;
			var payMethod = typeof(wayBillOrders[i].payMethodName) == 'undefined' ? '' : wayBillOrders[i].payMethodName;
			var receiptSlipNum = typeof(wayBillOrders[i].receiptSlipNum) == 'undefined' ? '' : wayBillOrders[i].receiptSlipNum;
			html += '<tr>';
			html += '<td>'+(i+1)+'</td>';
			html += '<td>'+wayBillNumber+'</td>';
			html += '<td>'+targetOutletsName+'</td>';
			html += '<td>'+consignee+'</td>';
			html += '<td>'+consigneeMobile+'</td>';
			html += '<td>'+cargoName+'</td>';
			html += '<td>'+cargoBrand+'</td>';
			html += '<td>'+cargoNumber+'</td>';
			html += '<td>'+cargoSetNumber+'</td>';
			html += '<td>'+agencyFund+'</td>';
			html += '<td>'+advanceCost+'</td>';
			html += '<td>'+total+'</td>';
			html += '<td>'+payMethod+'</td>';
			html += '<td>'+receiptSlipNum+'</td>';
			html += '</tr>';
			cargoNumberTotal += new Number(cargoNumber);
			agencyFundTotal += new Number(agencyFund);
			advanceCostTotal += new Number(advanceCost);
			totalTotal += new Number(total);
			receiptSlipNumTotal += new Number(receiptSlipNum);
		}
		html += '<tr>';
		html += '<td colspan="9">合计</td>';
		/* html += '<td>'+cargoNumberTotal+'</td>'; */
		html += '<td>'+agencyFundTotal+'</td>';
		html += '<td>'+advanceCostTotal+'</td>';
		html += '<td>'+totalTotal+'</td>';
		html += '<td></td>';
		html += '<td>'+receiptSlipNumTotal+'</td>';
		html += '</tr>'
		$('#wayBillOrderList').html(html);
	}
	</script>
</body>
</html>