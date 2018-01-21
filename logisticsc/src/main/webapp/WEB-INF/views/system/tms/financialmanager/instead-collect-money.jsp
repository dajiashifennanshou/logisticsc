<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>代收货款</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>代收货款</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
				        	<label class="control-label">开始时间：</label>
					        <input type="text" id="startTime" class="calendar">
					        
					        <label class="control-label">结束时间：</label>
					        <input type="text" id="endTime" class="calendar">
					        
				            <input type="text" id="condition" class="input-normal" placeholder="专线/物流商/运单号">
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
				title : '专线/物流商',
				elCls : 'center',
				dataIndex : 'companyName'
			}, {
				title : '运单号',
				elCls : 'center',
				dataIndex : 'wayBillNumber'
			}, {
				title : '代收货款',
				elCls : 'center',
				dataIndex : 'agencyFund'
			}, {
				title : '发货人',
				elCls : 'center',
				dataIndex : 'consignor'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consignee'
			}, {
				title : '垫付货款',
				dataIndex : 'advanceCost'
			}, {
				title : '实收货款',
				dataIndex : 'actualCollectMoney'
			}, {
				title : '已转货款',
				dataIndex : 'transferredMoney'
			}, {
				title : '收款人',
				dataIndex : 'collectMoneyPerson'
			}];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/system/tms/financial/getinsteadcollectmoneylist.shtml',
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