<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>云仓管理系统-入库记录详情</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript">
		$(function(){
			var grid = null;
			var tabId='#det_stowage_tab';
			var pagerId='#det_storage_pager';
			var detStorage_={
				newGird:function(tabId,pagerId,data){
					grid = $(tabId).jqGrid({
						url:"getYcInGoodsList.yc",
						datatype: "json",
						postData:data,
						autowidth: true,
						height: '100%',
						colNames:['所属运单','货物名称','货物品牌','货物型号','重量(T)','体积(m³)','数量','介绍'],
						colModel:[
							{name:'wayBillNo',align:'center'},
							{name:'goodsName',align:'center'},
							{name:'goodsBrand',align:'center'},
							{name:'model',align:'center'},
							{name:'weight',align:'center'},
							{name:'volume',align:'center'},
							{name:'goodsNum',align:'center'},
							{name:'elseExplan',align:'center'}
						],  
						viewrecords : true,
						page:1,
						rowNum:10,
						pager : pagerId,
						rowList:[10,20,30],
						altRows: true,
						multiselect: true,
						loadComplete : function() {
							var table = this;
							setTimeout(function(){
								pagerIcons(table);
							}, 0);
						}
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
			var id=request.QueryString("id");
			yc_public.ajax({"url":"getYcInstorageSingle.yc","data":{"id":id},"success":function(data){
				if(data.code==0){
					if(data.data){
						yc_public.setData("mod_in_form",data.data);
						detStorage_.newGird(tabId,pagerId,{'wayBillNo':data.data.waybillNo});
					}
				}
			}});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">入库详情</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="mod_in_form"  role="form">
					<input type="hidden" group="val" name="id"/>
					<div class="step-pane active"  data-step="1">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">网点编号</label>
							<div class="col-sm-9">
								<input type="text" disabled="disabled" group="val" name="branchNo" valited="required" placeholder="网点编号" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zoneNo">库区编号</label>
							<div class="col-sm-9">
								<input type="text" disabled="disabled" group="val" name="zoneNo" valited="required" placeholder="运单号" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="inType">入库类型</label>
							<div class="col-sm-9">
								<select group="val" name="inType" valited="required" class="select2-container select2-allowclear inSel tag-input-style">
									<option value=""></option>
									<option value="0">直接入库</option>
									<option value="1">扫描入库</option>
									<option value="2">手工入库</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="">备注</label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-5" group="val" name="remark"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
				
			</div>
		</div>
		<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">入库货物详情</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<div class="step-content pos-rel">
					<div class="step-pane active" data-step="1">
							<table id="det_stowage_tab"></table>
							<div id="det_storage_pager"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>