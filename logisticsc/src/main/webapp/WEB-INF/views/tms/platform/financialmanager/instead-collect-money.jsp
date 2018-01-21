<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代收货款</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-stdmod-body{
	overflow-x : hidden;
	overflow-y : auto;
}
.form-horizontal input{width: 140px;}
.form-horizontal select{width: 166px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>代收货款</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>发站网点：</label>
				        	<select id="startOutlets">
				        		<option value="">全部</option>
				        		<c:forEach items="${outletsInfos}" var="outletsInfo">
				        			<option value="${outletsInfo.id}">${outletsInfo.name}</option>
				        		</c:forEach>
				        	</select>
				        	<label>到站网点：</label><span>${outletsInfo.name}</span> 
				        	<label>开始时间</label>
				        	<input type="text" class="calendar" id="startTime"> 
				        	<label>结束时间</label>
				        	<input type="text" class="calendar" id="endTime">
				            <input type="text" class="input-normal" id="condition" placeholder="运单号">
				            <button type="button" class="button" onclick="search()">查询</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>代收货款列表<small> 运单数：<span id="wayBillNum"></span>个， 货款合计：<span id="insteadCollectMoney"></span>元</small></h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="insteadCollectMoneyList"></div>
								<div id="insteadCollectMoneyListBar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide" id="insteadCollectMoneyDialog">
			<form class="form-horizontal" id="insteadCollectMoneyForm" action="<%=request.getContextPath()%>/tms/financial/addinsteadcollectmoney.shtml" method="post">
				<input type="hidden" id="wayBillNumber_collect" name="wayBillNumber" value="0">
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款单位：</label>
						<div class="controls">
							<span id="payMoneyCompany_collect_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="payMoneyCompany_collect" name="payMoneyCompany" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款人：</label>
						<div class="controls">
							<span id="payMoneyPerson_collect_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="payMoneyPerson_collect" name="payMoneyPerson" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">代收货款：</label>
						<div class="controls">
							<span id="insteadCollectMoney_collect_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="insteadCollectMoney_collect" name="insteadCollectMoney" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">实收货款：</label>
						<div class="controls">
							<input type="text" name="actualCollectMoney" class="input-normal" data-rules="{required:true,number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">收款人：</label>
						<div class="controls">
							<span id="collectMoneyPerson_collect_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="collectMoneyPerson_collect" name="collectMoneyPerson" class="input-normal">
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="hide" id="payMoneyDialog">
			<form class="form-horizontal" id="payMoneyForm" action="<%=request.getContextPath()%>/tms/financial/addinsteadcollectmoney.shtml" method="post">
				<input type="hidden" id="wayBillNumber_pay" name="wayBillNumber" value="0">
				<div class="row">
					<div class="control-group">
						<label class="control-label">收款单位：</label>
						<div class="controls">
							<span id="collectMoneyCompany_pay_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="collectMoneyCompany_pay" name="collectMoneyCompany" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">收款人：</label>
						<div class="controls">
							<span id="collectMoneyPerson_pay_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="collectMoneyPerson_pay" name="collectMoneyPerson" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">代收货款：</label>
						<div class="controls">
							<span id="insteadCollectMoney_pay_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="insteadCollectMoney_pay" name="insteadCollectMoney" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">已转货款：</label>
						<div class="controls">
							<input type="text" name="transferredMoney" class="input-normal" data-rules="{required:true,number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款人：</label>
						<div class="controls">
							<span id="payMoneyPerson_pay_text" class="control-label" style="text-align: left;"></span>
							<input type="hidden" id="payMoneyPerson_pay" name="payMoneyPerson" class="input-normal">
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="hide" id="payDetailDialog">
			<div id="payDetailList"></div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
		var grid, store, payDetailGrid, payDetailStore;
		var insteadCollectMoneyDialog, payMoneyDialog, insteadCollectMoneyForm, payMoneyForm;
		var payDetailDialog;
		$(function(){
			loadBuiCalendar();
			loadForm();
			loadInsteadCollectMoneyList();
			loadInsteadCollectMoneyDialog();
			loadPayMoneyDialog();
			loadPayDetailDialog();
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
		
		function loadForm(){
			//var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
			BUI.use('bui/form',function(Form){
				insteadCollectMoneyForm = new Form.Form({
					srcNode : '#insteadCollectMoneyForm',
					submitType : 'ajax',
					callback : function(result){
				    	if(result == 1){
				    		insteadCollectMoneyDialog.close();
				    		BUI.Message.Alert('操作成功', 'success');
				    		store.load();
				    	}else{
				    		BUI.Message.Alert('操作失败', 'error');
				    	}
				    }
				}).render();
				payMoneyForm = new Form.Form({
					srcNode : '#payMoneyForm',
					submitType : 'ajax',
					callback : function(result){
				    	if(result == 1){
				    		payMoneyDialog.close();
				    		BUI.Message.Alert('操作成功', 'success');
				    		store.load();
				    	}else{
				    		BUI.Message.Alert('操作失败', 'error');
				    	}
				    }
				}).render();
			})
		}
		
		// 加载代收货款列表
		function loadInsteadCollectMoneyList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '运单号',
					width : 130,
					dataIndex : 'wayBillNumber'
				}, {
					title : '发站网点',
					dataIndex : 'startOutletsName'
				}, {
					title : '到站网点',
					dataIndex : 'targetOutletsName'
				}, {
					title : '代收货款',
					dataIndex : 'agencyFund'
				}, {
					title : '发货人',
					dataIndex : 'consignor'
				}, {
					title : '收货人',
					dataIndex : 'consignee'
				}, {
					title : '垫付货款',
					dataIndex : 'advancePayment'
				}, {
					title : '实收货款',
					dataIndex : 'actualCollectMoney'
				}, {
					title : '已转货款',
					dataIndex : 'transferredMoney'
				}, {
					title : '收款人',
					dataIndex : 'collectMoneyPerson'
				} ];
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getinsteadcollectmoneywaybilllist.shtml',
					autoLoad : true,
					proxy : { method : 'post' },
					//remoteSort : true,
					pageSize : 10,
					listeners : {
						beforeprocessload : function(e){
							$('#wayBillNum').text(e.data.results);
							$('#insteadCollectMoney').text(e.data.data);
						}
					}
				});
				grid = new Grid.Grid({
					render : '#insteadCollectMoneyList',
					columns : columns,
					store : store,
					forceFit : true,
					plugins : [Grid.Plugins.CheckSelection],
					tbar:{
		                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
		                items:[{
		                	btnCls : 'button button-normal',
		                    text:'代收货款',
		                    handler : showInsteadCollectMoneyDialog
		                }, {
		                	btnCls : 'button button-normal',
		                    text:'支付货款',
		                    handler : showPayMoneyDialog
		                }, {
		                	btnCls : 'button button-normal',
		                    text:'支付明细',
		                    handler : showPayDetailDialog
		                } ]
		            }
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#insteadCollectMoneyListBar',
					elCls : 'pagination pull-right',
					store : store
				});
				bar.render();
				grid.render();
			});
		}
		
		function getGridSelectedRow(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return null;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条记录','warning');
				return null;
			}
			return selection[0];
		}
		
		// 查询
		function search(){
			var params = {
				'startOutlets' : $('#startOutlets').val(),
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val(),
				'subject' : $('#subject').val(),
				'condition' : $('#condition').val()
			}
			store.load(params);
		}
		
		// 加载 代收货款 弹出框
		function loadInsteadCollectMoneyDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				insteadCollectMoneyDialog = new Overlay.Dialog({
			    	title:'代收货款',
			        width:460,
			        height:350,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'insteadCollectMoneyDialog',
			        success:function () {
						if(insteadCollectMoneyForm.isValid()){
							insteadCollectMoneyForm.ajaxSubmit();
						}
			        }
			    });
				insteadCollectMoneyDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 显示 代收货款 弹出框
		function showInsteadCollectMoneyDialog(){
			var selected = getGridSelectedRow();
        	if(selected != null){
        		$('#wayBillNumber_collect').val(selected.wayBillNumber);
        		$('#payMoneyCompany_collect').val(selected.consigneeCompany);
        		$('#payMoneyCompany_collect_text').text(selected.consigneeCompany);
        		$('#payMoneyPerson_collect').val(selected.consignee);
        		$('#payMoneyPerson_collect_text').text(selected.consignee);
        		$('#insteadCollectMoney_collect').val(selected.agencyFund);
        		$('#insteadCollectMoney_collect_text').text(selected.agencyFund);
        		$('#collectMoneyPerson_collect').val(selected.consignor);
        		$('#collectMoneyPerson_collect_text').text(selected.consignor);
        		insteadCollectMoneyDialog.show();
        	}
		}
		
		// 加载 支付货款 弹出框
		function loadPayMoneyDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				payMoneyDialog = new Overlay.Dialog({
			    	title:'支付货款',
			        width:460,
			        height:350,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'payMoneyDialog',
			        success:function () {
			        	if(payMoneyForm.isValid()){
			        		payMoneyForm.ajaxSubmit();
						}
			        }
			    });
				payMoneyDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 显示 支付货款  弹出框
		function showPayMoneyDialog(){
			var selected = getGridSelectedRow();
        	if(selected != null){
        		$('#wayBillNumber_pay').val(selected.wayBillNumber);
        		$('#collectMoneyCompany_pay').val(selected.consignorCompany);
        		$('#collectMoneyCompany_pay_text').text(selected.consignorCompany);
        		$('#collectMoneyPerson_pay').val(selected.consignor);
        		$('#collectMoneyPerson_pay_text').text(selected.consignor);
        		$('#insteadCollectMoney_pay').val(selected.agencyFund);
        		$('#insteadCollectMoney_pay_text').text(selected.agencyFund);
        		$('#payMoneyPerson_pay').val(selected.consignee);
        		$('#payMoneyPerson_pay_text').text(selected.consignee);
        		payMoneyDialog.show();
        	}
		}
		
		// 加载支付明细 弹出框
		function loadPayDetailDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				payDetailDialog = new Overlay.Dialog({
			    	title:'支付明细',
			        width:900,
			        height:350,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'payDetailDialog',
			        success:function () {
			        	this.close();
			        }
			    });
				payDetailDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		function showPayDetailDialog(){
			$('#payDetailList').html('');
			var selected = getGridSelectedRow();
			if(selected == null){
				return;
			}
			BUI.use([ 'bui/grid', 'bui/data' ], function(Grid, Data) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '运单号',
					width : 130,
					dataIndex : 'wayBillNumber'
				}, {
					title : '收款单位',
					dataIndex : 'collectMoneyCompany'
				}, {
					title : '收款人',
					dataIndex : 'collectMoneyPerson'
				}, {
					title : '代收货款',
					dataIndex : 'insteadCollectMoney'
				}, {
					title : '实收货款',
					dataIndex : 'actualCollectMoney'
				}, {
					title : '已转货款',
					dataIndex : 'transferredMoney'
				}, {
					title : '付款单位',
					dataIndex : 'payMoneyCompany'
				}, {
					title : '付款人',
					dataIndex : 'payMoneyPerson'
				}, {
					title : '操作时间',
					width : 150,
					dataIndex : 'operateTime',
					renderer:BUI.Grid.Format.datetimeRenderer
				} ];
				
				payDetailStore = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getinsteadcollectmoneylist.shtml',
					autoLoad : true,
					proxy : { method : 'post' },
					params : { 'wayBillNumber' : selected.wayBillNumber }
					//remoteSort : true,
					//pageSize : 10
				});
				payDetailGrid = new Grid.Grid({
					render : '#payDetailList',
					columns : columns,
					store : payDetailStore,
					//forceFit : true,
					//plugins : [Grid.Plugins.CheckSelection]
				});

				payDetailGrid.render();
			});
			payDetailDialog.show();
		}
		
	</script>
</body>
</html>