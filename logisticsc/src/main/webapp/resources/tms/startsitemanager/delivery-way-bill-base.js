// 全局变量
var contextPath = $('#contextPath').val();
var grid, store;
var numberInfoForm, driverInfoForm, baseInfoForm, costInfoForm;
var customCostIds = []; // 自定义运单费用 输入框id数组

// 构建货物编辑表格
function buildEditCargoGrid(data){
	var packageType = getPackageType();
	var countCostMode = getCountCostMode();
	BUI.use(['bui/grid','bui/data'],function(Grid,Data){
        var Store = Data.Store;
        var columns = [
	        {title : '货物名称',dataIndex : 'name',editor : {xtype : 'text', rules : {required : true}}}, //editor中的定义等用于 BUI.Form.Field.Text的定义
	        {title : '品牌', dataIndex : 'brand',editor : {xtype : 'text'}},
	        {title : '型号', dataIndex : 'model',editor : {xtype : 'text'}},
	        {title : '包装',dataIndex : 'packageType', editor : {xtype :'select',items : packageType},
	        	renderer : Grid.Format.enumRenderer(packageType)
	        },
	        {title : '件数', dataIndex : 'number',editor : {xtype : 'number'}, renderer : function(value,obj,index){
	        	countCargoFreight(obj, index);
	        	return value;
	        }},
	        {title : '套数', dataIndex : 'setNumber',editor : {xtype : 'number'}, renderer : function(value,obj,index){
	        	countCargoFreight(obj, index);
	        	return value;
	        }},
	        {title : '重量(t)', dataIndex : 'totalWeight',editor : {xtype : 'number'}, renderer : function(value,obj,index){
	        	countCargoFreight(obj, index);
	        	return value;
	        }},
	        {title : '体积(m³)', dataIndex : 'totalVolume',editor : {xtype : 'number'}, renderer : function(value,obj,index){
	        	countCargoFreight(obj, index);
	        	return value;
	        }},
	        {title : '计费方式',dataIndex : 'countCostMode', editor : {xtype :'select',items : countCostMode, rules : {required : true}},
	        	renderer : Grid.Format.enumRenderer(countCostMode)
	        },
	        {title : '单价', dataIndex : 'price',editor : {xtype : 'number'}, renderer : function(value,obj,index){
	        	countCargoFreight(obj, index);
	        	return value;
	        }}
        ];
        var editing = new Grid.Plugins.CellEditing({
      		triggerSelected : false //触发编辑的时候不选中行
    	});
      	store = new Store({
        	data : data,
        	autoLoad:true
      	});
      	grid = new Grid.Grid({
    		width : $('#editCargoGrid').width(),
        	render:'#editCargoGrid',
        	columns : columns,
        	forceFit : true,
        	tbar:{ //添加、删除
            	items : [{
              		btnCls : 'button button-small',
              		text : '<i class="icon-plus"></i>添加',
              		listeners : {
                		'click' : addFunction
              		}
            	}, {
           			btnCls : 'button button-small',
              		text : '<i class="icon-remove"></i>删除',
              		listeners : {
                		'click' : delFunction
              		}
            	}]
      		},
        	plugins : [editing,Grid.Plugins.CheckSelection],
        	store : store
       	});
    	grid.render();

    	//添加记录
    	function addFunction(){
      		store.addAt({});
    	}
    	//删除选中的记录
    	function delFunction(){
      		var selections = grid.getSelection();
      		if(grid.getItems().length == 1 || grid.getItems().length == selections.length){
      			return;
      		}
      		store.remove(selections);
    	}
  	});
}

// 加载BUI日历插件
function loadBuiCalendar(){
	BUI.use('bui/calendar',function(Calendar){
    	var datepicker = new Calendar.DatePicker({
        	trigger:'.calendar',
        	autoRender : true
        });
	});
}

// 验证 货物编辑表格数据
function validEditCargoGrid(){
	var items = grid.getItems();
	for(var i = 0; i < items.length; i++){
		if(isNull(items[i].name) || isNull(items[i].countCostMode.toString())){
			BUI.Message.Alert('货物信息填写不正确','warning');
			return false; 
		}
	}
	return true;
}

//加载行政区划信息
function loadXzqhInfo(pid, num){
	$.ajax({
		type : 'post',
		url : contextPath + '/xzqh/getxzqhinfo.shtml',
		data : { 'pid' : pid },
		success : function(result){
			var html = '<option value="">请选择</option>'
			for(var i = 0; i < result.length; i++){
				html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			if(num == 1){
				$('#targetProvince').html(html);
			}else if(num == 2){
				$('#targetCity').html(html);
			}else if(num == 3){
				$('#targetCounty').html(html);
			}
		}
	});
}

//加载发站网点信息
function loadStartOutletsInfo(){
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getcurrentoutletsinfo.shtml',
		success : function(result){
			$('#startOutlets').val(result.id);
			$('#startOutletsText').text(result.name);
			$('#startOutletsName').val(result.name);
		}
	});
}

// 加载目的网点信息
function loadtargetOutletsInfo(targetOutlets){
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getoutletsinfo.shtml',
		success : function(result){
			var html = '<option value="">请选择</option>'
			for(var i = 0; i < result.length; i++){
				html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$('#targetOutlets').html(html);
			if(targetOutlets != null && targetOutlets != '' && typeof(targetOutlets) != 'undefined'){
				$('#targetOutlets').val(targetOutlets);
			}
		}
	});
}

//回显行政区划信息
function showXzqhInfo(provinceId, cityId, countyId){
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getxzqhinfo.shtml',
		async: false,
		data : { 'pid' : '100000' },
		success : function(result){
			var html = ''
			for(var i = 0; i < result.length; i++){
				html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$('#targetProvince').html(html);
			$('#targetProvince').val(provinceId);
		}
	});
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getxzqhinfo.shtml',
		async: false,
		data : { 'pid' : provinceId },
		success : function(result){
			var html = ''
			for(var i = 0; i < result.length; i++){
				html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$('#targetCity').html(html);
			$('#targetCity').val(cityId);
		}
	});
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getxzqhinfo.shtml',
		async: false,
		data : { 'pid' : cityId },
		success : function(result){
			var html = ''
			for(var i = 0; i < result.length; i++){
				html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			$('#targetCounty').html(html);
			$('#targetCounty').val(countyId);
		}
	});
}

// 获取包装类型
function getPackageType(){
	var selectData = '{';
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getpackagetype.shtml',
		async : false,
		success : function(result){
			for(var i = 0; i < result.length; i++){
				var value = result[i].id;
				var text = result[i].name;
				if(i == result.length - 1){
					selectData += '"' + value + '" : "' + text + '"';
				}else{
					selectData += '"' + value + '" : "' + text + '", ';
				}
			}
		}
	});
	selectData += '}';
	return JSON.parse(selectData);
}

//获取计费方式
function getCountCostMode(){
	var selectData = '{';
	$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getcountcostmode.shtml',
		async : false,
		success : function(result){
			for(var i = 0; i < result.length; i++){
				var value = result[i].value;
				var text = result[i].name;
				if(i == result.length - 1){
					selectData += '"' + value + '" : "' + text + '"';
				}else{
					selectData += '"' + value + '" : "' + text + '", ';
				}
			}
		}
	});
	selectData += '}';
	return JSON.parse(selectData);
}

// 加载付款方式
function loadPayMethod(payMethod){
	/*$.ajax({
		type : 'post',
		url : contextPath + '/deliverycargo/getpaymode.shtml',
		success : function(result){
			var html = ''
			for(var i = 0; i < result.length; i++){
				html += '<option value="'+result[i].value+'">'+result[i].name+'</option>';
			}
			$('#payMethod').html(html);
			if(payMethod != null && payMethod != '' && typeof(payMethod) != 'undefined'){
				$('#payMethod').val(payMethod);
			}
		}
	});*/
}

function loadForm(){
	var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
	BUI.use('bui/form',function(Form){
		numberInfoForm = new Form.Form({
			srcNode : '#form_number_info',
			//errorTpl : errorTpl
		}).render();
		driverInfoForm = new Form.Form({
			srcNode : '#form_driver_info',
			//errorTpl : errorTpl
		}).render();
		baseInfoForm = new Form.Form({
			srcNode : '#form_base_info',
			//errorTpl : errorTpl
		}).render();
		costInfoForm = new Form.Form({
			srcNode : '#form_cost_info',
			errorTpl : errorTpl
		}).render();
		Form.Rules.add({
			name : 'mobile', // 规则名称
			msg : ' ',// 默认显示的错误信息
			validator : function(value, baseValue, formatMsg) { // 验证函数，验证值、基准值、格式化后的错误信息
				var regexp = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
				if (value && !regexp.test(value)) {
					return formatMsg;
				}
			}
		});
		Form.Rules.add({
			name : 'validInt', // 规则名称
			msg : ' ',// 默认显示的错误信息
			validator : function(value, baseValue, formatMsg) { // 验证函数，验证值、基准值、格式化后的错误信息
				var pattern = /^[1-9]*[1-9][0-9]*$/; //匹配非负整数
			    if (value && !pattern.test(value)) {
			    	return formatMsg;
			    }
			}
		});
		//验证车牌号是否正确
    	Form.Rules.add({
      		name : 'validVechileNumber',  //规则名称
            msg : ' ',//默认显示的错误信息
            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
            	var vechileNumberReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/ ;//验证车牌号
            	if(value && !vechileNumberReg.test(value)){
            		return formatMsg;
            	}
            }
        });
	})
}


// 计算货物运费信息
function countCargoFreight(obj, index){
	var total = 0;
	var items = grid.getItems();
	for(var i = 0; i < items.length; i++){
		var row = items[i];
		var number = isNull(row.number) ? 0 : row.number;
		var setNumber = isNull(row.setNumber) ? 0 : row.setNumber;
		var weight = isNull(row.totalWeight) ? 0 : row.totalWeight;
		var volume = isNull(row.totalVolume) ? 0 : row.totalVolume;
		var price = isNull(row.price) ? 0 : row.price;

		var rowTotalPrice = 0;
		
		var countCostMode = row.countCostMode;
		if(countCostMode == 0){ // 按重量
			rowTotalPrice = weight * price;
		}else if(countCostMode == 1){ // 按体积
			rowTotalPrice = volume * price;
		}else if(countCostMode == 2){ // 按件数
			rowTotalPrice = number * price;
		}else if(countCostMode == 3){ // 按套数
			rowTotalPrice = setNumber * price;
		}
		total += rowTotalPrice;
	}
	$('#freight').val(total.toFixed(2))
	countTotalMoney();
}

//计算代收货款 手续费
function countAgencyFundPoundage(value){
	var collectionDeliveryRatio = $('#collectionDeliveryRatio').val();
	if(isNull(value) || isNull(collectionDeliveryRatio)){
		$('#agencyFundPoundage').val(0);
	}else{
		var agencyFundPoundage = value * collectionDeliveryRatio / 100;
		$('#agencyFundPoundage').val(agencyFundPoundage.toFixed(2));
	}
	countTotalMoney();
}

//计算保险费
function countInsurance(value){
	var lineInsuranceRatio = $('#lineInsuranceRatio').val();
	if(isNull(value) || isNull(collectionDeliveryRatio)){
		$('#insurance').val(0);
	}else{
		var insurance = value * lineInsuranceRatio / 100;
		$('#insurance').val(insurance.toFixed(2));
	}
	countTotalMoney();
}

//计算总金额
function countTotalMoney(){
	var agencyFundPoundage = getNotNullData($('#agencyFundPoundage').val()); //代收货款手续费
	var insurance = getNotNullData($('#insurance').val()); // 保险费
	var takeCargoCost = getNotNullData($('#takeCargoCost').val()); // 提货费
	var loadUnloadCost = getNotNullData($('#loadUnloadCost').val()); // 装卸费
	var transferCost = getNotNullData($('#transferCost').val()); // 中转费
	var otherCost = getNotNullData($('#otherCost').val()); // 其他费用
	var freight = getNotNullData($('#freight').val()); //基础运费
	var advanceCost = getNotNullData($('#advanceCost').val()); // 垫付费用
	// 计算自定义运单费用
	var customCostTotal = 0;
	for(var i = 0; i < customCostIds.length; i++){
		customCostTotal += new Number(getNotNullData($('#'+customCostIds[i]).val()));
	}
	var total = new Number(agencyFundPoundage) + new Number(insurance) + new Number(takeCargoCost) + new Number(loadUnloadCost) + new Number(transferCost) + new Number(otherCost) + new Number(freight) + new Number(advanceCost) + customCostTotal;
	$('#total').val(total.toFixed(2));
}

function getNotNullData(value){
	if(value == null || value == '' || typeof(value) == 'undefined'){
		return 0;
	}else{
		return value;
	}
}

function isNull(v){
	if(v == null || v == '' || typeof(v) == 'undefined'){
		return true;
	}
	return false;
}

// 设置网点名称
function setOutletsName(obj, id){
	$('#'+id).val($(obj).find("option:selected").text());
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/outlets/getoutlets.shtml',
		data : { 'outletsId' : obj.value },
		success : function(result){
			var data = result.data;
			showXzqhInfo(data.province, data.city, data.county);
			baseInfoForm.valid();
		}
	});
}

// 获取自定义运单费用信息
function getCustomCost(){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/waybill/getcustomcost.shtml',
		success : function(result){
			loadCustomCost(result);
		}
	});
}

//加载自定义运费费用
function loadCustomCost(result){
	if(result != null && result != ''){
		var html = '<div class="row-fluid">';
		for(var i = 0; i < result.length; i++){
			html += '<div class="control-group span6">';
			html += '<label class="control-label">'+result[i].name+'</label>';
			html += '<div class="controls">';
			var money = typeof(result[i].money) == 'undefined' || result[i].money == null ? '' : result[i].money;
			html += '<input type="text" class="input-normal" id="'+result[i].name+'" value="'+money+'" onchange="countTotalMoney()" data-rules="{number:true}">';
			customCostIds.push(result[i].name);
			html += '</div></div>';
			if((i + 1) % 4 == 0){
				html += '</div>';
			}
			if((i + 1) % 4 == 0 && i != result.length - 1){
				html += '<div class="row-fluid">';
			}
		}
		if(result.length % 4 != 0){
			html += '<div>';
		}
	}
	$('#customCostDiv').html(html);
}

//构建运单费用信息 数据
function buildCostData(){
	var wayBillCostData = [];
	for(var i = 0; i < customCostIds.length; i++){
		var wayBillCost = new Object();
		wayBillCost.name = customCostIds[i];
		wayBillCost.money = $('#'+customCostIds[i]).val();
		wayBillCostData.push(wayBillCost);
	}
	return wayBillCostData;
}

//选择不同的付款方式 展示不同的效果
function changePayMethod(value){
	if(value == 1){
		// 到付
		$('#advancePaymentDiv').show();
		$('#multi-pay').hide();
	}else if(value == 6){
		// 多笔付
		$('#multi-pay').show();
		$('#advancePaymentDiv').hide();
		$('#advanceCost').val('');
	}else{
		$('#advancePaymentDiv').hide();
		$('#multi-pay').hide();
		$('#advanceCost').val('');
		countTotalMoney();
	}
}