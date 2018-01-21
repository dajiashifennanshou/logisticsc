<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-修改配送订单</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/DeliveryOrder/DeliveryOrder.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/daterangepicker.css" />
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-datetimepicker.css" />
	<script src="/logisticsc/Clound/assets/js/date-time/moment.js"></script>
	<script src="/logisticsc/Clound/assets/js/date-time/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript">
	$(function($) {
		//deliveryCharge_.initSelect("#joinerId");
		//表单赋值
		//获取id
		var id=request.QueryString("id");
		deliveryCharge_.getModData(id);
		
		//初始化标签
		deliveryCharge_.initList();
		deliveryCharge_.initDate();
		deliveryCharge_.init();
	});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">修改配送安装费用</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
			<form class="form-horizontal" id="mod_delivery_order_form"   role="form">
			<input type="hidden" group="val" name="id"/>
			<input type="hidden" group="val" id="afterGoodsId" name="afterGoodsId"/>
			<input type="hidden" group="val" id="deliveryNo" name="deliveryNo"/>
			<input type="hidden" group="val" id=dealerId name="dealerId"/>
					<div class="step-pane active" data-step="1">
						<!-- input标签 -->
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="consigneeName">客户姓名 </label>
							<div class="col-sm-9">
								<input type="text" group="val" valited="required" name="consigneeName" required="true" placeholder="客户姓名" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="consigneePhone">客户电话 </label>
							
								<div class="col-sm-9">
									<input type="text" group="val" valited="required,phone" name="consigneePhone" required="true"  placeholder="客户电话" class="col-xs-10 col-sm-5" />
								</div>									 
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="consigneeAddr">客户地址</label>
							<div class="col-sm-9">
								<input type="text" group="val" valited="required" name="consigneeAddr"  placeholder="客户地址" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="installCost">代收款</label>
							<div class="col-sm-9">
								<input type="text" group="val"  id="agentPaidCost" name="agentPaidCost" value="0" placeholder="代收费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="installCost">支付类型</label>
							<div class="col-sm-9">
								<select group="val"  name="payType" valited="required" id='payType' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">
										<option value="0">现付</option>
										<option value="1">到付</option>
									</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="subscribeTime">预约配送时间</label>
							<!-- #section:plugins/date-time.datetimepicker -->
								
							 <div class="col-sm-9">
								<input id="subscribeTime" group="val" name="subscribeTime" valited="required,date"  type="text" placeholder="请选择配送时间" data-date-format="YYYY-MM-DD HH:mm:ss" class="col-xs-10 col-sm-5" />
							</div> 
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="installCost">安装费用</label>
							<div class="col-sm-9">
								<input type="text" group="val" readonly="readonly" id="installCost" name="installCost"  placeholder="安装费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="deliveryCost">配送费用</label>
							
							<div class="col-sm-9">
								<input type="text"  placeholder="只有在此订单配载后才会显示.." name="deliveryCost" class="col-xs-10 col-sm-5" />
							</div>
						</div>
					</div>
					<!-- 下一页开始 -->
					<!-- 下一页结束 -->
				</form>
			</div>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<a onclick="deliveryCharge_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="deliveryCharge_.modSubmit('mod_delivery_order_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>

				<!-- /section:plugins/fuelux.wizard.buttons -->
			</div>
							
		</div>
	</div>
</div>
</body>
</html>