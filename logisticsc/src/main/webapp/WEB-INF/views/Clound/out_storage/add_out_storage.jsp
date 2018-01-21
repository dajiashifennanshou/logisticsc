<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-配载出库</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/out_storage/out_storage.js"></script>
	<script type="text/javascript">
		$(function(){
			//outStorage_.init();
			//outStorage_.getYcStorageBranch();
			//下拉框初始化
			//$('.select2').css('width','41.7%').select2({allowClear:true});
			outStorage_.init();
			outStorage_.initStowage('#stowage');
			outStorage_.initSelect('#stowage');
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">配载出库</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="add_out_form"  role="form">
					<!-- input标签 -->
					<div class="step-pane active" data-step="1">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="dealerId">选择配载单</label>
							<div class="col-sm-9">
								<select group="val" valited="required"  id="stowage" name="stowage" class="select2-container select2-allowclear select2 tag-input-style">
									<!-- 选择配载单来出库  -->
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<div class="step-pane" id="selectInstaller" data-step="2">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="selectInstaller">配载单信息</label>
							<div class="col-sm-9">
								<select group="val" valited="required" name="selectInstaller" id="selectInstaller" class="select2-container select2-allowclear select2 tag-input-style">
									<!-- 选择配载单来出库  -->
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="wizard-actions">
				<a onclick="outStorage_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="outStorage_.addSubmit('add_out_form');" class="btn btn-success btn-next" data-last="Finish">
					下页
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>