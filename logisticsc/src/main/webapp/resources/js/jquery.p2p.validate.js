//只能是字母或者汉字
jQuery.validator.addMethod("words", function (value, element) {
	var chinese = /^([A-Za-z]|[\u4E00-\u9FA5])+$/;
	return this.optional(element) || (chinese.test(value));
}, "只能是字母或者汉字");

//邮箱验证格式
jQuery.validator.addMethod("mail", function (value, element) {
	var chinese = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return this.optional(element) || (chinese.test(value));
}, "邮箱格式不正确");


//身份证验证格式
jQuery.validator.addMethod("idcard", function (value, element) {
	var chinese = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
	return this.optional(element) || (chinese.test(value));
}, "身份证格式不正确");


//手机号码验证格式
jQuery.validator.addMethod("phone", function (value, element) {
	var chinese = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	return this.optional(element) || (chinese.test(value));
}, "手机号码格式不正确");

//邮政编码验证格式
jQuery.validator.addMethod("zipcode", function (value, element) {
	var chinese = /^[1-9][0-9]{5}$/;
	return this.optional(element) || (chinese.test(value));
}, "邮政编码格式不正确");

//账号用户名验证，不包含特殊字符
jQuery.validator.addMethod("account", function (value, element) {
	var chinese = /^([a-zA-Z\u4e00-\u9fa5]+)$/;
	return this.optional(element) || (chinese.test(value));
}, "账号不能包含特殊字符");

//以http开头验证格式
jQuery.validator.addMethod("httpUrl", function (value, element) {
	var chinese = /^http:\/\// ;
	return this.optional(element) || (chinese.test(value));
}, "必须以http开头");

//座机号码验证格式
jQuery.validator.addMethod("telephone", function (value, element) {
	var chinese = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	return this.optional(element) || (chinese.test(value));
}, "座机号码格式不正确");

//QQ号码验证格式
jQuery.validator.addMethod("qq", function (value, element) {
	var chinese = /^[1-9]{1}[0-9]{4,8}$/;
	return this.optional(element) || (chinese.test(value));
}, "QQ号码格式不正确");


//正整数证格式
jQuery.validator.addMethod("positive", function (value, element) {
	//var chinese = /^[1-9]{1}[0-9]{4,8}$/;
	var chinese = /^[0-9]*[1-9][0-9]*$/;
	return this.optional(element) || (chinese.test(value));
}, "只能输入正整数");

//只能输入英文字母和数字
jQuery.validator.addMethod("wordNumber", function (value, element) {
	var wordNumber = /^[A-Za-z0-9]+$/;
	return this.optional(element) || (wordNumber.test(value));
}, "只能输入英文字母和数字");

//只能输入一个英文字母
jQuery.validator.addMethod("theletter", function (value, element) {
	var wordNumber = /^[A-Za-z]$/;
	return this.optional(element) || (wordNumber.test(value));
}, "只能输入首字母");
