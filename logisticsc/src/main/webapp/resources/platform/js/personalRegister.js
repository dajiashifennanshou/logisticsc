function loginName(){
	var login_name = $('#loginName').val();
	if(login_name==null || login_name==''){
		$("#loginNameMsg").text("请输入手机号！");
		return false;
	}else{
		if(login_name.length != 11 && !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(login_name)){
			$("#loginNameMsg").text('请输入正确的手机号码!');
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
			if(data == false){
				$("#loginNameMsg").text("用户已存在，请输入其他号码！");
				return false;
			}
		}
	}
	return true;
}
function dianzhuan(){
 window.location.href="/logisticsc/applyCenter/toorderEnterpriseFlow.shtml"; 
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
function validCode(){
	 var validCode = $('#validCode').val();
	if(validCode==null || validCode==''){
		$("#validCodeMsg").text('请输入验证码！');
		return false;
	}else{
		var flag = false;
		$.ajax({
			type : "POST",
			async : false,
			url : "/logisticsc/user/validcode.shtml",
			data : {'code':validCode},
			success : function(result) {
				flag = result;
			}
		});
		if(!flag){
			$("#validCodeMsg").text('验证码不正确！');
			return false;
		}
		return true;
	}
}
function code(){
	 var code = $('#code').val();
 	if(code==null || code==''){
 		$("#codeMsg").text('请输入动态码！');
 		return false;
 	} 
 	return true;
}
function agree(){
	if(!$("input[type='checkbox']").is(':checked')){
		$("#agreeMsg").text("不同意协议，无法注册！");
		return false;
	}
	return true;
}
function queding(){
	 window.location.href="../platform-login.jsp";
}
function zhuce(){
		$.post("/logisticsc/user/register.shtml",$('#theForm').serialize(),function(data){
	  		if(data.result){
	  			 $("#successModal").modal('show');
				 $("#successModalMsgs").html("恭喜你,"+data.msg+"");
	  		}else{
	  			 $("#errorModal").modal('show');
				 $("#errorModalMsgs").html(data.msg);
	  		}
		},'json');
}
$(document).ready(function() {
	$("#registerbtn").click(function() {
		return loginName() && password() && repassword() && validCode() && code() && agree() && zhuce();
	});
	$("#loginName").focus(function() {
		$("#loginNameMsg").text("");
	});
	$("#password").focus(function() {
		$("#passwordMsg").text("");
	});
	$("#repassword").focus(function() {
		$("#repasswordMsg").text("");
	});
	$("#validCode").focus(function() {
		$("#validCodeMsg").text("");
	});
	$("#code").focus(function() {
		$("#codeMsg").text("");
	});
	$("#agree").focus(function() {
		$("#agreeMsg").text("");
	});
		
});
function enterpriseSms(){
	var login_name = $('#loginName').val();
	if(login_name==null || login_name==''){
		$("#loginNameMsg").text("请输入手机号！");
		return false;
	}else{
		if(login_name.length != 11 && !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(login_name)){
			$("#loginNameMsg").text('请输入正确的手机号码!');
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
			if(data == false){
				$("#loginNameMsg").text("用户已存在，请输入其他号码！");
				return false;
			}
		}
	}
	var code = $('#validCode').val();
	if(!validCode()){
		return;
	}
	//发送验证码
	$.ajax({
		type : "POST",
		url : "/logisticsc/user/verificationMessage.shtml",
		data : {loginName:login_name,validCode:code},
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