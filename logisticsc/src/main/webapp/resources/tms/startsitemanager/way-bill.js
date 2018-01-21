$(function(){
	loadBuiCalendar();
	loadStartOutletsInfo();
	loadForm();
	var wayBillNumber = $('#wayBillNumber').val();
	
	// 编辑运单
	var wayBillOrderId = $('#wayBillOrderId').val();
	// 从货运交易系统订单 跳转到 开单入库
	var orderNumber = $('#orderNumber').val();
	
	if(isNull(wayBillNumber) && isNull(wayBillOrderId) && isNull(orderNumber)){
		buildEditCargoGrid([{}]);
		loadXzqhInfo('100000', 1);
		loadtargetOutletsInfo('');
		//loadPayMethod("");
		getCustomCost();
	}else if(!isNull(wayBillNumber)){
		loadLadingOrderData(wayBillNumber);
		loadCargoGridFormWayBillData(wayBillNumber);
		loadLadingOrderCostData(wayBillNumber);
	}else if(!isNull(wayBillOrderId)){ // 编辑运单
		loadWayBillOrderInfo(wayBillOrderId);
		loadWayBillOrderCargoInfo(wayBillOrderId);
		loadWayBillOrderCostInfo(wayBillOrderId);
	}else if(!isNull(orderNumber)){
		loadDataFormPlatformOrder(orderNumber);
		loadCargoGridFromOrderCargo(orderNumber);
		loadOrderCostData(orderNumber);
		getCustomCost();
	}
	
});

//加载提货单数据
function loadLadingOrderData(wayBillNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getladingorderbywaybillnumber.shtml',
		data : { 'wayBillNumber' : wayBillNumber },
		dataType : 'json',
		success : function(result){
			$('#orderNumber').val(result.orderNumber);
			$('#consignor').val(result.consignor);
			$('#consignorMobile').val(result.consignorMobile);
			$('#consignorAddress').val(result.consignorAddress);
			$('#consignorCompany').val(result.consignorCompany);
			$('#consignee').val(result.consignee);
			$('#consigneeMobile').val(result.consigneeMobile);
			$('#consigneeAddress').val(result.consigneeAddress);
			$('#consigneeCompany').val(result.consigneeCompany);
			
			$('#receiveType').val(result.receiveType);
			$('#receiptSlip').val(result.receiptSlip);
			$('#receiptSlipNum').val(result.receiptSlipNum);
			
			$('#cityDriverName').val(result.cityDriverName);
			$('#cityDriverPhone').val(result.cityDriverPhone);
			$('#cityVehicleNumber').val(result.cityVehicleNumber);
			
			$('#agencyFund').val(result.agencyFund);
			$('#agencyFundPoundage').val(result.agencyFundPoundage);
			$('#insuranceMoney').val(result.insuranceMoney);
			$('#insurance').val(result.insurance);
			$('#takeCargoCost').val(result.takeCargoCost);
			$('#loadUnloadCost').val(result.loadUnloadCost);
			$('#transferCost').val(result.transferCost);
			$('#otherCost').val(result.otherCost);
			$('#freight').val(result.freight);
			
			$('#payMethod').val(result.payMethod);
			if(result.payMethod == 1){
				$('#advancePaymentDiv').show();
				$('#advanceCost').val(result.advanceCost);
			}else if(result.payMethod == 6){
				$('#multi-pay').show();
				$('#immediatePay').val(result.immediatePay);
				$('#arrivePay').val(result.arrivePay);
				$('#backPay').val(result.backPay);
			}
			$('#total').val(result.total);
			$('#remark').val(result.remark);
			loadtargetOutletsInfo(result.targetOutlets);
			showXzqhInfo(result.targetProvince, result.targetCity, result.targetCounty);
		}
	});
}

//禁用运单单信息
function setWayBillDisabled(){
	$('#wayBillBaseInfo input, #wayBillBaseInfo select').attr('disabled','disabled');
	$('#wayBillCostInfo input, #wayBillCostInfo select').attr('disabled','disabled');
	$('#receiptSlipNum').removeAttr('disabled');
	//$('#receiptSlip').removeAttr('disabled');
}

// 加载运单 货物信息
function loadCargoGridFormWayBillData(wayBillNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/waybill/getladingordercargoinfo.shtml',
		data : { 'wayBillNumber' : wayBillNumber },
		success : function(result){
			buildCargoGrid(result);
		}
	});
}

//构建已加载的货物信息表格
function buildCargoGrid(data){
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
			Toolbar, Format) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [
   	        {title : '货物名称',dataIndex : 'name'},
   	        {title : '品牌', dataIndex : 'brand'},
   	        {title : '型号', dataIndex : 'model'},
   	        {title : '类型', dataIndex : 'goodsType'},
   	        {title : '包装',dataIndex : 'packageTypeName'},
   	        {title : '件数', dataIndex : 'number'},
   	        {title : '套数', dataIndex : 'setNumber'},
   	        {title : '重量(t)', dataIndex : 'totalWeight'},
   	        {title : '体积(m³)', dataIndex : 'totalVolume'},
   	        {title : '计费方式',dataIndex : 'countCostModeName'},
   	        {title : '单价', dataIndex : 'price'}
        ];
		
		var cargoStore = new Store({
			/*url : contextPath + '/tms/waybill/getladingordercargoinfo.shtml',
			autoLoad : true,*/
			data : data,
			/*proxy : { method : 'post' },
			params : {'wayBillNumber' : wayBillNumber}*/
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

//加载提货单费用数据
function loadLadingOrderCostData(wayBillNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/waybill/getladingordercostinfo.shtml',
		data : { 'wayBillNumber' : wayBillNumber },
		dataType : 'json',
		success : function(result){
			loadCustomCost(result);
			$('#wayBillBaseInfo input, #wayBillBaseInfo select').attr('disabled','disabled');
			$('#wayBillCostInfo input, #wayBillCostInfo select').attr('disabled','disabled');
		}
	});
}
// 转换提货单费用集合 为对象
/*function convertLadingOrderCostObj(result){
	var data = '{';
	for(var i = 0; i < result.length; i++){
		if(i == result.length - 1){
			data += '"' + result[i].code + '":"' + result[i].money + '"';
		}else{
			data += '"' + result[i].code + '":"' + result[i].money + '",';
		}
	}
	data += '}';
	return JSON.parse(data);
}*/


//保存运单信息
function saveWayBill(isNew){
	
	// 验证
	var numberInfoFormValid = numberInfoForm.isValid();
	var baseInfoFormValid = baseInfoForm.isValid();
	var costInfoFormValid = costInfoForm.isValid();
	if(!numberInfoFormValid || !baseInfoFormValid || !costInfoFormValid){
		return;
	}
	if(!validEditCargoGrid()){
		return;
	}
	
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/waybill/savewaybillorder.shtml',
		data : {
			'wayBillOrder' : JSON.stringify(buildWayBillData()),
			'wayBillOrderCargo' : JSON.stringify(grid.getItems()),
			'wayBillOrderCostInfo' : JSON.stringify(buildCostData()),
			'token' : $('#token').val()
		},
		success : function(result, textStatus, XMLHttpRequest){
			if(result == 1){
				if(isNew == 1){
					// 新建 跳转到新的开单入库页面
					window.location.href = contextPath + '/tms/waybill/towaybillpage.shtml';
				}else if(isNew == 0){
					// 跳转到运单管理界面
					window.location.href = contextPath + '/tms/waybill/towaybillmanagerpage.shtml';
				}
			}else{
				BUI.Message.Alert('保存失败','error');
				// 通过XMLHttpRequest取得响应头
				var isRepeatSubmit = XMLHttpRequest.getResponseHeader('isRepeatSubmit');
				if(isRepeatSubmit == 'true'){
					// 如果是重复提交，则做相应的提示
					//alert('请不要重复提交');
				} else {
					// 非重复提交， 则取响应头中的token，更新input中的token值
					var token = XMLHttpRequest.getResponseHeader('token');
					$('#token').val(token);
				}
			}
		}
	});
}
//构建运单基础数据
function buildWayBillData(){
	var targetOutlets = document.getElementById('targetOutlets');
	var wayBillOrder = {
			'id' : $("#wayBillOrderId").val(),
			'orderNumber' : $("#orderNumber").val(),
			'wayBillNumber' : $("#wayBillNumber").val(),
			'wayBillOrderTime' : $("#wayBillOrderTime").val(),
			'wayBillOrderUser' : $("#userId").val(),
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
			'sendType' : $("#sendType").val(),
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
	return wayBillOrder;
}

// ------------------运单编辑--------------------------
// 加载运单信息
function loadWayBillOrderInfo(id){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/waybill/getwaybillorderinfo.shtml',
		data : {'id':id},
		success : function(result){
			$('#orderNumber').val(result.orderNumber);
			$('#wayBillNumber').val(result.wayBillNumber);
			$('#wayBillOrderTime').val(formatDateTime(result.wayBillOrderTime));
			
			$('#consignor').val(result.consignor);
			$('#consignorMobile').val(result.consignorMobile);
			$('#consignorAddress').val(result.consignorAddress);
			$('#consignorCompany').val(result.consignorCompany);
			$('#consignee').val(result.consignee);
			$('#consigneeMobile').val(result.consigneeMobile);
			$('#consigneeAddress').val(result.consigneeAddress);
			$('#consigneeCompany').val(result.consigneeCompany);
			$('#sendType').val(result.sendType);
			$('#receiveType').val(result.receiveType);
			$('#receiptSlip').val(result.receiptSlip);
			$('#receiptSlipNum').val(result.receiptSlipNum);
			$('#cityDriverName').val(result.cityDriverName);
			$('#cityDriverPhone').val(result.cityDriverPhone);
			$('#cityVehicleNumber').val(result.cityVehicleNumber);
			
			$('#agencyFund').val(result.agencyFund);
			$('#agencyFundPoundage').val(result.agencyFundPoundage);
			$('#insuranceMoney').val(result.insuranceMoney);
			$('#insurance').val(result.insurance);
			$('#takeCargoCost').val(result.takeCargoCost);
			$('#loadUnloadCost').val(result.loadUnloadCost);
			$('#transferCost').val(result.transferCost);
			$('#otherCost').val(result.otherCost);
			
			$('#freight').val(result.freight);
			$('#payMethod').val(result.payMethod);
			if(result.payMethod == 1){
				$('#advancePaymentDiv').show();
				$('#advanceCost').val(result.advanceCost);
			}else if(result.payMethod == 6){
				$('#multi-pay').show();
				$('#immediatePay').val(result.immediatePay);
				$('#arrivePay').val(result.arrivePay);
				$('#backPay').val(result.backPay);
			}
			$('#remark').val(result.remark);
			
			loadtargetOutletsInfo(result.targetOutlets);
			showXzqhInfo(result.targetProvince, result.targetCity, result.targetCounty);
		}
	});
}

// 加载运单货物信息
function loadWayBillOrderCargoInfo(wayBillOrderId){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/waybill/getwaybillordercargoinfo.shtml',
		data : {'wayBillOrderId':wayBillOrderId},
		success : function(result){
			buildEditCargoGrid(result);
		}
	});
}

// 加载运单费用信息 (编辑)
function loadWayBillOrderCostInfo(wayBillOrderId){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/waybill/getwaybillordercostinfo.shtml',
		data : {'wayBillOrderId':wayBillOrderId},
		success : function(result){
			loadCustomCost(result);
		}
	});
}

// 获取运单费用信息的费用
function getCostMoney(code, result){
	for(var i = 0; i < result.length; i++){
		if(result[i].code == code){
			return result[i].money;
		}
	}
	return '';
}

// 加载数据(数据来自货运交易系统订单)
function loadDataFormPlatformOrder(orderNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/tmsorder/getorderbyordernumber.shtml',
		data : {'orderNumber' : orderNumber},
		success : function(result){
			loadtargetOutletsInfo(result.targetOutlets);
			showXzqhInfo(result.consigneeProvince, result.consigneeCity, result.consigneeCounty);
			$('#consignor').val(result.consignorName);
			$('#consignorMobile').val(result.consignorPhoneNumber);
			$('#consignorAddress').val(result.consignorAddress);
			$('#consignee').val(result.consigneeName);
			$('#consigneeMobile').val(result.consigneePhoneNumber);
			$('#consigneeAddress').val(result.consigneeAddress);
			$('#sendType').val(result.sendCargoType);
			$('#receiveType').val(result.receiveCargoType);
			//$('#receiptSlip').val(); 是否回单
			$('#cityDriverName').val(result.driverName);
			$('#cityDriverPhone').val(result.driverPhone);
			$('#cityVehicleNumber').val(result.vehicleNumber);
			
			$('#payMethod').val(result.payType);
			//$('#payMethod').attr('disabled','disabled');
			if(result.payType == 1){
				$('#advancePaymentDiv').show();
			}
			$('#insuranceMoney').val(result.insuranceMoney);
			if(result.isWaitPay == 1){
				$('#isWaitPay').attr('checked',true);
			}
			setWayBillDisabled();
		}
	});
}

// 加载订单 货物数据
function loadCargoGridFromOrderCargo(orderNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/tmsorder/getordercargobyordernumber.shtml',
		data : { 'orderNumber' : orderNumber },
		success : function(result){
			buildCargoGrid(result);
		}
	});
}

// 加载订单费用 数据
function loadOrderCostData(orderNumber){
	$.ajax({
		type : 'post',
		url : contextPath + '/tmsorder/getorderadditionalserverbyordernumber.shtml',
		data : { 'orderNumber' : orderNumber },
		async: false,
		success : function(result){
			var receiptSlip = result.isReceipt;
			if(receiptSlip == 1){
				$('#receiptSlip').val(1);
				$('#receiptSlip').attr('disabled','disabled');
			}
			var isCollectionDelivery = result.isCollectionDelivery;
			if(isCollectionDelivery == 1){
				$('#agencyFund').val(result.collectionDeliveryMoney);
			}
		}
	});
	$.ajax({
		type : 'post',
		url : contextPath + '/tmsorder/getorderbillbyordernumber.shtml',
		data : { 'orderNumber' : orderNumber },
		async: false,
		success : function(result){
			$('#freight').val(result.freight);
			$('#agencyFundPoundage').val(result.agencyFundPoundage);
			$('#insurance').val(result.insurance);
			$('#takeCargoCost').val(result.takeCargoCost);
			$('#loadUnloadCost').val(new Number(result.loadCargoCost) + new Number(result.unloadCargoCost));
			$('#otherCost').val(result.otherCost);
		}
	});
	countTotalMoney();
}

