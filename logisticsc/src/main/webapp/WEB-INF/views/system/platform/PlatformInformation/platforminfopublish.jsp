<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统信息发布</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/logisticsc/resources/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="/logisticsc/resources/newbui/css/bs3/bui-min.css" rel="stylesheet">
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
           	<h2>货运交易系统信息发布</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        	起始时间：<input id="start_time" name="startTime" type="text" class="calendar"/>-<input id="end_time" name="endTime" type="text" class="calendar"/>
        		<input id="condition" name="condition" type="text" placeholder="主题">
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
   <form id="#J_Form" class="form-horizontal" action="" style="margin-left: 15px;">
    <div class="row">
      <div class="control-group span8">
         <span>消息标题：</span>
    	<input name="title"  id="title" type="text"  style="width:500px;"  />
     </div>
      </div>
    <div class="row">
      <div class="control-group span8">
         <span>消息内容：</span>
    	<textarea name="content" id="content" style="width:500px; height: 220px;" ></textarea>
      </div>
      </div>
  </form>
    </div>
	<script type="text/javascript" src="/logisticsc/resources/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/logisticsc/resources/newbui/js/bui.js"></script>
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
				{title : '主题', dataIndex : 'title'},
				{title : '内容', dataIndex : 'content'},
				{title : '信息类型',dataIndex :'type',renderer:function(val){
					if(val==1){
						return '<sapn>系统</sapn>';
					}else if(val==2){
						return '<sapn>专线</sapn>';
					}
				}},
				{title : '时间', dataIndex : 'createTime',renderer:BUI.Grid.Format.datetimeRenderer},
			];
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'add_edit_dia', //设置隐藏的Dialog内容
		          editor:{
		        	  title:'信息发布',
		        	  width:600,
		        	  success:function(){
		        		  var editor = this;
		        		  	form = editor.get("form");
		        		  form.ajaxSubmit({
		        			  	url:'<%=request.getContextPath()%>/system/yypt/platinfo/platinsert.shtml',
		        			  	type:'post',
		        			  	dataType:'json',
		        				success:function(data){
		        					if(data.result == true){
		        						BUI.Message.Alert('发布成功！！！','success');
		        						editor.close();
		        						store.load();
		        					}
					        	},
					        	error:function(){
					        		BUI.Message.Alert('发布失败！！！','error');
					        		  editor.close();
					        	}
		          			});
		        	  }
		          }
		    });
			store = new Store({
				url: '<%=request.getContextPath()%>/system/yypt/platinfo/listdata.shtml',
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
	                  	text : '<i class="icon-plus"></i>发布',
	                  	listeners : {
	                    	"click":publish
	                  	}
	                },{
	                  	btnCls : 'button button-small',
	                  	text : '<i class="icon-plus"></i>删除',
	                  	listeners : {
	                    	'click':deletePub
	                  	}
	                }]
				}
				
			});
			function publish(){
				editing.add();
			}
			function deletePub(){
				var selects = grid.getSelection();
				var length = selects.length;
				if(length>0){
					var array = [];
					for(var i=0;i<length;i++){
						array.push(selects[i].id);
					}
					$.ajax({
						url:'<%=request.getContextPath()%>/system/tms/publish/delete.shtml',
						type:'post',
						data:{publishIds:array},
						dataType:'json',
						success:function(data){
							if(data.result == true){
								BUI.Message.Alert("操作成功");
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