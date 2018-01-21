<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客服中心</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

<style type="text/css">
.search li{
	display: inline-block;
	float: left;
	margin: 10px 15px;
}
</style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>信息通知</h2>
        </div>
        <div class="panel-body">
	        <form id="info_search_form" class="well form-inline">
		        <div class="controls bui-form-group" data-rules="{dateRange : true}">
		        		<label>日期：</label>
		              <input id="start_time" name="startTime" class="input-small calendar" type="text"><label>&nbsp;-&nbsp;</label>
		              <input id="end_time" name="endTime" class="input-small calendar" type="text">
		            </div>
		        <button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="evaluation_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	
	       	</div>
       	</div>
    </div>
    
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params={
				startTime:$("#start_time").val(),
				endTime:$("#end_time").val()
			}
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format'],function(Grid,Data,Toolbar,Format){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '主题',elCls:'center',dataIndex :'title'},
				{title : '内容',elCls:'center',dataIndex :'content'},
				{title : '发布时间',elCls:'center', dataIndex : 'createTime',renderer:BUI.Grid.Format.dateRenderer}
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/notice/search.shtml',
				autoLoad:true,
				pageSize:10,
			});
			var grid = new Grid.Grid({
			   	render:'#evaluation_grid',
			   	autoRender: true,
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
			});
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });

		});	
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
		//验证表单
	    BUI.use('bui/form',function(Form){
	          new Form.HForm({
	        srcNode : '#info_search_form',
	        /* defaultChildCfg : {
	          validEvent : 'blur' //移除时进行验证
	        } */
	      }).render();
	    });
	</script> 
</body>
</html>