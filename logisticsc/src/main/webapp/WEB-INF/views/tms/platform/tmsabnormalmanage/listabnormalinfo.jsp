<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>异常管理</title>
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
           	<h2>异常管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        	<label>发站网点：
	        		<select id="search_start_outlets" name="searchStartOutlets">
	        			<option value="">全部</option>
	        			<c:forEach var="outletsInfo" items="${outletsInfos }">
	        				<option value="${outletsInfo.id }">${outletsInfo.name }</option>
	        			</c:forEach>
	        		</select>
	        	</label>
	        	<label>到站网点：
					<select id="search_end_outlets" name="searchStartOutlets">
	        			<option value="">全部</option>
	        			<c:forEach var="outletsInfo" items="${outletsInfos }">
	        				<option value="${outletsInfo.id }">${outletsInfo.name }</option>
	        			</c:forEach>
	        		</select>
	        	</label>
	        	<br>
	        	<label>异常类型：
	        		<select id="search_abnormal_type" name="searchAbnormalType">
	        			<option value="">全部</option>
	        			<c:forEach var="map" items="${abnormalTypes }">
	        				<option value="${map.key }">${map.value	}</option>
	        			</c:forEach>
	        		</select>
	        	</label>
	        	<label>开始时间：<input name="searchStartTime" id="search_start_time" type="text" class="calendar"/> -
	        					<input name="searchEndTime" id="search_end_time" type="text" class="calendar"/></label>
	          	<input id="condition" name="condition" type="text" placeholder="运单号">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params = {
					startOutlets:$("#search_start_outlets").val(),
					endOutlets:$("#search_end_outlets").val(),
					abnormalType:$("#search_abnormal_type").val(),
					startTime:$("#search_start_time").val(),
					endTime:$("#search_end_time").val(),
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//消息提示
		var message;
	    BUI.use('bui/overlay',function(overlay){
	        message = BUI.Message;
	    });
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/calendar'],
					function(Grid,Data,Toolbar,Format,Calendar){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '异常时间', dataIndex : 'abnormalDate', elCls : 'center', width:150, renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '处理时间', dataIndex : 'handleDate', elCls : 'center', width:150, renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '订单号', dataIndex : 'ordeNumber', elCls : 'center', width:130},
				{title : '运单号', dataIndex : 'wayBillNumber', elCls : 'center', width:130},
				{title : '异常状态', dataIndex : 'abnormalStatus', elCls : 'center',renderer:function(val){
					if(val==1){
						return '未处理';
					}else if(val==2){
						return '已处理';	
					}else if(val==0){
						return '已关闭';	
					}
				}},
				{title : '异常类型',dataIndex :'abnormalTypeName', elCls : 'center'},
				{title : '异常环节',dataIndex :'abnormalNodeName', elCls : 'center'},
				{title : '保险金额',dataIndex : 'money', elCls : 'center'},
			 	{title : '理赔金额',dataIndex : 'compensationMoney', elCls : 'center'},
			 	{title : '处理结果',dataIndex : 'handleResult', elCls : 'center'},
			 	{title : '操作时间',dataIndex : 'abnormalDate', elCls : 'center',width:150,renderer:BUI.Grid.Format.datetimeRenderer},
			 	{title : '操作员',dataIndex : 'operationPerson', elCls : 'center'},
				{title : '发站网点',dataIndex :'startOutletsName', elCls : 'center'},
				{title : '到站网点', dataIndex : 'targetOutletsName', elCls : 'center'},
				{title : '发货人', dataIndex : 'consignor', elCls : 'center'},
				{title : '发货人电话', dataIndex : 'consignorMobile', elCls : 'center'},
				{title : '收货人', dataIndex : 'consignee', elCls : 'center'},
				{title : '收货人电话', dataIndex : 'consigneeMobile', elCls : 'center'},
				{title : '货物名称', dataIndex : 'cargoName', elCls : 'center'},
			 	{title : '件数',dataIndex : 'cargoPiece', elCls : 'center'},
			 	{title : '代收货款',dataIndex : 'agencyFund', elCls : 'center'},
			 	{title : '垫付货款',dataIndex : 'advanceCost', elCls : 'center'},
			 	{title : '总运费',dataIndex : 'total', elCls : 'center'},
			 	{title : '付款方式',dataIndex : 'payMethodName', elCls : 'center'}
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/abnormal/search.shtml',
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
				//forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{ //添加、删除
		         	items : [{
			          		btnCls : 'button button-normal',
			         		text : '异常处理',
			           		listeners : {
			                	'click' : handleAbnormal
			                }
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '关闭异常',
			           		listeners : {
			                	'click' : closeException
			                }
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '库存核销',
			           		listeners : {
			                	'click' : chargeOff
			                }
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '查看',
			           		listeners : {
			                	'click' : viewItems
			                }
			          	}
		          	],
				}
				
			});
			function addAbnormal(){
				window.location.href="<%=request.getContextPath()%>/tms/abnormal/addabnormal.shtml";
			}
			function handleAbnormal(){
				var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng==1){
		        	if(selects[0].abnormalStatus==1){
		        		window.location.href="<%=request.getContextPath()%>/tms/abnormal/handleabnormal.shtml?abnormalId="+selects[0].id;		        		
		        	}else{
		        		message.Alert("异常已处理","warning");
		        	}
		        }else{
		        	message.Alert("请选择一条记录","warning");
		        }
				
			}
			
			// 关闭异常
			function closeException(){
				var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng>0){
		        	var data = [];
		        	for(var i=0;i<lng;i++){
		        		var status = selects[i].abnormalStatus;
		        		if(status == 0){
		        			BUI.Message.Alert('运单异常已关闭','warning');
		        			return;
		        		}
		        		if(status == 1){
		        			BUI.Message.Alert('未处理的异常不能关闭','warning');
		        			return;
		        		}
		        		data.push(selects[i].wayBillNumber);
		        	}
		        	BUI.Message.Confirm('确定要关闭异常吗？',function(){
		        		$.ajax({
			        		url:'<%=request.getContextPath()%>/tms/abnormal/clsewybllrdr.shtml',
			        		data:{wayBillNumbers:data},
			        		type:'post',
			        		dataType:'json',
			        		success:function(data){
			        			if(data.result==true){
			        				message.Alert("异常已关闭","warning");
			        				store.load();
			        			}
			        		}
			        	})
		        	},'question');
		        }
			}
			function chargeOff(){
				
			}
			function viewItems(){
				var selection = grid.getSelection();
				if(selection.length != 1){
					BUI.Message.Alert('请选择一条记录','warning');
					return;
				}
				location.href = '<%=request.getContextPath()%>/tms/abnormal/toabnormalinfodetail.shtml?id='+selection[0].id;
			}
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
			//日期加载
			var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
		});
		
	</script>
</body>
</html>