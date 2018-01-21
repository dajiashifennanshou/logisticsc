<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发车清单详情</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.info-content{
	line-height: 40px;
}
.row-margin{margin-top: 8px;}
.form-horizontal .control-label{width: 80px;}
.form-dialog .control-label{width: 110px;}
.form-show-text{line-height: 30px;}
.form-horizontal .controls{height: 40px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div style="padding: 15px;">
			<label>发车单号：</label><span id="departNumber">${departList.departNumber}</span>
		</div>
	</div>
	<div class="row-fluid">
		<form class="form-horizontal" id="departListForm">
			<div class="panel">
				<div class="panel-header">
					<h3>司机运输协议</h3>
				</div>
				<div class="panel-body">
					<table border="1" width="100%">
						<tr>
							<td colspan="2">
								<div class="info-content" style="margin-left: 15px;">
									<b>${departList.startOutletsName}</b>
									<span> -- </span>
									<div style="display: inline;">${departList.wayOutletsName}</div>
									<span> -- </span>
									<b>${departList.targetOutletsName}</b>
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
											<span class="form-show-text">${departList.startOutletsName}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">到达网点</label>
										<div class="controls">
											<span class="form-show-text">${departList.targetOutletsName}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">发车时间</label>
										<div class="controls">
											<span class="form-show-text">
												<fmt:formatDate value="${departList.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">到达时间</label>
										<div class="controls">
											<span class="form-show-text">
												<fmt:formatDate value="${departList.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</span>
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
										<label class="control-label">车牌号</label>
										<div class="controls">
											<span class="form-show-text">${departList.vehicleNumber}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">挂车号</label>
										<div class="controls">
											<span class="form-show-text">${departList.hangVehicleNumber}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">车辆类型</label>
										<div class="controls">
											<span class="form-show-text">${departList.vehicleTypeName}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">司机</label>
										<div class="controls">
											<span class="form-show-text">${departList.driverName}</span>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">司机姓名</label>
										<div class="controls">
											<span class="form-show-text" id="driverName">${departList.driverName}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">司机电话</label>
										<div class="controls">
											<span class="form-show-text" id="phoneNumber">${departList.driverPhone}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">开户行</label>
										<div class="controls">
											<span class="form-show-text" id="bankName">${departList.driverBankName}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">银行卡号</label>
										<div class="controls">
											<span class="form-show-text" id="bankNumber">${departList.driverBankNumber}</span>
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
										<label class="control-label">应付司机</label>
										<div class="controls">
											<span class="form-show-text">${departList.shouldPayDriverCost}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">现付司机</label>
										<div class="controls">
											<span class="form-show-text">${departList.nowPayDriverCost}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">到付司机</label>
										<div class="controls">
											<span class="form-show-text">${departList.arrivePayDriverCost}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">回付司机</label>
										<div class="controls">
											<span class="form-show-text">${departList.backPayDriverCost}</span>
										</div>
									</div>
								</div>
								<%-- <div class="row-fluid">
									<div class="control-group span6">
										<label class="control-label">装车费</label>
										<div class="controls">
											<span class="form-show-text">${departList.loadCost}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">卸车费</label>
										<div class="controls">
											<span class="form-show-text">${departList.unloadCost}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">分流费</label>
										<div class="controls">
											<span class="form-show-text">${departList.shuntCost}</span>
										</div>
									</div>
								</div> --%>
							</td>
						</tr>
						<tr>
							<td align="center"><b>整车货险</b></td>
							<td>
								<div class="row-fluid row-margin">
									<div class="control-group span6">
										<label class="control-label">保险金额</label>
										<div class="controls">
											<span class="form-show-text">${departList.insuranceMoney}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">保险费</label>
										<div class="controls">
											<span class="form-show-text">${departList.insuranceCost}</span>
										</div>
									</div>
									<div class="control-group span6">
										<label class="control-label">是否年保</label>
										<div class="controls">
											<span class="form-show-text">
												<c:if test="${departList.isYearInsurance == 1}">是</c:if>
												<c:if test="${departList.isYearInsurance == 0}">否</c:if>
											</span>
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
						<textarea id="remark" name="remark" style="width: 98%;" disabled="disabled">${departList.remark}</textarea>
					</div>
				</div>
			</div>
		</form>
		<div class="hide" id="receiveMoneyDialog">
			<form class="form-horizontal" id="receiveMoneyOrderForm">
				<input type="hidden" id="wayBillNumbers" name="wayBillNumbers">
				<div class="row">
					<div class="control-group">
						<label class="control-label" style="width: 110px;">费用类型：</label>
						<div class="controls">
							<span class="control-text" id="costTypeText">运费</span>
							<input type="hidden" name="costType" value="0">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label" style="width: 110px;">金额：</label>
						<div class="controls">
							<span class="control-text" id="moneyText"></span>
							<input type="hidden" id="money" name="money">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label" style="width: 110px;"><s>*</s>POS收款：</label>
						<div class="controls">
							<input type="text" id="posMoney" name="posMoney" class="input-normal" value="0" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label" style="width: 110px;"><s>*</s>现金：</label>
						<div class="controls">
							<input type="text" id="cashMoney" name="cashMoney" class="input-normal" value="0" data-rules="{number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label" style="width: 110px;"><s>*</s>付款人：</label>
						<div class="controls">
							<input type="text" name="payPerson" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label" style="width: 110px;"><s>*</s>付款人手机号：</label>
						<div class="controls">
							<input type="text" name="payPersonMobile" class="input-normal" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label" style="width: 110px;">备注：</label>
						<div class="controls">
							<textarea name="remark"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row-fluid" style="margin-bottom: 10px;">
		<table class="button-box">
			<tr>
				<td><button class="button" onclick="history.back()">返回</button></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var grid, store, receiveMoneyOrderForm, receiveMoneyDialog;
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
			dataIndex : 'cargoNumber',
		}, {
			title : '套数',
			elCls : 'center',
			dataIndex : 'cargoSetNumber',
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
			url : '<%=request.getContextPath()%>/tms/waybill/getwaybillsbydepartnumber.shtml?departNumber='+$('#departNumber').text(),
			autoLoad : true
		});
		grid = new Grid.Grid({
			render : '#wayBillOrderList',
			columns : columns,
			store : store,
			plugins : [Grid.Plugins.Summary, Grid.Plugins.RowNumber,Grid.Plugins.AutoFit]
			//forceFit : true
		});
		grid.render();
	});
	
	$(function(){
		loadReceiveMoneyDialog();
		loadForm();
	});
	
	function loadForm(){
		BUI.use('bui/form',function(Form){
			receiveMoneyOrderForm = new Form.Form({
				srcNode : '#receiveMoneyOrderForm'
			}).render();
		})
	}
	
	function showReceiveMoneyDialog(wayBillNumber, money, payMethod){
		if(payMethod == 0 || payMethod == 2){
			BUI.Message.Alert('支付方式为到付的运单，可收款','warning');
			return;
		}
		$('#wayBillNumbers').val(wayBillNumber);
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
		        height:450,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'receiveMoneyDialog',
		        success:function () {
		        	if(!receiveMoneyOrderForm.isValid()){
		        		return;
		        	}
		        	var money = $('#money').val();
		        	var posMoney = $('#posMoney').val();
		        	var cashMoney = $('#cashMoney').val();
		        	if(money != (new Number(posMoney) + new Number(cashMoney))){
		        		BUI.Message.Alert('金额输入不正确，金额=POS收款+现金','warning');
		        		return;
		        	}
		        	$.ajax({
		        		type : 'post',
		        		url : '<%=request.getContextPath()%>/tms/waybillfreight/savereceivemoneyorder.shtml',
		        		data : $('#receiveMoneyOrderForm').serialize(),
		        		success : function(result){
		        			if(result == 1){
		        				receiveMoneyDialog.close();
		        				BUI.Message.Alert('操作成功',function(){
		        					store.load();
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
	
	</script>
</body>
</html>