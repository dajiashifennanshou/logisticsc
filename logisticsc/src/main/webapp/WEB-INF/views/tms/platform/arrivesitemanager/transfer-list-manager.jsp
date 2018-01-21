<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中转列表</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.form-inline input{width: 100px;}
.form-inline select{width: 120px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>中转列表</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="中转发车单号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="transferDepartList"></div>
						<div id="transferDepartListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var grid, store;

	$(function(){
		loadCalendar();
		loadTransferDepartList();
	});

	// 验证用户类型
	function validateUserType(){
		var userType = ${tms_user_session.userType};
		if(userType == 0 || userType == 1){
			BUI.Message.Alert('请使用网点账号登录','warning');
			return false;
		}
		return true;
	}
	
	// 加载运单列表
	function loadTransferDepartList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
				Toolbar) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '中转时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'transferTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '中转发车单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'transferDepartNumber'
			}, {
				title : '承运单位',
				elCls : 'center',
				dataIndex : 'carriageCompany'
			}, {
				title : '发站联系人',
				elCls : 'center',
				dataIndex : 'startSitePerson'
			}, {
				title : '发站联系电话',
				elCls : 'center',
				dataIndex : 'startSitePhone'
			}, {
				title : '到站联系人',
				elCls : 'center',
				dataIndex : 'endSitePerson'
			}, {
				title : '到站联系电话',
				elCls : 'center',
				dataIndex : 'endSitePhone'
			}, {
				title : '中转费合计',
				elCls : 'center',
				dataIndex : 'transferCost'
			}, {
				title : '现付',
				elCls : 'center',
				dataIndex : 'currentPay'
			}, {
				title : '回付',
				elCls : 'center',
				dataIndex : 'backPay'
			}, {
				title : '操作员',
				elCls : 'center',
				dataIndex : 'operatePerson'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/transfer/gettransferlist.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#transferDepartList',
				columns : columns,
				store : store,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                    btnCls : 'button button-normal',
	                    text:'查看详情',
	                    handler : showOutDepartListDetail
	                }]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#transferDepartListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}

	// 加载日历插件
	function loadCalendar(){
		BUI.use('bui/calendar',function(Calendar){
	    	var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	        });
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

	// 查看详情
	function showOutDepartListDetail(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一个发车单','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tms/transfer/totransferlistdetail.shtml?transferDepartNumber='+selection[0].transferDepartNumber;
	}
	
	</script>
</body>
</html>