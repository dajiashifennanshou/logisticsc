<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>现金日记账</title>
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
				<h3>现金日记账</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>网点：</label><span>${outletsInfo.name}</span> 
				        	<label>开始时间</label>
				        	<input type="text" class="calendar" id="startTime"> 
				        	<label>结束时间</label>
				        	<input type="text" class="calendar" id="endTime">
				        	<label>科目</label>
				        	<select id="subject">
				        		<option value="">全部</option>
				        		<c:forEach items="${dictionaries}" var="dictionary">
									<option value="${dictionary.name}">${dictionary.name}</option>
								</c:forEach>
				        	</select> 
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号">
				            <button type="button" class="button" onclick="search()">查询</button>
				        </form>
					</div>
				</div>
				<div class="panel">
					<div class="panel-header">
						<h3>现金收支明细 <small>收入合计：<span id="income_money"></span>元， 支出合计：<span id="expend_money"></span>元</small></h3>
					</div>
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span24">
								<div id="moneyDiaryList"></div>
								<div id="moneyDiaryListBar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide" id="incomeDialog">
			<form class="form-horizontal" id="incomeForm" action="<%=request.getContextPath()%>/tms/financial/addmoneydiaryrecord.shtml" method="post">
				<input type="hidden" name="type" value="0">
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>日期：</label>
						<div class="controls">
							<input type="text" name="dateStr" class="calendar calendar-time" data-rules="{required:true, date:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">科目：</label>
						<div class="controls">
							<%-- <select id="costSubject" name="costSubject" class="input-normal">
								<c:forEach items="${dictionaries}" var="dictionary">
									<option value="${dictionary.name}">${dictionary.name}</option>
								</c:forEach>
							</select> --%>
							<label class="control-label" style="text-align: left;">杂费</label>
							<input type="hidden" name="costSubject" value="杂费">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>金额：</label>
						<div class="controls">
							<input type="text" name="money" class="input-normal" data-rules="{required:true,number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>付款人：</label>
						<div class="controls">
							<input type="text" name="person" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">付款单位：</label>
						<div class="controls">
							<input type="text" name="company" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">摘要：</label>
						<div class="controls">
							<textarea name="remark"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="hide" id="expendDialog">
			<form class="form-horizontal" id="expendForm" action="<%=request.getContextPath()%>/tms/financial/addmoneydiaryrecord.shtml" method="post">
				<input type="hidden" name="type" value="1">
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>日期：</label>
						<div class="controls">
							<input type="text" name="dateStr" class="calendar calendar-time" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">科目：</label>
						<div class="controls">
							<%-- <select id="costSubject" name="costSubject" class="input-normal">
								<c:forEach items="${dictionaries}" var="dictionary">
									<option value="${dictionary.name}">${dictionary.name}</option>
								</c:forEach>
							</select> --%>
							<label class="control-label" style="text-align: left;">杂费</label>
							<input type="hidden" name="costSubject" value="杂费">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>金额：</label>
						<div class="controls">
							<input type="text" name="money" class="input-normal" data-rules="{required:true,number:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>收款人：</label>
						<div class="controls">
							<input type="text" name="person" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">收款单位：</label>
						<div class="controls">
							<input type="text" name="company" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">发车单号：</label>
						<div class="controls">
							<input type="text" name="departNumber" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">运单号：</label>
						<div class="controls">
							<input type="text" name="wayBillNumber" class="input-normal">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">摘要：</label>
						<div class="controls">
							<textarea name="remark"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tools/DateUtils.js"></script>
	<script type="text/javascript">
		var grid, store;
		var incomeDialog, expendDialog, incomeForm, expendForm;
		$(function(){
			loadBuiCalendar();
			loadForm();
			loadMoneyDiaryList();
			loadIncomeDialog();
			loadExpendDialog();
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
				incomeForm = new Form.Form({
					srcNode : '#incomeForm',
					submitType : 'ajax',
					callback : function(result){
				    	if(result == 1){
				    		incomeDialog.close();
				    		BUI.Message.Alert('操作成功', 'success');
				    		store.load();
				    	}else{
				    		BUI.Message.Alert('操作失败', 'error');
				    	}
				    }
				}).render();
				expendForm = new Form.Form({
					srcNode : '#expendForm',
					submitType : 'ajax',
					callback : function(result){
				    	if(result == 1){
				    		expendDialog.close();
				    		BUI.Message.Alert('操作成功', 'success');
				    		store.load();
				    	}else{
				    		BUI.Message.Alert('操作失败', 'error');
				    	}
				    }
				}).render();
			})
		}
		
		// 加载现金日记列表
		function loadMoneyDiaryList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
					Toolbar) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ {
					title : '日期',
					dataIndex : 'date',
					renderer : function(value){
						return formatDateTime(value);
					}
				}, {
					title : '科目',
					dataIndex : 'costSubject'
				}, {
					title : '类型',
					dataIndex : 'type',
					renderer : function(value){
						if(value == 0){
							return '收入';
						}else{
							return '支出';
						}
					}
				}, {
					title : '金额',
					dataIndex : 'money'
				}, {
					title : '收/付款人',
					dataIndex : 'person'
				}, {
					title : '收/付款单位',
					dataIndex : 'company'
				}, {
					title : '发车单号',
					dataIndex : 'departNumber'
				}, {
					title : '运单号',
					dataIndex : 'wayBillNumber'
				} ];
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/financial/getmoneydiarylist.shtml',
					autoLoad : true,
					proxy : { method : 'post' },
					//remoteSort : true,
					pageSize : 10,
					listeners : {
						beforeprocessload : function(e){
							$('#income_money').text(e.data.data.income);
							$('#expend_money').text(e.data.data.expend);
						}
					}
				});
				grid = new Grid.Grid({
					render : '#moneyDiaryList',
					columns : columns,
					store : store,
					forceFit : true,
					plugins : [Grid.Plugins.CheckSelection],
					tbar:{
		                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
		                items:[{
		                	btnCls : 'button button-normal',
		                    text:'杂费收入',
		                    handler : function(){
		                    	incomeDialog.show();
		                    }
		                }, {
		                	btnCls : 'button button-normal',
		                    text:'杂费支出',
		                    handler : function(){
		                    	expendDialog.show();
		                    }
		                } ]
		            }
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#moneyDiaryListBar',
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
				'startTime' : $('#startTime').val(),
				'endTime' : $('#endTime').val(),
				'subject' : $('#subject').val(),
				'condition' : $('#condition').val()
			}
			store.load(params);
		}
		
		// 加载 杂费收入 弹出框
		function loadIncomeDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				incomeDialog = new Overlay.Dialog({
			    	title:'杂费收入',
			        width:460,
			        height:420,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'incomeDialog',
			        success:function () {
						if(incomeForm.isValid()){
							incomeForm.ajaxSubmit();
						}
			        }
			    });
				incomeDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		// 加载 杂费支出 弹出框
		function loadExpendDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				expendDialog = new Overlay.Dialog({
			    	title:'杂费支出',
			        width:460,
			        height:420,
			        //closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'expendDialog',
			        success:function () {
			        	if(expendForm.isValid()){
			        		expendForm.ajaxSubmit();
						}
			        }
			    });
				expendDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
	</script>
</body>
</html>