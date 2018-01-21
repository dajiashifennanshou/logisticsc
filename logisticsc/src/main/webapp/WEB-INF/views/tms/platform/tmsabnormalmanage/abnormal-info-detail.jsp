<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>异常详情</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<form class="form-horizontal" id="form_abnormal" enctype="multipart/form-data" onSubmit="return false;">
		<div class="panel">
	        <div class="panel-header">
	           	<h2>基本运单信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label">运单号</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.wayBillNumber}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">发站网点</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.startOutletsName}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">到站网点</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.targetOutletsName}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货人</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.consignee}</label>
						</div>
					</div>
				</div>
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label">托运人</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.consignor}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运人电话</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.consignorMobile}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运日期</label>
						<div class="controls">
							<label class="control-label" style="text-align: left; width: 150px;"><fmt:formatDate value="${abnormal.wayBillOrderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险金额</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.money}元</label>
						</div>
					</div>
				</div>
			</div>
	    </div>
	    <div class="panel">
	        <div class="panel-header">
	           	<h2>异常信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label">发生日期</label>
						<div class="controls">
							<label class="control-label" style="text-align: left; width: 150px;"><fmt:formatDate value="${abnormal.abnormalDate }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">发现人</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.foundPerson}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">异常环节</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.abnormalNodeName}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">异常类型</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.abnormalTypeName}</label>
						</div>
					</div>
				</div>
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label">商品名称</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.cargoName}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">包装</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.cargoPackage}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">件数</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.cargoPiece}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">货差数量</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.cargoDiffNumber}</label>
						</div>
					</div>
				</div>
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label">货损数量</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.cargoDamageNumber}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">责任站点</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.dutySiteName}</label>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">登记人</label>
						<div class="controls">
							<label class="control-label" style="text-align: left;">${abnormal.operationPerson}</label>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">货损情况</label>
					<div class="controls control-row-auto">
						<div class="bordered span-width span24" style="height:60px;">${abnormal.content}</div>
		            </div>
				</div>
			</div>
	    </div>
	    <div class="panel">
	        <div class="panel-header">
	           	<h2>图片信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group">
						<img src="/logisticsc/img/retrive.shtml?resource=${abnormal.abImgPath}"/>
					</div>
				</div>
			</div>
	    </div>
	    <c:if test="${not empty abnormalHandle}">
	    	<div class="panel">
		        <div class="panel-header">
		           	<h2>处理信息</h2>
		        </div>
		        <div class="panel-body">
					<div class="row-fluid" style="margin-top: 10px;">
						<div class="control-group span6">
							<label class="control-label">处理日期</label>
							<div class="controls">
								<label class="control-label" style="text-align: left; width: 150px;"><fmt:formatDate value="${abnormalHandle.handleDate }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">理赔金额</label>
							<div class="controls">
								<label class="control-label" style="text-align: left;">${abnormalHandle.compensationMoney}元</label>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">客户意见</label>
						<div class="controls control-row-auto">
							<div class="bordered span-width span24" style="height:60px;">${abnormalHandle.customerOpinion}</div>
			            </div>
					</div>
					<div class="control-group">
						<label class="control-label">回复意见</label>
						<div class="controls control-row-auto">
							<div class="bordered span-width span24" style="height:60px;">${abnormalHandle.replyOpinion}</div>
			            </div>
					</div>
					<div class="control-group">
						<label class="control-label">处理结果</label>
						<div class="controls control-row-auto">
							<div class="bordered span-width span24" style="height:60px;">${abnormalHandle.handleResult}</div>
			            </div>
					</div>
					<div class="control-group">
						<label class="control-label">预防措施</label>
						<div class="controls control-row-auto">
							<div class="bordered span-width span24" style="height:60px;">${abnormalHandle.preventionMeasures}</div>
			            </div>
					</div>
				</div>
		    </div>
	    </c:if>
    </form>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
</body>
</html>