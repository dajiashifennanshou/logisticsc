<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代收货款</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>代收货款</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>出发网点：</label>
				        	<select class="input-normal" id="startOutlets">
					        	<option value="">全部</option>
					        	<c:forEach items="${outletsInfos}" var="outletsInfo">
					        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
					        	</c:forEach>
					        </select>
					        <label>到达网点：</label>
				        	<select class="input-normal" id="targetOutlets">
					        	<option value="">全部</option>
					        	<c:forEach items="${outletsInfos}" var="outletsInfo">
					        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
					        	</c:forEach>
					        </select>
				        	<label class="control-label">发车时间</label>
					        <input type="text" class="calendar" id="startTime">
					        <label class="control-label">到达时间</label>
					        <input type="text" class="calendar" id="endTime">
					        <label class="control-label">签收日期</label>
					        <input type="text" class="calendar" id="signTime">
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号/运单号/发货人/收货人">
				            <button type="button" class="button" onclick="search()">查询</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>代收货款</h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="wayBillFreightList"></div>
								<div id="wayBillFreightListBar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide" id="receiveMoneyDialog">
			<form class="form-horizontal" id="receiveMoneyOrderForm">
				<input type="hidden" id="wayBillNumbers" name="wayBillNumbers">
				<div class="row">
					<div class="control-group">
						<label class="control-label">费用类型：</label>
						<div class="controls">
							<span class="control-text" id="costTypeText">代收款</span>
							<input type="hidden" name="costType" value="1">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">金额：</label>
						<div class="controls">
							<span class="control-text" id="moneyText"></span>
							<input type="hidden" id="money" name="money">
						</div>
					</div>
				</div>
				<!-- <div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>POS收款：</label>
						<div class="controls">
							<input type="text" id="posMoney" name="posMoney" class="input-normal" value="0" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>现金：</label>
						<div class="controls">
							<input type="text" id="cashMoney" name="cashMoney" class="input-normal" value="0" data-rules="{number:true}">
						</div>
					</div>
				</div> -->
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>付款人：</label>
						<div class="controls">
							<input type="text" name="payPerson" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>付款人手机号：</label>
						<div class="controls">
							<input type="text" name="payPersonMobile" class="input-normal" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
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
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var grid, store, receiveMoneyDialog, receiveMoneyOrderForm;
		
		$(function(){
			loadBuiCalendar();
			loadwayBillFreightList();
			loadReceiveMoneyDialog();
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
		
		// 加载运单运费列表
		function loadwayBillFreightList(){
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
					title : '开单时间',
					elCls : 'center',
					width : 150,
					dataIndex : 'wayBillOrderTime',
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
					title : '垫付货款',
					elCls : 'center',
					dataIndex : 'advanceCost'
				}, {
					title : '代收货款',
					elCls : 'center',
					dataIndex : 'agencyFund'
				}, {
					title : '支付方式',
					elCls : 'center',
					dataIndex : 'payMethodName'
				}, {
					title : '运费',
					elCls : 'center',
					dataIndex : 'total'
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
					title : '签收人',
					elCls : 'center',
					dataIndex : 'signPerson'
				}, {
					title : '签收时间',
					elCls : 'center',
					width : 150,
					dataIndex : 'signTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '备注',
					elCls : 'center',
					dataIndex : 'remark'
				} ];
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/waybillfreight/getwaybillagencyfundlist.shtml',
					autoLoad : true,
					proxy:{
						method:'post',
					},
					//remoteSort : true,
					pageSize : 10
				});
				grid = new Grid.Grid({
					render : '#wayBillFreightList',
					columns : columns,
					store : store,
					//forceFit : true,
					plugins : [Grid.Plugins.CheckSelection],
					tbar:{
		                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
		                items:[{
		                	btnCls : 'button button-normal',
		                    text:'收款',
		                    handler : showReceiveMoneyDialog
		                } ]
		            }
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#wayBillFreightListBar',
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
				'startOutlets' : $('#startOutlets').val(),
				'targetOutlets' : $('#targetOutlets').val(),
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val(),
				'condition' : $('#condition').val()
			}
			store.load(params);
		}
		
		// 显示 收款 弹出框
		function showReceiveMoneyDialog(){
			var selection = grid.getSelection();
			if(selection.length != 1){
				BUI.Message.Alert('请选择一条记录','warning');
				return;
			}
			var money = 0;
			var wayBillNumbers = '';
			for(var i = 0; i < selection.length; i++){
				var selected = selection[i];
				money += new Number(selected.agencyFund);
				if(i == selection.length - 1){
					wayBillNumbers += selected.wayBillNumber;
				}else{
					wayBillNumbers += selected.wayBillNumber + ',';
				}
			}
			$('#wayBillNumbers').val(wayBillNumbers);
			$('#moneyText').text(money);
			$('#money').val(money);
			receiveMoneyDialog.show();
		}
		
		// 加载 收款 弹出框
		function loadReceiveMoneyDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				receiveMoneyDialog = new Overlay.Dialog({
			    	title:'运费收款订单',
			        width:460,
			        height:360,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'receiveMoneyDialog',
			        success:function () {
			        	if(!receiveMoneyOrderForm.isValid()){
			        		return;
			        	}
			        	/* var money = $('#money').val();
			        	var posMoney = $('#posMoney').val();
			        	var cashMoney = $('#cashMoney').val();
			        	if(money != (new Number(posMoney) + new Number(cashMoney))){
			        		BUI.Message.Alert('金额输入不正确，金额=POS收款+现金','warning');
			        		return;
			        	} */
			        	$.ajax({
			        		type : 'post',
			        		url : '<%=request.getContextPath()%>/tms/waybillfreight/savereceivemoneyorder.shtml',
			        		data : $('#receiveMoneyOrderForm').serialize(),
			        		success : function(result){
			        			if(result == 1){
			        				receiveMoneyDialog.close();
			        				BUI.Message.Alert('操作成功',function(){
			        					search();
			        				},'success');
			        			}else{
			        				BUI.Message.Alert('操作失败','error');
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
	</script>
</body>
</html>