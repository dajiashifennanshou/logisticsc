<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>外包管理</title>
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
				<h3>外包管理</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
					        <label class="control-label">外包状态</label>
					        <select class="input-normal" id="status">
					        	<option value="">全部</option>
					        	<c:forEach items="${outDepartStatusList}" var="outDepartStatus">
					        		<option value="${outDepartStatus.id}">${outDepartStatus.name}</option>
					        	</c:forEach>
					        </select>
				        	
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号/目的地">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="outDepartList"></div>
						<div id="outDepartListBar"></div>
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
		loadOutDepartList();
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
	function loadOutDepartList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
				Toolbar) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '外包时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'outSourceTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '外包发车单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'outDepartNumber'
			}, {
				title : '外包状态',
				elCls : 'center',
				dataIndex : 'status',
				renderer : function(value){
					if(value == 0){
						return '配载中';
					}else if(value == 1){
						return '已外包';
					}else if(value == 2){
						return '已作废';
					}
				}
			}, {
				title : '出发网点',
				elCls : 'center',
				dataIndex : 'startOutletsName'
			}, {
				title : '目的地',
				elCls : 'center',
				dataIndex : 'destination'
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
				title : '外包费合计',
				elCls : 'center',
				dataIndex : 'outSourceCost'
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
				dataIndex : 'operatePersonName'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/outdepart/search.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#outDepartList',
				columns : columns,
				store : store,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                	btnCls : 'button button-normal',
	                    text:'继续配载',
	                    handler : continueStorage
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'作废',
	                    handler : discard
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查看详情',
	                    handler : showOutDepartListDetail
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'外包出库',
	                    handler : outSource
	                }]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#outDepartListBar',
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

	// 继续配载
	function continueStorage(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length < 1){
			BUI.Message.Alert('请选择');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一条记录');
			return;
		}
		var selected = selection[0];
		if(selected.status == 0){
			BUI.Message.Confirm('确定要继续配载码？',function(){
				var outDepartNumber = selected.outDepartNumber;
	        	window.location.href = '<%=request.getContextPath()%>/tms/outdepart/tooutsourcepage.shtml?outDepartNumber='+outDepartNumber;
    		},'question');
    	}else{
    		BUI.Message.Alert('不允许此操作','error');
    	}
	}
	
	// 外包出库
	function outSource(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		var length = selection.length;
		if(length < 1){
			BUI.Message.Alert('请选择');
			return;
		}
		var outDepartLists = [];
		for(var i = 0; i < selection.length; i++){
			if(selection[i].status != 0){
				BUI.Message.Alert('不允许此操作','error');
				return;
			}
			var outDepartList = new Object();
			outDepartList.outDepartNumber = selection[i].outDepartNumber;
			outDepartList.wayBillNumbers = selection[i].wayBillNumbers;
			outDepartLists.push(outDepartList);
		}
		BUI.Message.Confirm('确认要出库？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/outdepart/outsource.shtml',
				data : { 'outDepartLists' : JSON.stringify(outDepartLists) },
				success : function(result){
					if(result > 0){
						BUI.Message.Alert('操作成功','success');
						search();
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
	}

	// 作废
	function discard(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		var length = selection.length;
		if(length < 1){
			BUI.Message.Alert('请选择');
			return;
		}
		var outDepartNumbers = [];
		for(var i = 0; i < selection.length; i++){
			if(selection[i].status != 0){
				BUI.Message.Alert('只有配载中的发车单能被作废','warning');
				return;
			}
			outDepartNumbers.push(selection[i].outDepartNumber);
		}
		BUI.Message.Confirm('确认要作废？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/outdepart/discard.shtml',
				data : { 'outDepartNumbers' : JSON.stringify(outDepartNumbers) },
				success : function(result){
					if(result > 0){
						BUI.Message.Alert('操作成功','success');
						search();
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
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
		window.location.href = '<%=request.getContextPath()%>/tms/outdepart/tooutdepartlistdetail.shtml?outDepartNumber='+selection[0].outDepartNumber;
	}
	
	//跳转到异常登记页面
	function toAbnormal(){
		if(!validateUserType()){
			return;
		}
		window.location.href="<%=request.getContextPath()%>/tms/abnormal/addabnormal.shtml";
	}
	</script>
</body>
</html>