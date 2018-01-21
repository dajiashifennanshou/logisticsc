<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加云仓网点</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/css/storage_branch.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/storage_branch/storage_branch.js"></script>
	<script type="text/javascript">
		$(function(){
			storageBranch_.getEmployee();
			var id = request.QueryString("id");
			storageBranch_.getModData(id, "mod_branch_form");
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
			$('.addSel').css('width','13.7%').select2({allowClear:true});
			$('.masterSel').css('width','41.7%').select2({allowClear:true});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">修改网点信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
						<form class="form-horizontal" id="mod_branch_form"  role="form">
						<input type="hidden" group="val" name="id" />
						<!-- input标签 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchName">网点名称</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="branchName" valited="required" placeholder="网点名称" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="joinType">合作形式</label>
							<div class="col-sm-9">
								<select group="val" name="joinType" valited="required"  class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
									<option value="0">直营</option>
									<option value="1">加盟</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchArea">仓库面积</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="branchArea" valited="required,float" placeholder="仓库面积" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchType">仓库类型</label>
							<div class="col-sm-9">
								<select group="val" name="branchType" valited="required"  class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
									<option value="0">平面仓库</option>
									<option value="1">立体仓库</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="province">仓库地址</label>
							<div class="col-sm-9">
									<select group="val" name="province" valited="required" class="select2-container select2-allowclear addSel tag-input-style">
										<option value=""></option>
										<option value="四川省">四川省</option>
										<option value="贵州省">贵州省</option>
									</select>
									<select group="val" name="city" valited="required" class="select2-container select2-allowclear addSel tag-input-style">
										<option value=""></option>
										<option value="成都市">成都市</option>
										<option value="毕节市">毕节市</option>
									</select>
									<select group="val" name="county" valited="required" class="select2-container select2-allowclear addSel tag-input-style">
										<option value=""></option>
										<option value="高新区">高新区</option>
										<option value="毕节区">毕节区</option>
									</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="town">乡镇街道</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="town" valited="required" placeholder="乡镇街道" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="masterId">负责人</label>
							<div class="col-sm-9">
								<select group="val" name="masterId" valited="required"  class="select2-container select2-allowclear masterSel tag-input-style">
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
				<a disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="storageBranch_.modSubmit('mod_branch_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>