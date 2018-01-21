<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加配载单</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/Stowage/Stowage.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-multiselect.css" />
	<script type="text/javascript">
	$(function($) {
		stowage_.initCar("#carNo",{carStatus:0});
		stowage_.initStowage("#deliveryNo",{orderStatus:0});
		stowage_.initSelect(".select2");
		stowage_.initList();
		stowage_.init();
		
	});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加配载单</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
			<form class="form-horizontal" id="add_stowage_form"   role="form">
				<div class="step-pane active" data-step="1">
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="carNo">选择车辆：</label>
							<div class="col-xs-12 col-sm-9">
								<!-- #section:plugins/input.multiselect -->
								<select group="val" name="carNo" valited="required" id="carNo" class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">
									<!-- <option value="川A-A10999">大卡车</option> -->
									
								</select>
								<!-- /section:plugins/input.multiselect -->
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-top" for="deliveryNo"> 选择配送单 </label>

							<div class="col-sm-8">
								<!-- #section:plugins/input.duallist -->
								<select multiple="multiple" group="val" valited="required" size="10" name="deliveryNo" id="deliveryNo">
									<!-- <option value="PS-85">PS-00125</option> -->
								</select>

								<!-- /section:plugins/input.duallist -->
								<div class="hr hr-16 hr-dotted"></div>
							</div>
						</div>
				</div>
				<div class="step-pane" data-step="2" >
						<div class="form-group" id="selectInstaller">
							
						</div>
						<br/>
				</div>
				<!-- 下一页开始 -->
				<!-- 下一页结束 -->
			</form>
			</div>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<a onclick="stowage_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="stowage_.addSubmit('add_stowage_form');" class="btn btn-success btn-next" data-last="Finish">
					下页
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>

				<!-- /section:plugins/fuelux.wizard.buttons -->
			</div>
							
		</div>
	</div>
</div>

</body>
</html>