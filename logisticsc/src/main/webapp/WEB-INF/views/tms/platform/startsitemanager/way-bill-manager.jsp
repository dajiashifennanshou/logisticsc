<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运单管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-row-register{
	color: red;
}
.bui-grid-row-handler{
	color: green;
}
</style>

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>运单管理</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label class="control-label">目的网点</label>
					        <select class="input-normal" id="targetOutlets">
					        	<option value="">全部</option>
					        	<c:forEach items="${outletsInfos}" var="outletsInfo">
					        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
					        	</c:forEach>
					        </select>
					        <label class="control-label">运单状态</label>
					        <select class="input-normal" id="wayBillStatus"></select>
				        	
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="运单号/订单号">
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
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var store, grid;
	$(function(){
		loadCalendar();
		loadWayBillStatus();
		loadWayBillOrderList();
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
	function loadWayBillOrderList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '开单时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'wayBillOrderTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '订单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'orderNumber'
			}, {
				title : '运单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'wayBillNumber'
			}, {
				title : '运单状态',
				elCls : 'center',
				dataIndex : 'statusName'
			}, {
				title : '异常状态',
				elCls : 'center',
				dataIndex : 'exceptionStatusName'
			}, {
				title : '出发网点',
				elCls : 'center',
				dataIndex : 'startOutletsName'
			}, {
				title : '到达网点',
				elCls : 'center',
				dataIndex : 'targetOutletsName'
			}, {
				title : '目的地',
				elCls : 'center',
				width : 200,
				dataIndex : 'targetAddress'
			}, {
				title : '发货人',
				elCls : 'center',
				dataIndex : 'consignor'
			}, {
				title : '发货电话',
				elCls : 'center',
				dataIndex : 'consignorMobile'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consignee'
			}, {
				title : '收货电话',
				elCls : 'center',
				dataIndex : 'consigneeMobile'
			}, {
				title : '发货方式',
				elCls : 'center',
				dataIndex : 'sendType',
				renderer : function(value){
					if(value == 0){
						return '自送网点';
					}else if(value == 1){
						return '上门取货';
					}
				}
			}, {
				title : '送货方式',
				elCls : 'center',
				dataIndex : 'receiveType',
				renderer : function(value){
					if(value == 0){
						return '客户自提';
					}else if(value == 1){
						return '送货上门';
					}
				}
			}, {
				title : '货物名称',
				elCls : 'center',
				dataIndex : 'cargoName'
			}, {
				title : '件数',
				elCls : 'center',
				dataIndex : 'cargoNumber'
			}, {
				title : '套数',
				elCls : 'center',
				dataIndex : 'cargoSetNumber'
			}, {
				title : '代收货款',
				elCls : 'center',
				dataIndex : 'agencyFund'
			}, {
				title : '垫付货款',
				elCls : 'center',
				dataIndex : 'advanceCost'
			}, {
				title : '总运费',
				elCls : 'center',
				dataIndex : 'total'
			}, {
				title : '付款方式',
				elCls : 'center',
				dataIndex : 'payMethodName'
			}, {
				title : '回单份数',
				elCls : 'center',
				dataIndex : 'receiptSlipNum'
			}, {
				title : '操作员',
				elCls : 'center',
				dataIndex : 'operatePersonName'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/waybill/searchwaybillorder.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#wayBillOrderList',
				columns : columns,
				store : store,
				itemStatusFields : { //设置数据跟状态的对应关系
		        	register : 'exceptionRegister',
		            handler : 'exceptionHandler' //如果readed : true,则附加 bui-grid-row-read 样式
		        },
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                	btnCls : 'button button-normal',
	                    text:'作废运单',
	                    handler : abolishWayBill
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查看运单',
	                    handler : findWayBillOrder
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'编辑运单',
	                    handler : editWayBill
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'导出',
	                    handler : exportWayBill
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'打印运单',
	                    handler : printWayBill
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'异常登记',
	                    handler : toAbnormal
	                }]
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
	
	// 加载订单状态 下拉框
	function loadWayBillStatus(){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/waybill/getwaybillstatus.shtml',
			success : function(result){
				var html = "<option value=''>全部</option>";
				if(result != null){
					result = eval("("+result+")");
					for(var i = 0; i < result.length; i++){
						html += "<option value='"+result[i].value+"'>"+result[i].name+"</option>";
					}
				}
				document.getElementById('wayBillStatus').innerHTML = html;
			}
		});
	}
	
	// 查询
	function search(){
		var params = {
			'targetOutlets' : $('#targetOutlets').val(),
			'status' : $('#wayBillStatus').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 作废运单
	function abolishWayBill(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length < 1){
			BUI.Message.Alert('请选择','warning');
			return;
		}
		for(var i = 0; i < selection.length; i++){
			if(selection[i].status != 0){
				BUI.Message.Alert(selection[0].statusName + '的运单不能被作废','warning');
				return;
			}
			if(selection[i].orderNumber != null && selection[i].orderNumber != ''){
				BUI.Message.Alert('货运交易系统订单不能被作废','warning');
				return;
			}
		}
		if(selection[0].exceptionStatus != 0){
			BUI.Message.Alert('异常运单不能作废','warning');
			return;
		}
		BUI.Message.Confirm('确定吗？',function(){
			var wayBillNumbers = '';
			for(var i = 0; i < selection.length; i++){
				if(i == selection.length - 1){
					wayBillNumbers += selection[i].wayBillNumber;
				}else{
					wayBillNumbers += selection[i].wayBillNumber + ',';
				}
			}
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/waybill/abolishwaybill.shtml',
				data : { 'wayBillNumbers' : wayBillNumbers },
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('操作成功',function(){
							store.load();
						},'success');
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		},'question');
	}
	
	// 查看运单
	function findWayBillOrder(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一条数据','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tms/waybill/towaybilldetailpage.shtml?wayBillNumber='+selection[0].wayBillNumber;
	}
	
	// 编辑运单
	function editWayBill(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一条数据','warning');
			return;
		}
		if(selection[0].exceptionStatus != 0){
			BUI.Message.Alert('异常运单不能编辑','warning');
			return;
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/waybill/validwaybillisedit.shtml',
			data : { 'wayBillNumber' : selection[0].wayBillNumber },
			success : function(result){
				if(result == 1){
					window.location.href = '<%=request.getContextPath()%>/tms/waybill/toeditwaybillpage.shtml?id='+selection[0].id;
				}else{
					BUI.Message.Alert('该运单不能编辑','warning');
				}
			}
		});
	}
	
	// 跳转到异常登记页面
	function toAbnormal(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert("请选择一条记录",'warning');
			return;
		}
		var exceptionStatus = selection[0].exceptionStatus;
		if(exceptionStatus != 0){
			BUI.Message.Alert("该运单已登记异常",'warning');
			return;
		}
		if(selection[0].status != 0){
			BUI.Message.Alert(selection[0].statusName + "的运单不能登记异常",'warning');
			return;
		}
		window.location.href="<%=request.getContextPath() %>/tms/abnormal/addabnormal.shtml?wayBillNumber="+selection[0].wayBillNumber;
	}
	
	// 导出运单
	function exportWayBill(){
		var status = $('#wayBillStatus').val();
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		var condition = $('#condition').val();
		window.location.href = '<%=request.getContextPath() %>/tms/waybill/exportwaybill.shtml?status='+status+'&startTime='+startTime+'&endTime='+endTime+'&condition='+condition;
	}
	
	// 打印运单
	function printWayBill(){
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一个运单','warning');
			return;
		}
		window.open('<%=request.getContextPath()%>/tms/waybill/towaybillpreview.shtml?wayBillNumber='+selection[0].wayBillNumber);
	}
	</script>
</body>
</html>