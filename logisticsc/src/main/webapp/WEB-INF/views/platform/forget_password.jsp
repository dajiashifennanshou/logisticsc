<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico" /> 
<link rel="stylesheet" href="${configProps['resources']}/platform/bootstrap-css/bootstrap.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/bootstrap-css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/forget-pass.css" />
<!--[if lt IE 9]>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js" ></script>
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/platform/js/placeholderfriend.js" ></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome.min.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome-ie7.min.css" />	
<title>忘记密码</title>
</head>
<script type="text/javascript">
$(function(){
	$("#xyb1").click(function(){
		return loginName() && code() && xiayibu();
	});
	$("#loginName").focus(function() {
		$("#loginNameMsg").text("");
	});
	$("#code").focus(function() {
		$("#codeMsg").text("");
	});
	$("#xyb2").click(function(){
		return password() && repassword() && chushimiama();
	});
	$("#password").focus(function() {
		$("#passwordMsg").text("");
	});
	$("#repassword").focus(function() {
		$("#repasswordMsg").text("");
	});
});
function loginName(){
	var login_name = $('#loginName').val();
	if(login_name==null || login_name==''){
		$("#loginNameMsg").text("请输入登录账号！");
		return false;
	}else{
		if(login_name.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(login_name)){
			$("#loginNameMsg").text('请输入正确的登录账号!');
			return false;
		}else{
			var data="";
			//方式验证码
			$.ajax({
				type : "POST",
				async : false,
				url : "/logisticsc/user/getUser.shtml",
				data : {loginName:login_name},
				success : function(result) {
					data = result.result;
				}
			});
			if(data == true){
				$("#loginNameMsg").text("用户未存在，请输入正确的登录号！");
				return false;
			}
		}
	}
	return true;
}
function code(){
	 var code = $('#code').val();
	if(code==null || code==''){
		$("#codeMsg").text('请输入验证码！');
		return false;
	} 
	return true;
}
function xiayibu(){
	var data = $("#dengluzhanghao").serialize();
	$.ajax({
		url:"/logisticsc/user/verificationForgetPasswordCode.shtml",
		data:data,
		type:'post',
		dataType:'json',
		success:function(data){
			var date = data.result;
			if(date == true){
				$(".wjmm-con1").hide();
				$(".wjmm-con2").show();
				return true;
			}else{
				$("#codeMsg").text('请输入正确的验证码！');
				return false;
			}
		}
	});
	return true;
}

function password() {
	var password = $('#password').val();
	if(password==null || password==''){
		$("#passwordMsg").text('请输入密码！');
		return false;
	}else{
		if(!/[0-9a-zA-Z_]{6,18}/.test(password)){
			$("#passwordMsg").text('密码字符长度为6-18位,区分大小写！');
			return false;
		}
	}
	return true;
}
function repassword() {
	var password = $('#password').val();
	var repassword = $('#repassword').val();
	if(repassword==null || repassword==''){
		$("#repasswordMsg").text('请输入确认密码！');
		return false;
	}else{
		if(!/[0-9a-zA-Z_]{6,18}/.test(repassword)){
			$("#repasswordMsg").text('密码字符长度为6-18位,区分大小写！');
			return false;
		}else{
			if(password!=repassword){
				$("#repasswordMsg").text('两次输入的密码不相同！');
				return false;
			}
		}
	}
	return true;
}
function chushimiama() {
	var data = $("#dengluzhanghao").serialize();
	$.ajax({
		url:"/logisticsc/user/verificationForgetPassword.shtml",
		data:data,
		type:'post',
		dataType:'json',
		success:function(data){
			var date = data.result;
			if(date == true){
				$(".wjmm-con1").hide();
				$(".wjmm-con2").hide();
				$(".wjmm-con3").show();
				return true;
			}else{
				 $("#errorModal").modal('show');
				 $("#errorModalMsgs").html(data.msg);
				return false;
			}
		}
	});
	return true;
}


function enterpriseSms(){
	var login_name = $('#loginName').val();
	if(login_name==null || login_name==''){
		$("#loginNameMsg").text("请输入登录号！");
		return false;
	}else{
		if(login_name.length != 11 && !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(login_name)){
			$("#loginNameMsg").text('请输入正确的登录号!');
			return false;
		}
		else{
			var data="";
			//方式验证码
			$.ajax({
				type : "POST",
				async : false,
				url : "/logisticsc/user/getUser.shtml",
				data : {loginName:login_name},
				success : function(result) {
					data = result.result;
				}
			});
			if(data == true){
				$("#loginNameMsg").text("用户未存在，请输入正确的登录号！");
				return false;
			}
		}
	}
	//方式验证码
	$.ajax({
		type : "POST",
		url : "/logisticsc/user/verificationMessage.shtml",
		data : {loginName:login_name},
		success : function(data) {
			if(data.result){
				$('#sendcode').unbind('click');
    				$("#sendcode").css("background-color","#666666");
    				var time = 120;
    				var interval = setInterval(function(){
					time--;
					$("#sendcode").empty();
					$("#sendcode").append(time + "秒后可再次获取");
					if(time == 0){
						time = 60;
						$("#sendcode").empty();
						$("#sendcode").append("获取验证码");
						clearInterval(interval);
						$("#sendcode").css("background-color","#dadada");
						$('#sendcode').bind('click', function(){
							enterpriseSms();
						});
					}
				},1000);
			}else{
				 $("#errorModal").modal('show');
				 $("#errorModalMsgs").html("发送失败");
			}
		}
	});
} 
</script>
<body>
	<div class="top">
			<div class="top-content">
				 <div class="logo f">
				 	<img src="/logisticsc/resources/platform/img/logo-1.png" />
				 </div>
				 <!--  
				 <div class="dh f">
				 	<span>
				 		<i class="icon-phone zhuce-dh"></i>
				 		18000510004
				 	</span>
				 </div>
				 -->
				 <div class="denglu">
				 	<span>
				 		<i class="icon-double-angle-left"></i>
				 		<a href="/logisticsc/main.jsp">回到首页</a>
				 	</span>
				 	<span>
				 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-desktop"></i>
				 		<a href="/logisticsc/platform-login.jsp">已有账号？马上登陆</a>
				 	</span>
				 </div>
			</div>
		</div>
		<div class="content">
		<form id="dengluzhanghao">
				<div class="container">
					<div class="row">
						<p class="col-sm-12">忘记密码</p>
						<i class=" icon-ambulance icon-large"></i>
					</div>
					<div class="wjmm-con1">
						<div class="wj-top1"></div>
							<div class="form-horizontal con1 ">
								<div class="form-group">
									<div class="col-sm-2"></div>
									<label class="col-sm-2 control-label">登陆账号：</label>
									<div class="col-sm-5">
										<label class="login-sjh" id="loginNameMsg"></label>
										<input type="text" id="loginName" name="loginName" class="form-control" placeholder="请输入登陆账号" style="position: relative;"/>
									</div>
								</div>
							</div>
							<div class="form-horizontal con1 con2 ">
								<div class="form-group">
									<div class="col-sm-2"></div>
									<label class="col-sm-2 control-label">验证码:</label>
									<div class="col-sm-3">
										<label class="login-sjh" id="codeMsg"></label>
										<input type="text" id="code" name="code" class="form-control user" placeholder="请输入验证码" style="position: relative;"/>
									</div>
									<div class="col-sm-2">
										<div class="btn btn-default" id="sendcode" onclick="enterpriseSms()">获取验证码</div>	
									</div>
								</div>
							</div>
							<div class="form-horizontal con1 con2 con3">
								<div class="btn btn-info wc-btn col-lg-offset-3" id="xyb1">下一步</div>
							</div>
					</div>
					<div class="wjmm-con2">
						<div class="wj-top2"></div>
							<div class="form-horizontal con1">
								<div class="form-group" id="dianhua">
										<div class="col-sm-2"></div>
										<label class="col-sm-2 control-label">密码：</label>
										<div class="col-sm-5">
											<label class="login-sjh" id="passwordMsg"></label>
											<input type="password" class="form-control user" id="password" name="password" placeholder="请输入密码" />
										</div>
									</div>
							</div>
							<div class="form-horizontal con2">
								<div class="form-group" id="dianhua">
									<div class="col-sm-2"></div>
									<label class="col-sm-2 control-label">确认密码：</label>
									<div class="col-sm-5">
										<label class="login-sjh" id="repasswordMsg"></label>
										<input type="password" class="form-control user" id="repassword" name="repassword" placeholder="请输入确认密码" />
									</div>
								</div>
							</div>
							<div class="form-horizontal con1 con2 con3">
								<div class="btn btn-info wc-btn col-lg-offset-3" id="xyb2">下一步</div>
							</div>
					</div>
					<div class="wjmm-con3">
						<div class="wj-top3"></div>
						<div class="forget3"></div>
						<div class="fanhui">
							<a href="../main.jsp">回首页</a>
						</div>
					</div>
				</div>
				</form>
			</div>
		<div class="bottom2">
			 © Copyright 2013-2017    粤ICP备15048227号-1    中工储运    版权所有
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
</body> 
</html>