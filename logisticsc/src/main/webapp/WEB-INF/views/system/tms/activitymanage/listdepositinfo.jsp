<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>活动管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css"
	rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css"
	rel="stylesheet">
<style>
/**内容超出 出现滚动条 **/
.bui-stdmod-body feedback_dia {
	overflow-x: hidden;
	overflow-y: auto;
}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-header">
			<h2>专线预存活动</h2>
		</div>
		<div class="panel-body">
			<form id="search_form" class="well form-inline">
				<input id="condition" name="condition" type="text" placeholder="专线">
				<button type="button" class="button button-normal"
					onclick="search()">查询</button>
			</form>
			<div id="render_grid"></div>
			<div id="pagingbar"></div>
		</div>
	</div>
	<!-- 默认隐藏 -->
	<div id="add_edit_dia" class="hide">
		<form class="form-horizontal">
			<div class="control-group">
				<div class="control-label">主题：</div>
				<div class="controls">
					<input name="title" type="text" />
				</div>
			</div>
			<div class="control-group">
				<div class="control-label">信息类型：</div>
				<div class="controls">
					<select id="type" name="type" class="input-small">
					</select>
				</div>
			</div>
			<div class="control-group">
				<div class="control-label">内容：</div>
				<div class="controls control-row-auto">
					<textarea name="content" class="control-row2 input-large"></textarea>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript"
		src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params={
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
				{title : '网点', dataIndex : 'name'},
				{title : '线路',dataIndex :'lineName'},
				{title : '账号', dataIndex : 'loginName'},
				{title : '联系人', dataIndex : 'trueName'},
				{title : '预存比例', dataIndex : 'depositRatio',renderer:function(val){
					return "1&nbsp;&nbsp;:&nbsp;&nbsp;"+val;
				}},
				{title : '开始时间',dataIndex :'startTime',renderer:BUI.Grid.Format.dateRenderer},
				{title : '结束时间', dataIndex : 'endTime',renderer:BUI.Grid.Format.dateRenderer},
				{title : '状态', dataIndex : 'status',renderer:function(val){
					if(val==1){
						return "未发布";
					}else if(val==2){
						return "审核中";
					}else if(val == 3){
						return "已发布";
					}else if(val==4){
						return "发布失败";
					}
				}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/system/tms/ratio/search.shtml',
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
				tbar:{
					items : [{
	                  	btnCls : 'button button-small',
	                  	text : '<i class="icon-plus"></i>审核通过',
	                  	listeners : {
	                    	"click":auditPass
	                  	}
	                },{
	                  	btnCls : 'button button-small',
	                  	text : '<i class="icon-plus"></i>拒绝',
	                  	listeners : {
	                    	'click':auditNotPass
	                  	}
	                }]
				}
				
			});
			//审核通过
			function auditPass(){
				var selects = grid.getSelection();
				var length = selects.length;
				if(length>0){
					var array = [];
					for(var i=0;i<length;i++){
						if(selects[i].status==3||selects[i].status==4){
							alert("当前操作禁止，该记录已经被审核过");
							return false;
						}else{
							array.push(selects[i].id);
						}
					}
					
					$.ajax({
						url:'<%=request.getContextPath()%>/system/tms/ratio/audit/pass.shtml',
						type:'post',
						data:{ratioIds:array},
						dataType:'json',
						success:function(data){
							if(data.result == true){
								alert("操作成功");
								store.load();
							}
						}
					})
				}
			}
			//未通过审核
			function auditNotPass(){
				var selects = grid.getSelection();
				var length = selects.length;
				if(length>0){
					var array = [];
					for(var i=0;i<length;i++){
						if(selects[i].status==4){
							alert("不能重复操作");
							return false;
						}else{
							array.push(selects[i].id);
						}
						
					}
					$.ajax({
						url:'<%=request.getContextPath()%>/system/tms/ratio/audit/nopass.shtml',
						type:'post',
						data:{ratioIds:array},
						dataType:'json',
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