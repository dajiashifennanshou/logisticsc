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
           	<h2>通讯录</h2>
        </div>
        <div class="panel-body">
	        <form id="search-form" class="well form-inline">
	            <input id="condition" type="text" class="control-text" placeholder="专线名称">
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
				condition:$("#condition").val()
			}
			store.load(params);
		}
		platformOrderList();
		function platformOrderList(){
			BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
					Toolbar, Format) {
				var Grid = Grid;
				var Store = Data.Store;
				var columns = [ 
					{title : '专线名称',elCls:'center',width:200,dataIndex :'companyName'},
					{title : 'qq',elCls:'center',dataIndex :'qq',width:200,renderer:function(val){
						if(val!=''&&val != null){
							return "<div style='position:absolute;left:50px'><a target='_blank' href='http://wpa.qq.com/msgrd?v=3&uin="+val+"&site=qq&menu=yes'>QQ："+val+"</a></div>";
						}else{
							return "<div style='position:absolute;left:50px'><span>QQ：~</span></div>";
						}
					}},
				]
					
				
				store = new Store({
					url : '<%=request.getContextPath()%>/tms/insmsg/searchLineCom.shtml',
					autoLoad : true,
					proxy : { method : 'post' },
					//remoteSort : true,
					pageSize : 10
				});
				grid = new Grid.Grid({
					render : '#evaluation_grid',
					columns : columns,
					store : store,
					//forceFit : true,
				});

				var bar = new Toolbar.NumberPagingBar({
					render : '#pagingbar',
					elCls : 'pagination pull-right',
					store : store
				});
				bar.render();
				grid.render();
			});
		}
	</script> 
</body>
</html>