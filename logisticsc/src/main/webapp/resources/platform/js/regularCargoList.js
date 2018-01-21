$(document).ready(function(){
	getCommonCargo();
	getPackageType();
	$("#baocun").click(function() {
		return cargoNameMsg() && cargoBrandMsg() && model() && packingInfoMsg()  && remarksMsg()  && addCommonCargo();
	});	
	$("#cargoNames").focus(function() {
		$("#cargoNameMsg").text("");
	});
	$("#cargoBrand").focus(function() {
		$("#cargoBrandMsg").text("");
	});
	$("#model").focus(function() {
		$("#modelMsg").text("");
	});
	$("#packingInfos").focus(function() {
		$("#packingInfoMsg").text("");
	});
	$("#remarks").focus(function() {
		$("#remarksMsg").text("");
	});
})
function qingkong(){
	$('#cargoNames').val("");
	$('#packingInfos').val(0);
	$('#remarks').val("");
	$('#cargoBrand').val("");
	$("#model").val("");
}
function selectCommonCargo(){
	getCommonCargo();
}
function cargoNameMsg() {
	var cargoName = $('#cargoNames').val();
	if( cargoName== null || cargoName==""){
		$("#cargoNameMsg").text("请输入货物名称！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(cargoName)){
		$("#cargoNameMsg").text("货物名称只能是中文！");
		return false;
	}else if(cargoName.length < 2 || cargoName.length > 6){
		$("#cargoNameMsg").text("货物名称字符长度为2-6位！");
		return false;
	}
	return true;
}
function cargoBrandMsg() {
	var cargoBrand = $('#cargoBrand').val();
	if( cargoBrand== null || cargoBrand==""){
		$("#cargoBrandMsg").text("请输入货物品牌！");
		return false;
	}else if(/^[0-9a-zA-a]*$/.test(cargoBrand)){
		$("#cargoBrandMsg").text("货物品牌只能是中文！");
		return false;
	}else if(cargoBrand.length < 2 || cargoBrand.length > 6){
		$("#cargoBrandMsg").text("货物品牌字符长度为2-6位！");
		return false;
	}
	return true;
}
function model() {
	var model = $("#model").val();
	if(model == null || model ==""){
		$("#modelMsg").text("请输入货物型号！");
		return false;
	}else if(model.length < 2 ||model.leng > 6){
		$("#modelMsg").text("货物型号字符长度为2-6位！");
		return false;
	}
	return true;
}
//function singleWeightMsg() {
//	var singleWeight = $('#singleWeight').val();
//	if( singleWeight== null || singleWeight==""){
//		$("#singleWeightMsg").text("请输入单件重量！");
//		return false;
//	}else if(!/^[0-9]{0}([0-9]|[.])+$/.test(singleWeight)){
//		$("#singleWeightMsg").text("单件重量只能是数字或者小数！");
//		return false;
//	}
//	return true;
//}
//function singleVolumeMsg() {
//	var singleVolume = $('#singleVolume').val();
//	if( singleVolume== null || singleVolume==""){
//		$("#singleVolumeMsg").text("请输入单件体积！");
//		return false;
//	}else if(!/^[0-9]{0}([0-9]|[.])+$/.test(singleVolume)){
//		$("#singleVolumeMsg").text("单件体积只能是数字或者小数！");
//		return false;
//	}
//	return true;
//}
function packingInfoMsg() {
	var packingInfo = $('#packingInfos').val();
	if(packingInfo == 0){
		$("#packingInfoMsg").text("请选择包装信息！");
		return false;
	}
	return true;
}
function remarksMsg() {
	var remarks = $('#remarks').val();
	if(remarks == null || remarks ==""){
		return true;
	}else if(remarks.length < 4 || remarks.length > 20){
		$("#remarksMsg").text("备注字符长度为4-20位！");
		return false;
	}
	return true;
}
//显示增加货物
function displayCommonCargo(){
	$('body').css("overflow","hidden")
	qingkong();
	$("#addCommonCargo").modal({show : true});
	$('body').css("overflow","scroll");
}
//增加货物
function addCommonCargo(){
	var date= $("#add_common_cargo").serialize();
	$.ajax({
		url:"/logisticsc/orderCenter/getCommonCargoName.shtml",
		data:date,
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#cargoNameMsg").text("货物名称已存在！");
			}else{
				$.ajax({
					url:"/logisticsc/orderCenter/doCommonCargo.shtml",
					data:date,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.result==true){
							$("#addCommonCargo").modal("hide");
							$("#successModal").modal('show');
							$("#successModalMsgs").html(data.msg);
							getCommonCargo();
						}else{
							$("#addCommonCargo").modal("hide");
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
function getCommonCargo(){
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var cargoName = $("#cargoName").val();
	var Conditions = {
			"cargoName":cargoName,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/orderCenter/getCommonCargo.shtml",
		data:Conditions,
		type:'post',
		dataType:'json',
		success:function(dataResult){
			var date = dataResult.data.params.rows;
			var page = dataResult.data;
			var body="";
			if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
				body+="<tr>"
					+"<td style='color:red;' colspan='16'>没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#getCommonCargo").html(rechargeRecord);
				return false;
			}
			for (var i = 0; i < date.length; i++) {
				if(null == date[i].remarks || '' == date[i].remarks || 'undefined' == date[i].remarks || date[i].remarks.length <= 0) {
					date[i].remarks = "";
				}
				if(null == date[i].cargoBrand || '' == date[i].cargoBrand || 'undefined' == date[i].cargoBrand || date[i].cargoBrand.length <= 0) {
					date[i].cargoBrand = "";
				}
				if(null == date[i].model || '' == date[i].model || 'undefined' == date[i].model || date[i].model.length <= 0) {
					date[i].model = "";
				}
				body+="<tr>"
					+"<td><input name='commonCargoId' value='"+date[i].id+"' type=checkbox></td>"
					+"<td>"+date[i].cargoName+"</td>"
					+"<td>"+date[i].cargoBrand+"</td>"
					+"<td>"+date[i].model+"</td>"
					+"<td>"+date[i].packingInfo+"</td>"
					+"<td>"+date[i].remarks+"</td>"
					+"</tr>";
			}
			rechargeRecord=body;
			$("#getCommonCargo").html(rechargeRecord);
			var foot="<div style='margin-top: 10px; float: right;'>"+
			"<input type='hidden' class='currentPage'/>"+
			"<div id='videoDiv' class='page'></div></div>";
			$("#pageList").html(foot);
			setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getCommonCargo);
		},
		error:function(error){
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";
			var rechargeRecord=body;
			$("#getCommonCargo").html(rechargeRecord);
		}
	})
}
//显示删除货物
function deleteCommonCargo() {
    var commonCargoId =[];//定义一个数组    
    $('input[name="commonCargoId"]:checked').each(function(){
    	commonCargoId.push($(this).val());
    });
    if(commonCargoId.length <= 0){
    	$("#promptModal").modal('show');
		$("#promptModalMsgs").html("请选择一条数据!");
    }else{
    	$.ajax({
    		url:"/logisticsc/orderCenter/doUpdateCommonCargo.shtml",
    		data: {'commonCargoIds' : JSON.stringify(commonCargoId)},
    		type:'post',
    		dataType:'json',
    		success:function(data){
    			if(data.result==true){
    				$("#successModal").modal('show');
    				$("#successModalMsgs").html(data.msg);
    				getCommonCargo();
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
//包装信息
function getPackageType(){
	$("#packingInfos").empty();
	$("#packingInfos").append("<option value='0'>请选择</option>");
	var getDictionary="";
  	 $.ajax({
       url :"/logisticsc/deliverycargo/getpackagetype.shtml",
       type : 'POST',
       dataType : 'json',
       success:function(result){
    		for(var i=0; i<result.length; i++) {
    			 getDictionary += "<option value=\""+result[i].name+"\">"+result[i].name+"</option>"; 
 			}
    	   $("#packingInfos").append(getDictionary);
	 }
  }) 
}