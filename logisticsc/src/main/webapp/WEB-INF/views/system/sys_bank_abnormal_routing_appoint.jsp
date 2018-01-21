<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>异常预约退款</title>
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
           	<h2>异常预约退款</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        	<!-- 	起始日期：<input id=""  type="text" class="calendar">-
        		<input id="" type="text" class="calendar"> -->
	          	<input id="condition" name="condition" type="text" placeholder="输入订单号">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    <input type="hidden" id="orderNumber"/>
    <input type="hidden" id="requestid"/>
    <input type="hidden" id="subAccount"/>
    <input type="hidden" id="orderType"/>
    <input type="hidden" id="money"/>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params = {
					orderNumber:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		var msg;
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Form,Overlay){
			Grid = Grid,
			Store = Data.Store,
			msg = BUI.Message,
			columns = [
				{title : '支付订单号',elCls:'center',width:200,dataIndex :'paymentRequestid'},
				{title : '订单号',elCls:'center',width:150,dataIndex :'orderNumber'},
				{title : '金额（元）',elCls:'center',dataIndex:'paymentAmount'},
				{title : '分账类型',elCls:'center',dataIndex :'paymentOrderType',renderer:function(val){
					if(val == 1){
						return "预付分账";	
					}
				}},
				{title : '订单状态',elCls:'center',dataIndex :'orderState',renderer:function(val){
					if(val == 2){
						return "提货中";
					}else{
						return '';
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
					}else{
						var count = 0;
						for(var i=0;i<obj.splitStateList.length;i++){
							if(obj.splitStateList[i].splitState == 0){
								count += 1;
							}
						}
						if(count == 0){
							return "分账失败";
						}else{
							return "分账成功";
						}
					}
				}},
				{title : '时间',elCls:'center',dataIndex:'paymentTime',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '操作',elCls:'center',renderer:function(val,obj){
					var subAccount = "",
						drawback = "";
					
					if(!obj.splitStateList || obj.splitStateList.length == 0){
						$("#requestid").val(obj.paymentRequestid);
						$("#subAccount").val(obj.ledgerno);
						$("#orderType").val(obj.paymentOrderType);
						$("#orderNumber").val(obj.orderNumber);
						$("#money").val(obj.paymentRequestid);
						subAccount = "<a href='javascript:void()' onclick='orderDrawback()'>退款</a>&nbsp;&nbsp;&nbsp;&nbsp;";
					}else{
						var count = 0;
						for(var i=0;i<obj.splitStateList.length;i++){
							if(obj.splitStateList[i].splitState == 0){
								count += 1;
							}
						}
						if(count == 0){
							$("#requestid").val(obj.paymentRequestid);
							$("#subAccount").val(obj.ledgerno);
							$("#orderType").val(obj.paymentOrderType);
							$("#orderNumber").val(obj.orderNumber);
							$("#money").val(obj.paymentRequestid);
							subAccount = "<a href='javascript:void()' onclick='orderDrawback()'>退款</a>&nbsp;&nbsp;&nbsp;&nbsp;";
						}
					}
					return subAccount;
				}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/bank/searchSubAccount4App.shtml',
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
		

		 //退款操作
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
		} 
	</script>
</body>
</html>