<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>运费分账管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body, #hidden_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>运费分账</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		支付日期：<input id="startTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar"> 
	          	<input id="condition" name="condition" type="text" placeholder="输入订单号">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    <div class="hide" id="signDialog">
			<form class="form-horizontal" id="signRecordForm">
			<input type="hidden" id="grid_index">
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>支付订单号：</label>
						<div class="controls">
							<span id="paymentRequestid" class="control-label" style="text-align:left;"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>到账网点：</label>
						<div class="controls">
							<span class="control-label" style="text-align:left;" id="outletsName"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>到账用户：</label>
						<div class="controls">
							<span  class="control-label" style="text-align:left;" id="tmsLoginName"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>金额(元)：</label>
						<div class="controls">
							<span  class="control-label" id="paymentAmount" style="text-align:left;color: red;font-size: 25px;"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label"><s>*</s>验证码：</label>
						<div class="controls">
							<input type="text" id="code" name="code" data-rules="{required:true}" >
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">&nbsp;</label>
						<div class="controls">
							<div class="button button-normal" id="sendcode" onclick="enterpriseSms()">获取验证码</div>
						</div>
					</div>
				</div>
			</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
	var number = 0;
		var signDialog;
		$(function(){
			loadForm();
			loadSignDialog();
		});
		
		function loadForm(){
			BUI.use('bui/form',function(Form){
				signRecordForm = new Form.Form({
					srcNode : '#signRecordForm'
				}).render();
			})
		}
		function loadSignDialog(){
			BUI.use(['bui/overlay'],function(Overlay){
				signDialog = new Overlay.Dialog({
			    	title:'分账验证',
			        width:460,
			        contentId:'signDialog',
			        success:function () {
			        	number ++;
			        	if(number == 1){
			        		signRecordForm.valid();
				    		if(!signRecordForm.isValid()){
				    			return;
				    		}
				    		var index = $('#grid_index').val();
							var data = {
									requestid:grid.getItemAt(index).paymentRequestid,
									subAccount:grid.getItemAt(index).ledgerno,
									orderType:grid.getItemAt(index).paymentOrderType,
									orderNumber:grid.getItemAt(index).orderNumber,
									tmsUserId:grid.getItemAt(index).tmsUserId,
									paymentAmount:grid.getItemAt(index).paymentAmount,
									code:$("#code").val()
							}
							$.ajax({
								url:'<%=request.getContextPath()%>/sys/bank/orderBillRouting.shtml',
								type:'post',
								data:data,
								success:function(result){
									if(result.result){
										BUI.Message.Alert("操作成功",function(){
											store.load();
											signDialog.close();
										},"success");
										
									}else{
										BUI.Message.Alert(result.data);
										signDialog.close();
									}
									number = 0;
								}
							})
			        	}
			        }
			    });
				signDialog.set('effect',{effect : 'fade', duration : 400});
			});
		}
		
		//获取验证码
		function enterpriseSms(){
			//方式验证码
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/sys/bank/verificationMessage.shtml",
				success : function(data) {
					if(data.result){
						$('#sendcode').unbind('click');
		    				$("#sendcode").css("background-color","#666666");
		    				var time = 60;
		    				var interval = setInterval(function(){
							time--;
							$("#sendcode").empty();
							$("#sendcode").append(time + "秒后可再次获取");
							if(time == 0){
								$.ajax({
									type : "POST",
									url : "<%=request.getContextPath()%>/sys/bank/eliminateMessage.shtml",
									success : function(data) {
										if(data.result){
											time = 60;
											$("#sendcode").empty();
											$("#sendcode").append("获取验证码");
											clearInterval(interval);
											$("#sendcode").css("background-color","#dadada");
											$('#sendcode').bind('click');
										}
									}
								});
							}
						},1000);
					}else{
						BUI.Message.Alert('发送失败','error');
					}
				}
			});
		}
		//查询
		function search(){
			var params = {
					orderNumber:$("#condition").val(),
					startTime:$("#startTime").val(),
					endTime:$("#endTime").val()
			};
			store.load(params);
		}
		//表格渲染
		var msg;
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Form,Overlay){
			var Grid = Grid,
			Store = Data.Store,
			msg = BUI.Message,
			columns = [
				{title : '时间',elCls:'center',dataIndex:'paymentTime',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '支付订单号',elCls:'center',width:200,dataIndex :'paymentRequestid'},
				{title : '订单号',elCls:'center',width:150,dataIndex :'orderNumber'},
				{title : '金额（元）',elCls:'center',dataIndex:'paymentAmount'},
				{title : '分账类型',elCls:'center',dataIndex :'paymentOrderType',renderer:function(val){
					if(val == 2){
						return "运费分账";	
					}
				}},
				{title : '订单状态',elCls:'center',dataIndex :'orderState',renderer:function(val){
					if(val == 4){
						return "货物入库";
					}else if(val == 5){
						return '运输中';
					}else if(val == 6){
						return '已到达';
					}else if(val == 7){
						return '送货中';
					}else if(val ==8){
						return '已签收';
					}
				}},
				{title : '支付用户',elCls:'center',dataIndex:'platformLoginName'},
				{title : '物流商名称',elCls:'center',dataIndex :'companyName'},
				{title : '组织代码',elCls:'center',dataIndex :'companyCode'},
				{title : '网点名称',elCls:'center',dataIndex :'outletsName'},
				{title : '到账用户',elCls:'center',dataIndex:'tmsLoginName'},
				{title : '分账状态',elCls:'center',renderer:function(val,obj){
					if(obj.splitStateList.length == 0){
						return "未分账";
					}else if(obj.splitStateList[0].splitState == 2){
							return "审核中";
					}else if(obj.splitStateList[0].splitState == 1){
						return "未分账";
					}
				}},
				{title : '操作',elCls:'center',width:250,renderer:function(val,obj,index){
					var subAccount = "",
						drawback = "";
					
					if(!obj.splitStateList || obj.splitStateList.length == 0){
						if(obj.ledgerno ==null || obj.ledgerno==''){
							subAccount="<span style='color: red'>网点管理员未绑定银行卡信息！</span>"
						}else{
							subAccount = "<a href='javascript:void()' onclick='orderSubAccount("+index+")'>分账</a>";
						}
					}else{
						if(obj.splitStateList[0].splitState == 2){
							subAccount = "分账";
						}else{
							if(obj.ledgerno ==null || obj.ledgerno==''){
								subAccount="<span style='color: red'>网点管理员未绑定银行卡信息！</span>"
							}else{
								subAccount = "<a href='javascript:void()' onclick='orderSubAccount("+index+")'>分账</a>";
							}
						}
					}
					return subAccount;
				}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/bank/searchSubAccount5App.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
				autoRender:true, 
			  	//forceFit:true,
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection],
			});
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store,
		    });
		});	
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
		
		//分账操作
		function orderSubAccount(index){
			$('#grid_index').val(index);
			$("#code").val("");
			$("#paymentRequestid").text(grid.getItemAt(index).paymentRequestid);
			$("#paymentAmount").text(grid.getItemAt(index).paymentAmount);
			$("#outletsName").text(grid.getItemAt(index).outletsName);
			$("#tmsLoginName").text(grid.getItemAt(index).tmsLoginName);
			signDialog.show();
		}
		
		<%-- //退款操作
		function orderDrawback(){
				var data = {
						requestid:$("#requestid").val(),
						money:$("#money").val(),
						orderNumber:$("#orderNumber").val()
				}
				msg.Confirm("确定要退款？",function(){
					$.ajax({
						url:'<%=request.getContextPath()%>/sys/bank/subAccountDrawback.shtml',
						type:'post',
						data:data,
						success:function(result){
							if(result.result){
								msg.Alert("操作成功",function(){
									$("#requestid").val("");
									$("#money").val("");
									$("#orderNumber").val("");
									store.load();
								},"success");
								
							}
						}
					})
				},"question");
		} --%>
	</script>
</body>
</html>