<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<title>配载单</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/Stowage/Stowage.js"></script>
	<script type="text/javascript">
		$(function(){
			stowage_.newGrid("#stowage_tab","#stowage_pager");
			$('.select2').css('width','150px').select2({allowClear:false});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">配载单</h4>
	</div>
	<div class="widget-header" style="padding-top: 6px;">
		<form id="selectFrom">
			<select group="val" name="stowageStatus" class="select2-container select2-allowclear select2 tag-input-style">
				<option value="">请选择配载单状态</option>
				<option value="0">待配送</option>
				<option value="1">在途</option>
				<option value="2">已完成</option>
			</select>
			<button type="reset" class="btn btn-purple btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				清空
			</button>
			<button type="button" class="btn btn-purple btn-sm" onclick="stowage_.findSelect('selectFrom');">
				<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
				确  定
			</button>
		</form>
	</div>
	<table id="stowage_tab"></table>
	<div id="stowage_pager"></div>
</div>
</body>
</html>
