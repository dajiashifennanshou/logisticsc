var lineIds,comIds,moneyId;
$(document).ready(function(){
	getCollectionLine();
	$("#xianluchongzhi").click(function(){
		return yun_money() && yanzhengLine();
	});
	$("#yun_money").focus(function() {
		$("#yun_moneyMsg").text("");
	});
})
function selectCollectionLine(){
	getCollectionLine();
}
function getCollectionLine(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var companyName = $("#companyName").val();
	var Conditions = {
			"companyName":companyName,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getCollectionLine.shtml",
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
					$("#getCollectionLine").html(rechargeRecord);
					return false;
				}
				for (var i = 0; i < date.length; i++) {	
					body+="<tr>"
						+"<td><input name='collectionLineId' value='"+date[i].recordId+"' type=checkbox></td>"
						+"<td>"+date[i].companyName+"</td>"
						+"<td>"+date[i].serverType+"</td>"
						+"<td>"+"从:"+date[i].startProvinceValue+"-"+date[i].startCityValue+"-"+date[i].startCountyValue+"<br/>"+
						"到:"+date[i].endProvinceValue+"-"+date[i].endCityValue+"-"+date[i].endCountyValue+"</td>"
						+"<td>"+date[i].heavyCargoPriceLow+"</td>"
						+"<td>"+date[i].bulkyCargoPriceLow+"</td>"
						+"<td>"+"￥"+date[i].lowestPrice+"</td>"
						+"<td>"+"<button type='button' class='btn btn-warning' onclick='toPlaceOrderPage("+date[i].id+")'>下单</button>&nbsp;&nbsp;"+"</td>"
						+"</tr>";
				}
				//<button type='button' class='btn btn-warning' onclick='displayXianluChongzhi("+date[i].id+","+date[i].comId+")'>预充值</button>
				rechargeRecord=body;
				$("#getCollectionLine").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getCollectionLine);
			},
			error:function(error){
				body+="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#getCollectionLine").html(rechargeRecord);
			}
		})
}
//余额验证
function yun_money() {
	var yun_money = $("#yun_money").val();
	var yun_jine = $("#yun_jine").text();
	yun_jine = parseFloat(yun_jine)
	if(null == yun_money || yun_money == ""){
		$("#yun_moneyMsg").text("充值金额不能为空！");
		return false;
	}else if(!/^([0-9.]+)$/.test(yun_money)){
		$("#yun_moneyMsg").text("充值金额只能输数字！");
		return false;
	}else if(yun_money > yun_jine){
		$("#yun_moneyMsg").text("充值金额不足！");
		return false;
	}
	return true;
}
function xianluqingkong() {
	$("#yun_money").val("");
}
//线路充值
function displayXianluChongzhi(lineId,comId) {
	$('body').css("overflow","hidden")
	xianluqingkong();
	$("#yunChongzhi").modal({show : true});
	 lineIds = lineId;
	 comIds =comId;
	$('body').css("overflow","scroll");
}
//验证充值是否存在线路
function yanzhengLine() {
	$.ajax({
		url:"/logisticsc/orderCenter/getUserLineId.shtml",
		data: {lineId:lineIds},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				date =data.data;
				moneyId = date.id;
				chongzhiCunzai();
			}else{
				chongzhi();
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}
	})
}
//充值线路金额  --- 存在
function chongzhiCunzai() {
	var yun_money = $("#yun_money").val();
	$.ajax({
		url:"/logisticsc/personalCenter/doUserLineConsumeRecord.shtml",
		data: {lineId:lineIds,money:yun_money,moneyId:moneyId,companyId:comIds},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#yunChongzhi").modal('hide');
				$("#successModal").modal('show');
				$("#successModalMsgs").html(data.msg);
				window.location="/logisticsc/orderCenter/toorderCollectionLine.shtml";
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
//充值线路金额  --- 未存在
function chongzhi() {
	var yun_money = $("#yun_money").val();
	$.ajax({
		url:"/logisticsc/orderCenter/doUserLineMoney.shtml",
		data: {lineId:lineIds,money:yun_money},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#yunChongzhi").modal('hide');
				$("#successModal").modal('show');
				$("#successModalMsgs").html(data.msg);
				window.location="/logisticsc/orderCenter/toorderCollectionLine.shtml";
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
//跳转到下单页面
function toPlaceOrderPage(lineId,startProvince,startCity,startCounty,endProvince,endCity,endCounty){
	window.location.href = "/logisticsc/deliverGoods/placeOrder.shtml?lineId="+lineId
	+ '&startProvince=' + startProvince + '&startCity=' + startCity + '&startCounty=' + startCounty
	+ '&endProvince=' + endProvince + '&endCity=' + endCity + '&endCounty=' + endCounty;
}
//显示删除我的收藏
function deletecommonDriver() {
    var collectionLineId =[];//定义一个数组    
    $('input[name="collectionLineId"]:checked').each(function(){
    	collectionLineId.push($(this).val());
    });
    if(collectionLineId.length <= 0){
    	$("#promptModal").modal('show');
		$("#promptModalMsgs").html("请选择一条数据!");
    }else{
    	$.ajax({
    		url:"/logisticsc/orderCenter/doUpdateCollectionLine.shtml",
    		data: {'collectionLineId' : JSON.stringify(collectionLineId)},
    		type:'post',
    		dataType:'json',
    		success:function(data){
    			if(data.result==true){
    				$("#successModal").modal('show');
    				$("#successModalMsgs").html(data.msg);
    				getCollectionLine();
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
}