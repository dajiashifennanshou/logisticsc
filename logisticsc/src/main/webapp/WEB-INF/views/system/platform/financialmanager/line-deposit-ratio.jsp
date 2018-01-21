<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>线路预存比例</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>线路预存比例</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
				        	<label class="control-label">审核状态：</label>
					        <select id="status">
					        	<option value="">全部</option>
					        	<c:forEach items="${depositRatioStatusList}" var="depositRatioStatus">
					        		<option value="${depositRatioStatus.id}">${depositRatioStatus.name}</option>
					        	</c:forEach>
					        </select>
				        	
				        	<label class="control-label">开始时间：</label>
					        <input type="text" id="startTime" class="calendar">
					        
					        <label class="control-label">结束时间：</label>
					        <input type="text" id="endTime" class="calendar">
					        
				            <input type="text" id="condition" class="input-normal" placeholder="专线/物流商">
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
				title : '线路',
				elCls : 'center',
				dataIndex : 'lineName'
			}, {
				title : '预存比例',
				elCls : 'center',
				dataIndex : 'depositRatio',
				renderer : function(value){
					return '1 : ' + value;
				}
			}, {
				title : '开始时间',
				elCls : 'center',
				dataIndex : 'startTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '结束时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'endTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '审核状态',
				elCls : 'center',
				dataIndex : 'status',
				renderer : function(value){
					if(value==1){
						return '未发布';
					}else if(value==2){
						return '审核中';
					}else if(value==3){
						return '已发布';
					}else if(value==4){
						return '发布失败';
					}else if(value==5){
						return '已过期';
					}
				}
			}, {
				title : '审核时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'checkTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/system/financial/getdepositratiolist.shtml',
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
			'status' : $('#status').val(),
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