<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>预约分账记录</title>
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
           	<h2>预约分账记录</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		分账日期：<input id="startTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar"> 
	          	<input id="condition" name="condition" type="text" placeholder="输入订单号">
	          	<input id="tmsLoginName" name="tmsLoginName" type="text" placeholder="输入到账用户">
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
					orderNumber:$("#condition").val(),
					startTime:$("#startTime").val(),
					endTime:$("#endTime").val(),
					tmsLoginName:$("#tmsLoginName").val()
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
				{title : '时间',elCls:'center',dataIndex:'paymentTime',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '支付订单号',elCls:'center',width:200,dataIndex :'paymentRequestid'},
				{title : '订单号',elCls:'center',width:150,dataIndex :'orderNumber'},
				{title : '金额（元）',elCls:'center',dataIndex:'amount'},
				{title : '分账类型',elCls:'center',dataIndex :'paymentOrderType',renderer:function(val){
					if(val == 1){
						return "预付分账";	
					}
				}},
				{title : '订单状态',elCls:'center',dataIndex :'orderState',renderer:function(val){
					if(val == 2){
						return "提货中";
					}else if(val == 3){
						return '议价';
					}else if(val == 4){
						return '货物入库';
					}else if(val == 5){
						return '运输中';
					}else if(val == 6){
						return '已到达';
					}else if(val == 7){
						return '送货中';
					}else if(val ==8){
						return '已签收';
					}else if(val ==11){
						return '已取消';
					}
					
				}},
				{title : '支付用户',elCls:'center',dataIndex:'platformLoginName'},
				{title : '物流商名称',elCls:'center',dataIndex :'companyName'},
				{title : '组织代码',elCls:'center',dataIndex :'companyCode'},
				{title : '网点名称',elCls:'center',dataIndex :'outletsName'},
				{title : '到账用户',elCls:'center',dataIndex:'tmsLoginName'},
				{title : '到账银行卡号',elCls:'center',dataIndex:'bankaccountnumber',width:150},
				{title : '分账状态',elCls:'center',renderer:function(val,obj){
					for (var int = 0; int < obj.splitStateList.length; int++) {
						if(obj.splitStateList[int].splitState == 0){
							return "分账成功";
						}
					}
				}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/bank/getMakeRecordSplit.shtml',
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
		
	</script>
</body>
</html>