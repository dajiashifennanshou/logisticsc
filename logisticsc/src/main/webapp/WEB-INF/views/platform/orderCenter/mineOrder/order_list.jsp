<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
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
<script src="${configProps['resources']}/platform/js/orderList.js"></script>
<link href="${configProps['resources']}/platform/css/dingdanzx.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/xingji.css" rel="stylesheet">
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
<jsp:include page="../../top.jsp" flush="true"></jsp:include>
<div id="#container">
	<div class="container-self">
		<div class="frame_left">
			<jsp:include page="../order_left.jsp"></jsp:include>
		</div>
		<div class="demo-content platformStyle">
			<div class="clearfix">
				<form class="form-inline" style="margin-top: 13px; margin-left: 18px;">
				  <label>订单状态：</label>
		          	<select id="orderSate" class="form-control fcjselect" name="state">
		          		<option value="">请选择</option>
		          	</select>
			      <label >下单日期：</label> 
				      <input type="text" class="form-control" id="onlineStartTimes" placeholder="请选择开始日期" />-
				      <input type="text" class="form-control" id="onlineEndTimes" placeholder="请选择结束日期" />
			      <label >订单号：</label>
				  	  <input type="text" id="condition" class="form-control" name="condition" placeholder="订单号/运单号">
					  <button type="button"  onclick="selectMineOrder()" class="btn btn-info" >查询</button>
					  <button type="button" class="btn btn-info" onclick="exportMineOrder()" >导出订单</button>
					  <iframe src="about:blank" name="hiddenIframe" class="hide"></iframe>
				</form>
			</div>
			<div class="nav-top2 f">
				<div class="p3">
					<p class="f" style="margin-top: -4px;">便利提醒：</p>
					<span onclick="caogao()" class="biaoqian ju-left">未支付<i><span>(</span><span id="caogao"></span><span>)</span></i></span>
					<span onclick="yijiangzhong()" class="biaoqian ju-left">议价中<i><span>(</span><span id="daifukuan"></span><span>)</span></i></span>
					<span onclick="yiquxiao()" class="biaoqian ju-left">已取消<i><span>(</span><span id="yiquxiao"></span><span>)</span></i></span>  
					<span onclick="yizuofei()" class="biaoqian ju-left">已作废<i><span>(</span><span id="yizuofei"></span><span>)</span></i></span>  
					<span onclick="yijujue()" class="biaoqian ju-left">已拒绝<i><span>(</span><span id="yijujue"></span><span>)</span></i></span> 
					<span onclick="yiqianshou()" class="biaoqian ju-left">已签收<i><span>(</span><span id="yiqianshou"></span><span>)</span></i></span> 
					<span onclick="baoxian()" class="biaoqian ju-left">保险<i><span>(</span><span id="baoxian"></span><span>)</span></i></span>  
				</div>
			</div>
			<div style="overflow: auto; width: 100%;">
				<table class="table tab_css_1" id="dingdan-zt">
					<thead>
						<tr>
						<th>订单号</th>
						<th>运单号</th>
						<th width="15%">物流商/线路</th>
						<th>货物信息</th>
						<th>预约</th>
						<th>预估</th>
						<th>确认</th>
						<th>未付</th>
						<th>时间</th>
						<th>状态</th>
						<th width="15%">操作</th>
						</tr>
					</thead>
					<tbody id="getMineOrder">
					</tbody>
				</table>
			</div>
			<div id="pageList">
			</div>
	</div>
</div>
</div>
<div class="footer">
    <div style="clear: both;"></div>
	<jsp:include page="../../bottom.jsp"></jsp:include>
</div>
<div id="selectOrder" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
			<div class="modal-content">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">订单详情</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<table border="1" width="100%" bordercolor="#cccccc">
							<tr>
								<td  class="td_colour td_style">订单信息</td>
							</tr>
							<tr>
								<td class="td_colour td_style">账单信息</td>
							</tr>
						</table>
					</div>
			</div>
		</div>
</div>
<div id="addEvaluate" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="add_evaluation">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">评论信息</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group"  style="margin-top: 14px;">
							
							<label class="control-label col-sm-4">订单编号</label>
							<div class="col-sm-8">
								<input type="text" readonly="readonly" style="position:relative;display:inline-block;width:250px" id="orderNumber" name="orderNumber" class="form-control required"/>
							</div>
						</div>
						<div class="form-group"   style="margin-top: 28px;">
							
							<label class="control-label col-sm-4">评价内容</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="evaluateContentMsg"></label>
								<textarea class="form-control" rows="3" style="position:relative;display:inline-block;width:250px" id="evaluateContent" name="evaluateContent" placeholder="请输入评价内容"></textarea>
							</div>
						</div>
						<div class="form-group"  style="margin-top: 28px;">
							<label class="control-label col-sm-4">评价星级</label>
							<div class="col-sm-8">
							<div class="cont">
								<label class="login-sjh" id="xingjiMsg"></label>
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
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" id="pingjia">保存</button>
					</div>
			</form>
				<!-- END form-->
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<div id="addComplain" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="add_complain">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">投诉信息</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 14px;" >
							<label class="control-label col-sm-4">订单编号</label>
							<div class="col-sm-8">
								<input type="text" readonly="readonly" style="display:inline-block;width:250px;" id="orderNumbers" name="orderNumber" class="form-control required">
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;" >
							<label class="control-label col-sm-4">投诉内容</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="complaintContentMsg"></label>
								<textarea class="form-control" style="display:inline-block;width:250px;" rows="3" id="complaintContent" name="complaintContent" placeholder="请输入投诉内容"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" id="toushu">保存</button>
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
								<label class="control-label col-sm-4">订单类型：</label>
								<div class="col-sm-8">
									<label class="login-sjh" id=""></label>
									<input type="text" readonly="readonly" style="display:inline-block;width:250px" id="dingdanleixing" class="form-control required"/>
									<input type="text" id="orderType" name="orderType" style="display: none;">
								</div>
						</div>		
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">支付金额：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<input type="text" readonly="readonly" name="amount" id="zhifujine" style="display:inline-block;width:250px" class="form-control required"/>
								<input type="text" id="zhifuOrderNumber" name="orderNumber" style="display: none;">
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><input type="radio" style="width:10px;height:7px;" checked="checked">易宝支付：</label>
							<div class="col-sm-8"  style="padding-top: 8px;">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<span style="font-size: 14px;">(通过易宝支付完成网银支付)</span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<!-- <button type="button" class="btn btn-success btn-save" id="zhifu">确认支付</button> -->
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
     <div id="quxiaoModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">温馨提示！</h4>
              		<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="shuaxin()">×</span></a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #6f9fd9!important;font-weight:bold">温馨提示:<span id="quxiaoMsg">确定取消该订单吗？</span></h5>
                </div>
               <div class="modal-footer">
                	<button type='button' class='btn btn-success' style='margin-top:5px;' onclick="back()">确定</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>