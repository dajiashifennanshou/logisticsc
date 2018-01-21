$(document).ready(function(){
	 getEvaluation();
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
})
function selectEva(){
	 getEvaluation();
}
function getEvaluation(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var condition = $("#condition").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	var state = $("#state").val();
	var Conditions = {
			"condition" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"evarepstate":state,
			"pageIndex":pageindex
		};
	 $.ajax({
		url:"/logisticsc/orderCenter/getEvaluation.shtml",
			data:Conditions,
			type:'post',
			dataType:'json',
			success:function(dataResult){
				var date = dataResult.data.params.rows;
				var page = dataResult.data;
				var body="";
				if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
					body+="<tr>"
						+"<td style='color:red;' colspan='16' >没有数据</td>";
						+"</tr>";
					var rechargeRecord=body;
					$("#getEvaluation").html(rechargeRecord);
					return false;
				}
				for (var i = 0; i < date.length; i++) {	
					var state ="";
					if(date[i].state == 1){
						state ="未回复";
					}else if(date[i].state == 2){
						state ="已回复"
					}
					if(null == date[i].wayBillNumber || '' == date[i].wayBillNumber || 'undefined' == date[i].wayBillNumber || date[i].wayBillNumber.length <= 0){
						var wayBillNumber ="";
					}else{
						var wayBillNumber = date[i].wayBillNumber;
					}
					body+="<tr>"
						+"<td>"+date[i].orderNumber+"</td>"
						+"<td>"+wayBillNumber+"</td>"
						+"<td>"+date[i].companyName+"</td>"
						+"<td>"+"从:"+date[i].startProvince+"-"+date[i].startCity+"-"+date[i].startCounty+"<br/>"+
						"到:"+date[i].endProvince+"-"+date[i].endCity+"-"+date[i].endCounty+"</td>"
						+"<td>"+date[i].evaluateContent+"</td>"
						+"<td>"+state+"</td>"
						+"<td>"+formartDate(date[i].evaluateTime)+"</td>"
						+"<td>"+"<button type='button' class='btn btn-warning' onclick='selectEvaluation("+date[i].evaId+")'>查看回复</button>"+"</td>"
						+"</tr>";
				}
				rechargeRecord=body;
				$("#getEvaluation").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getEvaluation);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
				+"</tr>";
				var rechargeRecord=body;
				$("#getEvaluation").html(rechargeRecord);
			}
		})
}
//查看回复
function selectEvaluation(id){
	 $.ajax({
		url:"/logisticsc/orderCenter/doGetEvaluationReply.shtml",
		data:{id:id},
		type:'post',
		dataType:'json',
		success:function(dataResult){
			if(dataResult.result == true){
				$("#orderNumbers").text(dataResult.data.evaluation.orderNumber);
				$("#wayBillNumber").text(dataResult.data.evaluation.wayBillNumber);
				/*$("#evaluateLevel").text();*/
				dengji(dataResult.data.evaluation.evaluateLevel);
				$("#evaluateTime").text(formartDate(dataResult.data.evaluation.evaluateTime));
				$("#evaluateContent").text(dataResult.data.evaluation.evaluateContent);
				$("#replyContent").text(dataResult.data.evaluationReply.replyContent);
				$("#replyTime").text(formartDate(dataResult.data.evaluationReply.replyTime));
				$("#replyPeople").text(dataResult.data.evaluationReply.replyPeople);
				$("#selectEvaluation").modal({show : true});
			}else{
				$("#promptModal").modal('show');
				$("#promptModalMsgs").html("评价占无回复");
			}
		},
		error:function(error){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统异常");
		}
	 })
}
function  dengji(dengji){
	if(dengji == 1)
	{
		$(".cont .nr .xx").each(function(){
			$(".star").css("width","28px");
		})
	}
	else if(dengji == 2)
	{
		$(".cont .nr .xx").each(function(){
			$(".star").css("width","56px");
		})
	}
	else if(dengji == 3){
		
		$(".cont .nr .xx").each(function(){
			$(".star").css("width","84px");
		})
	}
	else if(dengji==4){
		$(".cont .nr .xx").each(function(){
			$(".star").css("width","112px");
		})
		
	}
	else {
		$(".cont .nr .xx").each(function(){
			$(".star").css("width","140px");
		})
	}
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