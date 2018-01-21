<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统设置</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body ,#feedback_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>角色管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="角色名">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    <!-- 角色添加    默认隐藏 -->
	<div id="feedback_dia" class="hide">
    	<form id="feedback_form"  class="form-horizontal">
			<div class="row">
          		<div class="control-group">
            		<label class="control-label"><s>*</s>角色名：</label>
            		<div class="controls">
              			<input id="role_name" name="roleName" type="text" class="input-large control-text" data-rules="{required:true}">
            		</div>
         		 </div>
         	</div>
         	<div class="row">
          		<div class="control-group">
            		<label class="control-label">功能描述：</label>
            		<div class="controls  control-row-auto">
	                	<textarea id="role_desc" name="roleDesc" class="control-row4 input-large"></textarea>
	              	</div>
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
				condition:$("#condition").val()
			}
			store.load(params);
		}
		
		var message;
        BUI.use('bui/overlay',function(overlay){
        	message = BUI.Message;
        });
        
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/tree'],
					function(Grid,Data,Toolbar,Tree){
			var Grid = Grid,
				Store = Data.Store,
				columns = [
					{title : '角色名', elCls:'center',dataIndex : 'roleName'},
					{title : '功能描述',elCls:'center',dataIndex :'roleDesc'},
					{title : '状态',elCls:'center',dataIndex :'roleStatus',renderer:function(val){
						if(val==0){
							return "已禁用";
						}else{
							return "已启用";
						}
					}},
					{title : '操作',elCls:'center',dataIndex :'roleStatus', width:150,renderer : function(value,obj){
						var edit = '<a  href="javascript:void(0);" class="grid-command btn-edit"><i class="icon icon-yellow icon-pencil"></i> 编辑</a>'
						var update;
						var o = JSON.stringify(obj);
						
						if(value==1){
							update  = "<a  href='javascript:void(0);' onclick='forbidRole("+obj.id+")' class='grid-command btn-del'><i class='icon icon-remove'></i> 停用</a>";
						}else{
							update  = "<a  href='javascript:void(0);' onclick='enableRole("+obj.id+")' class='grid-command btn-del'><i class='icon icon-ok'></i> 启用</a>";
						}
						return edit + update;
					}}
				];
			var editing = new Grid.Plugins.DialogEditing({
		          contentId : 'feedback_dia', //设置隐藏的Dialog内容
		          triggerCls : 'btn-edit', //触发显示Dialog的样式
		          editor:{
		        	  title:'角色管理',
		        	  width:500,
		        	  success:function(){
		        			var editor = this,
		        		  		editType = editing.get("editType"),
		        		  		form = editor.get("form"),
		        		  		data = {},
		        		  		url = "";
		        			data.roleName = $("#role_name").val();
	        		  		data.roleDesc = $("#role_desc").val();
		        		  	if(editType=="edit"){
		        		  		data.id = editing.get("record").id;
		        		  		url = "<%=request.getContextPath()%>/tms/role/updateRole.shtml";
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/tms/role/insert.shtml";
		        		  	}
		        		  	form.valid();
		        		  	if(form.isValid()){
		        		  		$.ajax({
			        			 	url:url,
			        			 	type:'post',
			        			 	data:data,
			        			  	success:function(data){
			        					if(data.result == true){
			        						message.Alert("角色操作成功",function(){
			        							editor.close();
				        						store.load();
			        						},"success");
			        						
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
				url: '<%=request.getContextPath()%>/tms/role/search.shtml',
				type:'post',
				autoLoad:true,
				pageSize:10,
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
			         		text : '<i class="icon-plus"></i>新增',
			           		listeners : {
			                	'click' : addRole
			                }
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-remove"></i>删除',
			           		listeners : {
			                	'click' : delRole
			                }
			          	}
		          	],
				}
				
			});
			function addRole(){
		        editing.add();
			}
			
			function delRole(){
		        var selects = grid.getSelection();
		        var len = selects.length;
		        if(len>0){
		        	var idList = [];
		        	for(var i = 0;i<len;i++){
		        		idList.push(selects[i].id);
		        	}
		        	$.ajax({
		        		url:'<%=request.getContextPath()%>/tms/role/delete.shtml',
		        		type:'post',
		        		data:{roleIds:idList},
		        		success:function(result){
		        			if(result.result){
		        				message.Alert("角色删除成功",function(){
	        						store.load();
        						},"success");
		        			}else{
		        				message.Alert("角色删除失败，请确认该角色没有分配用户","warning");
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
		
		function forbidRole(id){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/role/forbid.shtml',
				type:'post',
				data:{roleId:id},
				success:function(result){
					if(result.result){
						message.Alert("该角色已禁用",function(){
							store.load();
						},"success");
					}
				}
			})
		}
		
		function enableRole(id){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/role/enable.shtml',
				type:'post',
				data:{roleId:id},
				success:function(result){
					if(result.result){
						message.Alert("该角色已启用",function(){
							store.load();
						},"success");
					}
				}
			})
		}
		
		
	</script>
</body>
</html>