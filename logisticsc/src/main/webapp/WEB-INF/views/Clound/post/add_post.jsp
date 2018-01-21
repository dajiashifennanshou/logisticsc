<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-添加职位信息</title>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/post/post.js"></script>
	<script type="text/javascript">
		$(function(){
			//下拉框初始化
			$('.select2').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
			post_.getPost();
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">添加职位信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
					<form class="form-horizontal" id="add_post_form"  role="form">
						<!-- input标签 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="postName">职位名称</label>
							<div class="col-sm-9">
								<input type="text" group="val" name="postName" valited="required" placeholder="职位名称" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="postLevel">职位级别</label>
							<div class="col-sm-9">
								<select group="val" valited="required" name="postLevel" class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
							</div>
						</div> -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="belongLevel">上级职位</label>
							<div class="col-sm-9">
								<select group="val" name="belongLevel" valited="required"  class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">职位描述</label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-5" group="val" name="postDesc"></textarea>
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
				<a onclick="post_.addSubmit('add_post_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>