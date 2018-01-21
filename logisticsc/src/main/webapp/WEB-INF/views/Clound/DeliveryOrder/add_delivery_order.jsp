<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>分销</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/DeliveryOrder/DeliveryOrderDepart.js"></script>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-multiselect.css" />
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/daterangepicker.css" />
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap-datetimepicker.css" />
	<script src="/logisticsc/Clound/assets/js/date-time/moment.js"></script>
	<script src="/logisticsc/Clound/assets/js/date-time/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript">
		var addGrid = null;
		var addStorage_=null;
		var addTabId='#add_order_tab';
		var addpagerId='#add_order_pager';
		//已选中的货物集合
		var _goodsObj={
				goodsIdList:new Array(),
				boxIsShow:false
		}
		$(function(){
			$("#add_add_order_tab").hide();
			$("#search_add_order_tab").hide();
		});
		$(function($) {
		$("#add_add_order_tab").hide();
		$("#search_add_order_tab").hide();
		deliveryCharge_.initSelect("#dealerId");
		deliveryCharge_.initDate();
		deliveryCharge_.init();
		deliveryCharge_.initJoiner();
		addStorage_={
			newGird:function(addTabId,pagerId,data){
				addGrid = $(addTabId).jqGrid({
					url:"getYcGoodsListByDealer.yc",
					datatype: "json",
					postData:data,
					autowidth: true,
					height: '100%',
					colNames:['选择','所属运单','货物名称','货物品牌','重量','体积','数量'],
					colModel:[
						{name:'',align:'center',formatter:function(val,opt,row){
							if(row.outStorageStatus==1){
								return "X";
							}else{
								return '<input '+(valiedInArray(_goodsObj.goodsIdList,row.wayBillNo+'_'+row.id+'_'+row.goodsType)?"checked=chcked":"")+' type="checkBox" name="selectGoodsBox_" onchange="boxchange(this)" value="'+row.id+'_'+row.wayBillNo+'_'+row.goodsType+'" />';
							}
						}},
						{name:'wayBillNo',align:'center'},
						{name:'goodsName',align:'center'},
						{name:'goodsBrand',align:'center'},
						{name:'weight',align:'center'},
						{name:'volume',align:'center'},
						{name:'goodsNum',align:'center'}
					],  
					viewrecords : true,
					page:1,
					rowNum:100,
					rowList:[10,20,30],
					altRows: true,
					pager : pagerId,
					multiselect: false,
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							pagerIcons(table);
						}, 0);
					}
				});
				$(addTabId).jqGrid("navGrid",addpagerId,{
					refresh: true,
					refreshtitle:"刷新",
					refreshicon:"ace-icon fa fa-refresh green"
				});
			}
		}

		//分页样式
		function pagerIcons(table) {
			var replacement = {
				'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
				'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
				'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
				'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
		}
		$('#dealerId option').eq(0).attr('selected', 'true');
		//初始加载
		addStorage_.newGird(addTabId,addpagerId,{'dealerId':$("#dealerId").val()});
	});
	//当经销商选择时
	function onDealerChange(ele){
		//dealerId
		var val=$(ele).val();
		addGrid.setGridParam({"postData":{'dealerId':val}}).trigger("reloadGrid");
		addStorage_.newGird(addTabId,addpagerId,{'dealerId':val});
	}
	/**
	 * 当选择框改变时
	 */
	function boxchange(ele){
		var s=ele.checked;
		var val=$(ele).val();
		if(s){
			if(!valiedInArray(_goodsObj.goodsIdList,val)){
				$('#goodsNumber_').html(_goodsObj.goodsIdList.push(val));
			}
		}else{
			var index=valiedInArray(_goodsObj.goodsIdList,val)
			if(index){
				_goodsObj.goodsIdList.splice(index,1);
				$('#goodsNumber_').html(_goodsObj.goodsIdList.length);
			}
		}
	}
	/**
	 * 验证字段是否在集合里
	 */
	function valiedInArray(arr,val){
		for(var v in arr){
			if(arr[v]==val){
				return v;
			}
		}
		return false;
	}
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">分销</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
			<form class="form-horizontal" id="add_delivery_order_form" >
					<div class="step-pane active" data-step="1">
							<!-- input标签 -->
							<div class="form-group"><label class="col-sm-3 control-label no-padding-right" for="joinerId">选择经销商 </label>
								<div class="col-sm-9">
									<select group="val" onselect="onDealerChange(this)" onchange="onDealerChange(this)" name="dealerId" valited="required" id='dealerId' class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">
										<!-- <option value="1">小美</option> -->
										<c:if test="${userList!=null }">
											<c:forEach items="${userList}" var="user">
												<option value="${user.id}"> ${user.trueName }-${user.joinName}-${user.loginName }</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="widget-header widget-header-blue widget-header-flat">
										<h4 class="widget-title lighter">选择经销商货物已选中(<b id="goodsNumber_">0</b>)件</h4>
									</div>
									<div class="widget-body">
										<div class="widget-main">
											<div class="step-content pos-rel">
												<div class=" active" >
														<table id="add_order_tab"></table>
														<div id="add_order_pager"></div>
												</div>
											</div>
										</div>
									</div>
							</div>
					</div>
					<div class="step-pane " data-step="2">
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
							<label class="col-sm-3 control-label no-padding-right" for="installCost">安装费用</label>
							<div class="col-sm-9">
								<input type="text" group="val" readonly="readonly" id="installCost" name="installCost" value="0"  class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="deliveryCost">配送费用</label>
							
							<div class="col-sm-9">
								<input type="text" readonly="readonly"  id="deliveryCost" name="deliveryCost"  required="true" value="0" class="col-xs-10 col-sm-5" />
							</div>
						</div>
					</div>
					<!-- 下一页开始 -->
					<!-- 下一页结束 -->
				</form>
			</div>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<a onclick="deliveryCharge_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="deliveryCharge_.addSubmit('add_delivery_order_form');" class="btn btn-success btn-next" data-last="Finish">
					下页
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>

				<!-- /section:plugins/fuelux.wizard.buttons -->
			</div>
							
		</div>
	</div>
</div>

</body>
</html>