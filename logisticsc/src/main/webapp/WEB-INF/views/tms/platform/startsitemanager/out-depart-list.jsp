<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>外包清单</title>
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
			<label>外包发车单号：</label><span>${outDepartList.outDepartNumber}</span>
		</div>
	</div>
	<div class="row-fluid">
		<form class="form-horizontal" id="outDepartListForm">
			<input type="hidden" name="outDepartNumber" value="${outDepartList.outDepartNumber}">
			<input type="hidden" name="id" value="${outDepartList.id}">
			<div class="panel">
				<div class="panel-header">
					<h3>外包运输协议</h3>
				</div>
				<div class="panel-body">
					<table border="1" width="100%">
						<tr>
							<td colspan="2">
								<div class="info-content" style="margin-left: 15px;">
									<b>外包信息</b>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label"><s>*</s>外包时间</label>
										<div class="controls">
											<input type="text" name="outSourceTime" id="outSourceTime" class="calendar calendar-time" data-rules="{required:true, date:true}" value="${outDepartList.outSourceTimeStr}">
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label"><s>*</s>承运单位</label>
										<div class="controls">
											<input type="text" name="carriageCompany" class="input-normal" data-rules="{required:true}" value="${outDepartList.carriageCompany}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">外包费用合计</label>
										<div class="controls">
											<span class="control-label" id="outSourceCostText" style="width: auto;">${outDepartList.outSourceCost}</span>
											<input type="hidden" name="outSourceCost" id="outSourceCost" value="${outDepartList.outSourceCost}">
										</div>
									</div>
								</div>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label"><s>*</s>发站联系人</label>
										<div class="controls">
											<input type="text" name="startSitePerson" class="input-normal" data-rules="{required:true}" value="${outDepartList.startSitePerson}">
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label"><s>*</s>到站联系人</label>
										<div class="controls">
											<input type="text" name="endSitePerson" class="input-normal" data-rules="{required:true}" value="${outDepartList.endSitePerson}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">现付</label>
										<div class="controls">
											<span class="control-label" id="currentPayText" style="width: auto;">${outDepartList.currentPay}</span>
											<input type="hidden" name="currentPay" id="currentPay" value="${outDepartList.currentPay}">
										</div>
									</div>
								</div>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label"><s>*</s>发站联系电话</label>
										<div class="controls">
											<input type="text" name="startSitePhone" class="input-normal" value="${outDepartList.startSitePhone}" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label"><s>*</s>到站联系电话</label>
										<div class="controls">
											<input type="text" name="endSitePhone" class="input-normal" value="${outDepartList.endSitePhone}" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">回付</label>
										<div class="controls">
											<span class="control-label" id="backPayText" style="width: auto;">${outDepartList.backPay}</span>
											<input type="hidden" name="backPay" id="backPay" value="${outDepartList.backPay}">
										</div>
									</div>
								</div>
								<div class="row-fluid row-margin">
									<div class="control-group span9">
										<label class="control-label"></label>
										<div class="controls">
										</div>
									</div>
									<div class="control-group span9">
										<label class="control-label"><s>*</s>目的地</label>
										<div class="controls">
											<input type="text" name="destination" class="input-normal" data-rules="{required:true}" value="${outDepartList.destination}">
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
						<textarea id="remark" name="remark" style="width: 98%;">${outDepartList.remark}</textarea>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="margin-bottom: 10px;">
				<table class="button-box">
					<tr>
						<td><button type="button" class="button" onclick="save()">保存</button></td>
						<td><button type="button" class="button">外包出库</button></td>
						<td><button type="button" class="button" onclick="go(-1)">取消</button></td>
					</tr>
				</table>
			</div>
		</form>
		
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var store, grid, editing, outDepartListForm;
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [ { title : '出发网点', elCls : 'center', dataIndex : 'startOutletsName' }, 
		                { title : '运单号', elCls : 'center', dataIndex : 'wayBillNumber', width : 130}, 
		                { title : '外包承运单号', elCls : 'center', dataIndex : 'outWayBillNumber', width : 130, editor : {xtype : 'text', rules : {required : true}}},
		                { title : '外包费', elCls : 'center', dataIndex : 'outSourceCost',editor : {xtype : 'number', rules : {required : true}}, renderer : function(value){
		                	countOutSourceCost();
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
						{ title : '货物名称', elCls : 'center', dataIndex : 'cargoName' }, 
						{ title : '件数', elCls : 'center', dataIndex : 'cargoNumber', }, 
						{ title : '套数', elCls : 'center', dataIndex : 'cargoSetNumber', }, 
						{ title : '代收货款', elCls : 'center', dataIndex : 'agencyFund', summary : true }, 
						{ title : '垫付货款', elCls : 'center', dataIndex : 'advanceCost', summary : true }, 
						{ title : '总运费', elCls : 'center', dataIndex : 'total', summary : true }, 
						{ title : '付款方式', elCls : 'center', dataIndex : 'payMethodName' }, 
						{ title : '回单份数', elCls : 'center', dataIndex : 'receiptSlipNum', summary : true },
						{ title : '开单时间', width : 150, elCls : 'center', dataIndex : 'wayBillOrderTime', renderer:BUI.Grid.Format.datetimeRenderer } 
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
			plugins : [editing, Grid.Plugins.Summary, Grid.Plugins.RowNumber,Grid.Plugins.AutoFit],
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
			outDepartListForm = new Form.Form({
				srcNode : '#outDepartListForm',
				//errorTpl : errorTpl
			}).render();
		})
	}
	
	// 保存
	function save(){
		var outDepartListFormValid = outDepartListForm.isValid();
		var editingValid = editing.isValid();
		if(!outDepartListFormValid || !editingValid){
			return;
		}
		if(!validOutSourceCost()){
			return;
		}
		var outDepartList = outDepartListForm.getRecord();
		outDepartList.outSourceTime = $('#outSourceTime').val(); // 设置日期
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/outdepart/saveoutdepartlist.shtml',
			data : {
				'outDepartListJson' : JSON.stringify(outDepartList),
				'wayBillOutSourceRecordJson' : JSON.stringify(grid.getItems())
			},
			success : function(result){
				if(result == 1){
					window.location.href = '<%=request.getContextPath()%>/tms/outdepart/tooutsourcemanagerpage.shtml';
				}else{
					BUI.Message.Alert('保存失败','error');
				}
			}
		});
	}
	
	// 计算外合计包费
	function countOutSourceCost(){
		var items = grid.getItems();
		var outSourceCostTotal = 0;
		for(var i = 0; i < items.length; i++){
			var outSourceCost = items[i].outSourceCost;
			if(outSourceCost != null && outSourceCost != '' && typeof(outSourceCost) != 'undefined'){
				outSourceCostTotal += new Number(outSourceCost);
			}
		}
		$('#outSourceCostText').text(outSourceCostTotal);
		$('#outSourceCost').val(outSourceCostTotal);
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
	
	// 验证 外包费
	function validOutSourceCost(){
		var outSourceCost = $('#outSourceCost').val();
		var currentPay = $('#currentPay').val()
		var backPay = $('#backPay').val();
		if(new Number(outSourceCost) != (new Number(currentPay) + new Number(backPay))){
			BUI.Message.Alert('外包费输入不正确','warning');
			return false;
		}
		return true;
	}
	</script>
</body>
</html>