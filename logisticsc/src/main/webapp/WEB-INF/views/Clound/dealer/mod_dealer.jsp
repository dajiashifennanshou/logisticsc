<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-修改经销商信息</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/dealer/dealer.js"></script>
	<script type="text/javascript">
		$(function(){
			//dealer_.getYcStorageBranch();
			var id = request.QueryString("id");
			dealer_.getModData(id, "mod_dealer_form");
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true});
		})
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">修改经销商信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
						<form class="form-horizontal" id="mod_dealer_form"  role="form">
						<input type="hidden" group="val" name="id"/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="dealerName">经销商名称</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="dealerName" valited="required" placeholder="经销商名称" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">网点编号</label>
							<div class="col-sm-9">
								<select group="val" name="branchNo" valited="required" class="select2-container select2-allowclear select2 tag-input-style" onchange="dealer_.getStorageZone(this.value);">
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
							<label class="col-sm-3 control-label no-padding-right" for="zoneArea">库区面积</label>
							<div class="col-sm-9">
								<input name="zoneArea" group="val" valited="required,number" type="text" placeholder="库区面积" class="col-xs-10 col-sm-5" />
							</div>
						</div>-->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="phone">手机号码</label>
							<div class="col-sm-9">
								<input name="phone" group="val" valited="required,phone" type="text" placeholder="手机号码" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">固定电话</label>
							<div class="col-sm-9">
								<input name="telephone" group="val" type="text" placeholder="固定电话" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="remark">备注</label>
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
				<a onclick="dealer_.modSubmit('mod_dealer_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>