<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-row-register{
	color: red;
}
.bui-grid-row-handler{
	color: green;
}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>订单管理</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
					        <label class="control-label">订单状态：</label>
					        <select id="orderStatus">
					        	<option value="">全部</option>
					        	<c:forEach items="${orderStatusList}" var="orderStatus">
					        		<option value="${orderStatus.id}">${orderStatus.name}</option>
					        	</c:forEach>
					        </select>
				        	
				        	<label class="control-label">开始时间：</label>
					        <input type="text" id="startTime" class="calendar">
					        
					        <label class="control-label">结束时间：</label>
					        <input type="text" id="endTime" class="calendar">
					        
				            <input type="text" id="condition" class="input-normal" placeholder="运单号/订单号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="platformOrderList"></div>
						<div id="platformOrderListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var store, grid;
	$(function(){
		platformOrderList();
		loadBuiCalendar();
	});
	
	// 验证用户类型
	function validateUserType(){
		var userType = ${tms_user_session.userType};
		if(userType == 0 || userType == 1){
			BUI.Message.Alert('请使用网点账号登录','warning');
			return false;
		}
		return true;
	}
	
	// 加载运单列表
	function platformOrderList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '下单时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'orderTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '订单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'orderNumber'
			}, {
				title : '运单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'wayBillNumber'
			}, {
				title : '订单状态',
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
				title : '发货方式',
				elCls : 'center',
				dataIndex : 'sendCargoType',
				renderer : function(value){
					if(value == '0'){
						return '自送网点';
					}else if(value == '1'){
						return '上门取货';
					}
				}
			}, {
				title : '支付方式',
				elCls : 'center',
				dataIndex : 'payTypeName'
			}, {
				title : '预约费',
				elCls : 'center',
				dataIndex : 'takeCargoCost'
			}, {
				title : '确认运费',
				elCls : 'center',
				dataIndex : 'totalCost'
			}, {
				title : '未付运费',
				elCls : 'center',
				dataIndex : 'unPrepaidCost'
			}, {
				title : '发货人',
				elCls : 'center',
				dataIndex : 'consignorName'
			}, {
				title : '发货电话',
				elCls : 'center',
				dataIndex : 'consignorPhoneNumber'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consigneeName'
			}, {
				title : '收货电话',
				elCls : 'center',
				dataIndex : 'consigneePhoneNumber'
			}, {
				title : '操作员',
				elCls : 'center',
				dataIndex : 'operatePerson'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tmsorder/searchOrder.shtml',
				autoLoad : true,
				proxy : { method : 'post' },
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#platformOrderList',
				columns : columns,
				store : store,
				itemStatusFields : { //设置数据跟状态的对应关系
		        	register : 'exceptionRegister',
		            handler : 'exceptionHandler' //如果readed : true,则附加 bui-grid-row-read 样式
		        },
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                items:[{
	                	btnCls : 'button button-normal',
	                    text:'接单',
	                    handler : receiveOrder
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'拒绝',
	                    handler : refuseOrder
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'派车提货',
	                    handler : deliveryCargo
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'开单入库',
	                    handler : palceWayBill
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'作废',
	                    handler : discardOrder
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'议价',
	                    handler : confirmCargoInfo
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查看详情',
	                    handler: toOrderItems
	                } ]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#platformOrderListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}
	
	// 查询
	function search(){
		var params = {
			'status' : $('#orderStatus').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 加载BUI日历插件
	function loadBuiCalendar(){
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
	}
	
	// 派车提货
	function deliveryCargo(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length < 1){
			BUI.Message.Alert('请选择订单','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一个订单','warning');
			return;
		}
		var status = selection[0].state;
		var sendCargoType = selection[0].sendCargoType;
		if(status != 1){
			BUI.Message.Alert('只有已受理的订单可以派车提货','warning');
			return;
		}
		if(sendCargoType != 1){
			BUI.Message.Alert('只有发货方式为上门取货可以派车提货','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/deliverycargo/deliverycargoorder.shtml?orderNumber='+selection[0].orderNumber;
	}
	
	// 验证提货订单状态
	function validateOrderStatusDeliveryCargo(orderNumber){
		var b = false;
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tmsorder/validateorderstatusdeliverycargo.shtml',
			async: false,
			data : {'orderNumber' : orderNumber},
			success : function(result){
				if(result == 1){
					b = true;
				}else{
					b = false;
				}
			}
		});
		return b;
	}
	
	// 接单
	function receiveOrder(){
		if(!validateUserType()){
			return;
		}
		var orderNumbers = [];
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择订单','warning');
			return;
		}
		for(var i = 0; i < selection.length; i++){
			if(selection[i].state == 0){
				orderNumbers.push(selection[i].orderNumber);
			}else{
				BUI.Message.Alert('只有预约状态的订单可以接单','warning');
				return;
			}
		}
		BUI.Message.Confirm('确定要接单吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tmsorder/receiveorder.shtml',
				data : {'orderNumbers' : JSON.stringify(orderNumbers)},
				success : function(result){
					if(result == 1){
						store.load();
						BUI.Message.Alert('接单成功','success');
					}else{
						BUI.Message.Alert('接单失败','error');
					}
				}
			});
		},'question');
	}
	
	// 拒绝订单
	function refuseOrder(){
		if(!validateUserType()){
			return;
		}
		var orderNumbers = [];
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择订单','warning');
			return;
		}
		for(var i = 0; i < selection.length; i++){
			if(selection[i].state == 0){
				orderNumbers.push(selection[i].orderNumber);
			}else{
				BUI.Message.Alert('只有预约状态的订单可以拒绝','warning');
				return;
			}
		}
		BUI.Message.Confirm('确定要拒绝吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tmsorder/refuseorder.shtml',
				data : {'orderNumbers' : JSON.stringify(orderNumbers)},
				success : function(result){
					if(result == 1){
						store.load();
						BUI.Message.Alert('操作成功');
					}else{
						BUI.Message.Alert('操作失败');
					}
				}
			});
		},'question');
	}
	
	// 作废订单
	function discardOrder(){
		if(!validateUserType()){
			return;
		}
		var orderNumbers = [];
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择订单','warning');
			return;
		}
		for(var i = 0; i < selection.length; i++){
			var state = selection[i].state;
			if(state == 0 || state == 1 || state == 2 || state == 11){
				orderNumbers.push(selection[i].orderNumber);
			}else{
				BUI.Message.Alert('该订单不能作废','warning');
				return;
			}
			orderNumbers.push(selection[i].orderNumber);
		}
		BUI.Message.Confirm('确定要作废吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tmsorder/discardorder.shtml',
				data : {'orderNumbers' : JSON.stringify(orderNumbers)},
				success : function(result){
					if(result == 1){
						store.load();
						BUI.Message.Alert('操作成功');
					}else{
						BUI.Message.Alert('操作失败');
					}
				}
			});
		},'question');
	}
	
	// 跳转到订单详情页面
	function toOrderItems(){
		var selection = grid.getSelection();
		if(selection.length==1){
			window.location.href='<%=request.getContextPath() %>/tmsorder/orderitems.shtml?id='+selection[0].id;
		}else{
			BUI.Message.Alert("请选择一条订单记录",'warning');
		}
		
	}
	
	// 跳转到异常登记页面
	function toAbnormal(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length==1){
			if(selection[0].wayBillNumber){
				if(selection[0].state != 2 && selection[0].state != 3){
					BUI.Message.Alert(selection[0].statusName + "的订单不能异常登记",'warning');
					return;
				}
				window.location.href="<%=request.getContextPath() %>/tms/abnormal/addabnormal.shtml?wayBillNumber="+selection[0].wayBillNumber;
			}else{
				BUI.Message.Alert("运单未生成，不能登记异常",'warning');
				return;
			}
		}else{
			BUI.Message.Alert("请选择一条订单",'warning');
		}
	}
	
	// 开单入库
	function palceWayBill(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length < 1){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一条记录','warning');
			return;
		}
		var status = selection[0].state;
		if(status != 3){
			BUI.Message.Alert('只有订单状态为议价的订单可以开单入库','warning');
			return;
		}
		var payType = selection[0].payType;
		if(payType == 0){
			var isPayment = false;
			$.ajax({
				type : 'post',
				async : false,
				url : '<%=request.getContextPath()%>/tmsorder/validateorderispay.shtml',
				data : {'orderNumber' : selection[0].orderNumber},
				success : function(result){
					isPayment = result;
				}
			});
			if(isPayment == false){
				BUI.Message.Alert('该订单支付为完成，请联系货主支付未付费用','warning');
				return;
			}
		}
		window.location.href = '<%=request.getContextPath()%>/tms/waybill/towaybillpage.shtml?orderNumber='+selection[0].orderNumber;
	}
	
	// 确认货物信息
	function confirmCargoInfo(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条订单记录','warning');
			return;
		}
		var status = selection[0].state;
		var sendCargoType = selection[0].sendCargoType;
		if(sendCargoType == 0){
			if(status != 1){
				BUI.Message.Alert('该订单状态为已受理时可以议价','warning');
				return;
			}
		}else if(sendCargoType == 1){
			if(status != 2){
				BUI.Message.Alert('该订单状态为提货中时可以议价','warning');
				return;
			}
		}
		var exceptionStatus = selection[0].exceptionStatus;
		if(exceptionStatus != null && exceptionStatus != 0){
			BUI.Message.Alert('异常状态的订单不能议价','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tmsorder/ordercargoconfirm.shtml?id='+selection[0].id;
	}
	</script>
</body>
</html>