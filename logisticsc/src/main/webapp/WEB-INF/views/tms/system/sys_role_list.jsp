<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理</title>
<style type="text/css">

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<body>
	<div class="demo-content">		
		<div id="grid">
		</div>
    </div>
	<script type="text/javascript">
	
		var store;
		
		BUI.use(['bui/grid','bui/data'],function (Grid,Data) {
			var Store = Data.Store;
			
			var columns = [
				{title : '角色名称',dataIndex :'rolename', width:300},
				{title : '拼音名称',dataIndex :'pinyinname', width:300},
				{title : '状态',dataIndex :'rolesStateStr', width:150}, 
				{title : '操作',dataIndex :'rolestate', width:150,renderer : function(value,obj){
					var edit = '<a  href="javascript:void(0);" onclick="editSysRole('+obj.id+')" class="grid-command btn-del"><i class="icon icon-yellow icon-pencil"></i> 编辑</a>'
					var update;
					var o = JSON.stringify(obj);
					
					if(value==1){
						update  = "<a  href='javascript:void(0);' onclick='updateSysRole("+o+")' class='grid-command btn-del'><i class='icon icon-remove'></i> 停用</a>";
					}else{
						update  = "<a  href='javascript:void(0);' onclick='updateSysRole("+o+")' class='grid-command btn-del'><i class='icon icon-ok'></i> 启用</a>";
					}
					return edit + update;
				}}
			];

			store = new Store({
				url : "${configProps['project']}/sysRole/sys_role_list.shtml",
				autoLoad:true, //自动加载数据
			 	pageSize:100	// 配置分页数目
			});
			
			
			
			var grid = new Grid.Grid({
				render:'#grid',
				//width:'100%',//如果表格使用百分比，这个属性一定要设置
				columns : columns,
				idField : 'id',
				forceFit: true,
				//plugins : [Grid.Plugins.CheckSelection],
				emptyDataTpl : '<div class="centered"><h2>查询的数据不存在</h2></div>',
				tbar:{ //添加、删除
	                items : [{
	                  btnCls : 'button button-small',
	                  text : '<i class="icon-plus"></i>添加',
	                  listeners : {
	                    'click' : addFunction
	                  }
	                }/* ,
	                {
	                  btnCls : 'button button-small',
	                  text : '<i class="icon-remove"></i>删除',
	                  listeners : {
	                    'click' : delFunction
	                  }
	                }*/] 
	            },
				store : store
			 });	  
			function addFunction(){
				window.location.href="${configProps['project']}/system/pageJump.shtml?url=/system/sys_role_add"
			}
	        grid.render()
		});
		
		function editSysRole(id){
			window.location.href="${configProps['project']}/sysRole/editSysRoleById/"+id+".shtml"
			
		}
		
		function updateSysRole(sysRole){
			$.ajax({
				url:"${configProps['project']}/sysRole/updateSysRoleState.shtml",
                type : 'POST',
                cache : false,
                data : {'id':sysRole.id,'rolestate':sysRole.rolestate},
                dataType : 'json',
                //contentType : "application/json;charset=UTF-8",
                success:function(data){
                	if (data.result) {
                    	BUI.Message.Alert('修改成功','success');
                    	store.load();
                    } else {
                    	BUI.Message.Alert('修改失败','error');
                    }
                }
            });
		}
    </script>
</body>