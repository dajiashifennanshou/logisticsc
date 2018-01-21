<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预付款</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-stdmod-body{
	overflow-x : hidden;
	overflow-y : auto;
}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>预付款</h3>
			</div>
			<div class="panel-body">
				<div class="panel">
					<div class="panel-header">
						<h3>预付款列表</h3>
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
		</div>
		<div class="hide" id="rechargeDialog">
			<form class="form-horizontal" id="rechargeForm">
				<div class="row">
					<div class="control-group">
						<label class="control-label">公司：</label>
						<div class="controls">
							<input type="text" name="companyName" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">线路：</label>
						<div class="controls">
							<select id="lineInfo" name="lineId" class="input-normal" style="width: 166px;"></select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款单位：</label>
						<div class="controls">
							<input type="text" name="advanceMoneyCompany" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">充值比率：</label>
						<div class="controls">
							<span class="control-text">
								<span>1 : </span>
								<span>
									<c:if test="${not empty depositRatio.depositRatio}">${depositRatio.depositRatio}</c:if>
									<c:if test="${empty depositRatio.depositRatio}">1</c:if>
								</span>
							</span>
							<c:if test="${not empty depositRatio.depositRatio}">
								<input type="hidden" name="advanceRatio" class="input-normal" value="${depositRatio.depositRatio}">
							</c:if>
							<c:if test="${empty depositRatio.depositRatio}">
								<input type="hidden" name="advanceRatio" class="input-normal" value="1">
							</c:if>
							
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>充值：</label>
						<div class="controls">
							<input type="text" name="advanceMoney" class="input-normal" data-rules="{required:true, number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>联系人：</label>
						<div class="controls">
				            <input type="text" name="contactPerson" class="input-normal" data-rules="{required:true}">
				        </div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>手机：</label>
						<div class="controls">
							<input type="text" name="phone" class="input-normal" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号码不正确'}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">电话：</label>
						<div class="controls">
							<input type="text" name="telephone" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">Email：</label>
						<div class="controls">
							<input type="text" name="email" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">QQ：</label>
						<div class="controls">
							<input type="text" name="qq" class="input-normal">
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="hide" id="advanceMoneyRecordDialog">
			<div id="advanceMoneyRecordList"></div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var grid, store, advanceMoneyRecordStore, advanceMoneyRecordGrid;
		var rechargeForm;
		
		var rechargeDialog, advanceMoneyReocrdDialog;
		$(function(){
			loadReceivableAccountList();
			loadRechargeDialog();
			loadAdvanceMoneyRecordDialog();
		});
		
		// 加载应收款列表
		function loadReceivableAccountList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '线路',
					dataIndex : 'lineName'
				}, {
					title : '余额',
					dataIndex : 'remainMoney'
				}, {
					title : '联系人',
					dataIndex : 'contactPerson'
				}, {
					title : '手机号',
					dataIndex : 'mobile'
				}, {
					title : '电话',
					dataIndex : 'phone'
				}, {
					title : 'Email',
					dataIndex : 'email'
				} ];
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getlineinfos.shtml',
					autoLoad : true,
					//remoteSort : true,
					pageSize : 10
				});
				grid = new Grid.Grid({
					render : '#receivableAccountList',
					columns : columns,
					store : store,
					forceFit : true,
					plugins : [Grid.Plugins.CheckSelection],
					tbar:{
		                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
		                items:[{
		                	btnCls : 'button button-normal',
		                    text:'充值',
		                    handler : function(){
		                    	buildLineInfoSelect();
		                    	rechargeDialog.show();
		                    	loadForm();
		                    }
		                }, {
		                	btnCls : 'button button-normal',
		                    text:'扣除'
		                }, {
		                	btnCls : 'button button-normal',
		                    text:'查看充值记录',
		                    handler : findAdvanceMoneyRecord
		                } ]
		            }
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
		
		function loadForm(){
			BUI.use('bui/form',function(Form){
				rechargeForm = new Form.Form({
					srcNode : '#rechargeForm'
				}).render();
			})
		}
		
		// 加载 充值 弹出框
		function loadRechargeDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				rechargeDialog = new Overlay.Dialog({
			    	title:'充值',
			        width:450,
			        height:500,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'rechargeDialog',
			        success:function () {
			        	if(rechargeForm.isValid()){
			        		addRechargeRecord();
				            this.close();
			        	}
			        }
			    });
				rechargeDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 添加充值记录
		function addRechargeRecord(){
			var form = $('#rechargeForm').serialize();
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/financial/addlineadvancemoneyrecord.shtml',
				data : $('#rechargeForm').serialize(),
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('操作成功','success');
						store.load();
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		}
		
		// 查看充值记录
		function findAdvanceMoneyRecord(){
			showAdvanceMoneyRecordDialog();
		}
		
		// 显示 充值记录 弹出框
		function showAdvanceMoneyRecordDialog(){
			var selection = grid.getSelection();
			if(selection.length == 0){
				BUI.Message.Alert('请选择','warning');
				return;
			}else if(selection.length > 1){
				BUI.Message.Alert('只能选择一条记录','warning');
				return;
			}
			$('#advanceMoneyRecordList').html('');
			loadAdvanceMoneyRecordList(selection[0].id);
			advanceMoneyReocrdDialog.show();
		}
		
		// 加载 充值记录 弹出框
		function loadAdvanceMoneyRecordDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				advanceMoneyReocrdDialog = new Overlay.Dialog({
			    	title:'充值记录',
			        width:800,
			        height:400,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'advanceMoneyRecordDialog',
			        success:function () {
			            this.close();
			        }
			    });
				advanceMoneyReocrdDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 加载收款记录列表
		function loadAdvanceMoneyRecordList(lineId){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '公司',
					elCls : 'center',
					dataIndex : 'companyName'
				}, {
					title : '付款单位',
					elCls : 'center',
					dataIndex : 'advanceMoneyCompany'
				}, {
					title : '充值比率',
					elCls : 'center',
					dataIndex : 'advanceRatio'
				}, {
					title : '金额',
					elCls : 'center',
					dataIndex : 'advanceMoney'
				}, {
					title : '联系人',
					elCls : 'center',
					dataIndex : 'contactPerson'
				}, {
					title : '手机',
					elCls : 'center',
					dataIndex : 'phone'
				}, {
					title : '电话',
					elCls : 'center',
					dataIndex : 'telephone'
				}, {
					title : 'Email',
					elCls : 'center',
					dataIndex : 'email'
				}, {
					title : 'QQ',
					elCls : 'center',
					dataIndex : 'qq'
				} ];
				
				advanceMoneyRecordStore = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getlineadvancemoneyrecord.shtml',
					autoLoad : true,
					params : { 'lineId' : lineId }
					//remoteSort : true,
					//pageSize : 10
				});
				advanceMoneyRecordGrid = new Grid.Grid({
					render : '#advanceMoneyRecordList',
					columns : columns,
					store : advanceMoneyRecordStore,
					//forceFit : true
				});

				advanceMoneyRecordGrid.render();
			});
		}
		
		// 构建线路信息 select 数据
		function buildLineInfoSelect(){
			var html = '';
			var selection = grid.getItems();
			for(var i = 0; i < selection.length; i++){
				html += '<option value="'+selection[i].id+'">'+selection[i].lineName+'</option>';
			}
			$('#lineInfo').html(html);
		}
	</script>
</body>
</html>