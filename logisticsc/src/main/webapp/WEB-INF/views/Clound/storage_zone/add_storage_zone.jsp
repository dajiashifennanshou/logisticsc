<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加云仓网点</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/storage_zone/storage_zone.js"></script>
	<script type="text/javascript">
		$(function(){
			storageZone_.getYcStorageBranch();
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true});
			$('.statusSel').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
		})
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加库区信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
						<form class="form-horizontal" id="add_zone_form"  role="form">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="storageNo">网点编号</label>
							<div class="col-sm-9">
								<select group="val" name="storageNo" valited="required"  class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zoneArea">库区面积</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="zoneArea" valited="required,number" placeholder="仓库面积" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zoneStatus">库区状态</label>
							<div class="col-sm-9">
								<select group="val" name="zoneStatus" valited="required"  class="select2-container select2-allowclear statusSel tag-input-style">
									<option value=""></option>
									<option value="0">启用</option>
									<option value="1">维修</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">备注</label>
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
				<a onclick="storageZone_.addSubmit('add_zone_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>