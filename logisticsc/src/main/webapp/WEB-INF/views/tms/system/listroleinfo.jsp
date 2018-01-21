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
    .bui-stdmod-body #role_name{
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
          		<div class="control-group span8">
            		<label class="control-label">角色名：</label>
            		<div class="controls">
              			<input id="role_name" name="roleName" type="text" class="input-normal control-text">
            		</div>
         		 </div>
         	</div>
         	<div class="row">
          		<div class="control-group span8">
            		<label class="control-label">权限：</label>
            		<div id="role_menu" class="controls" style="height:220px;width:165px">
              			
            		</div>
         		 </div>
         	</div>
         	<div class="row">
          		<div class="control-group span8">
            		<label class="control-label">描述：</label>
            		<div class="controls">
              			<input id="role_desc" name="roleDesc" type="text">
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
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/tree'],
					function(Grid,Data,Toolbar,Tree){
			var Grid = Grid,
				Store = Data.Store,
				resultItems = [],
				columns = [
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
			})
	        //由于这个树，不显示根节点，所以可以不指定根节点
	        var tree = new Tree.TreeList({
	          	render : '#role_menu',
	          	checkType: 'all', //checkType:勾选模式，提供了4中，all,onlyLeaf,none,custom
	          	showLine : true, //显示连接线
	          	nodes : resultItems,
	        }).render();
			var editing = new Grid.Plugins.DialogEditing({
		          contentId : 'feedback_dia', //设置隐藏的Dialog内容
		          triggerCls : 'btn-edit', //触发显示Dialog的样式
		          editor:{
		        	  title:'网点管理',
		        	  width:600,
		        	  height:500,
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
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/tms/role/update.shtml";
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/tms/role/insert.shtml";
		        		  	}
		        			$.ajax({
		        			 	url:url,
		        			 	type:'post',
								data:data,
		        				dataType:'json',
		        			  	success:function(data){
		        					if(data.result == true){
		        						alert("信息已成功提交");
		        						editor.close();
		        						store.load();
		        					}
					        	},
					        	error:function(){
					        		  alert("系统错误");
					        		  editor.close();
					        	}
	        		  		})
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
		        			if(data.result==true){
		        				alert("删除成功");
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
	</script>
</body>
</html>