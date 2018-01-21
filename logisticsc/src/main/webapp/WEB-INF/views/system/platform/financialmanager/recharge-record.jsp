<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员充值记录</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>会员充值记录</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
				        	<label class="control-label">开始时间：</label>
					        <input type="text" id="startTime" class="calendar">
					        
					        <label class="control-label">结束时间：</label>
					        <input type="text" id="endTime" class="calendar">
					        
				            <input type="text" id="condition" class="input-normal" placeholder="姓名/手机号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="consumeRecordList"></div>
						<div id="consumeRecordListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var store, grid;
	$(function(){
		loadConsumeRecordList();
		loadBuiCalendar();
	});
	
	// 加载运单列表
	function loadConsumeRecordList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '用户',
				elCls : 'center',
				dataIndex : 'userName'
			}, {
				title : '手机号',
				elCls : 'center',
				dataIndex : 'mobile'
			}, {
				title : '充值金额',
				elCls : 'center',
				dataIndex : 'money'
			}, {
				title : '充值卡号',
				elCls : 'center',
				dataIndex : 'consumeCard'
			}, {
				title : '充值时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'consumeTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/system/financial/getrechargerecord.shtml',
				autoLoad : true,
				proxy : { method : 'post' },
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#consumeRecordList',
				columns : columns,
				store : store,
				forceFit : true,
				plugins : [Grid.Plugins.CheckSelection]
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#consumeRecordListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}
	
	// 查询
	function search(){
		var params = {
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 加载BUI日历插件
	function loadBuiCalendar(){
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
	}
	</script>
</body>
</html>