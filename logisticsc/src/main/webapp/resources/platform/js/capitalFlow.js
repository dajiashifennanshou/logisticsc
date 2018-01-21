$(document).ready(function(){
	$("#onlineStartTimes").datetimepicker({
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
	getBySelectedPayment();
});
//消费记录
function getBySelectedPayment(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var condition = $("#condition").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	var Conditions = {
			"condition" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"orderTypes":2,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getBySelectedForm.shtml",
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
					$("#bankPayment").html(rechargeRecord);
					$("#pageList").html("");
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					if(date[i].payproducttype == 'SALES'){
						var payproducttype='网银';
					}
					if(date[i].orderType == 0){
						var orderType ='保险费';
					}else if(date[i].orderType ==1){
						var orderType ='预约费';
					}else if(date[i].orderType ==2){
						var orderType ='运费';
					}else if(date[i].orderType ==3){
						var orderType ='保证金';
					}
					body+="<tr>"
						+"<td>"+date[i].requestid+"</td>"
						+"<td>"+date[i].orderNumber+"</td>"
						+"<td>"+orderType+"</td>"
						+"<td>"+payproducttype+"</td>"
						+"<td>"+date[i].amount+"</td>"
						+"<td>"+formartDate(date[i].time)+"</td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				$("#bankPayment").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getBySelectedPayment);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#bankPayment").html(rechargeRecord);
			}
		})
}
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
//function getMineTransactionFlow() {
//	pageindex = $("#videoDiv").parent().find(".currentPage").val();
//	if(pageindex<1){
//		pageindex=1;
//	}
//	var condition = $("#condition").val();
//	var onlineStartTimes = $("#onlineStartTimes").val();
//	var onlineEndTimes = $("#onlineEndTimes").val();
//	var Conditions = {
//			"condition" : condition,
//			"startTime":onlineStartTimes,
//			"endTime":onlineEndTimes,
//			"pageIndex":pageindex
//		};
//	$.ajax({
//		url:"/logisticsc/orderCenter/getMineTransactionFlow.shtml",
//			data:Conditions,
//			type:'post',
//			dataType:'json',
//			success:function(dataResult){
//				var date = dataResult.data.params.rows;
//				var page = dataResult.data;
//				var body="";
//				if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
//					body+="<tr>"
//						+"<td style='color:red;' colspan='16' >没有数据</td>";
//						+"</tr>";
//					var rechargeRecord=body;
//					$("#getMineTransactionFlow").html(rechargeRecord);
//					return false;
//				}
//				for (var i = 0; i < date.length; i++) {	
//					body+="<tr>"
//						+"<td>"+date[i].orderNumber+"</td>"
//						+"<td>"+date[i].wayBillNumber+"</td>"
//						+"<td>"+"从:"+date[i].startProvince+"-"+date[i].startCity+"-"+date[i].startCounty+"<br/>"+
//						"到:"+date[i].endProvince+"-"+date[i].endCity+"-"+date[i].endCounty+"</td>"
//						+"<td>"+date[i].flowNumber+"</td>"
//						+"<td>"+date[i].transactionMoney+"</td>"
//						+"<td>"+formartDate(date[i].time)+"</td>"
//						+"<td>"+date[i].chargeUser+"</td>"
//						+"<td>"+date[i].payUser+"</td>"
//						+"</tr>";
//				}
//				rechargeRecord=body;
//				$("#getMineTransactionFlow").html(rechargeRecord);
//				var foot="<div style='margin-top: 10px; float: right;'>"+
//				"<input type='hidden' class='currentPage'/>"+
//				"<div id='videoDiv' class='page'></div></div>";
//				$("#pageList").html(foot);
//				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getMineTransactionFlow);
//			},
//			error:function(error){
//				body+="<tr>"
//					+"<td style='color:red;' colspan='16' >没有数据</td>";
//				+"</tr>";
//				var rechargeRecord=body;
//				$("#getMineTransactionFlow").html(rechargeRecord);
//			}
//		})
//}
