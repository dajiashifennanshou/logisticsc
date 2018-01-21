var comanyId=0;
$(document).ready(function(){
	doCompany();
	$("#shenqingqiye").click(function() {
		companyName() && postCode() && companyAddress() && companyPhone() && legalPerson() && companyFax() && legalMobile() && companyTaxNo() && contacts() && financeEmail() && contactsMobile() && companyInfo() && qq() && businessLicenses() && companyPhotos() && legalPhotos() && cardphotos() && log() && agree() && applyEnterprises();
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
	$("#preview0").focus(function() {
		$("#tupianMsg").text("");
	});
	$("#preview1").focus(function() {
		$("#tupianMsg").text("");
	});
	$("#preview2").focus(function() {
		$("#tupianMsg").text("");
	});
	$("#preview3").focus(function() {
		$("#tupianMsg").text("");
	});
	$("#agree").focus(function() {
		$("#agreeMsg").text("");
	});
})
function agree(){
	if(!$("input[type='checkbox']").is(':checked')){
		$("#agreeMsg").text("不同意协议，无法注册！");
		return false;
	}
	return true;
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
	}/*else if(!/^\d{15}$/.test(companyTaxNo)){
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
var maxsize = 512000; //图片大少
//营业执照
function businessLicenses(){
	if(comanyId == 0){
		var businessLicenses = $('#fileName0').val();
		if(businessLicenses==null || businessLicenses==''){
			$("#tupianMsg").text("请上传营业执照！");
			return false;
		}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(businessLicenses)){
			$("#tupianMsg").text("传营业执照格式不正确！");
			return false;
		}else {
			var businessLicensess = document.getElementById("fileName0");
			var size = businessLicensess.files[0].size;
			 if(size >maxsize){
				$("#tupianMsg").text("传营业执照图片不能大于512KB！");
				return false;
			}
		}
	}
	return true;
}
//公司照片
function companyPhotos(){
	if(comanyId == 0){
	var companyPhotos = $('#fileName1').val();
	if(companyPhotos==null || companyPhotos==''){
		$("#tupianMsg").text("请上传公司照片！");
		return false;
	}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(companyPhotos)){
		$("#tupianMsg").text("公司照片格式不正确！");
		return false;
	}else {
		var businessLicensess = document.getElementById("fileName1");
		var size = businessLicensess.files[0].size;
		 if(size >maxsize){
			$("#tupianMsg").text("公司照片格式图片不能大于512KB！");
			return false;
		}
	}
	}
	return true;
}
//法人身份证照
function legalPhotos(){
	if(comanyId == 0){
	var legalPhotos = $('#fileName2').val();
	if(legalPhotos==null || legalPhotos==''){
		$("#tupianMsg").text("请上传法人身份证照！");
		return false;
	}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(legalPhotos)){
		$("#tupianMsg").text("法人身份证照格式不正确！");
		return false;
	}else {
		var businessLicensess = document.getElementById("fileName2");
		var size = businessLicensess.files[0].size;
		 if(size >maxsize){
			$("#tupianMsg").text("法人身份证照不能大于512KB！");
			return false;
		}
	}
	}
	return true;
}
//名片照片
function cardphotos(){
	if(comanyId == 0){
		var cardphotos = $('#fileName3').val();
		if(cardphotos==null || cardphotos==''){
			$("#tupianMsg").text("请上传名片照片！");
			return false;
		}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(cardphotos)){
			$("#tupianMsg").text("名片照片格式不正确！");
			return false;
		}else {
			var businessLicensess = document.getElementById("fileName3");
			var size = businessLicensess.files[0].size;
			 if(size >maxsize){
				$("#tupianMsg").text("名片照片不能大于512KB！");
				return false;
			}
		}
	}
	return true;
}
//
function log(){
	if(comanyId == 0){
		var cardphotos = $('#fileName4').val();
		if(cardphotos==null || cardphotos==''){
			$("#tupianMsg").text("请上传LOG照片！");
			return false;
		}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(cardphotos)){
			$("#tupianMsg").text("名片照片格式不正确！");
			return false;
		}else {
			var businessLicensess = document.getElementById("fileName4");
			var size = businessLicensess.files[0].size;
			 if(size >maxsize){
				$("#tupianMsg").text("LOG照片不能大于512KB！");
				return false;
			}
		}
	}
	return true;
}
//查询公司信息  --包括图片信息
function doCompany(){
	$.ajax({
		url:"/logisticsc/personalCenter/getCompanyInfo.shtml",
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				var date = data.data;
				comanyId=date.id;
				$("#id").val(date.id);
				$("#companyName").val(date.companyName);
				$("#companyAddress").val(date.companyAddress);
				$("#legalPerson").val(date.legalPerson);
				$("#legalMobile").val(date.legalMobile);
				$("#contacts").val(date.contacts);
				$("#contactsMobile").val(date.contactsMobile);
				$("#QQ").val(date.qq);
				$("#postCode").val(date.postCode);
				$("#companyPhone").val(date.companyPhone);
				$("#companyFax").val(date.companyFax);
				$("#companyBank").val(date.companyBank);
				$("#companyBankNo").val(date.companyBankNo);
				$("#companyTaxNo").val(date.companyTaxNo);
				$("#financeEmail").val(date.financeEmail);
				$("#companyInfo").val(date.companyInfo);
				$("#companyCode").val(date.companyCode);
				var pic0 = document.getElementById("preview0");
				var pic1 = document.getElementById("preview1");
				var pic2 = document.getElementById("preview2");
				var pic3 = document.getElementById("preview3");
				var pic4 = document.getElementById("preview4");
				pic0.src = "/logisticsc/img/retrive.shtml?resource="+date.businessLicense;
				pic1.src = "/logisticsc/img/retrive.shtml?resource="+date.companyPhoto;
				pic2.src = "/logisticsc/img/retrive.shtml?resource="+date.legalPhoto;
				pic3.src = "/logisticsc/img/retrive.shtml?resource="+date.cardPhoto;
				pic4.src = "/logisticsc/img/retrive.shtml?resource="+date.logo;
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");	
		}
	})
}
function tiaozhuan(){
	window.location.href="/logisticsc/deliverGoods/deliverGoods.shtml"; 
}
//申请企业货主
function applyEnterprises(){
	var option = {
        url : '/logisticsc/applyCenter/applyEnterprise.shtml',
        type : 'post',
        dataType : 'json',
        headers : {"ClientCallMode" : "ajax"},
        success : function(data) {
        	if(data.result==true){
        		$("#successModal").modal('show');
        		$("#successModalMsgs").html("申请企业货主成功，审核通过，将会用短信通知你！");
			}else{
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html(data.msg);
			}
        }
     };
    $("#aaaaa").ajaxSubmit(option);
    return false;
}
function updateimg(obj,index){
	var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
	if(ie){ 
	document.getElementById('fileName'+index).click(); 
	}else{
	var a=document.createEvent("MouseEvents");//FF的处理 
	a.initEvent("click", true, true);  
	document.getElementById('fileName'+index).dispatchEvent(a);
	}
}
function fileNmae(index){
var docObj = document.getElementById("fileName"+index); 
var imgObjPreview = document.getElementById("preview"+index); 
if (docObj.files && docObj.files[0]) { 
	/*火狐下，直接设img属性*/
	imgObjPreview.style.display = 'block'; 
	imgObjPreview.style.width = '55px'; 
	imgObjPreview.style.height = '55px'; 
	/*imgObjPreview.src = docObj.files[0].getAsDataURL();*/ 
	/*火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式*/ 
	imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]); 
} else { 
	/*IE下，使用滤镜*/ 
	docObj.select(); 
	var imgSrc = document.selection.createRange().text; 
	var localImagId = document.getElementById("localImag"); 
	/*必须设置初始大小*/ 
	localImagId.style.width = "55px"; 
	localImagId.style.height = "55px"; 
	/*图片异常的捕捉，防止用户修改后缀来伪造图片*/ 
try { 
	localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc; 
} catch (e) { 
	alert("您上传的图片格式不正确，请重新选择!"); 
return false; 
} 
	imgObjPreview.style.display = 'none'; 
	document.selection.empty(); 
} 
	return true; 
}