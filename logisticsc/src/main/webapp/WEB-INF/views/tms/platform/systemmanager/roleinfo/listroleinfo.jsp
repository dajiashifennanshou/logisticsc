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
    .bui-stdmod-body,#role_menu,#role_menu_items{
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
			<!-- <div class="row">
          		<div class="control-group span8">
            		<label class="control-label">角色名：</label>
            		<div class="controls">
              			<input id="role_name" name="roleName" type="text" class="input-normal control-text">
            		</div>
         		 </div>
         	</div> -->
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
            		<label class="control-label"><s>*</s>权限：</label>
            		<div class="controls" style="height:260px;width:323px">
              			<div class="panel panel-head-borded panel-small" style="width:100%;height:100%">
						    <div class="panel-header">用户权限</div>
						    <div id="role_menu" style="width:100%;height:240px">
						    
						    </div>
						</div>
            		</div>
         		 </div>
         	</div>
         	<div class="row">
         		<div class="control-group">
            		<label class="control-label">&nbsp;</label>
            		<div class="controls">
              			&nbsp;
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
         	<!-- <div class="row">
          		<div class="control-group span8">
            		<label class="control-label">描述：</label>
            		<div class="controls">
              			<input id="role_desc" name="roleDesc" type="text">
            		</div>
         		 </div>
         	</div> -->
		</form>
	</div>
	<div id="view_perms" class="hide">
         	<!-- <div class="row">
          		<div class="control-group">
            		<label class="control-label"><s>*</s>权限：</label>
            		<div class="controls" style="height:260px;width:323px">
              			<div class="panel panel-head-borded panel-small" style="width:100%;height:100%">
						    <div class="panel-header">用户权限</div> -->
						    <div id="role_menu_itmes" style="width:100%;height:240px">
						    
						    </div>
						<!-- </div>
            		</div>
         		 </div>
         	</div> -->
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
		
		//表格渲染
		var treeStorePerms,treePerms;
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/tree','bui/overlay'],
					function(Grid,Data,Toolbar,Tree,Overlay){
			message = BUI.Message;
			var Grid = Grid,
				Store = Data.Store,
				//resultItems = [],
				
				columns = [
					{title : '创建时间', elCls:'center',dataIndex : 'createTime',renderer:Grid.Format.datetimeRenderer},
					{title : '角色名', elCls:'center',dataIndex : 'roleName'},
					{title : '功能描述',elCls:'center',dataIndex :'roleDesc'},
					{title : '角色类别', elCls:'center',dataIndex : 'ownerType',renderer:function(val){
						if(val==2||val==3||val==4||val==6){
							return "系统默认";
						}else{
							return "用户自定义";
						}
					}},
					{title : '状态',elCls:'center',dataIndex :'roleStatus',renderer:function(val){
						if(val==0){
							return "已禁用";
						}else{
							return "已启用";
						}
					}},
					{title : '操作',elCls:'center',dataIndex :'roleStatus', width:150,renderer : function(value,obj){
						var edit;
						var update;
						var viewPerms;
						var o = JSON.stringify(obj);
						viewPerms = "<span class='grid-command' onclick='viewPerms("+obj.id+")'><i class='icon icon-list-alt'></i> 查看权限</span>";
						if(obj.ownerType == 5){
							edit = '<a  href="javascript:void(0);" class="grid-command btn-edit"><i class="icon icon-yellow icon-pencil"></i> 编辑</a>';
						}else{
							edit = '<span class="grid-command" style="color:gray"><i class="icon icon-yellow icon-pencil"></i> 编辑</span>';
						}
						if(value==1){
							if(obj.ownerType == 5){
								update  = "<a  href='javascript:void(0);' onclick='forbidRole("+obj.id+")' class='grid-command btn-del'><i class='icon icon-remove'></i> 停用</a>";
							}else{
								update  = "<span class='grid-command' style='color:gray'><i class='icon icon-remove'></i> 停用</span>";
							}
						}else{
							if(obj.ownerType == 5){
								update  = "<a  href='javascript:void(0);' onclick='enableRole("+obj.id+")' class='grid-command btn-del'><i class='icon icon-ok'></i> 启用</a>";
							}else{
								update  = "<span class='grid-command' style='color:gray'><i class='grid-command icon icon-ok'></i> 启用</span>";
							}
							
						}
						return edit + update + viewPerms;
					}}
				];
				/* columns = [
		           	{title : '操作',renderer : function(){
		             	return '<span class="grid-command btn-edit"><i class="icon icon-white icon-edit x-icon x-icon-warning"></i>编辑</span>';
		            }},
					{title : '角色名', dataIndex : 'roleName'},
					{title : '权限',dataIndex :'menus',renderer:function(menus){
						var str = "",
							len = menus.length;
						for(var i=0;i<len;i++){
							str += menus[i].menuName+'</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[';
							var subMenus = menus[i].subMenus,
								subLen = subMenus.length;
							for(var j=0;j<subLen;j++){
								str += "&nbsp;&nbsp;"+subMenus[j].menuName+"&nbsp;&nbsp;";
							}
							str += ']</br>';
						}
						return str;
					}},
					{title : '描述',dataIndex :'roleDesc'},
				]; 
			
			$.ajax({
				url: '<%=request.getContextPath()%>/tms/role/rolemenu.shtml',
				type:'post',
				async:false,
				dataType:'json',
				success:function(result){
					if(result.result==true){
						var menus = result.rows;
						for(var i = 0;i<menus.length;i++){
				    		var menu = new Object();
			    			menu.id = ''+menus[i].id;
			    			menu.text = ''+menus[i].menuName;
			    			var children = [];
			    			var subMenus = menus[i].subMenus;
			    			for(var j=0;j<subMenus.length;j++){
			    				var subMenu = Object();
			    				subMenu.id = ''+subMenus[j].id;
			    				subMenu.text = ''+subMenus[j].menuName;
			    				children.push(subMenu);
			    			} 
			    			menu.children = children;
				    		resultItems.push(menu);
				    	}
					}
				},
			})*/
			
			var treeStore = new Data.TreeStore({ 
		    	map : {
					menuName : 'text',
		            id : 'id',
		            subMenus : 'children',
		            checked:'checked'
		        },
		        autoLoad : true,
				url : "${configProps['project']}/tms/role/searchRoleMenu.shtml"
		    });
			
	        //由于这个树，不显示根节点，所以可以不指定根节点
	        var tree = new Tree.TreeList({
				//height:'100%',
	          	render : '#role_menu',
	          	checkType: 'all', //checkType:勾选模式，提供了4中，all,onlyLeaf,none,custom
	          	showLine : true, //显示连接线
	          	store : treeStore,
	          	//nodes : resultItems,
	        }).render();
	        
	        treeStorePerms = new Data.TreeStore({ 
		    	map : {
					menuName : 'text',
		            id : 'id',
		            subMenus : 'children',
		            checked:'checked'
		        },
		        autoLoad : true,
				url : "${configProps['project']}/tms/role/searchRoleMenu.shtml"
		    });
	        
	        treePerms = new Tree.TreeList({
	            render : '#role_menu_itmes',
	            store : treeStorePerms,
	            showLine : true //显示连接线
	          });
	        treePerms.render();
	        
			var editing = new Grid.Plugins.DialogEditing({
		          contentId : 'feedback_dia', //设置隐藏的Dialog内容
		          triggerCls : 'btn-edit',
		          //触发显示Dialog的样式
		          editor:{
		        	  title:'网点管理',
		        	  width:600,
		        	  height:550,
		        	  success:function(){
		        		  var nodes=[];
		        		  var checkedNodes = tree.getCheckedNodes();
			  	          	BUI.each(checkedNodes,function(node,index){
			  	          		nodes.push(node.id);
			  	          		if(tree.getCheckedNodes()[index].parent.id){
			  	          			nodes.push(tree.getCheckedNodes()[index].parent.id);
			  	          		}
			  	         	});
		        			var editor = this,
		        				data = {
		        					roleName:$("#role_name").val(),
		        					roleDesc:$("#role_desc").val(),
		        					menuIds:unique(nodes)
		        				},
		        		  		editType = editing.get("editType"),
		        		  		record = editing.get("record"),
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/tms/role/update.shtml";
		        		  		data.id = record.id;
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/tms/role/insert.shtml";
		        		  	}
		        			$.ajax({
		        			 	url:url,
		        			 	type:'post',
								data:data,
		        				dataType:'json',
		        			  	success:function(data){
		        					if(data.result){
		        						BUI.Message.Alert("角色操作成功",function(){
		        							editor.close();
			        						store.load();
		        						},"success");
		        					}
					        	},
					        	error:function(){
					        		BUI.Message.Alert("网络错误","error");
					        	}
	        		  		})
		        	  }
		          }
		   	});
			
			editing.on("cancel",function(){
				editing.get("editor").clearValue();
				$("#role_menu ul li")
				.removeClass("bui-tree-item-checked");
			})
			editing.on("editorshow",function(){
				if(editing.get("editType")=='edit'){
					var record = editing.get("record");
					var params = {
							roleId:record.id
					}
					treeStore.load(params);
				}
			})
			
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
			         		text : '<i class="icon-plus"></i>删除',
			           		listeners : {
			                	'click' : deleteRole
			                }
			          	}
		          	],
				}
				
			});
			function addRole(){
		        editing.add();
			}
			function deleteRole(){
		        var selects = grid.getSelection();
		        var lng = selects.length;
		        message.Confirm("确定要删除",function(){
		        	if(lng>0){
			        	var data = [];
			        	for(var i=0;i<lng;i++){
			        		data.push(selects[i].id);
			        	}
			        	$.ajax({
			        		url:'<%=request.getContextPath()%>/tms/role/delete.shtml',
			        		data:{roleIds:data},
			        		type:'post',
			        		dataType:'json',
			        		success:function(data){
			        			if(data.result){
			        				message.Alert("记录已经成功被删除",function(){
			        					store.load();	
			        				},'success');
			        			}else{
			        				message.Alert(data.msg,'warning');
			        			}
			        		}
			        	})
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
		var dialog;
        BUI.use('bui/overlay',function(Overlay){
            dialog = new Overlay.Dialog({
              title:'角色权限',
              width:500,
              height:400,
              contentId:'view_perms',
           	  buttons:[],
              /* mask:false,
              buttons:[],
              bodyContent:'<p>这是一个非模态窗口,并且不带按钮</p>' */
            });
         
          });
		function unique(arr) {
		    var result = [], hash = {};
		    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
		        if (!hash[elem]) {
		            result.push(elem);
		            hash[elem] = true;
		        }
		    }
		    return result;
		}
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
		
		function viewPerms(roleId){
			treeStorePerms.load({"roleId":roleId},function(){
				dialog.show();
			});
			
		}
	</script>
</body>
</html>