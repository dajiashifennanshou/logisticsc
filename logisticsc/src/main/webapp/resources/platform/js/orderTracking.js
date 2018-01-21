$(document).ready(function(){
	var conditions =  JSON.parse($("#conditions").val());
	$("#condition").val(conditions);
	if($("#condition").val() != ""){
		document.getElementById("liuchengtu").style.visibility="visible";//显示  
		getOrderTracking();
	}
	$("#chaxun").click(function(){
		document.getElementById("liuchengtu").style.visibility="visible";//显示  
		getOrderTracking();
	});
})
function getOrderTracking(){
	var condition = $("#condition").val();
	$.ajax({
		url:"/logisticsc/orderTracking/getOrderTracking.shtml",
		data:{condition:condition.replace(/[ ]/g,"")},
		type:'post',
		dataType:'json',
		success:function(dataResult){
			if(dataResult.result == true){
				var date = dataResult.data;
				var page = dataResult.data.follows;
				var head="<table class='table table-striped table-bordered' id='bill-table1'>" 
					  +"<thead>" 
					  +"<tr>"
						+"<th>订单号</th>"
						+"<th>运单号</th>"
						+"<th>物流商</th>"
						+"<th>线路</th>"
					 +"</tr>" 
					 +"</thead><tbody>";
				var body="";
				if(null == date || '' == date || 'undefined' == date) {
					body+="<tr>"
						+"<td style='color:red;' colspan='7' >没有数据</td>";
						+"</tr>";
					var foot="</tbody></table>";
					var getOrder=head+body+foot;
					alert("123");
					$("#getOrder").html(getOrder);
					return false;
				}
				if(null == date.wayBillNumber || '' == date.wayBillNumber || date.wayBillNumber == 'undefined' || date.wayBillNumbelength <= 0){
					var wayBillNumber="";
				}else{
					var wayBillNumber = date.wayBillNumber;
				}
				body+="<tr>"
					+"<td>"+date.orderNumber+"</td>"
					+"<td>"+wayBillNumber+"</td>"
					+"<td>"+date.companyName+"</td>"
					+"<td>从:"+date.startProvince+"-"+date.startCity+"-"+date.startCounty+"<br/>"+
					"到:"+date.endProvince+"-"+date.endCity+"-"+date.endCounty+"</td>"
					+"</tr>";
				var foot="</tbody></table>";
				var getOrder=head+body+foot;
				$("#getOrder").html(getOrder);
				if(null == page || '' == page || 'undefined' == page || page.length <= 0) {
					var head1="<table class='table table-striped table-bordered' id='bill-table1'>" 
						  +"<thead>" 
						  +"<tr>"
							+"<th>时间</th>"
							+"<th>处理信息</th>"
							+"<th>处理人</th>"
						 +"</tr>" 
						 +"</thead><tbody>";
					var body1="";
					body1+="<tr>"
							+"<td style='color:red;' colspan='7' >没有数据</td>"
							+"</tr>";
					var foot1="</tbody></table>";
					var getOrderTracking=head1+body1+foot1;
					$("#getOrderTracking").html(getOrderTracking);
					return false;
				}else{
					var head1="<table class='table table-striped table-bordered' id='bill-table1'>" 
						  +"<thead>" 
						  +"<tr>"
							+"<th>时间</th>"
							+"<th>处理信息</th>"
							+"<th>处理人</th>"
						 +"</tr>" 
						 +"</thead><tbody>";
					var body1="";
					var index="";
					var td="";
					var a="";
					for (var i = 0; i < page.length; i++) {
						body1+="<tr>"
							+"<td>"+formartDate(page[i].handleTime)+"</td>"
							+"<td>"+page[i].handleInfo+"</td>"
							+"<td>"+page[i].operatePerson+"</td>"
						+"</tr>";
						
						/*if('0'== page[i].status){
							td+="<td><div class='circle circle1 f'style='z-index:999;'></div>"
							  +"<div class='fon-deny fon-shouli'>"
							  +"<span>"+page[i].statusName+"</span></div></td>";
						}else */if(i==page.length-1){
							td+="<td><div class='circle circle1 f'style='z-index:999;'></div>"
								  +"<div class='fon-deny fon-shouli'>"
								  +"<span>"+page[i].statusName+"</span></div></td>";
						}else{
							td+="<td><div class='line f'></div>"
							  +"<div class='circle f'></div>"
							  +"<div class='fon-deny'>"
							  +"<span>"+page[i].statusName+"</span>"
							  +"</div></td>";
						}
						a =i;
					}
					
					var foot1="</tbody></table>";
					var getOrderTracking=head1+body1+foot1;
					$("#getOrderTracking").html(getOrderTracking);
					$("#aaaaaaa").html(td);
				}
			}else{
				var head="<table class='table table-striped table-bordered' id='bill-table1'>" 
					  +"<thead>" 
					  +"<tr>"
						+"<th>订单号</th>"
						+"<th>运单号</th>"
						+"<th>物流商</th>"
						+"<th>线路</th>"
					 +"</tr>" 
					 +"</thead><tbody>";
				var body="";
				body+="<tr>"
					+"<td style='color:red;' colspan='7' >没有数据</td>";
					+"</tr>";
				var foot="</tbody></table>";
				var getOrder=head+body+foot;
				$("#getOrder").html(getOrder);
				$("#getOrderTracking").html("");
			}
		},
		error:function(data){
			body="<tr>"
				+"<td style='color:red;' colspan='7' >没有数据</td>";
				+"</tr>";
			var foot="</tbody></table>";
			var getOrder=head+body+foot;
			$("#getOrder").html(getOrder);
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
/*function liuchengtu(){
	document.getElementById('daishouliyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('yishoulixian').style.backgroundColor='#d2d2d2';
	document.getElementById('yishouliyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('tihuoxian').style.backgroundColor='#d2d2d2';
	document.getElementById('tihuoyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('huowurukuxian').style.backgroundColor='#d2d2d2';
	document.getElementById('huowurukuyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('yunshuzhongxian').style.backgroundColor='#d2d2d2';
	document.getElementById('yunshuzhongyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('yidaodaxian').style.backgroundColor='#d2d2d2';
	document.getElementById('yidaodayuan').style.backgroundColor='#d2d2d2';
	document.getElementById('songhuozhongxian').style.backgroundColor='#d2d2d2';
	document.getElementById('songhuozhongyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('yiqianshouxian').style.backgroundColor='#d2d2d2';
	document.getElementById('yiqianshouyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('yizuofeixian1').style.backgroundColor='#d2d2d2';
	document.getElementById('yizuofeixian2').style.backgroundColor='#d2d2d2';
	document.getElementById('yizuofeiyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('yijujuexian1').style.backgroundColor='#d2d2d2';
	document.getElementById('yijujueyuan').style.backgroundColor='#d2d2d2';
	document.getElementById('yijujuexian2').style.backgroundColor='#d2d2d2';
}*/
/*if($("#daishouli").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
}else if($("#yishouli").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yishoulixian').style.backgroundColor='#2e88c0';
	document.getElementById('yishouliyuan').style.backgroundColor='#2e88c0';
}else if($("#tihuozhong").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yishoulixian').style.backgroundColor='#2e88c0';
	document.getElementById('yishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoxian').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoyuan').style.backgroundColor='#2e88c0';
}else if($("#huowuruku").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yishoulixian').style.backgroundColor='#2e88c0';
	document.getElementById('yishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoxian').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoyuan').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuxian').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuyuan').style.backgroundColor='#2e88c0';
}else if($("#yunshuzhong").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yishoulixian').style.backgroundColor='#2e88c0';
	document.getElementById('yishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoxian').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoyuan').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuxian').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongxian').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongyuan').style.backgroundColor='#2e88c0';
}else if($("#yidaoda").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yishoulixian').style.backgroundColor='#2e88c0';
	document.getElementById('yishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoxian').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoyuan').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuxian').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongxian').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yidaodaxian').style.backgroundColor='#2e88c0';
	document.getElementById('yidaodayuan').style.backgroundColor='#2e88c0';
}else if($("#songhuozhong").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yishoulixian').style.backgroundColor='#2e88c0';
	document.getElementById('yishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoxian').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoyuan').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuxian').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongxian').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yidaodaxian').style.backgroundColor='#2e88c0';
	document.getElementById('yidaodayuan').style.backgroundColor='#2e88c0';
	document.getElementById('songhuozhongxian').style.backgroundColor='#2e88c0';
	document.getElementById('songhuozhongyuan').style.backgroundColor='#2e88c0';
}else if($("#yijianshou").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yishoulixian').style.backgroundColor='#2e88c0';
	document.getElementById('yishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoxian').style.backgroundColor='#2e88c0';
	document.getElementById('tihuoyuan').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuxian').style.backgroundColor='#2e88c0';
	document.getElementById('huowurukuyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongxian').style.backgroundColor='#2e88c0';
	document.getElementById('yunshuzhongyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yidaodaxian').style.backgroundColor='#2e88c0';
	document.getElementById('yidaodayuan').style.backgroundColor='#2e88c0';
	document.getElementById('songhuozhongxian').style.backgroundColor='#2e88c0';
	document.getElementById('songhuozhongyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yiqianshouxian').style.backgroundColor='#2e88c0';
	document.getElementById('yiqianshouyuan').style.backgroundColor='#2e88c0';
}else if($("#yizuofei").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yizuofeixian1').style.backgroundColor='#2e88c0';
	document.getElementById('yizuofeixian2').style.backgroundColor='#2e88c0';
	document.getElementById('yizuofeiyuan').style.backgroundColor='#2e88c0';
}else if($("#yijujue").text() == page[index].statusName){
	document.getElementById('daishouliyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yijujuexian1').style.backgroundColor='#2e88c0';
	document.getElementById('yijujueyuan').style.backgroundColor='#2e88c0';
	document.getElementById('yijujuexian2').style.backgroundColor='#2e88c0';
}*/