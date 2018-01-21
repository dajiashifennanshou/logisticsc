<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发车管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.form-inline input{width: 100px;}
.form-inline select{width: 120px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>发车管理</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
					        <label class="control-label">出发网点</label>
					        <div id="startOutlets" style="display: inline-block;"></div>
					        
					        <label class="control-label">到达网点</label>
					        <div id="targetOutlets" style="display: inline-block;"></div>
					        
					        <label class="control-label">发车单状态</label>
					        <select class="input-normal" id="status"></select>
				        	
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="departList"></div>
						<div id="departListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var grid, store, startOutletsSuggest, targetOutletsSuggest;
	$(function(){
		loadCalendar();
		loadDepartListStatus();
		loadDepartList();
		loadOutletsInfo();
	});
	
	// 加载发车单列表
	function loadDepartList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
				Toolbar) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '发车单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'departNumber'
			}, {
				title : '发车状态',
				elCls : 'center',
				dataIndex : 'statusName'
			}, {
				title : '出发网点',
				elCls : 'center',
				dataIndex : 'startOutletsName'
			}, {
				title : '途经网点',
				width : 150,
				elCls : 'center',
				dataIndex : 'wayOutletsName'
			}, {
				title : '到达网点',
				elCls : 'center',
				dataIndex : 'targetOutletsName'
			}, {
				title : '开单时间',
				elCls : 'center',
				width : 150,
				dataIndex : 'operateTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '车牌号',
				elCls : 'center',
				dataIndex : 'vehicleNumber'
			}, {
				title : '司机姓名',
				elCls : 'center',
				dataIndex : 'driverName'
			}, {
				title : '司机电话',
				elCls : 'center',
				dataIndex : 'driverPhone'
			}, {
				title : '保险金额',
				elCls : 'center',
				dataIndex : 'insuranceMoney'
			}, {
				title : '保险费',
				elCls : 'center',
				dataIndex : 'insuranceCost'
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/sys/depart/search.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#departList',
				columns : columns,
				store : store,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                    btnCls : 'button button-normal',
	                    text:'查看详情',
	                    handler : showDepartListDetail
	                }]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#departListBar',
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

	// 加载单车单状态 下拉框
	function loadDepartListStatus(){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/depart/getdepartliststatus.shtml',
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
			'startOutlets' : startOutletsSuggest.getSelectedValue(),
			'targetOutlets' : targetOutletsSuggest.getSelectedValue(),
			'status' : $('#status').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	// 查看详情
	function showDepartListDetail(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一个发车单','warning');
			return;
		}
		window.location.href = '<%=request.getContextPath()%>/tms/depart/todepartlistdetail.shtml?departNumber='+selection[0].departNumber;
	}
	
	// 加载网点信息
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
					startOutletsSuggest = new Select.Suggest({
					    render:'#startOutlets',
					    name:'startOutlets', //形成输入框的name
						data:data
					});
					startOutletsSuggest.render();
				});
				BUI.use('bui/select',function (Select) {
					targetOutletsSuggest = new Select.Suggest({
					    render:'#targetOutlets',
					    name:'targetOutlets', //形成输入框的name
						data:data
					});
					targetOutletsSuggest.render();
				});
			}
		});
	}
	</script>	
</body>
</html>