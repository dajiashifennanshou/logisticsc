<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<script type="text/javascript" src="/logisticsc/Clound/js/jquery-2.2.4.min.js"></script>
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
<script type="text/javascript" src="/logisticsc/Clound/js/Constant.js"></script>
<script type="text/javascript" src="/logisticsc/Clound/js/DateUtil.js"></script>
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<script src="${configProps['resources']}/platform/js/my_cloud.js"></script>
<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
<script type="text/javascript" src="/logisticsc/Clound/js/ValidateForm.js"></script>
<title>我的云仓</title>
</head>
<body>
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
<!-- 获取经销商id -->
<input id="user_id_feng" type="hidden" value="${user_session.id}" />
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="peronal_center_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
		<ul id="myTab" class="nav nav-tabs">
		   <li class="active"><a href="#my_goods" data-toggle="tab">我的货物</a></li>
		   <li><a href="#my_delivery" data-toggle="tab">我的配送单</a></li>
 		   <li><a href="#my_cost_center" data-toggle="tab">费用中心</a></li>
 		   
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="my_goods">
				<form class="form-inline" style="margin-top: 13px; margin-left: 18px;margin-bottom: 10px;">
			      <label >运单号：</label>
				  	  <input type="text" id="wayBillNo" class="form-control" name="deliveryNo" placeholder="运单号">
					  <button type="button"  onclick="my_goods()" class="btn btn-info" >查询</button>
					   <button type="button" class="btn" id="newOrder_" onclick="showSelect()">新增配送单</button>
				</form>
				<table class="table table-striped" id="dingdan-zt">
					<thead>
						<tr>
						 <th id="_goodsBoxTh" >选择</th>
						  <th>运单号</th>
				          <th>名称</th>
				          <th>类型</th>
				          <th>数量</th>
				          <th>重量</th>
				          <th>体积</th>
				          <th>状态</th>
				          <th>入库时间</th>
				        </tr>
					</thead>
					<tbody id="my_goods_list">
						<!-- 列表 -->
					</tbody>
				</table>
				<div id="my_goods_page">
					<!-- 分页 -->
				</div>
			</div> 
			<div class="tab-pane fade in" id="my_delivery">
				<form class="form-inline" style="margin-top: 13px; margin-left: 18px;margin-bottom: 10px;">
					
			      <label >配送单号：</label>
				  	  <input type="text" id="deliveryNo_1" class="form-control" placeholder="配送号">
					  <button type="button"  onclick="my_delivery()" class="btn btn-info" >查询</button>
				</form>
				<table class="table table-striped" id="dingdan-zt">
					<thead>
						<tr>
				          <th>配送单号</th>
				          <th>收货人</th>
				          <th>收货地址</th>
				          <th>收货电话</th>
				          <th>安装费</th>
				          <th>配送费</th>
				          <th>状态</th>
				          <th>创建时间</th>
				        </tr>
					</thead>
					<tbody id="my_delivery_list">
					
					</tbody>
				</table>
				<div id="my_delivery_page">
				</div>
			</div>
			<div class="tab-pane fade in" id="my_cost_center">
				<form class="form-inline" style="margin-top: 13px; margin-left: 18px;margin-bottom: 10px;">
					
			      <label >配送单号：</label>
				  	  <input type="text" id="deliveryNo_2" class="form-control" placeholder="配送号">
					  <button type="button"  onclick="my_cost_center()" class="btn btn-info" >查询</button>
				</form>
				<table class="table table-striped" id="dingdan-zt">
					<thead>
						<tr>
				          <th>配送单号</th>
				          <th>安装费</th>
				          <th>配送费</th>
				          <th>代收费</th>
				          <th>预付费</th>
				          <th>状态</th>
				          <th>操作</th>
				          <th>创建时间</th>
				        </tr>
					</thead>
					<tbody id="my_cost_center_list">
					
					</tbody>
				</table>
				<div id="my_cost_center_page">
				</div>
			</div>
		</div>
    </div>
</div>
</div>
<div id="selectGoods_" style="display:none;width:80px;height:120px;position:fixed;top:20%;right:5%;">
	<a href="javascript:;">查看货物(<label id="goodsNumber_">0</label>)</a><br/>
	<a href="javascript:;" onclick="showAddInfoPan()" >选货完成</a>
</div>
 <div style="clear: both;"></div>
<!-- 添加配送单 -->
<div id="addOrderPan"  class="modal fade in" style="opacity:1;">
	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal form-bordered" id="add_delivery_order">
				<div class="modal-header modal-info modal-success">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" id="myModalLabel">新增配送单</h4>
					<a class="close" data-dismiss="modal" style="margin-top: -23px;"><span onclick="tiaozhuan('addOrderPan')">×</span></a>
				</div>
				<div class="modal-body add-role-body">
					<div class="form-group" style="margin-top: 14px;" >
						<label class="control-label col-sm-4" for="consigneeName"><span style="color: red">*</span>&nbsp;&nbsp;客户姓名：</label>
						<div class="col-sm-8">
							<input type="text" group="val" valited="required" name="consigneeName" required="true" placeholder="客户姓名" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group" style="margin-top: 28px;"> 
						<label class="control-label col-sm-4" for="consigneePhone" >客户电话：</label>
						<div class="col-sm-8">
							<input type="text" group="val" valited="required,phone" name="consigneePhone" required="true"  placeholder="客户电话" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group" style="margin-top: 28px;">
						<label class="control-label col-sm-4" for="consigneeAddr" ><span style="color: red">*</span>&nbsp;&nbsp;客户地址：</label>
						<div class="col-sm-8">
							<input type="text" group="val" valited="required" name="consigneeAddr"  placeholder="客户地址" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group" style="margin-top: 28px;">
						<label class="control-label col-sm-4" for="agentPaidCost" ><span style="color: red">*</span>&nbsp;&nbsp;代收货款(元)：</label>
						<div class="col-sm-8">
							<input type="text" group="val"  id="agentPaidCost" name="agentPaidCost" value="0" placeholder="代收费用" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group" style="margin-top: 28px;">
						<label class="control-label col-sm-4" for="payType"><span style="color: red">*</span>&nbsp;&nbsp;支付类型：</label>
						<div class="col-sm-8">
							<select group="val"  name="payType" valited="required" id='payType' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">
								<option value="0">现付</option>
								<option value="1">到付</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 28px;">
						<label class="control-label col-sm-4" for="installCost" ><span style="color: red">*</span>&nbsp;&nbsp;安装费用：</label>
						<div class="col-sm-8">
							<input type="text" group="val" readonly="readonly" id="installCost" name="installCost" value="0" placeholder="安装费用" class="col-xs-10 col-sm-5" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a type="button" href="javascript:;" onclick="addOrder('add_delivery_order')" class="btn btn-success btn-save" id="submitOrder">保存</a>
				</div>
			</form>
		</div>
	</div>
</div>
 <div id="payModal" class="modal fade" tabindex="-2" data-backdrop="static">
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
					<input type="submit" class="btn btn-success" value="确认支付" onclick="wancheng()"/>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="querenModal" class="modal  fade" tabindex="-3" data-backdrop="static">
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
 <div class="footer">	
	<jsp:include page="../bottom.jsp"></jsp:include>
	</div>
</body>
</html>