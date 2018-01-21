<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>送货上门</title>
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
				<h3>送货上门</h3>
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
		<div class="hide" id="dialog">
			<form id="form" class="form-horizontal">
				<div class="row">
					<div class="control-group">
						<label class="control-label">配送方式：</label>
						<div class="controls">
							<select class="input-normal">
								<option>自提</option>
								<option>中转</option>
								<option>上门取货</option>
							</select>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="hide" id="updateReceiveTypeDialog">
			<form class="form-horizontal">
				<div class="row">
					<div class="control-group">
						<label class="control-label">配送方式：</label>
						<div class="controls">
							<select id="receiveTypeUpdate" class="input-normal">
								<option value="0">自提</option>
								<!-- <option value="1">送货上门</option> -->
								<!-- <option value="2">中转</option> -->
							</select>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="hide" id="exStorageDialog">
			<form id="exStorageForm" class="form-horizontal">
				<input type="hidden" id="wayBillOrderId" name="wayBillOrderId">
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>司机：</label>
						<div class="controls">
							<select id="driverName" class="input-normal" style="width: 166px;" onchange="choiceDriver(this.value)" data-rules="{required:true}">
								<option value="">选择司机</option>
								<c:forEach items="${driverInfos}" var="driverInfo">
									<option value="${driverInfo.id}">${driverInfo.driverName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">司机电话：</label>
						<div class="controls">
							<span id="driverPhone" class="control-label" style="text-align: left;"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><s>*</s>车辆：</label>
						<div class="controls">
							<select id="vehicleNumber" class="input-normal" style="width: 166px;" data-rules="{required:true}">
								<option value="">选择车辆</option>
								<c:forEach items="${vehicleInfos}" var="vehicleInfo">
									<option value="${vehicleInfo.plateNumber}">${vehicleInfo.plateNumber}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var grid, store, dialog, exStorageDialog, exStorageForm;
	$(function(){
		loadBuiCalendar();
		loadWayBillList();
		loadUpdateReceiveTypeDialog();
		loadDialog();
		loadForm();
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
				title : '出库状态',
				elCls : 'center',
				dataIndex : 'isOutStore',
				renderer : function(value, obj, index){
					if(value == 0){
						return '未出库';
					}else if(value == 1){
						return '已出库';
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
				dataIndex : 'consignee'
			}, {
				title : '收货电话',
				elCls : 'center',
				dataIndex : 'consigneeMobile'
			}, {
				title : '收货地址',
				elCls : 'center',
				dataIndex : 'consigneeAddress'
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
				url : '<%=request.getContextPath()%>/tms/sendcargo/getwaybillofhomedelivery.shtml',
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
	                    text:'出库',
	                    handler : function(){
	                    	showExStorageDialog();
	                    }
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'更改配送方式',
	                    handler : showChangeReceiveTypeDialog
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'异常登记',
	                    handler : toAbnormal
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查看运单',
	                    handler : findWayBillDetail
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
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		if(selection[0].isOutStore == 1){
			BUI.Message.Alert('已出库的运单不能更改配送方式','warning');
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
		for(var i = 0; i < selection.length; i++){
			
		}
		dialog.show();
	}
	
	// 加载 更改配送方式 弹出框
	function loadUpdateReceiveTypeDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			dialog = new Overlay.Dialog({
		    	title:'更改配送方式',
		        width:360,
		        height:140,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'updateReceiveTypeDialog',
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
	
	function loadForm(){
		BUI.use('bui/form',function(Form){
			exStorageForm = new Form.Form({
				srcNode : '#exStorageForm'
			}).render();
		})
	}
	
	// 显示 出库弹出框
	function showExStorageDialog(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		if(selection[0].isOutStore == 1){
			BUI.Message.Alert('已出库的运单不能再次出库','warning');
			return;
		}
		if(selection[0].exceptionStatus != 0){
			BUI.Message.Alert('异常状态的运单不能出库','warning');
			return;
		}
		$('#wayBillOrderId').val(selection[0].id);
		exStorageDialog.show();
	}
	
	// 加载 出库 弹出框
	function loadDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			exStorageDialog = new Overlay.Dialog({
		    	title:'出库',
		        width:460,
		        height:240,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'exStorageDialog',
		        success:function () {
		        	if(!exStorageForm.isValid()){
		        		return;
		        	}
		        	$.ajax({
		    			type : 'post',
		    			url : '<%=request.getContextPath()%>/tms/sendcargo/savewaybilloutstorerecord.shtml',
		    			data : {
			        		'wayBillOrderId' : $('#wayBillOrderId').val(),
			        		'driverName' : $("#driverName").find("option:selected").text(),
			        		'driverPhone' : $("#driverPhone").text(),
			        		'vehicleNumber' : $("#vehicleNumber").val()
			        	},
		    			success : function(result){
		    				if(result == 1){
		    					BUI.Message.Alert('操作成功', function(){
		    						search();
		    						exStorageDialog.close();
		    					}, 'success');
		    				}else{
		    					BUI.Message.Alert('操作失败', 'error');
		    				}
		    			}
		    		});
		        }
		    });
			exStorageDialog.set('effect',{effect : 'fade', duration : 400});
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
		if(selection[0].exceptionStatus != 0){
			BUI.Message.Alert("该运单已登记异常",'warning');
			return;
		}
		if(selection[0].signStatus == 1){
			BUI.Message.Alert("已签收的运单不能异常登记",'warning');
			return;
		}
		window.location.href="<%=request.getContextPath() %>/tms/abnormal/addabnormal.shtml?wayBillNumber="+selection[0].wayBillNumber;
	}
	
	// 选择司机
	function choiceDriver(driverId){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/driver/getdriverinfo.shtml',
			data : { 'id' : driverId },
			success : function(result){
				$('#driverPhone').text(result.phoneNumber);
			}
		});
	}
	</script>
</body>
</html>