<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客服中心</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>问题反馈</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	            <label>起始时间：</label><input id="start_time" name="startTime" type="text" class="calendar" placeholder="起始时间"><label>至</label><input id="end_time" name="endTime" type="text" class="calendar" placeholder="结束时间">
		        <button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="render_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	</div>
       	</div>
    </div>
    
    <!-- 默认隐藏 -->
    <div id="feedback_dia" class="hide">
    	<form id="feedback_form" action=""  class="form-horizontal">
    		<div class="control-group">
    			<div class="control-label"><s>*</s>主题：</div>
    			<div class="controls control-row-auto">
    				<input name="title" type="text" class="input-large" data-rules="{required : true}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label"><s>*</s>内容：</div>
    			<div class="controls control-row-auto">
    				<textarea name="content" id="" class="control-row4 input-large" data-rules="{required : true}"></textarea>
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
				condition:$("#condition").val()
			}
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format'],function(Grid,Data,Toolbar,Format){
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
			];
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'feedback_dia', //设置隐藏的Dialog内容
		          editor:{
		        	  title:'信息反馈',
		        	  width:600,
		        	  success:function(){
		        		  var editor = this,
		        			form = editor.get("form");
		        		    form.valid();
		        		    if(form.isValid()){
		        		    	form.ajaxSubmit({
				        			  url:'<%=request.getContextPath()%>/tms/feedback/insert.shtml',
				        			  type:'post',
				        			  success:function(data){
				        					if(data.result){
				        						BUI.Message.Alert("信息提交成功",function(){
				        							editor.close();
				        							store.load();
				        						},"success")
				        					}
							        	},
							        	error:function(){
							        		  alert("系统错误");
							        		  editor.close();
							        	}
				        		  })
		        		    }
		        	  }
		          }
		          });
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/feedback/search.shtml',
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
				plugins : [editing],
				tbar:{ //添加、删除
		         	items : [{
		          		btnCls : 'button button-normal',
		         		text : '<i class="icon-plus"></i>反馈',
		           		listeners : {
		                	'click' : addFeedback
		                }
		          	}],
				}
				
			});
			function addFeedback(){
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/sys/isOutlets.shtml',
					type:'post',
					success:function(result){
						if(result.result){
							editing.add();
						}else{
							BUI.Message.Alert(result.msg,"warning");
						}
					},
					error:function(){
						BUI.Message.Alert("网络错误","error");
					}
				})
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