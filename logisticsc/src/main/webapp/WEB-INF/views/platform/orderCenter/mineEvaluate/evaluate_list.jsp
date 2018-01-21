<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的评价</title>
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
<script src="${configProps['resources']}/platform/js/evaluateList.js"></script>
<link href="${configProps['resources']}/platform/css/xingji.css" rel="stylesheet">
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
		      <label >下单日期：</label> 
		      <input type="text" class="form-control" id="onlineStartTimes" placeholder="请选择开始日期" />-
		      <input type="text" class="form-control" id="onlineEndTimes" placeholder="请选择结束日期" />
		      <label >订单号：</label>
			  <input type="text" id="condition" class="form-control" name="condition" placeholder="订单号/运单号">
			  <button type="button" onclick="selectEva()" class="btn btn-info">查询</button>
			</form>
		</div>
		<div class="bill-zong">
			<table class="table table-striped"  id="bill-table1">
				<thead>
					<tr>
						<th>订单号</th>
						<th>运单号</th>
						<th>物流商</th>
						<th>线路</th>
						<th>评价内容</th>
						<th>状态</th>
						<th>评价时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="getEvaluation">
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
<div id="selectEvaluation" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog" style="width:60%">
			<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">评论回复信息</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<table border="1" width="100%" bordercolor="#cccccc">
							<tr>
								<td  class="td_colour td_style">评价信息</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-6">
											<span class="td_colour">订单号:</span><span class="td_colour" id="orderNumbers"></span>
										</div>
										<div class="col-sm-6">
											<span class="td_colour">运单号:</span><span class="td_colour" id="wayBillNumber"></span>
										</div>
										<div class="col-sm-12">
											 <span class="td_colour">评价星级:</span>
											<div class="cont">
												<div class="nr">
													<b rate="1" class="xx"></b>
													<b rate="2" class="xx"></b>
													<b rate="3" class="xx"></b>
													<b rate="4" class="xx"></b>
													<b rate="5" class="xx"></b>
													<div class="star" style="width: 0px;"></div>
												</div>
											</div>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">评价时间:</span><span class="td_colour" id="evaluateTime"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">评价内容:</span><span class="td_colour" id="evaluateContent"></span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td_colour td_style">评价回复</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-12">
											<span class="td_colour">评价回复时间:</span><span class="td_colour"  id="replyTime"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">评价回复账号:</span><span class="td_colour"  id="replyPeople"></span>
										</div>
										<div class="col-sm-12">
											<span class="td_colour">评价回复内容:</span><span class="td_colour"  id="replyContent"></span>
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