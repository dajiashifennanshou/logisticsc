<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中转清单</title>
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
			<label>中转发车单号：</label>
		</div>
	</div>
	<div class="row-fluid">
		<form class="form-horizontal" id="transferDepartListForm">
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
										<label class="control-label"><s>*</s>开单时间</label>
										<div class="controls">
											<input type="text" name="transferTime" id="transferTime" class="calendar calendar-time" data-rules="{required:true, date:true}">
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label"><s>*</s>承运单位</label>
										<div class="controls">
											<input type="text" name="carriageCompany" class="input-normal" data-rules="{required:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">中转费用合计</label>
										<div class="controls">
											<span class="control-label" id="transferCostText" style="width: auto;"></span>
											<input type="hidden" name="transferCost" id="transferCost">
										</div>
									</div>
								</div>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label"><s>*</s>发站联系人</label>
										<div class="controls">
											<input type="text" name="startSitePerson" class="input-normal" data-rules="{required:true}" >
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label"><s>*</s>到站联系人</label>
										<div class="controls">
											<input type="text" name="endSitePerson" class="input-normal" data-rules="{required:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">现付</label>
										<div class="controls">
											<span class="control-label" id="currentPayText" style="width: auto;"></span>
											<input type="hidden" name="currentPay" id="currentPay" >
										</div>
									</div>
								</div>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label"><s>*</s>发站联系电话</label>
										<div class="controls">
											<input type="text" name="startSitePhone" class="input-normal" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label"><s>*</s>到站联系电话</label>
										<div class="controls">
											<input type="text" name="endSitePhone" class="input-normal" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">回付</label>
										<div class="controls">
											<span class="control-label" id="backPayText" style="width: auto;"></span>
											<input type="hidden" name="backPay" id="backPay">
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
						<textarea id="remark" name="remark" style="width: 98%;"></textarea>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="margin-bottom: 10px;">
				<table class="button-box">
					<tr>
						<td><button type="button" class="button" onclick="save()">确定</button></td>
						<td><button type="button" class="button">取消</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var store, grid, editing, transferDepartListForm;
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [ { title : '出发网点', elCls : 'center', dataIndex : 'startOutletsName' }, 
		                { title : '运单号', elCls : 'center', dataIndex : 'wayBillNumber', width : 130}, 
		                { title : '中转承运单号', elCls : 'center', dataIndex : 'transferWayBillNumber', width : 130, editor : {xtype : 'text', rules : {required : true}}},
		                { title : '中转费', elCls : 'center', dataIndex : 'transferCost',editor : {xtype : 'number', rules : {required : true}}, renderer : function(value){
		                	countTransferCost();
		                	return value;
		                }},
		                { title : '现付', elCls : 'center', dataIndex : 'currentPay',editor : {xtype : 'number'}, renderer : function(value){
		                	countCurrentPayTotal();
		                	return value;
		                }},
		                { title : '回付', elCls : 'center', dataIndex : 'outBackPay',editor : {xtype : 'number'}, renderer : function(value){
		                	countBackPayTotal();
		                	return value;
		                }},
						{ title : '到达网点', elCls : 'center', dataIndex : 'targetOutletsName' }, 
						{ title : '发货人', elCls : 'center', dataIndex : 'consignor' }, 
						{ title : '发货电话', elCls : 'center', dataIndex : 'consignorMobile' }, 
						{ title : '收货人', elCls : 'center', dataIndex : 'consignee' }, 
						{ title : '收货电话', elCls : 'center', dataIndex : 'consigneeMobile' }, 
						{ title : '下单时间', width : 150, elCls : 'center', dataIndex : 'wayBillOrderTime', renderer:BUI.Grid.Format.datetimeRenderer } 
					];
		store = new Store({
			data : <%=request.getParameter("wayBillData")%>,
			autoLoad : true
		});
		editing = new Grid.Plugins.CellEditing({
      		triggerSelected : false //触发编辑的时候不选中行
    	});
		grid = new Grid.Grid({
			render : '#wayBillOrderList',
			columns : columns,
			store : store,
			//forceFit : true,
			plugins : [editing],
		});
		grid.render();
	});
	
	BUI.use('bui/calendar',function(Calendar){
    	var datepicker = new Calendar.DatePicker({
        	trigger:'.calendar',
        	autoRender : true
        });
	});
	
	$(function(){
		loadForm();
	});
	
	function loadForm(){
		//var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
		BUI.use('bui/form',function(Form){
			transferDepartListForm = new Form.Form({
				srcNode : '#transferDepartListForm',
				//errorTpl : errorTpl
			}).render();
		})
	}
	
	// 保存
	function save(){
		var transferDepartListFormValid = transferDepartListForm.isValid();
		var editingValid = editing.isValid();
		if(!transferDepartListFormValid || !editingValid){
			return;
		}
		if(!validTransferCost()){
			return;
		}
		var transferDepartList = transferDepartListForm.getRecord();
		transferDepartList.transferTime = $('#transferTime').val(); // 设置日期
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/transfer/savetransferlist.shtml',
			data : {
				'transferDepartListJson' : JSON.stringify(transferDepartList),
				'wayBillTransferRecordJson' : JSON.stringify(grid.getItems())
			},
			success : function(result){
				if(result == 1){
					BUI.Message.Alert('操作成功',function(){
						window.location.href = '<%=request.getContextPath()%>/tms/transfer/totransferlistmanager.shtml';
					},'success');
				}else{
					BUI.Message.Alert('操作失败','error');
				}
			}
		});
	}
	
	// 计算中转费 合计
	function countTransferCost(){
		var items = grid.getItems();
		var transferCostTotal = 0;
		for(var i = 0; i < items.length; i++){
			var transferCost = items[i].transferCost;
			if(transferCost != null && transferCost != '' && typeof(transferCost) != 'undefined'){
				transferCostTotal += new Number(transferCost);
			}
		}
		$('#transferCostText').text(transferCostTotal);
		$('#transferCost').val(transferCostTotal);
	}
	
	// 计算现付合计费用
	function countCurrentPayTotal(){
		var items = grid.getItems();
		var currentPayTotal = 0;
		for(var i = 0; i < items.length; i++){
			var currentPay = items[i].currentPay;
			if(currentPay != null && currentPay != '' && typeof(currentPay) != 'undefined'){
				currentPayTotal += new Number(currentPay);
			}
		}
		$('#currentPayText').text(currentPayTotal);
		$('#currentPay').val(currentPayTotal);
	}
	
	// 计算回付合计费用
	function countBackPayTotal(){
		var items = grid.getItems();
		var backPayTotal = 0;
		for(var i = 0; i < items.length; i++){
			var backPay = items[i].outBackPay;
			if(backPay != null && backPay != '' && typeof(backPay) != 'undefined'){
				backPayTotal += new Number(backPay);
			}
		}
		$('#backPayText').text(backPayTotal);
		$('#backPay').val(backPayTotal);
	}
	
	// 验证 中转费
	function validTransferCost(){
		var transferCost = $('#transferCost').val();
		var currentPay = $('#currentPay').val()
		var backPay = $('#backPay').val();
		if(new Number(transferCost) != (new Number(currentPay) + new Number(backPay))){
			BUI.Message.Alert('中转费输入不正确','warning');
			return false;
		}
		return true;
	}
	</script>
</body>
</html>