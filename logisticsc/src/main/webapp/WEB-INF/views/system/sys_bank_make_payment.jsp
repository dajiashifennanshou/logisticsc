<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付记录</title>
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
           	<h2>支付记录</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		起始日期：<input id="onlineStartTimes"  type="text" class="calendar">-
        		<input id="onlineEndTimes" type="text" class="calendar">
        		支付类型：
        		<select id="orderType" class="form-control fcjselect" name="orderType">
		          		<option value="">请选择</option>
		          		<option value="0">保险费</option>
		          		<option value="1">预约费</option>
		          		<option value="2">运费</option>
		          		<option value="3">保证金</option>
		          	</select>
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
					condition:$("#condition").val(),
					startTime:$("#onlineStartTimes").val(),
					endTime:$("#onlineEndTimes").val(),
					orderTypes:$("#orderType").val()
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
				{title : '时间',elCls:'center',dataIndex:'time',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '支付订单号',elCls:'center',width:200,dataIndex :'requestid'},
				{title : '支付账户',elCls:'center',dataIndex :'loginName'},
				{title : '订单号',elCls:'center',width:150,dataIndex :'orderNumber'},
				{title : '支付类型',elCls:'center',dataIndex :'orderType',renderer:function(val){
					if(val == 0){
						return "保险";	
					}else if(val == 1){
						return "预约费";	
					}else if(val == 2){
						return "运费";	
					}else if(val == 3){
						return "保证金";	
					}
				}},
				
				{title : '支付方式',elCls:'center',dataIndex:'payproducttype',renderer:function(val){
					if(val == 'SALES'){
						return "网银";
					}
				}},
				{title : '金额',elCls:'center',dataIndex :'amount'},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/bank/getSelectPaymentList.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
				autoRender:true, 
			  	forceFit:true,
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