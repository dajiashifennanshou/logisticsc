<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统-后台管理系统</title>
<style type="text/css">

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<body>
	<div class="demo-content">
    	<div class="row">
    		<div class="span24" style="background-color: transparent; margin-bottom: 10px">
				
			 		<button onclick="onClick()">保存</button>
			 	
			</div>
      		<div class="span13">
        		<div class="panel panel-head-borded panel-small">
          			<!-- <div class="panel-header">用户角色</div> -->
          			<div id="grid">
        			</div>
        		</div>
        	</div>

			<div class="span9">
			  <div class="panel panel-head-borded panel-small">
			    <div class="panel-header">用户权限</div>
			    <div id="tree">
			    
			    </div>
			  </div>
			</div>
    	</div>
    </div>
	<script type="text/javascript">
		var roleId;
		var menus;
		var tree;
		function onClick(){
			if(roleId==null){		
				return;
			}
			menus=[];
			var checkedNodes = tree.getCheckedLeaf();
				BUI.each(checkedNodes,function(node){
					var array = node.path;
					//alert(node.id);
					array.forEach(function(e){
						//alert(e);
						 if(e!=null&&$.inArray(e, menus)==-1){
							menus.push(e);
						  }
						
					});
				});
			
			$.ajax({
                url : "${configProps['project']}/sysRole/updateRoleMenu.shtml",
                type : 'POST',
                cache : false,
                data : {'menus':menus,'roleId':roleId},
                dataType : 'json',
                //contentType : "application/json;charset=UTF-8",
                success:function(data){
                	if (data.result) {
                    	BUI.Message.Alert('修改成功','success');
                    } else {
                    	BUI.Message.Alert('修改失败','error');
                    }
                }
            });
		};
		
		BUI.use(['bui/tree','bui/grid','bui/data'],function (Tree,Grid,Data) {
	
		    var treeStore = new Data.TreeStore({ 
				map : {
					menuname : 'text',
		            id : 'id',
		            subMenus : 'children',
		            checked:'checked'
		        },
		        autoLoad : true,
				url : "${configProps['project']}/sysMenu/getMenusByRole.shtml"
		    });
		      
			//由于这个树，不显示根节点，所以可以不指定根节点
			tree = new Tree.TreeList({
				render : '#tree',
				store : treeStore,
				checkType: 'all',
				showLine : true,
				height:'100%'
			});
			
			
			
			tree.render();  
		      
		      
			var Store = Data.Store;
			var columns = [
				{title : '角色名称',dataIndex :'rolename', width:100}
			];
	 
	 
			var store = new Store({
				url : "${configProps['project']}/sysRole/sys_role_list.shtml",
				autoLoad:true, //自动加载数据
			 	pageSize:2	// 配置分页数目
			});
			var grid = new Grid.Grid({
				render:'#grid',
				width:'100%',//如果表格使用百分比，这个属性一定要设置
				columns : columns,
				idField : 'id',
				store : store,
				plugins : [Grid.Plugins.RadioSelection]	// 插件形式引入单选表格
			 });
	  
	        grid.on('cellclick',function(ev){
	            var sender = ev.record;
	            treeStore.load({roleId:sender.id},function(data){
	            	roleId = sender.id;
	            	//alert(roleId);
	            });
	           // alert(sender.a);
	          });
	        grid.render()
		});
    </script>
</body>