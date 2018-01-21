<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>保险保单管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body, #hidden_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>保险保单管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        
        		<input id="condition" name="condition" type="text" placeholder="保险公司、保险人电话、被保险人证件号">
	        	<button type="button" class="button button-normal" onclick="search()">查询</button>
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
			var params = {
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Overlay){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '投保时间',elCls:'center',width:170,dataIndex :'insCreateTime'},
				{title : '账户',elCls:'center',width:170,dataIndex :'insCreateTime'},
				{title : '订单号',elCls:'center',width:170,dataIndex :'insOrderNum'},
				{title : '内部投保单号',elCls:'center',width:170,dataIndex :'insSysBaodan'},
				{title : '正式保单号',elCls:'center',width:170,dataIndex :'insLastBaodan'},
				{title : '保险公司',elCls:'center',dataIndex :'insComTag',renderer:function(val){
					if(val=="taipingyang"){
						return "太平洋保险";
					}else if(val == "yangguang"){
						return "阳光保险";
					}else if(val == "renbao"){
						return "中国人保财险";
					}else if(val == "pingan"){
						return "平安保险";
					}
				}},
				{title : '保险类型',elCls:'center',dataIndex :'insType',renderer:function(val){
					if(val == 'jb'){
						return "基本险";
					}else if(val == 'xh'){
						return "鲜活险";
					}else if(val == 'ys'){
						return "易碎险";
					}else if(val == 'zh'){
						return "综合险";
					}else if(val == 'lc'){
						return "冷藏险";
					}else if(val == 'sz'){
						return "失踪险";
					}
				}},
				{title : '保险人姓名',elCls:'center',dataIndex :'insTrueName'},
				{title : '保险人电话',elCls:'center',dataIndex :'insTel'},
				{title : '保险人证件号', elCls:'center',width:170,dataIndex :'insCardNum'},
				{title : '保险金额',elCls:'center',width:170,dataIndex :'insJine'},
				{title : '保险费用',elCls:'center',width:170,dataIndex :'insFee'},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/system/merchant/searchIns.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
			  	/* forceFit:true, */
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{ //添加、删除
		         	items : [{
			          		btnCls : 'button button-normal',
			         		text : '查看详情',
			           		handler : getItems
			          	}
		          	],
				}
			});
			//查看详情
			function getItems(){
				var selects = grid.getSelection();
				if(selects.length == 1){
					window.location.href="<%=request.getContextPath()%>/system/merchant/getInsItems.shtml?insId="+selects[0].id;
				}else{
					BUI.Message.Alert("请选择一条记录","warning");
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