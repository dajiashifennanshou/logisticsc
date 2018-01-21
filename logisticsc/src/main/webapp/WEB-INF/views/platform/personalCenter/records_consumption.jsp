<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
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
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<script src="${configProps['resources']}/platform/js/recordsConsumption.js"></script>
<title>我的消费记录</title>
</head>
<body>
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="peronal_center_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
		<ul id="myTab" class="nav nav-tabs">
		   <li class="active"><a href="#consumption" data-toggle="tab">消费记录</a></li>
		   <li><a href="#refund" data-toggle="tab">退款记录</a></li>
 		   <li><a href="#Receivables" data-toggle="tab">POS机收款记录</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="consumption">
				<form class="form-inline" style="margin-top: 13px; margin-left: 18px;margin-bottom: 10px;">
					<label>消费类型：</label>
		          	<select id="orderType" class="form-control fcjselect" name="orderType">
		          		<option value="">请选择</option>
		          		<option value="0">保险费</option>
		          		<option value="1">预约费</option>
		          		<option value="2">运费</option>
		          		<option value="3">保证金</option>
		          	</select>
			      <label >下单日期：</label> 
				      <input type="text" class="form-control" id="onlineStartTimes" placeholder="请选择开始日期" />-
				      <input type="text" class="form-control" id="onlineEndTimes" placeholder="请选择结束日期" />
			      <label >订单号：</label>
				  	  <input type="text" id="condition" class="form-control" name="condition" placeholder="订单号">
					  <button type="button"  onclick="getBySelectedPayment()" class="btn btn-info" >查询</button>
				</form>
				<table class="table table-striped" id="dingdan-zt">
					<thead>
						<tr>
				          <th>支付订单号</th>
				          <th>订单号</th>
				          <th>消费类型</th>
				          <th>支付方式</th>
				          <th>金额</th>
				          <th>时间</th>
				        </tr>
					</thead>
					<tbody id="bankPayment">
					</tbody>
				</table>
				<div id="pageList">
				</div>
			</div> 
			<div class="tab-pane fade in" id="refund">
				<form class="form-inline" style="margin-top: 13px; margin-left: 18px;margin-bottom: 10px;">
					<label>订单类型：</label>
		          	<select id="refundOrderType" class="form-control fcjselect">
		          		<option value="">请选择</option>
		          		<option value="0">保险费</option>
		          		<option value="1">预约费</option>
		          		<option value="2">运费</option>
		          	</select>
		          	<label>退款方式：</label>
		          	<select id="refundType" class="form-control fcjselect">
		          		<option value="">请选择</option>
		          		<option value="0">用户退款</option>
		          		<option value="1">物流提供商退款</option>
		          		<option value="2">系统退款</option>
		          	</select> 
			      <label >下单日期：</label> 
				      <input type="text" style="width: 150px;" class="form-control" id="refundStartTime" placeholder="请选择开始日期" />-
				      <input type="text" style="width: 150px;" class="form-control" id="refundEndTime" placeholder="请选择结束日期" />
			      <label >订单号：</label>
				  	  <input type="text" id="refundCondition" class="form-control" placeholder="订单号">
					  <button type="button"  onclick="getSelectRefund()" class="btn btn-info" >查询</button>
				</form>
				<table class="table table-striped" id="dingdan-zt">
					<thead>
						<tr>
				          <th>支付订单号</th>
				          <th>订单号</th>
				          <th>订单类型</th>
				          <th>退款方式</th>
				          <th>退款金额</th>
				          <th>时间</th>
				        </tr>
					</thead>
					<tbody id="refundList">
					</tbody>
				</table>
				<div id="refundPage">
				</div>
			</div>
			<div class="tab-pane fade in" id="Receivables">
				<form class="form-inline" style="margin-top: 13px; margin-left: 18px;margin-bottom: 10px;">
		          	<label>POS状态：</label>
		          	<select id="posStatus" class="form-control fcjselect">
		          		<option value="">请选择</option>
		          		<option value="0">完结</option>
		          		<option value="1">撤销</option>
		          	</select> 
			      <label >下单日期：</label> 
				      <input type="text" style="width: 150px;" class="form-control" id="posStartTime" placeholder="请选择开始日期" />-
				      <input type="text" style="width: 150px;" class="form-control" id="posEndTime" placeholder="请选择结束日期" />
			      <label >订单号：</label>
				  	  <input type="text" id="posCondition" class="form-control" placeholder="订单号">
					  <button type="button"  onclick="getSelectPos()" class="btn btn-info" >查询</button>
				</form>
				<table class="table table-striped" id="dingdan-zt">
					<thead>
						<tr>
				         
				          <th>订单号</th>
				          <th>金额(元)</th>
				          <th>物流商名称</th>
				          <th>网点名称</th>
				          <th>到账名称</th>
				          <th>订单状态</th>
				          <th>时间</th>
				        </tr>
					</thead>
					<tbody id="posList">
					</tbody>
				</table>
				<div id="posPage">
				</div>
			</div>
		</div>
    </div>
</div> 
</div>
 <div style="clear: both;"></div>
 <div class="footer">	
	<jsp:include page="../bottom.jsp"></jsp:include>
	</div>
</body>
</html>