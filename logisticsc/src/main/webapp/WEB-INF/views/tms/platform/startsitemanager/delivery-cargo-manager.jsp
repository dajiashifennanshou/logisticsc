<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提货管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

<style type="text/css">
.search li{
	display: inline-block;
	float: left;
	margin: 10px 15px;
}
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
				<h3>提货管理</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
					        <label class="control-label">订单状态：</label>
					        <select id="ladingOrderStatus"></select>
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
						<div id="ladingOrderList"></div>
						<div id="ladingOrderListBar"></div>
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
		loadBuiCalendar();
		loadLadingOrderStatus();
		loadLadingOrderList();
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
	
	// 加载提货单列表
	function loadLadingOrderList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '派车时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'ladingOrderTime',
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
				title : '派车单状态',
				elCls : 'center',
				dataIndex : 'statusName'
			}, {
				title : '异常状态',
				elCls : 'center',
				dataIndex : 'exceptionStatusName'
			}, {
				title : '车牌号',
				elCls : 'center',
				dataIndex : 'licensePlateNo'
			}, {
				title : '司机姓名',
				elCls : 'center',
				dataIndex : 'driverName'
			}, {
				title : '司机电话',
				elCls : 'center',
				dataIndex : 'driverMobile'
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
				dataIndex : 'consignor'
			}, {
				title : '发货人电话',
				elCls : 'center',
				dataIndex : 'consignorMobile'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consignee'
			}, {
				title : '收货人电话',
				elCls : 'center',
				dataIndex : 'consigneeMobile'
			}, {
				title : '操作员',
				elCls : 'center',
				dataIndex : 'operatePersonName'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/deliverycargo/searchladingorder.shtml',
				autoLoad : true,
				proxy : { method : 'post' },
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#ladingOrderList',
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
	                    text:'开单入库',
	                    handler : function(){
	                    	palceWayBill();
	                    }
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'开提货单',
	                    handler : function(){
	                    	deliveryCargo();
	                    }
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'派车提货',
	                    handler : deliveryTakeCargo
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'作废',
	                    handler : abolish
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'打印',
	                    handler : printDeliveryOrder
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'异常登记',
	                    handler : toAbnormal
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查询详情',
	                    handler : toDeliveryCargoOrderDetail
	                }]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#ladingOrderListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}
	
	function loadBuiCalendar(){
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
	}
	
	// 加载 更改派车单状态 弹出框
	function loadDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			dialog = new Overlay.Dialog({
		    	title:'更改派车单状态',
		        width:400,
		        height:230,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'updateLadingOrderStatusDialog',
		        success:function () {
		        	updateLadingOrderStatus(this);
		        }
		    });
            dialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	// 加载派车单状态下拉框
	function loadLadingOrderStatus(){
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '<%=request.getContextPath()%>/deliverycargo/getladingorderstatus.shtml',
			success : function(result){
				var html = '';
				if(result != null){
					result = eval("("+result+")");
					for(var i = 0; i < result.length; i++){
						html += "<option value='"+result[i].value+"'>"+result[i].name+"</option>";
					}
				}
				document.getElementById('ladingOrderStatus').innerHTML = '<option value="">全部</option>' + html;
				document.getElementById('ladingOrderStatus-dialog').innerHTML = html;
			}
		});
	}
	// 查询
	function search(){
		var params = {
			'status' : $('#ladingOrderStatus').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 开派车单
	function deliveryCargo(){
		if(!validateUserType()){
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/deliverycargo/deliverycargoorder.shtml';
	}
	
	// 派车提货
	function deliveryTakeCargo(){
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
		if(selection[0].status != 0){
			BUI.Message.Alert('只有未派车的提货单可派车提货','warning');
			return;
		}
		BUI.Message.Confirm('确定吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/deliverycargo/deliverytakecargo.shtml',
				data : {'wayBillNumber' : grid.getSelected().wayBillNumber},
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('操作成功','success');
						search();
					}else{
						BUI.Message.Alert('修改失败','error');
					}
				}
			});
		},'question');
	}
	
	// 作废
	function abolish(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		var status = selection[0].status;
		if(status == 2){
			BUI.Message.Alert('已开单入库的运单不能作废','warning');
			return;
		}else if(status == 3){
			BUI.Message.Alert('已作废的运单不能作废','warning');
			return;
		}
		var exceptionStatus = selection[0].exceptionStatus;
		if(exceptionStatus != 0){
			BUI.Message.Alert("异常状态的提货单不能作废",'warning');
			return;
		}
		BUI.Message.Confirm('确定吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/deliverycargo/abolish.shtml',
				data : {'wayBillNumber' : grid.getSelected().wayBillNumber},
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('操作成功','success');
						search();
					}else{
						BUI.Message.Alert('修改失败','error');
					}
				}
			});
		},'question');
	}
	
	// 开单入库
	function palceWayBill(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		if(selection[0].status != 1){
			BUI.Message.Alert('只有已派车的提货单可以开单入库','warning');
			return;
		}
		var exceptionStatus = selection[0].exceptionStatus;
		if(exceptionStatus != 0){
			BUI.Message.Alert("异常状态的提货单不能开单入库",'warning');
			return;
		}
		var orderNumber = selection[0].orderNumber;
		if(orderNumber != null && orderNumber != ''){
			BUI.Message.Alert("货运交易系统订单不能在此开单入库",'warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tms/waybill/towaybillpage.shtml?wayBillNumber='+selection[0].wayBillNumber;
	}
	
	// 异常登记
	function toAbnormal(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert("请选择",'warning');
			return;
		}
		var exceptionStatus = selection[0].exceptionStatus;
		if(exceptionStatus != 0){
			BUI.Message.Alert("该运单已登记异常",'warning');
			return;
		}
		if(selection[0].status != 1){
			BUI.Message.Alert("只有已派车的提货单可进行异常登记",'warning');
			return;
		}
		window.location.href="<%=request.getContextPath() %>/tms/abnormal/addabnormal.shtml?wayBillNumber="+selection[0].wayBillNumber;
	}
	
	// 打印
	function printDeliveryOrder(){
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一个提货单','warning');
			return;
		}
		window.open('<%=request.getContextPath()%>/deliverycargo/todeliverycargoorderpreview.shtml?wayBillNumber='+selection[0].wayBillNumber);
	}
	
	// 查询详情
	function toDeliveryCargoOrderDetail(){
		var selection = grid.getSelection();
		if(selection.length==1){
			window.location.href='<%=request.getContextPath() %>/deliverycargo/todeliverycargoorderdetail.shtml?id='+selection[0].id;
		}else{
			BUI.Message.Alert("请选择一条订单记录",'warning');
		}
	}
	</script>
</body>
</html>