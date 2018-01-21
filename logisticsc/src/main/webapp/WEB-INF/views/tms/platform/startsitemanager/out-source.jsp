<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>外包出库</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<script type="text/javascript">
var userType = ${tms_user_session.userType};
if(userType == 0 || userType == 1){
	alert('请使用网点账号登录');
	history.go(-1);
}
</script>
</head>
<body>
	<input type="hidden" id="outDepartNumber" value="${outDepartNumber}">
	<div class="doc-content">
		<div class="panel">
			<div class="panel-header">
				<h3>外包配载</h3>
			</div>
			<div class="panel-body">
				<div style="padding-bottom: 5px;">
	    			<label>目的网点：</label>
	    			<select onchange="searchLeft(this.value)">
	    				<option value="">全部</option>
	    				<c:forEach items="${outletsInfos}" var="outletsInfo">
	    					<option value="${outletsInfo.id}">${outletsInfo.name}</option>
	    				</c:forEach>
	    			</select>
	    		</div>
				<div class="row-fluid">
			    	<div class="span11">
			      		<div id="gridLeft"></div>
			      	</div>
			      	<div class="span2">
			      		<div class="row-fluid text-center">
			      			<div class="span24" style="padding-top: 100px;">
			      				<button class="button button-primary" onclick="moveRight()">&gt;</button>
			      			</div>
			      			<div class="span24" style="padding-top: 20px;">
			      				<button class="button button-primary" onclick="moveLeft()">&lt;</button>
			      			</div>
			      		</div>
			      	</div>
			      	<div class="span11">
			      		<div id="gridRight"></div>
			      	</div>
			    </div>
			    <div class="row-fluid" style="margin-top: 20px;">
			    	<div class="span24 text-center">
			    		<form id="toDepartListForm" action="<%=request.getContextPath()%>/tms/outdepart/tooutdepartlistpage.shtml"
			    		 method="post" onsubmit="return generateDepartList()">
			    			<input type="hidden" name="wayBillData" id="wayBillData">
			    			<input type="hidden" name="outDepartNumber" value="${outDepartNumber}">
			    			<button class="button button-info" type="submit">生成外包清单</button>
			    		</form>
			    	</div>
			    </div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var gridLeft, gridRight, storeLeft, storeRight;

	$(function(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
				Toolbar) {
			var Grid = Grid; 
			var Store = Data.Store;
			// ------------ 左边的表格
			var columnsLeft = [ {
				title : '运单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'wayBillNumber'
			}, {
				title : '异常状态',
				elCls : 'center',
				dataIndex : 'exceptionStatusName'
			}, {
				title : '到达网点',
				elCls : 'center',
				dataIndex : 'targetOutletsName'
			}, {
				title : '目的地',
				elCls : 'center',
				width : 200,
				dataIndex : 'targetAddress'
			}, {
				title : '发货人',
				elCls : 'center',
				dataIndex : 'consignor'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consignee'
			}, {
				title : '货物名称',
				elCls : 'center',
				dataIndex : 'cargoName'
			}, {
				title : '件数',
				elCls : 'center',
				dataIndex : 'cargoNumber'
			}, {
				title : '代收货款',
				elCls : 'center',
				dataIndex : 'agencyFund'
			}, {
				title : '垫付货款',
				elCls : 'center',
				dataIndex : 'advanceCost'
			}, {
				title : '总运费',
				elCls : 'center',
				dataIndex : 'total'
			}, {
				title : '付款方式',
				elCls : 'center',
				dataIndex : 'payMethodName'
			}, {
				title : '回单份数',
				elCls : 'center',
				dataIndex : 'receiptSlipNum'
			}, {
				title : '开单时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'wayBillOrderTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '操作员',
				elCls : 'center',
				dataIndex : 'operatePersonName'
			} ];
			storeLeft = new Store({
				url : '<%=request.getContextPath()%>/tms/waybill/getstowagewaybill.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 5
			// 需要在store中 配置pageSize
			});
			gridLeft = new Grid.Grid({
				height : 300,
				render : '#gridLeft',
				columns : columnsLeft,
				store : storeLeft,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection, Grid.Plugins.RowNumber,Grid.Plugins.AutoFit]
			});

			gridLeft.render();
			
			// ----------------右边的表格
			var columnsRight = [ {
				title : '运单号',
				width : 130,
				elCls : 'center',
				dataIndex : 'wayBillNumber'
			}, {
				title : '异常状态',
				elCls : 'center',
				dataIndex : 'exceptionStatusName'
			}, {
				title : '到达网点',
				elCls : 'center',
				dataIndex : 'targetOutletsName'
			}, {
				title : '目的地',
				elCls : 'center',
				width : 200,
				dataIndex : 'targetAddress'
			}, {
				title : '发货人',
				elCls : 'center',
				dataIndex : 'consignor'
			}, {
				title : '收货人',
				elCls : 'center',
				dataIndex : 'consignee'
			}, {
				title : '货物名称',
				elCls : 'center',
				dataIndex : 'cargoName'
			}, {
				title : '件数',
				elCls : 'center',
				dataIndex : 'cargoNumber'
			}, {
				title : '代收货款',
				elCls : 'center',
				dataIndex : 'agencyFund'
			}, {
				title : '垫付货款',
				elCls : 'center',
				dataIndex : 'advanceCost'
			}, {
				title : '总运费',
				elCls : 'center',
				dataIndex : 'total'
			}, {
				title : '付款方式',
				elCls : 'center',
				dataIndex : 'payMethodName'
			}, {
				title : '回单份数',
				elCls : 'center',
				dataIndex : 'receiptSlipNum'
			}, {
				title : '开单时间',
				width : 150,
				elCls : 'center',
				dataIndex : 'wayBillOrderTime',
				renderer:BUI.Grid.Format.datetimeRenderer
			}, {
				title : '操作员',
				elCls : 'center',
				dataIndex : 'operatePersonName'
			} ];
			storeRight = new Store({
				url : '<%=request.getContextPath()%>/tms/waybill/getwaybillsbyoutdepartnumber.shtml?outDepartNumber='+$('#outDepartNumber').val(),
				autoLoad : true,
				//remoteSort : true,
				pageSize : 5
			// 需要在store中 配置pageSize
			});
			gridRight = new Grid.Grid({
				height : 300,
				render : '#gridRight',
				columns : columnsRight,
				store : storeRight,
				//forceFit : true,
				plugins : [Grid.Plugins.CheckSelection, Grid.Plugins.RowNumber,Grid.Plugins.AutoFit]
			});

			gridRight.render();
		});
	});

	// 选择目的网点 加载运单信息
	function searchLeft(value){
		var params = {
			'targetOutlets' : value
		}
		storeLeft.load(params);
	}
	
	// 将库存货物添加到车辆
	function moveRight(){
		var selections = gridLeft.getSelection();
		storeLeft.remove(selections);
		storeRight.add(selections);
	}

	// 将车辆货物 退回到库存
	function moveLeft(){
		var selections = gridRight.getSelection();
		storeRight.remove(selections);
		storeLeft.add(selections);
	}

	// 生成发车清单
	function generateDepartList(){
		var data = gridRight.getItems();
		if(data == null || data == ''){
			return false;
		}
		$('#wayBillData').val(JSON.stringify(data));
	}
	</script>
</body>
</html>