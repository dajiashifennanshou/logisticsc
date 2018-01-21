
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>车辆管理</title>
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
           	<h2>车辆管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="车牌号">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
       	<input id="outlets_id" type="hidden" value="${sessionScope.tms_user_session.outletsId }"/>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params = {
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/overlay'],
					function(Grid,Data,Toolbar,Overlay){
			var Grid = Grid,
				Store = Data.Store,
				Format=Grid.Format,
				items = [];
				columns = [
					{title : '所属网点',elCls:'center', renderer:function(val,obj){
						return obj.outletsInfo.name;
					}},
					{title : '车牌号',elCls:'center',  dataIndex : 'plateNumber'},
					{title : '车型', elCls:'center', renderer:function(val,obj){
						return obj.vehicleTypeVal;
					}},
					{title : '车长', elCls:'center', renderer:function(val,obj){
						return obj.vehicleLongVal;
					}},
					{title : '核载重量',elCls:'center', dataIndex :'vehicleWeight',},
					{title : '核载体积',elCls:'center', dataIndex :'vehicleVolume'},
					{title : '注册时间',elCls:'center', dataIndex :'createTime',renderer:Format.datetimeRenderer}
				];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/vehicle/search.shtml',
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
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>添加',
			           		handler: addVehicle
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>编辑',
			           		handler: editVehicle
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>删除',
			           		handler: deleteVehicle
			          	}
		          	],
				}
				
			});
			function addVehicle(){
				$.ajax({
					url:"${configProps['project']}/tms/sys/isOutlets.shtml",
					type:'post',
					success:function(result){
						if(result.result){
							window.location.href="${configProps['project']}/tms/vehicle/insert.shtml";
						}else{
							BUI.Message.Alert(result.msg,'warning');
						}
					},
					error:function(result){
						BUI.Message.Alert("网络异常",'error');
					}
				})
			}
			function editVehicle(){
				$.ajax({
					url:"${configProps['project']}/tms/sys/isOutlets.shtml",
					type:'post',
					success:function(result){
						if(result.result){
							var selects = grid.getSelection();
							if(selects.length==1){
								window.location.href="${configProps['project']}/tms/vehicle/update.shtml?vehicleId="+selects[0].id;
							}else{
								BUI.Message.Alert('请选择一条记录',function(){
			    				},'warning');
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
			function deleteVehicle(){
		        var selects = grid.getSelection();
		        var len = selects.length;
		        if(len>0){
		        	var data = [];
		        	for(var i=0;i<len;i++){
		        		data.push(selects[i].id);
		        	}
		        	BUI.Message.Confirm("确定要删除？",function(){
		        		$.ajax({
			        		url:'<%=request.getContextPath()%>/tms/vehicle/delete.shtml',
			        		data:{vehicleIds:data},
			        		type:'post',
			        		dataType:'json',
			        		success:function(data){
			        			if(data.result==true){
			        				BUI.Message.Alert('车辆已成功被删除',function(){
			        					store.load();
			        				},'success');
			        			}
			        		}
			        	})
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
	</script>
</body>
</html>