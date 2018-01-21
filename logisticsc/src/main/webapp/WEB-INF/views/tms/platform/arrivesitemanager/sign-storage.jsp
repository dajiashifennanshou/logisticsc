<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签到入库</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<input type="hidden" id="currentOutlets" value="${tms_user_session.outletsId}">
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>签到入库</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
					        <label class="control-label">发车单状态</label>
					        <select class="input-normal" id="departStatus"></select>
				        	
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="wayBillOrderList"></div>
						<div id="wayBillOrderListBar"></div>
					</div>
				</div>
			</div>
		</div>
		<form id="departListDataForm" action="<%=request.getContextPath()%>/tms/signstorage/topartstoragepage.shtml" method="post">
			<input type="hidden" id="departListData" name="departListData">
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var grid, store;
	$(function(){
		loadCalendar();
		loadDepartListStatus();
		loadDepartList();
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
	function loadDepartList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
				Toolbar) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '发车单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'departNumber'
			}, {
				title : '发车状态',
				elCls : 'center',
				dataIndex : 'statusName'
			}, {
				title : '出发网点',
				elCls : 'center',
				dataIndex : 'startOutletsName'
			}, {
				title : '途经网点',
				width : 150,
				elCls : 'center',
				dataIndex : 'wayOutletsName'
			}, {
				title : '到达网点',
				elCls : 'center',
				dataIndex : 'targetOutletsName'
			}, {
				title : '车牌号',
				elCls : 'center',
				dataIndex : 'vehicleNumber'
			}, {
				title : '司机姓名',
				elCls : 'center',
				dataIndex : 'driverName'
			}, {
				title : '司机电话',
				elCls : 'center',
				dataIndex : 'driverPhone'
			}, {
				title : '应付运费',
				elCls : 'center',
				dataIndex : 'shouldPayDriverCost'
			}, {
				title : '现付',
				elCls : 'center',
				dataIndex : 'nowPayDriverCost'
			}, {
				title : '到付',
				elCls : 'center',
				dataIndex : 'arrivePayDriverCost'
			}, {
				title : '回付',
				elCls : 'center',
				dataIndex : 'backPayDriverCost'
			}, {
				title : '保险金额',
				elCls : 'center',
				dataIndex : 'insuranceMoney'
			}, {
				title : '到达时间',
				elCls : 'center',
				width : 150,
				dataIndex : 'endTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '操作员',
				elCls : 'center',
				dataIndex : 'operatePersonName'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/signstorage/getarrivedepartlist.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#wayBillOrderList',
				columns : columns,
				store : store,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                	btnCls : 'button button-normal',
	                    text:'车辆签到',
	                    handler : signVehicle
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'整车入库',
	                    handler : wholeVehicleStorage
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'部分入库',
	                    handler : partStorage
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查看详情',
	                    handler : showDepartListDetail
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'打印',
	                    handler : printDepartList
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'整车签收',
	                    handler : signAllVehicle
	                } ]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#wayBillOrderListBar',
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
	
	// 加载单车单状态 下拉框
	function loadDepartListStatus(){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/depart/getdepartliststatus.shtml',
			success : function(result){
				var html = "<option value=''>全部</option>";
				if(result != null){
					result = eval("("+result+")");
					for(var i = 0; i < result.length; i++){
						html += "<option value='"+result[i].value+"'>"+result[i].name+"</option>";
					}
				}
				document.getElementById('departStatus').innerHTML = html;
			}
		});
	}
	// 整车签收
	function signAllVehicle(){
		var currentOutlets = $('#currentOutlets').val();
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一个发车单','warning');
			return;
		}
		var status = selection[0].status;
		if(status != 3){
			BUI.Message.Alert('已卸货的发车单可整车签收','warning');
			return;
		}
		if(selection[0].targetOutlets != currentOutlets){
			BUI.Message.Alert('途经网点不能整车签收','error');
			return;
		}
		var departNumber = selection[0].departNumber;
		BUI.Message.Confirm('确定要整车签收吗？',function(){
			$.ajax({
        		type : 'post',
        		url : '<%=request.getContextPath()%>/tms/signstorage/signallvehicle.shtml',
        		data : { 'departNumber' : departNumber },
        		success : function(result){
        			if(result.result){
        				BUI.Message.Alert(reuslt.msg,function(){
        					search();
        				},'success');
        			}else{
        				BUI.Message.Alert(result.msg,'error');
        			}
        		}
        	});
		},'question');
	}
	// 查询
	function search(){
		var params = {
			'status' : $('#departStatus').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 车辆签到
	function signVehicle(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		var length = selection.length;
		if(length < 1){
			BUI.Message.Alert('请选择','warning');
			return;
		}
		var departLists = [];
		for(var i = 0; i < selection.length; i++){
			if(selection[i].status != 1){
				BUI.Message.Alert('不允许此操作','error');
				return;
			}
			var departList = new Object();
			departList.departNumber = selection[i].departNumber;
			departList.wayBillNumbers = selection[i].wayBillNumbers;
			departList.targetOutlets = selection[i].targetOutlets;
			departList.wayOutlets = selection[i].wayOutlets;
			departLists.push(departList);
		}
		if(!validWayOutletsIsUnloadCargo(departLists)){
			BUI.Message.Alert('车辆未到站，不允许此操作','warning');
			return;
		}
		BUI.Message.Confirm('确定吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/signstorage/sign.shtml',
				data : { 'departLists' : JSON.stringify(departLists) },
				success : function(result){
					if(result > 0){
						BUI.Message.Alert('操作成功', function(){
							search();
						},'success');
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
	}
	
	// 验证途径网点是否卸货完毕
	function validWayOutletsIsUnloadCargo(departLists){
		var flag = false;
		$.ajax({
			type : 'post',
			async : false,
			url : '<%=request.getContextPath()%>/tms/signstorage/validwayoutletsisunloadcargo.shtml',
			data : { 'departLists' : JSON.stringify(departLists) },
			success : function(result){
				flag = result;
			}
		});
		return flag;
	}
	
	// 部分入库
	function partStorage(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		var length = selection.length;
		if(length < 1){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(length > 1){
			BUI.Message.Alert('请选正确的发车单','warning');
			return;
		}
		if(selection[0].status != 2){
			BUI.Message.Alert('不允许此操作','error');
			return;
		}
		$('#departListData').val(JSON.stringify(selection[0]));
		$('#departListDataForm').submit();
	}
	
	// 整车入库
	function wholeVehicleStorage(){
		var currentOutlets = $('#currentOutlets').val();
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}
		var departListArr = [];
		for(var i = 0; i < selection.length; i++){
			if(selection[i].targetOutlets != currentOutlets){
				BUI.Message.Alert('该网点不允许整车入库','error');
				return;
			}
			if(selection[i].status == 2){
				var departList = new Object();
				departList.departNumber = selection[i].departNumber;
				departList.wayBillNumbers = selection[i].wayBillNumbers;
				departList.targetOutlets = selection[i].targetOutlets;
				departListArr.push(departList);
			}else{
				BUI.Message.Alert('不允许此操作','error');
				return;
			}
		}
		BUI.Message.Confirm('确定要整车入库吗？',function(){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/signstorage/wholevehiclestorage.shtml',
				data : { 'departLists' : JSON.stringify(departListArr) },
				success : function(result){
					if(result > 0){
						BUI.Message.Alert('操作成功', function(){
							search();
						},'success');
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
	}
	
	// 查看详情
	function showDepartListDetail(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一个发车单','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tms/depart/todepartlistdetail.shtml?departNumber='+selection[0].departNumber;
	}
	
	// 打印发车清单
	function printDepartList(){
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一个发车单','warning');
			return;
		}
		window.open('<%=request.getContextPath()%>/tms/depart/todepartlistpreview.shtml?departNumber='+selection[0].departNumber);
	}
	</script>
</body>
</html>