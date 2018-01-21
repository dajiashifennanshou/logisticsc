<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<title>采购</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/in_storage/in_storage.js"></script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">采购</h4>
	</div>
	<!-- <div class="widget-header" style="padding-top: 6px;">
		<form id="selectFrom">
			<select group="val" name="waybillSource" class="select2-container select2-allowclear select2 tag-input-style">
				<option value="">请选择订单来源</option>
				<option value="0">线上订单</option>
				<option value="1">线下订单</option>
			</select>
			<button type="reset" class="btn btn-purple btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				清空
			</button>
			<button type="button" class="btn btn-purple btn-sm" onclick="inStorage_.findSelect('selectFrom');">
				<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
				确  定
			</button>
		</form>
	</div> -->
	<table id="in_storage_tab"></table>
	<div id="in_storage_pager"></div>
</div>
</body>
<script type="text/javascript">
		$(function(){
			inStorage_.newGird("#in_storage_tab", "#in_storage_pager");
			$('.select2').css('width','150px').select2({allowClear:false});
			$("#edit_in_storage_tab").hide();
			$("#search_in_storage_tab").hide();
		});
</script>
</html>
