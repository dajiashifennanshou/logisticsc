$(document).ready(function(){
	$('#myTab a:first').tab('show');//初始化显示哪个tab 
    $('#myTab a').click(function (e) {
      e.preventDefault();//阻止a链接的跳转行为 
      $(this).tab('show');//显示当前选中的链接及关联的content 
    })
    //支付
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
	
	//退款
	$("#refundStartTime").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true 
	});
	$("#refundEndTime").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true 
	});
	getSelectRefund();
	//
	$("#posStartTime").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true 
	});
	$("#posEndTime").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true 
	});
	getSelectPos();
})
//pos机收款记录
function getSelectPos(){
	pageindex = $("#posDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var condition = $("#posCondition").val();
	var onlineStartTimes = $("#posStartTime").val();
	var onlineEndTimes = $("#posEndTime").val();
	var posStatus = $("#posStatus").val();
	var Conditions = {
			"orderNumber" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"statuss":posStatus,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/personalCenter/getSelectPos.shtml",
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
					$("#posList").html(rechargeRecord);
					$("#posPage").html("");
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					
					if(date[i].statusys == 0){
						var status ="完结";
					}else if(date[i].statusys == 1){
						var status="撤销";
					}
					body+="<tr>"
						+"<td>"+date[i].orderNumber+"</td>"
						+"<td>"+date[i].posMoney+"</td>"
						+"<td>"+date[i].companyName+"</td>"
						+"<td>"+date[i].outletsName+"</td>"
						+"<td>"+date[i].tmsLoginName+"</td>"
						+"<td>"+status+"</td>"
						+"<td>"+formartDate(date[i].operateTime)+"</td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				$("#posList").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='posDiv' class='page'></div></div>";
				$("#posPage").html(foot);
				setPage(document.getElementById("posDiv"),page.params.totalPage,page.pageIndex,getSelectPos);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#posList").html(rechargeRecord);
			}
		})
}
//退款记录
function getSelectRefund(){
	pageindex = $("#refundDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var orderType = $("#refundOrderType").val();
	var refundType = $("#refundType").val();
	var condition = $("#refundCondition").val();
	var onlineStartTimes = $("#refundStartTime").val();
	var onlineEndTimes = $("#refundEndTime").val();
	var Conditions = {
			"condition" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"orderTypes":orderType,
			"refundTypes":refundType,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/personalCenter/getSelectRefund.shtml",
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
					$("#refundList").html(rechargeRecord);
					$("#refundPage").html("");
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					if(date[i].orderType == 0){
						var orderType ='保险费';
					}else if(date[i].orderType ==1){
						var orderType ='预约费';
					}else if(date[i].orderType ==2){
						var orderType ='运费';
					}
					if(date[i].refundType == 0){
						var refundType ="用户退款";
					}else if(date[i].refundType == 1){
						var refundType ="物流商退款";
					}else if(date[i].refundType == 2){
						var refundType ="系统退款";
					}
					body+="<tr>"
						+"<td>"+date[i].requestid+"</td>"
						+"<td>"+date[i].orderNumber+"</td>"
						+"<td>"+orderType+"</td>"
						+"<td>"+refundType+"</td>"
						+"<td>"+date[i].amount+"</td>"
						+"<td>"+formartDate(date[i].time)+"</td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				$("#refundList").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='refundDiv' class='page'></div></div>";
				$("#refundPage").html(foot);
				setPage(document.getElementById("refundDiv"),page.params.totalPage,page.pageIndex,getSelectRefund);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#refundList").html(rechargeRecord);
			}
		})
}
//消费记录
function getBySelectedPayment(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var orderType = $("#orderType").val();
	var condition = $("#condition").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	var Conditions = {
			"condition" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"orderTypes":orderType,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/personalCenter/getBySelectedPayment.shtml",
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