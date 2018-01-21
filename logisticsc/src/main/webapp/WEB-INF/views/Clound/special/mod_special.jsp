<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-修改专线商信息</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/special/special.js"></script>
	<script type="text/javascript">
		$(function(){
	        special_.init();
			var id = request.QueryString("id");
			special_.getModData(id, "mod_special_form");
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true});
		})
	</script>
</head>
<body>
<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加专线信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="mod_special_form"  role="form">
					<input name="id" type="hidden" group="val">
					<div class="step-pane active" data-step="1">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="companyCode">公司代码</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="companyCode" valited="required" placeholder="公司代码" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="companyName">公司名称</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="companyName" valited="required" placeholder="公司名称" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchCode">网点代码</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="branchCode" valited="required" placeholder="公司代码" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchName">网点名称</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="branchName" valited="required" placeholder="公司名称" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchzone">网点区域</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="branchzone" valited="required" placeholder="公司区域" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">网点编号</label>
							<div class="col-sm-9">
								<select group="val" name="branchNo" valited="required" class="select2-container select2-allowclear select2 tag-input-style" onchange="special_.getStorageZone(this.value);">
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
						</div> -->
					</div>
					<div class="step-pane" data-step="2">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="linkman">联系人</label>
							<div class="col-sm-9">
								<input name="linkman" group="val" valited="required" type="text" placeholder="联系人" class="col-xs-10 col-sm-5" />
							</div>
						</div>
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
							<label class="col-sm-3 control-label no-padding-right" for="email">邮箱</label>
							<div class="col-sm-9">
								<input name="email" group="val" type="text" placeholder="邮箱" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="email">地址</label>
							<div class="col-sm-9">
								<input name="address" group="val" type="text" placeholder="地址" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="remark">备注</label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-5" group="val" name="remark"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			
			<div class="wizard-actions">
				<a onclick="special_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="special_.modSubmit('mod_special_form');" class="btn btn-success btn-next" data-last="Finish">
					下页
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>