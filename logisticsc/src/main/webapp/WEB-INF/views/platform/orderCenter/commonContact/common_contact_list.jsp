<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常用联系人</title>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap日期-->
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.js"></script>
<!-- bootstrap日期中文-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="${configProps['resources']}/platform/js/commonContactList.js"></script>
</head>	
<body>
<jsp:include page="../../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="../order_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
		<ul id="myTab" class="nav nav-tabs">
		   <li class="active"><a href="#shouhuoren" data-toggle="tab">常用收货人</a></li>
		   <li><a href="#fahuoren" data-toggle="tab">常用发货人</a></li>
		   <li><a href="#siji" data-toggle="tab">常用司机</a></li>
		</ul>
		 <!-- 收货人DIV-->
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="shouhuoren">
	      	 	<div class="clearfix">
					<form class="form-inline" style="margin-top: 13px; margin-left: 18px;">
				      <label >收货人名称：</label>
					  <input type="text" id="contactsName" class="form-control" name="contactsName" placeholder="收货人名称">
					  <button type="button" onclick="selectCommonContactshouhuo()" class="btn btn-info">查询</button>
					  <button type="button" onclick="displayConsignee()" class="btn btn-info">添加收货人</button>
					  <button type="button" class="btn" id="anniu-yellow" onclick="deleteCommonConsignee()">删除</button>
					</form>
				</div>
				<div class="bill-zong">
					<table class="table table-striped" id="bill-table1">
						<thead>
							<tr>
							  <th></th>
					          <th>收货人名称</th>
					          <th>单位名称</th>
					          <th>手机号</th>
					          <th>固定电话</th>
					          <th>地址</th>
					          <th>时间</th>
					        </tr>
						</thead>
						<tbody id="getListCommonConsignee">
						</tbody>
					</table>
					<div id="pageListshouhuo">
					</div>
				</div>
		   </div>
		   <!-- END -->
		   <!-- 发货人DIV-->
		   <div class="tab-pane fade" id="fahuoren">
		      <div class="clearfix">
					<form class="form-inline" style="margin-top: 13px; margin-left: 18px;">
				      <label >发货人名称：</label>
					  <input type="text" id="contactsNameFahuoren" class="form-control" name="contactsName" placeholder="发货人名称">
					  <button type="button" onclick="selectCommonContactfahuo()" class="btn btn-info">查询</button>
					  <button type="button" onclick="displayConsignor()" class="btn btn-info">添加发货人</button>
					  <button type="button" class="btn" id="anniu-yellow" onclick="deleteCommonConsignor()">删除</button>
					</form>
				</div>
				<div class="bill-zong">
					<table class="table table-striped" id="bill-table1">
						<thead>
							<tr>
							  <th></th>
					          <th>发货人名称</th>
					          <th>单位名称</th>
					          <th>手机号</th>
					          <th>固定电话</th>
					          <th>地址</th>
					          <th>时间</th>
					        </tr>
						</thead>
						<tbody id="getListCommonConsignor">
						</tbody>
					</table>
					<div id="pageListfahuo">
					</div>
				</div>
		   </div>
		   <!-- END -->
		   <!-- 司机DIV-->
		   <div class="tab-pane fade" id="siji">
		      <div class="clearfix">
					<form class="form-inline" style="margin-top: 13px; margin-left: 18px;">
				      <label >司机名称：</label>
					  <input type="text" id="driverName" class="form-control" name="driverName" placeholder="司机名称">
					  <button type="button" onclick="selectCommonDriver()" class="btn btn-info">查询</button>
					  <button type="button" onclick="displayCommonDriver()" class="btn btn-info">添加司机</button>
					  <button type="button" class="btn" id="anniu-yellow" onclick="deletecommonDriver()">删除</button>
					</form>
				</div>
				<div class="bill-zong">
					<table class="table table-striped"  id="bill-table1">
						<thead>
							<tr>
							   <th></th>
					           <th>司机名称</th>
					           <th>车牌号</th>
					           <th>手机号</th>
					           <th>车型</th>
					           <th>地址</th>
					           <th>时间</th>
					        </tr>
						</thead>
						<tbody id="getCommonDriver">
						</tbody>
					</table>
					<div id="pageListsiji">
					</div>
				</div>
		   </div>
		   <!-- END -->
		</div>
	</div>	
</div>
<!-- 发货人 -->
<div id="addConsignor" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="add_common_consignor">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">增加发货人</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 14px;" >
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;发货人：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="contactsNameFsg"></label>
								<input type="hidden" style="display:inline-block;width:250px" name="contactsType" class="form-control required" value="0"/>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="contactsNameF" name="contactsName" placeholder="请输入发货人名称"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;"> 
							<label class="control-label col-sm-4">单位名称：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="companyNameFsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="companyNameF" name="companyName" placeholder="请输入单位名称"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;手机号：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="phoneNumberFsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="phoneNumberF" name="phoneNumber" placeholder="请输入手机号"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;固定号码：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="telephoneFsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="telephoneF" name="telephone" placeholder="请输入固定号码"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;省：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="common_contact_provinces_fsg"></label>
								<select id="common_contact_provinces" class="form-control" name="province" onchange="ajaxCityContact()">
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;市：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="common_contact_citys_fsg"></label>
					          	<select id="common_contact_citys" class="form-control" name="city" onchange="ajaxCountyContact()">
					          		<option value="0">请选择</option>
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;区：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="common_contact_countys_fsg"></label>
					          	<select id="common_contact_countys" class="form-control" name="county">
					          		<option value="0">请选择</option>
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;详细地址：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="addressFsg"></label>
								<textarea class="form-control" rows="3"  style="display:inline-block;width:250px" id="addressF" name="address" placeholder="请输入详细地址"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" id="tianjiafahuoren">保存</button>
					</div>
			</form>
		</div>
	</div>
</div>
<!-- 收货人 -->
<div id="addConsignee" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="add_common_consignee">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">增加收货人</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 14px;" >
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;收货人：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="contactsNameMsg"></label>
								<input type="hidden" style="display:inline-block;width:250px" name="contactsType" class="form-control required" value="1"/>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="contactsNameS" name="contactsName" placeholder="请输入收货人名称"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">单位名称：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="companyNameMsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="companyNameS" name="companyName" placeholder="请输入单位名称"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;手机号：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="phoneNumberMsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="phoneNumberS" name="phoneNumber" placeholder="请输入手机号"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;固定号码：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="telephoneMsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="telephoneS" name="telephone" placeholder="请输入固定号码"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;省：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="common_contact_provinceMsg"></label>
								<select id="common_contact_province" class="form-control" name="province" onchange="ajaxCity()">
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;市：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="common_contact_cityMsg"></label>
					          	<select id="common_contact_city" class="form-control" name="city" onchange="ajaxCounty()">
					          		<option value="0">请选择</option>
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;区：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="common_contact_county_Msg"></label>
					          	<select id="common_contact_county" class="form-control" name="county">
					          		<option value="0">请选择</option>
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;详细地址：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="addressSMsg"></label>
								<textarea class="form-control" rows="3" style="display:inline-block;width:250px" id="addressS" name="address" placeholder="请输入详细地址"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" id="tianjiashouhuoren">保存</button>
					</div>
			</form>
		</div>
	</div>
</div>
<!-- 司机 -->
<div id="addCommonDriver" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="add_common_driver">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">增加司机</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 14px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;司机名称：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="driverNameMsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="driverNameText" name="driverName" placeholder="请输入司机名称"/>
								<input type="hidden" style="display:inline-block;width:250px" name="userId" class="form-control required" value="${user_session.id}"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;车牌号：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="licenseNumberMsg"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="licenseNumberText" name="licenseNumber" placeholder="请输入车牌号"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;手机号：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="phoneNumberMsgs"></label>
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="phoneNumberText" name="phoneNumber" placeholder="请输入手机号"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;车型：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="dictionaryTypeMsg"></label>
								<select id="dictionary_type" class="form-control fcjselect" name="vehicleType">
					          	</select>
							</div>
						</div>
					<!-- 	<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;省：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="driver_provinces_fsg"></label>
								<select id="driver_provinces" class="form-control" name="province" onchange="ajaxdriverCityContact()">
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;市：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="driver_citys_fsg"></label>
					          	<select id="driver_citys" class="form-control" name="city" onchange="ajaxdriverCountyContact()">
					          		<option value="0">请选择</option>
					          	</select>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;区：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="driver_countys_fsg"></label>
					          	<select id="driver_countys" class="form-control" name="county">
					          		<option value="0">请选择</option>
					          	</select>
							</div>
						</div> -->
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">详细地址</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="addressMsg"></label>
								<textarea class="form-control" rows="3" style="display:inline-block;width:250px" id="addressText" name="address" placeholder="请输入详细地址"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="margin-top: 28px;">
						<button type="button" class="btn btn-success btn-save" id="tianjiasiji">保存</button>
					</div>
			</form>
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
                    <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">温馨提示！</h4>
              		<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #cccccc!important;font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
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
<jsp:include page="../../bottom.jsp"></jsp:include>
</body>
</html>