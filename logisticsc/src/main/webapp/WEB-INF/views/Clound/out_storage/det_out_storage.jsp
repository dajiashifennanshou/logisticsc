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
		//打印
		function printPage(ele){
			yc_public.loading();
			$(ele).hide();
			window.print();
			$(ele).show();
			yc_public.closeLoading();
		}
		$(function(){
			var grid = null;
			var tabId='#det_stowage_tab';
			var detStorage_={
				newGird:function(tabId,data){
					grid = $(tabId).jqGrid({
						url:"getYcOutGoodsList.yc",
						datatype: "json",
						postData:data,
						autowidth: true,
						height: '100%',
						colNames:['配载单号','运单号','货物名称','货物品牌','生产厂家','货物重量','货物体积','货物数量'],
						colModel:[
							{name:'stowageNo',align:'center'},
							{name:'wailBillNo',align:'center'},
							{name:'goodsName',align:'center'},
							{name:'goodsBrand',align:'center'},
							{name:'vender',align:'center'},
							{name:'weight',align:'center'},
							{name:'volume',align:'center'},
							{name:'goodsCount',align:'center'}
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
			yc_public.ajax({"url":"getYcOutstorageSingle.yc","data":{"id":id},"success":function(data){
				if(data.code==0){
					yc_public.setData('det_out_form', data.data);
					detStorage_.newGird(tabId,{stowageNo:data.data.stowageNo});
					var crt=$("#createTime").val();
					$("#createTime").val(DateUtil.RemoveZero(crt));
				}
			}});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">出库单详情</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
			<form class="form-horizontal" id="det_out_form"  role="form">
				<div class="step-pane active" data-step="1">
						<input type="hidden" group="val" name="id"/>
						<!-- input标签 -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="dealerId">配载单号</label>
							<div class="col-sm-9">
								<input type="text"  name="stowageNo" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">网点编号</label>
							<div class="col-sm-9">
								<input type="text"  name="branchNo" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="branchNo">出库时间</label>
							<div class="col-sm-9">
								<input type="text"  name="createTime" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zoneNo">出库操作人</label>
							<div class="col-sm-9">
								<input type="text"  name="createUser" class="col-xs-10 col-sm-5" />
							</div>
						</div>
				</div>
			</form>
			</div>
			
			<div class="wizard-actions">
				<a onclick="printPage(this)" class="btn btn-success btn-next" data-last="Finish">
					打印
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>

				<!-- /section:plugins/fuelux.wizard.buttons -->
			</div>
		</div>
	</div>
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">出库货物详情</h4>
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