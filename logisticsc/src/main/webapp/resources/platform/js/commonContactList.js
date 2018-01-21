$(document).ready(function(){
	$('#myTab a:first').tab('show');//初始化显示哪个tab 
    $('#myTab a').click(function (e) { 
      e.preventDefault();//阻止a链接的跳转行为 
      $(this).tab('show');//显示当前选中的链接及关联的content 
    })
	getListCommonContactshouhuo();
	getListCommonContactfahuo();
	getListCommonDriver();
		
	//司机
	$("#tianjiasiji").click(function() {
		return driverNameMsg() && licenseNumberMsg() && phoneNumberMsgs() && dictionaryTypeMsg() && driver_provinces_fsg() && driver_citys_fsg() && driver_countys_fsg() && addressMsg() && addCommonDriver();
	});	
	$("#driverNameText").focus(function() {
		$("#driverNameMsg").text("");
	});
	$("#licenseNumberText").focus(function() {
		$("#licenseNumberMsg").text("");
	});
	$("#phoneNumberText").focus(function() {
		$("#phoneNumberMsgs").text("");
	});
	$("#dictionary_type").focus(function() {
		$("#dictionaryTypeMsg").text("");
	});
	$("#addressText").focus(function() {
		$("#addressMsg").text("");
	});
	$("#driver_provinces").focus(function() {
		$("#driver_provinces_fsg").text("");
	});
	$("#driver_citys").focus(function() {
		$("#driver_citys_fsg").text("");
	});
	$("#driver_countys").focus(function() {
		$("#driver_countys_fsg").text("");
	});
	//收货人
	$("#tianjiashouhuoren").click(function() {
		return contactsNameMsg() && companyNameMsg() && phoneNumberMsg() && telephoneMsg() && common_contact_provinceMsg() && common_contact_cityMsg() && common_contact_county_Msg() && addressSMsg() && addConsignee();
	});	
	$("#contactsNameS").focus(function() {
		$("#contactsNameMsg").text("");
	});
	$("#companyNameS").focus(function() {
		$("#companyNameMsg").text("");
	});
	$("#phoneNumberS").focus(function() {
		$("#phoneNumberMsg").text("");
	});
	$("#telephoneS").focus(function() {
		$("#telephoneMsg").text("");
	});
	$("#common_contact_province").focus(function() {
		$("#common_contact_provinceMsg").text("");
	});
	$("#common_contact_city").focus(function() {
		$("#common_contact_cityMsg").text("");
	});
	$("#common_contact_county").focus(function() {
		$("#common_contact_county_Msg").text("");
	});
	$("#addressS").focus(function() {
		$("#addressSMsg").text("");
	});
	//发货人
	$("#tianjiafahuoren").click(function() {
		return contactsNameFsg() && companyNameFsg() && phoneNumberFsg() && telephoneFsg() && common_contact_provinces_fsg() && common_contact_citys_fsg() && common_contact_countys_fsg() && addressFsg() && addConsignor();
	});	
	$("#contactsNameF").focus(function() {
		$("#contactsNameFsg").text("");
	});
	$("#companyNameF").focus(function() {
		$("#companyNameFsg").text("");
	});
	$("#phoneNumberF").focus(function() {
		$("#phoneNumberFsg").text("");
	});
	$("#telephoneF").focus(function() {
		$("#telephoneFsg").text("");
	});
	$("#common_contact_provinces").focus(function() {
		$("#common_contact_provinces_fsg").text("");
	});
	$("#common_contact_citys").focus(function() {
		$("#common_contact_citys_fsg").text("");
	});
	$("#common_contact_countys").focus(function() {
		$("#common_contact_countys_fsg").text("");
	});
	$("#addressF").focus(function() {
		$("#addressFsg").text("");
	});
})
function selectCommonDriver(){
	getListCommonDriver();
}
function selectCommonContactfahuo(){
	getListCommonContactfahuo();
}
function selectCommonContactshouhuo(){
	getListCommonContactshouhuo(); 
}
/******************************************司机*****************************************/
function qingkongsiji(){
	$('#driverNameText').val("");
	$('#licenseNumberText').val("");
	$('#phoneNumberText').val("");
	$('#dictionary_type').val("");
	$('#addressText').val("");
	$('#driver_provinces').val("");
	$('#driver_citys').val(0);
	$('#driver_countys').val(0);
}
function driverNameMsg(){
	var driverName = $('#driverNameText').val();
	if(driverName==null || driverName== ""){
		$("#driverNameMsg").text("请输入司机名称！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(driverName)){
		$("#driverNameMsg").text("司机名称只能是中文！");
		return false;
	}else if(driverName.length < 2 || driverName.length > 6){
		$("#driverNameMsg").text("司机名称字符长度为2-6位！");
		return false;
	}
	return true;
}
function licenseNumberMsg(){
	var licenseNumber = $('#licenseNumberText').val();
	if(licenseNumber==null || licenseNumber==""){
		$("#licenseNumberMsg").text("请输入司机车牌号！");
		return false;
	}else if(!/^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(licenseNumber)){
		$("#licenseNumberMsg").text("请输入正确的车牌号！");
		return false;
	}
	return true;
}
function phoneNumberMsgs(){
	var phoneNumber = $('#phoneNumberText').val();
	if(phoneNumber==null || phoneNumber==""){
		$("#phoneNumberMsgs").text("请输入司机手机号！");
		return false;
	}else if(phoneNumber.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(phoneNumber)){
		$("#phoneNumberMsgs").text('请输入正确的司机手机号!');
		return false;
	}
	return true;
}
function dictionaryTypeMsg(){
	var dictionaryType = $('#dictionary_type').val();
	if(dictionaryType == 0){
		$("#dictionaryTypeMsg").text("请选择车辆类型！");
		return false;
	}
	return true;
}
function addressMsg(){
	var address = $('#addressText').val();
	if(address==null || address==""){
		return true;
	}else if(/^[0-9a-zA-a]*$/.test(address)){
		$("#addressMsg").text("司机详细地址只能是中文！");
		return false;
	}else if(address.length < 3 || address.length > 30){
		$("#addressMsg").text("司机详细地址字符长度为3-30位！");
		return false;
	}
	return true;
}
function driver_provinces_fsg() {
	var common_contact_province = $('#driver_provinces').val();
	if(common_contact_province == 0){
		$("#driver_provinces_fsg").text("请选择省！");
		return false;
	}
	return true;
}
function driver_citys_fsg() {
	var common_contact_city = $('#driver_citys').val();
	if(common_contact_city== 0){
		$("#driver_citys_fsg").text("请选择市！");
		return false;
	}
	return true;
}
function driver_countys_fsg() {
	var common_contact_county = $('#driver_countys').val();
	if(common_contact_county== 0){
		$("#driver_countys_fsg").text("请选择区！");
		return false;
	}
	return true;
}
//显示增加司机
function displayCommonDriver(){
	getDictionary();
	getdriverProvinceContact();
	$('body').css("overflow","hidden")
	qingkongsiji();
	$("#addCommonDriver").modal({show : true});
	$('body').css("overflow","scroll");
}
//增加司机
function addCommonDriver(){
	var date = $("#add_common_driver").serialize();
	$.ajax({
		url:"/logisticsc/orderCenter/getCommonDriverId.shtml",
		data:date,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#licenseNumberMsg").text("司机车牌号已存在！");
			}else{
				$.ajax({
					url:"/logisticsc/orderCenter/selectPlatformCommonDriverPhone.shtml",
					data:date,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.result==true){
							$("#phoneNumberMsgs").text("司机手机号已存在！");
						}else{
							  $.ajax({
									url:"/logisticsc/orderCenter/doCommonDriver.shtml",
									data:date,
									type:'post',
									dataType:'json',
									success:function(data){
										if(data.result==true){
											$("#addCommonDriver").modal("hide");
											$("#successModal").modal('show');
											$("#successModalMsgs").html(data.msg);
											getListCommonDriver();
										}
									},
									error:function(data){
										$("#errorModal").modal('show');
										$("#errorModalMsgs").html(data.msg);
									}
								})
						}
					},
					error:function(data){
						$("#errorModal").modal('show');
						$("#errorModalMsgs").html(data.msg);
					}
				})
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html(data.msg);
		}
	})
}
//显示删除司机
function deletecommonDriver() {
    var commonDriver =[];//定义一个数组    
    $('input[name="commonDriver"]:checked').each(function(){
    	commonDriver.push($(this).val());
    });
    if(commonDriver.length <= 0){
    	$("#promptModal").modal('show');
		$("#promptModalMsgs").html("请选择一条数据!");
    }else{
    	 $.ajax({
    			url:"/logisticsc/orderCenter/deleteCommonDriver.shtml",
    			data: {'commonDriver' : JSON.stringify(commonDriver)},
    			type:'post',
    			dataType:'json',
    			success:function(data){
    				if(data.result==true){
    					$("#successModal").modal('show');
    					$("#successModalMsgs").html(data.msg);
    					getListCommonDriver();
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
}
//获取车辆类型
function getDictionary(){
	$("#dictionary_type").empty();
	var getDictionary="";
  	 $.ajax({
       url :"/logisticsc/orderCenter/getDictionary.shtml",
       type : 'POST',
       dataType : 'json',
       success:function(data){
      	 if(data.result==true){
      		 var data = data.data;
      		$("#dictionary_type").append("<option value='0'>请选择</option>");
      		 for(var i=0; i<data.length; i++) {	
      			 getDictionary += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>"; 
   			}
   		$("#dictionary_type").append(getDictionary);
         }
	 },error:function(data){
		 $("#errorModal").modal('show');
		 $("#errorModalMsgs").html("系统错误!");
	}
  }) 
}
/******************************************收货人*****************************************/
function qingkomgshouhuoren() {
	$('#contactsNameS').val("");
	$('#companyNameS').val("");
	$('#phoneNumberS').val("");
	$('#telephoneS').val("");
	$('#common_contact_province').val("");
	$('#common_contact_city').val(0);
	$('#common_contact_county').val(0);
	$('#addressS').val("");
}
function contactsNameMsg() {
	var contactsName = $('#contactsNameS').val();
	if(contactsName==null || contactsName==""){
		$("#contactsNameMsg").text("请输入收货人名称！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(contactsName)){
		$("#contactsNameMsg").text("收货人名称只能是中文！");
		return false;
	}else if(contactsName.length < 2 || contactsName.length > 10){
		$("#contactsNameMsg").text("司收货人名称字符长度为2-10位！");
		return false;
	}
	return true;
}
function companyNameMsg() {
	var companyName = $('#companyNameS').val();
	if(companyName==null || companyName==""){
		return true;
	}else if(/^[0-9a-zA-a]*$/.test(companyName)){
		$("#companyNameMsg").text("单位名称只能是中文！");
		return false;
	}else if(companyName.length < 2 || companyName.length > 10){
		$("#companyNameMsg").text("单位名称字符长度为2-10位！");
		return false;
	}
	return true;
}
function phoneNumberMsg() {
	var phoneNumber = $('#phoneNumberS').val();
	if(phoneNumber==null || phoneNumber==""){
		$("#phoneNumberMsg").text("请输入手机号！");
		return false;
	}else if(phoneNumber.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(phoneNumber)){
		$("#phoneNumberMsg").text('请输入正确的手机号!');
		return false;
	}
	return true;
}
function telephoneMsg() {
	var telephone = $('#telephoneS').val();
	if(telephone==null || telephone==""){
		$("#telephoneMsg").text("请输入固定号码！");
		return false;
	}else if(!/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(telephone)){
		$("#telephoneMsg").text('请输入正确的固定号码!格式xxx-xxxxxxxx|xxxxxxxxxxxx');
		return false;
	}
	return true;
}
function common_contact_provinceMsg() {
	var common_contact_province = $('#common_contact_province').val();
	if(common_contact_province == 0){
		$("#common_contact_provinceMsg").text("请选择省！");
		return false;
	}
	return true;
}
function common_contact_cityMsg() {
	var common_contact_city = $('#common_contact_city').val();
	if(common_contact_city== 0){
		$("#common_contact_cityMsg").text("请选择市！");
		return false;
	}
	return true;
}
function common_contact_county_Msg() {
	var common_contact_county = $('#common_contact_county').val();
	if(common_contact_county== 0){
		$("#common_contact_county_Msg").text("请选择区！");
		return false;
	}
	return true;
}
function addressSMsg() {
	var addressS = $('#addressS').val();
	if(addressS==null || addressS==""){
		$("#addressSMsg").text("请输入详细地址！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(addressS)){
		$("#addressSMsg").text("详细地址只能是中文！");
		return false;
	}else if(addressS.length < 3 || addressS.length > 30){
		$("#addressSMsg").text("详细地址字符长度为3-30位！");
		return false;
	}
	return true;
}
//显示增加收货人
function displayConsignee(){
	getProvince();
	$('body').css("overflow","hidden")
	qingkomgshouhuoren();
	$("#addConsignee").modal({show : true});
	$('body').css("overflow","scroll");
}
//增加收货人
function addConsignee(){
	var date = $("#add_common_consignee").serialize();
	$.ajax({
		url:"/logisticsc/orderCenter/getPlatCommonContactId.shtml",
		data:date,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#phoneNumberMsg").text("手机号已添加！");
			}else{
				$.ajax({
					url:"/logisticsc/orderCenter/doContacts.shtml",
					data:date,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.result==true){
							$("#addConsignee").modal("hide");
							$("#successModal").modal('show');
							$("#successModalMsgs").html(data.msg);
							getListCommonContactshouhuo();
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
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}
	})
}
//显示删除收货人
function deleteCommonConsignee() {
    var commonConsigneeId =[];//定义一个数组    
    $('input[name="commonConsigneeId"]:checked').each(function(){
    	commonConsigneeId.push($(this).val());
    });
    if(commonConsigneeId.length <= 0){
    	$("#promptModal").modal('show');
		$("#promptModalMsgs").html("请选择一条数据!");
    }else{
    	 $.ajax({
    			url:"/logisticsc/orderCenter/doUpdateContacts.shtml",
    			data: {'commonContact' : JSON.stringify(commonConsigneeId)},
    			type:'post',
    			dataType:'json',
    			success:function(data){
    				if(data.result==true){
    					$("#successModal").modal('show');
    					$("#successModalMsgs").html(data.msg);
    					getListCommonContactshouhuo();
    				}else{
    					$("#errorModal").modal('show');
    					$("#errorModalMsgs").html(data.msg);
    				}
    			},
    			error:function(data){
    				$("#errorModal").modal('show');
    				$("#errorModalMsgs").html(data.msg);
    			}
    		})
    }
}
//获取省
function getProvince(){
	var getXzqhInfo="";
 	 $.ajax({
      url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
      type : 'POST',
      dataType : 'json',
      success:function(data){
     	 if(data.result==true){
     		 var data = data.data;
     		$("#common_contact_province").append("<option value='0'>请选择</option>");
     		 for(var i=0; i<data.length; i++) {	
     			getXzqhInfo += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>"; 
  			}
  		$("#common_contact_province").append(getXzqhInfo);
        }
		},error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}
 	 })
}
//市
function ajaxCity(){
	var province=$("#common_contact_province").val();
	var data={pid:province};
	$("#common_contact_city").empty();
	$("#common_contact_county").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#common_contact_city").append("<option value='0'>请选择</option>"); 
				$("#common_contact_county").append("<option value='0'>请选择</option>"); 
				$.each(data.data,function(i){
					$("#common_contact_city").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}	
	})
}
//区
function ajaxCounty(){
	var city=$("#common_contact_city").val();
	var data={pid:city};
	$("#common_contact_county").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#common_contact_county").append("<option value='0'>请选择</option>"); 
				$.each(data.data,function(i){
					$("#common_contact_county").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})		
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}	
	})
}
/******************************************发货人*****************************************/
function qingkomgfahuoren() {
	$('#contactsNameF').val("");
	$('#companyNameF').val("");
	$('#phoneNumberF').val("");
	$('#telephoneF').val("");
	$('#common_contact_provinces').val("");
	$('#common_contact_citys').val(0);
	$('#common_contact_countys').val(0);
	$('#addressF').val("");
}
function contactsNameFsg() {
	var contactsName = $('#contactsNameF').val();
	if(contactsName==null || contactsName==""){
		$("#contactsNameFsg").text("请输入收货人名称！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(contactsName)){
		$("#contactsNameFsg").text("收货人名称只能是中文！");
		return false;
	}else if(contactsName.length < 2 || contactsName.length > 10){
		$("#contactsNameFsg").text("司收货人名称字符长度为2-10位！");
		return false;
	}
	return true;
}
function companyNameFsg() {
	var companyName = $('#companyNameF').val();
	if(companyName==null || companyName==""){
		return true;
	}else if(/^[0-9a-zA-a]*$/.test(companyName)){
		$("#companyNameFsg").text("单位名称只能是中文！");
		return false;
	}else if(companyName.length < 2 || companyName.length > 10){
		$("#companyNameFsg").text("单位名称字符长度为2-10位！");
		return false;
	}
	return true;
}
function phoneNumberFsg() {
	var phoneNumber = $('#phoneNumberF').val();
	if(phoneNumber==null || phoneNumber==""){
		$("#phoneNumberFsg").text("请输入手机号！");
		return false;
	}else if(phoneNumber.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(phoneNumber)){
		$("#phoneNumberFsg").text('请输入正确的手机号!');
		return false;
	}
	return true;
}
function telephoneFsg() {
	var telephone = $('#telephoneF').val();
	if(telephone==null || telephone==""){
		$("#telephoneFsg").text("请输入固定号码！");
		return false;
	}else if(!/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(telephone)){
		$("#telephoneFsg").text('请输入正确的固定号码!格式格式xxx-xxxxxxxx|xxxxxxxxxxxx');
		return false;
	}
	return true;
}
function common_contact_provinces_fsg() {
	var common_contact_provinces = $('#common_contact_provinces').val();
	if(common_contact_provinces == 0){
		$("#common_contact_provinces_fsg").text("请选择省！");
		return false;
	}
	return true;
}
function common_contact_citys_fsg() {
	var common_contact_citys = $('#common_contact_citys').val();
	if(common_contact_citys== 0){
		$("#common_contact_citys_fsg").text("请选择市！");
		return false;
	}
	return true;
}
function common_contact_countys_fsg() {
	var common_contact_countys = $('#common_contact_countys').val();
	if(common_contact_countys== 0){
		$("#common_contact_countys_fsg").text("请选择区！");
		return false;
	}
	return true;
}
function addressFsg() {
	var addressS = $('#addressF').val();
	if(addressS==null || addressS==""){
		$("#addressFsg").text("请输入详细地址！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(addressS)){
		$("#addressFsg").text("详细地址只能是中文！");
		return false;
	}else if(addressS.length < 3 || addressS.length > 30){
		$("#addressFsg").text("详细地址字符长度为3-30位！");
		return false;
	}
	return true;
}


//显示增加发货人
function displayConsignor(){
	getProvinceContact();
	$('body').css("overflow","hidden")
	qingkomgfahuoren();
	$("#addConsignor").modal({show : true});
	$('body').css("overflow","scroll");
}
//增加发货人
function addConsignor(){
	var date = $("#add_common_consignor").serialize();
	$.ajax({
		url:"/logisticsc/orderCenter/getPlatCommonContactId.shtml",
		data:date,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#phoneNumberFsg").text("手机号已添加！");
			}else{
				$.ajax({
					url:"/logisticsc/orderCenter/doContacts.shtml",
					data:date,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.result==true){
							$("#addConsignor").modal("hide");
							$("#successModal").modal('show');
							$("#successModalMsgs").html(data.msg);
							getListCommonContactfahuo();
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
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}
	})
}
//显示删除发货人
function deleteCommonConsignor() {
    var commonConsignorId =[];//定义一个数组    
    $('input[name="commonConsignorId"]:checked').each(function(){
    	commonConsignorId.push($(this).val());
    });
    if(commonConsignorId.length <= 0){
       	$("#promptModal").modal('show');
		$("#promptModalMsgs").html("请选择一条数据!");
    }else{
    	$.ajax({
    		url:"/logisticsc/orderCenter/doUpdateContacts.shtml",
    		data: {'commonContact' : JSON.stringify(commonConsignorId)},
    		type:'post',
    		dataType:'json',
    		success:function(data){
    			if(data.result==true){
    				$("#successModal").modal('show');
    				$("#successModalMsgs").html(data.msg);
    				getListCommonContactfahuo();
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
}
//获取省
function getProvinceContact(){
	var getXzqhInfo="";
 	 $.ajax({
      url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
      type : 'POST',
      dataType : 'json',
      success:function(data){
     	 if(data.result==true){
     		 var data = data.data;
     		$("#common_contact_provinces").append("<option value='0'>请选择</option>");
     		 for(var i=0; i<data.length; i++) {	
     			getXzqhInfo += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>"; 
  			}
  		$("#common_contact_provinces").append(getXzqhInfo);
        }
		},error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}
 	 })
}
//市
function ajaxCityContact(){
	var province=$("#common_contact_provinces").val();
	var data={pid:province};
	$("#common_contact_citys").empty();
	$("#common_contact_countys").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#common_contact_citys").append("<option value='0'>请选择</option>"); 
				$("#common_contact_countys").append("<option value='0'>请选择</option>"); 
				$.each(data.data,function(i){
					$("#common_contact_citys").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}	
	})
}
//区
function ajaxCountyContact(){
	var city=$("#common_contact_citys").val();
	var data={pid:city};
	$("#common_contact_countys").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#common_contact_countys").append("<option value='0'>请选择</option>"); 
				$.each(data.data,function(i){
					$("#common_contact_countys").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})		
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}	
	})
}
/*****************************************************************************/
//获取省
function getdriverProvinceContact(){
	var getXzqhInfo="";
 	 $.ajax({
      url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
      type : 'POST',
      dataType : 'json',
      success:function(data){
     	 if(data.result==true){
     		 var data = data.data;
     		$("#driver_provinces").append("<option value='0'>请选择</option>");
     		 for(var i=0; i<data.length; i++) {	
     			getXzqhInfo += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>"; 
  			}
  		$("#driver_provinces").append(getXzqhInfo);
        }
		},error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}
 	 })
}
//市
function ajaxdriverCityContact(){
	var province=$("#driver_provinces").val();
	var data={pid:province};
	$("#driver_citys").empty();
	$("#driver_countys").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#driver_citys").append("<option value='0'>请选择</option>"); 
				$("#driver_countys").append("<option value='0'>请选择</option>"); 
				$.each(data.data,function(i){
					$("#driver_citys").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
		}	
	})
}
//区
function ajaxdriverCountyContact(){
	var city=$("#driver_citys").val();
	var data={pid:city};
	$("#driver_countys").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#driver_countys").append("<option value='0'>请选择</option>"); 
				$.each(data.data,function(i){
					$("#driver_countys").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})		
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");
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
//司机	
function getListCommonDriver(){
	pageindex = $("#videoDivsiji").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var driverName = $("#driverName").val();
	var Conditions = {
			"driverName":driverName,
			"pageIndex":pageindex
		};
$.ajax({
	url:"/logisticsc/orderCenter/getListCommonDriver.shtml",
	data:Conditions,
	dataType:"json",
	type:"post",
	success:function(dataResult){
		var date = dataResult.data.params.rows;
		var page = dataResult.data;
		var body="";
		if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
				+"</tr>";
			var lineMoney=body;
			$("#getCommonDriver").html(lineMoney);
			return false;
		}
		for (var i = 0; i < date.length; i++) {
			if(null == date[i].address || '' == date[i].address || 'undefined' == date[i].address || date[i].address.length <= 0){
				date[i].address = "";
			}
			body+="<tr>"
				+"<td><input name='commonDriver' value='"+date[i].id+"' "+" type=checkbox></td>"
				+"<td>"+date[i].driverName+"</td>"
				+"<td>"+date[i].licenseNumber+"</td>"
				+"<td>"+date[i].phoneNumber+"</td>"
				+"<td>"+date[i].vehicleTypeName+"</td>"
				+"<td>"+date[i].address+"</td>"
				+"<td>"+formartDate(date[i].createTime)+"</td>"
				+"</tr>";
		}
		lineMoney=body;
		$("#getCommonDriver").html(lineMoney);
		var foot="<div style='margin-top: 10px; float: right;'>"+
		"<input type='hidden' class='currentPage'/>"+
		"<div id='videoDivsiji' class='page'></div></div>";
		$("#pageListsiji").html(foot);
		setPage(document.getElementById("videoDivsiji"),page.params.totalPage,page.pageIndex,getListCommonDriver);
	},
	error:function(error){
		body+="<tr>"
			+"<td style='color:red;' colspan='16' >没有数据</td>";
		+"</tr>";	
		var lineMoney=body;
		$("#getCommonDriver").html(lineMoney);
	}
});
}
//发货人
function getListCommonContactfahuo(){
	pageindex = $("#videoDivfahuo").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var contactsName = $("#contactsNameFahuoren").val();
	var Conditions = {
			"contactsName":contactsName,
			"contactsType":0,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getListCommonContact.shtml",
		data:Conditions,
		dataType:"json",
		type:"post",
		success:function(dataResult){
			var date = dataResult.data.params.rows;
			var page = dataResult.data;
			var body="";
			if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
				body+="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var lineMoney=body;
				$("#getListCommonConsignor").html(lineMoney);
				return false;
			}
			for (var i = 0; i < date.length; i++) {
				if(null == date[i].companyName || '' == date[i].companyName || 'undefined' == date[i].companyName || date[i].companyName.length <= 0){
					date[i].companyName = "";
				}
				body+="<tr>"
					+"<td><input name='commonConsignorId' value='"+date[i].id+"' type=checkbox></td>"
					+"<td>"+date[i].contactsName+"</td>"
					+"<td>"+date[i].companyName+"</td>"
					+"<td>"+date[i].phoneNumber+"</td>"
					+"<td>"+date[i].telephone+"</td>"
					+"<td>"+date[i].address+"</td>"
					+"<td>"+formartDate(date[i].createTime)+"</td>"
					+"</tr>";
			}
			lineMoney=body;
			$("#getListCommonConsignor").html(lineMoney);
			var foot="<div style='margin-top: 10px; float: right;'>"+
			"<input type='hidden' class='currentPage'/>"+
			"<div id='videoDivfahuo' class='page'></div></div>";
			$("#pageListfahuo").html(foot);
			setPage(document.getElementById("videoDivfahuo"),page.params.totalPage,page.pageIndex,getListCommonContactfahuo);
		},
		error:function(error){
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";	
			var lineMoney=body;
			$("#getListCommonConsignor").html(lineMoney);
		}
	});
}
//收货人
function getListCommonContactshouhuo(){
	pageindex = $("#videoDivshouhuo").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var contactsName = $("#contactsName").val();
	var Conditions = {
			"contactsName":contactsName,
			"contactsType":1,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getListCommonContact.shtml",
		data:Conditions,
		dataType:"json",
		type:"post",
		success:function(dataResult){
			var date = dataResult.data.params.rows;
			var page = dataResult.data;
			var body="";
			if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
				body+="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var lineMoney=body;
				$("#getListCommonConsignee").html(lineMoney);
				return false;
			}
			for (var i = 0; i < date.length; i++) {
				if(null == date[i].companyName || '' == date[i].companyName || 'undefined' == date[i].companyName || date[i].companyName.length <= 0){
					date[i].companyName = "";
				}
				body+="<tr>"
					+"<td><input value='"+date[i].id+"' name='commonConsigneeId' type=checkbox></td>"
					+"<td>"+date[i].contactsName+"</td>"
					+"<td>"+date[i].companyName+"</td>"
					+"<td>"+date[i].phoneNumber+"</td>"
					+"<td>"+date[i].telephone+"</td>"
					+"<td>"+date[i].address+"</td>"
					+"<td>"+formartDate(date[i].createTime)+"</td>"
					+"</tr>";
			}
			lineMoney=body;
			$("#getListCommonConsignee").html(lineMoney);
			var foot="<div style='margin-top: 10px; float: right;'>"+
			"<input type='hidden' class='currentPage'/>"+
			"<div id='videoDivshouhuo' class='page'></div></div>";
			$("#pageListshouhuo").html(foot);
			setPage(document.getElementById("videoDivshouhuo"),page.params.totalPage,page.pageIndex,getListCommonContactshouhuo);
		},
		error:function(error){
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";	
			var lineMoney=body;
			$("#getListCommonConsignee").html(lineMoney);
		}
	});
}