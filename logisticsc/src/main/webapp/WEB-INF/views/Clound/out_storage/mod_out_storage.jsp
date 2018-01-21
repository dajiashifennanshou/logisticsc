<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-修改出库记录</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/out_storage/out_storage.js"></script>
	<script type="text/javascript">
		$(function(){
			outStorage_.getYcStorageBranch();
			var id = request.QueryString("id");
			outStorage_.getModData(id, "mod_out_form");
			var branchNo = $("select[name=branchNo]").val();
			outStorage_.getStorageZone(branchNo);
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">修改出库记录</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
					<form class="form-horizontal" id="mod_out_form"  role="form">
						<input type="hidden" group="val" name="id"/>
						<!-- input标签 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="dealerId">经销商</label>
							<div class="col-sm-9">
								<select group="val" valited="required" name="dealerId" class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
									<option value="100">经销商1</option>
									<option value="200">经销商2</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">网点编号</label>
							<div class="col-sm-9">
								<select group="val" name="branchNo" valited="required" class="select2-container select2-allowclear select2 tag-input-style" onchange="outStorage_.getStorageZone(this.value);">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zoneNo">库区编号</label>
							<div class="col-sm-9">
								<select group="val" name="zoneNo" valited="required" class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="deliveryNo">所属配送单</label>
							<div class="col-sm-9">
								<select group="val" valited="required" name="deliveryNo" class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
									<option value="001">001</option>
									<option value="002">002</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">备注</label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-5" group="val" name="remark"></textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="wizard-actions">
				<a disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="outStorage_.modSubmit('mod_out_form');" class="btn btn-success btn-next" data-last="Finish">
					确定
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>