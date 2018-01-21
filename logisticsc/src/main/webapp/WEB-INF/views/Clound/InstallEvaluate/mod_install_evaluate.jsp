
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-修改评价信息</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/InstallEvaluate/install_evaluate.js"></script>
	<script type="text/javascript">
		$(function(){
			var id = request.QueryString("id");
			ie_.getModData(id, "mod_ie_form");
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">修改评价信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
					<form class="form-horizontal" id="mod_ie_form"  role="form">
						<input type="hidden" group="val" name="id"  />
						<!-- input标签 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="deliveryNo">配送单号</label>
							<div class="col-sm-9">
								<input type="text" group="val" disabled="disabled" name="deliveryNo" valited="required" placeholder="配送单号" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="satisfaction">客户评价</label>
							<div class="col-sm-9">
								<select group="val" name="satisfaction" valited="required" class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
									<option value="2">非常满意</option>
									<option value="1">满意</option>
									<option value="0">不满意</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="customerSug">客户建议</label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-5" group="val" name="customerSug"></textarea>
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
				<a onclick="ie_.modSubmit('mod_ie_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>