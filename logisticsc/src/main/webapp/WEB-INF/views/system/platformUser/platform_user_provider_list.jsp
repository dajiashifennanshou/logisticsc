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
           	<h2>物流提供商管理</h2>
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		注册日期：<input id="staeTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar">
        		状态：
        		<select id="status" class="form-control fcjselect">
	          		<option value="">请选择</option>
	          		<option value="0">未启用</option>
	          		<option value="1">已启用</option>
	          	</select>
	          	<input id="condition" name="condition" type="text" placeholder="请输入账号">
	          	<input id="companyName" name="companyName" type="text" placeholder="请输入公司名称">
	          		<input id="companyCode" style="width: 95px;" name="companyCode" type="text" placeholder="请输入组织代码">
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
					loginName:$("#condition").val(),
					companyName:$("#companyName").val(),
					companyCode:$("#companyCode").val(),
					startTime:$("#staeTime").val(),
					endTime:$("#endTime").val(),
					status:$("#status").val()
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
					{title : '注册时间',dataIndex :'create_time', width:150,renderer:BUI.Grid.Format.datetimeRenderer},
					{title : '账户',dataIndex :'login_name'},
					{title : '电话',dataIndex :'mobile'},
					{title : '公司名称',dataIndex :'company_name',width:150},
					{title : '组织代码',dataIndex :'company_code'},
					{title : '联系人',dataIndex :'contacts1'},
					{title : '联系人电话',dataIndex :'contacts1_mobile'},
					{title : '状态',dataIndex : 'status',renderer : function (value,obj) {
						if(value==1){
							return "已启用";
						}else{
							return "未启用";
						}
					}},
					{title : '操作',dataIndex : 'status',renderer : function (value,obj) {
						if(value==1){
							return "<a class='page-action' data-type='setTitle' href='${configProps['project']}/sysPlatformUser/getUserCompanyProvider.shtml?id="+obj.userId+"'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer' class='page-action' data-type='setTitle' onclick='updateUserStatus("+obj.userId+")'>停用</a>  ";
						}else{
							return "<a class='page-action' data-type='setTitle' href='${configProps['project']}/sysPlatformUser/getUserCompanyProvider.shtml?id="+obj.userId+"'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer' class='page-action' data-type='setTitle' onclick='updateUserStatus("+obj.userId+")'>启用</a>  ";
						}
					}}
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sysPlatformUser/getProviderPlatformUser.shtml',
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
		function updateUserStatus(userId){
			$.ajax({
	    		cache: true,
	    		type: "POST",
	    		url:"${configProps['project']}/sysPlatformUser/updatePlatformUserStatus.shtml",
	    		data:{"userId":userId},
	    		async: false,
	    		error: function(request) {
	    			alert("连接错误");
	    		},
	    		success: function(data) {

	    			if(data.result){
		      			BUI.Message.Alert('操作成功！',function(){
		      				store.load();
		      			},'success'); 
		      		}else{
		      			BUI.Message.Alert('操作失败！','error');
		      		}
	    			
	    		}
	    	});
		}
	</script>
</body>