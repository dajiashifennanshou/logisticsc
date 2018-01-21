<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/dingdanzx.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/order-detail.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../../top.jsp" flush="true"></jsp:include>
	<div class="container-self">
		<div class="frame_left">
			<jsp:include page="../order_left.jsp"></jsp:include>
		</div>
		<div class="demo-content platformStyle">
			<div class="info-title">预约订单信息</div>
			<div class="info-content">
				<table class="info-content-table">
					<tr>
						<td class="detail-title">发货人:</td>
						<td class="detail-content-left">${platformOrder.consignorName}</td>
						<td class="detail-title">手机号:</td>
						<td class="detail-content-left">${platformOrder.consignorPhoneNumber}</td>
						<td class="detail-title">固定电话:</td>
						<td class="detail-content-left">${platformOrder.consignorTelephone}</td>
					</tr>
					<tr>
						<td class="detail-title">发货地址:</td>
						<td class="detail-content-left" colspan="5">
							${platformOrder.consignorProvinceVal}
							${platformOrder.consignorCityVal}
							${platformOrder.consignorCountyVal}
							${platformOrder.consignorAddress}
						</td>
					</tr>
					<tr>
						<td class="detail-title">收货人:</td>
						<td class="detail-content-left">${platformOrder.consigneeName}</td>
						<td class="detail-title">手机号:</td>
						<td class="detail-content-left">${platformOrder.consigneePhoneNumber}</td>
						<td class="detail-title">固定电话:</td>
						<td class="detail-content-left">${platformOrder.consigneeTelephone}</td>
					</tr>
					<tr>
						<td class="detail-title">收货地址:</td>
						<td class="detail-content-left" colspan="5">
							${platformOrder.consigneeProvinceVal}
							${platformOrder.consigneeCityVal}
							${platformOrder.consigneeCountyVal}
							${platformOrder.consigneeAddress}
						</td>
					</tr>
					<tr>
						<td class="detail-title">司机姓名:</td>
						<td class="detail-content-left">${platformOrder.driverName}</td>
						<td class="detail-title">司机电话:</td>
						<td class="detail-content-left">${platformOrder.driverPhone}</td>
						<td class="detail-title">车牌号:</td>
						<td class="detail-content-left">${platformOrder.vehicleNumber} 车型：${platformOrder.vehicleTypeVal}</td>
					</tr>
					<tr>
						<td class="detail-title">发货方式:</td>
						<td class="detail-content-left">
							<c:if test="${platformOrder.sendCargoType == 0}">自送网点</c:if>
							<c:if test="${platformOrder.sendCargoType == 1}">上门取货</c:if>
						</td>
						<c:if test="${platformOrder.sendCargoType == 0}">
							<td class="detail-title">网点地址:</td>
							<td class="detail-content-left">${startOutlets.address}</td>
						</c:if>
						<c:if test="${platformOrder.sendCargoType == 1}">
							<td class="detail-title">预约时间:</td>
							<td class="detail-content-left">
								<fmt:formatDate value="${platformOrder.deliveryDate}" pattern="yyyy-MM-dd"/>
								${platformOrder.deliveryStartTime}
								${platformOrder.deliveryEndTime}
							</td>
						</c:if>
						<td class="detail-title">收货方式:</td>
						<td class="detail-content-left">
							<c:if test="${platformOrder.receiveCargoType == 0}">客户自提</c:if>
							<c:if test="${platformOrder.receiveCargoType == 1}">送货上门</c:if>
						</td>
					</tr>
					<tr>
						<td align="right">回单:</td>
						<td>
							<c:if test="${empty platformOrderAdditionalServer.isReceipt or platformOrderAdditionalServer.isReceipt == 0}">否</c:if>
							<c:if test="${platformOrderAdditionalServer.isReceipt == 1}">是</c:if>
						</td>
						<td align="right">等款放货:</td>
						<td>
							<c:if test="${empty platformOrder.isWaitPay or platformOrder.isWaitPay == 0}">否</c:if>
							<c:if test="${platformOrder.isWaitPay == 1}">是</c:if>
						</td>
						<td align="right">综合信息服务:</td>
						<td>是</td>
					</tr>
					<tr>
						<td align="right">装货:</td>
						<td>
							<c:if test="${empty platformOrderAdditionalServer.isLoadCargo or platformOrderAdditionalServer.isLoadCargo == 0}">否</c:if>
							<c:if test="${platformOrderAdditionalServer.isLoadCargo == 1}">是</c:if>
						</td>
						<c:if test="${platformOrderAdditionalServer.isLoadCargo == 1}">
							<td align="right">楼层:</td>
							<td>${platformOrderAdditionalServer.loadCargoFloor}</td>
							<td align="right">电梯:</td>
							<td>
								<c:if test="${empty platformOrderAdditionalServer.loadCargoIsElevator or platformOrderAdditionalServer.loadCargoIsElevator == 0}">否</c:if>
								<c:if test="${platformOrderAdditionalServer.loadCargoIsElevator == 1}">是</c:if>
							</td>
						</c:if>
					</tr>
					<tr>
						<td align="right">卸货:</td>
						<td>
							<c:if test="${empty platformOrderAdditionalServer.isUnloadCargo or platformOrderAdditionalServer.isUnloadCargo == 0}">否</c:if>
							<c:if test="${platformOrderAdditionalServer.isUnloadCargo == 1}">是</c:if>
						</td>
						<c:if test="${platformOrderAdditionalServer.isUnloadCargo == 1}">
							<td align="right">楼层:</td>
							<td>${platformOrderAdditionalServer.unloadCargoFloor}</td>
							<td align="right">电梯:</td>
							<td>
								<c:if test="${empty platformOrderAdditionalServer.unloadCargoIsElevator or platformOrderAdditionalServer.unloadCargoIsElevator == 0}">否</c:if>
								<c:if test="${platformOrderAdditionalServer.unloadCargoIsElevator == 1}">是</c:if>
							</td>
						</c:if>
					</tr>
					<tr>
						<td class="detail-title">发票类型</td>
						<td class="detail-content-left">
							<c:if test="${platformOrder.receiptType == 0}">不需要</c:if>
							<c:if test="${platformOrder.receiptType == 1}">普通发票</c:if>
							<c:if test="${platformOrder.receiptType == 2}">增值税发票</c:if>
						</td>
						<c:if test="${platformOrder.receiptType == 1}">
							<td class="detail-title">发票抬头:</td>
							<td class="detail-content-right">
								<c:if test="${platformOrder.receiptTitle == 0}">个人</c:if>
								<c:if test="${platformOrder.receiptTitle == 1}">单位：${platformOrder.receiptCompanyName}</c:if>
							</td>
						</c:if>
						<td class="detail-title">支付方式:</td>
						<td class="detail-content-left">
							<c:if test="${platformOrder.payType == 0}">现付</c:if>
							<c:if test="${platformOrder.payType == 1}">到付</c:if>
							<c:if test="${platformOrder.payType == 2}">使用预付款</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<table class="table table-striped" id="dingdan-zt" style="margin: 0px;">
								<thead>
									<tr>
										<th>货物名称</th>
										<th>货物品牌</th>
										<th>货物型号</th>
										<th>件数</th>
										<th>套数</th>
										<th>重量(t)</th>
										<th>体积(m³)</th>
										<th>包装信息</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty platformOrderCargoTemps}">
										<c:forEach items="${platformOrderCargos}" var="cargo">
											<tr>
												<td>${cargo.name}</td>
												<td>${cargo.brand}</td>
												<td>${cargo.model}</td>
												<td>${cargo.number}</td>
												<td>${cargo.setNumber}</td>
												<td>${cargo.singleWeight}</td>
												<td>${cargo.singleVolume}</td>
												<td>${cargo.packageTypeName}</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${not empty platformOrderCargoTemps}">
										<c:forEach items="${platformOrderCargoTemps}" var="cargo">
											<tr>
												<td>${cargo.name}</td>
												<td>${cargo.brand}</td>
												<td>${cargo.model}</td>
												<td>${cargo.number}</td>
												<td>${cargo.setNumber}</td>
												<td>${cargo.singleWeight}</td>
												<td>${cargo.singleVolume}</td>
												<td>${cargo.packageTypeName}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td class="detail-title">代收货款</td>
						<td class="detail-content-left">
							<c:if test="${empty platformOrderAdditionalServer.collectionDeliveryMoney}">0</c:if>
							<c:if test="${not empty platformOrderAdditionalServer.collectionDeliveryMoney}">${platformOrderAdditionalServer.collectionDeliveryMoney}</c:if>
							元
						</td>
						<td class="detail-title">代收款手续费</td>
						<td class="detail-content-left">${platformOrderBill.agencyFundPoundage}元</td>
						<td class="detail-title">预约费</td>
						<td class="detail-content-left">${platformOrderBill.estimateTakeCargoCost}元</td>
					</tr>
					<tr>
						<td class="detail-title">投保金额</td>
						<td class="detail-content-left">
							<c:if test="${empty platformOrder.insuranceMoney}">0</c:if>
							<c:if test="${not empty platformOrder.insuranceMoney}">${platformOrder.insuranceMoney}</c:if>
							元
						</td>
						<td class="detail-title">保险费:</td>
						<td class="detail-content-right">${platformOrderBill.insurance}元</td>
						<td class="detail-title">送货费:</td>
						<td class="detail-content-right">${platformOrderBill.estimateSendCargoCost}元</td>
					</tr>
					<tr>
						<td class="detail-title">装货费:</td>
						<td class="detail-content-left">${platformOrderBill.estimateLoadCargoCost}元</td>
						<td class="detail-title">卸货费:</td>
						<td class="detail-content-left">${platformOrderBill.estimateUnloadCargoCost}元</td>
					</tr>
					<tr>
						<td class="detail-title">基础运费:</td>
						<td class="detail-content-left">${platformOrderBill.estimateFreight}元</td>
						<td class="detail-title">总运费</td>
						<td class="detail-content-left">${platformOrderBill.estimateTotalCost}元</td>
					</tr>
				</table>
			</div>
			<c:if test="${platformOrder.state >= 3}">
				<div class="info-title">确认订单信息</div>
				<div class="info-content">
					<table class="info-content-table">
						<tr>
							<td class="detail-title">发货人:</td>
							<td class="detail-content-left">${platformOrder.consignorName}</td>
							<td class="detail-title">手机号:</td>
							<td class="detail-content-left">${platformOrder.consignorPhoneNumber}</td>
							<td class="detail-title">固定电话:</td>
							<td class="detail-content-left">${platformOrder.consignorTelephone}</td>
						</tr>
						<tr>
							<td class="detail-title">发货地址:</td>
							<td class="detail-content-left" colspan="5">
								${platformOrder.consignorProvinceVal}
								${platformOrder.consignorCityVal}
								${platformOrder.consignorCountyVal}
								${platformOrder.consignorAddress}
							</td>
						</tr>
						<tr>
							<td class="detail-title">收货人:</td>
							<td class="detail-content-left">${platformOrder.consigneeName}</td>
							<td class="detail-title">手机号:</td>
							<td class="detail-content-left">${platformOrder.consigneePhoneNumber}</td>
							<td class="detail-title">固定电话:</td>
							<td class="detail-content-left">${platformOrder.consigneeTelephone}</td>
						</tr>
						<tr>
							<td class="detail-title">收货地址:</td>
							<td class="detail-content-left" colspan="5">
								${platformOrder.consigneeProvinceVal}
								${platformOrder.consigneeCityVal}
								${platformOrder.consigneeCountyVal}
								${platformOrder.consigneeAddress}
							</td>
						</tr>
						<tr>
							<td class="detail-title">发货方式:</td>
							<td class="detail-content-left">
								<c:if test="${platformOrder.sendCargoType == 0}">自送网点</c:if>
								<c:if test="${platformOrder.sendCargoType == 1}">上门取货</c:if>
							</td>
							<c:if test="${platformOrder.sendCargoType == 0}">
								<td class="detail-title">网点地址:</td>
								<td class="detail-content-left">xxx</td>
							</c:if>
							<c:if test="${platformOrder.sendCargoType == 1}">
								<td class="detail-title">预约时间:</td>
								<td class="detail-content-left">
									<fmt:formatDate value="${platformOrder.deliveryDate}" pattern="yyyy-MM-dd"/>
									${platformOrder.deliveryStartTime}
									${platformOrder.deliveryEndTime}
								</td>
							</c:if>
							<td class="detail-title">收货方式:</td>
							<td class="detail-content-left">
								<c:if test="${platformOrder.receiveCargoType == 0}">客户自提</c:if>
								<c:if test="${platformOrder.receiveCargoType == 1}">送货上门</c:if>
							</td>
						</tr>
						<tr>
							<td align="right">回单:</td>
							<td>
								<c:if test="${empty platformOrderAdditionalServer.isReceipt or platformOrderAdditionalServer.isReceipt == 0}">否</c:if>
								<c:if test="${platformOrderAdditionalServer.isReceipt == 1}">是</c:if>
							</td>
							<td align="right">等款放货:</td>
							<td>
								<c:if test="${empty platformOrder.isWaitPay or platformOrder.isWaitPay == 0}">否</c:if>
								<c:if test="${platformOrder.isWaitPay == 1}">是</c:if>
							</td>
							<td align="right">综合信息服务:</td>
							<td>是</td>
						</tr>
						<tr>
							<td align="right">装货:</td>
							<td>
								<c:if test="${empty platformOrderAdditionalServer.isLoadCargo or platformOrderAdditionalServer.isLoadCargo == 0}">否</c:if>
								<c:if test="${platformOrderAdditionalServer.isLoadCargo == 1}">是</c:if>
							</td>
							<c:if test="${platformOrderAdditionalServer.isLoadCargo == 1}">
								<td align="right">楼层:</td>
								<td>${platformOrderAdditionalServer.loadCargoFloor}</td>
								<td align="right">电梯:</td>
								<td>
									<c:if test="${empty platformOrderAdditionalServer.loadCargoIsElevator or platformOrderAdditionalServer.loadCargoIsElevator == 0}">否</c:if>
									<c:if test="${platformOrderAdditionalServer.loadCargoIsElevator == 1}">是</c:if>
								</td>
							</c:if>
						</tr>
						<tr>
							<td align="right">卸货:</td>
							<td>
								<c:if test="${empty platformOrderAdditionalServer.isUnloadCargo or platformOrderAdditionalServer.isUnloadCargo == 0}">否</c:if>
								<c:if test="${platformOrderAdditionalServer.isUnloadCargo == 1}">是</c:if>
							</td>
							<c:if test="${platformOrderAdditionalServer.isUnloadCargo == 1}">
								<td align="right">楼层:</td>
								<td>${platformOrderAdditionalServer.unloadCargoFloor}</td>
								<td align="right">电梯:</td>
								<td>
									<c:if test="${empty platformOrderAdditionalServer.unloadCargoIsElevator or platformOrderAdditionalServer.unloadCargoIsElevator == 0}">否</c:if>
									<c:if test="${platformOrderAdditionalServer.unloadCargoIsElevator == 1}">是</c:if>
								</td>
							</c:if>
						</tr>
						<tr>
							<td class="detail-title">发票类型</td>
							<td class="detail-content-left">
								<c:if test="${platformOrder.receiptType == 0}">不需要</c:if>
								<c:if test="${platformOrder.receiptType == 1}">普通发票</c:if>
								<c:if test="${platformOrder.receiptType == 2}">增值税发票</c:if>
							</td>
							<c:if test="${platformOrder.receiptType == 1}">
								<td class="detail-title">发票抬头:</td>
								<td class="detail-content-right">
									<c:if test="${platformOrder.receiptTitle == 0}">个人</c:if>
									<c:if test="${platformOrder.receiptTitle == 1}">单位：${platformOrder.receiptCompanyName}</c:if>
								</td>
							</c:if>
							<td class="detail-title">支付方式:</td>
							<td class="detail-content-left">
								<c:if test="${platformOrder.payType == 0}">现付</c:if>
								<c:if test="${platformOrder.payType == 1}">到付</c:if>
								<c:if test="${platformOrder.payType == 2}">使用预付款</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<table class="table table-striped" id="dingdan-zt" style="margin: 0px;">
									<thead>
										<tr>
											<th>货物名称</th>
											<th>货物品牌</th>
											<th>货物型号</th>
											<th>件数</th>
											<th>套数</th>
											<th>重量(t)</th>
											<th>体积(m³)</th>
											<th>单价</th>
											<th>包装信息</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${platformOrderCargos}" var="cargo">
											<tr>
												<td>${cargo.name}</td>
												<td>${cargo.brand}</td>
												<td>${cargo.model}</td>
												<td>${cargo.number}</td>
												<td>${cargo.setNumber}</td>
												<td>${cargo.singleWeight}</td>
												<td>${cargo.singleVolume}</td>
												<td>${cargo.price}</td>
												<td>${cargo.packageTypeName}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td class="detail-title">代收货款:</td>
							<td class="detail-content-left">
								<c:if test="${empty platformOrderAdditionalServer.collectionDeliveryMoney}">0</c:if>
								<c:if test="${not empty platformOrderAdditionalServer.collectionDeliveryMoney}">${platformOrderAdditionalServer.collectionDeliveryMoney}</c:if>
								元
							</td>
							<td class="detail-title">代收款手续费:</td>
							<td class="detail-content-left">${platformOrderBill.agencyFundPoundage}元</td>
							<td class="detail-title">提货费:</td>
							<td class="detail-content-left">
								<c:if test="${empty platformOrderBill.takeCargoCost}">0</c:if>
								<c:if test="${not empty platformOrderBill.takeCargoCost}">${platformOrderBill.takeCargoCost}</c:if>
								元
							</td>
						</tr>
						<tr>
							<td class="detail-title">投保金额:</td>
							<td class="detail-content-right">
								<c:if test="${empty platformOrder.insuranceMoney}">0</c:if>
								<c:if test="${not empty platformOrder.insuranceMoney}">${platformOrder.insuranceMoney}</c:if>
								元
							</td>
							<td class="detail-title">保险费:</td>
							<td class="detail-content-right">${platformOrderBill.insurance}元</td>
							<td class="detail-title">送货费:</td>
							<td class="detail-content-right">${platformOrderBill.sendCargoCost}元</td>
						</tr>
						<tr>
							<td class="detail-title">装货费:</td>
							<td class="detail-content-left">${platformOrderBill.loadCargoCost}元</td>
							<td class="detail-title">卸货费:</td>
							<td class="detail-content-left">${platformOrderBill.unloadCargoCost}元</td>
						</tr>
						<tr>
							<td class="detail-title">基础运费:</td>
							<td class="detail-content-left">${platformOrderBill.freight}元</td>
							<td class="detail-title">总运费</td>
							<td class="detail-content-left">${platformOrderBill.totalCost}元</td>
						</tr>
					</table>
				</div>
			</c:if>
		</div>
	</div>
	<div style="clear: both;"></div>
	<jsp:include page="../../bottom.jsp"></jsp:include>
	<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
	<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
</body>
</html>