<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
<head>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<title>评价</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/InstallEvaluate/install_evaluate.js"></script>
	<script type="text/javascript">
		$(function(){
			ie_.newGrid("#ie_tab", "#ie_pager");
			//下拉框初始化
			$('.select2').css("width","150px").select2({allowClear:true,minimumResultsForSearch: -1});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">评价</h4>
	</div>
	<div class="widget-header" style="padding-top: 6px;">
		<form id="ie_form">
			<select group="val" name="evaluateStatus" class="select2-container select2-allowclear select2 tag-input-style">
				<option value="">请选择配送单状态</option>
				<option value="0">待评价配送单</option>
				<option value="1">已评价配送单</option>
			</select>
			<button type="reset" class="btn btn-purple btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				清空
			</button>
			<button type="button" class="btn btn-purple btn-sm" onclick="ie_.condSearch();">
				<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
				确定
			</button>
		</form>
	</div>
	<table id="ie_tab"></table>
	<div id="ie_pager"></div>
</div>
</body>
</html>
