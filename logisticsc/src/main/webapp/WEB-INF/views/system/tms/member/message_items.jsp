<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>短信设置管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>短信设置管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
			        	<label>选择区域：</label>
				      	<select id="province" class="input-small" onchange="loadXzqhInfo(this.value, 2)"></select>&nbsp;&nbsp;
				      	<select id="city" class="input-small" onchange="loadXzqhInfo(this.value, 3)"></select>&nbsp;&nbsp;
				       	<select id="county" class="input-small"></select>
				       	<input id="condition" type="text" placeholder="网点">
			        	<button type="button" class="button button-normal" onclick="search()">查询</button>
			        </li>
	      		</ul>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		$(function(){
			loadXzqhInfo('100000', 1);
		});
		
		//查询
		function search(){
			var params={
				'province' : $("#province").val(),
				'city' : $("#city").val(),
				'county' : $("#county").val(),
				'condition' : $("#condition").val()
			}
			store.load(params);
		}
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
					function(Grid,Data,Toolbar,Format,Select){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '网点', dataIndex : 'outletsName'},
				{title : '名称', dataIndex : 'name'},
				{title : '触发事件',dataIndex :'triggerEvent'},
				{title : '内容',dataIndex :'content'},
				{title : '说明', dataIndex : 'remark'}
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/shortmessage/getshortmessageofplatform.shtml',
				autoLoad:true,
				pageSize:10,
				proxy:{ method:'post'}
			});

			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection],
			});
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
		});	
		
		//加载行政区划信息
		function loadXzqhInfo(pid, num){
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/xzqh/getxzqhinfo.shtml',
				data : { 'pid' : pid },
				success : function(result){
					var html = '<option value="">请选择</option>'
					for(var i = 0; i < result.length; i++){
						html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
					}
					if(num == 1){
						$('#province').html(html);
					}else if(num == 2){
						$('#city').html(html);
					}else if(num == 3){
						$('#county').html(html);
					}
				}
			});
		}
	</script>
</body>
</html>