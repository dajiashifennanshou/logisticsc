<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的投诉</title>
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
<script src="${configProps['resources']}/platform/js/complainList.js"></script>
</head>
<body>
<jsp:include page="../../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="../order_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
		<div class="clearfix">
			<form class="form-inline" style="margin-top: 13px; margin-left: 18px;">
			  <label >投诉状态：</label>
			  	<select id="complainState" class="form-control fcjselect">
	          		<option value="">请选择</option>
	          		<option value="1">未回复</option>
	          		<option value="2">已回复</option>
	          		<option value="3">已反馈</option>
	          </select>
		      <label >下单日期：</label> 
		      <input type="text" class="form-control" id="onlineStartTimes" placeholder="请选择开始日期" />-
		      <input type="text" class="form-control" id="onlineEndTimes" placeholder="请选择结束日期" />
		      <label >订单号：</label>
			  <input type="text" id="condition" class="form-control" name="condition" placeholder="订单号/运单号">
			  <button type="button" onclick="selectComp()" class="btn btn-info">查询</button>
			</form>
		</div>
		<div class="bill-zong">
			<table class="table table-striped" id="bill-table1">
				<thead>
					<tr>
						<th>订单号</th>
						<th>运单号</th>
						<th>物流商</th>
						<th>线路</th>
						<th>投诉内容</th>
						<th>状态</th>
						<th>投诉时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="getComplain">
				</tbody>
			</table>
			<div id="pageList">
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
    <div id="selectComplainExhibition" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog" style="width:60%">
			<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">投诉回复信息</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<table border="1" width="100%" bordercolor="#cccccc">
							<tr>
								<td  class="td_colour td_style">投诉信息</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-6">
											<span class="td_colour">订单号:</span><span class="td_colour" id="orderNumber"></span>
										</div>
										<div class="col-sm-6">
											<span class="td_colour">运单号:</span><span class="td_colour" id="wayBillNumber"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉时间:</span><span class="td_colour" id="complaintTime"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉内容:</span><span class="td_colour" id="complaintContent"></span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td_colour td_style">投诉回复</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-12">
											<span class="td_colour">投诉处理时间:</span><span class="td_colour"  id="handleTime"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉处理账号:</span><span class="td_colour"  id="handlePeople"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉处理内容:</span><span class="td_colour"  id="handleContent"></span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td_colour td_style">投诉反馈</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-12">
											<span class="td_colour">投诉反馈:</span>
											<div class="radio-btn1" id="radio-btn1">
												<input type="radio" id="sex1" name="sex" value="1" /> 非常满意
 											    <input type="radio" id="sex2" name="sex" value="2" /> 满意
 											    <input type="radio" id="sex3" name="sex" value="3" /> 一般
 											    <input type="radio" id="sex4" name="sex" value="4" /> 不满意
 											    <input type="radio" id="sex5" name="sex" value="5" /> 非常不满意
											</div>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉反馈时间:</span><span class="td_colour"  id="handleSatisfiedTime"></span>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
			</form>
			</div>
		</div>
	</div>
	 <div id="selectComplain" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog" style="width:60%">
			<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="update_complain">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">投诉回复信息</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<table border="1" width="100%" bordercolor="#cccccc">
							<tr>
								<td  class="td_colour td_style">投诉信息</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-6">
											<span class="td_colour">订单号:</span><span class="td_colour" id="orderNumbers"></span>
										</div>
										<div class="col-sm-6">
											<span class="td_colour">运单号:</span><span class="td_colour" id="wayBillNumbers"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉时间:</span><span class="td_colour" id="complaintTimes"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉内容:</span><span class="td_colour" id="complaintContents"></span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td_colour td_style">投诉回复</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-12">
											<span class="td_colour">投诉处理时间:</span><span class="td_colour"  id="handleTimes"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉处理账号:</span><span class="td_colour"  id="handlePeoples"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">投诉处理内容:</span><span class="td_colour"  id="handleContents"></span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td_colour td_style">投诉反馈</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-12">
											<span class="td_colour">投诉反馈:</span><!-- <input type="text" style="display:inline-block;width:250px" class="form-control required" id="handleSatisfiedDegrees" name="handleSatisfiedDegree"/> -->
											<div class="radio-btn1" id="radio-btn1">
												<input type="radio" name="sex" value="1" /> 非常满意
 											    <input type="radio" name="sex" value="2" /> 满意
 											    <input type="radio" name="sex" value="3" /> 一般
 											    <input type="radio" name="sex" value="4" /> 不满意
 											    <input type="radio" name="sex" value="5" /> 非常不满意
											</div>
										</div>
									</div>
									<div style="line-height: 40px;">
										<div class="col-sm-6">
											<input  type="hidden" style="display:inline-block;width:250px" class="form-control required" id="ids" name="id"/>
											<input  type="hidden" style="display:inline-block;width:250px" class="form-control required" id="complaintId"/>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" onclick="updateComplain()">确认</button>
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