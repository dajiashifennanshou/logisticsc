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
           	<h2>普通专线管理</h2>
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
				{title : '专线', dataIndex : 'companyName'},
				{title : '组织代码', dataIndex : 'companyCode'},
				{title : '地址',dataIndex :'companyAddress'},
				{title : '联系人',dataIndex :'contacts'},
				{title : '联系电话', dataIndex : 'contactsMobile'},
			 	{title : '物流商',dataIndex : 'userType',renderer:function(val){
			 		if(val == 4){
			 			return "是";
			 		}else{
			 			return "否";
			 		}
			 	}},
			 	{title : '管理员', dataIndex : 'trueName'},
			 	{title : '账号', dataIndex : 'loginName'},
			 	{title : '状态', dataIndex : 'userStatus',renderer:function(val){
			 		if(val == 2){
			 			return "禁用";
			 		}else if(val ==1 ){
			 			return "正常";
			 		}
			 	}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/sys/cmp/search.shtml',
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
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>禁用',
			         		triggerCls : 'btn-edit',
			           		listeners : {
			                	'click' : forbid
			                }
			          	}
		          	],
				}
				
			});
			function forbid(){
				var selects = grid.getSelection();
				var length = selects.length;
				if(length>0){
					var array = [];
					for(var i=0;i<length;i++){
						array.push(selects[i].userId);
					}
					$.ajax({
						url:"<%=request.getContextPath()%>/tms/sys/cmp/forbid.shtml",
						data:{userIds:array},
						dataType:'json',
						type:'post',
						success:function(data){
							if(data.result == true){
								alert("操作成功");
								store.load();
							}
						}
					})
				}
			}
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
	</script>
</body>
</html>