<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司新闻列表</title>
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
           	<h2>公司新闻</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline" enctype="multipart/form-data">
	        	起始时间：<input id="start_time" name="startTime" type="text" class="calendar"/>-<input id="end_time" name="endTime" type="text" class="calendar"/>
        		<input id="condition" name="condition" type="text" placeholder="公司新闻标题">
	        	<button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="companyNews">
	       	</div>
	       	<div id="pagingbar">
	       	</div>
       	</div>
    </div>
    <script type="text/javascript">
    var adForm;
	//查询
	function search(){
		var params={
			startTime:$("#start_time").val(),
			endTime:$("#end_time").val(),
			condition:$("#condition").val(),
			newsType:"0"
		}
		store.load(params);
	}
	
	BUI.use('bui/form',function(Form){
		adForm = new Form.Form({
			srcNode : '#ad_form',
			callback : function(result){
				
		    }
		}).render();
	})
	
	//表格渲染
	BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
				function(Grid,Data,Toolbar,Format,Select){
		var Grid = Grid,
		Store = Data.Store,
		columns = [
			{title : '标题', dataIndex : 'newsTitle'},
			{title : '创建时间', dataIndex : 'newsTime',renderer:BUI.Grid.Format.datetimeRenderer},
			{title : '创建者',dataIndex :'username'}
			
		];
		store = new Store({
			url: '<%=request.getContextPath()%>/system/news/getNews.shtml?newsType='+0,
			autoLoad:true,
			proxy:{
				method:'post',
			},
			pageSize:10,
		});

		grid = new Grid.Grid({
		   	render:'#companyNews',
		  	autoRender:true, 
			columns : columns,
			forceFit : true,
			store : store,
			loadMask: true,//加载数据时显示屏蔽层
			plugins : [Grid.Plugins.CheckSelection],
			tbar:{
				items : [{
                  	btnCls : 'button button-small',
                  	text : '<i class="icon-plus"></i>添加',
                  	listeners : {
                    	"click":addAd
                  	}
                },{
                  	btnCls : 'button button-small',
                  	text : '<i class="icon-plus"></i>编辑',
                  	listeners : {
                    	'click':editAd
                  	}
                },{
                	 btnCls : 'button button-small',
                     text : '<i class="icon-remove"></i>删除',
                     listeners : {
                       'click' : delFunction
                     }
                }
                  	]
			}
			
		});
		function addAd(){
			window.location.href="<%=request.getContextPath()%>/system/news/jumpAddNews.shtml";
		}
		function editAd(){
			var selects = grid.getSelection();
			var len = selects.length;
			if(len==1){
				window.location.href="<%=request.getContextPath()%>/system/news/getNewsId.shtml?id="+selects[0].id;
			}else{
				BUI.Message.Alert("请选择一条记录");
			}
		}
		
		//删除
		function delFunction(){
			 var selections = grid.getSelection();
			 var ids = new Array();
			 var len = selections.length;
			 for (var i = 0; i < len; i++) {
				 ids[i] = selections[i].id
			}
			if(len > 0){
				BUI.Message.Alert("确定删除吗？",function(){
				 $.ajax({
        			  	url:'<%=request.getContextPath()%>/system/news/deleteNews.shtml?',
        			  	type:'post',
        			  	dataType:'json',
        			  	data:{"idss":JSON.stringify(ids)},
        				success:function(data){
        					if(data.result == true){
        						BUI.Message.Alert("删除成功！！！","success");
        						store.load();
        					}
			        	},
			        	error:function(){
			        		BUI.Message.Alert("删除失败！！！","error");
			        	}
          			});
				},"success");
			}else{
				BUI.Message.Alert("请选择一条记录");
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