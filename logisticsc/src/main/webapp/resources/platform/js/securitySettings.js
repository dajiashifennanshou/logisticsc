$(document).ready(function() {
	$("#xiugaimima").click(function() {
		return passwordMimaMsg() && newPasswordMimaMsg() && confirmPasswordMimaMsg() && updatePassword();
	});
	$("#passwordMima").focus(function() {
		$("#passwordMimaMsg").text("");
	});
	$("#newPasswordMima").focus(function() {
		$("#newPasswordMimaMsg").text("");
	});
	$("#confirmPasswordMima").focus(function() {
		$("#confirmPasswordMimaMsg").text("");
	});
	$("#baocunyouxiang").click(function() {
		return emailMsg() && verCodeMsg() && updateEmail();
	});
	$("#email").focus(function() {
		$("#emailMsg").text("");
	});
	$("#verCode").focus(function() {
		$("#verCodeMsg").text("");
	});
});
function qingkongmima(){
	$('#passwordMima').val("");
	$('#newPasswordMima').val("");
	$('#confirmPasswordMima').val("");
}
function qingkongyouxiang(){
	 $('#email').val("");
	 $('#verCode').val("");
}
function emailMsg() {
	var email = $('#email').val();
	if(email == null ||email ==""){
		$("#emailMsg").text("请输入邮箱！");
		return false;
	}else if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)){
		$("#emailMsg").text("请输入正确的邮箱格式！");
		return false;
	}
	return true;
}
function verCodeMsg() {
	var verCode = $('#verCode').val();
	if(verCode==null || verCode==''){
 		$("#verCodeMsg").text('请输入验证码！');
 		return false;
 	} 
 	return true;
}
function passwordMimaMsg() {
	var passwordMima = $('#passwordMima').val();
	if(passwordMima==null || passwordMima==''){
		$("#passwordMimaMsg").text('请输入旧密码！');
		return false;
	}else{
		var data="";
		//方式验证码
		$.ajax({
			type : "POST",
			async : false,
			url : "/logisticsc/user/getUserPassword.shtml",
			data : {password:passwordMima},
			success : function(result) {
				data = result.result;
			}
		});
		if(data == false){
			$("#passwordMimaMsg").text("旧密码错误！");
			return false;
		}
	}
	return true;
}
function newPasswordMimaMsg() {
	var newPasswordMima = $('#newPasswordMima').val();
	if(newPasswordMima==null || newPasswordMima==''){
		$("#newPasswordMimaMsg").text('请输入新密码！');
		return false;
	}else if(!/[0-9a-zA-Z_]{6,18}/.test(newPasswordMima)){
		$("#newPasswordMimaMsg").text('密码字符长度为6-18位,区分大小写！');
		return false;
	}	
//	}else if(newPasswordMima.length< 5 || newPasswordMima.length>20){
//		$("#newPasswordMimaMsg").text('密码字符长度为5-20位！');
//		return false;
//	}
	return true;
}
function confirmPasswordMimaMsg() {
	var newPasswordMima = $('#newPasswordMima').val();
	var confirmPasswordMima = $('#confirmPasswordMima').val();
	if(confirmPasswordMima==null || confirmPasswordMima==''){
		$("#confirmPasswordMimaMsg").text('请输入确认密码！');
		return false;
	}else{
		 if(!/[0-9a-zA-Z_]{6,18}/.test(newPasswordMima)){
			$("#newPasswordMimaMsg").text('密码字符长度为6-18位,区分大小写！');
			return false;
		}else{
			if(confirmPasswordMima != newPasswordMima){
				$("#confirmPasswordMimaMsg").text('两次输入的密码不相同！');
				return false;
			}
		}
	}
	return true;
}

//显示修改邮箱
function displayEmail(){
	var userEmail = $("#userEmail").val();
	$("#email1").click(function(){
		return emailMsg() && email1();
	});
	qingkongyouxiang();
	if(userEmail.length>0){
		$('body').css("overflow","hidden");
		$("#updateEmail").modal({show : true});
		$('body').css("overflow","scroll");
	}else{
		$("#jiuyouxiang").hide();
		$('body').css("overflow","hidden");
		$("#updateEmail").modal({show : true});
		$('body').css("overflow","scroll");
	}
}
//显示绑定取现密码
function displayCashPassword(){
	var cashPassword = $("#cashPassword").val();
	if(cashPassword.length>0){
		$('body').css("overflow","hidden");
		$("#updateCashPassword").modal({show : true});
		$('body').css("overflow","scroll");
	}else{
		$("#jiuquxianmima").hide();
		$('body').css("overflow","hidden");
		$("#updateCashPassword").modal({show : true});
		$('body').css("overflow","scroll");
	}
}
//修改邮箱
function updateEmail(){
	 var verCode = $("#verCode").val();
		$.ajax({
			url:"/logisticsc/personalCenter/bindingEmail.shtml",
			data:{"verCode":verCode},
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.result==true){
					$("#updateEmail").modal("hide");
					$("#successModal").modal('show');
					$("#successModalMsgs").html(data.msg);
				}else{
					$("#errorModal").modal('show');
					$("#errorModalMsgs").html(data.msg);
				}
			},
			error:function(data){
				$("#email1").append("获取验证码");
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html("系统错误!");
			}
		})
}
//邮箱发送验证码
function email1(){
var email = $("#email").val();
	$.ajax({
		url:"/logisticsc/personalCenter/sendMail.shtml",
		data:{"email":email},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$('#email1').unbind('click');
				$("#email1").css("background-color","#666666");
				var time = 60;
				var interval = setInterval(function(){
				time--;
				$("#email1").empty();
				$("#email1").append(time + "秒后可再次获取");
				if(time == 0){
					time = 60;
					$("#email1").empty();
					$("#email1").append("获取验证码");
					clearInterval(interval);
					$("#email1").css("background-color","#dadada");
					$('#email1').bind('click', function(){
						email1();
					});
				}
			},1000);
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
//显示修改密码
function displayPassword(){
	$('body').css("overflow","hidden");
	qingkongmima();
	$("#updatePassword").modal({show : true});
	$('body').css("overflow","scroll");
}
function tiaozhuan() {
	window.location.href="/logisticsc/user/outUser.shtml"; 
}
function youxiangtiaozhuan() {
	window.location.href="/logisticsc/personalCenter/toorderSecuritySettings.shtml"; 
}
function updatePassword(){
	var data = $("#update_password").serialize();
	$.ajax({
		url:"/logisticsc/personalCenter/updatePwd.shtml",
		data:data,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#updatePassword").modal("hide");
				$("#successModals").modal('show');
				$("#successModalMsgss").html(data.msg);
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