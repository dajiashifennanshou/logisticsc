<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中转订单详情</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.info-content{
	line-height: 40px;
}
.row-margin{margin-top: 8px;}
.form-horizontal input[type="text"], input[type="password"], input[type="email"]{width: 120px;}
.form-horizontal select{width: 146px;}
.form-show-text{line-height: 30px;}
.form-horizontal .controls{height: 40px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div style="padding: 15px;">
			<label>中转发车单号：</label><span>${transferDepartList.transferDepartNumber}</span>
		</div>
	</div>
	<div class="row-fluid">
		<form class="form-horizontal" id="outDepartListForm">
			<div class="panel">
				<div class="panel-header">
					<h3>中转运输协议</h3>
				</div>
				<div class="panel-body">
					<table border="1" width="100%">
						<tr>
							<td colspan="2">
								<div class="info-content" style="margin-left: 15px;">
									<b>中转信息</b>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label">中转时间</label>
										<div class="controls">
											<span class="control-label" style="width: auto;">${transferDepartList.transferTimeStr}</span>
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label">承运单位</label>
										<div class="controls">
											<span class="control-label" style="width: auto;">${transferDepartList.carriageCompany}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">外包费用合计</label>
										<div class="controls">
											<span class="control-label"style="width: auto;">${transferDepartList.transferCost} 元</span>
										</div>
									</div>
								</div>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label">发站联系人</label>
										<div class="controls">
											<span class="control-label"style="width: auto;">${transferDepartList.startSitePerson}</span>
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label">到站联系人</label>
										<div class="controls">
											<span class="control-label"style="width: auto;">${transferDepartList.endSitePerson}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">现付</label>
										<div class="controls">
											<span class="control-label" id="currentPayText" style="width: auto;">${transferDepartList.currentPay} 元</span>
										</div>
									</div>
								</div>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label">发站联系电话</label>
										<div class="controls">
											<span class="control-label" id="currentPayText" style="width: auto;">${transferDepartList.startSitePhone}</span>
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label">到站联系电话</label>
										<div class="controls">
											<span class="control-label" id="currentPayText" style="width: auto;">${transferDepartList.endSitePhone}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">回付</label>
										<div class="controls">
											<span class="control-label" style="width: auto;">${transferDepartList.backPay} 元</span>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel">
				<div class="panel-header">
					<h3>运单列表</h3>
				</div>
				<div class="panel-body">
					<div class="row-fluid">
						<div class="span24">
							<div id="wayBillOrderList"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel">
				<div class="panel-header">
					<h3>备注信息</h3>
				</div>
				<div class="panel-body">
					<div>
						<textarea id="remark" name="remark" style="width: 98%;" disabled="disabled">${transferDepartList.remark}</textarea>
					</div>
				</div>
				<input type="hidden" id="wayBillOrders" value='${wayBillOrders}'>
			</div>
		</form>
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
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var store, grid;
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [ { title : '出发网点', elCls : 'center', dataIndex : 'startOutletsName' }, 
		                { title : '运单号', elCls : 'center', dataIndex : 'wayBillNumber', width : 130}, 
		                { title : '中转承运单号', elCls : 'center', dataIndex : 'transferWayBillNumber', width : 130},
		                { title : '中转费', elCls : 'center', dataIndex : 'transferCost'},
		                { title : '现付', elCls : 'center', dataIndex : 'currentPay'},
		                { title : '回付', elCls : 'center', dataIndex : 'outBackPay'},
						{ title : '到达网点', elCls : 'center', dataIndex : 'targetOutletsName' }, 
						{ title : '发货人', elCls : 'center', dataIndex : 'consignor' }, 
						{ title : '发货电话', elCls : 'center', dataIndex : 'consignorMobile' }, 
						{ title : '收货人', elCls : 'center', dataIndex : 'consignee' }, 
						{ title : '收货电话', elCls : 'center', dataIndex : 'consigneeMobile' }, 
						{ title : '下单时间', width : 150, elCls : 'center', dataIndex : 'wayBillOrderTime', renderer:BUI.Grid.Format.datetimeRenderer } 
					];
		store = new Store({
			data : JSON.parse($('#wayBillOrders').val()),
			autoLoad : true
		});
		grid = new Grid.Grid({
			render : '#wayBillOrderList',
			columns : columns,
			store : store,
			//forceFit : true,
		});
		grid.render();
	});
	</script>
</body>
</html>