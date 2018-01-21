$(function(){
	loadBuiCalendar();
	loadForm();
	loadStartOutletsInfo();
	loadVehicleInfo();
	
	getCustomCost();
	var orderNumber = $('#orderNumber').val();
	if(isNull(orderNumber)){
		buildEditCargoGrid([{}]);
		loadXzqhInfo('100000', 1);
		loadtargetOutletsInfo('');
	}else{
		loadOrderData(orderNumber);
		buildCargoGrid(orderNumber);
		loadPlatformOrderBillInfo(orderNumber);
	}
});

// 加载车辆信息
function loadVehicleInfo(){
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getvehicleinfo.shtml',
		success : function(result){
			var html = '<option value="">请选择车辆</option>';
			for(var i = 0; i < result.length; i++){
				html += '<option value="'+result[i].plateNumber+'">'+result[i].plateNumber+'</option>';
			}
			$('#licensePlateNo').html(html);
		}
	});
}

/* 订单提交 */
// 提交提货单
function submitDeliveryBill(){
	// 验证
	var numberInfoFormValid = numberInfoForm.isValid();
	var driverInfoFormValid = driverInfoForm.isValid();
	var baseInfoFormValid = baseInfoForm.isValid();
	var costInfoFormValid = costInfoForm.isValid();
	if(!numberInfoFormValid || !driverInfoFormValid || !baseInfoFormValid || !costInfoFormValid){
		return;
	}
	if(!validEditCargoGrid()){
		return;
	}
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/saveladingorder.shtml',
		data : {
			'ladingOrder' : JSON.stringify(buildLadingOrderData()),
			'ladingOrderCargo' : JSON.stringify(grid.getItems()),
			'ladingOrderCostInfo' : JSON.stringify(buildCostData())
		},
		success : function(result){
			if(result == 1){
				// 跳转到提货单管理界面
				window.location.href = contextPath + '/deliverycargo/deliverycargomanager.shtml';
			}else{
				BUI.Message.Alert('保存失败','error');
			}
		}
	});
}

// 构建提货单数据
function buildLadingOrderData(){
	var targetOutlets = document.getElementById('targetOutlets');
	var ladingOrder = {
			'orderNumber' : $("#orderNumber").val(),
			'wayBillNumber' : $("#wayBillNumber").val(),
			'ladingOrderTime' : $("#ladingOrderTime").val(),
			'ladingOrderUser' : $("#userId").val(),
			'driverName' : $("#driverName").val(),
			'driverMobile' : $("#driverMobile").val(),
			'licensePlateNo' : $("#licensePlateNo").val(),
			'startOutlets' : $("#startOutlets").val(),
			'startOutletsName' : $("#startOutletsName").val(),
			'targetOutlets' : targetOutlets.value,
			'targetOutletsName' : targetOutlets.options[targetOutlets.options.selectedIndex].text,
			'targetProvince' : $("#targetProvince").val(),
			'targetCity' : $("#targetCity").val(),
			'targetCounty' : $("#targetCounty").val(),
			'consignor' : $("#consignor").val(),
			'consignorCompany' : $("#consignorCompany").val(),
			'consignorMobile' : $("#consignorMobile").val(),
			'consignorAddress' : $("#consignorAddress").val(),
			'consignee' : $("#consignee").val(),
			'consigneeCompany' : $("#consigneeCompany").val(),
			'consigneeMobile' : $("#consigneeMobile").val(),
			'consigneeAddress' : $("#consigneeAddress").val(),
			//'sendType' : $("#sendType").val(),
			'receiveType' : $("#receiveType").val(),
			'receiptSlip' : $("#receiptSlip").val(),
			'receiptSlipNum' : $("#receiptSlipNum").val(),
			'cityDriverName' : $("#cityDriverName").val(),
			'cityDriverPhone' : $("#cityDriverPhone").val(),
			'cityVehicleNumber' : $("#cityVehicleNumber").val(),
			'agencyFund' : $("#agencyFund").val(),
			'agencyFundPoundage' : $("#agencyFundPoundage").val(),
			'insuranceMoney' : $("#insuranceMoney").val(),
			'insurance' : $("#insurance").val(),
			'takeCargoCost' : $("#takeCargoCost").val(),
			'loadUnloadCost' : $("#loadUnloadCost").val(),
			'transferCost' : $("#transferCost").val(),
			'otherCost' : $("#otherCost").val(),
			'freight' : $("#freight").val(),
			'isWaitPay' : $("#isWaitPay").val(),
			'advanceCost' : $("#advanceCost").val(),
			'immediatePay' : $("#immediatePay").val(),
			'arrivePay' : $("#arrivePay").val(),
			'backPay' : $("#backPay").val(),
			'payMethod' : $("#payMethod").val(),
			'total' : $("#total").val(),
			'remark' : $("#remark").val()
	};
	return ladingOrder;
}

// 加载货运交易系统订单数据
function loadOrderData(orderNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getplatformorderinfo.shtml',
		data : { 'orderNumber' : orderNumber },
		dataType : 'json',
		success : function(result){
			$('#consignor').val(result.consignorName);
			$('#consignorMobile').val(result.consignorPhoneNumber);
			$('#consignorAddress').val(result.consignorAddress);
			$('#consignee').val(result.consigneeName);
			$('#consigneeMobile').val(result.consigneePhoneNumber);
			$('#consigneeAddress').val(result.consigneeAddress);
			$('#receiveType').val(result.receiveCargoType);
			$('#cityDriverName').val(result.driverName);
			$('#cityDriverPhone').val(result.driverPhone);
			$('#cityVehicleNumber').val(result.vehicleNumber);
			
			$('#totalWorth').text(result.totalWorth);
			
			var isInsurance = result.isInsurance;
			if(isInsurance == 1){
				$('#insuranceMoney').val(result.insuranceMoney);
				$('#insuranceMoney').attr('disabled','disabled');
			}
			$('#payMethod').val(result.payType);
			$('#payMethod').attr('disabled','disabled');
			if(result.payType == 1){
				$('#advancePaymentDiv').show();
				$('#advanceCost').val(result.advanceCost);
			}
			if(result.isWaitPay == 1){
				$('#isWaitPay').attr('checked',true);
			}
			loadtargetOutletsInfo(result.targetOutlets);
			$('#targetOutletsName').val(result.targetOutletsName);
			showXzqhInfo(result.consigneeProvince, result.consigneeCity, result.consigneeCounty);
			
			setDeliveryDisabled();
		}
	});
}

// 禁用提货单信息
function setDeliveryDisabled(){
	$('#ladingOrderBaseInfo input, #ladingOrderBaseInfo select, #ladingOrderCostInfo input').attr('disabled','disabled');
	$('#receiptSlipNum').removeAttr('disabled');
	$('#receiptSlip').removeAttr('disabled');
}

// 构建已加载的货物信息表格
function buildCargoGrid(orderNumber){
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [
   	        {title : '货物名称',dataIndex : 'name'},
   	        {title : '品牌', dataIndex : 'brand'},
   	        {title : '型号', dataIndex : 'model'},
   	        {title : '包装',dataIndex : 'packageTypeName'},
   	        {title : '件数', dataIndex : 'number'},
   	        {title : '套数', dataIndex : 'setNumber'},
   	        {title : '重量', dataIndex : 'totalWeight'},
   	        {title : '体积', dataIndex : 'totalVolume'},
   	        {title : '计费方式',dataIndex : 'countCostModeName'},
   	        {title : '单价', dataIndex : 'price'}
        ];
		
		var cargoStore = new Store({
			url : contextPath + '/deliverycargo/getordercargoinfo.shtml',
			autoLoad : true,
			proxy : { method : 'post' },
			params : {'orderNumber' : orderNumber}
		});
		grid = new Grid.Grid({
			render : '#editCargoGrid',
			columns : columns,
			store : cargoStore,
			forceFit : true,
			//plugins : [Grid.Plugins.CheckSelection]
		});
		grid.render();
	});
}

//加载货运交易系统 账单信息
function loadPlatformOrderBillInfo(orderNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getplatformorderbillinfo.shtml',
		data : { 'orderNumber' : orderNumber },
		dataType : 'json',
		async: false,
		success : function(result){
			$('#freight').val(result.estimateFreight);
			$('#totalCost').val(result.totalCost);
			$('#takeCargoCost').val(result.estimateTakeCargoCost);
			$('#agencyFundPoundage').val(result.agencyFundPoundage);
			$('#insurance').val(result.insurance);
			$('#loadUnloadCost').val(new Number(result.estimateLoadCargoCost) + new Number(result.estimateUnloadCargoCost));
			$('#totalCost').attr('disabled','disabled');
			$('#insurance').attr('disabled','disabled');
			$('#loadUnloadCost').attr('disabled','disabled');
		}
	});
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getplatformorderadditionalserverinfo.shtml',
		data : { 'orderNumber' : orderNumber },
		async: false,
		success : function(result){
			var isCollectionDelivery = result.isCollectionDelivery;
			if(isCollectionDelivery == 1){
				$('#agencyFund').val(result.collectionDeliveryMoney);
				$('#agencyFund').attr('disabled','disabled');
			}
			var receiptSlip = result.isReceipt;
			if(receiptSlip == 1){
				$('#receiptSlip').val(1);
				$('#receiptSlip').attr('disabled','disabled');
			}
		}
	});
	countTotalMoney();
}

// 选择司机  加载司机信息
function choiceDriver(id){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/driver/getdriverinfo.shtml',
		data : { 'id' : id },
		success : function(result){
			$('#driverName').val(result.driverName);
			$('#driverMobile').val(result.phoneNumber);
		}
	});
}