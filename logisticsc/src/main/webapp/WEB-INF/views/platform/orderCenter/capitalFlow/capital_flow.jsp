<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金流水</title>
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
<script src="${configProps['resources']}/platform/js/capitalFlow.js"></script>
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
				  	  <input type="text" id="condition" class="form-control" name="condition" placeholder="订单号">
				<button type="button"  onclick="getBySelectedPayment()" class="btn btn-info" >查询</button>
				  <button type="button" class="btn btn-info">刷新</button>
			</form>
		</div>
		<div class="bill-zong">
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
	</div>
</div>
<div style="clear: both;"></div>
<jsp:include page="../../bottom.jsp"></jsp:include>		
</body>
</html>