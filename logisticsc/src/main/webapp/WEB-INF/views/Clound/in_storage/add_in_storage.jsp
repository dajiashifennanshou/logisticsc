<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>线上入库</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/in_storage/in_storage.js"></script>
	<script type="text/javascript">
		$(function(){
			inStorage_.init();
			inStorage_.loadCargoTab();
			//inStorage_.getWayBillOrder();
			var branchNo=$('#branchNo').val();
			inStorage_.getStorageZone(branchNo);
			inStorage_.getWayBillOrder($('#dealerId').val());
			/* inStorage_.getYcStorageBranch(); */
			//下拉框初始化
			$('.inSel').css('width','41.7%').select2({allowClear:true,minimumResultsForSearch: -1});
			$('.select2').css('width','41.7%').select2({allowClear:true});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">线上入库</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="add_in_form"  role="form">
				<input type="hidden" id="branchNo" value="${branchNo }" />
					<!-- input标签 -->
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="branchNo">请选择经销商</label>
						<div class="col-sm-9">
							<select group="val" onchange="inStorage_.getWayBillOrder(this.value)" id="dealerId" name="dealerId" valited="required" id='dealerId' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">
									<!-- <option value="1">小美</option> -->
									<c:if test="${userList!=null }">
										<c:forEach items="${userList}" var="user">
											<option value="${user.id}"> ${user.trueName }-${user.joinName}-${user.loginName }</option>
										</c:forEach>
									</c:if>
								</select>
						</div>
					</div>
					<div class="step-pane active" data-step="1">	
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="waybillNo">运单号</label>
							<div class="col-sm-9">
								<select group="val" name="waybillNo" valited="required" class="select2-container select2-allowclear select2 tag-input-style" onchange="inStorage_.getWayBillOrderCargoInfo(this.value)">
									<option value=""></option>
								</select>
							</div>
						</div>
						<table id="cargo_tab"></table>
						<input group="val" name="cId" id="cId" type="hidden"/>
					</div>
					<div class="step-pane" data-step="2">
						<!-- <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">网点编号</label>
							<div class="col-sm-9">
								<select group="val" name="branchNo" valited="required" class="select2-container select2-allowclear select2 tag-input-style" onchange="inStorage_.getStorageZone(this.value);">
									<option value=""></option>
								</select>
							</div>
						</div> -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zoneNo">库区编号</label>
							<div class="col-sm-9">
								<select group="val" name="zoneNo" valited="required" class="select2-container select2-allowclear select2 tag-input-style">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="inType">入库类型</label>
							<div class="col-sm-9">
								<select group="val" name="inType" valited="required" class="select2-container select2-allowclear inSel tag-input-style">
									<option value=""></option>
									<option value="0">直接入库</option>
									<option value="1">扫描入库</option>
									<option value="2">手工入库</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">备注</label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-5" group="val" name="remark"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="wizard-actions">
				<a onclick="inStorage_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="inStorage_.addSubmit('add_in_form');" class="btn btn-success btn-next" data-last="Finish">
					下页
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>

			</div>
				
			</div>
		</div>
	</div>
</div>
</body>
</html>