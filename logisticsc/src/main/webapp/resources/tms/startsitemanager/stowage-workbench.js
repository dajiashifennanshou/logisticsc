// 全局变量
var contextPath = $('#contextPath').val();
var gridLeft, gridRight, storeLeft, storeRight;

$(function(){
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
			Toolbar) {
		var Grid = Grid; 
		var Store = Data.Store;
		// ------------ 左边的表格
		var columnsLeft = [ {
			title : '开单时间',
			width : 150,
			elCls : 'center',
			dataIndex : 'wayBillOrderTime',
			renderer:BUI.Grid.Format.datetimeRenderer
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
			title : '收货人',
			elCls : 'center',
			dataIndex : 'consignee'
		}, {
			title : '收货人电话',
			elCls : 'center',
			dataIndex : 'consigneeMobile'
		}, {
			title : '发货人',
			elCls : 'center',
			dataIndex : 'consignor'
		}, {
			title : '发货人电话',
			elCls : 'center',
			dataIndex : 'consignorMobile'
		}, {
			title : '货物名称',
			elCls : 'center',
			dataIndex : 'cargoName'
		}, {
			title : '件数',
			elCls : 'center',
			dataIndex : 'cargoNumber'
		}, {
			title : '套数',
			elCls : 'center',
			dataIndex : 'cargoSetNumber'
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
			title : '操作员',
			elCls : 'center',
			dataIndex : 'operatePersonName'
		} ];
		storeLeft = new Store({
			url : contextPath + '/tms/waybill/getstowagewaybill.shtml',
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
			plugins : [Grid.Plugins.CheckSelection, Grid.Plugins.RowNumber,Grid.Plugins.AutoFit]
		});

		gridLeft.render();
		
		// ----------------右边的表格
		var columnsRight = [ {
			title : '开单时间',
			width : 150,
			elCls : 'center',
			dataIndex : 'wayBillOrderTime',
			renderer:BUI.Grid.Format.datetimeRenderer
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
			title : '收货人',
			elCls : 'center',
			dataIndex : 'consignee'
		}, {
			title : '收货人电话',
			elCls : 'center',
			dataIndex : 'consigneeMobile'
		}, {
			title : '发货人',
			elCls : 'center',
			dataIndex : 'consignor'
		}, {
			title : '发货人电话',
			elCls : 'center',
			dataIndex : 'consignorMobile'
		}, {
			title : '货物名称',
			elCls : 'center',
			dataIndex : 'cargoName'
		}, {
			title : '件数',
			elCls : 'center',
			dataIndex : 'cargoNumber'
		}, {
			title : '套数',
			elCls : 'center',
			dataIndex : 'cargoSetNumber'
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
			title : '操作员',
			elCls : 'center',
			dataIndex : 'operatePersonName'
		} ];
		storeRight = new Store({
			url : contextPath + '/tms/waybill/getwaybillsbydepartnumber.shtml?departNumber='+$('#departNumber').val(),
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