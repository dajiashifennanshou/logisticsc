<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-修改安装费用</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/InstallCharge/InstallCharge.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-multiselect.css" />
	<script type="text/javascript">
		$(function($) {
			//获取id数据
			installCharge_.init();
			installCharge_.initFirstLv('#firstLvCode');
			//installCharge_.initBranchNo('#branchNo');
			var id=request.QueryString("id");
			installCharge_.getModData(id);
			installCharge_.initTwoLv('#twoLvCode');
			//获取二级数据
			installCharge_.getModData(id);
			installCharge_.initSelect2('#firstLvCode');
			installCharge_.initSelect2('#twoLvCode');
			installCharge_.initSelect2('#branchNo');
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">修改安装费用</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="mod_install_form" action="addYcInstallCharge.yc"  role="form">
				<div class="step-pane active" data-step="1">
						<input type="hidden" name="id" value="" group="val" />
						<!-- input标签 -->
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="installCharge">安装费用 </label>
						
							<div class="col-sm-9">
								<input type="text" valited="required,float" group="val" name="installCharge" placeholder="安装费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="installCharge">配送费用 </label>
						
							<div class="col-sm-9">
								<input type="text" valited="float,required" group="val" name="deliverCharge" required="true" placeholder="安装费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="towLvName">选择类型：</label>

							<div class="col-xs-12 col-sm-9" id="twoHtml">
								<select group="val" name="twoLvCode" valited="required" id="twoLvCode"  class="select2-container select2-allowclear typeSel tag-input-style">
									<option value="">请选择类型....</option>
								</select>
							</div>
						</div>
				</div>
				</form>
			</div>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<a  disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="installCharge_.modSubmit('mod_install_form');" class="btn btn-success btn-next" data-last="Finish">
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