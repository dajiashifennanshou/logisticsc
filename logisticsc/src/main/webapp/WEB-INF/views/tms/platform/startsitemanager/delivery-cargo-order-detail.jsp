<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提货单</title>
<%-- <link href="${configProps['resources']}/tms/base/css/base.css" rel="stylesheet"> --%>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-table .bui-grid-cell-inner{padding: 5px 0px;}
.bui-grid-table .bui-grid-hd-inner{padding: 6px 0px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;}
.form-horizontal .control-detail{display: inline-block;line-height: 30px;}
</style>
</head>
<body>
	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
	<input type="hidden" id="userId" value="${tms_user_session.id}">
	<form class="form-horizontal" id="form_number_info">
		<div class="row-fluid" style="margin-top: 10px;">
			<div class="control-group span8">
				<label class="control-label">货运交易系统订单</label>
				<div class="controls">
					<span class="control-detail">${ladingOrder.orderNumber}</span>
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">运单号</label>
				<div class="controls">
					<span class="control-detail">${ladingOrder.wayBillNumber}</span>
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">派车时间</label>
				<div class="controls">
					<span class="control-detail"><fmt:formatDate value="${ladingOrder.ladingOrderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</div>
			</div>
		</div>
	</form>
	<div class="panel">
		<div class="panel-header">
			<h3>车辆信息</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" id="form_driver_info">
				<div class="row-fluid">
					<div class="control-group span8">
						<label class="control-label">司机姓名</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.driverName}</span>
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label">司机电话</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.driverMobile}</span>
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label">车牌号</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.licensePlateNo}</span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>基本信息</h3>
		</div>
		<div class="panel-body" id="ladingOrderBaseInfo">
			<form class="form-horizontal" id="form_base_info">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">出发网点</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.startOutletsName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">到达网点</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.targetOutletsName}</span>
						</div>
					</div>
					<div class="control-group span12">
						<label class="control-label">目的地</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.targetProvinceName}</span>&nbsp;
							<span class="control-detail">${ladingOrder.targetCityName}</span>&nbsp;
							<span class="control-detail">${ladingOrder.targetCountyName}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">托运人</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consignor}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运电话</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consignorMobile}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运地址</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consignorAddress}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运单位</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consignorCompany}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">收货人</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consignee}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货电话</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consigneeMobile}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货地址</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consigneeAddress}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货单位</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.consigneeCompany}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">收货方式</label>
						<div class="controls">
							<c:if test="${ladingOrder.receiveType == 0}"><span class="control-detail">客户自提</span></c:if>
							<c:if test="${ladingOrder.receiveType == 1}"><span class="control-detail">送货上门</span></c:if>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">回单/收条</label>
						<div class="controls">
							<c:if test="${ladingOrder.receiptSlip == 1}"><span class="control-detail">是</span></c:if>
							<c:if test="${empty ladingOrder.receiptSlip or ladingOrder.receiptSlip == 0}"><span class="control-detail">否</span></c:if>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">份数</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.receiptSlipNum}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">城配司机</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.cityDriverName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">司机电话</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.cityDriverPhone}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">车牌号</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.cityVehicleNumber}</span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>货物信息</h3>
		</div>
		<div class="panel-body">
			<div id="editCargoGrid"></div>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>费用信息</h3>
		</div>
		<div class="panel-body" id="ladingOrderCostInfo">
			<form class="form-horizontal" id="form_cost_info">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">代收货款</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.agencyFund}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手续费</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.agencyFundPoundage}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">投保金额</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.insuranceMoney}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险费</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.insurance}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">提货费</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.takeCargoCost}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">装卸费</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.loadUnloadCost}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">中转费</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.transferCost}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">其他费用</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.otherCost}</span>
						</div>
					</div>
				</div>
				<div id="customCostDiv"></div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">基础运费</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.freight}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">支付方式</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.payMethodName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">合计</label>
						<div class="controls">
							<span class="control-detail">${ladingOrder.total}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">等款放货</label>
						<div class="controls">
							<c:if test="${ladingOrder.isWaitPay == 0}"><span class="control-detail">否</span></c:if>
							<c:if test="${ladingOrder.isWaitPay == 1}"><span class="control-detail">是</span></c:if>
						</div>
					</div>
				</div>
				<c:if test="${ladingOrder.payMethod == 1}">
					<div class="row-fluid" id="advancePaymentDiv">
						<div class="control-group span6">
							<label class="control-label">垫付货款</label>
							<div class="controls">
								<span class="control-detail">${ladingOrder.advanceCost}</span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${ladingOrder.payMethod == 6}">
					<div class="row-fluid" id="multi-pay">
						<div class="control-group span6">
							<label class="control-label">现付</label>
							<div class="controls">
								<span class="control-detail">${ladingOrder.immediatePay}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">到付</label>
							<div class="controls">
								<span class="control-detail">${ladingOrder.arrivePay}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">回付</label>
							<div class="controls">
								<span class="control-detail">${ladingOrder.backPay}</span>
							</div>
						</div>
					</div>
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>备注信息</h3>
		</div>
		<div class="panel-body">
			<span class="control-detail">${ladingOrder.remark}</span>
		</div>
	</div>
	<div class="row-fluid" style="margin-bottom: 10px;">
		<table class="button-box">
			<tr>
				<td><button class="button" onclick="history.back()">返回</button></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var columns = [
   	        {title : '货物名称',dataIndex : 'name'}, 
   	     	{title : '品牌',dataIndex : 'brand'}, 
   	     	{title : '型号', dataIndex : 'model'},
   	     	{title : '包装', dataIndex : 'packageTypeName'},
   	     	{title : '件数', dataIndex : 'number'},
   	     	{title : '套数',dataIndex : 'setNumber'}, 
   	        {title : '重量', dataIndex : 'totalWeight'},
   	     	{title : '体积', dataIndex : 'totalVolume'},
   	     	{title : '计费方式',dataIndex : 'countCostMode',renderer:function(val){
		   	     	switch(val){
		   	     	case 0:return "按重量";
		   	     	case 1:return "按体积";
		   	     	case 2:return "按件数";
		   	     	case 3:return "按套数";
		   	     	}
   	     	}},
   	     {title : '单价', dataIndex : 'price'}
        ];
		var datas = ${ladingOrderCargoInfos};
		grid = new Grid.Grid({
			render : '#editCargoGrid',
			columns : columns,
			items : datas,
			forceFit : true,
		}).render();
	});
	</script>
</body>
</html>