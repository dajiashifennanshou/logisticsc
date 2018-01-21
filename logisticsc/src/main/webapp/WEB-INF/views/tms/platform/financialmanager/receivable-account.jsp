<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应收应付账</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>应收应付账</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>网点：</label><span>${outletsName}</span>
				        	<label class="control-label">途径网点</label>
					        <select class="input-normal" id="wayOutlets">
					        	<option value="">全部</option>
					        	<c:forEach items="${outletsInfos}" var="outletsInfo">
					        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
					        	</c:forEach>
					        </select>
					        <label class="control-label">目的网点</label>
					        <select class="input-normal" id="targetOutlets">
					        	<option value="">全部</option>
					        	<c:forEach items="${outletsInfos}" var="outletsInfo">
					        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
					        	</c:forEach>
					        </select>
					        <label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号">
				            <button type="button" class="button" onclick="search()">查询</button>
				            <button type="button" class="button" onclick="exportDepartList()">导出</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>发车单列表</h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="receivableAccountList"></div>
								<div id="receivableAccountListBar"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>网点：</label><span>${outletsName}</span>
					        <label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="outDepartListStartTime">
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="outDepartListEndTime">
				            <input type="text" class="input-normal" id="outDepartListCondition" placeholder="外包发车单号">
				            <button type="button" class="button" onclick="searchDepartList()">查询</button>
				        	<button type="button" class="button" onclick="exportOutDepartList()">导出</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>外包发车单列表</h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="outDepartList"></div>
								<div id="outDepartListBar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide" id="receiveMoneyRecordDialog">
			<div id="receiveMoneyRecordList"></div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var grid, store, receiveMoneyRecordStore, receiveMoneyRecordGrid;
		var outGrid, outStore;
		
		var receiveMoneyReocrdDialog;
		$(function(){
			loadCalendar();
			loadReceivableAccountList();
			loadOutDepartList();
			loadReceiveMoneyRecordDialog();
		});
		
		// 加载应收款列表
		function loadReceivableAccountList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '发车日期',
					elCls : 'center',
					dataIndex : 'operateTime',
					width : 150,
					renderer:BUI.Grid.Format.datetimeRenderer
				}, {
					title : '发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'departNumber',
					renderer : function(value){
						var path = '<%=request.getContextPath()%>';
						return '<a href="'+path+'/tms/depart/todepartlistdetail.shtml?departNumber='+value+'&isReceiveMoney=1">'+value+'</a>';
					}
				}, {
					title : '出发网点',
					elCls : 'center',
					dataIndex : 'startOutletsName'
				}, {
					title : '途径网点',
					elCls : 'center',
					dataIndex : 'wayOutletsName'
				}, {
					title : '到达网点',
					elCls : 'center',
					dataIndex : 'targetOutletsName'
				}, {
					title : '应收运费合计',
					elCls : 'center',
					dataIndex : 'receivableTotal'
				}, {
					title : '是否完结',
					elCls : 'center',
					dataIndex : 'isCompleted',
					renderer : function(value){
						if(value == 0){
							return '未完结';
						}else if(value == 1){
							return '已完结';
						}
					}
				}, {
					title : '代收货款合计',
					elCls : 'center',
					dataIndex : 'agencyFundTotal'
				}, {
					title : '是否完结',
					elCls : 'center',
					dataIndex : 'agencyFundIsCompleted',
					renderer : function(value){
						if(value == 0){
							return '未完结';
						}else if(value == 1){
							return '已完结';
						}
					}
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
					title : '司机运费',
					elCls : 'center',
					dataIndex : 'shouldPayDriverCost'
				} ];
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/depart/getreceivabledepartlist.shtml',
					autoLoad : true,
					//remoteSort : true,
					pageSize : 10,
					listeners : {
						beforeprocessload : function(e){
							/* $('#billNum').text(e.data.results);
							$('#shouldTotal').text(e.data.data.shouldTotal);
							$('#actualTotal').text(e.data.data.actualTotal); */
						}
					}
				});
				grid = new Grid.Grid({
					render : '#receivableAccountList',
					columns : columns,
					store : store,
					//forceFit : true,
					/* plugins : [Grid.Plugins.CheckSelection],
					tbar:{
		                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
		                items:[{
		                	btnCls : 'button button-normal',
		                    text:'查看收款记录',
		                    handler : findReceiveMoneyRecord
		                } ]
		            } */
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#receivableAccountListBar',
					elCls : 'pagination pull-right',
					store : store
				});
				bar.render();
				grid.render();
			});
		}
		
		// 加载外包发车单列表
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
					dataIndex : 'outDepartNumber',
					renderer : function(value){
						var path = '<%=request.getContextPath()%>';
						return '<a href="'+path+'/tms/outdepart/tooutdepartlistdetail.shtml?outDepartNumber='+value+'">'+value+'</a>';
					}
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
				}];
				
				outStore = new Store({
					url : '<%=request.getContextPath()%>/tms/outdepart/search.shtml',
					autoLoad : true,
					//remoteSort : true,
					pageSize : 10
				});
				outGrid = new Grid.Grid({
					render : '#outDepartList',
					columns : columns,
					store : outStore,
					//forceFit : true,
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#outDepartListBar',
					elCls : 'pagination pull-right',
					store : outStore
				});
				bar.render();
				outGrid.render();
			});
		}
		
		// 查询
		function search(){
			var params = {
				'wayOutlets' : $('#wayOutlets').val(),
				'targetOutlets' : $('#targetOutlets').val(),
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val(),
				'condition' : $('#condition').val()
			}
			store.load(params);
		}
		
		function searchDepartList(){
			var params = {
				'startTime' : $('#outDepartListStartTime').val(),
				'endTime' : $('#outDepartListEndTime').val(),
				'condition' : $('#outDepartListCondition').val()
			}
			outStore.load(params);
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
		
		// 查看收款记录
		function findReceiveMoneyRecord(){
			showChangeReceiveTypeDialog();
		}
		
		// 显示 更改配送方式 弹出框
		function showChangeReceiveTypeDialog(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条记录','warning');
				return;
			}
			$('#receiveMoneyRecordList').html('');
			loadReceiveMoneyRecordList(selection[0].departNumber);
			receiveMoneyReocrdDialog.show();
		}
		
		// 加载 更改配送方式 弹出框
		function loadReceiveMoneyRecordDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				receiveMoneyReocrdDialog = new Overlay.Dialog({
			    	title:'收款记录',
			        width:800,
			        height:400,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'receiveMoneyRecordDialog',
			        success:function () {
			        	
			            this.close();
			        }
			    });
				receiveMoneyReocrdDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 加载收款记录列表
		function loadReceiveMoneyRecordList(departNumber){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '日期',
					dataIndex : 'operateTime'
				}, {
					title : '运单号',
					dataIndex : 'wayBillNumber'
				}, {
					title : '科目',
					dataIndex : 'costType'
				}, {
					title : '金额',
					dataIndex : 'actualReceiveMoney'
				}, {
					title : '付款人',
					dataIndex : 'payPerson'
				}, {
					title : '付款单位',
					dataIndex : 'payCompany'
				} ];
				
				receiveMoneyRecordStore = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getreceivemoneyrecordbydepartnumber.shtml',
					autoLoad : true,
					params : { 'departNumber' : departNumber }
					//remoteSort : true,
					//pageSize : 10
				});
				receiveMoneyRecordGrid = new Grid.Grid({
					render : '#receiveMoneyRecordList',
					columns : columns,
					store : receiveMoneyRecordStore,
					forceFit : true
				});

				receiveMoneyRecordGrid.render();
			});
		}
		// 导出发车单
		function exportDepartList(){
			var wayOutlets = $('#wayOutlets').val();
			var targetOutlets = $('#targetOutlets').val();
			var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			var condition = $('#condition').val();
			window.location.href = '<%=request.getContextPath() %>/tms/depart/exportdepartlistlist.shtml?wayOutlets='+wayOutlets+'&targetOutlets='+targetOutlets+'&startTime='+startTime+'&endTime='+endTime+'&condition='+condition;
		}
		
		// 导出外包发车单
		function exportOutDepartList(){
			var startTime = $('#outDepartListStartTime').val();
			var endTime = $('#outDepartListEndTime').val();
			var condition = $('#outDepartListCondition').val();
			window.location.href = '<%=request.getContextPath() %>/tms/outdepart/exportoutdepartlist.shtml?startTime='+startTime+'&endTime='+endTime+'&condition='+condition;
		}
	</script>
</body>
</html>