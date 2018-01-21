<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应收款订单表</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-stdmod-body{
	overflow-x : auto;
	overflow-y : auto;
}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>应收款订单表</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>收款状态：</label>
				        	<select class="input-normal" id="status">
					        	<option value="">全部</option>
					        	<c:forEach items="${receiveMoneyOrderStatusList}" var="receiveMoneyOrderStatus">
					        		<option value="${receiveMoneyOrderStatus.id}">${receiveMoneyOrderStatus.name}</option>
					        	</c:forEach>
					        </select>
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
				            <input type="text" class="input-normal" id="condition" placeholder="收款订单号">
				            <button type="button" class="button" onclick="search()">查询</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>应收款订单表</h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="receiveMoneyOrderList"></div>
								<div id="receiveMoneyOrderListBar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide" id="receiveMoneyDialog">
			<form class="form-horizontal" id="receiveMoneyOrderForm">
				<input type="hidden" id="receiveMoneyOrderId" name="receiveMoneyOrderId">
				<input type="hidden" name="isAgencyFund" value="0">
				<input type="hidden" name="token" id="token" value="${token}">
				<div class="row">
					<div class="control-group">
						<label class="control-label">订单号：</label>
						<div class="controls">
							<span class="control-text" id="orderNumberText"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">费用类型：</label>
						<div class="controls">
							<span class="control-text" id="costTypeText">运费</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">应收金额：</label>
						<div class="controls">
							<span class="control-text" id="cashMoneyText"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>实收现金：</label>
						<div class="controls">
							<input type="text" name="receiveMoney" id="receiveMoney" class="input-normal" data-rules="{required:true, number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款人：</label>
						<div class="controls">
							<span class="control-text" id="payPersonText"></span>
							<input type="hidden" id="payPersonDialog" name="payPerson">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款人手机号：</label>
						<div class="controls">
							<span class="control-text" id="payPersonMobileText"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">备注：</label>
						<div class="controls">
							<textarea name="remark"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="hide" id="wayBillDetailDialog">
			<div style="margin-bottom: 10px;">
				<span>收款订单号：</span><span id="receiveMoneyOrderNumber"></span>	
			</div>
			<div id="wayBillFreightList" style="width: 770px;"></div>
		</div>
		<div class="hide" id="receiveMoneyOrderRecordDialog">
			<div id="receiveMoneyOrderRecordList" style="width: 770px;"></div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var grid, store, receiveMoneyDialog, receiveMoneyOrderForm;
		var wayBillDetailDialog, receiveMoneyOrderRecordDialog;
		
		$(function(){
			loadBuiCalendar();
			loadReceiveMoneyOrdertList();
			loadReceiveMoneyDialog();
			loadWayBillDetailDialog();
			loadReceiveMoneyOrderRecordDialog();
			loadForm();
		});
		
		// 加载BUI日历插件
		function loadBuiCalendar(){
			BUI.use('bui/calendar',function(Calendar){
		    	var datepicker = new Calendar.DatePicker({
		        	trigger:'.calendar',
		        	autoRender : true
		        });
			});
		}
		
		// 加载收款订单列表
		function loadReceiveMoneyOrdertList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '收款订单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'orderNumber'
				}, {
					title : '收款状态',
					elCls : 'center',
					dataIndex : 'status',
					renderer : function(value){
						if(value == 0){
							return '未完结';
						}else if(value == 1){
							return '已完结';
						}else if(value == 2){
							return '已撤销';
						}
					}
				}, {
					title : '订单时间',
					elCls : 'center',
					width : 150,
					dataIndex : 'operateTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '发货人',
					elCls : 'center',
					dataIndex : 'consignor'
				}, {
					title : '发货人手机号',
					elCls : 'center',
					dataIndex : 'consignorMobile'
				}, {
					title : '收货人',
					elCls : 'center',
					dataIndex : 'consignee'
				}, {
					title : '收货人手机号',
					elCls : 'center',
					dataIndex : 'consigneeMobile'
				}, {
					title : '应收运费合计',
					elCls : 'center',
					dataIndex : 'money'
				}, {
					title : '已收运费合计',
					elCls : 'center',
					dataIndex : '',
					renderer : function(value, obj, index){
						return new Number(obj.cashReceivedMoney) + new Number(obj.posReceivedMoney);
					}
				}, {
					title : '现金已收',
					elCls : 'center',
					dataIndex : 'cashReceivedMoney'
				}, {
					title : 'POS已收',
					elCls : 'center',
					dataIndex : 'posReceivedMoney'
				}, {
					title : '备注',
					elCls : 'center',
					dataIndex : 'remark'
				} ];
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/waybillfreight/getreceivemoneyorderlist.shtml',
					autoLoad : true,
					proxy:{
						method:'post',
					},
					//remoteSort : true,
					pageSize : 10
				});
				grid = new Grid.Grid({
					render : '#receiveMoneyOrderList',
					columns : columns,
					store : store,
					//forceFit : true,
					plugins : [Grid.Plugins.CheckSelection],
					tbar:{
		                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
		                items:[{
		                	btnCls : 'button button-normal',
		                    text:'现金收款',
		                    handler : showReceiveMoneyDialog
		                }, {
		                	btnCls : 'button button-normal',
		                    text:'查看运单详情',
		                    handler : showWayBillDetailDialog
		                }, {
		                	btnCls : 'button button-normal',
		                    text:'查看收款记录',
		                    handler : showReceiveMoneyOrderRecordDialog
		                } ]
		            }
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#receiveMoneyOrderListBar',
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
				'status' : $('#status').val(),
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val(),
				'condition' : $('#condition').val()
			}
			store.load(params);
		}
		
		// 显示 收款 弹出框
		function showReceiveMoneyDialog(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条订单记录','warning');
				return;
			}
			/* if(selection[0].cashMoney == selection[0].cashReceivedMoney){
				BUI.Message.Alert('该订单现金收款已完毕','warning');
				return;
			} */
			if(selection[0].status == 1){
				BUI.Message.Alert('该订单收款已完结','warning');
				return;
			}
			$('#receiveMoneyOrderId').val(selection[0].id);
			$('#orderNumberText').text(selection[0].orderNumber);
			var cashMoney = new Number(selection[0].money) - new Number(selection[0].cashReceivedMoney) - new Number(selection[0].posReceivedMoney);
			$('#cashMoneyText').text(cashMoney.toFixed(2));
			$('#payPersonText').text(selection[0].payPerson);
			$('#payPersonDialog').val(selection[0].payPerson);
			$('#payPersonMobileText').text(selection[0].payPersonMobile);
			receiveMoneyDialog.show();
		}
		
		// 加载 收款 弹出框
		function loadReceiveMoneyDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				receiveMoneyDialog = new Overlay.Dialog({
			    	title:'运费收款订单',
			        width:460,
			        height:450,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'receiveMoneyDialog',
			        success:function () {
			        	if(!receiveMoneyOrderForm.isValid()){
			        		return;
			        	}
			        	var cashMoney = $('#cashMoneyText').text();
			        	var receiveMoney = $('#receiveMoney').val();
			        	if(new Number(receiveMoney) > new Number(cashMoney)){
			        		BUI.Message.Alert('实收现金不能大于应收现金');
			        		return;
			        	}
			        	$.ajax({
			        		type : 'post',
			        		url : '<%=request.getContextPath()%>/tms/waybillfreight/savereceivemoneyorderrecord.shtml',
			        		data : $('#receiveMoneyOrderForm').serialize(),
			        		success : function(result, textStatus, XMLHttpRequest){
			        			// 通过XMLHttpRequest取得响应头
			        			var isRepeatSubmit = XMLHttpRequest.getResponseHeader('isRepeatSubmit');
			        			if(isRepeatSubmit == 'true'){
			        				//alert('请不要重复提交');
			        			}else{
			        				var token = XMLHttpRequest.getResponseHeader('token');
				        			$('#token').val(token);
				        			if(result == 1){
				        				receiveMoneyDialog.close();
				        				BUI.Message.Alert('操作成功',function(){
				        					search();
				        				},'success');
				        			}else{
				        				BUI.Message.Alert('操作失败','error');
				        			}
			        			}
			        		}
			        	});
			        }
			    });
				receiveMoneyDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		function loadForm(){
			var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
			BUI.use('bui/form',function(Form){
				receiveMoneyOrderForm = new Form.Form({
					srcNode : '#receiveMoneyOrderForm',
					//errorTpl : errorTpl
				}).render();
			})
		}
		
		// 加载运单详情 弹出框
		function loadWayBillDetailDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				wayBillDetailDialog = new Overlay.Dialog({
			    	title:'运单详情',
			        width:800,
			        height:450,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'wayBillDetailDialog',
			        success:function () {
			        	this.close();
			        }
			    });
				wayBillDetailDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 查看运单详情
		function showWayBillDetailDialog(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条订单记录','warning');
				return;
			}
			$('#receiveMoneyOrderNumber').text(selection[0].orderNumber);
			$('#wayBillFreightList').html('');
			loadwayBillFreightList(selection[0].id);
			wayBillDetailDialog.show();
		}
		
		// 加载运单运费列表
		function loadwayBillFreightList(receiveMoneyOrderId){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'departNumber'
				}, {
					title : '发车日期',
					elCls : 'center',
					width : 150,
					dataIndex : 'startTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '到达日期',
					elCls : 'center',
					width : 150,
					dataIndex : 'endTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '运单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'wayBillNumber'
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
					title : '运费',
					elCls : 'center',
					dataIndex : 'total'
				}, {
					title : '付款方式',
					elCls : 'center',
					dataIndex : 'payMethodName'
				}, {
					title : '垫付货款',
					elCls : 'center',
					dataIndex : 'advanceCost'
				}, {
					title : '代收货款',
					elCls : 'center',
					dataIndex : 'agencyFund'
				}, {
					title : '货物品牌',
					elCls : 'center',
					dataIndex : 'cargoBrand'
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
					title : '发货人',
					elCls : 'center',
					dataIndex : 'consignor'
				}, {
					title : '发货人手机号',
					elCls : 'center',
					dataIndex : 'consignorMobile'
				}, {
					title : '收货人',
					elCls : 'center',
					dataIndex : 'consignee'
				}, {
					title : '收货人手机号',
					elCls : 'center',
					dataIndex : 'consigneeMobile'
				}, {
					title : '运费',
					elCls : 'center',
					dataIndex : 'total'
				}, {
					title : '支付方式',
					elCls : 'center',
					dataIndex : 'payMethodName'
				}, {
					title : '开单时间',
					elCls : 'center',
					width : 150,
					dataIndex : 'wayBillOrderTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '签收人',
					elCls : 'center',
					dataIndex : 'signPerson'
				}, {
					title : '签收时间',
					elCls : 'center',
					dataIndex : 'signTime'
				}, {
					title : '备注',
					elCls : 'center',
					dataIndex : 'remark'
				} ];
				
				var wayBillFreightStore = new Store({
					url : '<%=request.getContextPath()%>/tms/waybillfreight/getreceivemoneywaybilllist.shtml',
					autoLoad : true,
					params : { 'receiveMoneyOrderId' : receiveMoneyOrderId }
					//remoteSort : true,
					//pageSize : 10
				});
				var wayBillFreightGrid = new Grid.Grid({
					render : '#wayBillFreightList',
					columns : columns,
					store : wayBillFreightStore,
					//forceFit : true,
					//plugins : [Grid.Plugins.CheckSelection]
				});
				wayBillFreightGrid.render();
			});
		}
		
		// 加载 收款订单收款记录 弹出框
		function loadReceiveMoneyOrderRecordDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				receiveMoneyOrderRecordDialog = new Overlay.Dialog({
			    	title:'收款记录',
			        width:800,
			        height:450,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'receiveMoneyOrderRecordDialog',
			        success:function () {
			        	this.close();
			        }
			    });
				receiveMoneyOrderRecordDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 查看收款记录
		function showReceiveMoneyOrderRecordDialog(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条订单记录','warning');
				return;
			}
			$('#receiveMoneyOrderRecordList').html('');
			loadReceiveMoneyOrderRecordList(selection[0].id);
			receiveMoneyOrderRecordDialog.show();
		}
		
		// 加载收款订单收款记录
		function loadReceiveMoneyOrderRecordList(receiveMoneyOrderId){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '付款人',
					elCls : 'center',
					dataIndex : 'payPerson'
				}, {
					title : '付款人手机号',
					elCls : 'center',
					dataIndex : 'payPersonMobile'
				}, {
					title : '收款金额',
					elCls : 'center',
					dataIndex : 'receiveMoney'
				}, {
					title : '收款方式',
					elCls : 'center',
					dataIndex : 'receiveMoneyType',
					renderer : function(value){
						if(value == 0){
							return '现金';
						}else if(value == 1){
							return 'POS机';
						}
					}
				}, {
					title : '收款人',
					elCls : 'center',
					dataIndex : 'operatePerson'
				}, {
					title : '收款时间',
					elCls : 'center',
					width : 150,
					dataIndex : 'operateTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				}];
				
				var receiveMoneyOrderRecordStore = new Store({
					url : '<%=request.getContextPath()%>/tms/waybillfreight/getreceivemoneyorderrecord.shtml',
					autoLoad : true,
					params : { 'receiveMoneyOrderId' : receiveMoneyOrderId }
					//remoteSort : true,
					//pageSize : 10
				});
				var receiveMoneyOrderRecordGrid = new Grid.Grid({
					render : '#receiveMoneyOrderRecordList',
					columns : columns,
					store : receiveMoneyOrderRecordStore,
					forceFit : true,
					//plugins : [Grid.Plugins.CheckSelection]
				});
				receiveMoneyOrderRecordGrid.render();
			});
		}
	</script>
</body>
</html>