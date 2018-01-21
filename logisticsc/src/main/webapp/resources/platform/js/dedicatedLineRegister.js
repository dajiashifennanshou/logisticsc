$(document).ready(function() {
	$("#xyb1").click(function() {
		return loginName() && password() && repassword() && code() && xiayibu();
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
	$("#code").focus(function() {
		$("#codeMsg").text("");
	});
	$("#querenzhuce").click(function() {
		return companyName() && postCode() && companyAddress() && companyPhone() && legalPerson() && companyFax() && legalMobile() && companyTaxNo() && contacts() && financeEmail() && contactsMobile() && companyInfo() && qq() && logMsg() && businessLicenses() && companyPhotos() && legalPhotos() && cardphotos() && agree() && registerDedicatedLine();
	});
	$("#companyName").focus(function() {
		$("#companyNameMsg").text("");
	});
	$("#postCode").focus(function() {
		$("#postCodeMsg").text("");
	});
	$("#companyAddress").focus(function() {
		$("#companyAddressMsg").text("");
	});
	$("#companyPhone").focus(function() {
		$("#companyPhoneMsg").text("");
	});
	$("#legalPerson").focus(function() {
		$("#legalPersonMsg").text("");
	});
	$("#companyFax").focus(function() {
		$("#companyFaxMsg").text("");
	});
	$("#legalMobile").focus(function() {
		$("#legalMobileMsg").text("");
	});
	$("#companyTaxNo").focus(function() {
		$("#companyTaxNoMsg").text("");
	});
	$("#contacts").focus(function() {
		$("#contactsMsg").text("");
	});
	$("#financeEmail").focus(function() {
		$("#financeEmailMsg").text("");
	});
	$("#contactsMobile").focus(function() {
		$("#contactsMobileMsg").text("");
	});
	$("#companyInfo").focus(function() {
		$("#companyInfoMsg").text("");
	});
	$("#QQ").focus(function() {
		$("#QQMsg").text("");
	});
	$("#businessLicenses").focus(function() {
		$("#businessLicensesMsg").text("");
	});
	$("#companyPhotos").focus(function() {
		$("#companyPhotosMsg").text("");
	});
	$("#legalPhotos").focus(function() {
		$("#legalPhotosMsg").text("");
	});
	$("#cardphotos").focus(function() {
		$("#cardphotosMsg").text("");
	});
	$("#log").focus(function() {
		$("#logMsg").text("");
	});
	$("#agree").focus(function() {
		$("#agreeMsg").text("");
	});
});
function xiayibu(){
	var data = $("#register_dedicatedLine").serialize();
	$.ajax({
		url:"/logisticsc/user/getCode.shtml",
		data:data,
		type:'post',
		dataType:'json',
		success:function(data){
			var date = data.result;
			if(date == true){
				$(".denglu1").hide();
				$(".denglu2").show();
				return true;
			}else{
				$("#codeMsg").text('请输入正确的验证码！');
				return false;
			}
		}
	});
	return true;
}
function loginName(){
	var login_name = $('#loginName').val();
	if(login_name==null || login_name==''){
		$("#loginNameMsg").text("请输入手机号！");
		return false;
	}else{
		if(login_name.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(login_name)){
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
function password() {
	var password = $('#password').val();
	if(password==null || password==''){
		$("#passwordMsg").text('请输入密码！');
		return false;
	}else{
		if(password.length< 5 || password.length>20){
			$("#passwordMsg").text('密码字符长度为5-20位！');
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
		if(repassword.length< 5 || repassword.length>20){
			$("#repasswordMsg").text('确认密码字符长度为5-20位！');
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
function code(){
	 var code = $('#code').val();
 	if(code==null || code==''){
 		$("#codeMsg").text('请输入验证码！');
 		return false;
 	} 
 	return true;
}
//发送验证码	
function enterpriseSms(){
	var login_name = $('#loginName').val();
	if(login_name==null || login_name==''){
		$("#loginNameMsg").text("请输入手机号！");
		return false;
	}else{
		if(login_name.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(login_name)){
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
	//方式验证码
	$.ajax({
		type : "POST",
		url : "/logisticsc/user/verificationMessage.shtml",
		data : {loginName:login_name},
		success : function(data) {
			if(data.result){
				$('#sendcode').unbind('click');
    				$("#sendcode").css("background-color","#666666");
    				var time = 60;
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
//公司名称
function companyName(){
	var companyName = $('#companyName').val();
	if(companyName==null || companyName==''){
		$("#companyNameMsg").text("请输入公司名称！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(companyName)){
		$("#companyNameMsg").text("公司名称只能是中文！");
		return false;
	}else if(companyName.length < 3 || companyName.length > 20){
		$("#companyNameMsg").text("公司名称字符长度为3-20位！");
		return false;
	}
	return true;
}
//邮政编码
function postCode(){
	var re= /^[1-9]\d{5}$/;
	var postCode = $('#postCode').val();
	if(postCode == null || postCode == ""){
		return true;
	}
	if(!re.test(postCode)){
		$("#postCodeMsg").text("请输入正确的邮政编码！");
		return false;
	}
	return true;
}
//公司地址
function companyAddress(){
	var companyAddress = $('#companyAddress').val();
	if(companyAddress==null || companyAddress==''){
		$("#companyAddressMsg").text("请输入公司地址！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(companyAddress)){
		$("#companyAddressMsg").text("公司地址只能是中文！");
		return false;
	}else if(companyAddress.length < 3 || companyAddress.length > 30){
		$("#companyAddressMsg").text("公司地址字符长度为3-30位！");
		return false;
	}
	return true;
}
//公司电话
function companyPhone() {
	var companyPhone = $('#companyPhone').val();
	if(companyPhone == null ||companyPhone == ""){
		return true;
	}else if(!/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(companyPhone)){
		$("#companyPhoneMsg").text('请输入正确的公司电话!');
		return false;
	}
	return true;
}
//法定代表人
function legalPerson(){
	var legalPerson = $('#legalPerson').val();
	if(legalPerson==null || legalPerson==''){
		$("#legalPersonMsg").text("请输入法定代表人！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(legalPerson)){
		$("#legalPersonMsg").text("法定代表人只能是中文！");
		return false;
	}else if(legalPerson.length < 2 || legalPerson.length > 10){
		$("#legalPersonMsg").text("法定代表人字符长度为2-10位！");
		return false;
	}
	return true;
}
//公司传真
function companyFax(){
	var companyFax = $('#companyFax').val();
	if(companyFax == null ||companyFax == ""){
		return true;
	}else if(!/^(\d{3,4}-)?\d{7,8}$/.test(companyFax)){
		$("#companyFaxMsg").text("公司传真格式不对!xxx-xxxxxxxx");
		return false;
	}
	return true;
}
//法定人电话
function legalMobile(){
	var legalMobile = $('#legalMobile').val();
	if(legalMobile==null || legalMobile==''){
		$("#legalMobileMsg").text("请输入法定人电话！");
		return false;
	}else if(legalMobile.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(legalMobile)){
		$("#legalMobileMsg").text('请输入正确的手机号码!');
		return false;
	}
	return true;
}
//税务登记号
function companyTaxNo(){
	var companyTaxNo = $('#companyTaxNo').val();
	if(companyTaxNo == null || companyTaxNo == ""){
		return true;
	}else if(companyTaxNo.length < 3 || companyTaxNo.length > 20){
		$("#companyTaxNoMsg").text('税务登记号字符长度3-20!');
		return false;
	}
	/*else if(!/^\d{15}$/.test(companyTaxNo)){
		$("#companyTaxNoMsg").text('请输入正确税务登记号!');
		return false;
	}*/
	return true;
}
//联系人
function contacts(){
	var contacts = $('#contacts').val();
	if(contacts==null || contacts==''){
		$("#contactsMsg").text("请输入联系人！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(contacts)){
		$("#contactsMsg").text("联系人只能是中文！");
		return false;
	}else if(contacts.length < 2 || contacts.length > 10){
		$("#contactsMsg").text("联系人字符长度为2-10位！");
		return false;
	}
	return true;
}
//财务邮箱
function financeEmail(){
	var financeEmail = $('#financeEmail').val();
	if(financeEmail == null ||financeEmail ==""){
		return true;
	}else if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(financeEmail)){
		$("#financeEmailMsg").text("请输入正确的邮箱格式！");
		return false;
	}
	return true;
}
//联系人电话
function contactsMobile(){
	var contactsMobile = $('#contactsMobile').val();
	if(contactsMobile==null || contactsMobile==''){
		$("#contactsMobileMsg").text("请输入联系人电话！");
		return false;
	}else if(contactsMobile.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(contactsMobile)){
		$("#contactsMobileMsg").text('请输入正确的手机号码!');
		return false;
	}
	return true;
}
//公司介绍
function companyInfo(){
	var companyInfo = $('#companyInfo').val();
	if(companyInfo == null || companyInfo==""){
		return true;
	}else if(companyInfo.length < 15 || companyInfo.length > 50){
		$("#companyInfoMsg").text("公司介绍字符长度为15-50位！");
		return false;
	}
	return true;
}
function qq() {
	var qq = $('#QQ').val();
	if(qq == null || qq ==""){
		return true;
	}else if(!/^[1-9]{1}[0-9]{4,8}$/.test(qq)){
		$("#QQMsg").text("请输入正确的QQ号码！");
		return false;
	}
	return true;
}
//营业执照
function businessLicenses(){
	var businessLicenses = $('#businessLicenses').val();
	if(businessLicenses==null || businessLicenses==''){
		$("#businessLicensesMsg").text("请上传营业执照！");
		return false;
	}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(businessLicenses)){
		$("#businessLicensesMsg").text("请上传正确图片格式！");
		return false;
	}
	return true;
}
//公司照片
function companyPhotos(){
	var companyPhotos = $('#companyPhotos').val();
	if(companyPhotos==null || companyPhotos==''){
		$("#companyPhotosMsg").text("请上传公司照片！");
		return false;
	}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(companyPhotos)){
		$("#companyPhotosMsg").text("请上传正确图片格式！");
		return false;
	}
	return true;
}
//法人身份证照
function legalPhotos(){
	var legalPhotos = $('#legalPhotos').val();
	if(legalPhotos==null || legalPhotos==''){
		$("#legalPhotosMsg").text("请上传法人身份证照！");
		return false;
	}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(legalPhotos)){
		$("#legalPhotosMsg").text("请上传正确图片格式！");
		return false;
	}
	return true;
}
//名片照片
function cardphotos(){
	var cardphotos = $('#cardphotos').val();
	if(cardphotos==null || cardphotos==''){
		$("#cardphotosMsg").text("请上传名片照片！");
		return false;
	}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(cardphotos)){
		$("#cardphotosMsg").text("请上传正确图片格式！");
		return false;
	}
	return true;
}
function logMsg() {
	var log = $('#log').val();
	if(log==null || log==''){
		$("#logMsg").text("请上传公司Log照片！");
		return false;
	}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(log)){
		$("#logMsg").text("请上传正确图片格式！");
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
//专线注册
function registerDedicatedLine(){
	var option = {
	        url : '/logisticsc/user/doinsertDedicatedLineRegister.shtml',
	        type : 'post',
	        dataType : 'json',
	        headers : {"ClientCallMode" : "ajax"},
	        success : function(data) {
	        	if(data.result==true){
						$(".denglu1").hide();
						$(".denglu2").hide();
						$(".denglu3").show();
				}else{
					$("#errorModal").modal('show');
					 $("#errorModalMsgs").html(data.msg);
				}
	        },
	        error: function(data) {
	        	 $("#errorModal").modal('show');
				 $("#errorModalMsgs").html("系统错误");
	        }
	     };
	    $("#register_dedicatedLine").ajaxSubmit(option);
	    return false;
}