<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-出库详情</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript">
		$(function(){
			var grid = null;
			var tabId='#det_stowage_tab';
			var detStorage_={
				newGird:function(tabId,data){
					grid = $(tabId).jqGrid({
						url:"getYcDeliveryOrderListBystNo.yc",
						datatype: "json",
						postData:data,
						autowidth: true,
						height: '100%',
						colNames:['网点编号','订单号','预约时间','下单经销商','收货人','收货地址','收货人电话','状态'],
						colModel:[
							{name:'branchNo',align:'center'},
							{name:'deliveryNo',align:'center'},
							{name:'subscribeTime',align:'center',formatter:function(val){
								return DateUtil.RemoveZero(val);
							}},
							{name:'dealerName',align:'center'},
							{name:'consigneeName',align:'center'},
							{name:'consigneeAddr',align:'center'},
							{name:'consigneePhone',align:'center'},
							{name:'orderStatus',align:'center',formatter:function(val){
								return Constant.DeliveryOrderStatus[val];
							}}
						],  
						viewrecords : true,
						page:1,
						rowNum:100,
						rowList:[10,20,30],
						altRows: true,
						multiselect: true,
						loadComplete : function() {
							var table = this;
							setTimeout(function(){
								//pagerIcons(table);
							}, 0);
						}
					});
				}
			}

			var id=request.QueryString("id");
			yc_public.ajax({"url":"getYcStowageSingle.yc","data":{"id":id},"success":function(data){
				if(data.code==0){
					yc_public.setData('det_form', data.data);
					detStorage_.newGird(tabId,{stowageNo:data.data.stowageNo});
					var sta=$("#stowageStatus").val();
					var crt=$("#createTime").val();
					var star=Constant.StowageStatus[sta];
					$("#stowageStatus").val(star.substring(star.indexOf('>')+1,star.lastIndexOf('<')));
					$("#createTime").val(DateUtil.RemoveZero(crt));
				}
			}});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">配载单信息</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
			<form class="form-horizontal" id="det_form"  role="form">
				<div class="step-pane active" data-step="1">
						<input type="hidden" group="val" name="id"/>
						<!-- input标签 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="dealerId">配载单号</label>
							<div class="col-sm-9">
								<input type="text" disabled="disabled" name="stowageNo" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">所配车辆</label>
							<div class="col-sm-9">
								<input type="text" disabled="disabled" name="carNo" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">配载状态</label>
							<div class="col-sm-9" id="statusShow">
								<input type="text" disabled="disabled" id="stowageStatus"  name="stowageStatus" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">创建时间</label>
							<div class="col-sm-9">
								<input type="text" disabled="disabled" id="createTime" name="createTime" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zoneNo">创建人</label>
							<div class="col-sm-9">
								<input type="text" disabled="disabled" name="createUser" class="col-xs-10 col-sm-5" />
							</div>
						</div>
				</div>
			</form>
			</div>
			
		</div>
	</div>
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">配载订单列表详情</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<div class="step-pane active" data-step="1">
						<table id="det_stowage_tab"></table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>