<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<!--定义媒体查询，后者确定-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--显示此网页的IE版本-->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<title>下单</title>

<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!--[if lt IE 9]>-->
<!-- <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
 -->
<!--弹窗口-->
<!-- <script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script> -->
<style type="text/css">
.btn:focus,
.btn:active:focus,
.btn.active:focus,
.btn.focus,
.btn:active.focus,
.btn.active.focus {
    outline: none;          
}
.center-block() {
	display: block;
	margin-left: auto;
	margin-right: auto;
}
.container,
.navbar-static-top .container,
.navbar-fixed-top .container,
.navbar-fixed-bottom .container {
    /*width:1200px;
    .center-block();*/
}
.con1 {
	margin-top: 10px;
}
.lie1-font {
	color: #333333;
	width: 1200px;
	height: 35px;
	line-height: 35px;
	font-size: 16px;
	font-weight: bold;
	border-bottom: 1px;
	border-bottom-color: #9dc7e1;
	border-bottom-style: solid;
	color: #5d86b9;
	font-weight: bold;
	font-size: 20px;
}
.left1 {
	margin-left: 2%;
}
</style>
<title>我要发货-下单</title>
<style type="text/css">
.cargo-info-table td{
	text-align: center;
}
.cargo-info-table input{
	width: 92%;
	height: 92%;
}
.cargo-info-table select{
	width: 80px;
	height: 27px;
}
.cargo-info-table a{
	font-size: 12px;
	margin: 0px;
	padding: 0px;
}
.edit-table-input, .edit-table-select{
	width: 80px;
	font-size: 12px;
	border-radius: 2px;
	border: 1px solid #ccc;
	padding: 2px 4px;
}
.edit-table-select{
	padding: 3px 4px;
}

.form-horizontal .custom-input{
	padding: 2px 3px;
	font-size: 14px;
	height: auto;
}
.form-horizontal .checkbox-inline{
	padding: 0px;
}
.lie1-font {
	
	width: 1200px;
	height: 35px;
	line-height: 35px;
	font-weight: bold;
	border-bottom: 1px;
	border-bottom-color: #9dc7e1;
	border-bottom-style: solid;
	color: #5d86b9;
	font-weight: bold;
	font-size: 20px;
}
s{color: red;padding-right: 5px;text-decoration: none;}
.tooltip{width: 200px;}
.tooltip-width .tooltip{width: 120px;}
.btn-qr {
	display: inline-block;
	margin-bottom: 0px;
	cursor: pointer;
	border: 1px solid rgb(204, 204, 204);
	border-radius: 4px;
	white-space: nowrap;
	vertical-align: middle;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857;
	text-align: center;
	color: white;
	background-color: #449d44;
	padding: 6px 12px;
	height: 32px;
}
</style>
</head>
<body>
	<jsp:include page="../top.jsp"></jsp:include>
	<div class="container" style="margin-top: 20px;">
		<input type="hidden" id="lineId" value="${lineId}">
		<input type="hidden" id="userId" value="${user_session.id}">
		<input type="hidden" id="token" value="${token}">
		<div class="row">
			<div class="col-md-12 lie1-font">发货人信息</div>
		</div>
		<form role="form" class="form-horizontal con1">  
            <div class="form-group">  
                <label for="" class="col-sm-2 control-label"><s>*</s>发货人</label>  
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="consignorName" name="consignorName" placeholder="请输入发货人" onblur="validRequiredStyle(this)"/>  
                </div>
                <div class="col-sm-1" style="width: 70px;">
	            	<button class="btn btn-default btn-sm" onclick="loadCommonContact(0)" type="button" data-toggle="modal" data-target="#commonContactModal">选择</button>
	            </div>
                <div class="col-sm-2 checkbox">
                  	<label><input type="checkbox" name="isCommonConsignor" value="1">保存为常用联系人</label>
                </div>
                <div class="col-sm-3">
                     <label id="consignorNameValid" class="control-label" style="color: red;"></label>
                </div>
            </div>
        </form>
        <form role="form" class="form-horizontal con1">
			<div class="form-group">
				<label class="col-sm-2 control-label">地址</label>
				<label class="col-sm-10 control-label" style="text-align: left;">
					${startOutlets.provinceValue} &nbsp;
					${startOutlets.cityValue} &nbsp;
					<select id="consignorCountySelect" name="consignorCountySelect" class="form-control" style="display: inline; width: 200px;" onchange="document.getElementById('consignorCounty').value = this.value;sendTypeSetting()" onblur="validRequiredStyle(this)"></select>
					<span id="consignorCountySelectValid" class="control-label" style="color: red;"></span>
				</label>
				<input type="hidden" name="consignorProvince" value="${startOutlets.province}">
				<input type="hidden" name="consignorCity" value="${startOutlets.city}">
				<input type="hidden" name="consignorCounty" id="consignorCounty">
			</div>
		</form>
        <form role="form" class="form-horizontal con1">
			<div class="form-group">
				<label class="col-sm-2 control-label"><s>*</s>详细地址</label>
				<div class="col-sm-3">
					<input type="text" id="consignorAddress" name="consignorAddress" class="form-control" placeholder="请输入详细地址" onblur="validRequiredStyle(this)" />
				</div>
				<div class="col-sm-3">
                     <label id="consignorAddressValid" class="control-label" style="color: red;"></label>
                </div>
			</div>
		</form>
		<form role="form" class="form-horizontal con1">
			<div class="form-group">
				<label class="col-sm-2 control-label"><s>*</s>手机号</label>
				<div class="col-sm-3">
					<input type="text" id="consignorPhoneNumber" name="consignorPhoneNumber" class="form-control" maxlength="11" placeholder="请输入手机号" onblur="validRequiredStyle(this), validMobileStyle(this)"/>
				</div>
				<div class="col-sm-3">
                     <label id="consignorPhoneNumberValid" class="control-label" style="color: red;"></label>
                </div>
			</div>
		</form>
		<form role="form" class="form-horizontal con1">
			<div class="form-group">
				<label class="col-sm-2 control-label">固定电话</label>
				<div class="col-sm-3">
					<input type="text" id="consignorTelephone" name="consignorTelephone" class="form-control" maxlength="12" placeholder="固定电话(028-88888888)" onblur="validTelePhoneStyle(this)"/>
				</div>
				<div class="col-sm-3">
                     <label id="consignorTelephoneValid" class="control-label" style="color: red;"></label>
                </div>
			</div>
		</form>
		<div class="row con1">
			<div class="col-md-12 lie1-font">收货人信息</div>
		</div>
		<form role="form" class="form-horizontal con1">  
            <div class="form-group">
                <label for="" class="col-sm-2 control-label"><s>*</s>收货人</label>
                <div class="col-sm-3">
                    <input type="text" id="consigneeName" name="consigneeName" class="form-control"  placeholder="请输入收货人" onblur="validRequiredStyle(this)"/>  
                </div>
                <div class="col-sm-1" style="width: 70px;">
                	<button class="btn btn-default btn-sm" onclick="loadCommonContact2(0)" type="button" data-toggle="modal" data-target="#commonContactModal2">选择</button>
	            	
                </div>
                <div class="col-sm-2 checkbox">
                  	<label><input type="checkbox" name="isCommonConsignee" value="1">保存为常用联系人</label>
                </div>
                <div class="col-sm-3">
                     <label id="consigneeNameValid" class="control-label" style="color: red;"></label>
                </div>
            </div>  
        </form>
        <form role="form" class="form-horizontal con1">
			<div class="form-group">
				<label class="col-sm-2 control-label">地址</label>
				<label class="col-sm-10 control-label" style="text-align: left;">
					${endOutlets.provinceValue} &nbsp;
					${endOutlets.cityValue} &nbsp;
					<select id="consigneeCountySelect" name="consigneeCountySelect" class="form-control" style="display: inline; width: 200px;" onchange="document.getElementById('consigneeCounty').value = this.value" onblur="validRequiredStyle(this)"></select>
					<span id="consigneeCountySelectValid" class="control-label" style="color: red;"></span>
				</label>
				
				<input type="hidden" name="consigneeProvince" value="${endOutlets.province}">
				<input type="hidden" name="consigneeCity" value="${endOutlets.city}">
				<input type="hidden" name="consigneeCounty" id="consigneeCounty">
			</div>
		</form>
		<form role="form" class="form-horizontal con1">  
            <div class="form-group">  
                <label for="" class="col-sm-2 control-label"><s>*</s>详细地址</label>  
                <div class="col-sm-3">
                    <input type="text" id="consigneeAddress" name="consigneeAddress" class="form-control"  placeholder="请输入详细地址" onblur="validRequiredStyle(this)"/>  
                </div>
                <div class="col-sm-3">
                     <label id="consigneeAddressValid" class="control-label" style="color: red;"></label>
                </div>
            </div>
        </form>
		<form role="form" class="form-horizontal con1">  
            <div class="form-group">  
                <label for="" class="col-sm-2 control-label"><s>*</s>手机号</label>  
                <div class="col-sm-3">  
                    <input type="text" id="consigneePhoneNumber" name="consigneePhoneNumber" class="form-control" maxlength="11" placeholder="请输入手机号" onblur="validRequiredStyle(this), validMobileStyle(this)"/>  
                </div>
                <div class="col-sm-3">
                     <label id="consigneePhoneNumberValid" class="control-label" style="color: red;"></label>
                </div>
             </div>
        </form>
        <form role="form" class="form-horizontal con1">  
            <div class="form-group">  
                <label for="" class="col-sm-2 control-label">固定电话</label>  
                <div class="col-sm-3">  
                    <input type="text" id="consigneeTelephone" name="consigneeTelephone" class="form-control" maxlength="12" placeholder="固定电话(028-88888888)" onblur="validTelePhoneStyle(this)"/>  
                </div>
                <div class="col-sm-3">
                     <label id="consigneeTelephoneValid" class="control-label" style="color: red;"></label>
                </div>
            </div>
        </form>
		<div class="row">
			<div class="col-md-12 lie1-font">货物信息</div>
		</div>
		<div class="row con1">
			<button class="btn btn-default btn-sm" onclick="loadCommonCargo(0)" data-toggle="modal" data-target="#myModal">选择常用货物</button>
			<button class="btn btn-default btn-sm" onclick="addCargoEditLine()">添加</button>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">常发货物</h4>
		         </div>
		         <div class="modal-body">
		         	<table class="table" id="commonCargoTable" style="margin-bottom: 0px;border-top: 0px;">
		         	</table>
		         	<div style="position: relative; height: 30px;">
		         		<div style="position: absolute; right: 10px;">
		         			<button class="btn btn-default btn-sm" onclick="loadCommonCargo(-1)">上一页</button>
			         		<div id="pageIndex" style="display: inline-block; padding: 1px 3px;"></div>/
			         		<div id="totalPage" style="display: inline-block; padding: 1px 3px;"></div>
			         		<button class="btn btn-default btn-sm" onclick="loadCommonCargo(1)">下一页</button>
		         		</div>
		         	</div>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="choicCarog()">确定</button>
		         </div>
		      </div>
			</div>
		</div>
		<div class="row col-md-12 con1">
			<table class="table col-md-12 text-center" id="cargoTable">
				<thead>
					<tr>
						<th class="text-center">货物名称</th>
						<th class="text-center">货物品牌</th>
						<th class="text-center">货物型号</th>
						<th class="text-center">货物类型</th>
						<th class="text-center">件数</th>
						<th class="text-center">套数</th>
						<th class="text-center">重量(t)</th>
						<th class="text-center">体积(m³)</th>
						<th class="text-center">包装信息</th>
						<th class="text-center">设为常用</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="edit-table-input" value="" onblur="showValidNullTip(this)"></td>
						<td><input type="text" class="edit-table-input" value=""></td>
						<td><input type="text" class="edit-table-input" value=""></td>
						<!-- 货物类型 -->
						<td>
							<select name="packageInfo" class="edit-table-select">
								<c:forEach items="${goodsTypes}" var="goodsType">
									<option value="${goodsType.id}">${goodsType.softName}</option>
								</c:forEach>
							</select>
						</td>
						<td><input type="text" class="edit-table-input" value="" onblur="showValidIntTip(this), countTotalWeightAndVolume()"></td>
						<td><input type="text" class="edit-table-input" value="" onblur="showValidIntTip(this)"></td>
						<td><input type="text" class="edit-table-input" value="" onblur="showValidNullTip(this), showValidNumberTip(this), countTotalWeightAndVolume()"></td>
						<td><input type="text" class="edit-table-input" value="" onblur="showValidNullTip(this), showValidNumberTip(this), countTotalWeightAndVolume()"></td>
						<td>
							<select name="packageInfo" class="edit-table-select">
								<c:forEach items="${packageTypes}" var="packageType">
									<option value="${packageType.id}">${packageType.name}</option>
								</c:forEach>
							</select>
						</td>
						<td><input type="checkbox" value="1"></td>
						<td><a href="javascript://" style="text-decoration: none;" onclick="deleteCargo(this)">删除</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<form role="form" class="form-horizontal con1">
            <div class="form-group">
            	<div class="col-sm-4">
            		<label class="col-sm-4 control-label">预估重量</label>
            		<label id="estimateWeightText" class="control-label" style="font-weight: normal;">0</label> 吨
				    <input type="hidden" name="estimateWeight" id="estimateWeight" value="0">
            	</div>
            	<div class="col-sm-4">
            		<label class="col-sm-4 control-label">预估体积</label>
            		<label id="estimateVolumeText" class="control-label" style="font-weight: normal;">0</label> 立方米
				    <input type="hidden" name="estimateVolume" id="estimateVolume" value="0">
            	</div>
            	<div class="col-sm-4">
            		<label class="col-sm-4 control-label">预估基础运费</label>
            		<label id="estimateFreightText" class="control-label" style="font-weight: normal;">0</label> 元
				    <input type="hidden" name="estimateFreight" id="estimateFreight" value="0">
            	</div>
            </div>
            <input type="hidden" id="heavyCargoPriceLow" value="${lineInfo.heavyCargoPriceLow}">
            <input type="hidden" id="heavyCargoPriceMid" value="${lineInfo.heavyCargoPriceMid}">
            <input type="hidden" id="heavyCargoPriceHigh" value="${lineInfo.heavyCargoPriceHigh}">
            <input type="hidden" id="bulkyCargoPriceLow" value="${lineInfo.bulkyCargoPriceLow}">
            <input type="hidden" id="bulkyCargoPriceMid" value="${lineInfo.bulkyCargoPriceMid}">
            <input type="hidden" id="bulkyCargoPriceHigh" value="${lineInfo.bulkyCargoPriceHigh}">
        </form>
		<div class="row con1">
			<label class="lie1-font control-label col-sm-12">配送方式</label>
		</div>
		<form role="form" class="form-horizontal con1">  
			<div class="form-group">
				<div class="row">
					<label class="col-sm-2 control-label">发货方式</label>
				 	<div class="radio col-sm-2" style="width: 120px;">
					   <label>
					      <input type="radio" id="home_deliver" name="sendCargoType" value="1" checked="checked" disabled="disabled" onclick="showAppointmentTime(this)">上门取货 
					   </label>
					</div>
					<div class="col-sm-8 control-label" id="door_pick_up" style="text-align: left; display: none;">
						<label>预约提货：</label>
						<input type="text" name="deliveryDate" id="deliveryDate">
						<input type="text" name="deliveryStartTime" id="deliveryStartTime" style="width: 60px;">至
						<input type="text" name="deliveryEndTime" id="deliveryEndTime" style="width: 60px;">
					</div>
				</div>
			 	<div class="row">
			 		<div class="radio col-sm-2 col-sm-offset-2" style="width: 120px;">
					   <label>
					      <input type="radio" id="self-deliver" name="sendCargoType" value="0" checked="checked" onchange="showOutletsAddress()">自送网点
					   </label>
					</div>
					<div id="self_delivery_outlets" class="col-sm-8 control-label" id="self_delivery_outlets" style="text-align: left;">
						<label>网点地址：</label>
						<span>${startOutlets.provinceValue}${startOutlets.cityValue}${startOutlets.countyValue}${startOutlets.address}</span>
					</div>
			 	</div>
			</div>
		</form>
		<form role="form" class="form-horizontal con1">  
			<div class="form-group">
				<div class="row">
					<label class="col-sm-2 control-label">收货方式</label>
				 	<div class="radio col-sm-2" style="width: 120px;">
					   <label>
					      <input type="radio" name="receiveCargoType" value="1">送货上门 
					   </label>
					</div>
				</div>
				<div class="row">
			 		<div class="radio col-sm-2 col-sm-offset-2" style="width: 120px;">
					   <label>
					      <input type="radio" name="receiveCargoType" value="0" checked="checked">客户自提
					   </label>
					</div>
			 	</div>
			</div>
		</form>
		<div class="row">
			<div class="col-md-12 lie1-font">
				城配司机
				<!-- <button class="btn btn-default btn-sm" type="button" id="cityDriverControlBtn" onclick="controlCityDriverBox(this)">添加</button>
				 -->
			</div>
		</div>
		<div id="cityDriverBox">
			<form role="form" class="form-horizontal con1">  
	            <div class="form-group">
	                <label for="" class="col-sm-2 control-label">姓名</label>  
	                <div class="col-sm-3">
	                    <input type="text" id="driverName" name="driverName" class="form-control"  placeholder="请输入姓名"/>  
	                </div>
	                <div class="col-sm-1" style="width: 70px;">
		            	<button class="btn btn-default btn-sm" onclick="loadCommonDriver(0)" type="button" data-toggle="modal" data-target="#commonDriverModal">选择</button>
		            </div>
	                <div class="col-sm-2 checkbox">
	                  	<label><input type="checkbox" id="isCommonDriver" name="isCommonDriver" value="1">保存为常用司机</label>
	                </div>
	                <div class="col-sm-3">
	                     <label id="driverNameValid" class="control-label" style="color: red;"></label>
	                </div>
	            </div>
	        </form>
	        <form role="form" class="form-horizontal con1">  
	            <div class="form-group">  
	                <label for="" class="col-sm-2 control-label">手机号</label>  
	                <div class="col-sm-3">
	                    <input type="text" id="driverPhone" name="driverPhone" class="form-control"  placeholder="请输入电话" maxlength="11" onblur="validAloneMobileStyle(this)"/>  
	                </div>
	                <div class="col-sm-3">
	                     <label id="driverPhoneValid" class="control-label" style="color: red;"></label>
	                </div>
	            </div>
	        </form>
	        <form role="form" class="form-horizontal con1">  
	            <div class="form-group">  
	                <label for="" class="col-sm-2 control-label">车牌号</label>  
	                <div class="col-sm-3">
	                    <input type="text" id="vehicleNumber" name="vehicleNumber" class="form-control"  placeholder="请输入车牌号" onblur="validVehicleNumberStyle(this)"/>  
	                </div>
	                <div class="col-sm-3">
	                     <label id="vehicleNumberValid" class="control-label" style="color: red;"></label>
	                </div>
	            </div>
	        </form>
	        <form role="form" class="form-horizontal con1">  
	            <div class="form-group">  
	                <label for="" class="col-sm-2 control-label">车型</label>  
	                <div class="col-sm-3">
	                    <select id="vehicleType" name="vehicleType" class="form-control">
							<c:forEach items="${vehicleTypes}" var="vehicleType">
								<option value="${vehicleType.id}">${vehicleType.name}</option>
							</c:forEach>
						</select>
	                </div>
	            </div>
	        </form>
		</div>
		<div class="row con1">
			<label class="lie1-font control-label col-sm-12">增值服务</label>
		</div>
		<form class="from-horizontal con1">
			<div class="form-group">
				<div class="row">
					<div class="col-sm-3 col-sm-offset-1">
						<div class="checkbox-inline">
							<input type="checkbox" name="isReceipt" value="1" checked="checked">回单
						</div>
					</div>
					<div class="col-sm-2">
						<div class="checkbox-inline">
						    <input type="checkbox" checked="checked" disabled="disabled">综合信息服务
						</div>
					</div>
					<div class="col-sm-2 text-right">
						<div class="checkbox-inline">
							<c:if test="${additionalServerConf.isLoadAndUnload == 1}">
								<input type="checkbox" name="isLoadCargo" onchange="showFloorElevator(this, 1)" value="1">
								<span data-toggle="tooltip" data-placement="right" title="不上楼：重货：${additionalServerConf.heavyCargo}元/吨<br/>不上楼：泡货：${additionalServerConf.bulkyCargo}元/立方米<br/>不上楼：最低收费：${additionalServerConf.noUpstairsLowPrice}元<br/>上楼：有电梯加收：${additionalServerConf.elevatorAdditional}%<br/>上楼：无电梯每层加收：${additionalServerConf.noElevatorAdditional}%<br/>上楼：最低收费：${additionalServerConf.goUpstairsLowPrice}元<br/>" style="color: blue;">上门取货装货</span>
							</c:if>
							<c:if test="${empty additionalServerConf.isLoadAndUnload || additionalServerConf.isLoadAndUnload == 0}">
								<input type="checkbox" name="isLoadCargo" disabled="disabled" value="1"><span style="color: blue;">上门取货装货</span>
							</c:if>
						</div>
					</div>
					<div class="col-sm-3" id="loadFloor" style="display: none;">
						<div class="checkbox-inline">
							<span>所在楼层</span><input type="text" name="loadCargoFloor" style="font-size: 12px; width: 60px;">
						</div>
						<div class="checkbox-inline">
							<input type="checkbox" name="loadCargoIsElevator" value="1">电梯
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3 col-sm-offset-1">
						<div class="checkbox-inline tooltip-width">
							<c:if test="${additionalServerConf.isCollectionDelivery == 1}">
								<input type="checkbox" name="isCollectionDelivery" value="1" onchange="showCollectDelivery(this)"><span style="color: blue;">代收款</span>
							</c:if>
							<c:if test="${empty additionalServerConf.isCollectionDelivery || additionalServerConf.isCollectionDelivery == 0}">
								<input type="checkbox" name="isCollectionDelivery" value="1" disabled="disabled"><span style="color: blue;">代收款</span>
							</c:if>
							<div id="collectDeliveryDiv" style="display: none;">
								<span>(${additionalServerConf.collectionDeliveryRatio}%)</span>
								<input type="text" name="collectionDeliveryMoney" onblur="validNumber(this)" style="font-size: 12px; width: 60px;" />	
						    	<span>元</span>
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="checkbox-inline">
							<input type="checkbox" name="isWaitPay" value="1">等款放货
						</div>
					</div>
					<div class="col-sm-2 text-right">
						<div class="checkbox-inline">
							<c:if test="${additionalServerConf.isLoadAndUnload == 1}">
								<input type="checkbox" name="isUnloadCargo" value="1" onchange="showFloorElevator(this, 2)">
						    	<span data-toggle="tooltip" data-placement="right" title="不上楼：重货：${additionalServerConf.heavyCargo}元/吨<br/>不上楼：泡货：${additionalServerConf.bulkyCargo}元/立方米<br/>不上楼：最低收费：${additionalServerConf.noUpstairsLowPrice}元<br/>上楼：有电梯加收：${additionalServerConf.elevatorAdditional}%<br/>上楼：无电梯每层加收：${additionalServerConf.noElevatorAdditional}%<br/>上楼：最低收费：${additionalServerConf.goUpstairsLowPrice}元<br/>" style="color: blue;">送货上门卸货</span>
							</c:if>
							<c:if test="${empty additionalServerConf.isLoadAndUnload || additionalServerConf.isLoadAndUnload == 0}">
								<input type="checkbox" name="isUnloadCargo" value="1" disabled="disabled"><span style="color: black;">送货上门卸货</span>
							</c:if>
						</div>
					</div>
					<div class="col-sm-3" id="unloadFloor" style="display: none;">
						<div class="checkbox-inline">
							<span>所在楼层</span><input type="text" name="unloadCargoFloor" style="font-size: 12px; width: 60px;">
						</div>
						<div class="checkbox-inline">
							<input type="checkbox" name="loadCargoIsElevator" value="1">电梯
						</div>
					</div>
				</div>
			</div>   
		</form>
		<div class="row con1">
			<label class="lie1-font control-label col-sm-12">货物保险</label>
		</div>
		<form class="form-horizontal con1">
			<div class="row">
				<div class="col-sm-3 col-sm-offset-1">
					<div class="checkbox-inline">
						<c:if test="${additionalServerConf.isLineInsurance == 1}">
							<input type="checkbox" name="isInsurance" id="isInsurance" checked="checked" value="1" onclick="checkedLineInsurance(this)">专线投保(<a href="../platform-insurance.html" target="_blank" style="text-decoration: none;">投保说明</a>)
						</c:if>
						<c:if test="${empty additionalServerConf.isLineInsurance || additionalServerConf.isLineInsurance == 0}">
							<input type="checkbox" name="isInsurance" id="isInsurance" value="1" disabled="disabled">专线投保(投保说明)
						</c:if>
					</div>
				</div>
				<div class="col-sm-6">
					<c:if test="${additionalServerConf.isLineInsurance == 1}">
						<span>投保金额(费率：${additionalServerConf.lineInsuranceRatio}%)</span>
						<div class="checkbox-inline">
							<input type="text" name="insuranceMoney" id="insuranceMoney" class="form-control custom-input" placeholder="投保金额" onblur="validNumber(this)" style="width: 100px;"/>
						</div>
					</c:if>
					<c:if test="${empty additionalServerConf.isLineInsurance || additionalServerConf.isLineInsurance == 0}">
						<span>投保金额</span>
						<div class="checkbox-inline">
							<input type="text" name="insuranceMoney" id="insuranceMoney" class="form-control custom-input" placeholder="投保金额" disabled="disabled" style="width: 100px;"/>
						</div>
					</c:if>
					<span>元</span>
					<small style="color: #ccc;">(提示：投保金额不得大于货物本身价格)</small>
				</div>
			</div>
        </form>
		<div class="row con1">
			<label class="lie1-font control-label col-sm-12">发票信息</label>
		</div>
		<form class="from-horizontal con1">
			<div class="form-group">
				<div class="row">
					<c:if test="${additionalServerConf.isCommonReceipt == 1}">
						<div class="col-sm-3 col-sm-offset-1">
							<div class="radio-inline">
								<input type="radio" name="receiptType" value="1" onchange="showReceiptTitle()">普通发票(费率：${additionalServerConf.commonReceiptRate}%)
							</div>
						</div>
					</c:if>
					<c:if test="${additionalServerConf.isAddTaxReceipt == 1}">
						<div class="col-sm-3">
							<div class="radio-inline">
								<input type="radio" name="receiptType" value="2" onchange="hideReceiptTitle()">增值税发票(费率：${additionalServerConf.addTaxReceiptRate}%)
							</div>
						</div>
					</c:if>
					<div class="col-sm-3">
						<div class="radio-inline">
							<input type="radio" name="receiptType" checked="checked" value="0" onchange="hideReceiptTitle()">不需要
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-8 col-sm-offset-1" id="receiptTitle" style="display: none;">
						<span>发票抬头：</span>
						<div class="radio-inline">
							<input id="receiptTitlePerson" type="radio" name="receiptTitle" checked="checked" value="0" onchange="hideReceiptCompanyName()">个人
						</div>
						<div class="radio-inline">
							<input type="radio" name="receiptTitle" value="1" onchange="showReceiptCompanyName()">单位
						</div>
						<div class="radio-inline" id="receiptCompanyName" style="display: none;">
							<input type="text" name="receiptCompanyName" class="form-control" style="padding: 2px 3px; height: auto;">
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row con1">
			<label class="lie1-font control-label col-sm-12">支付方式</label>
		</div>
		<form class="from-horizontal con1">
			<div class="form-group">
				<div class="row">
					<c:if test="${additionalServerConf.isImmediatePay == 1}">
						<div class="col-sm-3 col-sm-offset-1">
							<div class="radio-inline">
								<input type="radio" id="onlinePay" name="payType" checked="checked" value="0">在线支付
							</div>
						</div>
					</c:if>
					<div class="col-sm-3">
						<div class="radio-inline">
							<c:if test="${empty additionalServerConf.isImmediatePay or additionalServerConf.isImmediatePay == 0}">
								<input type="radio" name="payType" value="1" checked="checked" onclick="selectArrivePay(this)">到付	
							</c:if>
							<c:if test="${additionalServerConf.isImmediatePay == 1}">
								<input type="radio" name="payType" value="1" onclick="selectArrivePay(this)">到付	
							</c:if>
						</div>
					</div>
					<%-- <c:if test="${additionalServerConf.isArrivePay == 1}">
						
					</c:if> --%>
					<!-- <div class="col-sm-3">
						<div class="radio-inline">
							<input type="radio" name="payType" value="2">使用预付款
						</div>
					</div> -->
				</div>
			</div>   
        </form>
        <div class="row" style="margin: 20px auto;">
        	<div class="col-sm-1 col-sm-offset-1">
        		<button type="button" class="btn btn-default" onclick="orderSubmit()">提交</button>
        	</div>
        	<div class="col-sm-1">
        		<button type="button" class="btn btn-default">取消</button>
        	</div>
        </div>
	</div>

	<div class="modal fade" id="commonContactModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">常用联系人</h4>
	         </div>
	         <div class="modal-body">
	         	<table class="table" id="commonContactTable" style="margin-bottom: 0px;border-top: 0px;"></table>
	         	<div style="position: relative; height: 30px;">
	         		<div style="position: absolute; right: 10px;">
	         			<button class="btn btn-default btn-sm" onclick="loadCommonContact(-1)">上一页</button>
		         		<div id="contactPageIndex" style="display: inline-block; padding: 1px 3px;"></div>/
		         		<div id="contactTotalPage" style="display: inline-block; padding: 1px 3px;"></div>
		         		<button class="btn btn-default btn-sm" onclick="loadCommonContact(1)">下一页</button>
	         		</div>
	         	</div>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="choicCommonContact()">确定</button>
	         </div>
	      </div>
		</div>
	</div>
	<div class="modal fade" id="commonContactModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">常用联系人</h4>
	         </div>
	         <div class="modal-body">
	         	<table class="table" id="commonContactTable2" style="margin-bottom: 0px;border-top: 0px;"></table>
	         	<div style="position: relative; height: 30px;">
	         		<div style="position: absolute; right: 10px;">
	         			<button class="btn btn-default btn-sm" onclick="loadCommonContact2(-1)">上一页</button>
		         		<div id="contactPageIndex2" style="display: inline-block; padding: 1px 3px;"></div>/
		         		<div id="contactTotalPage2" style="display: inline-block; padding: 1px 3px;"></div>
		         		<button class="btn btn-default btn-sm" onclick="loadCommonContact2(1)">下一页</button>
	         		</div>
	         	</div>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="choicCommonContact2()">确定</button>
	         </div>
	      </div>
		</div>
	</div>
	<div class="modal fade" id="commonDriverModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">常用司机</h4>
	         </div>
	         <div class="modal-body">
	         	<table class="table" id="commonDriverTable" style="margin-bottom: 0px;border-top: 0px;"></table>
	         	<div style="position: relative; height: 30px;">
	         		<div style="position: absolute; right: 10px;">
	         			<button class="btn btn-default btn-sm" onclick="loadCommonDriver(-1)">上一页</button>
		         		<div id="driverPageIndex" style="display: inline-block; padding: 1px 3px;"></div>/
		         		<div id="driverTotalPage" style="display: inline-block; padding: 1px 3px;"></div>
		         		<button class="btn btn-default btn-sm" onclick="loadCommonDriver(1)">下一页</button>
	         		</div>
	         	</div>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="choicCommonDriver()">确定</button>
	         </div>
	      </div>
		</div>
	</div>
	<div id="payModal" class="modal fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-horizontal form-bordered" id="orderPayForm" target="_blank" action="/logisticsc/orderCenter/doOrderMoney.shtml" method="post">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">支付预约费</h4>
						<a class="close" data-dismiss="modal" style="margin-top: -23px;"><span onclick="tiaozhuan()">×</span></a>
					</div>
					<div class="modal-body add-role-body">
						<input type="hidden" id="orderNumber" name="orderNumber">
						<div class="form-group" style="margin-top: 20px;">
							<label class="control-label col-sm-4">支付金额：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="money" name="amount" readonly="readonly" style="width: 180px;" />
								<input type="text" id="orderType" name="orderType" value="1" style="display: none;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4">
								<input type="radio" checked="checked">
								易宝支付：</label>
							<div class="col-sm-8" style="line-height: 30px; padding-top: 5px;">
								<span class="control-label" style="font-size: 14px;">(通过易宝支付完成在线支付)</span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn-qr" value="确认支付" onclick="wancheng()"/>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="querenModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">登录网上银行支付</h4>
              		<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="shuaxin()">×</span></a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #6f9fd9!important;font-weight:bold">温馨提示:<span id="querenMsg">请您在新打开的网上银行页面进行支付，支付完成前请不要关闭该窗口</span></h5>
                </div>
               <div class="modal-footer">
                	<button type='button' class='btn btn-success' style='margin-top:5px;' onclick="shuaxin()">完成支付</button>
                </div>
            </div>
        </div>
    </div>
    <div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				 <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
			</div>
			<div class="modal-body success-info">
				 <h5 style="color: #6f9fd9!important;font-weight:bold">成功提示:<span id="successModalMsgs"></span></h5>
			</div>
		</div>
	</div>
</div>
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" style="color:#333!important;" id="myModalLabel">温馨提示！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
<div id="errorModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: red !important;">操作失败！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: red !important;font-weight:bold">错误提示:<span id="errorModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
	<div style="clear: both;"></div>
	
	<input type="hidden" id="startSelectCity" value="${startOutletsAdd.city}">
	<input type="hidden" id="startSelectCounty" value="${startOutletsAdd.county}">
	<input type="hidden" id="endSelectCity" value="${endOutletsAdd.city}">
	<input type="hidden" id="endSelectCounty" value="${endOutletsAdd.county}">
	
	<jsp:include page="../bottom.jsp"></jsp:include>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js" ></script>
	<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.js"></script>
	<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
	function wancheng() {
		$("#payModal").modal('hide');
		$("#querenModal").modal('show');
	}
	function shuaxin() {
		window.location.href="/logisticsc/orderCenter/toorderlistpage.shtml";
	}
	var editLineHtml = null;
	var mobilePartten = /^\d{11}$/;
	var telephonePartten = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	var vehicleNumberPartten = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	var isValid = false;
	
	$(function(){
		buildCargoEditLineHtml();
		loadcalander();
		$('[data-toggle="tooltip"]').tooltip({'html':true});
		sendTypeSetting();
		loadConsignorAddress();
	});
	
	function validRequiredStyle(obj){
		var name = $(obj).attr('name');
		if(obj.value == null || obj.value == '' || typeof(obj.value) == 'undefined'){
            obj.style.borderColor = 'red';
            $('#'+name+'Valid').text('不能为空');
        }else{
            obj.style.borderColor = '#ccc';
            $('#'+name+'Valid').text('');
        }
	}
	
	function validMobileStyle(obj){
		var name = $(obj).attr('name');
		var value = obj.value;
		if(value != null && value != '' && typeof(value) != 'undefined'){
			if(!mobilePartten.test(obj.value)){
	            obj.style.borderColor = 'red';
	            $('#'+name+'Valid').text('手机号不正确');
	        }else{
	        	obj.style.borderColor = '#ccc';
	            $('#'+name+'Valid').text('');
	        }
		}
	}
	
	function validTelePhoneStyle(obj){
		var name = $(obj).attr('name');
		var value = obj.value;
		if(value != null && value != '' && typeof(value) != 'undefined'){
			if(!telephonePartten.test(obj.value)){
	            obj.style.borderColor = 'red';
	            $('#'+name+'Valid').text('固定电话格式不正确');
	        }else{
	        	obj.style.borderColor = '#ccc';
	            $('#'+name+'Valid').text('');
	        }
		}else{
			obj.style.borderColor = '#ccc';
            $('#'+name+'Valid').text('');
		}
	}
	
	function validAloneMobileStyle(obj){
		var name = $(obj).attr('name');
		var value = obj.value;
		if(value != null && value != '' && typeof(value) != 'undefined'){
			if(!mobilePartten.test(obj.value)){
	            obj.style.borderColor = 'red';
	            $('#'+name+'Valid').text('手机号不正确');
	        }else{
	        	obj.style.borderColor = '#ccc';
	            $('#'+name+'Valid').text('');
	        }
		}else{
			obj.style.borderColor = '#ccc';
            $('#'+name+'Valid').text('');
		}
	}
	
	// 验证车牌号
	function validVehicleNumberStyle(obj){
		var name = $(obj).attr('name');
		var value = obj.value;
		if(value != null && value != '' && typeof(value) != 'undefined'){
			if(!vehicleNumberPartten.test(obj.value)){
	            obj.style.borderColor = 'red';
	            $('#'+name+'Valid').text('车牌号格式不正确');
	        }else{
	        	obj.style.borderColor = '#ccc';
	            $('#'+name+'Valid').text('');
	        }
		}else{
			obj.style.borderColor = '#ccc';
            $('#'+name+'Valid').text('');
		}
	}
	
	// 控制城配司机 显示/隐藏
	function controlCityDriverBox(obj){
		var text = $(obj).text();
		if(text == '添加'){
			var sendCargoType = $("input[name='sendCargoType']:checked").val();
			if(sendCargoType != 0){
				$("#promptModal").modal('show');
				$("#promptModalMsgs").html("发货方式为自送网点，可填写城配司机信息");
				return;
			}
			$(obj).text('关闭');
			$('#cityDriverBox').show();
		}else if(text == '关闭'){
			$(obj).text('添加');
			$('#driverName').val('');
			$('#driverPhone').val('');
			validAloneMobileStyle(document.getElementById('driverPhone'));
			$('#vehicleNumber').val('');
			$('#isCommonDriver').attr('checked',false);
			$('#cityDriverBox').hide();
		}
	}
	
	// 删除货物
	function deleteCargo(obj){
		var data = buildCargoInfoData();
		if(data.length <= 1){
			$("#promptModal").modal('show');
			$("#promptModalMsgs").html("不能没有货物");
		}else{
			if(confirm('确定要删除吗？')){
				$(obj).parent().parent().remove();
			}
		}
	}
	
	// 添加货物编辑行
	function addCargoEditLine(){
		$('#cargoTable tbody').append(editLineHtml);
	}
	
	// 构建 货物信息行编辑html
	function buildCargoEditLineHtml(){
		editLineHtml = $('#cargoTable tbody').html();
	}
	
	// 获取总价值， 设置投保金额
	function getTotalWorth(obj){
		var value = obj.value;
		if(value != null && value != ''){
			if(isNaN(value)){
				obj.value = '';
			}else{
				var checked = $('#isInsurance').get(0).checked;
				if(checked){
					$('#insuranceMoney').val(value);
					$('#insuranceMoney').attr('disabled','disabled')
				}
			}
		}else{
			$('#insuranceMoney').val('');
			var checked = $('#isInsurance').get(0).checked;
			if(checked){
				$('#insuranceMoney').removeAttr('disabled')
			}
		}
	}
	
	// 是否选择专线投保
	function checkedLineInsurance(obj){
		if(obj.checked){
			var totalWorth = $('#totalWorth').val();
			if(totalWorth != null && totalWorth != '' && typeof(totalWorth) != 'undefined'){
				$('#insuranceMoney').val(totalWorth);
				$('#insuranceMoney').attr('disabled','disabled')
			}else{
				$('#insuranceMoney').removeAttr('disabled')
			}
		}else{
			$('#insuranceMoney').val('');
			$('#insuranceMoney').attr('disabled','disabled')
		}
	}
	
	function loadcalander(){
		$("#deliveryDate").datetimepicker({
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
			minView: 2,
			autoclose:true //选择日期后自动关闭 
		});
		$("#deliveryStartTime").datetimepicker({
			format: 'hh:00',
			language: 'zh-CN',
			startView: 1,
			minView: 1,
			autoclose:true //选择日期后自动关闭 
		});
		$("#deliveryEndTime").datetimepicker({
			format: 'hh:00',
			language: 'zh-CN',
			startView: 1,
			minView: 1,
			autoclose:true //选择日期后自动关闭 
		});
	}
	
	/* // 改变件数  设置货物 总重量/总体积
	function setTotalWeightAndVolume(){
		setTotalWeight();
		setTotalVolume();
	}
	
	// 计算 设置货物总重量
	function setTotalWeight(){
		var totalWeight = 0;
		
		var numberObj = $("input[name='singleNumber']");
		var weightObj = $("input[name='singleWeight']");
		for(var i = 0; i < weightObj.length; i++){
			totalWeight += new Number(weightObj[i].value) * new Number(numberObj[i].value);
		}
		$('#totalWeight').val(totalWeight);
	}
	
	// 计算 设置货物总体积
	function setTotalVolume(){
		var totalVolume = 0;
		var numberObj = $("input[name='singleNumber']");
		var volumeObj = $("input[name='singleVolume']");
		for(var i = 0; i < volumeObj.length; i++){
			totalVolume += new Number(volumeObj[i].value) * new Number(numberObj[i].value);
		}
		$('#totalVolume').val(totalVolume);
	} */
	
	// 选择上门取货 显示预约时间
	function showAppointmentTime(obj){
		var payType = $("input[name='payType']:checked").val();
		if(payType == 1){
			$("#promptModal").modal('show');
			$("#promptModalMsgs").html("付款方式为到付，不能选择上门取货");
			obj.checked = false;
			document.getElementById('self-deliver').checked = true;
			return;
		}
		$('#door_pick_up').show();
		$('#self_delivery_outlets').hide();
		
		// 隐藏城配司机
		//$('#cityDriverControlBtn').text('添加');
		$('#driverName').val('');
		$('#driverPhone').val('');
		validAloneMobileStyle(document.getElementById('driverPhone'));
		$('#vehicleNumber').val('');
		$('#isCommonDriver').attr('checked',false);
		$('#cityDriverBox').hide();
	}
	// 选择自送网点 显示网点地址
	function showOutletsAddress(){
		$('#door_pick_up').hide();
		$('#self_delivery_outlets').show();
		// 显示城配司机
		$('#cityDriverBox').show();
	}
	// 选择普通发票 显示发票抬头
	function showReceiptTitle(){
		$('#receiptTitle').show();
	}
	// 隐藏发票抬头
	function hideReceiptTitle(){
		$("#receiptTitlePerson").attr("checked","checked");
		$('#receiptTitle').hide();
		hideReceiptCompanyName();
	}
	// 显示发票单位输入框
	function showReceiptCompanyName(){
		$('#receiptCompanyName').show();
	}
	// 以藏发票单位输入框
	function hideReceiptCompanyName(){
		$('#receiptCompanyName').hide();
	}
	// 显示代收款输入框
	function showCollectDelivery(obj){
		if(obj.checked){
			document.getElementById('collectDeliveryDiv').style.display = 'inline';
			$('input[name="isWaitPay"]').attr('disabled',false);
		}else{
			document.getElementById('collectDeliveryDiv').style.display = 'none';
			$('input[name="isWaitPay"]').attr('disabled',true);
		}
	}
	// 显示 楼层输入框，是否有电梯
	function showFloorElevator(obj, num){
		if(obj.checked){
			if(num == 1){
				$('#loadFloor').show();
			}else if(num == 2){
				$('#unloadFloor').show();
			}
		}else{
			if(num == 1){
				$('#loadFloor').hide();
			}else if(num == 2){
				$('#unloadFloor').hide();
			}
		}
	}
	
	// 选择到付
	function selectArrivePay(obj){
		if(obj.checked){
			var sendCargoType = $("input[name='sendCargoType']:checked").val();
			if(sendCargoType == 1){
				$("#promptModal").modal('show');
				$("#promptModalMsgs").html("发货方式为上门取货，不能选择到付");
				obj.checked = false;
				document.getElementById('onlinePay').checked = true;
			}
		}
	}
	
	// 订单提交
	function orderSubmit(){
		if(!validOrderInfoData() || !validCargoInfoData()){
			return;
		}
		
		var sendCargoType = $("input[name='sendCargoType']:checked").val();
		var payType = $("input[name='payType']:checked").val();
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/deliverGoods/placeorder.shtml',
			data : {
				'order' : JSON.stringify(buildOrderData()),
				'orderAddServer' : JSON.stringify(buildOrderAdditionalServerData()),
				'cargoInfos' : JSON.stringify(buildCargoInfoData()),
				'token' : $('#token').val()
			},
			success : function(result){
				if(result.result){
					if(sendCargoType == 1 && payType == 0){
						var money = result.data.money;
						if(money == 0){
							money = 0.1;
						}
						$('#money').val(money);
						$('#orderNumber').val(result.data.orderNumber);
						$('#payModal').modal({show:true});
					}else{
						window.location.href = '<%=request.getContextPath()%>/orderCenter/toorderlistpage.shtml';
					}
				}else{
					 $("#errorModal").modal('show');
					 $("#errorModalMsgs").html('保存失败');
				}
			},
			error : function(data){
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html('系统异常');
			}
		});
		
	}
	// 构建订单基础数据
	function buildOrderData(){
		var isCommonConsignor = 0;
		var isCommonDriver = 0;
		var isCommonConsignee = 0;
		var isWaitPay = 0;
		if($("input[name='isCommonConsignor']").get(0).checked){
			isCommonConsignor = 1;
		}
		if($("input[name='isCommonDriver']").get(0).checked){
			isCommonDriver = 1;
		}
		if($("input[name='isCommonConsignee']").get(0).checked){
			isCommonConsignee = 1;
		}
		if($("input[name='isWaitPay']").get(0).checked){
			isWaitPay = 1;
		}
		var order = {
				'tmsLineId' : $("#lineId").val(),
				'userId' : $("#userId").val(),
				'consignorName' : $("input[name='consignorName']").val(),
				'consignorProvince' : $("input[name='consignorProvince']").val(),
				'consignorCity' : $("input[name='consignorCity']").val(),
				'consignorCounty' : $("input[name='consignorCounty']").val(),
				'consignorAddress' : $("input[name='consignorAddress']").val(),
				'consignorPhoneNumber' : $("input[name='consignorPhoneNumber']").val(),
				'consignorTelephone' : $("input[name='consignorTelephone']").val(),
				'driverPhone' : $("input[name='driverPhone']").val(),
				'driverName' : $("input[name='driverName']").val(),
				'vehicleNumber' : $("input[name='vehicleNumber']").val(),
				'vehicleType' : $("select[name='vehicleType']").val(),
				'consigneeName' : $("input[name='consigneeName']").val(),
				'consigneeProvince' : $("input[name='consigneeProvince']").val(),
				'consigneeCity' : $("input[name='consigneeCity']").val(),
				'consigneeCounty' : $("input[name='consigneeCounty']").val(),
				'consigneeAddress' : $("input[name='consigneeAddress']").val(),
				'consigneePhoneNumber' : $("input[name='consigneePhoneNumber']").val(),
				'consigneeTelephone' : $("input[name='consigneeTelephone']").val(),
				'isCommonConsignor' : isCommonConsignor,
				'isCommonDriver' : isCommonDriver,
				'isCommonConsignee' : isCommonConsignee,
				'estimateWeight' : $("input[name='estimateWeight']").val(),
				'estimateVolume' : $("input[name='estimateVolume']").val(),
				'estimateFreight' : $("input[name='estimateFreight']").val(),
				'sendCargoType' : $("input[name='sendCargoType']:checked").val(),
				'deliveryDate' : $("input[name='deliveryDate']").val(),
				'deliveryStartTime' : $("input[name='deliveryStartTime']").val(),
				'deliveryEndTime' : $("input[name='deliveryEndTime']").val(),
				'receiveCargoType' : $("input[name='receiveCargoType']:checked").val(),
				'isInsurance' : $("input[name='isInsurance']:checked").val(),
				'insuranceMoney' : $("input[name='insuranceMoney']").val(),
				'receiptType' : $("input[name='receiptType']:checked").val(),
				'receiptTitle' : $("input[name='receiptTitle']:checked").val(),
				'receiptCompanyName' : $("input[name='receiptCompanyName']").val(),
				'payType' : $("input[name='payType']:checked").val(),
				'isWaitPay' : isWaitPay
		};
		return order;
	}
	// 构建订单增值服务数据
	function buildOrderAdditionalServerData(){
		var orderAdditionalServer = {
			'isReceipt' : $("input[name='isReceipt']:checked").val(),
			//'isImExStore' : $("input[name='isImExStore']:checked").val(),
			'isLoadCargo' : $("input[name='isLoadCargo']:checked").val(),
			'loadCargoFloor' : $("input[name='loadCargoFloor']").val(),
			'loadCargoIsElevator' : $("input[name='loadCargoIsElevator']:checked").val(),
			'isCollectionDelivery' : $("input[name='isCollectionDelivery']:checked").val(),
			'collectionDeliveryMoney' : $("input[name='collectionDeliveryMoney']").val(),
			'isUnloadCargo' : $("input[name='isUnloadCargo']:checked").val(),
			'unloadCargoFloor' : $("input[name='unloadCargoFloor']").val(),
			'unloadCargoIsElevator' : $("input[name='unloadCargoIsElevator']:checked").val()
		}
		return orderAdditionalServer;
	}
	
	// 构建货物信息数据
	function buildCargoInfoData(){
		var cargoInfos = [];
		var trs = $('#cargoTable tr');
		for(var i = 1; i < trs.length; i++){
			var tds = $(trs[i]).children();
			var cargo = new Object();
			cargo.name = $(tds[0]).children()[0].value;
			cargo.brand = $(tds[1]).children()[0].value;
			cargo.model = $(tds[2]).children()[0].value;
			cargo.goodsType=$(tds[3]).children()[0].value;
			cargo.number = $(tds[4]).children()[0].value; 
			cargo.setNumber = $(tds[5]).children()[0].value;
			cargo.singleWeight = $(tds[6]).children()[0].value;
			cargo.singleVolume = $(tds[7]).children()[0].value;
			cargo.packageType = $(tds[8]).children()[0].value;
			if($(tds[9]).children()[0].checked){
				cargo.isCommon = 1;
			}else{
				cargo.isCommon = 0;
			}
			cargoInfos.push(cargo)
		}
		return cargoInfos;
	}
	
	// -------------- 货物 编辑 表格 验证 样式 ----------000----------
	function showValidNullTip(obj){
		var value = obj.value;
		if(value == null || value == ''){
			obj.style.borderColor = 'red';
		}else{
			obj.style.borderColor = '#ccc';
		}
	}
	function showValidIntTip(obj){
		var value = obj.value;
		if(value != null && value != ''){
			if(isNaN(value)){
				obj.value = '';
				obj.style.borderColor = '#ccc';
			}else{
				if(parseInt(value) != value){
					obj.value = '';
					obj.style.borderColor = '#ccc';
				}
			}
		}
	}
	function showValidNumberTip(obj){
		var value = obj.value;
		if(value != null && value != ''){
			if(isNaN(value)){
				obj.value = '';
				obj.style.borderColor = '#ccc';
			}
		}
	}
	// -------------- 货物 编辑 表格 验证 样式 ----------000----------
	// 计算货物 总重量 总体积
	function countTotalWeightAndVolume(){
		var totalWeight = 0;
		var totalVolume = 0;
		var trs = $('#cargoTable tr');
		for(var i = 1; i < trs.length; i++){
			var tds = $(trs[i]).children();
			//var number = $(tds[3]).children()[0].value;
			var singleWeight = $(tds[5]).children()[0].value;
			var singleVolume = $(tds[6]).children()[0].value;
			totalWeight += new Number(singleWeight);
			totalVolume += new Number(singleVolume);
		}
		totalWeight = totalWeight.toFixed(2);
		totalVolume = totalVolume.toFixed(2);
		$('#estimateWeight').val(totalWeight);
		$('#estimateWeightText').text(totalWeight);
		$('#estimateVolume').val(totalVolume);
		$('#estimateVolumeText').text(totalVolume);
		estimateFreight();
	}
	
	// 计算预估运费
	function estimateFreight(){
		var heavyCargoPriceLow = new Number($('#heavyCargoPriceLow').val());
		var heavyCargoPriceMid = new Number($('#heavyCargoPriceMid').val());
		var heavyCargoPriceHigh = new Number($('#heavyCargoPriceHigh').val());
		var bulkyCargoPriceLow = new Number($('#bulkyCargoPriceLow').val());
		var bulkyCargoPriceMid = new Number($('#bulkyCargoPriceMid').val());
		var bulkyCargoPriceHigh = new Number($('#bulkyCargoPriceHigh').val());
		var estimateWeight = new Number($('#estimateWeight').val());
		var estimateVolume = new Number($('#estimateVolume').val());
		var estimateWeightFreight = 0;
		if(estimateWeight < 1){
			estimateWeightFreight = heavyCargoPriceLow * estimateWeight;
		}else if(estimateWeight > 1 && estimateWeight <= 3){
			estimateWeightFreight = heavyCargoPriceMid * estimateWeight;
		}else{
			estimateWeightFreight = heavyCargoPriceHigh * estimateWeight;
		}
		var estimateVolumeFreight = 0;
		if(estimateVolume < 1){
			estimateVolumeFreight = bulkyCargoPriceLow * estimateVolume;
		}else if(estimateVolume > 1 && estimateVolume <= 3){
			estimateVolumeFreight = bulkyCargoPriceMid * estimateVolume;
		}else{
			estimateVolumeFreight = bulkyCargoPriceHigh * estimateVolume;
		}
		var estimateFreight = 0;
		if(estimateWeightFreight > estimateVolumeFreight){
			estimateFreight = estimateWeightFreight;
		}else{
			estimateFreight = estimateVolumeFreight;
		}
		estimateFreight = estimateFreight.toFixed(2);
		$('#estimateFreight').val(estimateFreight);
		$('#estimateFreightText').text(estimateFreight);
	}
	
	// --------------订单信息 验证 -------------1111-------
	
	// 验证货物信息数据
	function validCargoInfoData(){
		var flag = true;
		var trs = $('#cargoTable tr');
		for(var i = 1; i < trs.length; i++){
			var tds = $(trs[i]).children();
			var name = $(tds[0]).children()[0].value;
			if(name == null || name == ''){
				$(tds[0]).children()[0].style.borderColor = 'red';
				flag = false;
			}
			var number = $(tds[3]).children()[0].value;
			var setNumber = $(tds[4]).children()[0].value;
			if((number == null || number == '') && (setNumber == null || setNumber == '')){
				//$(tds[3]).children()[0].style.borderColor = 'red';
				alert('货物件数和套数不能均为空');
				flag = false;
			}
			var singleWeight = $(tds[5]).children()[0].value;
			if(singleWeight == null || singleWeight == ''){
				$(tds[5]).children()[0].style.borderColor = 'red';
				flag = false;
			}
			var singleVolume = $(tds[6]).children()[0].value;
			if(singleVolume == null || singleVolume == ''){
				$(tds[6]).children()[0].style.borderColor = 'red';
				flag = false;
			}
		}
		return flag;
	}
	
	function validOrderInfoData(){
		var flag = true;
		var consignorName = $("input[name='consignorName']").val();
		if(consignorName == null || consignorName == ''){
			$("input[name='consignorName']").css('borderColor','red');
			$('#consignorNameValid').text('不能为空');
			flag = false;
		}
		var consignorCountySelect = $('#consignorCountySelect').val();
		if(consignorCountySelect == null || consignorCountySelect == ''){
			$('#consignorCountySelect').css('borderColor','red');
			$('#consignorCountySelectValid').text('不能为空');
			flag = false;
		}
		var consignorAddress = $("input[name='consignorAddress']").val();
		if(consignorAddress == null || consignorAddress == ''){
			$("input[name='consignorAddress']").css('borderColor','red');
			$('#consignorAddressValid').text('不能为空');
			flag = false;
		}
		var consignorPhoneNumber = $("input[name='consignorPhoneNumber']").val();
		if(consignorPhoneNumber == null || consignorPhoneNumber == ''){
			$("input[name='consignorPhoneNumber']").css('borderColor','red');
			$('#consignorPhoneNumberValid').text('不能为空');
			flag = false;
		}else{
			if(!mobilePartten.test(consignorPhoneNumber)){
				$("input[name='consignorPhoneNumber']").css('borderColor','red');
				$('#consignorPhoneNumberValid').text('手机号不正确');
				flag = false;
			}
		}
		var consignorTelephone = $("input[name='consignorTelephone']").val();
		if(consignorTelephone != null && consignorTelephone != ''){
			if(!telephonePartten.test(consignorTelephone)){
				$("input[name='consignorTelephone']").css('borderColor','red');
				$('#consignorTelephoneValid').text('固定电话格式不正确');
				flag = false;
			}
		}
		var driverPhone = $("input[name='driverPhone']").val();
		if(driverPhone != null && driverPhone != ''){
			if(!mobilePartten.test(driverPhone)){
				$("input[name='driverPhone']").css('borderColor','red');
				$('#driverPhoneValid').text('手机号码不正确');
				flag = false;
			}
		}
		// TODO
		/* var driverPhone = $("input[name='driverPhone']").val();
		if(driverPhone != null && driverPhone != ''){
			if(!mobilePartten.test(driverPhone)){
				$("input[name='driverPhone']").css('borderColor','red');
				$('#driverPhoneValid').text('手机号码不正确');
				flag = false;
			}
		} */
		var consigneeName = $("input[name='consigneeName']").val();
		if(consigneeName == null || consigneeName == ''){
			$("input[name='consigneeName']").css('borderColor','red');
			$('#consigneeNameValid').text('不能为空');
			flag = false;
		}
		var consigneeCountySelect = $('#consigneeCountySelect').val();
		if(consigneeCountySelect == null || consigneeCountySelect == ''){
			$('#consigneeCountySelect').css('borderColor','red');
			$('#consigneeCountySelectValid').text('不能为空');
			flag = false;
		}
		var consigneeAddress = $("input[name='consigneeAddress']").val();
		if(consigneeAddress == null || consigneeAddress == ''){
			$("input[name='consigneeAddress']").css('borderColor','red');
			$('#consigneeAddressValid').text('不能为空');
			flag = false;
		}
		var consigneePhoneNumber = $("input[name='consigneePhoneNumber']").val();
		if(consigneePhoneNumber == null || consigneePhoneNumber == ''){
			$("input[name='consigneePhoneNumber']").css('borderColor','red');
			$('#consigneePhoneNumberValid').text('不能为空');
			flag = false;
		}else{
			if(!mobilePartten.test(consigneePhoneNumber)){
				$("input[name='consigneePhoneNumber']").css('borderColor','red');
				$('#consigneePhoneNumberValid').text('手机号码不正确');
				flag = false;
			}
		}
		var consigneeTelephone = $("input[name='consigneeTelephone']").val();
		if(consigneeTelephone != null && consigneeTelephone != ''){
			if(!telephonePartten.test(consigneeTelephone)){
				$("input[name='consigneeTelephone']").css('borderColor','red');
				$('#consigneeTelephoneValid').text('固定电话格式不正确');
				flag = false;
			}
		}
		return flag;
	}
	
	// --------------订单信息 验证 -------------1111-------
	
	function validNumber(obj){
		var value = obj.value;
		if(value != null && value != ''){
			if(isNaN(value)){
				obj.value = '';
			}
		}
	}
	
	// ------------------常用联系人(发货人)----------------
	// 加载常用联系人(发货人)
	function loadCommonContact(num){
		var limit = 5;
		var pageIndex = $('#contactPageIndex').text();
		if(pageIndex == null || pageIndex == '' || typeof(pageIndex) == 'undefined'){
			pageIndex = 1;
		}else{
			if(1 + new Number(num) <= 0){
				pageIndex = 1;
			}else{
				pageIndex = 1 + new Number(num);
			}
		}
		var start = (new Number(pageIndex) - 1) * limit;
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/orderCenter/getListCommonContact.shtml',
			data : { 'start' : start, 'pageIndex' : pageIndex, 'limit' : limit, 'contactsType' : 0 },
			success : function(result){
				$('#contactPageIndex').text(pageIndex);
				$('#contactTotalPage').text(result.data.params.totalPage);
				var rows = result.data.params.rows;
				var html = '<tr>';
				html += '<th><input type="checkbox"></th>';
				html += '<th><span>发货人名称</span></th>';
				html += '<th><span>详细地址</span></th>';
				html += '<th><span>手机号</span></th>';
				html += '<th><span>固定电话</span></th>';
				html += '</tr>';
				for(var i = 0; i < rows.length; i++){
					html += '<tr>';
					html += '<td><input type="checkbox" onchange="singleChoicCheckBox(this)"></td>';
					html += '<td><span>'+rows[i].contactsName+'</span></td>';
					html += '<td><span>'+rows[i].address+'</span></td>';
					html += '<td><span>'+rows[i].phoneNumber+'</span></td>';
					html += '<td><span>'+rows[i].telephone+'</span></td>';
					html += '</tr>';
				}
				$('#commonContactTable').html(html);
			}
		});
	}
	
	var lastCheckBox = null;
	function singleChoicCheckBox(obj){
		if(obj.checked){
			if(lastCheckBox != null){
				$(lastCheckBox).removeAttr("checked");
			}
			lastCheckBox = obj;
		}
	}
	
	// 选择常用联系人(发货人)
	function choicCommonContact(){
		var num = 0;
		$('#commonContactTable tr').each(function(i, e){
			if(i > 0){
				var checked = $($(this).children()[0]).children()[0].checked;
				if(checked){
					num += 1;
				}
			}
		});
		if(num > 1){
			$("#promptModal").modal('show');
			$("#promptModalMsgs").html("只能选择一个联系人");
			return;
		}
		$('#commonContactTable tr').each(function(i, e){
			var checked = $($(this).children()[0]).children()[0].checked;
			if(checked){
				var contactsName = $($($(this).children()[1]).children()[0]).text();
				var address = $($($(this).children()[2]).children()[0]).text();
				var phoneNumber = $($($(this).children()[3]).children()[0]).text();
				var telephone = $($($(this).children()[4]).children()[0]).text();
				$('#consignorName').val(contactsName);
				validRequiredStyle(document.getElementById('consignorName'));
				$('#consignorAddress').val(address);
				validRequiredStyle(document.getElementById('consignorAddress'));
				$('#consignorPhoneNumber').val(phoneNumber);
				validRequiredStyle(document.getElementById('consignorPhoneNumber'));
				$('#consignorTelephone').val(telephone);
				validRequiredStyle(document.getElementById('consignorTelephone'));
			}
		});
	}
	
	// ------------------常用联系人(收货人)----------------
	// 加载常用联系人(收货人)
	function loadCommonContact2(num){
		var limit = 5;
		var pageIndex = $('#contactPageIndex2').text();
		if(pageIndex == null || pageIndex == '' || typeof(pageIndex) == 'undefined'){
			pageIndex = 1;
		}else{
			if(1 + new Number(num) <= 0){
				pageIndex = 1;
			}else{
				pageIndex = 1 + new Number(num);
			}
		}
		var start = (new Number(pageIndex) - 1) * limit;
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/orderCenter/getListCommonContact.shtml',
			data : { 'start' : start, 'pageIndex' : pageIndex, 'limit' : limit, 'contactsType' : 1 },
			success : function(result){
				$('#contactPageIndex2').text(pageIndex);
				$('#contactTotalPage2').text(result.data.params.totalPage);
				var rows = result.data.params.rows;
				var html = '<tr>';
				html += '<th><input type="checkbox"></th>';
				html += '<th><span>发货人名称</span></th>';
				html += '<th><span>详细地址</span></th>';
				html += '<th><span>手机号</span></th>';
				html += '<th><span>固定电话</span></th>';
				html += '</tr>';
				for(var i = 0; i < rows.length; i++){
					html += '<tr>';
					html += '<td><input type="checkbox" onchange="singleChoicCheckBox(this)"></td>';
					html += '<td><span>'+rows[i].contactsName+'</span></td>';
					html += '<td><span>'+rows[i].address+'</span></td>';
					html += '<td><span>'+rows[i].phoneNumber+'</span></td>';
					html += '<td><span>'+rows[i].telephone+'</span></td>';
					html += '</tr>';
				}
				$('#commonContactTable2').html(html);
			}
		});
	}
	
	// 选择常用联系人(收货人)
	function choicCommonContact2(){
		var num = 0;
		$('#commonContactTable2 tr').each(function(i, e){
			if(i > 0){
				var checked = $($(this).children()[0]).children()[0].checked;
				if(checked){
					num += 1;
				}
			}
		});
		if(num > 1){
			$("#promptModal").modal('show');
			$("#promptModalMsgs").html("只能选择一个联系人");
			return;
		}
		$('#commonContactTable2 tr').each(function(i, e){
			var checked = $($(this).children()[0]).children()[0].checked;
			if(checked){
				var contactsName = $($($(this).children()[1]).children()[0]).text();
				var address = $($($(this).children()[2]).children()[0]).text();
				var phoneNumber = $($($(this).children()[3]).children()[0]).text();
				var telephone = $($($(this).children()[4]).children()[0]).text();
				$('#consigneeName').val(contactsName);
				$('#consigneeAddress').val(address);
				$('#consigneePhoneNumber').val(phoneNumber);
				$('#consigneeTelephone').val(telephone);
				validRequiredStyle(document.getElementById('consigneeName'));
				validRequiredStyle(document.getElementById('consigneeAddress'));
				validRequiredStyle(document.getElementById('consigneePhoneNumber'));
				validRequiredStyle(document.getElementById('consigneeTelephone'));
			}
		});
	}
	
	// ------------------常用司机----------------
	// 加载常用司机
	function loadCommonDriver(num){
		var limit = 5;
		var pageIndex = $('#driverPageIndex').text();
		if(pageIndex == null || pageIndex == '' || typeof(pageIndex) == 'undefined'){
			pageIndex = 1;
		}else{
			if(1 + new Number(num) <= 0){
				pageIndex = 1;
			}else{
				pageIndex = 1 + new Number(num);
			}
		}
		var start = (new Number(pageIndex) - 1) * limit;
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/orderCenter/getListCommonDriver.shtml',
			data : { 'start' : start, 'pageIndex' : pageIndex, 'limit' : limit },
			success : function(result){
				$('#driverPageIndex').text(pageIndex);
				$('#driverTotalPage').text(result.data.params.totalPage);
				var rows = result.data.params.rows;
				var html = '<tr>';
				html += '<th><input type="checkbox"></th>';
				html += '<th><span>姓名</span></th>';
				html += '<th><span>手机号</span></th>';
				html += '<th><span>车牌号</span></th>';
				html += '<th><span>车型</span></th>';
				html += '</tr>';
				for(var i = 0; i < rows.length; i++){
					html += '<tr>';
					html += '<td><input type="checkbox" onchange="singleChoicCheckBox(this)"></td>';
					html += '<td><span>'+rows[i].driverName+'</span></td>';
					html += '<td><span>'+rows[i].phoneNumber+'</span></td>';
					html += '<td><span>'+rows[i].licenseNumber+'</span></td>';
					html += '<td><span>'+rows[i].vehicleTypeName+'</span><input type="hidden" value="'+rows[i].vehicleType+'"></td>';
					html += '</tr>';
				}
				$('#commonDriverTable').html(html);
			}
		});
	}
	
	// 选择常用司机
	function choicCommonDriver(){
		var num = 0;
		$('#commonDriverTable tr').each(function(i, e){
			if(i > 0){
				var checked = $($(this).children()[0]).children()[0].checked;
				if(checked){
					num += 1;
				}
			}
		});
		if(num > 1){
			$("#promptModal").modal('show');
			$("#promptModalMsgs").html("只能选择一个联系人");
			return;
		}
		$('#commonDriverTable tr').each(function(i, e){
			var checked = $($(this).children()[0]).children()[0].checked;
			if(checked){
				var driverName = $($($(this).children()[1]).children()[0]).text();
				var phoneNumber = $($($(this).children()[2]).children()[0]).text();
				var licenseNumber = $($($(this).children()[3]).children()[0]).text();
				var vehicleType = $($($(this).children()[4]).children()[1]).val();
				$('#driverName').val(driverName);
				$('#driverPhone').val(phoneNumber);
				$('#vehicleNumber').val(licenseNumber);
				$('#vehicleType').val(vehicleType);
			}
		});
	}
	
	// ------------------常用货物------------------
	// 加载常用货物
	function loadCommonCargo(num){
		var limit = 5;
		var pageIndex = $('#pageIndex').text();
		if(pageIndex == null || pageIndex == '' || typeof(pageIndex) == 'undefined'){
			pageIndex = 1;
		}else{
			if(1 + new Number(num) <= 0){
				pageIndex = 1;
			}else{
				pageIndex = 1 + new Number(num);
			}
		}
		var start = (new Number(pageIndex) - 1) * limit;
		
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/orderCenter/getCommonCargo.shtml',
			data : { 'start' : start, 'pageIndex' : pageIndex, 'limit' : limit },
			success : function(result){
				$('#pageIndex').text(pageIndex);
				$('#totalPage').text(result.data.params.totalPage);
				var rows = result.data.params.rows;
				var html = '<tr>';
				html += '<th><input type="checkbox"></th>';
				html += '<th><span>货物名称</span></th>';
				html += '<th><span>货物品牌</span></th>';
				html += '<th><span>货物型号</span></th>';
				html += '<th><span>单件重量</span></th>';
				html += '<th><span>单件体积</span></th>';
				html += '<th><span>包装信息</span></th>';
				html += '</tr>';
				for(var i = 0; i < rows.length; i++){
					html += '<tr>';
					html += '<td><input type="checkbox"></td>';
					html += '<td><span>'+rows[i].cargoName+'</span></td>';
					html += '<td><span>'+rows[i].cargoBrand+'</span></td>';
					html += '<td><span>'+rows[i].model+'</span></td>';
					html += '<td><span>'+rows[i].singleWeight+'</span></td>';
					html += '<td><span>'+rows[i].singleVolume+'</span></td>';
					html += '<td><span>'+rows[i].packingInfoName+'</span></td>';
					html += '</tr>';
				}
				$('#commonCargoTable').html(html);
			}
		});
	}
	
	// 选择 货物
	function choicCarog(){
		$('#commonCargoTable tr').each(function(i, e){
			if(i > 0){
				var editTableHtml = '';
				var checked = $($(this).children()[0]).children()[0].checked;
				if(checked){
					var cargoName = $($($(this).children()[1]).children()[0]).text();
					var goodsname = $($($(this).children()[2]).children()[0]).text();
					var goodstype = $($($(this).children()[3]).children()[0]).text();
					var singleWeight = $($($(this).children()[4]).children()[0]).text();
					var singleVolume = $($($(this).children()[5]).children()[0]).text();
					var packageType = $($($(this).children()[6]).children()[1]).val();
					editTableHtml += '<tr>';
					editTableHtml += '<td><input type="text" class="edit-table-input" value="'+cargoName+'" onblur="showValidNullTip(this)"></td>';
					editTableHtml += '<td><input type="text" class="edit-table-input" value="'+goodsname+'"></td>';
					editTableHtml += '<td><input type="text" class="edit-table-input" value="'+goodstype+'"></td>';
					editTableHtml += '<td><select name="packageInfo" class="edit-table-select">'+loadGoodsType()+'</select></td>';
					editTableHtml += '<td><input type="text" class="edit-table-input" onblur="showValidIntTip(this), countTotalWeightAndVolume()"></td>';
					editTableHtml += '<td><input type="text" class="edit-table-input" onblur="showValidIntTip(this)"></td>';
					editTableHtml += '<td><input type="text" class="edit-table-input" value="'+singleWeight+'" onblur="showValidNullTip(this), showValidNumberTip(this), countTotalWeightAndVolume()"></td>';
					editTableHtml += '<td><input type="text" class="edit-table-input" value="'+singleVolume+'" onblur="showValidNullTip(this), showValidNumberTip(this), countTotalWeightAndVolume()"></td>';
					editTableHtml += '<td><select name="packageInfo" class="edit-table-select">'+loadPackageType()+'</select></td>';
					editTableHtml += '<td><input type="checkbox" value="1"></td>';
					editTableHtml += '<td><a href="javascript://" style="text-decoration: none;" onclick="deleteCargo(this)">删除</a></td>';
					editTableHtml += '</td>';
					$('#cargoTable tbody').prepend(editTableHtml);
				}
			}
		});
		countTotalWeightAndVolume();
	}
	
	// 加载包装类型
	function loadPackageType(){
		var html = '';
		$.ajax({
			type : 'post',
			async: false,
			url : '<%=request.getContextPath()%>/dic/getpackagetype.shtml',
			success : function(result){
				var rows = result.rows;
				for(var i = 0; i < rows.length; i++){
					html += '<option value="'+rows[i].id+'">'+rows[i].name+'</option>';
				}
			}
		});
		return html;
	}
	// 加载商品类型
	function loadGoodsType(){
		var html = '';
		$.ajax({
			type : 'post',
			async: false,
			url : '<%=request.getContextPath()%>/dic/getGoodstype.shtml',
			success : function(result){
				var rows = result.rows;
				for(var i = 0; i < rows.length; i++){
					html += '<option value="'+rows[i].id+'">'+rows[i].softName+'</option>';
				}
			}
		});
		return html;
	}
	// 支付提货费
	function payTakeCargoCost(){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/deliverGoods/paytakecargocost.shtml',
			data : {'orderNumber' : $('#orderNumber').val(), 'money' : $('#money').val()},
			success : function(result){
				if(result > 0){
					$("#successModal").modal('show');
  					$("#successModalMsgs").html("支付成功");
					window.location.href = '<%=request.getContextPath()%>/orderCenter/toorderlistpage.shtml';
				}else{
					 $("#errorModal").modal('show');
  					 $("#errorModalMsgs").html("支付失败");
				}
			}
		});
	}
	function tiaozhuan() {
		window.location.href = '<%=request.getContextPath()%>/orderCenter/toorderlistpage.shtml';
	}
	
	// 发货方式 设置
	function sendTypeSetting(){
		var consignorCounty = $('input[name="consignorCounty"]').val();
		if(consignorCounty == null || consignorCounty == '' || typeof(consignorCounty) == 'undefined'){
			consignorCounty = $('#startSelectCounty').val();
		}
		var outletsPriceRangeConfs = ${outletsPriceRangeConfs};
		for(var i = 0; i < outletsPriceRangeConfs.length; i++){
			if(outletsPriceRangeConfs[i].county == consignorCounty){
				$('input[name="sendCargoType"]').attr('disabled',false);
				break;
			}else{
				$("#home_deliver").removeAttr("checked");
				$('#home_deliver').attr('disabled',true);
				document.getElementById('self-deliver').checked = true;
				$('#door_pick_up').hide();
				$('#self_delivery_outlets').show();
				$('#deliveryDate').val('');
				$('#deliveryStartTime').val('');
				$('#deliveryEndTime').val('');
			}
		}
	}
	
	// 加载发货人地址
	function loadConsignorAddress(){
		var startSelectCity = $('#startSelectCity').val();
		var startSelectCounty = $('#startSelectCounty').val();
		var startCity = ${startOutlets.city};
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/xzqh/getxzqhinfo.shtml',
			data : {'pid' : startCity},
			success : function(result){
				var html = '<option value="">请选择区</option>';
				for(var i = 0; i < result.length; i++){
					html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$('#consignorCountySelect').html(html);
				if(startSelectCounty != null && startSelectCounty != '' && typeof(startSelectCounty) != 'undefined'){
					if(startSelectCity == startCity){
						$('#consignorCountySelect').val(startSelectCounty);
						$('#consignorCounty').val(startSelectCounty);
					}
				}
			}
		});
		var endSelectCity = $('#endSelectCity').val();
		var endSelectCounty = $('#endSelectCounty').val();
		var endCity = ${endOutlets.city};
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/xzqh/getxzqhinfo.shtml',
			data : {'pid' : endCity},
			success : function(result){
				var html = '<option value="">请选择区</option>';
				for(var i = 0; i < result.length; i++){
					html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$('#consigneeCountySelect').html(html);
				if(endSelectCounty != null && endSelectCounty != '' && typeof(endSelectCounty) != 'undefined'){
					if(endSelectCity == endCity){
						$('#consigneeCountySelect').val(endSelectCounty);
						$('#consigneeCounty').val(endSelectCounty);
					}
				}
			}
		});
	}
	</script>
</body>
</html>
