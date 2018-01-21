<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<title>货物类型</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/goodsType/goodsType.js"></script>
	<script type="text/javascript">
		$(function(){
			goodsType_.newGird("#employee_tab", "#employee_pager");
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">货物类型</h4>
	</div>
	<div class="widget-header hide" style="padding-top: 6px;">
		<form id="emp_form">
			<input class="col-xs-2" group=val type="text" name="employeeName" placeholder="请输入员工姓名...">
			<button style="margin-left: 3px;" type="reset" class="btn btn-purple btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				清空
			</button>
			<button type="button" class="btn btn-purple btn-sm" onclick="employee_.condSearch();">
				<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
				确定
			</button>
		</form>
	</div>
	<table id="employee_tab"></table>
	<div id="employee_pager"></div>
</div>
</body>
</html>
