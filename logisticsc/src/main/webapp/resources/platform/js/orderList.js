var evaluateLevel =0;
$(function(){
	$(".cont .nr .xx").each(function(){
		$(this).mouseover(function(){
		})
		$(this).click(function(){
			$("#xingjiMsg").text("");
			$(".star").width($(this).attr("rate")*28);
			var t=$(this).attr("rate");
			evaluateLevel = t;
		})
	})
	$("#pingjia").click(function() {
		return evaluateContent() && xingji() && addEvaluate();
	});
	$("#evaluateContent").focus(function() {
		$("#evaluateContentMsg").text("");
	});
	$("#toushu").click(function() {
		return complaintContent() && addComplain();
	});
	$("#complaintContent").focus(function() {
		$("#complaintContentMsg").text("");
	});
	$("#zhifu").click(function(){
		return zhifuqueren();
	});
})
//评价内容
function evaluateContent() {
	var evaluateContent = $("#evaluateContent").val();
	if(evaluateContent == null || evaluateContent ==""){
		$("#evaluateContentMsg").text("评价内容不能为空");
		return false;
	}else if(evaluateContent.length < 5 || evaluateContent.length > 50){
		$("#evaluateContentMsg").text("评价内容字符5-50位！");
		return false;
	}
	return true;
}
//投诉
function complaintContent() {
	var complaintContent = $("#complaintContent").val();
	if(complaintContent == null || complaintContent ==""){
		$("#complaintContentMsg").text("投诉内容不能为空");
		return false;
	}else if(complaintContent.length < 5 || complaintContent.length > 50){
		$("#complaintContentMsg").text("投诉内容字符5-50位！");
		return false;
	}
	return true;
}
function xingji(){
	if(evaluateLevel == 0 ){
		$("#xingjiMsg").text("请选择评价星级！");
		return false;
	}
	return true;
}
//显示评价
function displayEvaluate(orderNumber){
	qingkongpingjia();
	var orderNumber = orderNumber;
	$('body').css("overflow","hidden")
	$("#orderNumber").val(orderNumber);
	$("#addEvaluate").modal({show : true});
	$('body').css("overflow","scroll");
}
function qingkongpingjia(){
	$("#evaluateContent").text("");
	$(".star").width("");
}
function qingkongtoushu() {
	$("#complaintContent").text("");
}
//增加评价
function addEvaluate(){
	var evaluateContent = $("#evaluateContent").val();
	var orderNumber =$("#orderNumber").val();
	var Conditions = {
			"orderNumber":orderNumber,
			"evaluateContent":evaluateContent,
			"evaluateLevel":evaluateLevel
		};
	$.ajax({
		url:"/logisticsc/orderCenter/doEvaluation.shtml",
		data:Conditions,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
			$("#addEvaluate").modal("hide");
			$("#successModal").modal('show');
			$("#successModalMsgs").html(data.msg);
			getMineOrder();
			}else{
				$("#addEvaluate").modal("hide");
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
//显示投诉
function dispLayComplain(orderNumber){
	qingkongtoushu();
	var orderNumber = orderNumber;
	$('body').css("overflow","hidden")
	$("#orderNumbers").val(orderNumber);
	$("#addComplain").modal({show : true});
	$('body').css("overflow","scroll");
}
//增加投诉
function addComplain(){
	var data = $("#add_complain").serialize();
	$.ajax({
		url:"/logisticsc/orderCenter/doComplain.shtml",
		data:data,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
			$("#addComplain").modal("hide");
			$("#successModal").modal('show');
			$("#successModalMsgs").html(data.msg);
			getMineOrder();
			}else{
				$("#addComplain").modal("hide");
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
$(document).ready(function(){
	 orderState();
	 getMineOrder();
	 isInsurance();
	 stateAlerady();
	 stateRefused();
	 finalPrice(); 
	 stateWait();
	 isDraft();
	 stateCancel();
	 statVoid();
	$("#onlineStartTimes").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true //选择日期后自动关闭 
	});
	$("#onlineEndTimes").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		minView: 2,
		autoclose:true 
	});
});
function selectMineOrder(){
	getMineOrder();
}
function caogao(){
	type="1"; 
	$("#orderSate").val("");
	$("#condition").val("");
	$("#onlineStartTimes").val("");
	$("#onlineEndTimes").val("");
	getMineOrder();
}
function yijiangzhong(){
	type="2"; 
	$("#orderSate").val("");
	$("#condition").val("");
	$("#onlineStartTimes").val("");
	$("#onlineEndTimes").val("");
	getMineOrder();
}
function yiquxiao(){
	type="3"; 
	$("#orderSate").val("");
	$("#condition").val("");
	$("#onlineStartTimes").val("");
	$("#onlineEndTimes").val("");
	getMineOrder();
}
function yizuofei(){
	type="4"; 
	$("#orderSate").val("");
	$("#condition").val("");
	$("#onlineStartTimes").val("");
	$("#onlineEndTimes").val("");
	getMineOrder();
}
function yijujue(){
	type="5"; 
	$("#orderSate").val("");
	$("#condition").val("");
	$("#onlineStartTimes").val("");
	$("#onlineEndTimes").val("");
	getMineOrder();
}
function yiqianshou(){
	type="6"; 
	$("#orderSate").val("");
	$("#condition").val("");
	$("#onlineStartTimes").val("");
	$("#onlineEndTimes").val("");
	getMineOrder();
}
function baoxian() {
	type="7"; 
	$("#orderSate").val("");
	$("#condition").val("");
	$("#onlineStartTimes").val("");
	$("#onlineEndTimes").val("");
	getMineOrder();
}
// 1草稿 2待付款3 待确认费用 4已拒绝 5已签收 6保险
var type=""; 
function getMineOrder(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var orderSate = $("#orderSate").val();
	var condition = $("#condition").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	if(type == 1){
		conditionType =1;
	}else if(type == 2){
		conditionType =2;
	}else if(type == 3){
		conditionType =3;
	}else if(type == 4){
		conditionType = 4;
	}else if(type == 5){
		conditionType =5;
	}else if(type == 6){
		conditionType =6;
	}else if(type == 7){
		conditionType =7;
	}else{
		conditionType = 0;
	}
	var Conditions = {
			"condition" : condition,
			"startTime":onlineStartTimes,
			"endTime":onlineEndTimes,
			"state":orderSate,
			"conditionType":conditionType,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getMineOrder.shtml",
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
					$("#getMineOrder").html(rechargeRecord);
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					var btton="";
					 if(date[i].isEvaluation == 1 || date[i].state != "已签收"){
						btton ="<button type='button' class='btn btn-inverse'>评价</button>"
					}else if(date[i].isEvaluation == 0 && date[i].state == "已签收"){
						btton ="<button type='button' class='btn btn-success' onclick='displayEvaluate(\""+date[i].orderNumber+"\")'>评价</button>"
					}
					var btton2="";
					if(date[i].isComplain == 1){
						btton2 ="&nbsp;&nbsp;<button type='button' class='btn btn-inverse'>投诉</button>"
					}else if(date[i].isComplain == 0){
						btton2 ="&nbsp;&nbsp;<button type='button' onclick='dispLayComplain(\""+date[i].orderNumber+"\")' class='btn btn-success'>投诉</button>"
					}
					if(null == date[i].finalPrice || '' == date[i].finalPrice || 'undefined' == date[i].finalPrice || date[i].finalPrice.length <= 0){
						var finalPrice =0;
					}else{
						var finalPrice = date[i].finalPrice;
					}

					if(null == date[i].wayBillNumber || '' == date[i].wayBillNumber || 'undefined' == date[i].wayBillNumber || date[i].wayBillNumber.length <= 0){
						var wayBillNumber ="";
					}else{
						var wayBillNumber = date[i].wayBillNumber;
					}
					var weifu ="";
					var btton3="";
					if(date[i].isDraft == 0 && date[i].isPayment == 1 && date[i].state =="预约" && date[i].sendCargoType ==1 && date[i].payType == 0){
						weifu=date[i].takeCargoCost;
						btton3="&nbsp;&nbsp;<button type='button' class='btn btn-success' style='margin-top:5px;' onclick='zhifu("+weifu.toFixed(2)+","+date[i].isDraft+","+"\""+date[i].orderNumber+"\")'>支付</button>";
					}else if(date[i].billPayment == 1 && date[i].isPayment == 0 && date[i].state =="议价"&& date[i].sendCargoType ==1 && date[i].payType == 0){
						weifu=new Number(date[i].totalCost)-new Number(date[i].takeCargoCost);
						btton3="&nbsp;&nbsp;<button type='button' class='btn btn-success' style='margin-top:5px;' onclick='zhifu("+weifu.toFixed(2)+","+date[i].isDraft+","+"\""+date[i].orderNumber+"\")'>支付</button>";
					}else if(date[i].billPayment == 1 && date[i].isPayment == 1 && date[i].state =="议价"&& date[i].sendCargoType ==0 && date[i].payType == 0){
						weifu=new Number(date[i].totalCost)-new Number(date[i].takeCargoCost);
						btton3="&nbsp;&nbsp;<button type='button' class='btn btn-success' style='margin-top:5px;' onclick='zhifu("+weifu.toFixed(2)+","+date[i].isDraft+","+"\""+date[i].orderNumber+"\")'>支付</button>";
					}else if(date[i].billPayment == 1 && date[i].isPayment == 1 && date[i].state =="议价" && date[i].sendCargoType ==0 && date[i].payType == 1){
						weifu=date[i].totalCost;
						btton3="&nbsp;&nbsp;<button type='button' class='btn btn-success' style='margin-top:5px;' onclick='zhifu("+weifu.toFixed(2)+","+date[i].isDraft+","+"\""+date[i].orderNumber+"\")'>支付</button>";
					}else{
						btton3="&nbsp;&nbsp;<button type='button' class='btn btn-inverse'>支付</button>"
						weifu=0;
					}
					var btton4="";
					 if(date[i].state =="预约" || date[i].state == "已受理" || date[i].state =="议价"){
						btton4="&nbsp;&nbsp;<button onclick='quxiaodingdan(\""+date[i].orderNumber+"\")' type='button'class='btn btn-success' style='margin-top:5px;'>取消</button>";
					}else{
						btton4="&nbsp;&nbsp;<button type='button'class='btn btn-inverse' style='margin-top:5px;'>取消</button>";
					}
					if(null == date[i].estimateTotal || '' == date[i].estimateTotal || 'undefined' == date[i].estimateTotal || date[i].estimateTotal.length <= 0){
						date[i].estimateTotal = 0;
					}
					body+="<tr class='tr_css' align='center'>"
						/*+"<td><input value='"+date[i].orderNumber+"' name='orderNumber' style='width:13px;height:13px;' type=checkbox></td>"*/
						+"<td>"+date[i].orderNumber+"<br/><a href='javascript:dingdangenzong("+date[i].orderNumber+");' style='color: red'>订单跟踪</a></td>"
						+"<td>"+wayBillNumber+"</td>"
						+"<td>"+date[i].companyName+"<br/>从:"+date[i].startProvince+"-"+date[i].startCity+"-"+date[i].startCounty+"<br/>到:"+date[i].endProvince+"-"+date[i].endCity+"-"+date[i].endCounty+"</td>"
						+"<td>"+date[i].estimateWeight+"&nbsp;T/"+date[i].estimateVolume+"&nbsp;m³"+"</td>"
						+"<td>"+"￥"+date[i].takeCargoCost+"</td>"
						+"<td>"+"￥"+date[i].estimateTotal+"</td>"
						+"<td>"+"￥"+date[i].totalCost+"</td>"
						+"<td>"+"￥"+weifu.toFixed(2)+"</td>"
						+"<td>"+formartDate(date[i].orderTime)+"</td>"
						+"<td>"+date[i].state+"</td>"
						+"<td style='text-align:left;padding-left:20px'>"+btton+btton2+btton3+btton4+"&nbsp;&nbsp;<button type='button'class='btn btn-success'onclick='showOrderDetail(\""+date[i].orderNumber+"\")' style='margin-top:5px;'>查看</button></td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				$("#getMineOrder").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getMineOrder);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#getMineOrder").html(rechargeRecord);
			}
		})
} 
function dingdangenzong(dingdanhao) {
	var name=new Array(); 
	var value=new Array();
	name[0]="condition";
	value[0]=dingdanhao;
	buildForm("/logisticsc/orderTracking/toorderorderTracking.shtml",name,value);
}
function wancheng() {
	$("#yunChongzhi").modal('hide');
	$("#querenModal").modal('show');
}
function shuaxin() {
	window.location.href="/logisticsc/orderCenter/toorderlistpage.shtml";
}
var orderNumbers ="";
function quxiaodingdan(orderNumber) {
	orderNumbers= orderNumber;
	$("#quxiaoModal").modal('show');
}
function back() {
	$("#quxiaoModal").modal('hide');
	$.ajax({
		url:"/logisticsc/orderCenter/doBack.shtml",
		data: {orderNumber:orderNumbers},
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			if(data.result==true){
				$("#successModalzhifu").modal('show');
				$("#successModalMsgszhifu").html(data.msg);
			}else{
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
function zhifu(totalCost,isDraft,orderNumber) {
	$('body').css("overflow","hidden");
	if(isDraft == 0){
		$("#zhifujine").val(totalCost);
		$("#dingdanleixing").val("预约费");
		$("#zhifuOrderNumber").val(orderNumber);
		$("#orderType").val(1);
		$("#yunChongzhi").modal({show : true});
	}else{
		$("#dingdanleixing").val("运费");
		$("#zhifujine").val(totalCost);
		$("#zhifuOrderNumber").val(orderNumber);
		$("#orderType").val(2);
		$("#yunChongzhi").modal({show : true});
	}
	$('body').css("overflow","scroll");
}
/*//支付提货费
function payTakeCargoCost(){
	$.ajax({
		type : 'post',
		url : '/logisticsc/deliverGoods/paytakecargocost.shtml',
		data : {'orderNumber' : $('#zhifuOrderNumbers').val(), 'money' : $('#zhifujines').val()},
		success : function(result){
			if(result > 0){
				$("#yunChongzhis").modal('hide');
				$("#successModalzhifu").modal('show');
				$("#successModalMsgszhifu").html("支付成功");
			}else{
				$("#yunChongzhis").modal('hide');
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html("支付失败");
			}
		}
	});
}*/
function zhifuqueren() {
	var yun_money = $("#zhifujine").val();
	var orderNumber = $("#zhifuOrderNumber"). val();
	$.ajax({
		url:"/logisticsc/orderCenter/doOrderMoney.shtml",
		data: {orderNumber:orderNumber,amount:yun_money},
		type:'post',
		dataType:'json',
		async:false,
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
			$("#yunChongzhi").modal('hide');
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}
	})
}
function tiaozhuan() {
	window.location="/logisticsc/orderCenter/toorderlistpage.shtml";
}
function exportMineOrder() {
	var orderSate = $("#orderSate").val();
	var condition = $("#condition").val();
	var onlineStartTimes = $("#onlineStartTimes").val();
	var onlineEndTimes = $("#onlineEndTimes").val();
	if(type == 1){
		conditionType =1;
	}else if(type == 2){
		conditionType =2;
	}else if(type == 3){
		conditionType =3;
	}else if(type == 4){
		conditionType = 4;
	}else if(type == 5){
		conditionType =5;
	}else if(type == 6){
		conditionType =6;
	}else if(type == 7){
		conditionType =7;
	}else if(type == 8){
		conditionType =8;
	}else{
		conditionType = 0;
	}
	var url="/logisticsc/orderCenter/exportMineOrder.shtml?condition="+condition+"&startTime="+onlineStartTimes+"&endTime="+onlineEndTimes+"&state="+orderSate+"&conditionType="+conditionType;
	location.href=url;
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
//订单类型
function orderState(){
	var getXzqhInfo="";
  	 $.ajax({
       url :"/logisticsc/tmsorder/getorderstatus.shtml",
       type : 'POST',
       dataType : 'json',
       success:function(result){
    	   result = eval("(" + result + ")");
    	   for(var i=0; i<result.length; i++) {	
     			getXzqhInfo += "<option value=\""+result[i].value+"\">"+result[i].name+"</option>"; 
  			}
  		$("#orderSate").append(getXzqhInfo);
	 },error:function(data){
		 $("#errorModal").modal('show');
		 $("#errorModalMsgs").html("系统错误!");
	}
})
}
//获取 草稿总数
function isDraft() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderisDraft.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
		    	$("#caogao").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}
//获取 已作废
function statVoid() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderOrderStateVoid.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
	    		   $("#yizuofei").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}
//获取 已取消
function stateCancel() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderOrderStateCancel.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
	    		   $("#yiquxiao").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}
//获取 代付款
function stateWait() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderOrderStateWait.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
	    		   $("#daifukuan").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}
//获取 确认付款
function finalPrice() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderFinalPrice.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
	    		   $("#daiquerenfeiyong").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}
//获取 已拒绝
function stateRefused() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderOrderStateRefused.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
	    		   $("#yijujue").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}
//获取 已签收
function stateAlerady() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderOrderStateAlerady.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
	    		   $("#yiqianshou").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}
//获取 保险
function isInsurance() {
	$.ajax({
	       url :"/logisticsc/orderCenter/getOrderIsInsurance.shtml",
	       type : 'POST',
	       dataType : 'json',
	       success:function(data){
	    	   if(data.result==true){
	    		   $("#baoxian").text(data.data);  
	    	   }else{
	    		   $("#errorModal").modal('show');
	    			$("#errorModalMsgs").html(data.msg);
	    	   }
		 },error:function(data){
			 $("#errorModal").modal('show');
			 $("#errorModalMsgs").html("系统错误!");
		}
	})
}

// 查询 订单详细信息
function showOrderDetail(orderNumber){
	window.location.href = '/logisticsc/orderCenter/toorderdetailpage.shtml?orderNumber='+orderNumber;
}