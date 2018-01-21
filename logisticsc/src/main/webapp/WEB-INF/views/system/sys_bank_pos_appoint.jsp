<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pos机收款记录</title>
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
           	<h2>POS机收款记录</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		<label>订单状态：</label>
	          	<select id="posStatus" style="width: 95px;" class="form-control fcjselect">
	          		<option value="">请选择</option>
	          		<option value="0">完结</option>
	          		<option value="1">撤销</option>
	          	</select> 
        		起始日期：<input id="startTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar">
	          	<input id="condition" style="width: 95px;" name="condition" type="text" placeholder="输入订单号">
	          	<input id="companyName" style="width: 95px;" name="companyName" type="text" placeholder="物流商名称">
	          	<input id="companyCode" style="width: 95px;" name="companyCode" type="text" placeholder="输入组织代码">
	          	<input id="outletsName" style="width: 95px;" name="outletsName" type="text" placeholder="输入网点名称">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params = {
					statuss:$("#posStatus").val(),
					orderNumber:$("#condition").val(),
					startTime:$("#startTime").val(),
					endTime:$("#endTime").val(),
					companyName:$("#companyName").val(),
					companyCode:$("#companyCode").val(),
					outletsName:$("#outletsName").val()
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
				{title : '时间',elCls:'center',dataIndex:'operateTime',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '订单号',elCls:'center',width:150,dataIndex :'orderNumber'},
				{title : '金额（元）',elCls:'center',dataIndex:'posMoney'},
				{title : '物流商名称',elCls:'center',dataIndex :'companyName'},
				{title : '组织代码',elCls:'center',dataIndex :'companyCode'},
				{title : '网点名称',elCls:'center',dataIndex :'outletsName'},
				{title : '收款账户',elCls:'center',dataIndex:'tmsLoginName'},
				{title : '订单状态',elCls:'center',renderer:function(val,obj){
					if(obj.statusys == 0){
						return "完结";
					}else if(obj.statusys == 1){
							return "撤销";
					}
				}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/bank/getBankPos.shtml',
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