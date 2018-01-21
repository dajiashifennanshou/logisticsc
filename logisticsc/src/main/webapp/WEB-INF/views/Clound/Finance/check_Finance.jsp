<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<title>审核</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/Finance/Finance.js"></script>
	<script type="text/javascript">
		$(function(){
			finance_.newGrid("#delivery_order_tab","#delivery_order_pager");
			$('.select2').css('width','150px').select2({allowClear:false});
			$("#add_delivery_order_tab").hide();
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">审核</h4>
	</div>
	<div class="widget-header" style="padding-top: 6px;">
		<form id="selectFrom">
			<select group="val" name="status" class="select2-container select2-allowclear select2 tag-input-style">
				<option value="">请选择收款状态</option>
				<option value="0">未收款</option>
				<option value="1">已收款</option>
			</select>
			<button type="reset" class="btn btn-purple btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				清空
			</button>
			<button type="button" class="btn btn-purple btn-sm" onclick="finance_.findSelect('selectFrom');">
				<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
				确  定
			</button>
			<button type="button" class="btn btn-purple btn-sm" >
				导出
			</button>
			<button type="button" class="btn btn-purple btn-sm">
				打印
			</button>
		</form>
	</div>
	<table id="delivery_order_tab"></table>
	<div id="delivery_order_pager"></div>
</div>
</body>
</html>
