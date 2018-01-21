<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统设置</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>用户管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="用户名，姓名">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    
    <!-- 默认隐藏 -->
    <div id="hidden_dia" class="hide">
	    <form id="add_edit_form" class="form-horizontal">
          	<div class="control-group">
            	<label class="control-label">用户名：</label>
            	<div class="controls">
              		<input id="true_name" name="trueName" type="text" class="input-normal control-text">
            	</div>
         	</div>
         	<div class="control-group">
		        <label class="control-label">角色：</label>
		        <div id="select_role" class="controls">
					 <input type="hidden" id="hide" value="" name="roleIds">
		        </div>
      		</div>
      		<div class="control-group">
            	<label class="control-label">输入密码：</label>
            	<div class="controls">
              		<input id="password" name="password" type="text" class="input-normal control-text">
            	</div>
         	</div>
         	<div class="control-group">
            	<label class="control-label">确认密码：</label>
            	<div class="controls">
              		<input id="password" name="repPassword" type="text" class="input-normal control-text">
            	</div>
         	</div>
          	<div class="control-group">
            	<label class="control-label">备注：</label>
            	<div class="controls  control-row-auto">
		          	<textarea name="" id="" class="control-row4 input-large"></textarea>
		        </div>
         	</div>
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			renderItems();
		})
		//查询
		function search(){
			var params = {
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/select'],
					function(Grid,Data,Toolbar,Select){
			var Grid = Grid,
				Store = Data.Store,
				items = [];
				columns = [
					{title : '操作',renderer : function(){
					 	return '<span class="grid-command btn-edit"><i class="icon icon-white icon-edit x-icon x-icon-warning"></i>编辑</span>';
					}},
					{title : '用户名', dataIndex : 'loginName'},
					{title : '姓名', dataIndex : 'trueName'},
					{title : '固定电话',dataIndex :'phone',},
					{title : '邮箱',dataIndex :'email'},
				];
			$.ajax({
				url: '<%=request.getContextPath()%>/tms/role/ajax/getrole.shtml',
				type:'post',
				async:false,
				dataType:'json',
				success:function(result){
					if(result.result==true){
						for(var i= 0;i<result.rows.length;i++){
							data = {};
							data.text = result.rows[i].roleName;
							data.value = result.rows[i].id;
							items.push(data);
						}
					}
				},
			})
	        var select = new Select.Select({
	          	render:'#select_role',
	          	valueField:'#hide',
	          	multipleSelect:true,
	          	items:items
	        }).render();
			var editing = new Grid.Plugins.DialogEditing({
		          contentId : 'hidden_dia', //设置隐藏的Dialog内容
		          triggerCls : 'btn-edit', //触发显示Dialog的样式
		          editor:{
		        	  title:'用户管理',
		        	  width:600,
		        	  height:500,
		        	  effect:{effect:'fade',duration:400},
		        	  success:function(){
		        			var editor = this,
		        				form = editor.get("form"),
		        		  		editType = editing.get("editType"),
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/tms/user/update.shtml";
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/tms/user/insert.shtml";
		        		  	}
		        			form.ajaxSubmit({
		        			 	url:url,
		        			 	type:'post',
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
				url: '<%=request.getContextPath()%>/tms/user/search.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
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
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		listeners : {
			                	'click' : addObj
			                }
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>删除',
			           		listeners : {
			                	'click' : deleteObj
			                }
			          	}
		          	],
				}
				
			});
			function addObj(){
		        editing.add();
			}
			function deleteObj(){
		        var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng>0){
		        	var data = [];
		        	for(var i=0;i<lng;i++){
		        		data.push(selects[i].id);
		        	}
		        	$.ajax({
		        		url:'<%=request.getContextPath()%>/tms/user/delete.shtml',
		        		data:{userIds:data},
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
		//异步渲染
		function renderItems(){
			$.ajax({
				url: '<%=request.getContextPath()%>/tms/user/render.shtml',
				type:'post',
				async:false,
				dataType:'json',
				success:function(result){
					var items = [];
					if(result.result==true){
						var summary = result.summary,
							company = summary.company,
							outletsInfo = summary.outletsInfo,
							outletsInfos = summary.outletsInfos,
							node = $("#outlets_info");
						$("#company_name").val(company.companyName);
						$("#company_code").val(company.companyCode);
						if(outletsInfo){
							node.append("<option value='"+outletsInfo.id+"'>"+outletsInfo.name+"</option>");
						}
						if(!outletsInfos&&outletsInfos.length>0){
							for(var i= 0;i<outletsInfos.length;i++){
								node.append("<option value='"+outletsInfo[i].id+"'>"+outletsInfo[i].name+"</option>");
							}
						}
					}
					return items;
				},
			})
		}
	</script>
</body>
</html>