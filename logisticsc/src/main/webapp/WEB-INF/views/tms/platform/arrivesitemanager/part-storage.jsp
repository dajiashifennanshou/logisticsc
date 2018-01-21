<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部分入库</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.form-show-text{line-height: 40px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;line-height: 40px;}
</style>
</head>
<body>
	<input type="hidden" id="currentOutlets" value="${outletsInfo.id}">
	<div class="row-fluid">
		<div style="padding: 15px;">
			<label>发车单号：</label><span id="departNumber"></span>
		</div>
	</div>
	<div class="row-fluid">
		<form class="form-horizontal" id="departListForm">
			<div class="panel">
				<div class="panel-header">
					<h3>运单信息</h3>
				</div>
				<div class="panel-body">
					<table border="1" width="100%">
						<tr>
							<td colspan="2">
								<div id="outlets_line" style="margin-left: 15px; line-height: 40px;">
									<!-- <b id="startOutletsLine"></b>
									<span> --&gt; </span>
									<b id="wayOutletsLine"></b>
									<span> --&gt; </span>
									<b id="targetOutletsLine"></b> -->
								</div>
							</td>
						</tr>
						<tr>
							<td align="center" width="80px"><b>发车信息</b></td>
							<td>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">出发网点</label>
										<div class="controls">
											<span class="form-show-text" id="startOutletsName"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">下一个网点</label>
										<div class="controls">
											<span class="form-show-text" id="wayOutletsName"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">到达网点</label>
										<div class="controls">
											<span class="form-show-text" id="targetOutletsName"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">发车时间</label>
										<div class="controls">
											<span class="form-show-text" id="startTime"></span>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">到达时间</label>
										<div class="controls">
											<span class="form-show-text" id="endTime"></span>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center"><b>车辆信息</b></td>
							<td>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">车牌号</label>
										<div class="controls">
											<span class="form-show-text" id="vehicleNumber"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">挂车号</label>
										<div class="controls">
											<span class="form-show-text" id="hangVehicleNumber"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">司机姓名</label>
										<div class="controls">
											<span class="form-show-text" id="driverName"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">司机电话</label>
										<div class="controls">
											<span class="form-show-text" id="driverPhone"></span>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">车辆类型</label>
										<div class="controls">
											<span class="form-show-text" id="vehicleType"></span>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center"><b>司机费用</b></td>
							<td>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">应付司机</label>
										<div class="controls">
											<span class="form-show-text" id="shouldPayDriverCost"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">现付司机</label>
										<div class="controls">
											<span class="form-show-text" id="nowPayDriverCost"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">到付司机</label>
										<div class="controls">
											<span class="form-show-text" id="arrivePayDriverCost"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">回付司机</label>
										<div class="controls">
											<span class="form-show-text" id="backPayDriverCost"></span>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center"><b>操作费用</b></td>
							<td>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">装车费</label>
										<div class="controls">
											<span class="form-show-text" id="loadCost"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">卸车费</label>
										<div class="controls">
											<span class="form-show-text" id="unloadCost"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">分流费</label>
										<div class="controls">
											<span class="form-show-text" id="shuntCost"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">是否年保</label>
										<div class="controls">
											<span class="form-show-text" id="isYearInsurance"></span>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">保险金额</label>
										<div class="controls">
											<span class="form-show-text" id="insuranceMoney"></span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">保险费</label>
										<div class="controls">
											<span class="form-show-text" id="insuranceCost"></span>
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
							<input type="hidden" id="wayBillNumbers" name="wayBillNumbers">
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
						<textarea id="remark" disabled="disabled" style="width: 98%;"></textarea>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var grid, store;
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [ {
			title : '下单时间',
			width : 140,
			elCls : 'center',
			dataIndex : 'wayBillOrderTime',
			renderer:BUI.Grid.Format.datetimeRenderer
		}, {
			title : '运单号',
			width : 130,
			elCls : 'center',
			dataIndex : 'wayBillNumber'
		}, {
			title : '运单状态',
			elCls : 'center',
			dataIndex : 'statusName'
		}, {
			title : '出发网点',
			elCls : 'center',
			dataIndex : 'startOutletsName'
		}, {
			title : '到达网点',
			elCls : 'center',
			dataIndex : 'targetOutletsName'
		}, {
			title : '目的地',
			elCls : 'center',
			width : 200,
			dataIndex : 'targetAddress'
		}, {
			title : '发货人',
			elCls : 'center',
			dataIndex : 'consignor'
		}, {
			title : '发货电话',
			elCls : 'center',
			dataIndex : 'consignorMobile'
		}, {
			title : '收货人',
			elCls : 'center',
			dataIndex : 'consignee'
		}, {
			title : '收货电话',
			elCls : 'center',
			dataIndex : 'consigneeMobile'
		}, {
			title : '操作',
			elCls : 'center',
			renderer : function(value, obj, index){
				var status = obj.status;
				var currentOutlets = $('#currentOutlets').val();
				if(status != 5 && obj.targetOutlets == currentOutlets){
					return '<span class="grid-command" onclick="storage(\''+obj.wayBillNumber+'\')">入库</span>';
				}else{
					return '<span>入库</span>';
				}
			}
		} ];
		
		store = new Store({
			url : '<%=request.getContextPath()%>/tms/signstorage/getwaybillorderlist.shtml',
			autoLoad : true,
			params : { 'wayBillNumbers' : <%=request.getParameter("departListData")%>.wayBillNumbers }
		});
		grid = new Grid.Grid({
			render : '#wayBillOrderList',
			columns : columns,
			store : store,
			//forceFit : true
		});
		grid.render();
	});
	
	$(function(){
		loadDepartInfo();
	});
	
	// 加载发车单信息
	function loadDepartInfo(){
		var departList = <%=request.getParameter("departListData")%>;
		$('#departNumber').text(departList.departNumber);
		
		showOutletsLine(departList.startOutletsName, departList.wayOutletsName, departList.targetOutletsName);
		// 发车信息
		$('#startOutletsName').text(departList.startOutletsName);
		$('#wayOutletsName').text(departList.wayOutletsName);
		$('#targetOutletsName').text(departList.targetOutletsName);
		$('#startTime').text(formatDate(departList.startTime));
		$('#endTime').text(formatDate(departList.endTime));
		// 车辆信息
		$('#vehicleNumber').text(isNull(departList.vehicleNumber));
		$('#hangVehicleNumber').text(isNull(departList.hangVehicleNumber));
		$('#driverName').text(isNull(departList.driverName));
		$('#driverPhone').text(isNull(departList.driverPhone));
		showVehicleTypeName(departList.vehicleType);
		// 司机费用
		$('#shouldPayDriverCost').text(isNull(departList.shouldPayDriverCost));
		$('#nowPayDriverCost').text(isNull(departList.nowPayDriverCost));
		$('#arrivePayDriverCost').text(isNull(departList.arrivePayDriverCost));
		$('#backPayDriverCost').text(isNull(departList.backPayDriverCost));
		// 操作费用
		$('#loadCost').text(isNull(departList.loadCost));
		$('#unloadCost').text(isNull(departList.unloadCost));
		$('#shuntCost').text(isNull(departList.shuntCost));
		$('#isYearInsurance').text(isNull(departList.isYearInsurance));
		$('#insuranceMoney').text(isNull(departList.insuranceMoney));
		$('#insuranceCost').text(isNull(departList.insuranceCost));
		// 备注信息
		$('#remark').val(isNull(departList.remark));
	}
	
	function isNull(str){
		return str == null ? '' : str;
	}
	
	// 展示网点导航信息
	function showOutletsLine(startOutletsName, wayOutletsNames, targetOutletsName){
		var html = '<b>'+startOutletsName+'</b><span> --&gt; <span>';
		if(wayOutletsNames != null && wayOutletsNames != ''){
			var wayOutletsNameArr = wayOutletsNames.split(',');
			for(var i = 0; i < wayOutletsNameArr.length; i++){
				html += '<b>'+wayOutletsNameArr[i]+'</b><span> --&gt; <span>';
			}
		}
		html += '<b>'+targetOutletsName+'</b>';
		$('#outlets_line').html(html);
	}
	
	// 入库
	function storage(wayBillNumber){
		var departNumber = $('#departNumber').text();
		BUI.Message.Confirm('确定要入库吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/signstorage/waybillpartstorage.shtml',
				data : { 'departNumber' : departNumber, 'wayBillNumber' : wayBillNumber },
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('操作成功',function(){
							store.load();
						},'success');
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
	}
	
	// 展示 车辆类型名称
	function showVehicleTypeName(vehicleType){
		if(vehicleType == null || vehicleType == ''){
			return '';
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/dic/getvehicletype.shtml',
			success : function(result){
				var data = result.rows;
				for(var i = 0; i < data.length; i++){
					if(vehicleType == data[i].id){
						$('#vehicleType').text(data[i].name);
					}
				}
			}
		});
	}
	</script>
</body>
</html>