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
						url:"getGoodsPagerByDeliveryNo.yc",
						datatype: "json",
						postData:data,
						autowidth: true,
						height: '100%',
						colNames:['所属运单','货物名称','货物品牌','生产厂家','重量','体积','数量','介绍'],
						colModel:[
							{name:'wayBillNo',align:'center'},
							{name:'goodsName',align:'center'},
							{name:'goodsBrand',align:'center'},
							{name:'vender',align:'center'},
							{name:'weight',align:'center'},
							{name:'volume',align:'center'},
							{name:'goodsNum',align:'center'},
							{name:'elseExplan',align:'center'}
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
			yc_public.ajax({"url":"getYcDeliveryOrderSingle.yc","data":{"id":id},"success":function(data){
				if(data.code==0){
					yc_public.setData('mod_delivery_order_form', data.data);
					detStorage_.newGird(tabId,{dNo:data.data.deliveryNo});
				}
			}});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">修改配送安装费用</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
			<form class="form-horizontal" id="mod_delivery_order_form"   role="form">
					<div class="step-pane active" data-step="1">
						<!-- input标签 -->
						<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="consigneeName">客户姓名 </label>
							<div class="col-sm-9">
								<input type="text" group="val" valited="required" name="consigneeName" required="true" placeholder="客户姓名" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="consigneePhone">客户电话 </label>
								<div class="col-sm-9">
									<input type="text" group="val" valited="required,phone" name="consigneePhone" required="true"  placeholder="客户电话" class="col-xs-10 col-sm-5" />
								</div>									 
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="consigneeAddr">客户地址</label>
							<div class="col-sm-9">
								<input type="text" group="val" valited="required" name="consigneeAddr"  placeholder="客户地址" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="installCost">安装费用</label>
							<div class="col-sm-9">
								<input type="text" group="val" readonly="readonly" name="installCost"  placeholder="安装费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="installCost">代收款</label>
							<div class="col-sm-9">
								<input type="text" group="val"  id="agentPaidCost" name="agentPaidCost" value="0" placeholder="代收费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="installCost">支付类型</label>
							<div class="col-sm-9">
								<select group="val"  name="payType" valited="required" id='payType' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">
									<option value="0">现付</option>
									<option value="1">到付</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="subscribeTime">预约配送时间</label>
							<!-- #section:plugins/date-time.datetimepicker -->
							 <div class="col-sm-9">
								<input id="subscribeTime" group="val" name="subscribeTime" valited="required,date"  type="text" placeholder="请选择配送时间" data-date-format="YYYY-MM-DD HH:mm:ss" class="col-xs-10 col-sm-5" />
							</div> 
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="deliveryCost">配送费用</label>
							<div class="col-sm-9">
								<input type="text" readonly="readonly" group="val" value="0" name="deliveryCost"  placeholder="配送费用" class="col-xs-10 col-sm-5" />
							</div>
						</div>
					</div>
					<!-- 下一页开始 -->
					<!-- 下一页结束 -->
				</form>
			</div>
							
		</div>
	</div>
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">订单货物详情</h4>
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