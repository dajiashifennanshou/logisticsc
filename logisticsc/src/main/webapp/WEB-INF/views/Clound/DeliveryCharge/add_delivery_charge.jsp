<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加配送费用标准</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/DeliveryCharge/DeliveryCharge.js"></script>
	<link rel="stylesheet" href="assets/css/bootstrap-multiselect.css" />
	<script type="text/javascript">
	$(function($) {
		deliveryCharge_.init();
		deliveryCharge_.initCars('#carType');
	});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加配送费用标准</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="add_delivery_form"   role="form">
				<div class="step-pane active" data-step="1">
						<!-- input标签 -->
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="deliveryCost">配送费用 </label>
						
							<div class="col-sm-9">
								<input type="text" valited="float,required" group="val" name="deliveryCost" required="true" placeholder="安装费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="carType">选择车型：</label>

							<div class="col-xs-12 col-sm-9">
								<!-- #section:plugins/input.multiselect -->
								<select group="val" valited="required" name="carType" id="carType" class="multiselect">
									<option value="">请选择车型....</option>
								</select>
								<!-- /section:plugins/input.multiselect -->
							</div>
						</div>
				</div>
			</form>
			</div>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<a onclick="deliveryCharge_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="deliveryCharge_.addSubmit('add_delivery_form');" class="btn btn-success btn-next" data-last="Finish">
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