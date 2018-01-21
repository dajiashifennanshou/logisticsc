<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会员审核</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>会员审核</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		申请日期：<input id="staeTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar">
        		申请类型：
        		<select id="apply_type" class="form-control fcjselect">
	          		<option value="">请选择</option>
	          		<option value="0">企业货主</option>
	          		<option value="1">物流提供商</option>
	          		<option value="2">专线</option>
	          		<option value="3">修改资质</option>
	          	</select>
	          	审核状态：
        		<select id="apply_state" class="form-control fcjselect">
	          		<option value="">请选择</option>
	          		<option value="0">未处理</option>
	          		<option value="50">未通过</option>
	          		<option value="100">已通过</option>
	          	</select>
	          	<input id="condition" name="condition" type="text" placeholder="请输入用户登录名">
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
					staeTime:$("#staeTime").val(),
					endTime:$("#endTime").val(),
					apply_type:$("#apply_type").val(),
					apply_state:$("#apply_state").val()
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
			    {title : '申请时间',dataIndex :'time',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
			  	{title : '登录名',dataIndex :'login_name'},
				{title : '电话',dataIndex :'mobile'},
				{title : '公司',dataIndex :'company_name',width:150,},
				{title : '审核环节',dataIndex :'apply_name'},
				{title : '审核类型',dataIndex : 'apply_type',renderer : function (value,obj) {
						if(value==1 ){
							return "申请物流商";
						}else if(value==3){
							return "修改资质";
						}
						else if(value==0){
							return "申请企业货主";
						}else if(value==2){
							return "申请专线";
						}
					}
		        },
				{title : '审核状态',dataIndex : 'apply_state',renderer : function (value,obj) {
						if(value==0){
							return "未处理";
						}else if(value==50){
							return "未通过";
						}else if(value==100){
							return "已通过";
						}
					}
		        },
		        {title : '操作',dataIndex : 'apply_state',renderer : function (value,obj) {
					if(value==0){
						return "<a class='page-action' data-type='setTitle' href='${configProps['project']}/sysPlatformUser/getVerifyUser.shtml?id="+obj.userId+"'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='page-action' data-type='setTitle' href='${configProps['project']}/sysPlatformUser/doGetVerifyUser.shtml?id="+obj.userId+"'>处理</a>";
					}else if(value==50){
						return "<a class='page-action' data-type='setTitle' href='${configProps['project']}/sysPlatformUser/getVerifyUser.shtml?id="+obj.userId+"'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;处理";
					}else if(value==100){
						return "<a class='page-action' data-type='setTitle' href='${configProps['project']}/sysPlatformUser/getVerifyUser.shtml?id="+obj.userId+"'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;处理";
					}
				}
	        }
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sysPlatformUser/getVerifyUserList.shtml',
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