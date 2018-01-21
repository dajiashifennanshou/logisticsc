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
           	<h2>评论管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search-form" class="well form-inline" data-rules="{dateRange : true}">
	        	<div>
	        		<label class="control-label">评论时间</label>
	        		<div class="controls bui-form-group" data-rules="{dateRange : true}">
			            <input id="start_time" type="text" class="calendar" data-tip="{text : '起始日期'}"><label>至</label>
			            <input type="text" id="end_time" class="calendar" placeholder="结束日期">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            </div>
	        	</div>
	            <input id="condition" type="text" class="control-text" placeholder="订单号">
		        <button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="evaluation_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	
	       	</div>
       	</div>
    </div>
    
    <!-- 评论回复  默认隐藏 -->
    <div id="evl_rply_dia" class="hide">
    	<form id="evl_rply_form" action=""  class="form-horizontal">
    		<div class="control-group">
    			<div class="control-label">评价内容：</div>
    			<div class="controls control-row-auto">
    				<textarea id="eva_cnt" name="evaluateContent" class="control-row2 input-large" disabled></textarea>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">回复：</div>
    			<div class="controls control-row-auto">
    				<textarea id="evl_rply" name="evaluationReply" id="" class="control-row4 input-large"></textarea>
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
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form'],function(Grid,Data,Toolbar,Format,Form){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '订单号',elCls:'center',dataIndex :'orderNumber'},
				{title : '线路',elCls:'center',dataIndex :'line'},
				{title : '下单时间', elCls:'center',dataIndex : 'orderTime',renderer:BUI.Grid.Format.dateRenderer},
				{title : '用户',elCls:'center', dataIndex : 'loginName'},
			 	{title : '评价内容',elCls:'center',dataIndex : 'evaluateContent'},
				{title : '评价时间',elCls:'center',dataIndex : 'evaluateTime',renderer:BUI.Grid.Format.dateRenderer},
				{title : '回复内容',elCls:'center',dataIndex : 'replyContent'},
				{title : '回复时间',elCls:'center',dataIndex : 'replyTime',renderer:BUI.Grid.Format.dateRenderer},
				{title : '操作',elCls:'center',dataIndex: 'state',renderer : function(evl){
					if(evl==1){
						return '<span class="grid-command btn-edit">回复</span>'
					}else{
						return '<span>已处理</span>'
					}
					
				}}
			];
	        editing = new Grid.Plugins.DialogEditing({
	          contentId : 'evl_rply_dia', //设置隐藏的Dialog内容
	          triggerCls : 'btn-edit', //触发显示Dialog的样式
	          editor:{
	        	  title:'评价回复',
	        	  width:600,
	        	  success:function(){
	        		  var editor = this,
	        		  	form = editor.get("form"),
	        		  	record = form.get("record");
	        		  var data = {
	        				evaluateId:record.id,
	        		  		replyContent:$("#evl_rply").val()
	        		  };
	        		  form.valid();
	        		  if(form.isValid()){
	        			  $.ajax({
	        				  	url:'<%=request.getContextPath()%>/tms/evaluation/reply.shtml',
		        			  	data:data,
	        				  	type:'post',
		        				success:function(result){
		        					if(result.result){
		        						BUI.Message.Alert("回复成功",function(){
		        							editor.close();
		        							store.load();
		        						},"success")
		        					}
					        	},
					        	error:function(){
					        		BUI.Message.Alert("网络异常","error")
					        	}
	        			  })
	        		  };
	        	  }
	          }
	          });
	        editing.on("editorshow",function(){
	        	var record = editing.get("record");
	        	$("#eva_cnt").val(record.evaluateContent);
	        })
	        
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/evaluation/search.shtml',
				autoLoad:true,
				pageSize:10,
			});
			var grid = new Grid.Grid({
			   	render:'#evaluation_grid',
			   	autoRender: true,
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
			
			new Form.HForm({
		        srcNode : '#search-form',
		        defaultChildCfg : {
		          validEvent : 'blur' //移除时进行验证
		        }
		      }).render();
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