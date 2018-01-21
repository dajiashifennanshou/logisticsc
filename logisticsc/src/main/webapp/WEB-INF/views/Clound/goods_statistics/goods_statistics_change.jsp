<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<title>库存调整</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/goods_statistics/goods_statistics_change.js"></script>
	<style type="text/css">
		#count{float: right;padding-top: 8px;padding-right: 10px;}#count span{color: #ff0000;}
	</style>
	<script type="text/javascript">
		$(function(){
			gs_.getYcJoin();
			gs_.getGoodsStatisticsSumCount();
			$('.select2').css('width','150').select2({allowClear:true});
			gs_.newGird("#gs_tab", "#gs_pager");
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">库存调整</h4>
	</div>
	<div class="widget-header" style="padding-top: 6px;">
		<form id="gs_from">
			<select group="val" name="dealerId" class="select2-container select2-allowclear select2 tag-input-style">
				<option value="">请选择加盟商</option>
			</select>
			<button type="reset" class="btn btn-purple btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				清空
			</button>
			<button type="button" class="btn btn-purple btn-sm" onclick="gs_.condSearch();">
				<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
				确  定
			</button>
			<div id="count">总货物数量：<span>0</span></div>
		</form>
	</div>
	<table id="gs_tab"></table>
	<div id="gs_pager"></div>
</div>
</body>
</html>
