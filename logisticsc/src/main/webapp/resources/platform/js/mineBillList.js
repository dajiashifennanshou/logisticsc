$(document).ready(function(){
	getListorderBill();
	$("#onlineStartTimes").datetimepicker({
		/*format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true //选择日期后自动关闭
*/		
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true 
	});
	$("#onlineEndTimes").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true 
	});
	$("#zhifu").click(function(){
		return zhifuqueren();
	});
})
//时间戳转换
function formartDate(str){
	var d = new Date();
    d.setTime(str);
    var year = d.getFullYear();
    var month = d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
    var day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
    var hour = d.getHours();
    var minute = d.getMinutes();
    var second = d.getSeconds();
    return year + "-" + month  + "-" + day + " " + hour + ":" + minute + ":" + second;
}
function selectOrderBill(){
	getListorderBill();
}
function getListorderBill(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var orderNumber = $("#orderNumber").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	var billState = $("#billState").val();
	var Conditions = {
			"orderNumber" : orderNumber,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"state":billState,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getListorderBill.shtml",
			data:Conditions,
			type:'post',
			dataType:'json',
			success:function(dataResult){
				var date = dataResult.data.params.rows;
				var page = dataResult.data;
				var body="";
				if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
					body="<tr>"
						+"<td style='color:red;' colspan='16' >没有数据</td>";
						+"</tr>";
					var rechargeRecord=body;
					$("#getListorderBill").html(rechargeRecord);
					return false;
				}
				for (var i = 0; i < date.length; i++) {	
					var state = "";
					if(date[i].state != 0){
						state ="已完成";
					}else if(date[i].state == 0){
						state ="未完成";
					}
					if(null == date[i].wayBillNumber || '' == date[i].wayBillNumber || 'undefined' == date[i].wayBillNumber || date[i].wayBillNumber.length <= 0){
						var wayBillNumber ="";
					}else{
						var wayBillNumber = date[i].wayBillNumber;
					}
					if(null == date[i].prepaidCost || '' ==  date[i].prepaidCost || 'undefined' ==  date[i].prepaidCost ||  date[i].prepaidCost <= 0){
						date[i].prepaidCost = 0;
					}else{
						date[i].prepaidCost;
					}
					var unpaid = "";
					var paid="0";
					var btton3="";
					if(date[i].isPayment == 0){
						unpaid = "0";
						paid =date[i].totalCost;
						btton3="&nbsp;&nbsp;<button type='button' class='btn btn-inverse'>支付</button>"
					}else if(date[i].isPayment == 1){
						paid = date[i].estimateTakeCargoCost;
						unpaid = date[i].totalCost-date[i].estimateTakeCargoCost;
						btton3="&nbsp;&nbsp;<button type='button' class='btn btn-success' style='margin-top:5px;' onclick='zhifu("+unpaid+","+"\""+date[i].orderNumber+"\")'>支付</button>";
					}
					body+="<tr class='tr_css' align='center'>"
						/*+"<td><input value='"+date[i].id+"' name='id' type=checkbox></td>"*/
						+"<td>"+date[i].orderNumber+"</td>"
						+"<td>"+wayBillNumber+"</td>"
						+"<td>"+state+"</td>"
						+"<td>"+formartDate(date[i].createTime)+"</td>"
						+"<td>"+date[i].companyName+"<br/>"+"从:"+date[i].startOutlets+"到:"+date[i].endOutlets+"</td>"
						+"<td>"+"￥"+date[i].totalCost+"</td>"
						+"<td>"+"￥"+paid+"</td>"
						+"<td>"+"￥"+unpaid+"</td>"
						+"<td>"+"<button type='button' class='btn btn-success' onclick='displayBill(\""+date[i].orderNumber+"\")'>查看</button>"+btton3+"</td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				$("#getListorderBill").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getListorderBill);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#getListorderBill").html(rechargeRecord);
			}
		})
}
function zhifu(totalCost,orderNumber) {
	$('body').css("overflow","hidden");
	$("#zhifujine").val(totalCost);
	$("#zhifuOrderNumber").val(orderNumber);
	$("#yunChongzhi").modal({show : true});
	$('body').css("overflow","scroll");
}
function zhifuqueren() {
	var yun_money = $("#zhifujine").val();
	var orderNumber = $("#zhifuOrderNumber"). val();
	$.ajax({
		url:"/logisticsc/orderCenter/doOrderMoney.shtml",
		data: {orderNumber:orderNumber},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#yunChongzhi").modal('hide');
				$("#successModalzhifu").modal('show');
				$("#successModalMsgszhifu").html(data.msg);
			}else{
				$("#yunChongzhi").modal('hide');
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html(data.msg);
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}
	})
}
function tiaozhuan() {
	window.location="/logisticsc/orderCenter/toMybill.shtml";
}
//导出我的账单
function exportMineBill(){
	var orderNumber = $("#orderNumber").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	var billState = $("#billState").val();
	var url="/logisticsc/orderCenter/exportMineBill.shtml?orderNumber="+orderNumber+"&startTime="+onlineStartTimes+"&endTime="+onlineEndTimes+"&state="+billState;
	location.href=url;
}
//显示账单详情
function displayBill(orderNumber){
	$.ajax({
		url:"/logisticsc/orderCenter/doMineBillInfo.shtml",
		data:{"orderNumber":orderNumber},
		type:'post',
		dataType:'json',
		success:function(dataResult){
			var data = dataResult.data;
			if(dataResult.result==true){
				$("#orderNumbers").text(data.orderNumber);
				if(null == data.estimateFreight || '' == data.estimateFreight || 'undefined' == data.estimateFreight || data.estimateFreight <= 0){
					$("#estimateFreight").text("0");
				}else{
					$("#estimateFreight").text(data.estimateFreight);
				}
				if(null == data.estimateTakeCargoCost || '' == data.estimateTakeCargoCost || 'undefined' == data.estimateTakeCargoCost || data.estimateTakeCargoCost <= 0){
					$("#estimateTakeCargoCost").text("0");
					$("#estimateTakeCargoCosts").text("0");
				}else{
					$("#estimateTakeCargoCost").text(data.estimateTakeCargoCost);
					$("#estimateTakeCargoCosts").text(data.estimateTakeCargoCost);
				}
				if(null == data.estimateSendCargoCost || '' == data.estimateSendCargoCost || 'undefined' == data.estimateSendCargoCost || data.estimateSendCargoCost <= 0){
					$("#estimateSendCargoCost").text("0");
				}else{
					$("#estimateSendCargoCost").text(data.estimateSendCargoCost);
				}if(null == data.estimateLoadCargoCost || '' == data.estimateLoadCargoCost || 'undefined' == data.estimateLoadCargoCost || data.estimateLoadCargoCost <= 0){
					$("#estimateLoadCargoCost").text("0");
				}else{
					$("#estimateLoadCargoCost").text(data.estimateLoadCargoCost);
				}if(null == data.estimateUnloadCargoCost || '' == data.estimateUnloadCargoCost || 'undefined' == data.estimateUnloadCargoCost || data.estimateUnloadCargoCost <= 0){
					$("#estimateUnloadCargoCost").text("0");
				}else{
					$("#estimateUnloadCargoCost").text(data.estimateUnloadCargoCost);
				}if(null == data.estimateTotalCost || '' == data.estimateTotalCost || 'undefined' == data.estimateTotalCost || data.estimateTotalCost <= 0){
					$("#estimateTotalCost").text("0");
				}else{
					$("#estimateTotalCost").text(data.estimateTotalCost);
				}
				/*$("#yufuweifu").text(data.estimateTotalCost-data.estimateTakeCargoCost);*/
	
				if(null == data.agencyFundPoundage || '' == data.agencyFundPoundage || 'undefined' == data.agencyFundPoundage || data.agencyFundPoundage <= 0){
					$("#agencyFundPoundages").text("0");
					$("#agencyFundPoundage").text("0");
				}else{
					$("#agencyFundPoundages").text(data.agencyFundPoundage);
					$("#agencyFundPoundage").text(data.agencyFundPoundage);
				}
				if(null == data.wayBillNumber || '' == data.wayBillNumber || 'undefined' == data.wayBillNumber || data.wayBillNumber <= 0){
					$("#wayBillNumber").text("");
				}else{
					$("#wayBillNumber").text(data.wayBillNumber);
				}
				if(null == data.freight || '' == data.freight || 'undefined' == data.freight || data.freight <= 0){
					$("#freight").text("0");
				}else{
					$("#freight").text(data.freight);
				}
				$("#consignorName").text(data.consignorName);
				//发货地址
				var consignorAddress = data.consignorProvince+"-"+data.consignorCity+"-"+data.consignorCounty;
				$("#consignorProvince").text(consignorAddress);
				
				$("#consignorPhoneNumber").text(data.consignorPhoneNumber);
				$("#consigneeName").text(data.consigneeName);
				//收货地址
				var consignorAddress =data.consigneeProvince+"-"+data.consigneeCity+"-"+data.consigneeCounty
				$("#consigneeProvince").text(consignorAddress);
				
				$("#consigneePhoneNumber").text(data.consigneePhoneNumber);
				$("#loginName").text(data.loginName);
				//状态
				var state = "";
				if(data.state != 0){
					state ="已完成";
				}else if(data.state == 0){
					state ="未完成";
				}
				$("#state").text(state);
				//时间
				$("#payDate").text(formartDate(data.createTime));
				if(null == data.takeCargoCost || '' == data.takeCargoCost || 'undefined' == data.takeCargoCost || data.takeCargoCost <= 0){
					$("#takeCargoCost").text("0");
				}else{
					$("#takeCargoCost").text(data.takeCargoCost);
				}
				if(null == data.sendCargoCost || '' == data.sendCargoCost || 'undefined' == data.sendCargoCost || data.sendCargoCost <= 0){
					$("#sendCargoCosts").text("0");		
				}else{
					$("#sendCargoCosts").text(data.sendCargoCost);
				}
				if(null == data.loadCargoCost || '' == data.loadCargoCost || 'undefined' == data.loadCargoCost || data.loadCargoCost <= 0){
					$("#loadCargoCosts").text("0");
				}else{
					$("#loadCargoCosts").text(data.loadCargoCost);
				}
				if(null == data.unloadCargoCost || '' == data.unloadCargoCost || 'undefined' == data.unloadCargoCost || data.unloadCargoCost <= 0){
					$("#unloadCargoCosts").text("0");
				}else{
					$("#unloadCargoCosts").text(data.unloadCargoCost);
				}
				if(null == data.insurance || '' == data.insurance || 'undefined' == data.insurance || data.insurance <= 0){
					$("#insurance").text("0");
					$("#insurances").text("0");
				}else{
					$("#insurance").text(data.insurance);
					$("#insurances").text(data.insurance);
				}
				if(null == data.otherCost || '' == data.otherCost || 'undefined' == data.otherCost || data.otherCost <= 0){
					$("#otherCosts").text("0");
				}else{
					$("#otherCosts").text(data.otherCost);
				}
				if(null == data.totalCost || '' == data.totalCost || 'undefined' == data.totalCost || data.totalCost <= 0){
					$("#totalCost").text("0");
				}else{
					$("#totalCost").text(data.totalCost);
				}
				if(null == data.prepaidCost || '' == data.prepaidCost || 'undefined' == data.prepaidCost || data.prepaidCost <= 0){
					$("#prepaidCost").text("0");
				}else{
					$("#prepaidCost").text(data.prepaidCost);
				}
				//未付余额
				$("#unpaidCost").text(data.totalCost-data.prepaidCost);
				
				$("#selectBill").modal({show : true});
			}else{
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html(dataResult.msg);
			}
		},
		error:function(dataResult){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html(dataResult.msg);
		}
	})
}