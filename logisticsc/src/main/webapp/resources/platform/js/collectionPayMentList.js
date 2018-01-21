$(document).ready(function(){
	 getCollectionPayment();
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
});
function selectCollectionPayment(){
	getCollectionPayment();
}
function getCollectionPayment(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var condition = $("#condition").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	var paynentState = $("#paynentState").val();
	var Conditions = {
			"condition" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"state":paynentState,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getCollectionPayment.shtml",
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
					$("#getCollectionPayment").html(rechargeRecord);
					return false;
				}
				
				for (var i = 0; i < date.length; i++) {	
					var state ="";
					if(date[i].state == 0){
						state ="未完成";
					}else if(date[i].state == 1){
						state ="已完成"
					}
					if(null == date[i].wayBillNumber || '' == date[i].wayBillNumber || 'undefined' == date[i].wayBillNumber || date[i].wayBillNumber.length <= 0){
						var wayBillNumber ="";
					}else{
						var wayBillNumber = date[i].wayBillNumber;
					}
					body+="<tr>"
						+"<td>"+date[i].orderNumber+"</td>"
						+"<td>"+wayBillNumber+"</td>"
						+"<td>"+state+"</td>"
						+"<td>"+date[i].consignorName+"</td>"
						+"<td>"+date[i].consigneeName+"</td>"
						+"<td>"+formartDate(date[i].operateTime)+"</td>"
						+"<td>"+"￥"+date[i].agencyFund+"</td>"
						+"<td>"+"￥"+date[i].receivedFund+"</td>"
						+"<td>"+"￥"+date[i].uncollectedFund+"</td>"
						+"</tr>";
				}
				rechargeRecord=body;
				$("#getCollectionPayment").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getCollectionPayment);
				
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
				+"</tr>";
				var rechargeRecord=body;
				$("#getCollectionPayment").html(rechargeRecord);
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