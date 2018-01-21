<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中转运单</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-stdmod-body{
	overflow-x : hidden;
	overflow-y : auto;
}
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
				<h3>中转</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
					        <label class="control-label">异常状态</label>
					        <select class="input-normal" id="exceptionStatus">
					        	<option value="">全部</option>
					        	<option value="0">无</option>
					        	<option value="1">异常登记</option>
					        	<option value="2">异常处理</option>
					        </select>
				        	<label class="control-label">签收状态</label>
					        <select class="input-normal" id="signStatus">
					        	<option value="">全部</option>
					        	<option value="0">未签收</option>
					        	<option value="1">已签收</option>
					        </select>
					        <label class="control-label">开始时间：</label>
					        <input type="text" id="startTime" class="calendar">
					        <label class="control-label">结束时间：</label>
					        <input type="text" id="endTime" class="calendar">
				            <input type="text" class="input-normal" id="condition" placeholder="运单号">
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
	<div class="hide" id="dialog">
		<form class="form-horizontal">
			<div class="row">
				<div class="control-group">
					<label class="control-label">配送方式：</label>
					<div class="controls">
						<select id="receiveTypeUpdate" class="input-normal">
							<option value="0">自提</option>
							<option value="1">送货上门</option>
						</select>
					</div>
				</div>
			</div>
		</form>
	</div>
	<form id="toTransferWayBillListForm" action="<%=request.getContextPath()%>/tms/transfer/totransferwaybilllist.shtml" method="post">
		<input type="hidden" name="wayBillData" id="wayBillData">
	</form>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var grid, store, dialog;
	$(function(){
		loadWayBillList();
		loadDialog();
		loadBuiCalendar();
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
	
	// 加载BUI日历插件
	function loadBuiCalendar(){
		BUI.use('bui/calendar',function(Calendar){
	    	var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	        });
		});
	}
	
	// 加载运单列表
	function loadWayBillList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
				Toolbar) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '到达时间',
				elCls : 'center',
				width : 150,
				dataIndex : 'endTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '出库时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'outTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '签收时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'signTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '运单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'wayBillNumber'
			}, {
				title : '异常状态',
				elCls : 'center',
				dataIndex : 'exceptionStatusName'
			}, {
				title : '配送方式',
				elCls : 'center',
				dataIndex : 'receiveType',
				renderer : function(value, obj, index){
					if(value == 0){
						return '自提';
					}else if(value == 1){
						return '送货上门';
					}else if(value == 2){
						return '中转';
					}
				}
			}, {
				title : '签收状态',
				elCls : 'center',
				dataIndex : 'signStatus',
				renderer : function(value, obj, index){
					if(value == 0){
						return '未签收';
					}else if(value == 1){
						return '已签收';
					}
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
				title : '目的地',
				elCls : 'center',
				width : 200,
				dataIndex : 'targetAddress'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consignor'
			}, {
				title : '收货电话',
				elCls : 'center',
				dataIndex : 'consignorMobile'
			}, {
				title : '收货地址',
				elCls : 'center',
				dataIndex : 'consignorAddress'
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
				url : '<%=request.getContextPath()%>/tms/sendcargo/getwaybilloftransfer.shtml',
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
	                items:[ {
	                    btnCls : 'button button-normal',
	                    text:'更改配送方式',
	                    handler : showChangeReceiveTypeDialog
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'中转出库',
	                    handler : transferOutSource
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查看运单',
	                    handler : findWayBillDetail
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'异常登记',
	                    handler : toAbnormal
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
	
	// 查询
	function search(){
		var params = {
			'exceptionStatus' : $('#exceptionStatus').val(),
			'signStatus' : $('#signStatus').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 显示 更改配送方式 弹出框
	function showChangeReceiveTypeDialog(){
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		if(selection[0].signStatus == 1){
			BUI.Message.Alert('已签收的运单不能更改配送方式','warning');
			return;
		}
		if(selection[0].exceptionStatus != 0){
			BUI.Message.Alert('异常状态的运单不能更改配送方式','warning');
			return;
		}
		dialog.show();
	}
	
	// 加载 更改配送方式 弹出框
	function loadDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			dialog = new Overlay.Dialog({
		    	title:'更改配送方式',
		        width:360,
		        height:150,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'dialog',
		        success:function () {
		        	updateReceiveType();
		            this.close();
		        }
		    });
            dialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	// 更改配送方式
	function updateReceiveType(){
		var receiveType = $('#receiveTypeUpdate').val();
		var selection  = grid.getSelection();
		var wayBillNumberArr = [];
		for(var i = 0; i < selection.length; i++){
			wayBillNumberArr.push(selection[i].wayBillNumber);
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/sendcargo/updatereceivetype.shtml',
			data : { 'receiveType' : receiveType, 'wayBillNumbers' : JSON.stringify(wayBillNumberArr) },
			success : function(result){
				if(result == 1){
					BUI.Message.Alert('操作成功', function(){
						search();
					}, 'success');
				}else{
					BUI.Message.Alert('操作失败', 'error');
				}
			}
		});
	}
	
	// 查看运单
	function findWayBillDetail(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一个运单','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tms/waybill/towaybilldetailpage.shtml?wayBillNumber='+selection[0].wayBillNumber;
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
		if(selection[0].signStatus == 1){
			BUI.Message.Alert("已签收的运单不能登记异常",'warning');
			return;
		}
		if(selection[0].exceptionStatus != 0){
			BUI.Message.Alert("该运单已登记异常",'warning');
			return;
		}
		window.location.href="<%=request.getContextPath() %>/tms/abnormal/addabnormal.shtml?wayBillNumber="+selection[0].wayBillNumber;
	}
	
	// 中转出库
	function transferOutSource(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选中转运单','warning');
			return;
		}
		for(var i = 0; i < selection.length; i++){
			if(selection[i].outTime != null){
				BUI.Message.Alert('该运单已中转','warning');
				return;
			}
			if(selection[i].signStatus == 1){
				BUI.Message.Alert('已签收的运单不能中转','warning');
				return;
			}
			if(selection[i].exceptionStatus != 0){
				BUI.Message.Alert('异常状态的运单不能中转','warning');
				return;
			}
		}
		$('#wayBillData').val(JSON.stringify(selection));
		$('#toTransferWayBillListForm').submit();
	}
	
	</script>
</body>
</html>