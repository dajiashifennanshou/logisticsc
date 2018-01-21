<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网点收支账</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-stdmod-body{
	overflow-x : hidden;
	overflow-y : auto;
}
.form-inline input{width: 120px;}
.form-inline select{width: 146px;}
.form-horizontal input{width: 120px;}
.form-horizontal select{width: 146px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>网点收支账</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>网点：</label>
				        	<c:if test="${empty outletsInfo}">
				        		<select onchange="document.getElementById('outletsId').value=this.value;">
					        		<option value="">请选择</option>
					        		<c:forEach items="${outletsInfos}" var="outletsInfo">
					        			<option value="${outletsInfo.id}">${outletsInfo.name}</option>
					        		</c:forEach>
					        	</select>
				        	</c:if>
				        	<c:if test="${not empty outletsInfo}">
				        		<label>${outletsInfo.name}</label>
				        	</c:if>
				        	<input type="hidden" id="outletsId" value="${outletsInfo.id}">
				        	<!-- <label>结算完毕</label>
				        	<select class="input-normal" id="isCompleted">
					        	<option value="">全部</option>
					        	<option value="0">未完结</option>
					        	<option value="1">已完结</option>
					        </select> -->
				        	<label>开始时间</label>
				        	<input type="text" class="calendar" id="startTime"> 
				        	<label>结束时间</label>
				        	<input type="text" class="calendar" id="endTime">
				            <button type="button" class="button" onclick="search()">查询</button>
				            <button type="button" class="button">打印</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>应收对账</h3>
						<!-- <h3>应收对账 <small>发车单数：<span id="departListNum"></span>个，应收合计：<span id="shouldTotal"></span>元，实收合计：<span id="actualTotal"></span>元</small></h3> -->
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
			</div>
			<div class="panel-body">
				<div class="panel">
					<div class="panel-header">
						<h3>应付对账 <small>账单数：<span id="billNum"></span>个， 应付合计：<span id="receivablePayTotal"></span>元</small></h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="receivablePayList"></div>
								<div id="receivablePayListBar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
		var receivableGrid, receivableStore;
		$(function(){
			loadBuiCalendar();
			loadReceivableAccountList();
			loadReceivablePayList();
		});
		
		// 加载BUI日历插件
		function loadBuiCalendar(){
			BUI.use('bui/calendar',function(Calendar){
		    	var datepicker = new Calendar.DatePicker({
		        	trigger:'.calendar',
		        	autoRender : true
		        });
			});
		}
		
		// 加载应收款列表
		function loadReceivableAccountList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
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
				}, {
					title : '发车日期',
					elCls : 'center',
					dataIndex : 'operateTime',
					width : 150,
					renderer:BUI.Grid.Format.datetimeRenderer
				} ];
				
				receivableStore = new Store({
					url : '<%=request.getContextPath()%>/tms/depart/getreceivabledepartlist.shtml',
					autoLoad : true,
					//remoteSort : true,
					pageSize : 10,
					listeners : {
						beforeprocessload : function(e){
							/* $('#departListNum').text(e.data.results);
							$('#shouldTotal').text(e.data.data.shouldTotal);
							$('#actualTotal').text(e.data.data.actualTotal); */
						}
					}
				});
				receivableGrid = new Grid.Grid({
					render : '#receivableAccountList',
					columns : columns,
					store : receivableStore,
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
					store : receivableStore
				});
				bar.render();
				receivableGrid.render();
			});
		}
		
		// 加载应付款列表
		function loadReceivablePayList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '费用项目',
					elCls : 'center',
					dataIndex : 'costSubject'
				}, {
					title : '发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'departNumber'
				}, {
					title : '外包发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'outDepartNumber'
				}, {
					title : '中转发车单号',
					elCls : 'center',
					width : 130,
					dataIndex : 'transferDepartNumber'
				}, {
					title : '出发网点',
					elCls : 'center',
					dataIndex : 'startOutletsName'
				}, {
					title : '到达网点',
					elCls : 'center',
					dataIndex : 'targetOutletsName'
				}, {
					title : '结算状态',
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
					title : '应付合计',
					elCls : 'center',
					dataIndex : 'total'
				}, {
					title : '现付',
					elCls : 'center',
					dataIndex : 'currentPay'
				}, {
					title : '到付',
					elCls : 'center',
					dataIndex : 'arrivePay'
				}, {
					title : '回付',
					elCls : 'center',
					dataIndex : 'backPay'
				}, {
					title : '收款人',
					elCls : 'center',
					dataIndex : 'receivePerson'
				}, {
					title : '收款人电话',
					elCls : 'center',
					dataIndex : 'receivePersonPhone'
				}, {
					title : '发车时间',
					elCls : 'center',
					dataIndex : 'createTime',
					width : 150,
					renderer:BUI.Grid.Format.datetimeRenderer
				} ];
				
				receivablePayStore = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getreceivablepaybill.shtml',
					autoLoad : true,
					proxy : { method : 'post' },
					//remoteSort : true,
					pageSize : 10,
					listeners : {
						beforeprocessload : function(e){
							$('#billNum').text(e.data.results);
							$('#receivablePayTotal').text(e.data.data);
						}
					}
				});
				receivablePayGrid = new Grid.Grid({
					render : '#receivablePayList',
					columns : columns,
					store : receivablePayStore,
					//forceFit : true,
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#receivablePayListBar',
					elCls : 'pagination pull-right',
					store : receivablePayStore
				});
				bar.render();
				receivablePayGrid.render();
			});
		}
		
		// 查询
		function search(){
			var receivableparams = {
				'startOutlets' : $('#outletsId').val(),
				/* 'isCompleted' : $('#isCompleted').val(), */
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val()
			}
			var receivablePayparams = {
				'outletsId' : $('#outletsId').val(),
				/* 'isCompleted' : $('#isCompleted').val(), */
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val()
			}
			receivableStore.load(receivableparams);
			receivablePayStore.load(receivablePayparams);
		}
		
	</script>
</body>
</html>