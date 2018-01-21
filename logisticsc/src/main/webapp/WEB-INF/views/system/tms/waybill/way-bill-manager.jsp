<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运单管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>运单管理</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
					        <label class="control-label">网点</label>
					        <div id="outletsId" style="display: inline-block;"></div>
					        
					        <label class="control-label">运单状态</label>
					        <select class="input-normal" id="status"></select>
				        	
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="运单号/订单号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="wayBillOrderList"></div>
						<div id="wayBillOrderListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var store, grid;
	var outletsSuggest;
	$(function(){
		loadCalendar();
		loadWayBillStatus();
		loadWayBillOrderList();
		
		loadOutletsInfo();
	});
	
	function loadOutletsInfo(){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/sys/waybill/getalloutletsinfo.shtml',
			success : function(result){
				var data = [];
				for(var i = 0; i < result.length; i++){
					var d = {};
					d.value = result[i].id;
					d.text = result[i].name;
					data.push(d);
				}
				BUI.use('bui/select',function (Select) {
					outletsSuggest = new Select.Suggest({
					    render:'#outletsId',
					    name:'suggest', //形成输入框的name
						data:data
					});
					outletsSuggest.render();
				});
			}
		});
		
	}
	
	// 加载运单列表
	function loadWayBillOrderList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '订单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'orderNumber'
			}, {
				title : '运单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'wayBillNumber'
			}, {
				title : '运单状态',
				elCls : 'center',
				dataIndex : 'statusName'
			}, {
				title : '出发网点',
				elCls : 'center',
				dataIndex : 'startOutletsName'
			}, {
				title : '到达网点',
				elCls : 'center',
				dataIndex : 'targetOutletsName'
			}, {
				title : '发货人',
				elCls : 'center',
				dataIndex : 'consignor'
			}, {
				title : '发货电话',
				elCls : 'center',
				dataIndex : 'consignorMobile'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consignee'
			}, {
				title : '收货电话',
				elCls : 'center',
				dataIndex : 'consigneeMobile'
			}, {
				title : '下单时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'wayBillOrderTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/waybill/search.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#wayBillOrderList',
				columns : columns,
				store : store,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                    btnCls : 'button button-normal',
	                    text:'查看运单',
	                    handler : findWayBillOrder
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'导出'
	                }]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#wayBillOrderListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}
	
	// 加载日历插件
	function loadCalendar(){
		BUI.use('bui/calendar',function(Calendar){
	    	var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	        });
		});
	}
	
	// 加载订单状态 下拉框
	function loadWayBillStatus(){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/waybill/getwaybillstatus.shtml',
			success : function(result){
				var html = "<option value=''>全部</option>";
				if(result != null){
					result = eval("("+result+")");
					for(var i = 0; i < result.length; i++){
						html += "<option value='"+result[i].value+"'>"+result[i].name+"</option>";
					}
				}
				document.getElementById('status').innerHTML = html;
			}
		});
	}
	
	// 查询
	function search(){
		var params = {
			'outletsId' : outletsSuggest.getSelectedValue(),
			'status' : $('#status').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 查看运单
	function findWayBillOrder(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一条数据','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tms/waybill/towaybilldetailpage.shtml?wayBillNumber='+selection[0].wayBillNumber;
	}
	
	</script>
</body>
</html>