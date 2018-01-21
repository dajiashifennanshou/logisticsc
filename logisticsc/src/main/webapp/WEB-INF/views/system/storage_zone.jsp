<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>云仓库区管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

<script type="text/javascript" src="/logisticsc/Clound/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/logisticsc/Clound/js/Constant.js"></script>
<script type="text/javascript" src="/logisticsc/Clound/js/DateUtil.js"></script>
<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body, #hidden_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2 style="color:green;">云仓库区管理</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
        		支付日期：<input id="startTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar"> 
	          	<input id="deliveryNo" name="condition" type="text" placeholder="输入配送单号">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
	var number = 0;
	
		/* 添加 */
		function addFunction(){
			window.location.href="toAddStorageZone.yc";
		}
		/* 修改 */
		function editFunction(id){
			window.location="toModStorageZone.yc?id="+id;
		}
		/**
		删除*/
		function delFunction(id){
			var i=grid.getSelectionValues();
			alert(i);
			yc_public.confirm({msg:'你确定删除么?',callback:function(){
				yc_public.ajax({url:'delYcStowage.yc',data:{'id':id},success:function(data){
					window.location="toStorageBranch.yc";
				}})
			}})
		}
		//查询
		function search(){
			var params = {
					
					
			};
			var deliveryNo=$("#deliveryNo").val();
			var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			if(deliveryNo){
				params['deliveryNo']=deliveryNo;
			}
			if(startTime){
				params['createTime']=startTime;
			}
			if(endTime){
				params['updateTime']=endTime;
			}
			store.load(params);
		}
		//表格渲染
		var msg;
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Form,Overlay){
			var Grid = Grid,
			Store = Data.Store,
			msg = BUI.Message,
			columns = [
				//{title : '时间',elCls:'center',dataIndex:'deliveryNo',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '网点名称',elCls:'center',width:100,dataIndex :'branchName'},
				{title : '库区名称',elCls:'center',width:100,dataIndex :'zoneName'},
				{title : '库区编号',elCls:'center',width:100,dataIndex :'zoneNo'},
				{title : '库区状态',elCls:'center',width:100,dataIndex:'zoneStatus',renderer:function(val){
					return Constant.StorageZoneStatus[val];
				}},
				{title : '库区面积(M²))',elCls:'center',width:100,dataIndex:'zoneArea'},
				{title : '创建时间',elCls:'center',width:200,dataIndex:'createTime' ,renderer:function(val){
					return DateUtil.RemoveZero(val);
				}},
				{title : '操作',elCls:'center',width:100,dataIndex :'',renderer:function(val,obj){
					var html="";
					html+='<a  href="javascript:void(0);" onclick="editFunction('+obj.id+')" class="grid-command btn-del"><i class="icon icon-yellow icon-pencil"></i> 编辑</a>';
					//html+='<a  href="javascript:void(0);" onclick="delFunction('+obj.id+')" class="grid-command btn-del"><i class="icon icon-yellow icon-pencil"></i> 删除</a>';
					return html;
				}},
			];
			store = new Store({
				url: 'getTmsStorageZoneList.yc',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:7
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
				autoRender:true, 
			  	//forceFit:true,
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{ //添加、删除
	                items : [{
	                  btnCls : 'button button-small',
	                  text : '<i class="icon-plus"></i>添加',
	                  listeners : {
	                    'click' : addFunction
	                  }
	                } ,
	                {
	                  btnCls : 'button button-small',
	                  text : '<i class="icon-remove"></i>删除',
	                  listeners : {
	                    'click' : delFunction
	                  }
	                }] 
	            }
			});
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store,
		    });
		});	
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
		//分账操作
		function orderSubAccount(index){
			$('#grid_index').val(index);
			$("#code").val("");
			$("#paymentRequestid").text(grid.getItemAt(index).paymentRequestid);
			$("#paymentAmount").text(grid.getItemAt(index).paymentAmount);
			$("#outletsName").text(grid.getItemAt(index).outletsName);
			$("#tmsLoginName").text(grid.getItemAt(index).tmsLoginName);
			signDialog.show();
			
		}
		
		<%-- //退款操作
		function orderDrawback(){
				var data = {
						requestid:$("#requestid").val(),
						money:$("#money").val(),
						orderNumber:$("#orderNumber").val()
				}
				msg.Confirm("确定要退款？",function(){
					$.ajax({
						url:'<%=request.getContextPath()%>/sys/bank/subAccountDrawback.shtml',
						type:'post',
						data:data,
						success:function(result){
							if(result.result){
								msg.Alert("操作成功",function(){
									$("#requestid").val("");
									$("#money").val("");
									$("#orderNumber").val("");
									store.load();
								},"success");
								
							}
						}
					})
				},"question");
		} --%>
	</script>
</body>
</html>