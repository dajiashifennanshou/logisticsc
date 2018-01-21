<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的钱包</title>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<script src="${configProps['resources']}/platform/js/myWallet.js"></script>
</head>
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
		left: 18px;
	}
	.wallet_pic1 {
		position: absolute;
		top: 0px;
		left: 30px;
	}
	.li1_fon {
		position: absolute;
		left: 127px;
		top: 20px;
	}
	.li1_fon span {
		font-size: 14px;
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
		top: 32px;
	}
	.fon_span4 {
		color: #333;
		font-size: 12px;
		text-align: center;
		position: absolute;
		top: 66px;
		left: 90px;
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
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="peronal_center_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle" style="float:left;">
<!-- 		<div>
			<img alt="" src="/logisticsc/resources/platform/img/my_wallet_bg23.png">
		</div> -->
		<div class="demo-content">
			<div class="mywallet_con">
				<ul id="sub1">
				<c:if test="${user_session.userType == 4 || user_session.userType == 5}">
					<li class="wallet_yue">
						<img src="/logisticsc/resources/platform/img/my_wallet1.png" />
						<div class="li1_fon">
							<span>当前账户余额</span><br />
							<span class="fon_price" id="jine"></span>
							<input type="text" id="userType" value="${user_session.userType}" style="display: none;">
							<span>元</span><br/>
							<span>
								保证金：
								<i style="font-size:16px;color:#f88312;" id="baozhengjin"></i>元
							</span><br />
						</div>
					</li>
					</c:if>
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
		<p style="margin-top:10px;">
			1.请你在绑定银行卡时，一定要仔细填写正确的资料！<br>
			2.上传图片资质类型支持jpg,jpeg,png，文件大小限制512KB！<br>
			3.银行卡（Bank Card） 是由商业银行等金融机构及邮政储汇机构向社会发行的具有消费信用、转账结算、存取现金等全部或部分功能的信用支付工具。银行卡包括信用卡和借记卡两种。<br>
			4.银行卡（Bank Card） 是由商业银行等金融机构及邮政储汇机构向社会发行的具有消费信用、转账结算、存取现金等全部或部分功能的信用支付工具。<br>
			5.银行卡（Bank Card） 是由商业银行等金融机构及邮政储汇机构向社会发行的具有消费信用、转账结算、存取现金等全部或部分功能的信用支付工具。<br>
		</p>
	 </div>
</div>
</div>
<div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
			</div>
			<div class="modal-body success-info">
				 <h5 style="color: #6f9fd9!important;font-weight:bold">成功提示:<span id="successModalMsgs"></span></h5>
			</div>
		</div>
	</div>
</div>
<div id="errorModal" class="modal  fade" tabindex="-1" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-info modal-success">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel" style="color: red !important;">操作失败！</h4>
                <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
            </div>
            <div class="modal-body success-info">
                <h5 style="color: red !important;font-weight:bold">错误提示:<span id="errorModalMsgs"></span></h5>
            </div>
        </div>
    </div>
</div>
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-info modal-success">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">温馨提示！</h4>
                 <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
            </div>
            <div class="modal-body success-info">
                <h5 style="color: #cccccc!important;font-weight:bold">温馨提示:<span id="promptModalMsgsDIV"></span></h5>
            </div>
        </div>
    </div>
</div>
<div id="xiugaiyinhangka" class="modal  fade" tabindex="-1" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-info modal-success">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">温馨提示！</h4>
                 <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
            </div>
            <div class="modal-body success-info">
                <h5 style="color: #cccccc!important;font-weight:bold">温馨提示:<span id="xiugaiyinhangkaMsg"></span></h5>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-primary" onclick="displayCrad()">确定修改</button>
            </div>
        </div>
    </div>
</div>
 <div style="clear: both;"></div>
 <div class="footer">
	<jsp:include page="../bottom.jsp"></jsp:include>
</div>	
</body>
</html>