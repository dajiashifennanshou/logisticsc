<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签收列表</title>
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
				<h3>签收列表</h3>
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
					         <label class="control-label">外包状态</label>
					        <select class="input-normal" id="out_store_status">
					        	<option value="">全部</option>
					        	<option value="1">是</option>
					        	<option value="0">否</option>
					        </select>
					         <label class="control-label">中转状态</label>
					        <select class="input-normal" id="transfer_status">
					        	<option value="">全部</option>
					        	<option value="1">是</option>
					        	<option value="0">否</option>
					        </select>
				        	<label class="control-label">配送方式</label>
					        <select class="input-normal" id="receiveType">
					        	<option value="">全部</option>
					        	<option value="0">自提</option>
					        	<option value="1">送货上门</option>
					        </select>
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
		<div class="hide" id="signDialog">
			<form class="form-horizontal" id="signRecordForm">
				<input type="hidden" name="token" value="${token}">
				<input type="hidden" id="signWayBillNumber" name="wayBillNumber">
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>签收时间：</label>
						<div class="controls">
							<input type="text" name="signTimeStr" class="calendar calendar-time" data-rules="{required:true, date:true}" style="width: 140px;">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>签收人：</label>
						<div class="controls">
							<input type="text" name="signPerson" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">证件号码：</label>
						<div class="controls">
							<input type="text" name="idCard" class="input-normal" data-rules="{idCard:''}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>联系电话：</label>
						<div class="controls">
							<input type="text" name="phone" class="input-normal" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">联系地址：</label>
						<div class="controls">
							<input type="text" name="address" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">代收货款：</label>
						<div class="controls bui-form-field-plain">
				            <span class="control-text" id="agencyFund">0</span>
				        </div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款方式：</label>
						<div class="controls">
							<span class="control-text" id="payMethod"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">应收运费：</label>
						<div class="controls">
							<span class="control-text" id="freight">0</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">垫付货款：</label>
						<div class="controls">
							<span class="control-text" id="advanceCost">0</span>
						</div>
					</div>
				</div>
				<!-- <div class="row">
					<div class="control-group">
						<label class="control-label">下线实收：</label>
						<div class="controls">
							<input type="text" name="receiveMoney" class="input-normal" value="0" data-rules="{required:true,number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">结算完毕：</label>
						<div class="controls">
							<input type="checkbox" name="isCompleted" value="1">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">扣预付款：</label>
						<div class="controls">
							<input type="checkbox" name="isAdvanceMoney" value="1">
						</div>
					</div>
				</div> -->
			</form>
		</div>
		<div class="hide" id="signRecordDialog">
			<form class="form-horizontal">
				<div class="row">
					<div class="control-group">
						<label class="control-label">签收时间：</label>
						<div class="controls">
							<span class="control-text" id="signTimeStr"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">签收人：</label>
						<div class="controls">
							<span class="control-text" id="signPerson"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">证件号码：</label>
						<div class="controls">
							<span class="control-text" id="idCard"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">联系电话：</label>
						<div class="controls">
							<span class="control-text" id="phone"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">联系地址：</label>
						<div class="controls">
							<span class="control-text" id="address"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">代收货款：</label>
						<div class="controls bui-form-field-plain">
				            <span class="control-text" id="agencyFund_info">0</span>
				        </div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款方式：</label>
						<div class="controls">
							<span class="control-text" id="payMethod_info"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">应收运费：</label>
						<div class="controls">
							<span class="control-text" id="freight_info">0</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">垫付货款：</label>
						<div class="controls">
							<span class="control-text" id="advanceCost_info">0</span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var grid, store, signDialog, signRecordForm, signRecordDialog;
	$(function(){
		loadBuiCalendar();
		loadWayBillList();
		loadSignDialog();
		loadSignRecordDialog();
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
	
	function loadForm(){
		var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
		BUI.use('bui/form',function(Form){
			signRecordForm = new Form.Form({
				srcNode : '#signRecordForm',
				//errorTpl : errorTpl
			}).render();
			
			Form.Rules.add({
				name : 'idCard',
				msg : '证件号码不正确',
				validator : function(value, baseValue, formatMsg){
					var idCardPartten = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/;
					if(value != null && value != ''){
						if(!idCardPartten.test(value)){
							return formatMsg;
				        }
					}
				}
			});
		})
	}
	
	// 加载运单列表
	function loadWayBillList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
				Toolbar) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
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
				url : '<%=request.getContextPath()%>/tms/sendcargo/getsignwaybilllist.shtml',
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
	                    text:'签收',
	                    handler : showSignDialog
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'异常登记',
	                    handler : toAbnormal
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'查看签收信息',
	                    handler : findSignInfo
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
			'signStatus' : $('#signStatus').val(),
			'out_store_status' : $('#out_store_status').val(),
			'transfer_status' : $('#transfer_status').val(),
			'exceptionStatus' : $('#exceptionStatus').val(),
			'receiveType' : $('#receiveType').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 显示 签收弹出框
	function showSignDialog(){
		if(!validateUserType()){
			return;
		}
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		if(selection[0].signStatus == 1){
			BUI.Message.Alert('该运单已签收','warning');
			return;
		}
		if(selection[0].exceptionStatus != 0){
			BUI.Message.Alert('异常状态的运单不能签收','warning');
			return;
		}
		if(selection[0].receiveType == 1){
			if(selection[0].isOutStore == 0){
				BUI.Message.Alert('未出库的运单不能签收','warning');
				return;
			}
		}
		$('#signWayBillNumber').val(selection[0].wayBillNumber);
		$('#agencyFund').text(selection[0].agencyFund == null ? 0 : selection[0].agencyFund);
		$('#freight').text(selection[0].freight == null ? 0 : selection[0].freight);
		$('#advanceCost').text(selection[0].advanceCost == null ? 0 : selection[0].advanceCost);
		$('#payMethod').text(selection[0].payMethodName);
		
		signDialog.show();
		loadForm();
	}
	
	// 加载 签收 弹出框
	function loadSignDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			signDialog = new Overlay.Dialog({
		    	title:'签收',
		        width:460,
		        height:500,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'signDialog',
		        success:function () {
		        	var isValid = signRecordForm.isValid();
		        	if(!isValid){
		        		return;
		        	}
		        	
		        	var form = $('#signRecordForm').serialize();
		        	$.ajax({
		        		type : 'post',
		        		url : '<%=request.getContextPath()%>/tms/sign/signwaybill.shtml',
		        		data : $('#signRecordForm').serialize(),
		        		success : function(result){
		        			if(result == 1){
		        				BUI.Message.Alert('签收成功','success');
		        				search();
		        			}else{
		        				BUI.Message.Alert('签收失败','error');
		        			}
		        		}
		        	});
		        	this.close();
		        }
		    });
			signDialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	// 加载 签收记录 弹出框
	function loadSignRecordDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			signRecordDialog = new Overlay.Dialog({
		    	title:'签收信息',
		        width:460,
		        height:500,
		        contentId:'signRecordDialog',
		        success:function () {
		        	this.close();
		        }
		    });
			signRecordDialog.set('effect',{effect : 'fade', duration : 400});
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
	
	// 查看签收信息
	function findSignInfo(){
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		if(selection[0].signStatus != 1){
			BUI.Message.Alert('该运单未签收','warning');
			return;
		}
		$('#agencyFund_info').text(selection[0].agencyFund == null ? 0 : selection[0].agencyFund);
		$('#payMethod_info').text(selection[0].payMethodName);
		$('#freight_info').text(selection[0].freight);
		$('#advanceCost_info').text(selection[0].advanceCost == null ? 0 : selection[0].advanceCost);
		signRecordDialog.show();
		$.ajax({
    		type : 'post',
    		url : '<%=request.getContextPath()%>/tms/sign/getsignrecord.shtml',
    		data : {'wayBillNumber' : selection[0].wayBillNumber},
    		success : function(result){
    			$('#signTimeStr').text(result.signTime);
    			$('#signPerson').text(result.signPerson);
    			$('#idCard').text(result.idCard);
    			$('#phone').text(result.phone);
    			$('#address').text(result.address);
    		}
    	});
	}
	</script>
</body>
</html>