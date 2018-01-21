$(function(){
	if($("#userType").val() == 4 ||$("#userType").val()==5){
		jine();
		baozhengjin();
	}
	selectBank();
});
function displayCrad(){
	window.location.href="/logisticsc/personalCenter/toAddBank.shtml";
}
function baozhengjin() {
	$.ajax({
	      url :"/logisticsc/personalCenter/getBankPayment.shtml",
	      type : 'POST',
	      dataType : 'json',
	      success:function(data){
	     	 if(data.result==true){
	     		var date = data.data;
	     		$("#baozhengjin").text(date);
	     	 }
			}
	 	 })
}
function jine() {
	$.ajax({
      url :"/logisticsc/personalCenter/getJine.shtml",
      type : 'POST',
      dataType : 'json',
      success:function(data){
     	 if(data.result==true){
     		 var date = data.data;
     		arr=date.split(":");
     		 $("#jine").text(arr[1]);
        }else  if(data.result==false){
        	$("#jine").text("0.00");
        }
		}
 	 })
}
function toAddQualification() {
	$.ajax({
		url:"/logisticsc/personalCenter/getBankUserId.shtml",
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				window.location.href="/logisticsc/personalCenter/toAddQualification.shtml";
			}else if(data.result==false){
				$("#promptModal").modal('show');
				$("#promptModalMsgs").html("请绑定银行卡！");
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统异常");
		}
	})
}
function dispxiugaiyinhangka() {
	$("#xiugaiyinhangka").modal('show');
	$("#xiugaiyinhangkaMsg").html("确定撤销银行卡？撤销必须重新绑定银行卡信息！");
}
//查询账户信息
function selectBank() {
	$.ajax({
			url:"/logisticsc/personalCenter/getBankUserId.shtml",
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.result==true){
					document.getElementById('iDBody2').style.display = "none";
					var date=data.data;
					var car = date.bankaccountnumber;
					if(date.isQualifications == 0){
						document.getElementById('iDBody3').style.display = "none";
						document.getElementById('iDBody4').style.display = "block";
					}
					if(car.length == 16){
						car = car.substring(car.length,12);
					}else if(car.length == 17){
						car = car.substring(car.length,13);
					}else if(car.length == 18){
						car = car.substring(car.length,14);
					}else if(car.length == 19){
						car = car.substring(car.length,15);
					}
					body="<li class='mywallet_li3'><span class='fon_span1'>已绑定银行卡</span>"
						+"<a class='fon_span2' href='javascript:dispxiugaiyinhangka();'>修改</a>"
						+"<span class='fon_span3'>"+date.bankheadname+"</span>"
						+"<span class='fon_span4'>"+date.bankaccountnumber.substring(0,6)+"......"+car+"</span>"
						+"<img src='/logisticsc/resources/platform/img/my_wallet4.png' class='wallet_pic3'/></li>";
					lineMoney=body;
					$('#sub1').append(body)
				}
			},
			error:function(data){
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html("系统异常");
			}
		})
	}