<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员提现记录</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>会员提现记录</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
					        <label class="control-label">审核状态：</label>
					        <select id="status">
					        	<option value="">全部</option>
					        	<c:forEach items="${takeCashStatusList}" var="takeCashStatus">
					        		<option value="${takeCashStatus.id}">${takeCashStatus.name}</option>
					        	</c:forEach>
					        </select>
				        	
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
						<div id="takeCashApplyList"></div>
						<div id="takeCashApplyListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="hide" id="reasonDialog">
		<form id="form" class="form-horizontal">
			<div class="row-fluid">
				<textarea style="width: 90%"></textarea>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
	var store, grid, reasonDialog;
	$(function(){
		loadTakeCashApplyList();
		loadBuiCalendar();
		loadReasonDialog();
	});
	
	// 加载运单列表
	function loadTakeCashApplyList(){
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
				title : '提现金额',
				elCls : 'center',
				dataIndex : 'money'
			}, {
				title : '状态',
				elCls : 'center',
				dataIndex : 'statusName'
			}, {
				title : '申请时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'applyTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '审核时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'auditTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '审核人',
				elCls : 'center',
				dataIndex : 'auditPerson'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/system/financial/gettakecashapplylist.shtml',
				autoLoad : true,
				proxy : { method : 'post' },
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#takeCashApplyList',
				columns : columns,
				store : store,
				forceFit : true,
				plugins : [Grid.Plugins.CheckSelection]
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#takeCashApplyListBar',
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
	
	// 审核通过
	function auditPass(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一条记录','warning');
			return;
		}
		BUI.Message.Confirm('确定吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/system/financial/takecashauditpass.shtml',
				data : { 'id' : selection[0].id },
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('操作成功','success');
						store.load();
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
	}
	
	// 加载 审核不通过原因 弹出框
	function loadReasonDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			reasonDialog = new Overlay.Dialog({
		    	title:'原因',
		        width:360,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'reasonDialog',
		        success:function () {
		        	BUI.Message.Confirm('确定吗？',function(){
		    			$.ajax({
		    				type : 'post',
		    				url : '<%=request.getContextPath()%>/system/financial/takecashauditnotpass.shtml',
		    				data : { 'id' : grid.getSelection()[0].id },
		    				success : function(result){
		    					if(result == 1){
		    						BUI.Message.Alert('操作成功','success');
		    						store.load();
		    						reasonDialog.close();
		    					}else{
		    						BUI.Message.Alert('操作失败','error');
		    					}
		    				}
		    			});
		    		},'question');
		        }
		    });
			reasonDialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	// 审核不通过
	function auditNotPass(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一条记录','warning');
			return;
		}
		reasonDialog.show();
	}
	</script>
</body>
</html>