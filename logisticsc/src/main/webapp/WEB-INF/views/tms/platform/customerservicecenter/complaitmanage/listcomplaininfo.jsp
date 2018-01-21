<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="-1"/>
<title>客服中心</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>投诉管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	            <label>起始时间：</label><input id="start_time" name="startTime" type="text" class="calendar" placeholder="起始时间"><label>至</label><input id="end_time" name="endTime" type="text" class="calendar" placeholder="结束时间">
	            <input id="condition" name="conditon" type="text" class="control-text" placeholder="订单号">
		        <button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="complain_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	</div>
       	</div>
    </div>
    <!-- 投诉回复  默认隐藏 -->
    <div id="cmpl_rply_dia" class="hide">
    	<form id="evl_rply_form" action=""  class="form-horizontal">
    		<div class="control-group">
    			<div class="control-label">投诉内容：</div>
    			<div class="controls control-row-auto">
    				<textarea name="complaintContent" class="control-row2 input-large" readonly="readonly"></textarea>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">回复：</div>
    			<div class="controls control-row-auto">
    				<textarea id="handle_content" name="handleContent" id="" class="control-row4 input-large"></textarea>
    			</div>
    		</div>
    		<div>
    			<input id="id" type="hidden" name="id">
    		</div>
    	</form>
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
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });

		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format'],function(Grid,Data,Toolbar,Format){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '订单号',elCls:'center',dataIndex :'orderNumber'},
				{title : '线路',elCls:'center',dataIndex :'line'},
				{title : '投诉人',elCls:'center', dataIndex : 'loginName'},
			 	{title : '投诉内容',elCls:'center',dataIndex : 'complaintContent'},
			 	{title : '投诉时间',elCls:'center',dataIndex : 'complaintTime',renderer:BUI.Grid.Format.dateRenderer},
			 	{title : '处理人',elCls:'center', dataIndex : 'handlePeople'},
			 	{title : '处理内容',elCls:'center',dataIndex : 'handleContent'},
			 	{title : '处理时间',elCls:'center',dataIndex : 'handleTime',renderer:BUI.Grid.Format.dateRenderer},
			 	{title : '客户满意度',elCls:'center',dataIndex : 'handleSatisfiedDegree',renderer:function(val){
			 		switch(val){
			 			case 1: return "非常满意";break;
			 			case 2: return "满意";break;
			 			case 3: return "一般";break;
			 			case 4: return "不满意";break;
			 			default: return "非常不满意";break;
			 		}
			 	}},
				{title : '操作',elCls:'center',dataIndex:'state',renderer : function(stt){
					if(stt==1){
						return '<span class="grid-command btn-edit">回复</span>'
					}else{
						return '<span>已回复</span>'
					}
					
				}}
			];		
			 editing = new Grid.Plugins.DialogEditing({
		          contentId : 'cmpl_rply_dia', //设置隐藏的Dialog内容
		          triggerCls : 'btn-edit', //触发显示Dialog的样式
		          editor:{
		        	  title:'投诉回复',
		        	  width:600,
		        	  success:function(){
		        		  var editor = this,
		        		  	record = editing.get("form").get("record");
		        		  var data = {
		        				complaintId:record.id,
		        		  		handleContent:$("#handle_content").val()
		        		  };
		        		  
		        		  $.ajax({
		        			  	url:'<%=request.getContextPath()%>/tms/complain/handle.shtml',
		        			  	data:data,
		        			  	type:'post',
		        			  	dataType:'json',
		        				success:function(data){
		        					if(data.result == true){
		        						BUI.Message.Alert("处理成功",function(){
		        							editor.close();
			        						store.load();
		        						},"success");
		        					}
					        	},
					        	error:function(){
					        		BUI.Message.Alert("网络异常","warning");
					        	}
		          			});
		        	  }
		          }
		          });
		        
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/complain/search.shtml',
				autoLoad:true,
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#complain_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
			 	plugins : [editing],// 插件形式引入多选表格
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
	</script>
</body>
</html>