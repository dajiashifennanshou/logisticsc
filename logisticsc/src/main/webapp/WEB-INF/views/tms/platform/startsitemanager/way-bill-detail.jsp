<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运单详情</title>
<%-- <link href="${configProps['resources']}/tms/base/css/base.css" rel="stylesheet"> --%>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-table .bui-grid-cell-inner{padding: 5px 0px;}
.bui-grid-table .bui-grid-hd-inner{padding: 6px 0px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;}
.form-horizontal .control-detail{display: inline-block;line-height: 30px;}
.form-horizontal select{width: 166px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-header">
			<h3>基本信息</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span8">
						<label class="control-label">货运交易系统订单</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.orderNumber}</span>
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label">运单号</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.wayBillNumber}</span>
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label">开单时间</label>
						<div class="controls">
							<span class="control-detail">
			            		<fmt:formatDate value="${wayBillOrder.wayBillOrderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			            	</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">出发网点</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.startOutletsName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">到达网点</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.targetOutletsName}</span>
						</div>
					</div>
					<div class="control-group span12">
						<label class="control-label">目的地</label>
						<div class="controls">
							<span class="control-detail">
								${wayBillOrder.targetProvinceName }&nbsp;
								${wayBillOrder.targetCityName }&nbsp;
								${wayBillOrder.targetCountyName }
							</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">托运人</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consignor}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运电话</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consignorMobile}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运地址</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consignorAddress}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运单位</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consignorCompany}</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">收货人</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consignee}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货电话</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consigneeMobile}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货地址</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consigneeAddress}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货单位</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.consigneeCompany}</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">发货方式</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${wayBillOrder.sendType == 0}">自送网点</c:if>
			            		<c:if test="${wayBillOrder.sendType==1}">上门取货</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货方式</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${wayBillOrder.receiveType==0}">客户自提</c:if>
			            		<c:if test="${wayBillOrder.receiveType==1}">送货上门</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">回单/收条</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${wayBillOrder.receiptSlip==0}">无</c:if>
			            		<c:if test="${wayBillOrder.receiptSlip==1}">是</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">份数</label>
						<div class="controls">
			            	<span class="control-detail">${wayBillOrder.receiptSlipNum}</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">城配司机</label>
						<div class="controls">
			            	<span class="control-detail">${wayBillOrder.cityDriverName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">城配司机</label>
						<div class="controls">
			            	<span class="control-detail">${wayBillOrder.cityDriverPhone}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">城配司机</label>
						<div class="controls">
			            	<span class="control-detail">${wayBillOrder.cityVehicleNumber}</span>
						</div>
					</div>
				</div>
			</form>
	    </div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>货物信息 </h3>
		</div>
		<div class="panel-body">
			<div id="editCargoGrid"></div>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>费用信息</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">代收货款</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.agencyFund}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手续费</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.agencyFundPoundage}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">投保金额</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.insuranceMoney}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险费</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.insurance}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">提货费</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.takeCargoCost}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">装卸费</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.loadUnloadCost}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">中转费</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.transferCost}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">其他费用</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.otherCost}</span>
						</div>
					</div>
				</div>
				<div id="customCostDiv"></div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">基础运费</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.freight}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">支付方式</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.payMethodName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">合计</label>
						<div class="controls">
							<span class="control-detail">${wayBillOrder.total}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">等款放货</label>
						<div class="controls">
							<span class="control-detail">
								<c:if test="${wayBillOrder.isWaitPay == 1}">是</c:if>
								<c:if test="${wayBillOrder.isWaitPay == 0}">否</c:if>
							</span>
						</div>
					</div>
				</div>
				<c:if test="${wayBillOrder.payMethod == 1}">
					<div class="row-fluid">
						<div class="control-group span6">
							<label class="control-label">垫付货款</label>
							<div class="controls">
								<span class="control-detail">${wayBillOrder.advanceCost}</span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${wayBillOrder.payMethod == 6}">
					<div class="row-fluid">
						<div class="control-group span6">
							<label class="control-label">现付</label>
							<div class="controls">
								<span class="control-detail">${wayBillOrder.immediatePay}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">到付</label>
							<div class="controls">
								<span class="control-detail">${wayBillOrder.arrivePay}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">回付</label>
							<div class="controls">
								<span class="control-detail">${wayBillOrder.backPay}</span>
							</div>
						</div>
					</div>
				</c:if>
			</form>
	   	</div>
	</div>
	<div class="row-fluid" style="margin-bottom: 10px;">
		<table class="button-box">
			<tr>
				<td><button class="button" onclick="history.go(-1)">返回</button></td>
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
	   	        {title : '类型', dataIndex : 'model'},
	   	     	{title : '包装',dataIndex : 'packageTypeName'},
	   	        {title : '件数', dataIndex : 'number'},
	   	     	{title : '套数',dataIndex : 'setNumber'}, 
	   	        {title : '重量', dataIndex : 'totalWeight'},
	   	     	{title : '体积', dataIndex : 'totalWeight'},
	   	     	{title : '计费方式',dataIndex : 'countCostMode',renderer:function(val){
			   	     	switch(val){
			   	     	case 0:return "按重量";
			   	     	case 1:return "按体积";
			   	     	case 2:return "按件数";
			   	     	case 3:return "按套数";
			   	     	}
	   	     	}},
	   	        {title : '单价', dataIndex : 'price'},
	        ];
			
			var datas = ${wayBillOrderCargoInfosJson};
			grid = new Grid.Grid({
				render : '#editCargoGrid',
				columns : columns,
				items : datas,
				forceFit : true,
			}).render();
		});
		
		loadCustomCost();
		function loadCustomCost(){
			var result = ${wayBillOrderCostInfosJson};
			if(result != null && result != ''){
				var html = '<div class="row-fluid">';
				for(var i = 0; i < result.length; i++){
					html += '<div class="control-group span6">';
					html += '<label class="control-label">'+result[i].name+'</label>';
					html += '<div class="controls">';
					var money = typeof(result[i].money) == 'undefined' || result[i].money == null ? '' : result[i].money;
					html += '<span class="control-detail">'+money+'</span>';
					html += '</div></div>';
					if((i + 1) % 4 == 0){
						html += '</div>';
					}
					if((i + 1) % 4 == 0 && i != result.length - 1){
						html += '<div class="row-fluid">';
					}
				}
				if(result.length % 4 != 0){
					html += '<div>';
				}
			}
			$('#customCostDiv').html(html);
		}
	</script>
</body>
</html>