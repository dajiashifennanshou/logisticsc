<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加安装费用标准</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/InstallCharge/InstallCharge.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-multiselect.css" />
	<script type="text/javascript">
	$(function($) {
		
		//installCharge_.initBranchNo('#branchNo');
		installCharge_.init();
		//installCharge_.initFirstLv('#firstLvCode');
		installCharge_.initTwoLv('#twoLvCode');
		//installCharge_.initSelect2('#firstLvCode');
		installCharge_.initSelect2('#twoLvCode');
		installCharge_.initSelect2('#branchNo');
	});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加安装费用标准</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="add_install_form"   role="form">
				<div class="step-pane active" data-step="1">
						<!-- input标签 -->
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="installCharge">安装费用 </label>
						
							<div class="col-sm-9">
								<input type="text" valited="float,required" group="val" name="installCharge" required="true" placeholder="安装费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="installCharge">配送费用 </label>
						
							<div class="col-sm-9">
								<input type="text" valited="float,required" group="val" name="deliverCharge" required="true" placeholder="安装费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						
						<!-- <div class="form-group">
							<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="firstLvCode">选择一级类型：</label>

							<div class="col-xs-12 col-sm-9">
								#section:plugins/input.multiselect
								<select group="val" valited="required" name="firstLvCode" id="firstLvCode" class="multiselect">
									<option value="">请选择一级类型....</option>
								</select>


								/section:plugins/input.multiselect
							</div>
						</div> -->
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="twoLvCode">选择类型：</label>

							<div class="col-xs-12 col-sm-9" id="twoHtml">
								<select group="val" valited="required" name="twoLvCode" id="twoLvCode" class="multiselect" >
									<option value="">请选择类型....</option>
								</select>
							</div>
						</div>
				</div>
			</form>
			</div>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<a onclick="installCharge_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="installCharge_.addSubmit('add_install_form');" class="btn btn-success btn-next" data-last="Finish">
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