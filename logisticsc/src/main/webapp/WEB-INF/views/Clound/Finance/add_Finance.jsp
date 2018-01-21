<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/Finance/Finance.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/daterangepicker.css" />
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-datetimepicker.css" />
	<script src="/logisticsc/Clound/assets/js/date-time/moment.js"></script>
	<script src="/logisticsc/Clound/assets/js/date-time/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript">
	$(function($) {
		finance_.init();
		finance_.initSelect("#costType");
		finance_.initSelect("#status");
		finance_.initOrder("#orderNumber");
		finance_.initSelect("#orderNumber");
	});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">结算</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
			<form class="form-horizontal" id="add_finance_form"  role="form">
					<div class="step-pane active" data-step="1">
							<!-- input标签 -->
							<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="orderNumber">订单号 </label>
								<div class="col-sm-9">
									<select group="val" onchange="finance_.onOrderNumber(this)" name="orderNumber" valited="required" id='orderNumber' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="选择订单号...">
										
									</select>
								</div>
							</div>
						<!-- 选择货物 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-top" for="costType">费用类型 </label>

							<div class="col-sm-9">
								<!-- #section:plugins/input.duallist -->
									<select group="val"  name="costType" valited="required" id='costType' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="选择费用类型...">
										<option value="0">安装费</option>
										<option value="1">配送费</option>
										<option value="2">安装配送费</option>
										<option value="3">预付费</option>
										<option value="4">代收费</option>
									</select>
							</div>
						</div>
						<!-- input标签 -->
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="money">金额(元) </label>
							<div class="col-sm-9">
								<input type="text" group="val" valited="required,number" length="10" name="money" id="money" placeholder="金额" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="payPerson">付款客户 </label>
							
								<div class="col-sm-9">
									<input type="text" group="val" valited="required" name="payPerson" id="payPerson" required="true"  placeholder="付款客户" class="col-xs-10 col-sm-5" />
								</div>									 
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="payPersonMobile">客户电话</label>
							<div class="col-sm-9">
								<input type="text" group="val" valited="required,phone" id="payPersonMobile" name="payPersonMobile"  placeholder="客户电话" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="status">收款状态</label>
							<div class="col-sm-9">
								<select group="val"  name="status" valited="required" id='status' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">
									<option value="0">未收款</option>
									<option value="1">已收款</option>
									<option value="2">搁置</option>
								</select>
							</div>
						</div>
					</div>
					<!-- 下一页开始 -->
					<!-- 下一页结束 -->
				</form>
			</div>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<a onclick="finance_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="finance_.addSubmit('add_finance_form');" class="btn btn-success btn-next" data-last="Finish">
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