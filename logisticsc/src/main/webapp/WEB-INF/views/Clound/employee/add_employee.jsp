<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加员工信息</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/employee/employee.js"></script>
	<script type="text/javascript">
		$(function(){
			employee_.getPost();
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加员工信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
					<form class="form-horizontal" id="add_employee_form"  role="form">
						<!-- input标签 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="employeeName">姓名</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="employeeName" valited="required" placeholder="姓名" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="gender">性别</label>
							<div class="col-sm-9">
								<select group="val" name="gender" valited="required" class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
									<option value="0">男</option>
									<option value="1">女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">年龄</label>
							<div class="col-sm-9">
								<input type="number" group="val" valited="number" length=2 name="age" min="0"  placeholder="年龄" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="position">职位</label>
							<div class="col-sm-9">
								<select group="val" name="position" valited="required"  class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="term">工作年限</label>
							<div class="col-sm-9">
								<input type="number" group="val" valited="number" length=2 name="term" min="0" placeholder="工作年限" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="phone">手机号码</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="phone" valited="required,phone" placeholder="手机号码" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="cardNumber">身份证号</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="cardNumber" valited="required,card" placeholder="身份证号" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">住宅地址</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="address" placeholder="住宅地址" class="col-xs-10 col-sm-5" />
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
				<a onclick="employee_.addSubmit('add_employee_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>