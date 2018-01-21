<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的钱包</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
<script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
<script src="http://g.alicdn.com/bui/seajs/2.3.0/sea.js"></script>
<script src="http://g.alicdn.com/bui/bui/1.1.21/config.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
<script type="text/javascript">
$(function(){
	jine();
	selectBank();
});
function dispxiugaiyinhangka() {
	BUI.Message.Alert("确定撤销银行卡？撤销必须重新绑定银行卡信息！",function(){
		window.location.href="/logisticsc/sys/account/toAddBank.shtml";
	},"success");
}
function jine() {
	$.ajax({
      url :"/logisticsc/sys/account/getJine.shtml",
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
		url:"/logisticsc/sys/account/getBankUserId.shtml",
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				window.location.href="/logisticsc/sys/account/toAddCredit.shtml";
			}else if(data.result==false){
				BUI.Message.Alert("请绑定银行卡！","error");
			}
		}
	})
}
//查询账户信息
function selectBank() {
	$.ajax({
			url:"/logisticsc/sys/account/getBankUserId.shtml",
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
						+"<p class='fon_span4'>"+date.bankaccountnumber.substring(0,6)+"......"+car+"</p>"
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
function displayCrad(){
	window.location.href="/logisticsc/sys/account/toAddBank.shtml";
}
</script>
<style type="text/css">
	*,li{
		margin: 0px;
		padding: 0px;
		color: #333;
		font-family: 'arial';
	}
	.mywallet_con {
		width: 1038px;
		float: left;
		margin-bottom: 10px;
	}
	.mywallet_con ul li {
		width: 278px;
		height: 134px;
		background-color: #f9f9f9;
		margin-left: 38px;
		margin-top: 18px;
		float: left;
		border: 1px solid #f6f6f6;
		list-style: none;
		position: relative;
	}
	.mywallet_con ul{
		margin-left:-38px;
		margin-top:-18px;
	}
	.wallet_yue img {
		position: absolute;
		top: 22px;
		left: 30px;
	}
	.wallet_pic1 {
		position: absolute;
		top: 0px;
		left: 30px;
	}
	.li1_fon {
		position: absolute;
		left: 138px;
		top: 30px;
	}
	.li1_fon span {
		font-size: 16px;
	}
	.mywallet_con ul li .fon_price {
		color: #f88312;
		font-size: 32px;
	}
	.wallet_pic2 {
		position: absolute;
		right: 62px;
		top: 26px;
	}
	.mywallet_con ul li .wallet_a1 {
		display: block;
		width: 108px;
		height: 32px;
		color: white;
		background: #3bd173;
		line-height: 32px;
		text-align: center;
		position: absolute;
		bottom: 30px;
		right: 25px;
		text-decoration: none;
	}
	.fon_span1 {
		font-size: 14px;
		margin-left: 13px;
		float: left;
		margin-top: 5px;
	}
	.fon_span2 {
		font-size: 14px;
		color: #f88312;
		float: right;
		margin-right: 13px;
		text-decoration: none;
		margin-top: 5px;
	}
	.fon_span3 {
		color: #2e6dcc;
		font-size: 18px;
		text-align: center;
		position: absolute;
		top: 40px;
		display: block;
		width: 100%;
	}
	.fon_span4 {
		color: #333;
		font-size: 12px;
		text-align: center;
		position: absolute;
		top: 66px;
		display: block;
		width: 100%;
	}
	.wallet_pic3 {
		position: absolute;
		bottom: 10px;
		right: 10px;
	}
	.wallet_pic4 {
		position: absolute;
		bottom: 10px;
		left: 35px;
	}
	.my-wallet-notice{
		display: block;
		float: left;
		width: 1028px;
		padding: 20px 37px 37px 37px;
		line-height: 32px;
		font-size: 16px;
		border: 1px dashed rgb(231, 231, 231);
	}
	.my-wallet-notice h4{
		background-color: #f9f9f9;
		display: block;
		width: 113px;
		height: 31px;
		line-height: 30px;
		text-align: center;
		font-size: 16px;
		font-weight: bold;
		color: #666;
		
	}
</style>
<body>
<div id="container" style="margin-left: 40px;margin-top: 20px;">
<div class="container-self">
	<div class="demo-content platformStyle" style="float:left;">
		<div>
			<img alt="" src="/logisticsc/resources/platform/img/my_wallet_bg23.png">
		</div>
		<div class="demo-content">
			<div class="mywallet_con">
				<ul id="sub1">
					<li class="wallet_yue">
						<img src="/logisticsc/resources/platform/img/my_wallet1.png" />
						<div class="li1_fon">
							<span style="margin-bottom: 0px;display: block;">当前账户余额</span><br />
							<span class="fon_price" id="jine"></span>
							<span>元</span>
						</div>
					</li>
					<li class="mywallet_li2" onclick="displayCrad()" id="iDBody2">
						<img src="/logisticsc/resources/platform/img/my_wallet2.png"  class="wallet_pic1"/>
						<img src="/logisticsc/resources/platform/img/my_wallet3.png" class="wallet_pic2" />
						<a href="#" class="wallet_a1">添加银行卡</a>
					</li>
					<li class="mywallet_li3" onclick="toAddQualification()" id="iDBody3">
						<img src="/logisticsc/resources/platform/img/my_wallet5.png"  class="wallet_pic4"/>
						<img src="/logisticsc/resources/platform/img/my_wallet6.png" class="wallet_pic2" />
						<a href="#" class="wallet_a1" style="background:#1e81ce">上传银行资质</a>
					</li>
					<li class="mywallet_li3" id="iDBody4" onclick="toAddQualification()" style="display: none;">
						<img src="/logisticsc/resources/platform/img/my_wallet5.png"  class="wallet_pic4"/>
						<img src="/logisticsc/resources/platform/img/my_wallet6.png" class="wallet_pic2" />
						<a href="#" class="wallet_a1" style="background:#1e81ce">银行资质成功</a>
					</li>
				</ul>
			</div>
			
		</div>
    </div>
    <div class="my-wallet-notice" style="">
		<h4>绑定须知</h4>
		<img src="/logisticsc/resources/platform/img/mywallet_flow.jpg"  />
		<p style="margin-top:10px; font-family:微软雅黑;font-size: 12px;">
			1.请你在绑定银行卡时，一定要仔细填写正确的资料！<br>
			2.上传图片资质类型支持jpg,jpeg,png，文件大小限制512KB！<br>
			3.银行卡（Bank Card） 是由商业银行等金融机构及邮政储汇机构向社会发行的具有消费信用、转账结算、存取现金等全部或部分功能的信用支付工具。银行卡包括信用卡和借记卡两种。<br>
			4.银行卡（Bank Card） 是由商业银行等金融机构及邮政储汇机构向社会发行的具有消费信用、转账结算、存取现金等全部或部分功能的信用支付工具。<br>
			5.银行卡（Bank Card） 是由商业银行等金融机构及邮政储汇机构向社会发行的具有消费信用、转账结算、存取现金等全部或部分功能的信用支付工具。<br>
		</p>
	 </div>
</div>
</div>
</body>
</html>