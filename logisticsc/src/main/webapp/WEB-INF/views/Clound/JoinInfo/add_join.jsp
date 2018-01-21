<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加加盟商信息</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-datetimepicker.css">
	<script src="/logisticsc/Clound/assets/js/date-time/moment.js"></script>
	<script src="/logisticsc/Clound/assets/js/date-time/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinInfo/join_info.js"></script>
	<script type="text/javascript">
		$(function(){
		 	$('#startUseTime').datetimepicker({});
	        $('#endUseTime').datetimepicker({
	            useCurrent: false 
	        });
	        $("#startUseTime").on("dp.change", function (e) {
	            $('#endUseTime').data("DateTimePicker").minDate(e.date);
	        });
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true});
			$('.typeSel').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
		})
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加加盟商信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
					<form class="form-horizontal" id="add_join"  role="form">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="joinType">加盟商类型</label>
							<div class="col-sm-9">
								<select group="val" name="joinType" valited="required"  class="select2-container select2-allowclear typeSel tag-input-style" onchange="join_.getUnRegister(this.value);">
									<option value=""></option>
									<option value="0">经销商</option>
									<option value="1">专线</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="joinerId">申请人</label>
							<div class="col-sm-9">
								<select group="val" name="joinerId" valited="required"  class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="days">使用时间(月)</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="days" valited="required,number" placeholder="使用时间：月为单位" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">经销商留言</label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-5" group="val" name="dealerMsg"></textarea>
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
				<a onclick="join_.addSubmit('add_join');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>