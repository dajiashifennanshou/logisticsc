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
<title>基础数据</title>
</head>
<body>
	<div class="panel">
		<div class="panel-header">
			<h3>基础数据</h3>
		</div>
		<div class="panel-body" id="wayBillBaseInfo">
			<form class="form-horizontal" id="basicDataForm">
				<input type="hidden" name="id" value="${basicData.id}">
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">支付方式：</label>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${basicData.isImmediatePay == 1}">
								<input type="checkbox" name="isImmediatePay" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${basicData.isImmediatePay != 1}">
								<input type="checkbox" name="isImmediatePay" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">现付</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${basicData.isArrivePay == 1}">
								<input type="checkbox" name="isArrivePay" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${basicData.isArrivePay != 1}">
								<input type="checkbox" name="isArrivePay" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">到付</label>
						</div>
					</div>
					<%-- <div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${basicData.isAdvancePay == 1}">
								<input type="checkbox" name="isAdvancePay" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${basicData.isAdvancePay != 1}">
								<input type="checkbox" name="isAdvancePay" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">预付</label>
						</div>
					</div> --%>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">发票类型：</label>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<input type="checkbox" id="isCommonReceipt" value="1" checked="checked" disabled="disabled" style="height: 30px; width: 14px; float: left;">
							<input type="hidden" name="isCommonReceipt" value="1">
							<label class="control-label" style="text-align: left;">普通发票</label>
						</div>
					</div>
					<div class="control-group span10">
						<label class="control-label">收费点</label>
						<div class="controls">
							<input type="text" name="commonReceiptRate" value="${basicData.commonReceiptRate}" style="width: 40px;" data-rules="{number:true}"> %
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
							<c:if test="${basicData.isAddTaxReceipt == 1}">
								<input type="checkbox" name="isAddTaxReceipt" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${basicData.isAddTaxReceipt != 1}">
								<input type="checkbox" name="isAddTaxReceipt" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">增值税发票</label>
						</div>
					</div>
					<div class="control-group span10">
						<label class="control-label">收费点</label>
						<div class="controls">
							<input type="text" name="addTaxReceiptRate" value="${basicData.addTaxReceiptRate}" style="width: 40px;" data-rules="{number:true}"> %
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
							<input type="checkbox" id="isNoReceipt" value="1" checked="checked" disabled="disabled" style="height: 30px; width: 14px; float: left;">
							<input type="hidden" name="isNoReceipt" value="1">
							<label class="control-label" style="text-align: left;">无发票</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">保险设置</label>
					</div>
					<div class="control-group span6">
						<label class="control-label"></label>
						<div class="controls">
							<c:if test="${basicData.isSupportInsurance == 1}">
								<input type="checkbox" name="isSupportInsurance" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<c:if test="${basicData.isSupportInsurance != 1}">
								<input type="checkbox" name="isSupportInsurance" value="1" style="height: 30px; width: 14px; float: left;">
							</c:if>
							<label class="control-label" style="text-align: left;">支持保险</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group span3">
						<label class="control-label">&nbsp;</label>
					</div>
					<div class="control-group span16">
						<label class="control-label"></label>
						<div class="controls" style="height: 120px; width: 400px; border: 1px solid black; padding-left: 10px; line-height: 40px;">
							<div>
								<c:if test="${basicData.isForceInsurance == 1}">
									<input type="checkbox" name="isForceInsurance" value="1" checked="checked" style="height: 30px; width: 14px; float: left;">
								</c:if>
								<c:if test="${basicData.isForceInsurance != 1}">
									<input type="checkbox" name="isForceInsurance" value="1" style="height: 30px; width: 14px; float: left;">
								</c:if>
								<label class="control-label" style="text-align: left;">强制购买保险</label>
							</div>
							<div style="clear: both;">
								<label class="control-label" style="width: auto; height: 40px; line-height: 40px;">保险费率</label>
								<input type="text" name="insuranceRate" value="${basicData.insuranceRate}" data-rules="{number:true}"> %
							</div>
							<div>
								<label class="control-label" style="width: auto; height: 40px; line-height: 40px;">最低额度</label>
								<input type="text" name="lowestQuato" value="${basicData.lowestQuato}" data-rules="{number:true}"> 元
							</div>
						</div>
					</div>
				</div>
				
				<div class="actions-bar">
		        	<div class="row ">
			            <div class="span13 offset3 ">
			              <button type="button" class="button" onclick="edit()">编辑</button>
			              <button type="button" onclick="save()" id="saveBtn" disabled="disabled" class="button button-primary">保存</button>
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
	var basicDataForm;
	$(function(){
		loadForm();
		$('.form-horizontal input').attr('disabled','disabled');
	});
	
	function loadForm(){
		BUI.use('bui/form',function(Form){
			basicDataForm = new Form.Form({
				srcNode : '#basicDataForm'
			}).render();
		})
	}
	
	function edit(){
		$('.form-horizontal input').removeAttr('disabled');
		$('#isCommonReceipt').attr('disabled','disabled');
		$('#isNoReceipt').attr('disabled','disabled');
		$('#saveBtn').removeAttr('disabled');
	}
	
	// 保存
	function save(){
		if(!basicDataForm.isValid()){
			return;
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/basicdata/save.shtml',
			data : $("#basicDataForm").serialize(),
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