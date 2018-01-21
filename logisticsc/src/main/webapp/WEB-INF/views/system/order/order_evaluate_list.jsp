<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统-后台管理系统</title>
<style type="text/css">

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>订单评价管理</h2>
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		评价日期：<input id="startTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar">
	          	<input id="condition" name="condition" type="text" placeholder="输入订单号/运单号">
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
					condition:$("#condition").val(),
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
				{title : '评论时间',dataIndex :'evaluateTimeStr',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '订单号',dataIndex :'orderNumber',width:150},
				{title : '运单号',dataIndex :'wayBillNumber',width:150},
				{title : '物流商',dataIndex :'companyName',width:150},
				{title : '物流商/线路',dataIndex : 'companyName',width:200,renderer : function (value,obj) {
					return "从："+obj.startProvince+"-"+obj.startCity+"-"+obj.startCounty
							+"<br/>到："
							+obj.endProvince+"-"+obj.endCity+"-"+obj.endCounty;
				}},
				{title : '组织代码',dataIndex :'companyCode'},
				{title : '评价状态',dataIndex : 'status',renderer : function (value,obj) {
					if(obj.status == 0){
						return "已停用"; 
					}else{
						return "已启用"; 
					}
				}}, 
				{title : '操作',dataIndex : 'id',renderer : function (value,obj) {
					if(obj.status == 0){
						return "<a href='#' onclick='getEvaluate("+obj.evaId+")'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='updateEvaluation("+obj.evaId+")'>启用</a>";
					}else{
						return "<a href='#' onclick='getEvaluate("+obj.evaId+")'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='updateEvaluation("+obj.evaId+")'>禁用</a>"; 
					}
					}
				}
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/systemOrder/getEvaluation.shtml',
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
		function getEvaluate(id){
			window.location.href = "${configProps['project']}/systemOrder/doGetEvaluationReply.shtml?id="+id;
			return false;
		}
		function updateEvaluation(id){
			var evaId =id;
			$.ajax({
	    		cache: true,
	    		type: "POST",
	    		url:"${configProps['project']}/systemOrder/updateEvaluation.shtml",
	    		data:{"id":evaId},
	    		async: false,
	    		error: function(request) {
	    			alert("连接错误");
	    		},
	    		success: function(data) {
	    			if(data.result){
		      			BUI.Message.Alert('操作成功！',function(){
		      				window.location="${configProps['project']}/system/pageJump.shtml?url=/system/order/order_evaluate_list";
		      			},'success'); 
		      		}else{
		      			BUI.Message.Alert('操作失败！','error');
		      		}
	    			
	    		}
	    	});
		}
	</script>
</body>