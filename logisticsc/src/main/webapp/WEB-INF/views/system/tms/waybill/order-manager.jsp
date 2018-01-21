<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

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
					        <label class="control-label">网点：</label>
					        <input type="text" id="outletsName" class="input-normal" placeholder="网点名称">
					        
					        <label class="control-label">订单状态：</label>
					        <select id="status"></select>
				        	
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
		loadOrderStatus();
	});
	
	// 加载运单列表
	function platformOrderList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
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
				title : '发货人',
				elCls : 'center',
				dataIndex : 'consignorName'
			}, {
				title : '发货电话',
				elCls : 'center',
				dataIndex : 'consignorPhoneNumber'
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
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consigneeName'
			}, {
				title : '收货电话',
				elCls : 'center',
				dataIndex : 'consigneePhoneNumber'
			}, {
				title : '下单时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'orderTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/sys/order/search.shtml',
				autoLoad : true,
				proxy : { method : 'post' },
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#platformOrderList',
				columns : columns,
				store : store,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                items:[{
	                    btnCls : 'button button-normal',
	                    text:'查看详情',
	                    handler: toOrderItems
	                }]
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
			'outletsName' : $('#outletsName').val(),
			'status' : $('#status').val(),
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
	
	// 加载订单状态下拉框
	function loadOrderStatus(){
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '<%=request.getContextPath()%>/tmsorder/getorderstatus.shtml',
			success : function(result){
				var html = "<option value=''>全部</option>";
				if(result != null){
					result = eval("("+result+")");
					for(var i = 0; i < result.length; i++){
						html += "<option value='"+result[i].value+"'>"+result[i].name+"</option>";
					}
				}
				document.getElementById('status').innerHTML = html;
			}
		});
	}
	
	// 获取选择的订单号
	function getCheckedOrder(){
		var orderNumbers = [];
		var selection = grid.getSelection();
		for(var i = 0; i < selection.length; i++){
			orderNumbers.push(selection[i].orderNumber);
		}
		return orderNumbers;
	}
	
	// 跳转到订单详情页面
	function toOrderItems(){
		var selection = grid.getSelection();
		if(selection.length==1){
			window.location.href='<%=request.getContextPath() %>/tmsorder/orderitems.shtml?id='+selection[0].id;
		}else{
			BUI.Message.Alert("只能选择一条记录",'warning');
		}
		
	}
	
	</script>
</body>
</html>