<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开单入库</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-table .bui-grid-cell-inner{padding: 5px 0px;}
.bui-grid-table .bui-grid-hd-inner{padding: 6px 0px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;}
.form-horizontal input{width: 120px;}
.form-horizontal select{width: 146px;}
.form-horizontal .select-group{width: 120px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
.form-show-text{line-height: 30px; font-weight: bold;}
</style>
<script type="text/javascript">
var userType = ${tms_user_session.userType};
if(userType == 0 || userType == 1){
	alert('请使用网点账号登录');
	history.go(-1);
}
</script>
</head>
<body>
	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
	<input type="hidden" id="userId" value="${tms_user_session.id}">
	<input type="hidden" id="token" value="${token}">
	<div id= "printBox">
	<form class="form-horizontal" id="form_number_info">
		<input type="hidden" id="wayBillOrderId" value="${wayBillOrderId}">
		<div class="row-fluid" style="margin-top: 10px;">
			<div class="control-group span8">
				<label class="control-label">货运交易系统订单</label>
				<div class="controls">
					<input type="text" id="orderNumber" class="input-normal" value="${orderNumber}" disabled="disabled">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">运单号</label>
				<div class="controls">
					<input type="text" id="wayBillNumber" class="input-normal" value="${wayBillNumber}" disabled="disabled">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">开单时间</label>
				<div class="controls">
					<input type="text" id="wayBillOrderTime" class="calendar calendar-time" style="width: 120px;">
				</div>
			</div>
		</div>
	</form>
	<div class="panel">
		<div class="panel-header">
			<h3>基本信息</h3>
		</div>
		<div class="panel-body" id="wayBillBaseInfo">
			<form class="form-horizontal" id="form_base_info">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">出发网点</label>
						<div class="controls">
							<span class="form-show-text" id="startOutletsText"></span>
							<input type="hidden" id="startOutlets">
							<input type="hidden" id="startOutletsName">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>到达网点</label>
						<div class="controls">
							<select id="targetOutlets" data-rules="{required:true}" data-messages="{required:' '}" onchange="setOutletsName(this, 'targetOutletsName')"></select>
						</div>
					</div>
					<div class="control-group span12">
						<label class="control-label"><s>*</s>目的地</label>
						<div class="controls">
							<select class="select-group" id="targetProvince" onchange="loadXzqhInfo(this.value, 2)" data-rules="{required : true}" data-messages="{required:' '}" style="width: 120px;"></select>
							<select class="select-group" id="targetCity" onchange="loadXzqhInfo(this.value, 3)" data-rules="{required : true}" data-messages="{required:' '}" style="width: 120px;"></select>
							<select class="select-group" id="targetCounty" data-rules="{required : true}" data-messages="{required:' '}" style="width: 120px;"></select>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label"><s>*</s>托运人</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignor" oninput="loadCustomerList(this.value)" data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>托运电话</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignorMobile" data-rules="{required:true,regexp:/^\d{11}$/}" data-messages="{required:' ', regexp:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>托运地址</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignorAddress" data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运单位</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignorCompany">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label"><s>*</s>收货人</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consignee" oninput="loadCustomerList(this.value)"  data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>收货电话</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consigneeMobile" data-rules="{required:true,regexp:/^\d{11}$/}" data-messages="{required:' ', regexp:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>收货地址</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consigneeAddress" data-rules="{required:true}" data-messages="{required:' '}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货单位</label>
						<div class="controls">
							<input type="text" class="input-normal" id="consigneeCompany">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">发货方式</label>
						<div class="controls">
							<select id="sendType" class="input-normal">
								<option value="0">自送网点</option>
								<option value="1">上门取货</option>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货方式</label>
						<div class="controls">
							<select id="receiveType" class="input-normal">
								<option value="0">客户自提</option>
								<option value="1">送货上门</option>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">回单/收条</label>
						<div class="controls">
							<select id="receiptSlip" class="input-normal">
								<option value="0">无</option>
								<option value="1">回单/收条</option>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">份数</label>
						<div class="controls">
							<input type="text" id="receiptSlipNum" class="input-normal" data-rules="{validInt:''}">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">城配司机</label>
						<div class="controls">
							<input type="text" class="input-normal" id="cityDriverName">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">司机电话</label>
						<div class="controls">
							<input type="text" class="input-normal" id="cityDriverPhone" data-rules="{mobile:''}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">车牌号</label>
						<div class="controls">
							<input type="text" class="input-normal" id="cityVehicleNumber" data-rules="{validVechileNumber:true}">
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
		<div class="panel-body" id="wayBillCostInfo">
			<form class="form-horizontal" id="form_cost_info">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">代收货款</label>
						<div class="controls">
							<input type="text" class="input-normal" id="agencyFund" onchange="countAgencyFundPoundage(this.value)" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手续费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="agencyFundPoundage" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">投保金额</label>
						<div class="controls">
							<input type="text" class="input-normal" id="insuranceMoney" onchange="countInsurance(this.value)" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="insurance" disabled="disabled">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">提货费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="takeCargoCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">装卸费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="loadUnloadCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">中转费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="transferCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">其他费用</label>
						<div class="controls">
							<input type="text" class="input-normal" id="otherCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<div id="customCostDiv"></div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">基础运费</label>
						<div class="controls">
							<input type="text" class="input-normal" id="freight" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">支付方式</label>
						<div class="controls">
							<select class="input-normal" id="payMethod" onchange="changePayMethod(this.value)">
								<c:forEach items="${payMethods}" var="payMethod">
									<option value="${payMethod.id}">${payMethod.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">合计</label>
						<div class="controls">
							<input type="text" class="input-normal" id="total" disabled="disabled">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">等款放货</label>
						<div class="controls">
							<input type="checkbox" id="isWaitPay" value="1" style="height: 30px; width: 15px;">
						</div>
					</div>
				</div>
				<div class="row-fluid" id="advancePaymentDiv" style="display: none;">
					<div class="control-group span6">
						<label class="control-label">垫付货款</label>
						<div class="controls">
							<input type="text" class="input-normal" id="advanceCost" onchange="countTotalMoney()" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<div class="row-fluid" id="multi-pay" style="display: none;">
					<div class="control-group span6">
						<label class="control-label">现付</label>
						<div class="controls">
							<input type="text" class="input-normal" id="immediatePay" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">到付</label>
						<div class="controls">
							<input type="text" class="input-normal" id="arrivePay" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">回付</label>
						<div class="controls">
							<input type="text" class="input-normal" id="backPay" data-rules="{number:true}">
						</div>
					</div>
				</div>
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
	</div>
	<div class="row-fluid" style="margin-bottom: 10px;">
		<table class="button-box">
			<tr>
				<td><button class="button" onclick="saveWayBill(0)">保存</button></td>
				<td><button class="button" onclick="printPage()(0)">保存并打印</button></td>
				<td><button class="button" onclick="saveWayBill(1)">保存并新建</button></td>
				<td><button class="button" onclick="history.go(-1)">取消</button></td>
			</tr>
		</table>
	</div>
	<div id="customerDiv" style="width: 600px; padding: 2px;background-color: blue; position: absolute; top: 182px;left: 105px;display: none;">
		<div id="customerList"></div>
	</div>
	<!-- 增值服务配置 数据 -->
	<input type="hidden" id="collectionDeliveryRatio" value="${additionalServer.collectionDeliveryRatio}">
	<input type="hidden" id="lineInsuranceRatio" value="${additionalServer.lineInsuranceRatio}">
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery.PrintArea.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tms/startsitemanager/delivery-way-bill-base.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tms/startsitemanager/way-bill.js"></script>
	
	<script type="text/javascript">
	var customerFlag = 0;
	$(function(){
		$('#consignor').click(function(e){
			if($('#customerDiv').css('display') == 'none'){
				$('#customerDiv').css('top','182px');
				$('#customerDiv').toggle();
		        e.stopPropagation();
		        $('#customerList').html('');
		        loadCustomerInfoList();
		        customerFlag = 1;
			}
		});
		$('#consignee').click(function(e){
			if($('#customerDiv').css('display') == 'none'){
				$('#customerDiv').css('top','222px');
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
	function printPage(isNew){
		// 验证
		var numberInfoFormValid = numberInfoForm.isValid();
		var baseInfoFormValid = baseInfoForm.isValid();
		var costInfoFormValid = costInfoForm.isValid();
		if(!numberInfoFormValid || !baseInfoFormValid || !costInfoFormValid){
			return;
		}
		if(!validEditCargoGrid()){
			return;
		}
		
		$.ajax({
			type : 'post',
			url : contextPath + '/tms/waybill/savewaybillorder.shtml',
			data : {
				'wayBillOrder' : JSON.stringify(buildWayBillData()),
				'wayBillOrderCargo' : JSON.stringify(grid.getItems()),
				'wayBillOrderCostInfo' : JSON.stringify(buildCostData()),
				'token' : $('#token').val()
			},
			success : function(result, textStatus, XMLHttpRequest){
				if(result == 1){
					if(isNew == 1){
						// 新建 跳转到新的开单入库页面
						window.location.href = contextPath + '/tms/waybill/towaybillpage.shtml';
					}else if(isNew == 0){
						// 跳转到运单管理界面
						window.location.href = contextPath + '/tms/waybill/towaybillmanagerpage.shtml';
					}
				}else{
					BUI.Message.Alert('保存失败','error');
					// 通过XMLHttpRequest取得响应头
					var isRepeatSubmit = XMLHttpRequest.getResponseHeader('isRepeatSubmit');
					if(isRepeatSubmit == 'true'){
						// 如果是重复提交，则做相应的提示
						//alert('请不要重复提交');
					} else {
						// 非重复提交， 则取响应头中的token，更新input中的token值
						var token = XMLHttpRequest.getResponseHeader('token');
						$('#token').val(token);
					}
				}
			}
		});
		$('#printBox').printArea();
	}
	</script>
</body>
</html>