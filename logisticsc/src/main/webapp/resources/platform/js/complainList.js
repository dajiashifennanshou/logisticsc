var manyidu=0; 
$(document).ready(function(){
	 getComplain(); 
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
		$("#radio-btn1 input").each(function(){
			$(this).click(function(){
				var t=$(this).attr("value");
				manyidu=t;
			})
		})
})
function selectComp(){
	 getComplain();
 }
function getComplain(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var condition = $("#condition").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	var complainState = $("#complainState").val();
	var Conditions = {
			"condition" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"state":complainState,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getComplain.shtml",
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
					$("#getComplain").html(rechargeRecord);
					return false;
				}
				for (var i = 0; i < date.length; i++) {	
					var state ="";
					if(date[i].state == 1){
						state ="未回复";
					}else if(date[i].state == 2){
						state ="已回复"
					}else if(date[i].state == 3){
						state ="已反馈"
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
						+"<td>"+date[i].complaintContent.substring(0,8)+"......"+"</td>"
						+"<td>"+state+"</td>"
						+"<td>"+formartDate(date[i].complaintTime)+"</td>"
						+"<td>"+"<button type='button' class='btn btn-warning' onclick='selectComplain("+date[i].complainId+")'>查看回复</button>"+"</td>"
						+"</tr>";
				}
				rechargeRecord=body;
				$("#getComplain").html(rechargeRecord);
				$("#getCollectionPayment").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getComplain);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
				+"</tr>";
				var rechargeRecord=body;
				$("#getComplain").html(rechargeRecord);
			}
		})
} 
//修改投诉满意度
function updateComplain(){
	var data = {
			"id" :$("#ids").val(),
			"complaintId":$("#complaintId").val(),
			"handleSatisfiedDegree":manyidu
		};
	$.ajax({
		url:"/logisticsc/orderCenter/doUpdateComplainHandle.shtml",
		data:data,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#selectComplain").modal("hide");
				$("#successModal").modal('show');
				$("#successModalMsgs").html(data.msg);
			}else{
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html(data.msg);
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}
	})
}
//查看投诉回复
function selectComplain(complainId){
	$.ajax({
		url:"/logisticsc/orderCenter/doGetComplainHandle.shtml",
		data:{complaintId:complainId},
		type:'post',
		dataType:'json',
		success:function(dataResult){
			if(dataResult.result == true){
				complain = dataResult.data.complain;
				comlainHandle = dataResult.data.comlainHandle;
				if(comlainHandle.handleSatisfiedDegree == null){
					$('body').css("overflow","hidden")
					if(null == complain.wayBillNumber || '' == complain.wayBillNumber || 'undefined' == complain.wayBillNumber || complain.wayBillNumber.length <= 0){
						$("#wayBillNumbers").text("");
					}else{
						$("#wayBillNumbers").text(complain.wayBillNumber);
					}
					$("#orderNumbers").text(complain.orderNumber);
					
					$("#complaintTimes").text(formartDate(complain.complaintTime));
					$("#complaintContents").text(complain.complaintContent);
					$("#handleTimes").text(formartDate(comlainHandle.handleTime));
					$("#handlePeoples").text(comlainHandle.handlePeople);
					$("#handleContents").text(comlainHandle.handleContent);
					$("#handleSatisfiedDegrees").val(comlainHandle.handleSatisfiedDegree);
					$("#ids").val(comlainHandle.id);
					$("#complaintId").val(comlainHandle.complaintId);
					$("#selectComplain").modal({show : true});
					$('body').css("overflow","scroll");
				}else{
					$('body').css("overflow","hidden")
					$("#orderNumber").text(complain.orderNumber);
					if(null == complain.wayBillNumber || '' == complain.wayBillNumber || 'undefined' == complain.wayBillNumber || complain.wayBillNumber.length <= 0){
						$("#wayBillNumber").text("");
					}else{
						$("#wayBillNumber").text(complain.wayBillNumber);
					}
					$("#complaintTime").text(formartDate(complain.complaintTime));
					$("#complaintContent").text(complain.complaintContent);
					$("#handleTime").text(formartDate(comlainHandle.handleTime));
					$("#handlePeople").text(comlainHandle.handlePeople);
					$("#handleContent").text(comlainHandle.handleContent);
					if(comlainHandle.handleSatisfiedDegree  == 1){
						$("#sex1").attr("checked",true);
					}else if(comlainHandle.handleSatisfiedDegree  == 2){
						$("#sex2").attr("checked",true);
					}else if(comlainHandle.handleSatisfiedDegree  == 3){
						$("#sex3").attr("checked",true);
					}else if(comlainHandle.handleSatisfiedDegree  == 4){
						$("#sex4").attr("checked",true);
					}else if(comlainHandle.handleSatisfiedDegree  == 5){
						$("#sex5").attr("checked",true);
					}
					$("#handleSatisfiedTime").text(formartDate(comlainHandle.handleTime));
					$("#selectComplainExhibition").modal({show : true});
					$('body').css("overflow","scroll");
				}
				
			}else{
				$("#promptModal").modal('show');
				$("#promptModalMsgs").html("此投诉占无回复!");
			}
		},
		error:function(error){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
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