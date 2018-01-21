<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<title>云仓管理系统-加盟商管理</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinInfo/join_info.js"></script>
	<script type="text/javascript">
		$(function(){
			join_.newGrid("#join_tab", "#join_pager");
			$('.select2').css("width","200px").select2({allowClear:true,minimumResultsForSearch: -1});
		});
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">加盟商管理</h4>
	</div>
	<div class="widget-header" style="padding-top: 6px;">
		<form id="join_form">
			<select style="margin-left: 3px;" group=val name="applyStatus" class="select2-container select2-allowclear select2 tag-input-style">
				<option value="">请选择申请状态</option>
				<option value="0">待审核</option>
				<option value="1">已通过</option>
			</select>
			<button style="margin-left: 3px;" type="reset" class="btn btn-purple btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				清空
			</button>
			<button type="button" class="btn btn-purple btn-sm" onclick="join_.condSearch();">
				<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
				确定
			</button>
		</form>
	</div>
	<table id="join_tab"></table>
	<div id="join_pager"></div>
</div>
</body>
</html>
