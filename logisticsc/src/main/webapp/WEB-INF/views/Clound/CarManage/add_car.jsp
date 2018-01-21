<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加车辆信息</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/CarManage/car_manage.js"></script>
	<script type="text/javascript">
		$(function(){
			car_.initCars('#carType');
			car_.getEmployee();
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true});
			$('.typeSel').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
		})
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加车辆信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
						<form class="form-horizontal" id="add_car"  role="form">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="carNo">车牌号</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="carNo" valited="required,carNumber" placeholder="车牌号" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="carType">车型</label>
							<div class="col-sm-9">
								<select group="val" name="carType" valited="required" id="carType"  class="select2-container select2-allowclear typeSel tag-input-style">
									<option value="">请选择车型</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="weight">载重量</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="weight" valited="required,number" placeholder="载重量" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="volume">荷载体积</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="volume" valited="required,float" length=2 placeholder="荷载体积" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="driverId">司机</label>
							<div class="col-sm-9">
								<select group="val" name="driverId" multiple="multiple" valited="required"  class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="选择司机...">
									<option value=""></option>
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
				<a disabled="disabled" class="btn btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="car_.addSubmit('add_car');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>