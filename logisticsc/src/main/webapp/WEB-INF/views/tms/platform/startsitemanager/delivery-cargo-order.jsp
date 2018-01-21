<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提货单</title>
<%-- <link href="${configProps['resources']}/tms/base/css/base.css" rel="stylesheet"> --%>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-table .bui-grid-cell-inner{padding: 5px 0px;}
.bui-grid-table .bui-grid-hd-inner{padding: 6px 0px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;}
.form-horizontal input{width: 120px;}
.form-horizontal select{width: 146px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
</style>
</head>
<body>
	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
	<input type="hidden" id="userId" value="${tms_user_session.id}">
	<form class="form-horizontal" id="form_number_info">
		<div class="row-fluid" style="margin-top: 10px;">
			<div class="control-group span8">
				<label class="control-label">货运交易系统订单</label>
				<div class="controls">
					<input type="text" id="orderNumber" name="orderNumber" class="input-normal" disabled="disabled" value="${orderNumber}">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">运单号</label>
				<div class="controls">
					<input type="text" id="wayBillNumber" name="wayBillNumber" class="input-normal" disabled="disabled">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">派车时间</label>
				<div class="controls">
					<input type="text" id="ladingOrderTime" name="ladingOrderTime" class="calendar calendar-time" data-rules="{date:true}" style="width: 120px;">
				</div>
			</div>
		</div>
	</form>
	<div class="panel">
		<div class="panel-header">
			<h3>车辆信息</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" id="form_driver_info">
				<div class="row-fluid">
					<div class="control-group span8">
						<label class="control-label"><s>*</s>司机姓名</label>
						<div class="controls">
							<select class="input-normal" style="width: 166px;" onchange="choiceDriver(this.value)" data-rules="{required:true}">
								<option value="">选择司机</option>
								<c:forEach items="${driverInfos}" var="driverInfo">
									<option value="${driverInfo.id}">${driverInfo.driverName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="driverName" name="driverName" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label">司机电话</label>
						<div class="controls">
							<input type="text" id="driverMobile" name="driverMobile" class="input-normal" readonly="readonly">
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label"><s>*</s>车牌号</label>
						<div class="controls">
							<select id="licensePlateNo" name="licensePlateNo" data-rules="{required:true}"></select>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>基本信息</h3>
		</div>
		<div class="panel-body" id="ladingOrderBaseInfo">
			<form class="form-horizontal" id="form_base_info">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">出发网点</label>
						<div class="controls">
							<span class="control-label" id="startOutletsText" style="text-align: left;"></span>
							<input type="hidden" id="startOutlets" name="startOutlets">
							<input type="hidden" id="startOutletsName" name="startOutletsName">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>到达网点</label>
						<div class="controls">
							<select id="targetOutlets" name="targetOutlets" data-rules="{required:true}" data-messages="{required:' '}" onchange="setOutletsName(this, 'targetOutletsName')"></select>
							<input type="hidden" id="targetOutletsName" name="targetOutletsName">
						</div>
					</div>
					<div class="control-group span12">
						<label class="control-label"><s>*</s>目的地</label>
						<div class="controls">
							<select id="targetProvince" name="targetProvince" onchange="loadXzqhInfo(this.value, 2)" data-rules="{required : true}" data-messages="{required:' '}" style="width: 120px;"></select>
							<select id="targetCity" name="targetCity" onchange="loadXzqhInfo(this.value, 3)" data-rules="{required : true}" data-messages="{required:' '}" style="width: 120px;"></select>
							<select id="targetCounty" name="targetCounty" data-rules="{required : true}" data-messages="{required:' '}" style="width: 120px;"></select>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label"><s>*</s>托运人</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignor" name="consignor" data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>托运电话</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignorMobile" name="consignorMobile" data-rules="{required:true,regexp:/^\d{11}$/}" data-messages="{required:' ', regexp:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>托运地址</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignorAddress" name="consignorAddress" data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运单位</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignorCompany" name="consignorCompany">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label"><s>*</s>收货人</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignee" name="consignee" data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>收货电话</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consigneeMobile" name="consigneeMobile" data-rules="{required:true,regexp:/^\d{11}$/}" data-messages="{required:' ', regexp:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>收货地址</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consigneeAddress" name="consigneeAddress" data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货单位</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consigneeCompany" name="consigneeCompany">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">收货方式</label>
						<div class="controls">
							<select id="receiveType" name="receiveType" class="input-normal">
								<option value="0">客户自提</option>
								<option value="1">送货上门</option>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">回单/收条</label>
						<div class="controls">
							<select id="receiptSlip" name="receiptSlip" class="input-normal">
								<option value="0">无</option>
								<option value="1">回单/收条</option>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">份数</label>
						<div class="controls">
							<input type="text" id="receiptSlipNum" name="receiptSlipNum" class="input-normal" data-rules="{validInt:''}">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">城配司机</label>
						<div class="controls">
							<input type="text" class="input-normal" id="cityDriverName" name="cityDriverName">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">司机电话</label>
						<div class="controls">
							<input type="text" class="input-normal" id="cityDriverPhone" name="cityDriverPhone" data-rules="{mobile:''}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">车牌号</label>
						<div class="controls">
							<input type="text" class="input-normal" id="cityVehicleNumber" name="cityVehicleNumber" data-rules="{validVechileNumber:true}">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>货物信息</h3>
		</div>
		<div class="panel-body">
			<div id="editCargoGrid"></div>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>费用信息</h3>
		</div>
		<div class="panel-body" id="ladingOrderCostInfo">
			<form class="form-horizontal" id="form_cost_info">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">代收货款</label>
						<div class="controls">
							<input type="text" class="input-normal" id="agencyFund" name="agencyFund" onchange="countAgencyFundPoundage(this.value)" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手续费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="agencyFundPoundage" name="agencyFundPoundage" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">投保金额</label>
						<div class="controls">
							<input type="text" class="input-normal" id="insuranceMoney" name="insuranceMoney"  onchange="countInsurance(this.value)" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="insurance" name="insurance" disabled="disabled">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">提货费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="takeCargoCost" name="takeCargoCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">装卸费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="loadUnloadCost" name="loadUnloadCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">中转费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="transferCost" name="transferCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">其他费用</label>
						<div class="controls">
							<input type="text" class="input-normal" id="otherCost" name="otherCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<div id="customCostDiv"></div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">基础运费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="freight" name="freight" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">支付方式</label>
						<div class="controls">
							<select class="input-normal" id="payMethod" name="payMethod" onchange="changePayMethod(this.value)">
								<c:forEach items="${payMethods}" var="payMethod">
									<option value="${payMethod.id}">${payMethod.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">合计</label>
						<div class="controls">
							<input type="text" class="input-normal" id="total" name="total" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">等款放货</label>
						<div class="controls">
							<input type="checkbox" id="isWaitPay" name="isWaitPay" value="1" style="height: 30px; width: 15px;">
						</div>
					</div>
				</div>
				<div class="row-fluid" id="advancePaymentDiv" style="display: none;">
					<div class="control-group span6">
						<label class="control-label">垫付货款</label>
						<div class="controls">
							<input type="text" class="input-normal" id="advanceCost" name="advanceCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<div class="row-fluid" id="multi-pay" style="display: none;">
					<div class="control-group span6">
						<label class="control-label">现付</label>
						<div class="controls">
							<input type="text" class="input-normal" id="immediatePay" name="immediatePay" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">到付</label>
						<div class="controls">
							<input type="text" class="input-normal" id="arrivePay" name="arrivePay" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">回付</label>
						<div class="controls">
							<input type="text" class="input-normal" id="backPay" name="backPay" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<!-- 
				
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label" id="freightName">基础运费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="freight" name="freight" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label" id="agencyFundName">代收货款</label>
						<div class="controls">
							<input type="text" class="input-normal" id="agencyFund" name="agencyFund" onchange="countAgencyFundPoundage(this.value)" data-rules="{number:true}" data-messages="{number:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label" id="agencyFundPoundageName">手续费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="agencyFundPoundage" name="agencyFundPoundage" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label" id="loadUnloadedCostName">装卸费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="loadUnloadCost" name="loadUnloadCost" data-rules="{number:true}" data-messages="{number:' '}">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label" id="insuranceMoneyName">投保金额</label>
						<div class="controls">
							<input type="text" class="input-normal" id="insuranceMoney" name="insuranceMoney" onchange="countInsurance(this.value)" data-rules="{number:true}" data-messages="{number:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label" id="insuranceName">保险费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="insurance" name="insurance" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label" id="otherCostName">其他费用</label>
						<div class="controls">
							<input type="text" class="input-normal" id="otherCost" name="otherCost" data-rules="{number:true}" data-messages="{number:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label" id="takeCargoCostName">提货费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="takeCargoCost" name="takeCargoCost" data-rules="{number:true}" data-messages="{number:' '}">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label" id="transferCostName">中转费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="transferCost" name="transferCost" data-rules="{number:true}" data-messages="{number:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label" id="advanceCostName">垫付费用</label>
						<div class="controls">
							<input type="text" class="input-normal" id="advanceCost" name="advanceCost" data-rules="{number:true}" data-messages="{number:' '}">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">支付方式</label>
						<div class="controls">
							<select class="input-normal" id="payMethod" name="payMethod" onchange="setTotalMoneyPrefix(this)"></select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><span id="totalMoneyPrefix"></span>合计</label>
						<div class="controls">
							<input type="text" class="input-normal" id="total" name="total" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">等款发货</label>
						<div class="controls">
							<input type="checkbox" id="isWaitMoney" name="isWaitMoney" value="1" style="height: 30px; width: 15px;">
						</div>
					</div>
				</div> -->
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>备注信息</h3>
		</div>
		<div class="panel-body">
			<textarea id="remark" style="width: 98%;"></textarea>
		</div>
	</div>
	<div class="row-fluid" style="margin-bottom: 10px;">
		<table class="button-box">
			<tr>
				<td><button class="button" onclick="submitDeliveryBill()">提交</button></td>
				<td><button class="button" onclick="history.go(-1)">取消</button></td>
			</tr>
		</table>
	</div>
	<div id="customerDiv" style="width: 600px; padding: 2px;background-color: blue; position: absolute; top: 322px;left: 105px;display: none;">
		<div id="customerList"></div>
	</div>
	<!-- 增值服务配置 数据 -->
	<input type="hidden" id="collectionDeliveryRatio" value="${additionalServer.collectionDeliveryRatio}">
	<input type="hidden" id="lineInsuranceRatio" value="${additionalServer.lineInsuranceRatio}">
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tms/startsitemanager/delivery-way-bill-base.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tms/startsitemanager/delivery-cargo.js"></script>
	
	<script type="text/javascript">
	var customerFlag = 0;
	$(function(){
		$('#consignor').click(function(e){
			if($('#customerDiv').css('display') == 'none'){
				$('#customerDiv').css('top','322px');
				$('#customerDiv').toggle();
		        e.stopPropagation();
		        $('#customerList').html('');
		        loadCustomerInfoList();
		        customerFlag = 1;
			}
		});
		$('#consignee').click(function(e){
			if($('#customerDiv').css('display') == 'none'){
				$('#customerDiv').css('top','362px');
				$('#customerDiv').toggle();
		        e.stopPropagation();
		        $('#customerList').html('');
		        loadCustomerInfoList();
		        customerFlag = 2;
			}
		});
		$(document).click(function() {
	        $("#customerDiv").hide();
	    });
		$('#consignor, #consignee, #customerDiv').click(function(e){
	        e.stopPropagation();
		});
	});
	
	// 加载客户信息列表
	function loadCustomerInfoList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ 
				{title : '姓名',elCls:'center', dataIndex : 'customerName', elCls : 'center'},
				{title : '手机号',elCls:'center', dataIndex : 'phone', elCls : 'center'},
				{title : '详细地址',elCls:'center',dataIndex :'address', elCls : 'center'},
				{title : '公司名称',elCls:'center', dataIndex : 'companyName', elCls : 'center'}
			];
			customerStore = new Store({
				url: '<%=request.getContextPath()%>/tms/customer/search.shtml',
				autoLoad : true,
				//remoteSort : true,
				proxy:{
					method:'post',
				},
				pageSize : 10
			});
			customerGrid = new Grid.Grid({
				render : '#customerList',
				columns : columns,
				store : customerStore,
				forceFit : true,
				bbar:{
	                pagingBar:true
	            },
	            listeners : {
	            	itemclick : function(e){
	            		if(customerFlag == 1){
	            			$('#consignor').val(e.item.customerName);
		            		$('#consignorMobile').val(e.item.phone);
		            		$('#consignorAddress').val(e.item.address);
		            		$('#consignorCompany').val(e.item.companyName);
	            		}else if(customerFlag == 2){
	            			$('#consignee').val(e.item.customerName);
		            		$('#consigneeMobile').val(e.item.phone);
		            		$('#consigneeAddress').val(e.item.address);
		            		$('#consigneeCompany').val(e.item.companyName);
	            		}
	            		baseInfoForm.valid();
	            	}
	            }
			});
			customerGrid.render();
		});
	}
	
	function loadCustomerList(value){
		customerStore.load({'condition':value});
	}
	
	</script>
	
</body>
</html>