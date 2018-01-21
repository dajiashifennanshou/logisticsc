// 全局变量
var contextPath = $('#contextPath').val();
var grid, store, departureSuccessDialog;

$(function(){
	loadCalendar();
	loadDepartListStatus();
	loadDepartList();
});

//验证用户类型
function validateUserType(){
	var userType = $('#userType').val();
	if(userType == 0 || userType == 1){
		BUI.Message.Alert('请使用网点账号登录','warning');
		return false;
	}
	return true;
}

// 加载运单列表
function loadDepartList(){
	BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar' ], function(Grid, Data,
			Toolbar) {
		var Grid = Grid;
		var Store = Data.Store;
		var columns = [ {
			title : '发车时间',
			elCls : 'center',
			width : 150,
			dataIndex : 'operateTime',
			renderer:BUI.Grid.Format.datetimeRenderer
		}, {
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
			title : '应付运费',
			elCls : 'center',
			dataIndex : 'shouldPayDriverCost'
		}, {
			title : '现付',
			elCls : 'center',
			dataIndex : 'nowPayDriverCost'
		}, {
			title : '到付',
			elCls : 'center',
			dataIndex : 'arrivePayDriverCost'
		}, {
			title : '回付',
			elCls : 'center',
			dataIndex : 'backPayDriverCost'
		}, {
			title : '保险金额',
			elCls : 'center',
			dataIndex : 'insuranceMoney'
		}, {
			title : '操作员',
			elCls : 'center',
			dataIndex : 'operatePersonName'
		} ];
		
		store = new Store({
			url : contextPath + '/tms/depart/search.shtml',
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
                    text:'继续配载',
                    handler : function(){
                    	if(!validateUserType()){
                			return;
                		}
                    	var selection = grid.getSelection();
                		if(selection.length < 1){
                			BUI.Message.Alert('请选择');
                			return;
                		}else if(selection.length > 1){
                			BUI.Message.Alert('只能选择一条记录');
                			return;
                		}
            			var selected = selection[0];
                    	if(selected.status == 0){
                    		BUI.Message.Confirm('确定要继续配载码？',function(){
	                    		var departNumber = selected.departNumber;
	                        	window.location.href = contextPath + '/tms/waybill/tostowageworkbenchpage.shtml?departNumber='+departNumber;
                    		},'question');
                    	}else{
                    		BUI.Message.Alert('不允许此操作','error');
                    	}
                		
                    }
                }, {
                    btnCls : 'button button-normal',
                    text:'作废',
                    handler : abolishDepartList
                }, {
                    btnCls : 'button button-normal',
                    text:'查看详情',
                    handler : showDepartListDetail
                }, {
                    btnCls : 'button button-normal',
                    text:'打印',
                    handler : printDepartList
                }, {
                    btnCls : 'button button-normal',
                    text:'发车出库',
                    handler : departure
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

// 加载单车单状态 下拉框
function loadDepartListStatus(){
	$.ajax({
		type : 'post',
		url : contextPath + '/tms/depart/getdepartliststatus.shtml',
		success : function(result){
			var html = "<option value=''>全部</option>";
			if(result != null){
				result = eval("("+result+")");
				for(var i = 0; i < result.length; i++){
					html += "<option value='"+result[i].value+"'>"+result[i].name+"</option>";
				}
			}
			document.getElementById('departStatus').innerHTML = html;
		}
	});
}
// 发车出库
function departure(){
	if(!validateUserType()){
		return;
	}
	var selection = grid.getSelection();
	var length = selection.length;
	if(length < 1){
		BUI.Message.Alert('请选择');
		return;
	}
	var departLists = [];
	for(var i = 0; i < selection.length; i++){
		if(selection[i].status != 0){
			BUI.Message.Alert('仅配载中的发车单允许发车出库','error');
			return;
		}
		var departList = new Object();
		departList.departNumber = selection[i].departNumber;
		departList.wayBillNumbers = selection[i].wayBillNumbers;
		departLists.push(departList);
	}
	BUI.Message.Confirm('确认要出库吗？',function(){
		$.ajax({
			type : 'post',
			url : contextPath + '/tms/depart/updatedeparture.shtml',
			data : { 'departLists' : JSON.stringify(departLists) },
			success : function(result){
				if(result > 0){
					showDepartureSuccessMessage();
					search();
				}else{
					BUI.Message.Alert('操作失败','error');
				}
			}
		});
	},'question');
}

// 作废 发车单
function abolishDepartList(){
	if(!validateUserType()){
		return;
	}
	var selection = grid.getSelection();
	if(selection.length < 1){
		BUI.Message.Alert('请选择');
		return;
	}
	var departNumbers = [];
	for(var i = 0; i < selection.length; i++){
		if(selection[i].status != 0){
			BUI.Message.Alert('只有配载中的发车能被作废','warning');
			return;
		}
		departNumbers.push(selection[i].departNumber);
	}
	BUI.Message.Confirm('确定要作废码？',function(){
		$.ajax({
			type : 'post',
			url : contextPath + '/tms/depart/abolish.shtml',
			data : { 'departNumbers' : JSON.stringify(departNumbers) },
			success : function(result){
				if(result == 1){
					BUI.Message.Alert('操作成功',function(){search();},'success');
				}else{
					BUI.Message.Alert('操作失败','error');
				}
			}
		});
	},'question');
}

function showDepartListDetail(){
	var selection = grid.getSelection();
	if(selection.length == 0){
		BUI.Message.Alert('请选择','warning');
		return;
	}else if(selection.length > 1){
		BUI.Message.Alert('只能选择一个发车单','warning');
		return;
	}
	window.location.href = contextPath + '/tms/depart/todepartlistdetail.shtml?departNumber='+selection[0].departNumber;
}

//跳转到异常登记页面
function toAbnormal(){
	if(!validateUserType()){
		return;
	}
	window.location.href=contextPath + "/tms/abnormal/addabnormal.shtml";
}

// 发车出库成功，提示框
function showDepartureSuccessMessage(){
	BUI.use('bui/overlay',function(Overlay){
		BUI.Message.Show({
			msg : '发车出库成功，请确定是否购买保险',
			icon : 'question',
			buttons : [{
				text : '是',
				elCls : 'button button-primary',
				handler : function(){
					this.close();
					window.open('/logisticsc/insurance/toInsurance.shtml');
				}
			}, {
				text : '否',
				elCls : 'button',
				handler : function(){
					this.close();
				}
			}]
		});
	});
}

// 打印发车清单
function printDepartList(){
	var selection = grid.getSelection();
	if(selection.length != 1){
		BUI.Message.Alert('请选择一个发车单','warning');
		return;
	}
	window.open(contextPath + '/tms/depart/todepartlistpreview.shtml?departNumber='+selection[0].departNumber);
}