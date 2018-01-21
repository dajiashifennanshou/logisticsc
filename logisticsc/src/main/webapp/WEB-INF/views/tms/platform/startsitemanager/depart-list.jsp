<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发车清单</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.info-content{
	line-height: 40px;
}
.row-margin{margin-top: 8px;}
.form-horizontal .control-label{width: 80px;}
input[type="text"], input[type="password"], input[type="email"]{width: 120px;}
select{width: 146px;}
.form-show-text{line-height: 30px;}
.form-horizontal .controls{height: 40px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div style="padding: 15px;">
			<label>发车单号：</label><span>${departNumber}</span>
		</div>
	</div>
	<div class="row-fluid">
		<form class="form-horizontal" id="departListForm">
			<input type="hidden" id="departNumber" name="departNumber" value="${departNumber}">
			<div class="panel">
				<div class="panel-header">
					<h3>司机运输协议</h3>
				</div>
				<div class="panel-body">
					<table border="1" width="100%">
						<tr>
							<td colspan="2">
								<div class="info-content" style="margin-left: 15px;">
									<b id="startOutletsLine"></b>
									<span> -- </span>
									<div id="wayOutletsPanel" style="display: inline;"></div>
									<div id="wayOutletsDiv" style="display: inline;">
										<label class="">途径网点</label>
										<select id="wayOutlets" onchange="chooseWayOutlets(this)" data-rules="{required:true}"></select>
									</div>
									<input type="hidden" id="wayOutletsArr" name="wayOutlets">
									<input type="hidden" id="wayOutletsNameArr" name="wayOutletsName">
									<b id="targetOutletsLine"></b>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center" width="80px"><b>发车信息</b></td>
							<td>
								<div class="row-fluid row-margin">
									<div class="control-group span6">
										<label class="control-label">出发网点</label>
										<div class="controls">
											<span class="form-show-text" id="startOutletsText">成都</span>
											<input type="hidden" id="startOutlets" name="startOutlets">
											<input type="hidden" id="startOutletsName" name="startOutletsName">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">到达网点</label>
										<div class="controls">
											<%-- <select id="targetOutlets" name="targetOutlets">
												<c:forEach items="${outletsInfos}" var="outletsInfo">
													<option value="${outletsInfo.id}">${outletsInfo.name}</option>
												</c:forEach>
											</select> --%>
											<span class="form-show-text" id="targetOutletsText"></span>
											<input type="hidden" id="targetOutlets" name="targetOutlets">
											<input type="hidden" id="targetOutletsName" name="targetOutletsName">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label"><s>*</s>发车时间</label>
										<div class="controls">
											<input type="text" name="startTimeStr" class="calendar calendar-time" data-rules="{required:true}">
										</div>
									</div>
									<div class="control-group span6" style = "display:none">
										<label class="control-label">到达时间</label>
										<div class="controls">
											<input type="text" name="endTimeStr" class="calendar calendar-time">
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center"><b>车辆信息</b></td>
							<td>
								<div class="row-fluid row-margin">
									<div class="control-group span6">
										<label class="control-label"><s>*</s>车牌号</label>
										<div class="controls">
											<select id="vehicleNumber" name="vehicleNumber" onchange="choiceVehicle(this.value)" data-rules="{required:true}"></select>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">挂车号</label>
										<div class="controls">
											<span class="form-show-text" id="hangVehicleNumberText"></span>
											<input type="hidden" id="hangVehicleNumber" name="hangVehicleNumber">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">车辆类型</label>
										<div class="controls">
											<span class="form-show-text" id="vehicleTypeText"></span>
											<input type="hidden" id="vehicleType" name="vehicleType">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label"><s>*</s>司机</label>
										<div class="controls">
											<select id="driver" name="driver" onchange="loadDriverInfo(this.value)" data-rules="{required:true}"></select>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">司机姓名</label>
										<div class="controls">
											<span class="form-show-text" id="driverNameText"></span>
											<input type="hidden" id="driverName" name="driverName">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">司机电话</label>
										<div class="controls">
											<span class="form-show-text" id="driverPhoneText"></span>
											<input type="hidden" id="driverPhone" name="driverPhone">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">开户行</label>
										<div class="controls">
											<span class="form-show-text" id="bankNameText"></span>
											<input type="hidden" id="bankName" name="bankName">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">银行卡号</label>
										<div class="controls">
											<span class="form-show-text" id="bankNumberText"></span>
											<input type="hidden" id="bankNumber" name="bankNumber">
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center"><b>司机费用</b></td>
							<td>
								<div class="row-fluid row-margin">
									<div class="control-group span6">
										<label class="control-label"><s>*</s>应付司机</label>
										<div class="controls">
											<input type="text" name="shouldPayDriverCost" id="shouldPayDriverCost" data-rules="{required:true, number:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">现付司机</label>
										<div class="controls">
											<input type="text" name="nowPayDriverCost" id="nowPayDriverCost" data-rules="{number:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">到付司机</label>
										<div class="controls">
											<input type="text" name="arrivePayDriverCost" id="arrivePayDriverCost" data-rules="{number:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">回付司机</label>
										<div class="controls">
											<input type="text" name="backPayDriverCost" id="backPayDriverCost" data-rules="{number:true}">
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span24">
										<span style="margin-left: 30px; color: #aaa;">应付司机运费 = 现付 + 到付 + 回付</span>
									</div>
								</div>
								<!-- <div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">装车费</label>
										<div class="controls">
											<input type="text" id="loadCost" name="loadCost" data-rules="{number:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">卸车费</label>
										<div class="controls">
											<input type="text" id="unloadCost" name="unloadCost" data-rules="{number:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">分流费</label>
										<div class="controls">
											<input type="text" id="shuntCost" name="shuntCost" data-rules="{number:true}">
										</div>
									</div>
								</div> -->
							</td>
						</tr>
						<tr>
							<td align="center"><b>整车货险</b></td>
							<td>
								<div class="row-fluid row-margin">
									<div class="control-group span6">
										<label class="control-label">保险金额</label>
										<div class="controls">
											<input type="text" name="insuranceMoney" data-rules="{number:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">保险费</label>
										<div class="controls">
											<input type="text" name="insuranceCost" data-rules="{number:true}">
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">是否年保</label>
										<div class="controls">
											<select id="isYearInsurance" name="isYearInsurance">
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
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
						<td><button type="button" class="button" onclick="save()">保存</button></td>
						<td><button type="button" class="button" onclick="startOutStore()">发车出库</button></td>
						<td><button type="button" class="button" onclick="history.go(-1)">取消</button></td>
					</tr>
				</table>
			</div>
		</form>
		
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var store, departListForm;
	
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [ {
			title : '开单时间',
			width : 150,
			elCls : 'center',
			dataIndex : 'wayBillOrderTime',
			renderer:BUI.Grid.Format.datetimeRenderer
		}, {
			title : '运单号',
			width : 130,
			elCls : 'center',
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
			dataIndex : 'agencyFund',
			summary : true
		}, {
			title : '垫付货款',
			elCls : 'center',
			dataIndex : 'advanceCost',
			summary : true
		}, {
			title : '总运费',
			elCls : 'center',
			dataIndex : 'total',
			summary : true
		}, {
			title : '付款方式',
			elCls : 'center',
			dataIndex : 'payMethodName'
		}, {
			title : '回单份数',
			elCls : 'center',
			dataIndex : 'receiptSlipNum',
			summary : true
		}, {
			title : '操作员',
			elCls : 'center',
			dataIndex : 'operatePersonName'
		} ];
		
		store = new Store({
			data : <%=request.getParameter("wayBillData")%>,
			autoLoad : true
		});
		var grid = new Grid.Grid({
			render : '#wayBillOrderList',
			columns : columns,
			store : store,
			plugins : [Grid.Plugins.Summary, Grid.Plugins.RowNumber,Grid.Plugins.AutoFit]
			//forceFit : true,
			/* tbar:{
                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
                items:[{
                	xclass : 'bar-item-text',
                    text:'运单号'
                }, {
                	content : '<input name="name" id="id"/>'
                }, {
                	xclass:'bar-item-button',
                    btnCls : 'button button-small',
                    text:'搜索'
                }]
            } */
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
		loadOutletsInfo();
		loadWayBillNumbers();
		loadWayOutlets();
		loadForm();
		var departNumber = $('#departNumber').val();
		if(departNumber == null || departNumber == '' || typeof(departNumber) == 'undefined'){
			loadVehicleInfo('','');
			loadVehicleType('');
			loadDriverData('');
		}else{
			loadDepartListInfo();
		}
	});
	
	function loadForm(){
		var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
		BUI.use('bui/form',function(Form){
			departListForm = new Form.Form({
				srcNode : '#departListForm',
				errorTpl : errorTpl
			}).render();
		})
	}
	
	// 加载 发车单信息
	function loadDepartListInfo(){
		var departNumber = $('#departNumber').val();
		if(departNumber == null || departNumber == '' || typeof(departNumber) == 'undefined'){
			return;
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/depart/getdepartlistinfo.shtml',
			data : { 'departNumber' : departNumber },
			success : function(result){
				$('input[name="startTimeStr"]').val(formatDate(result.startTime));
				$('input[name="endTimeStr"]').val(formatDate(result.endTime));
				loadVehicleInfo(result.vehicleNumber,result.hangVehicleNumber);
				loadVehicleType(result.vehicleType);
				loadDriverData(result.driver);
				
				$('input[name="shouldPayDriverCost"]').val(result.shouldPayDriverCost);
				$('input[name="nowPayDriverCost"]').val(result.nowPayDriverCost);
				$('input[name="arrivePayDriverCost"]').val(result.arrivePayDriverCost);
				$('input[name="backPayDriverCost"]').val(result.backPayDriverCost);
				
				/* $('input[name="loadCost"]').val(result.loadCost);
				$('input[name="unloadCost"]').val(result.unloadCost);
				$('input[name="shuntCost"]').val(result.shuntCost); */
				
				$('input[name="insuranceMoney"]').val(result.insuranceMoney);
				$('input[name="insuranceCost"]').val(result.insuranceCost);
				$('#isYearInsurance').val(result.isYearInsurance);
				
				$('#remark').val(result.remark);
				
				//displayWayOutletsInfo(result.wayOutlets, result.wayOutletsName);
			}
		});
	}
	
	// 选择 车辆
	function choiceVehicle(vehicleNumber){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/vehicle/getvehicleinfobyvehiclenumber.shtml',
			data : {'vehicleNumber' : vehicleNumber},
			success : function(result){
				if(result == null || result == ''){
					$('#hangVehicleNumber').val('');
					$('#hangVehicleNumberText').text('');
					$('#vehicleType').val('');
					$('#vehicleTypeText').text('');
				}else{
					$('#hangVehicleNumber').val(result.trailerNumber);
					$('#hangVehicleNumberText').text(result.trailerNumber);
					$('#vehicleType').val(result.vehicleType);
					$('#vehicleTypeText').text(result.vehicleTypeVal);
				}
			}
		});
	}
	
	// 回显途经网点信息
	/* function displayWayOutletsInfo(wayOutlets, wayOutletsName){
		var wayOutletsArr = wayOutlets.split(',');
		var wayOutletsNameArr = wayOutletsName.split(',');
		for(var i = 0; i < wayOutletsArr.length; i++){
			setWayOutletsInfo(wayOutletsArr[i], wayOutletsNameArr[i]);
		}
	} */
	
	// 加载运单号
	function loadWayBillNumbers(){
		var wayBills = <%=request.getParameter("wayBillData")%>;
		var wayBillNumbers = '';
		for(var i = 0; i < wayBills.length; i++){
			if(i == wayBills.length - 1){
				wayBillNumbers += wayBills[i].wayBillNumber;
			}else{
				wayBillNumbers += wayBills[i].wayBillNumber + ',';
			}
		}
		$('#wayBillNumbers').val(wayBillNumbers);
	}
	
	// 加载网点信息
	function loadOutletsInfo(){
		var wayBill = <%=request.getParameter("wayBillData")%>[0];
		$('#startOutletsText').text(wayBill.startOutletsName);
		$('#startOutlets').val(wayBill.startOutlets);
		$('#startOutletsName').val(wayBill.startOutletsName);
		$('#startOutletsLine').text(wayBill.startOutletsName);
		
		/* $('#targetOutlets').val(wayBill.targetOutlets);
		$('#targetOutletsName').val(wayBill.targetOutletsName);
		
		$('#targetOutletsLine').text(wayBill.targetOutletsName); */
	}
	
	// 加载车辆信息
	function loadVehicleInfo(vehicleNumber, hangVehicleNumber){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/vehicle/getvehicleinfolist.shtml',
			success : function(result){
				var html = '<option value="">请选择</option>'
				for(var i = 0; i < result.length; i++){
					html += '<option value="'+result[i].plateNumber+'">'+result[i].plateNumber+'</option>';
				}
				$('#vehicleNumber').html(html);
				//$('#hangVehicleNumber').html(html);
				if(vehicleNumber != ''){
					$('#vehicleNumber').val(vehicleNumber);
				}
				if(hangVehicleNumber != ''){
					$('#hangVehicleNumber').val(hangVehicleNumber);
				}
			}
		});
	}
	
	// 加载车辆类型
	function loadVehicleType(vehicleType){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/depart/getvehicletype.shtml',
			success : function(result){
				var html = '<option value="">请选择</option>'
				for(var i = 0; i < result.length; i++){
					html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$('#vehicleType').html(html);
				if(vehicleType != ''){
					$('#vehicleType').val(vehicleType);
				}
			}
		});
	}
	
	// 加载司机信息
	function loadDriverData(driver){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/driver/getdriverlist.shtml',
			success : function(result){
				var html = '<option value="">请选择</option>'
				for(var i = 0; i < result.length; i++){
					html += '<option value="'+result[i].id+'">'+result[i].driverName+'</option>';
				}
				$('#driver').html(html);
				if(driver != ''){
					$('#driver').val(driver);
					loadDriverInfo(driver);
				}
			}
		});
	}
	
	// 加载司机信息
	function loadDriverInfo(id){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/driver/getdriverinfo.shtml',
			data : { 'id' : id },
			success : function(result){
				if(result == null || result == ''){
					$('#driverName').val('');
					$('#driverNameText').text('');
					$('#driverPhone').val('');
					$('#driverPhoneText').text('');
					$('#bankName').val('');
					$('#bankNameText').text('');
					$('#bankNumber').val('');
					$('#bankNumberText').text('');
				}else{
					$('#driverName').val(result.driverName);
					$('#driverNameText').text(result.driverName);
					$('#driverPhone').val(result.phoneNumber);
					$('#driverPhoneText').text(result.phoneNumber);
					$('#bankName').val(result.bankName);
					$('#bankNameText').text(result.bankName);
					$('#bankNumber').val(result.bankNumber);
					$('#bankNumberText').text(result.bankNumber);
				}
			}
		});
	}
	
	// 加载 途经网点 下拉列表
	function loadWayOutlets(){
		var outletsId = null;
		var html = '<option value="">请选择</option>';
		var wayBillData = ${wayBillData};
		for(var i = 0; i < wayBillData.length; i++){
			if(outletsId == null){
				outletsId = wayBillData[i].targetOutlets;
				html += '<option value="'+wayBillData[i].targetOutlets+'">'+wayBillData[i].targetOutletsName+'</option>';
			}else{
				if(outletsId != wayBillData[i].targetOutlets){
					html += '<option value="'+wayBillData[i].targetOutlets+'">'+wayBillData[i].targetOutletsName+'</option>';
				}
			}
		}
		$('#wayOutlets').html(html);
		
		var wayOutletsObj = document.getElementById('wayOutlets');
		if(wayOutletsObj.length == 2){
			$('#wayOutletsDiv').hide();
			$('#wayOutlets').val(wayOutletsObj[1].value);
			$('#targetOutletsLine').text(wayOutletsObj[1].innerHTML);
			
			$('#targetOutletsText').text(wayOutletsObj[1].innerHTML);
			$('#targetOutlets').val(wayOutletsObj[1].value);
			$('#targetOutletsName').val(wayOutletsObj[1].innerHTML);
		}
	}
	
	// 选择途径网点
	function chooseWayOutlets(obj){
		
		var id = obj.value;
		var text = obj.options[obj.options.selectedIndex].text;
		
		if(id == null || id == ''){
			return;
		}
		setWayOutletsInfo(id, text);
	}
	
	// 设置途经网点信息
	function setWayOutletsInfo(id, text){
		
		$('#wayOutletsPanel').append('<b>'+text+'</b><span> -- </span>');
		var wayOutletsArr = $('#wayOutletsArr').val();
		if(wayOutletsArr == null || wayOutletsArr == '' || typeof(wayOutletsArr) == 'undefined'){
			$('#wayOutletsArr').val(id);
		}else{
			$('#wayOutletsArr').val(wayOutletsArr + ',' + id);
		}
		var wayOutletsNameArr = $('#wayOutletsNameArr').val();
		if(wayOutletsNameArr == null || wayOutletsNameArr == '' || typeof(wayOutletsNameArr) == 'undefined'){
			$('#wayOutletsNameArr').val(text);
		}else{
			$('#wayOutletsNameArr').val(wayOutletsNameArr + ',' + text);
		}
		$("#wayOutlets option[value='"+id+"']").remove();
		
		// 判断 下拉框 是否是最后一个值
		var wayOutletsObj = document.getElementById('wayOutlets');
		if(wayOutletsObj.length == 2){
			$('#wayOutletsDiv').hide();
			$('#wayOutlets').val(wayOutletsObj[1].value);
			$('#targetOutletsLine').text(wayOutletsObj[1].innerHTML);
			
			$('#targetOutletsText').text(wayOutletsObj[1].innerHTML);
			$('#targetOutlets').val(wayOutletsObj[1].value);
			$('#targetOutletsName').val(wayOutletsObj[1].innerHTML);
		}
	}
	
	// 保存
	function save(){
		if(!departListForm.isValid()){
			return;
		}
		if(!validDriverCost()){
			return;
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/depart/save.shtml',
			data : $('#departListForm').serialize(),
			success : function(result){
				if(result == 1){
					BUI.Message.Alert('保存成功',function(){
						window.location.href = '<%=request.getContextPath()%>/tms/depart/todepartlistmanagerpage.shtml';
					},'success');
				}else{
					BUI.Message.Alert('保存失败','error');
				}
			}
		});
	}
	
	// 验证司机运费
	function validDriverCost(){
		var shouldPayDriverCost = new Number($('#shouldPayDriverCost').val());
		var nowPayDriverCost = new Number($('#nowPayDriverCost').val());
		var arrivePayDriverCost = new Number($('#arrivePayDriverCost').val());
		var backPayDriverCost = new Number($('#backPayDriverCost').val());
		/* var loadCost = new Number($('#loadCost').val());
		var unloadCost = new Number($('#unloadCost').val());
		var shuntCost = new Number($('#shuntCost').val()); */
		if(shouldPayDriverCost != (nowPayDriverCost + arrivePayDriverCost + backPayDriverCost)){
			BUI.Message.Alert('司机费用填写不正确','warning');
			return false;
		}
		return true;
	}
	
	// 发车出库
	function startOutStore(){
		if(!departListForm.isValid()){
			return;
		}
		if(!validDriverCost()){
			return;
		}
		BUI.Message.Confirm('确定要直接发车出库吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/depart/savedeparture.shtml',
				data : $('#departListForm').serialize(),
				success : function(result){
					if(result == 1){
						window.location.href = '<%=request.getContextPath()%>/tms/depart/todepartlistmanagerpage.shtml';
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
		
	}
	
	// 发车出库成功，提示框
	function showDepartureSuccessMessage(){
		BUI.use('bui/overlay',function(Overlay){
			BUI.Message.Show({
				msg : '发车出库成功，请确定是否购买保险',
				icon : 'question',
				buttons : [{
					text : '是',
					elCls : 'button button-primary',
					handler : function(){
						this.close();
						window.location.href = '<%=request.getContextPath()%>/tms/depart/todepartlistmanagerpage.shtml';
						window.open('<%=request.getContextPath()%>/insurance/toInsurance.shtml');
					}
				}, {
					text : '否',
					elCls : 'button',
					handler : function(){
						this.close();
						window.location.href = '<%=request.getContextPath()%>/tms/depart/todepartlistmanagerpage.shtml';
					}
				}]
			});
		});
	}
	</script>
</body>
</html>