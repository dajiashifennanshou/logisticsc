<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的账单</title>
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
<script src="${configProps['resources']}/platform/js/mineBillList.js"></script>
</head>
<style>
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
	table th{
            white-space: nowrap;
        }
        table td{
            white-space: nowrap;
        }
        body,table{
         font-size:12px;
        }
  table{
         empty-cells:show; 
         /* border-collapse: collapse; */
         margin:0 auto;
        }
 
  h1,h2,h3{
   font-size:12px;
   margin:0;
   padding:0;
  }
  table.tab_css_1{
  /*  border:1px solid #cad9ea; */
   color:#666;
  }
  table.tab_css_1 th {
   background-image: url("th_bg1.gif");
   background-repeat:repeat-x;
   height:30px;
  }
  table.tab_css_1 td,table.tab_css_1 th{
 /*   border:1px solid #cad9ea; */
   padding:0 1em 0;
  }
  table.tab_css_1 tr.tr_css{
  /*  background-color:#f5fafe; */
   height:30px;
  }
</style>
<body>
<jsp:include page="../../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="../order_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
		<div class="clearfix">
			<form class="form-inline" id="" style="margin-top: 13px; margin-left: 18px;">
			  <label >账单状态：</label>
			  	<select id="billState" class="form-control fcjselect">
	          		<option value="">请选择</option>
	          		<option value="0">未完成</option>
	          		<option value="1">已完成</option>
	          	</select>
		      <label >下单日期：</label> 
		      <input type="text" class="form-control" id="onlineStartTimes" placeholder="请选择开始日期" />-
		      <input type="text" class="form-control" id="onlineEndTimes" placeholder="请选择结束日期" />
		      <label >订单号：</label>
			  <input type="text" id="orderNumber" class="form-control" name="orderNumber" placeholder="订单号">
			  <button type="button" onclick="selectOrderBill()" class="btn btn-info">查询</button>
			  <button type="button" class="btn btn-info" onclick="exportMineBill()">导出订单</button>
			  <iframe src="about:blank" name="hiddenIframe" class="hide"></iframe>
			</form>
		</div>
		<div style="overflow: auto; width: 100%;" class="bill-zong">
			<table class="table tab_css_1" id="bill-table1">
				<thead>
					<tr>
			          <th width="10%">订单号</th>
			          <th width="8%">运单号</th>
			          <th width="8%">账单状态</th>
			          <th width="15%">下单日期</th>
			          <th width="12%">线路</th>
			          <th width="8%">总费</th>
			          <th width="5%">已付</th>
			          <th width="5%">未付</th>
			          <th width="12%">操作</th>
			        </tr>
				</thead>
				<tbody id="getListorderBill">
				</tbody>
			</table>
		</div>
			<div id="pageList">
			</div>
	</div>
</div>
<div id="selectBill" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog" style="width:70%">
			<div class="modal-content">
				<!-- BEGIN form-->
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">账单详情</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<table border="1" width="100%" bordercolor="#cccccc">
							<tr>
								<td  class="td_colour td_style">订单信息</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-4">
											<span class="td_colour">订单号:</span><span class="td_colour" id="orderNumbers"></span>
										</div>
										<div class="col-sm-8">
											<span class="td_colour">运单号:</span><span class="td_colour" id="wayBillNumber"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">发货人:</span><span class="td_colour"  id="consignorName"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">地址:</span><span class="td_colour"  id="consignorProvince"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">手机号:</span><span class="td_colour"  id="consignorPhoneNumber"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">收货人:</span><span class="td_colour"  id="consigneeName"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">地址:</span><span class="td_colour"  id="consigneeProvince"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">手机号:</span><span class="td_colour"  id="consigneePhoneNumber"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">用户:</span><span class="td_colour"  id="loginName"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">账单状态:</span><span class="td_colour"  id="state"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">下单时间:</span><span class="td_colour"  id="payDate"></span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td_colour td_style">预估费用信息</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-4">
											<span class="td_colour">预估运费:￥</span><span class="td_colour" id="estimateFreight"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">预估提货费:￥</span><span class="td_colour" id="estimateTakeCargoCost"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">预估送货费:￥</span><span class="td_colour"  id="estimateSendCargoCost"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">预估装货费:￥</span><span class="td_colour"  id="estimateLoadCargoCost"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">预估卸货费:￥</span><span class="td_colour"  id="estimateUnloadCargoCost"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">保险费:￥</span><span class="td_colour"  id="insurance"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">代收款手续费:￥</span><span class="td_colour"  id="agencyFundPoundage"></span>
										</div>
										<div class="col-sm-4">
											<span style="color: red">预估总费用:￥</span><span style="color: red"  id="estimateTotalCost"></span>
										</div>
										<div class="col-sm-4">
											<span style="color: red">已付费用:￥</span><span style="color: red"  id="estimateTakeCargoCosts"></span>
										</div>
									<!-- 	<div class="col-sm-4">
											<span style="color: red">预估未付费用:￥</span><span style="color: red"  id="yufuweifu"></span>
										</div> -->
									</div>
								</td>
							</tr>
							<tr>
								<td class="td_colour td_style">确认费用信息</td>
								<td>
									<div style="line-height: 40px;">
										<div class="col-sm-4">
											<span class="td_colour">运费:￥</span><span class="td_colour"  id="freight"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">提货费:￥</span><span class="td_colour"  id="takeCargoCost"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">送货费:￥</span><span class="td_colour"  id="sendCargoCosts"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">装货费:￥</span><span class="td_colour"  id="loadCargoCosts"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">卸货费:￥</span><span class="td_colour"  id="unloadCargoCosts"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">保险费:￥</span><span class="td_colour"  id="insurances"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">代收款手续费:￥</span><span class="td_colour"  id="agencyFundPoundages"></span>
										</div>
										<div class="col-sm-4">
											<span class="td_colour">其他费用:￥</span><span class="td_colour"  id="otherCosts"></span>
										</div>
										<div class="col-sm-4">
											<span style="color: red">总费用:￥</span><span style="color: red"  id="totalCost"></span>
										</div>
										<div class="col-sm-4">
											<span style="color: red">已付费用:￥</span><span style="color: red"  id="prepaidCost"></span>
										</div>
										<div class="col-sm-4">
											<span style="color: red">未付费用:￥</span><span style="color: red"  id="unpaidCost"></span>
										</div>
									</div>
								</td>		
							</tr>
						</table>
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
       <div id="yunChongzhi" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="yun_chongzhi" target="_blank" action="/logisticsc/orderCenter/doOrderMoney.shtml" method="post">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">支付</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">支付金额：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<input type="text" readonly="readonly" name="amount" id="zhifujine" style="display:inline-block;width:250px" class="form-control required"/>
								<input type="text" id="zhifuOrderNumber" name="orderNumber" style="display: none;">
								<input type="text" id="orderType"  name="orderType" style="display: none;" value="2">
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><input type="radio" style="width:10px;height:7px;" checked="checked">易宝支付：</label>
							<div class="col-sm-8"  style="padding-top: 9px;">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<span style="font-size: 14px;">(通过易宝支付完成在线支付)</span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<!-- <button type="button" class="btn btn-success btn-save" id="zhifu">确认支付</button> -->
						<input type="submit" class="btn-qr" value="确认支付"/>
					</div>
			</form>
		</div>
	</div>
</div>
<div id="successModalzhifu" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="tiaozhuan()">×</span></a>
			</div>
			<div class="modal-body success-info">
				 <h5 style="color: #6f9fd9!important;font-weight:bold">成功提示:<span id="successModalMsgszhifu"></span></h5>
			</div>
		</div>
	</div>
</div>
	<div style="clear: both;"></div>
	<jsp:include page="../../bottom.jsp"></jsp:include>
</body>
</html>