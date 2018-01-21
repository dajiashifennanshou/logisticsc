<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投保注意事项设置</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
<script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
<script src="http://g.alicdn.com/bui/seajs/2.3.0/sea.js"></script>
<script src="http://g.alicdn.com/bui/bui/1.1.21/config.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/lang/zh_CN.js"></script>
</head>
 <style>
    .bui-tree-list{
      overflow: auto;
    }
  </style>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>投保注意事项设置</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
		//表格渲染
		var dialog;
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Form,Overlay){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '保险公司',elCls:'center',dataIndex :'itemName'},
				{title : '缩写',elCls:'center',dataIndex :'itemAbbr'},
				{title : '上次更新时间',elCls:'center',dataIndex:'udpateDate',renderer:Grid.Format.datetimeRenderer},
				{title : '操作',elCls:'center',dataIndex:'isEnabled',renderer:function(val,obj,index){
					return '<a href="javascript:void(0);" onclick="editTemp('+index+')" class="grid-command btn-edit"><i class="icon icon-yellow icon-pencil"></i> 编辑</a>';
				}},
			];
			
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/config/searchInsNote.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	//autoRender:true, 
			  	forceFit:true,
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
			}).render();
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store,
		    });
		});	
		

		function editTemp(index){
			window.location.href="<%=request.getContextPath()%>/sys/config/toEditInsNote.shtml?itemAbbr="+grid.getItemAt(index).itemAbbr;
		}
	</script>    
</body>
</html>