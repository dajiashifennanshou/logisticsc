<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
/* .form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;} */
.form-horizontal input{width: 80px;}
</style>
<title>增值服务管理</title>
<script type="text/javascript">
var userType = ${tms_user_session.userType};
if(userType == 0 || userType == 1){
	alert('请使用网点账号登录');
	history.go(-1);
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-header">
			<h3>增值服务管理</h3>
		</div>
		<div class="panel-body" id="wayBillBaseInfo">
			<form class="form-horizontal" id="additionalServerForm">
				<input type="hidden" name="id" value="${additionalServer.id}">
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">装卸：</label>
					</div>
					<div class="control-group span11">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${additionalServer.isLoadAndUnload == 1}">
								<input type="checkbox" name="isLoadAndUnload" checked="checked" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${empty additionalServer.isLoadAndUnload || additionalServer.isLoadAndUnload == 0}">
								<input type="checkbox" name="isLoadAndUnload" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">是否提供装卸</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">&nbsp;</div>
					<div class="control-group span11">
						<label class="control-label">不上楼收费：</label>
					</div>
					<div class="control-group span11">
						<label class="control-label">上楼收费：</label>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">&nbsp;</div>
					<div class="control-group span11">
						<label class="control-label">重货：</label>
						<div class="controls">
							<input type="text" name="heavyCargo" value="${additionalServer.heavyCargo}" class="input-normal" data-rules="{number:true}">元/吨
						</div>
					</div>
					<div class="control-group span11">
						<label class="control-label">有电梯加收：</label>
						<div class="controls">
							<input type="text" name="elevatorAdditional" value="${additionalServer.elevatorAdditional}" class="input-normal" data-rules="{number:true}">%
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">&nbsp;</div>
					<div class="control-group span11">
						<label class="control-label">泡货：</label>
						<div class="controls">
							<input type="text" name="bulkyCargo" value="${additionalServer.bulkyCargo}" class="input-normal" data-rules="{number:true}">元/立方米
						</div>
					</div>
					<div class="control-group span11">
						<label class="control-label">无电梯每层加收：</label>
						<div class="controls">
							<input type="text" name="noElevatorAdditional" value="${additionalServer.noElevatorAdditional}" class="input-normal" data-rules="{number:true}">%
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">&nbsp;</div>
					<div class="control-group span11">
						<label class="control-label">最低收费：</label>
						<div class="controls">
							<input type="text" name="noUpstairsLowPrice" value="${additionalServer.noUpstairsLowPrice}" class="input-normal" data-rules="{number:true}">元
						</div>
					</div>
					<div class="control-group span11">
						<label class="control-label">最低收费：</label>
						<div class="controls">
							<input type="text" name="goUpstairsLowPrice" value="${additionalServer.goUpstairsLowPrice}" class="input-normal" data-rules="{number:true}">元
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">代收货款：</label>
					</div>
					<div class="control-group span11">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${additionalServer.isCollectionDelivery == 1}">
								<input type="checkbox" name="isCollectionDelivery" checked="checked" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${empty additionalServer.isCollectionDelivery || additionalServer.isCollectionDelivery == 0}">
								<input type="checkbox" name="isCollectionDelivery" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label">手续费比例：</label>
							<input type="text" name="collectionDeliveryRatio" value="${additionalServer.collectionDeliveryRatio}" class="input-normal" data-rules="{number:true}">%
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">专线投保：</label>
					</div>
					<div class="control-group span11">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${additionalServer.isLineInsurance == 1}">
								<input type="checkbox" name="isLineInsurance" checked="checked" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${empty additionalServer.isLineInsurance || additionalServer.isLineInsurance == 0}">
								<input type="checkbox" name="isLineInsurance" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label">保险费率：</label>
							<input type="text" name="lineInsuranceRatio" value="${additionalServer.lineInsuranceRatio}" class="input-normal" data-rules="{number:true}">%
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label"><s>*</s>回单：</label>
					</div>
					<div class="control-group span11">
						<label class="control-label"></label>
						<div class="controls">
							<input type="checkbox" id="isReceipt" checked="checked" value="1" style="height: 30px; width: 14px; float: left;">
							<input type="hidden" name="isReceipt" value="1">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label"><s>*</s>支付方式：</label>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${additionalServer.isImmediatePay == 1}">
								<input type="checkbox" name="isImmediatePay" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${additionalServer.isImmediatePay != 1}">
								<input type="checkbox" name="isImmediatePay" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">现付</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<input type="checkbox" id="isArrivePay" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							<input type="hidden" name="isArrivePay" value="1">
							<label class="control-label" style="text-align: left;">到付</label>
						</div>
					</div>
					<%-- <div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${additionalServer.isAdvancePay == 1}">
								<input type="checkbox" name="isAdvancePay" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${additionalServer.isAdvancePay != 1}">
								<input type="checkbox" name="isAdvancePay" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">预付</label>
						</div>
					</div> --%>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label"><s>*</s>发票类型：</label>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${additionalServer.isCommonReceipt == 1}">
								<input type="checkbox" name="isCommonReceipt" value="1" checked="checked" disabled="disabled" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${additionalServer.isCommonReceipt != 1}">
								<input type="checkbox" name="isCommonReceipt" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">普通发票</label>
						</div>
					</div>
					<div class="control-group span10">
						<label class="control-label">收费点</label>
						<div class="controls">
							<input type="text" name="commonReceiptRate" value="${additionalServer.commonReceiptRate}" style="width: 40px;" data-rules="{number:true}"> %
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">&nbsp;</label>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${additionalServer.isAddTaxReceipt == 1}">
								<input type="checkbox" name="isAddTaxReceipt" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${additionalServer.isAddTaxReceipt != 1}">
								<input type="checkbox" name="isAddTaxReceipt" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">增值税发票</label>
						</div>
					</div>
					<div class="control-group span10">
						<label class="control-label">收费点</label>
						<div class="controls">
							<input type="text" name="addTaxReceiptRate" value="${additionalServer.addTaxReceiptRate}" style="width: 40px;" data-rules="{number:true}"> %
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">&nbsp;</label>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<input type="checkbox" id="isNoReceipt" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							<input type="hidden" name="isNoReceipt" value="1">
							<label class="control-label" style="text-align: left;">无发票</label>
						</div>
					</div>
				</div>
				
				<div class="actions-bar">
		        	<div class="row ">
			            <div class="span13 offset3 ">
			              <button type="button" class="button" onclick="edit()">编辑</button>
			              <button type="button" onclick="save()" class="button button-primary">保存</button>
			            </div>
		          </div>
		        </div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var additionalServerForm;
	$(function(){
		loadForm();
		$('.form-horizontal input').attr('disabled','disabled');
	});
	
	function loadForm(){
		BUI.use('bui/form',function(Form){
			additionalServerForm = new Form.Form({
				srcNode : '#additionalServerForm'
			}).render();
		})
	}
	
	function edit(){
		$('.form-horizontal input').removeAttr('disabled');
		$('#isReceipt').attr('disabled',true);
		$('#isArrivePay').attr('disabled',true);
		$('#isNoReceipt').attr('disabled',true);
	}
	
	// 保存
	function save(){
		if(!additionalServerForm.isValid()){
			return;
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/additionalserver/save.shtml',
			data : $("#additionalServerForm").serialize(),
			success : function(result){
				if(result == 1){
					BUI.Message.Alert('保存成功',function(){
						window.location.reload(true);
					},'success');
				}else{
					BUI.Message.Alert('保存失败','error');
				}
			}
		});
	}
	</script>
</body>
</html>