<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>预存费管理</title>
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
           	<h2>预存费管理</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<!-- 预存费添加    默认隐藏 -->
	<div id="content_add_dpstrt" class="hide">
		<form id="dpstrt_form" class="form-horizontal">
			<div class="row">
          		<div class="control-group span8">
            		<label class="control-label">线路：</label>
            		<div class="controls">
              			<select id="line_select" name="lineId" data-rules="{required : true}">
              				<option value="">请选择</option>
              				<c:forEach var="lineInfo" items="${lineInfos }">
              					<option value="${lineInfo.id }">${lineInfo.lineName }</option>
              				</c:forEach>
              			</select>
            		</div>
         		 </div>
         	</div>
         	<div class="row">
          		<div class="control-group span8">
            		<label class="control-label">预存费比例：</label>
            		<div class="bui-form-group controls">
              			<span>1&nbsp;&nbsp;:&nbsp;&nbsp;</span><input name="depositRatio" type="number" class="input-small" data-rules="{required : true}" data-messages="{required:'输入有效数字'}"/>
            		</div>
         		 </div>
         	</div>
         	<div class="row">
          		<div class="control-group span12">
		         	<label class="control-label">有效期：</label>
		         	<div class="controls bui-form-group" data-rules="{dateRange : true}">
		              <input id="start_time" name="startTime" data-tip="{text : '起始日期'}" data-rules="{required:true}" data-messages="{required:'起始日期不能为空'}" class="input-small calendar" type="text"><label>&nbsp;-&nbsp;</label>
		              <input id="end_time" name="endTime" data-tip="{text : '结束日期'}" data-rules="{required:true}" data-messages="{required:'结束日期不能为空'}" class="input-small calendar" type="text">
		            </div>
		        </div>
		    </div>
         	<!-- <div class="row">
          		<div class="control-group span8">
            		<label class="control-label">起始时间：</label>
            		<div class="controls">
              			<input name="startTime" id="start_time" type="text" class="calendar" data-rules="{required : true}">
            		</div>
         		 </div>
         	</div>
         	<div class="row">
          		<div class="control-group span8">
            		<label class="control-label">结束时间：</label>
            		<div class="controls">
              			<input name="endTime" id="end_time" type="text" class="calendar" data-rules="{required : true}">
            		</div>
         		 </div>
         	</div> -->
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	//表格渲染
	BUI.use(['bui/grid','bui/data','bui/toolbar','bui/tree','bui/overlay'],
				function(Grid,Data,Toolbar,Tree,Overlay){
		var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '所属网点',elCls:'center',renderer:function(val,obj){
					return obj.outletsInfo.name;
				}},
	           	{title : '线路',elCls:'center',renderer:function(val,row){
	           		var body = '从：'+row.lineInfo.startOutletsObj.provinceValue+'&nbsp;&nbsp;&nbsp;&nbsp;'+row.lineInfo.startOutletsObj.cityValue+'&nbsp;&nbsp;&nbsp;&nbsp;'+row.lineInfo.startOutletsObj.countyValue+'</br>到：'
	           				+row.lineInfo.endOutletsObj.provinceValue+'&nbsp;&nbsp;&nbsp;&nbsp;'+row.lineInfo.endOutletsObj.cityValue+'&nbsp;&nbsp;&nbsp;&nbsp;'+row.lineInfo.endOutletsObj.countyValue;
	           		return body;
	           	}},
				{title : '预存费比例',elCls:'center', dataIndex : 'depositRatio',renderer:function(val){
					return "1&nbsp;&nbsp;:&nbsp;&nbsp;"+val;
				}},
				{title : '开始时间',elCls:'center',dataIndex :'startTime',renderer:Grid.Format.dateRenderer},
				{title : '结束时间',elCls:'center', dataIndex : 'endTime',renderer:Grid.Format.dateRenderer},
				{title : '创建时间',elCls:'center', dataIndex : 'createTime',renderer:Grid.Format.dateRenderer},
				{title : '状态',elCls:'center', dataIndex : 'status',renderer:function(val){
					if(val==1){
						return '未发布';
					}else if(val==2){
						return '审核中';
					}else if(val==3){
						return '已发布';
					}else if(val==4){
						return '发布失败';
					}else if(val==5){
						return '已过期';
					}
				}}
			];
        var editing = new Grid.Plugins.DialogEditing({
          	contentId : 'content_add_dpstrt', //设置隐藏的Dialog内容
          	triggerCls : 'btn-edit', //触发显示Dialog的样式
          	editor: {
        	  	title: '预存费管理',
        	  	width:500,
              	success : function(){
                  	var editor = this,
	    				form = editor.get("form"),
	    		  		editType = editing.get("editType"),
	    		  		data = $("#dpstrt_form").serialize();
	    		  		url = "";
                  	if(editType == 'add'){ //可以根据编辑类型决定url
                    	url = '<%=request.getContextPath()%>/tms/dpstrt/insert.shtml';
                  	}else{
                  		/* data = form + "&"+record.id; */
                    	url = '<%=request.getContextPath()%>/tms/dpstrt/update.shtml';
                    	data += "&id="+editing.get("record").id;
                  	}
                  	form.valid();
                  	if(form.isValid()){
                  		$.ajax({ //表单异步提交
                         	url : url,
                         	type: 'post',
                         	data:data,
                         	success : function(data){
    	                       	if(editType == 'add'){
    	                         		if(data.result){
    	                         			BUI.Message.Alert('信息添加成功',function(){
    	                         				edtor.close();
    			                         		store.load();
    	                         			},'success');
    	                         		}
    	                       	}else{
    	                       		if(data.result){
	                         			BUI.Message.Alert('信息修改成功',function(){
	                         				edtor.close();
			                         		store.load();
	                         			},'success');
	                         		}
    	                       	}
                         	},
                         	error : function(){
                           		alert("系统错误");
                         	}
                        });
                    }
                }
           	}
       	}),
		store = new Store({
			url: '<%=request.getContextPath()%>/tms/dpstrt/search.shtml',
			pageSize:10,
			autoLoad:true,
			proxy:{
				method:'post',
			}
		});
		grid = new Grid.Grid({
		   	render:'#render_grid',
		  	autoRender:true, 
			columns : columns,
			forceFit : true,
			store : store,
			loadMask: true,//加载数据时显示屏蔽层
			plugins : [Grid.Plugins.CheckSelection,editing],
			tbar:{ //添加、删除
	         	items : [
	         		{
		          		btnCls : 'button button-normal',
		         		text : '<i class="icon-plus"></i>添加',
		                handler: addDepositRatio
		          	},{
		          		btnCls : 'button button-normal',
		         		text : '<span class="grid-command btn-edit">编辑</span>',
		         		handler: editDepositRatio
		          	},{
		          		btnCls : 'button button-normal',
		         		text : '<i class="icon-plus"></i>删除',
		         		handler: deleteDepositRatio
		          	},{
		          		btnCls : 'button button-normal',
		         		text : '<i class="icon-plus"></i>发布',
		         		handler: publish
		          	}
	          	],
			}
		});
		function addDepositRatio(){
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
					BUI.Message.Alert("网络异常","error");
				}
			})
		}
		function editDepositRatio(){
			$.ajax({
				url:"${configProps['project']}/tms/sys/isOutlets.shtml",
				type:'post',
				success:function(result){
					if(result.result){
						var selects = grid.getSelection();
						if(selects.length==1){
							editing.edit(selects[0]);
						}else{
							BUI.Message.Alert('请选择一条记录','warning');
						}
					}else{
						BUI.Message.Alert(result.msg,'warning');
					}
				},
				error:function(result){
					BUI.Message.Alert("网络异常",'error');
				}
			})
		}
		function deleteDepositRatio(){
			var selects = grid.getSelection();
	        var len = selects.length;
	        if(len>0){
	        	var data = [];
	        	for(var i=0;i<len;i++){
	        		var select = selects[i];
	        		if(select.status!=2&&select.status!=3){
	        			data.push(select.id);
	        		}else{
	        			BUI.Message.Alert('当前记录不能删除','warning');
	        			return false;
	        		}
	        	}
	        	BUI.Message.Confirm("确定要删除该记录？",function(){
	        		$.ajax({
		        		url:'<%=request.getContextPath()%>/tms/dpstrt/delete.shtml',
		        		data:{depositIds:data},
		        		type:'post',
		        		dataType:'json',
		        		success:function(data){
		        			if(data.result){
		        				BUI.Message.Alert('记录已成功被删除',function(){
	                         		store.load();
	                 			},'success');
		        			}
		        		}
		        	})
	        	})
	        }
		}
		function publish(){
			var selects = grid.getSelection(),
				records = store.getResult();
	        var len = selects.length,
	        	lenR = records.length;
	        if(len==1){
	        	var data = [];
        		for(var j=0;j<lenR;j++){
        			record = records[j];
        			if(record.status == 3){
        				BUI.Message.Alert('该线路已发布运存费信息，不能重复发布','warning');
            			return false;
        			}else if(record.status == 2){
        				BUI.Message.Alert('该线路已有预存费信息正在审核，不能重复发布','warning');
            			return false;
        			}
        		}
        		var select = selects[0];
        		if(select.status==2){
        			BUI.Message.Alert('当前记录正在审核','warning');
        			return false;
        		}else if(select.status==3){
        			BUI.Message.Alert('信息已发布，不能重复发布','warning');
        			return false;
        		}else if(select.status==4){
        			BUI.Message.Alert('该信息未能通过审核','warning');
        			return false;
        		}else{
        			data.push(select.id);
        		}
	        	$.ajax({
	        		url:'<%=request.getContextPath()%>/tms/dpstrt/publish.shtml',
	        		data:{depositIds:data},
	        		type:'post',
	        		dataType:'json',
	        		success:function(data){
	        			if(data.result){
	        				BUI.Message.Alert('信息提交成功，请等待系统审核',function(){
                         		store.load();
                 			},'success');
	        			}
	        		}
	        	})
	        }else{
	        	BUI.Message.Alert('请选择一条记录','warning');
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
	BUI.use('bui/calendar',function(Calendar){
        var datepicker = new Calendar.DatePicker({
        	trigger:'.calendar',
        	autoRender : true
    	});
    });
	</script>
</body>
</html>