<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body feedback_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>网点管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
			        	<label>选择区域：</label>
				      	<select id="province" class="input-small" name="province"><option>省份</option></select>&nbsp;&nbsp;
				      	<select class="input-small" name="city"><option>城市</option></select>&nbsp;&nbsp;
				       	<select class="input-small" name="county"><option>区</option></select>
				       	<input name="content" type="text" placeholder="专线">
			        	<button type="button" class="button button-normal" onclick="search()">查询</button>
			        </li>
	      		</ul>
	        </form>
	     	<div id="render_grid">
	  
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
				endTime:$("#end_time").val(),
				condition:$("#condition").val()
			}
			store.load(params);
		}
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
					function(Grid,Data,Toolbar,Format,Select){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '网点名称', dataIndex : 'name'
					/* return "<a href='javascript:void(0)' onclick='showOutletsItems("+row.id+")'>"+val+"</a>"; */
				},
				{title : '编号', dataIndex : 'outletsNumber'},
				{title : '网点类型',dataIndex :'typeVal'},
				{title : '所属省',dataIndex :'provinceValue'},
				{title : '所属市', dataIndex : 'cityValue'},
			 	{title : '所属区',dataIndex : 'countyValue'},
			 	{title : '联系人', dataIndex : 'contactPerson'},
			 	{title : '固定电话', dataIndex : 'mobile'},
			 	{title : '所属专线', dataIndex : 'companyName'},
			 	{title : '管理员', dataIndex : 'trueName'},
			 	{title : '管理员账号', dataIndex : 'loginName'},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/sys/outlets/search.shtml',
				autoLoad:true,
				pageSize:10,
			});

			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
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
		
		function showOutletsItems(id){
			window.location.href="<%=request.getContextPath()%>/tms/sys/outlets/detail/list.shtml?outletsId="+id;
		}
	</script>
</body>
</html>