<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body,#add_edit_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>信息反馈</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        	起始时间：<input id="start_time" name="startTime" type="text" class="calendar"/>-<input id="end_time" name="endTime" type="text" class="calendar"/>
	        	<button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="render_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	</div>
       	</div>
    </div>
    <!-- 默认隐藏 -->
    <div id="add_edit_dia" class="hide">
    	<form class="form-horizontal">
    		<div class="control-group">
    			<div class="control-label">主题：</div>
    			<div class="controls">
    				<input name="title" type="text" style="width:300px" readonly/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label"><s>*</s>回复内容：</div>
    			<div class="controls control-row-auto" >
    				<textarea name="contentReply" id="reply_content" data-rules="{required:true}" style="width:300px;height:150px"></textarea>
    			</div>
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
			}
			store.load(params);
		}
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
					function(Grid,Data,Toolbar,Format,Select){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
					{title : '标题',elCls:'center',dataIndex :'title'},
					{title : '问题内容', elCls:'center',dataIndex : 'content'},
					{title : '回复内容',elCls:'center', dataIndex : 'contentReply'},
					{title : '提问人',elCls:'center',renderer:function(val,obj){
						return obj.user.loginName;
					}},
					{title : '提问时间',elCls:'center',dataIndex : 'createTime',renderer:BUI.Grid.Format.dateRenderer},
					{title : '回复人',elCls:'center',renderer:function(val,obj){
						if(obj.replyUser){
							return obj.replyUser.username;
						}else{
							return "";
						}
						
					}},
					{title : '回复时间',elCls:'center',dataIndex : 'replyTime',renderer:BUI.Grid.Format.dateRenderer},
			];
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'add_edit_dia', //设置隐藏的Dialog内容
		          editor:{
		        	  title:'信息回复',
		        	  width:600,
		        	  height:450,
		        	  success:function(){
		        		  var editor = this,
		        		    form = editor.get("form"),
		        		  	record = editing.get("record"),
		        		    contentReply = $("#reply_content").val();
		        		  form.valid();
		        		  if(form.isValid()){
		        			  $.ajax({
			        			  	url:'<%=request.getContextPath()%>/system/feedback/update.shtml',
			        			  	type:'post',
			        			  	data:{id:record.id,contentReply:contentReply},
			        			  	dataType:'json',
			        				success:function(data){
			        					if(data.result){
			        						BUI.Message.Alert("信息回复成功",function(){
			        							editor.close();
				        						store.load();
			        						},"success");
			        					}else{
			        						BUI.Message.Alert(data.msg,"warning");
			        					}
						        	},
						        	error:function(){
						        		BUI.Message.Alert("网络异常","error");
						        	}
			          			});
		        		  }
		        		  
		        	  }
		          }
		    });
			store = new Store({
				url: '<%=request.getContextPath()%>/system/feedback/search.shtml',
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
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{
					items : [{
	                  	btnCls : 'button button-small',
	                  	text : '<i class="icon-plus"></i>回复',
	                  	listeners : {
	                    	"click":reply
	                  	}
	                }]
				}
				
			});
			function reply(){
				var selects = grid.getSelection();
				var len = selects.length;
				if(len==1){
					editing.edit(selects[0]);
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