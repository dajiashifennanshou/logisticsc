<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>专线钱包</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>专线钱包</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
				            <input type="text" id="condition" class="input-normal" placeholder="专线/物流商/真实姓名">
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
				title : '物流商名称',
				elCls : 'center',
				dataIndex : 'companyName'
			}, {
				title : '组织代码',
				elCls : 'center',
				dataIndex : 'companyCode'
			}, {
				title : '网点名称',
				elCls : 'center',
				dataIndex : 'outletsName'
			}, {
				title : '登录账号',
				elCls : 'center',
				dataIndex : 'loginName'
			}, {
				title : '真实姓名',
				elCls : 'center',
				dataIndex : 'trueName'
			}, {
				title : '银行卡号',
				elCls : 'center',
				dataIndex : 'bankNumber'
			}, {
				title : '余额',
				elCls : 'center',
				dataIndex : 'balanceMoney'
			}, {
				title : '操作',
				elCls : 'center',
				dataIndex : '',
				renderer : function(value, obj, index){
					return '<a href="javascript:void(0)" onclick="findBlance('+obj.id+','+index+')">查看余额</a>';
				}
			}];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/system/tms/financial/getplatformlineuser.shtml',
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
	
	// 查看余额
	function findBlance(id, index){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/system/tms/financial/findblance.shtml',
			data : { 'id' : id },
			success : function(result){
				var data = result.data;
				if(data != null && data != '' && typeof(data) != 'undefined'){
					var item = grid.getItemAt(index);
					item.balanceMoney = data.split(':')[1];
					grid.updateItem(item);
				}
			}
		});
	}
	</script>
</body>
</html>