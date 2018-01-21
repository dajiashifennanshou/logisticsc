<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回单管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>回单管理</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form id="receipt_form" class="well form-inline">
					        <label class="control-label">回单状态</label>
					        <select class="input-normal" id="receiptStatus">
					        	<option value="">全部</option>
					        	<c:forEach items="${receiptStatusList}" var="receiptStatus">
					        		<option value="${receiptStatus.value}">${receiptStatus.name}</option>
					        	</c:forEach>
					        </select>
				        	
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="运单号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="wayBillOrderList"></div>
						<div id="wayBillOrderListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="hide" id="updateReceiptStatusDialog">
		<form class="form-horizontal">
			<div class="row">
				<div class="control-group">
					<label class="control-label">回单状态：</label>
					<div class="controls">
						<select id="receiptStatus-dialog" class="input-normal">
							<c:forEach items="${receiptStatusList}" var="receiptStatus">
				        		<option value="${receiptStatus.value}">${receiptStatus.name}</option>
				        	</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var store, grid, updateReceiptStatusDialog;
	$(function(){
		loadCalendar();
		loadWayBillOrderList();
		loadUpdateReceiptStatusDialog();
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
	function loadWayBillOrderList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '下单时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'wayBillOrderTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '回单份数',
				elCls : 'center',
				dataIndex : 'receiptSlipNum'
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
				title : '运单状态',
				elCls : 'center',
				dataIndex : 'statusName'
			}, {
				title : '是否回单',
				elCls : 'center',
				dataIndex : 'receiptSlip',
				renderer : function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}
			}, {
				title : '回单状态',
				elCls : 'center',
				dataIndex : 'receiptStatusName'
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
				title : '货物名称',
				elCls : 'center',
				dataIndex : 'cargoName'
			}, {
				title : '件数',
				elCls : 'center',
				dataIndex : 'cargoNumber'
			}, {
				title : '套数',
				elCls : 'center',
				dataIndex : 'cargoSetNumber'
			}, {
				title : '代收货款',
				elCls : 'center',
				dataIndex : 'agencyFund'
			}, {
				title : '垫付货款',
				elCls : 'center',
				dataIndex : 'advanceCost'
			}, {
				title : '总运费',
				elCls : 'center',
				dataIndex : 'total'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/waybill/searchreceipt.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#wayBillOrderList',
				columns : columns,
				store : store,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                	btnCls : 'button button-normal',
	                    text:'回单状态更改',
	                    handler : showUpdateReceiptStatusDialog
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'导出',
	                    handler: exportReceipt
	                }]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#wayBillOrderListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}
	
	// 加载日历插件
	function loadCalendar(){
		BUI.use('bui/calendar',function(Calendar){
	    	var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	        });
		});
	}
	
	// 查询
	function search(){
		var params = {
			'receiptStatus' : $('#receiptStatus').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 显示 回单状态更改 弹出框
	function showUpdateReceiptStatusDialog(){
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
		var receiptSlip = selection[0].receiptSlip;
		if(receiptSlip == 0){
			BUI.Message.Alert('无回单，不可更改回单状态','warning');
			return;
		}
		updateReceiptStatusDialog.show();
	}
	
	// 加载 回单状态更改 弹出框
	function loadUpdateReceiptStatusDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			updateReceiptStatusDialog = new Overlay.Dialog({
		    	title:'回单状态更改',
		        width:360,
		        height:140,
		        contentId:'updateReceiptStatusDialog',
		        success:function () {
		        	$.ajax({
		            	type : 'post',
		            	url : '<%=request.getContextPath()%>/tms/waybill/updatereceiptstatus.shtml',
		            	data : {
		            		'wayBillNumber' : grid.getSelection()[0].wayBillNumber,
		            		'receiptStatus' : $('#receiptStatus-dialog').val()
		            	},
		            	success : function(result){
		            		if(result == 1){
		            			updateReceiptStatusDialog.close();
		            			BUI.Message.Alert('修改成功',function(){
		            				search();
		            			},'success');
		            		}else{
		            			BUI.Message.Alert('修改失败','error');
		            		}
		            	}
		            });
		        }
		    });
			updateReceiptStatusDialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	function exportReceipt(){
		var receiptStatus = $("#receiptStatus").val(),
			startTime = $("#startTime").val(),
			endTime = $("#endTime").val(),
			condition = $("#condition").val();
		window.location.href='<%=request.getContextPath()%>/tms/waybill/exportReceipt.shtml?receiptStatus='+receiptStatus
				+'&startTime='+startTime+'&endTime='+endTime+'&condition='+condition;
	}
	</script>
</body>
</html>