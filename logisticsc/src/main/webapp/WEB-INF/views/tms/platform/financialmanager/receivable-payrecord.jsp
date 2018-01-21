<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应付款订单表</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>应付款订单表</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>网点：</label><span>成都</span>
				        	<label class="control-label">结算状态</label>
					        <select class="input-normal" id="isCompleted">
					        	<option value="">全部</option>
					        	<option value="0">未完结</option>
					        	<option value="1">已完结</option>
					        </select>
					        <label class="control-label">费用项目</label>
					        <select class="input-normal" id="costSubject">
					        	<option value="">全部</option>
					        	<option value="外包费">外包费</option>
					        	<option value="中转费">中转费</option>
					        	<option value="司机运费">司机运费</option>
					        </select>
					        <label class="control-label">开始时间</label>
					        <input type="text" id="startTime" class="calendar">
					        <label class="control-label">结束时间</label>
					        <input type="text" id="endTime" class="calendar">
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号">
				            <button type="button" class="button" onclick="search()">查询</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>应付款订单表 </h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="receivablePayList"></div>
								<div id="receivablePayListBar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide" id="receivablePayDialog">
			<form class="form-horizontal" id="receivablePayForm">
				<input type="hidden" name="billId" id="billId">
				<input type="hidden" name="departNumber" id="departNumber">
				<input type="hidden" name="total" id="total">
				<input type="hidden" name="token" id="token" value="${token}">
				<div class="row">
					<div class="control-group">
						<label class="control-label">费用项目：</label>
						<div class="controls">
							<span id="costSubjectText" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="costSubjectDialog" name="costSubject">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">收款公司：</label>
						<div class="controls">
							<input type="text" name="receiveCompany" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>收款人：</label>
						<div class="controls">
							<input type="text" name="receivePerson" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>手机号：</label>
						<div class="controls">
							<input type="text" name="receivePersonPhone" class="input-normal" data-rules="{required:true,regexp:/^\d{11}$/}" data-messages="{regexp:'手机号码不正确'}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款类型：</label>
						<div class="controls">
							<select id="payMethod" onchange="choicePayMethod(this.value)"></select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">应付：</label>
						<div class="controls">
							<span id="shouldPayMoney" class="control-label" style="text-align: left;"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">金额：</label>
						<div class="controls">
							<input type="text" id="receiveMoney" name="receiveMoney" class="input-normal" readonly="readonly">
						</div>
					</div>
				</div>
				<!-- <div class="row">
					<div class="control-group">
						<label class="control-label">是否完结：</label>
						<div class="controls">
							<input type="checkbox" name="isCompleted" value="1" style="height: 30px; width: 15px;">
						</div>
					</div>
				</div> -->
				<input type="hidden" id="isCompleted" name="isCompleted" value="0">
			</form>
		</div>
		<div class="hide" id="receivablePayRecordDialog">
			<div id="receivablePayRecordList"></div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var grid, store, receivablePayRecordStore, receivablePayRecordGrid;
		var receivablePayDialog, receivablePayRecordDialog;
		var receivablePayForm;
		
		$(function(){
			loadReceivablePayList();
			loadCalendar();
			loadReceivablePayDialog();
			loadReceivablePayRecordDialog();
		});
		
		// 加载应付款列表
		function loadReceivablePayList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '发车时间',
					elCls : 'center',
					dataIndex : 'createTime',
					width : 150,
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '费用项目',
					elCls : 'center',
					dataIndex : 'costSubject'
				}, {
					title : '发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'departNumber'
				}, {
					title : '外包发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'outDepartNumber'
				}, {
					title : '中转发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'transferDepartNumber'
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
					dataIndex : 'destination'
				}, {
					title : '承运公司名称',
					elCls : 'center',
					dataIndex : 'carriageCompany'
				}, {
					title : '车牌号',
					elCls : 'center',
					dataIndex : 'vehicleNumber'
				}, {
					title : '司机名称',
					elCls : 'center',
					dataIndex : 'driverName'
				}, {
					title : '司机电话',
					elCls : 'center',
					dataIndex : 'driverPhone'
				}, {
					title : '结算状态',
					elCls : 'center',
					dataIndex : 'isCompleted',
					renderer : function(value){
						if(value == 0){
							return '未完结';
						}else if(value == 1){
							return '已完结';
						}
					}
				}, {
					title : '应付合计',
					elCls : 'center',
					dataIndex : 'total'
				}, {
					title : '现付已付',
					elCls : 'center',
					dataIndex : 'currentPay'
				}, {
					title : '到付已付',
					elCls : 'center',
					dataIndex : 'arrivePay'
				}, {
					title : '回付已付',
					elCls : 'center',
					dataIndex : 'backPay'
				}, {
					title : '收款人',
					elCls : 'center',
					dataIndex : 'receivePerson'
				}, {
					title : '收款人电话',
					elCls : 'center',
					dataIndex : 'receivePersonPhone'
				} ];
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getreceivablepaybill.shtml',
					autoLoad : true,
					proxy : { method : 'post' },
					//remoteSort : true,
					pageSize : 10,
					listeners : {
						beforeprocessload : function(e){
							$('#receivablePayTotal').text(e.data.data);
						}
					}
				});
				grid = new Grid.Grid({
					render : '#receivablePayList',
					columns : columns,
					store : store,
					//forceFit : true,
					plugins : [Grid.Plugins.CheckSelection],
					tbar:{
		                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
		               items:[ /*{
		                	btnCls : 'button button-normal',
		                    text:'付款',
		                    handler : showReceivablePayDialog
		                },*/ {
		                	btnCls : 'button button-normal',
		                    text:'查看付款记录',
		                    handler : findReceivablePayRecord
		                } ]
		            }
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#receivablePayListBar',
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
				'isCompleted' : $('#isCompleted').val(),
				'costSubject' : $('#costSubject').val(),
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val(),
				'condition' : $('#condition').val()
			}
			store.load(params);
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
		
		// 显示 付款 弹出框
		function showReceivablePayDialog(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条记录','warning');
				return;
			}
			var isCompleted = selection[0].isCompleted;
			if(isCompleted == 1){
				BUI.Message.Alert('该账单已完结','warning');
        		return;
			}
			receivablePayDialog.show();
			var departNumber = selection[0].departNumber;
			if(departNumber != null && departNumber != ''){
				var startOutlets = selection[0].startOutlets;
				var targetOutlets = selection[0].targetOutlets;
				var outletsId = selection[0].outletsId;
				var options = '';
				if(startOutlets == outletsId){
					options += '<option value="0">现付</option>';
					options += '<option value="2">回付</option>';
					$('#payMethod').html(options);
					$('#shouldPayMoney').text(selection[0].currentPay);
					$('#receiveMoney').val(selection[0].currentPay);
				}else if(targetOutlets == outletsId){
					options += '<option value="1">到付</option>';
					$('#payMethod').html(options);
					$('#shouldPayMoney').text(selection[0].arrivePay);
					$('#receiveMoney').val(selection[0].arrivePay);
				}
			}
			var outDepartNumber = selection[0].outDepartNumber;
			if(outDepartNumber != null && outDepartNumber != ''){
				var options = '<option value="0">现付</option>';
				options += '<option value="2">回付</option>';
				$('#payMethod').html(options);
				$('#shouldPayMoney').text(selection[0].currentPay);
				$('#receiveMoney').val(selection[0].currentPay);
			}
			var transferDepartNumber = selection[0].transferDepartNumber;
			if(transferDepartNumber != null && transferDepartNumber != ''){
				var options = '<option value="0">现付</option>';
				options += '<option value="2">回付</option>';
				$('#payMethod').html(options);
				$('#shouldPayMoney').text(selection[0].currentPay);
				$('#receiveMoney').val(selection[0].currentPay);
			}
			
			$('#billId').val(selection[0].id);
			$('#costSubjectText').text(selection[0].costSubject);
			$('#costSubjectDialog').val(selection[0].costSubject);
			$('#total').val(selection[0].total);
			var departNumber = selection[0].departNumber;
			if(departNumber == null || departNumber == '' || typeof(departNumber) == 'undefined'){
				departNumber = selection[0].outDepartNumber;
			}
			if(departNumber == null || departNumber == '' || typeof(departNumber) == 'undefined'){
				departNumber = selection[0].transferDepartNumber;
			}
			$('#departNumber').val(departNumber);
        	loadForm();
		}
		
		// 加载 付款 弹出框
		function loadReceivablePayDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				receivablePayDialog = new Overlay.Dialog({
			    	title:'付款',
			        width:450,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'receivablePayDialog',
			        success:function () {
			        	if(!receivablePayForm.isValid()){
			        		return;
			        	}
			        	var receiveMoney = $('#receiveMoney').val();
			        	var shouldPayMoney = $('#shouldPayMoney').text();
			        	if(new Number(receiveMoney) > new Number(shouldPayMoney)){
			        		BUI.Message.Alert('付款金额不能大于应付金额','warning');
			        		return;
			        	}
			        	$.ajax({
			        		type : 'post',
			        		url : '<%=request.getContextPath()%>/tms/financial/savereceivablepaybillrecord.shtml',
			        		data : $('#receivablePayForm').serialize(),
			        		success : function(result, textStatus, XMLHttpRequest){
			        			// 通过XMLHttpRequest取得响应头
			        			var isRepeatSubmit = XMLHttpRequest.getResponseHeader('isRepeatSubmit');
			        			if(isRepeatSubmit == 'true'){
			        				//alert('请不要重复提交');
			        			}else{
			        				var token = XMLHttpRequest.getResponseHeader('token');
				        			$('#token').val(token);
				        			if(result == 1){
				        				BUI.Message.Alert('操作成功',function(){
				        					search();
				        				},'success');
				        			}else{
				        				BUI.Message.Alert('操作失败','error');
				        			}
				        			receivablePayDialog.close();
			        			}
			        		}
			        	});
			        }
			    });
				receivablePayDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		function loadForm(){
			BUI.use('bui/form',function(Form){
				receivablePayForm = new Form.Form({
					srcNode : '#receivablePayForm'
				}).render();
			})
		}
		
		// 查看付款记录
		function findReceivablePayRecord(){
			showReceivablePayRecordDialog();
		}
		
		// 显示 更改配送方式 弹出框
		function showReceivablePayRecordDialog(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条记录','warning');
				return;
			}
			$('#receivablePayRecordList').html('');
			receivablePayReocrdDialog.show();
			loadReceivablePayRecordList(selection[0].id);
		}
		
		// 加载 更改配送方式 弹出框
		function loadReceivablePayRecordDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				receivablePayReocrdDialog = new Overlay.Dialog({
			    	title:'付款记录',
			        width:800,
			        height:400,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'receivablePayRecordDialog',
			        success:function () {
			            this.close();
			        }
			    });
				receivablePayReocrdDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 加载收款记录列表
		function loadReceivablePayRecordList(billId){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '日期',
					width : 150,
					elCls : 'center',
					dataIndex : 'operateTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '收款单位',
					elCls : 'center',
					dataIndex : 'receiveCompany'
				}, {
					title : '收款人',
					elCls : 'center',
					dataIndex : 'receivePerson'
				}, {
					title : '手机号',
					elCls : 'center',
					dataIndex : 'receivePersonPhone'
				}, {
					title : '金额',
					elCls : 'center',
					dataIndex : 'receiveMoney'
				} ];
				
				receivablePayRecordStore = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getreceivablepaybillrecord.shtml',
					autoLoad : true,
					params : { 'billId' : billId }
					//remoteSort : true,
					//pageSize : 10
				});
				receivablePayRecordGrid = new Grid.Grid({
					render : '#receivablePayRecordList',
					columns : columns,
					store : receivablePayRecordStore,
					forceFit : true
				});

				receivablePayRecordGrid.render();
			});
		}
		
		function choicePayMethod(value){
			var row = grid.getSelection()[0];
			if(value == 0){
				var currentPay = row.currentPay == null ? 0 : row.currentPay;
				$('#shouldPayMoney').text(currentPay);
				$('#receiveMoney').val(currentPay);
			}else if(value == 1){
				var arrivePay = row.arrivePay == null ? 0 : row.arrivePay;
				$('#shouldPayMoney').text(arrivePay);
				$('#receiveMoney').val(arrivePay);
			}else if(value == 2){
				var backPay = row.backPay == null ? 0 : row.backPay;
				$('#shouldPayMoney').text(backPay);
				$('#receiveMoney').val(backPay);
			}
		}
	</script>
</body>
</html>