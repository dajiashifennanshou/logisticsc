var map;

$(function(){
	getLogisticsCompany();
	startAjaxProvince();
	endAjaxProvince();
	getListCompanCondition();
	province();
	getOutletsInfo();
	loadBMap();
	getCompanInfo();
	$("#go_logisticsc").click(function(){
		$.ajax({
			url:'../tms/login.shtml',
			type:'post',
			success:function(data){
				if(data.result){
					window.location.href="../tms/index.shtml";
				}else{
					$("#promptModal").modal('show');
					$("#promptModalMsgs").html(data.msg);
				}
			},
		})
	})
	/*调用方法如下：*/
	jqtab(".nav-con3",".nav-con4","click");
})
function jqtab(tabtit,tab_conbox,shijian){
		$(tab_conbox).find("li").hide();
		$(tabtit).find("li:first").addClass("cur").show(); 
		$(tab_conbox).find("li:first").show();
		$(tabtit).find("li").bind(shijian,function(){
			$(this).addClass("cur").siblings("li").removeClass("cur"); 
			var activeindex = $(tabtit).find("li").index(this);
			$(tab_conbox).children().eq(activeindex).show().siblings().hide();
			return false;
		});
	}
// 加载百度地图
function loadBMap(){
	map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
}
var name,mobile,phone,address;
function wangdianditu(id){
	$.ajax({
		url:"/logisticsc/company/getOutletsInfoId.shtml",
		data:{id:id},
		dataType:"json",
		type:"post",
		success:function(dataResult){
			if(dataResult.result ==true){
				var data = dataResult.data;
				 name = data.name;
				 mobile=data.mobile;
				 phone =data.phone;
				 address=data.provinceValue+data.cityValue+data.countyValue+data.address;
				 map.enableScrollWheelZoom();
				 map.enableContinuousZoom(); 
					var myGeo = new BMap.Geocoder();
					myGeo.getPoint(address, function(point){
						if (point) {
							map.centerAndZoom(point, 15);
							var marker = new BMap.Marker(point);
							map.addOverlay(marker);
							map.panTo(point);
							var infoWindow = new BMap.InfoWindow("网点名称："+name+"<br/>电话："+mobile+"/"+phone+"<br/>地址："+address+"");  // 创建信息窗口对象 
							marker.addEventListener("click", function(){ 
							map.openInfoWindow(infoWindow,point); 
							})
							$("#wangdianditu").modal({show : true});
						}else{
							$("#errorModal").modal('show');
							$("#errorModalMsgs").html("网点地址有错误，解析失败!");
						}
					});
			}else{
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html(data.msg);
			}
		},error:function(error){
		}
	});
	
}
function chaxun(){
	getListCompanCondition();
}
function wangdianchaxun(){
	getOutletsInfo();
}
function getCompanInfo(){
	var companyId = JSON.parse($("#companyId").val());
	pageindex = $("#pingjiaDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var Conditions = {
			"companyId":companyId,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/company/getCompanInfo.shtml",
		data:Conditions,
		dataType:"json",
		type:"post",
		success:function(dataResult){
			var date = dataResult.data.params.rows.evaluations;
			var data=dataResult.data.params.rows;
			var page = dataResult.data;
			$("#haopingdu").text(data.praise);
			$("#haopingrenshu").text(data.praisePeople);
			$("#zhongpingrenshu").text(data.commonlyPeople);
			$("#chapingrenshu").text(data.badPeople);
			$("#chaping").css("width",""+data.bad+"px");
			$("#zhongping").css("width",""+data.commonly+"px");
			$("#haoping").css("width",""+data.praise+"px");
			var body="";
			if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var lineMoney=body;
				$("#pingjia").html(lineMoney);
				return false;
			}
			for (var i = 0; i < date.length; i++) {
				var wid="";
				if(1 == date[i].evaluateLevel)
				{
				
						wid =28;
					
				}
				else if(2 == date[i].evaluateLevel)
				{
					
						wid =56;
					
				}
				else if(3 == date[i].evaluateLevel){
					
						wid =84;
					
				}
				else if(4==date[i].evaluateLevel){
					
						wid =112;
					
				}
				else {
					
						wid =140;
					
				}
				body+="<tr>"
					+"<td>"+date[i].loginName+"</td>"
					+"<td>"+date[i].startProvince+"-"+date[i].startCity+"-"+date[i].endProvince+"-"+date[i].endCity+"</td>"
					+"<td style='padding-left:20px;'>"+date[i].evaluateContent+"</td>"
					+"<td>"+formartDate(date[i].evaluateTime)+"</td>"
					+"<td><div class='cont'><div class='nr'><b rate='1' class='xx'></b><b rate='2' class='xx'></b><b rate='3' class='xx'></b><b rate='4' class='xx'></b><b rate='5' class='xx'></b><div class='star' style='width: "+wid+"px;'></div></div></div></td>"
					+"</tr>";
			}
			lineMoney=body;
			$("#pingjia").html(lineMoney);
			var foot="<div style='margin-top: 10px; float: right;'>"+
			"<input type='hidden' class='currentPage'/>"+
			"<div id='pingjiaDiv' class='page'></div></div>";
			$("#pingjiaPageList").html(foot);
			setPage(document.getElementById("pingjiaDiv"),page.params.totalPage,page.pageIndex,getCompanInfo);
		},error:function(error){
			body="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";	
			var lineMoney=body;
			$("#pingjia").html(lineMoney);
		}
	});
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
function getOutletsInfo(){
	var companyId = JSON.parse($("#companyId").val());
	pageindex = $("#wangdianDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	
	
	var Conditions = {
			"province":$("#province").val(),
			"city":$("#countys").val(),
			"name":$("#name").val(),
			"companyId":companyId,
			"pageIndex":pageindex
		};
	$.ajax({
		url:"/logisticsc/company/getOutletsInfo.shtml",
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
				$("#wangdian").html(lineMoney);
				return false;
			}
			for (var i = 0; i < date.length; i++) {
				body+="<tr>"
					+"<td>"+date[i].name+"</td>"
					+"<td>"+date[i].province+"-"+date[i].city+"-"+date[i].county+"-"+date[i].address+"</td>"
					+"<td>"+date[i].phone+"</td>"
					+"<td>"+date[i].contactPerson+"</td>"
					+"<td><input type='button' class='btn btn-info' onclick='wangdianditu("+date[i].id+")' value='查看'></td>"
					+"</tr>";
			}
			lineMoney=body;
			$("#wangdian").html(lineMoney);
			var foot="<div style='margin-top: 10px; float: right;'>"+
			"<input type='hidden' class='currentPage'/>"+
			"<div id='wangdianDiv' class='page'></div></div>";
			$("#wangdianPageList").html(foot);
			setPage(document.getElementById("wangdianDiv"),page.params.totalPage,page.pageIndex,getOutletsInfo);
		},
		error:function(error){
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";	
			var lineMoney=body;
			$("#wangdian").html(lineMoney);
		}
	});
}
function getListCompanCondition(){
	var companyId = JSON.parse($("#companyId").val());
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	if($('#start_input').val() == null || $('#start_input').val() == ""){
		$("#startProvince").val("");
		$("#startCity").val("");
		$("#startCounty").val("");
	}
	if($('#target_input').val() == null || $('#target_input').val() == ""){
		$("#targetProvince").val("");
		$("#targetCity").val("");
		$("#targetCounty").val("");
	}
	var Conditions = {
			"startProvince":$("#startProvince").val(),
			"startCity":$("#startCity").val(),
			"endProvince":$("#targetProvince").val(),
			"endCity":$("#targetCity").val(),
			"companyId":companyId,
			"pageIndex":pageindex
		};
	
	var isStartCounty = $('#isStartCounty').get(0).checked;
	var isEndCounty = $('#isEndCounty').get(0).checked;
	if(isStartCounty){
		Conditions.startCounty = $("#startCounty").val();
	}else{
		Conditions.startCounty = null;
	}
	if(isEndCounty){
		Conditions.endCounty = $("#targetCounty").val();
	}else{
		Conditions.endCounty = null;
	}
	
	$.ajax({
		url:"/logisticsc/company/getListCompanCondition.shtml",
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
				$("#fuwuxianlu").html(lineMoney);
				return false;
			}
			for (var i = 0; i < date.length; i++) {
				body+="<tr>"
					+"<td>"+"从:"+date[i].startProvinceValue+"-"+date[i].startCityValue+"-"+date[i].startCountyValue+"<br/>"+
					"到:"+date[i].endProvinceValue+"-"+date[i].endCityValue+"-"+date[i].endCountyValue+"</td>"
					+"<td>时效:"+date[i].timeLong+"小时"+"<br/>服务类型:"+date[i].transportMode+"<br/>运输方式:"+date[i].serverType+"</td>"
					+"<td style='position:relative;'>"+"<a href='#' onmouseover='display("+i+")' onmouseout='disappear("+i+")'>重货："+date[i].heavyCargoPriceLow+"/吨"+"</a><div id='box"+i+"' class='boxs2' onmouseover='display()' onmouseout='disappear()'><span><1吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceLow+"/吨</span><br/><span>1-3吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceMid+"/吨</span><br/><span>>3吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceHigh+"/吨</span></div><br/>"+"<a href='#' onmouseover='displays("+i+")' onmouseout='disappears("+i+")'>泡货："+date[i].bulkyCargoPriceLow+"/立方米"+"</a><div id='boxs"+i+"' class='boxs2' onmouseover='display()' onmouseout='disappear()'><span>0-3/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceLow+"/立方</span><br/><span>3-10/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceMid+"/立方</span><br/><span>>10/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceHigh+"/立方</span></div><br/>"+"最低价："+date[i].lowestPrice+"/票"+"</td>"
					+"<td>"+"已承运"+date[i].countOrder+"/票"+"<br/>"+"已有"+date[i].countOrderEvaluation+"条评价"+"</td>"
					+"<td>"+"<input type='button' class='btn btn-info' value='下单' onclick='toPlaceOrderPage("+date[i].id+")'><br/><input type='button'onclick='minenCollection("+date[i].id+")' class='btn btn-info' style='margin-top:5px;' value='收藏'>"+"</td>"
					+"</tr>";
			}
			lineMoney=body;
			$("#fuwuxianlu").html(lineMoney);
			var foot="<div style='margin-top: 10px; float: right;'>"+
			"<input type='hidden' class='currentPage'/>"+
			"<div id='videoDiv' class='page'></div></div>";
			$("#pageList").html(foot);
			setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getListCompanCondition);
		},
		error:function(error){
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";	
			var lineMoney=body;
			$("#fuwuxianlu").html(lineMoney);
		}
	});
}
function displays(index){
	document.getElementById("boxs"+index).style.display="block"; 
	}
function disappears(index){
	document.getElementById("boxs"+index).style.display="none"; 
	}
function display(index){
	document.getElementById("box"+index).style.display="block"; 
	}
function disappear(index){
	document.getElementById("box"+index).style.display="none"; 
	}
function qingkomg() {
	$("#username").val("");
	$("#password").val("");
	$("#checkCode").val("");
}
//跳转到下单页面
function toPlaceOrderPage(lineId){
	var startProvince = $("#startProvince").val();
	var startCity = $("#startCity").val();
	var startCounty = $("#startCounty").val();
	var endProvince = $("#targetProvince").val();
	var endCity = $("#targetCity").val();
	var endCounty = $("#targetCounty").val();
	
	if($("#userNames").val() == null || $("#userNames").val() == ""){
		qingkomg();
		$("#login").modal({show : true});
	}else{
		window.location.href = '/logisticsc/deliverGoods/placeOrder.shtml?lineId=' + lineId
		+ '&startProvince=' + startProvince + '&startCity=' + startCity + '&startCounty=' + startCounty
		+ '&endProvince=' + endProvince + '&endCity=' + endCity + '&endCounty=' + endCounty
	}
}
//我的收藏
function minenCollection(lineId){
	if($("#userNames").val() == null || $("#userNames").val() == ""){
		qingkomg();
		$("#login").modal({show : true});
	}else{
		$.ajax({
		       url :"/logisticsc/deliverGoods/getCollectionLineId.shtml",
		       data:{"lineId":lineId},
		       type : 'POST',
		       dataType : 'json',
		       success:function(data){
		      	 if(data.result==true){
		      		$("#promptModal").modal('show');
					$("#promptModalMsgs").html(data.msg);
		         }else{
		        	 $.ajax({
		      	       url :"/logisticsc/deliverGoods/doCollectionLine.shtml",
		      	       data:{"lineId":lineId},
		      	       type : 'POST',
		      	       dataType : 'json',
		      	       success:function(data){
		      	      	 if(data.result==true){
		      	      		$("#successModal").modal('show');
		      				$("#successModalMsgs").html("收藏成功");
		      	         }
		      		 },error:function(data){
		      			 $("#errorModal").modal('show');
		      				$("#errorModalMsgs").html(data.msg);
		      		}
		      	})
		         }
			 },error:function(data){
				 $("#errorModal").modal('show');
				 $("#errorModalMsgs").html("系统错误!");
			}
		})
	}
}
//网点地省
function province(){
	var getXzqhInfo="";
	 $.ajax({
     url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
     type : 'POST',
     dataType : 'json',
     success:function(data){
    	 if(data.result==true){
    		 var data = data.data;
    		$("#province").append("<option value=''>请选择</option>");
    		 for(var i=0; i<data.length; i++) {	
    			getXzqhInfo += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>"; 
 			}
 		$("#province").append(getXzqhInfo);
       }
		},error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}
	 })
}
//市
function contact(){
	var province=$("#province").val();
	var data={pid:province};
	$("#countys").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#countys").append("<option value=''>请选择</option>"); 
				$.each(data.data,function(i){
					$("#countys").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})
			}
		}
	})
}
//起始地省
function startAjaxProvince(){
	var getXzqhInfo="";
	 $.ajax({
     url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
     type : 'POST',
     dataType : 'json',
     success:function(data){
    	 if(data.result==true){
    		 var data = data.data;
    		$("#start_province_outlets").append("<option value=''>请选择</option>");
    		 for(var i=0; i<data.length; i++) {	
    			getXzqhInfo += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>"; 
 			}
 		$("#start_province_outlets").append(getXzqhInfo);
       }
		},error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}
	 })
}
//起始地市
function startAjaxCity(){
	var province=$("#start_province_outlets").val();
	var data={pid:province};
	$("#start_city_outlets").empty();
	$("#start_county_outlets").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#start_city_outlets").append("<option value=''>请选择</option>"); 
				$("#start_county_outlets").append("<option value=''>请选择</option>"); 
				$.each(data.data,function(i){
					$("#start_city_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}	
	})
	}
//起始地县
function startAjaxCounty(){
	var city=$("#start_city_outlets").val();
	var data={pid:city};
	$("#start_county_outlets").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#start_county_outlets").append("<option value=''>请选择</option>"); 
				$.each(data.data,function(i){
					$("#start_county_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})		
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}	
	})
}
//目的地省
function endAjaxProvince(){
	var getXzqhInfo="";
  	 $.ajax({
       url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
       type : 'POST',
       dataType : 'json',
       success:function(data){
      	 if(data.result==true){
      		 var data = data.data;
      		$("#end_province_outlets").append("<option value=''>请选择</option>");
      		 for(var i=0; i<data.length; i++) {	
      			getXzqhInfo += "<option value=\""+data[i].id+"\">"+data[i].name+"</option>"; 
   			}
   		$("#end_province_outlets").append(getXzqhInfo);
         }
	 },error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
	}
})
}
//目的地市
function endAjaxCity(){
	var province=$("#end_province_outlets").val();
	var data={pid:province};
	$("#end_city_outlets").empty();
	$("#end_county_outlets").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#end_city_outlets").append("<option value=''>请选择</option>"); 
				$("#end_county_outlets").append("<option value=''>请选择</option>"); 
				$.each(data.data,function(i){
					$("#end_city_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}	
	})
	}
//目的地县
function endAjaxCounty(){
	var city=$("#end_city_outlets").val();
	var data={pid:city};
	$("#end_county_outlets").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#end_county_outlets").append("<option value=''>请选择</option>"); 
				$.each(data.data,function(i){
					$("#end_county_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				})		
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}	
	})
}
/*****************************************************************/

//获取物流提供商公司信息
function getLogisticsCompany(){
	var companyId = JSON.parse($("#companyId").val());
	 $.ajax({
	  url :"/logisticsc/company/getLogisticsCompany.shtml",
	  data:{"companyId":companyId},
	  type : 'POST',
	  dataType : 'json',
	  success:function(data){
	 	 if(data.result==true){
	 		 if(null == data.data.companyName || '' == data.data.companyName || 'undefined' == data.data.companyName || data.data.companyName <= 0){
	 			$("#company_name").text("暂无信息");
	 			$("#companyName").text("暂无信息");
	 		 }else{
	 			$("#company_name").text(data.data.companyName);
	 			$("#companyName").text(data.data.companyName);
	 		 }
	 		if(null == data.data.companyPhone || '' == data.data.companyPhone || 'undefined' == data.data.companyPhone || data.data.companyPhone <= 0){
	 			 $("#company_phone").text("暂无信息");
	 		}else{
	 			 $("#company_phone").text(data.data.companyPhone);
	 		}
	 		
	 		if(null == data.data.contacts1 || '' == data.data.contacts1 || 'undefined' == data.data.contacts1 || data.data.contacts1 <= 0){
	 			 $("#contacts1").text("暂无信息");
	 		}else{
	 			 $("#contacts1").text(data.data.contacts1);
	 		}
	 		
	 		if(null == data.data.contacts1Mobile || '' == data.data.contacts1Mobile || 'undefined' == data.data.contacts1Mobile || data.data.contacts1Mobile <= 0){
	 			 $("#contacts1Mobile").text("暂无信息");
	 		}else{
	 			 $("#contacts1Mobile").text(data.data.contacts1Mobile);
	 		}
	 		
	 		if(null == data.data.contacts2 || '' == data.data.contacts2 || 'undefined' == data.data.contacts2 || data.data.contacts2 <= 0){
	 			 $("#contacts2").text("暂无信息");
	 		}else{
	 			 $("#contacts2").text(data.data.contacts2);
	 		}
	 		if(null == data.data.contacts2Mobile || '' == data.data.contacts2Mobile || 'undefined' == data.data.contacts2Mobile || data.data.contacts2Mobile <= 0){
	 			 $("#contacts2Mobile").text("暂无信息");
	 		}else{
	 			 $("#contacts2Mobile").text(data.data.contacts2Mobile);
	 		}
			if(null == data.data.companyInfo || '' == data.data.companyInfo || 'undefined' == data.data.companyInfo || data.data.companyInfo <= 0){
				$("#company_info").text("暂无信息");
			}else{
				$("#company_info").text(data.data.companyInfo);	
			}
			if(null == data.data.staffNumber || '' == data.data.staffNumber || 'undefined' == data.data.staffNumber || data.data.staffNumber <= 0){
				 $("#staff_number").text("暂无信息");
			}else{
				$("#staff_number").text(data.data.staffNumber+"人");
			}
			if(null == data.data.brandName || '' == data.data.brandName || 'undefined' == data.data.brandName || data.data.brandName <= 0 ){
				$("#brand_name").text("暂无信息");
			}else{
				$("#brand_name").text(data.data.brandName);
			}
			if(null == data.data.region || '' == data.data.region || 'undefined' == data.data.region || data.data.region <= 0){
				$("#region").text("暂无信息");
			}else{
				$("#region").text(data.data.region);
			}
			if(null == data.data.vehicleInfo || '' == data.data.vehicleInfo || 'undefined' == data.data.vehicleInfo || data.data.vehicleInfo <= 0){
				 $("#vehicle_info").text("暂无信息");
				}else{
					$("#vehicle_info").text(data.data.vehicleInfo);
				}
			if(null == data.data.warehouseInfo || '' == data.data.warehouseInfo || 'undefined' == data.data.warehouseInfo || data.data.warehouseInfo <= 0){
				 $("#warehouse_info").text("暂无信息");
				}else{
					$("#warehouse_info").text(data.data.warehouseInfo);
				}
			if(null == data.data.annualMoney || '' == data.data.annualMoney || 'undefined' == data.data.annualMoney || data.data.annualMoney <= 0){
				 $("#annual_money").text("暂无信息");
				}else{
					$("#annual_money").text(data.data.annualMoney);
					$("#noney_danwei").text("万元");
				}
			if(null == data.data.carriageGoods || '' == data.data.carriageGoods || 'undefined' == data.data.carriageGoods || data.data.carriageGoods <= 0){
				 $("#carriage_goods").text("暂无信息");
				}else{
					 $("#carriage_goods").text(data.data.carriageGoods);
				}
			if(null == data.data.serviceInfo || '' == data.data.serviceInfo || 'undefined' == data.data.serviceInfo || data.data.serviceInfo <= 0){
				$("#service_info").text("暂无信息");
				}else{
					$("#service_info").text(data.data.serviceInfo);
				}
			if(null == data.data.companyFax || '' == data.data.companyFax || 'undefined' == data.data.companyFax || data.data.companyFax <= 0){
				$("#company_fax").text("暂无信息");
				}else{
					$("#company_fax").text(data.data.companyFax);
				}
			if(null == data.data.companyAddress || '' == data.data.companyAddress || 'undefined' == data.data.companyAddress || data.data.companyAddress <= 0){
				$("#company_address").text("暂无信息");
			}else{
				$("#company_address").text(data.data.companyAddress);
			}
			var pic0 = document.getElementById("gongsitupian");
			pic0.src = "/logisticsc/img/retrive.shtml?resource="+data.data.logo;
	    }
	},error:function(data){
		$("#errorModal").modal('show');
		$("#errorModalMsgs").html("系统错误");
	}
	 })
}
//登陆
function login() {
	var username=$("#username").val();
    var password=$("#password").val();
    var reCode=$("#checkCode").val();
	if(null == username ||"" == username){
		$("#promptInfo").text("用户名不能为空！");
		myReload();
		return;
	}else if(null == password ||"" == password){
		$("#promptInfo").text("密码不能为空！");
		myReload();
		return;
	}else if(null == reCode ||"" == reCode){
		$("#promptInfo").text("验证码不能为空！");
		myReload();
		return;
	}
    $.ajax({
        url : "/logisticsc/user/loginUser.shtml",
        type : 'POST',
        data : {"loginName":username,"password":password,"reCode":reCode},
        dataType : 'json',
        success:function(data){
            if (data.result) {
            	$("#login").modal("hide");
            	window.location = "../deliverGoods/deliverGoods.shtml";
            } else {
               $("#promptInfo").text(data.msg);
           		myReload();
            }
        }
    });
}


var choice_flag = null;
$(function(){
	$('#start_input').click(function(e){
		$('.county_box').css('left','51px');
		choice_flag = 1;
		if($('.county_box').css('display') == 'none'){
			$('.county_box').toggle();
	        e.stopPropagation();
	        getXzqhList(100000, '' ,0);
		}
	});
	$('#target_input').click(function(e){
		$('.county_box').css('left','390px');
		choice_flag = 2;
		if($('.county_box').css('display') == 'none'){
			$('.county_box').toggle();
			
	        e.stopPropagation();
	        getXzqhList(100000, '' ,0);
		}
	});
	$('.choice-county, .county_box').click(function(e){
	    e.stopPropagation();
	});

	$('.county_box_nav td').click(function(e){
		$('.county_box_nav td').removeClass('active');
		$(this).addClass('active');
		var content = $(this).text();
		if(content == '省'){
			$('#content-province').show();
			$('#content-city').hide();
			$('#content-county').hide();
		}else if(content == '市'){
			$('#content-province').hide();
			$('#content-city').show();
			$('#content-county').hide();
		}else if(content == '县'){
			$('#content-province').hide();
			$('#content-city').hide();
			$('#content-county').show();
		}
	});
})

$(document).click(function() {
    $(".county_box").hide();
});
var line_html = '';
function getXzqhList(pid, name, num){
	if(choice_flag == 1){
		if(num == 1){
			line_html = '';
			line_html += name;
			$('#start_input').val(line_html);
			$('#startProvince').val(pid);
			$('#startCity').val('');
			$('#startCounty').val('');
		}else if(num == 2){
			if(line_html.indexOf('-') > 0){
				line_html = line_html.split('-')[0];
			}
			line_html += '-' + name;
			$('#start_input').val(line_html);
			$('#startCity').val(pid);
			$('#startCounty').val('');
		}else if(num == 3){
			if(line_html.indexOf('-') > 0){
				line_html = line_html.split('-')[0] + '-' + line_html.split('-')[1];
			}
			line_html += '-' + name;
			$('#start_input').val(line_html);
			$('#startCounty').val(pid);
		}
	}else if(choice_flag == 2){
		if(num == 1){
			line_html = '';
			line_html += name;
			$('#target_input').val(line_html);
			$('#targetProvince').val(pid);
			$('#targetCity').val('');
			$('#targetCounty').val('');
		}else if(num == 2){
			if(line_html.indexOf('-') > 0){
				line_html = line_html.split('-')[0];
			}
			line_html += '-' + name;
			$('#target_input').val(line_html);
			$('#targetCity').val(pid);
			$('#targetCounty').val('');
		}else if(num == 3){
			if(line_html.indexOf('-') > 0){
				line_html = line_html.split('-')[0] + '-' + line_html.split('-')[1];
			}
			line_html += '-' + name;
			$('#target_input').val(line_html);
			$('#targetCounty').val(pid);
		}
	}
	
	$.ajax({
		type : 'post',
		url : '/logisticsc/xzqh/getxzqhinfo.shtml',
		data : {'pid' : pid},
		success : function(result){
			if(num == 0){
				$('#content-province').html(buildXzqhHtml(result, new Number(num) + 1));
				$('#content-province').show();
				$('#content-city').hide();
				$('#content-county').hide();
				controlCountyBoxNav('省');
			}else if(num == 1){
				$('#content-city').html(buildXzqhHtml(result, new Number(num) + 1));
				$('#content-province').hide();
				$('#content-city').show();
				$('#content-county').hide();
				controlCountyBoxNav('市');
			}else if(num == 2){
				$('#content-county').html(buildXzqhHtml(result, new Number(num) + 1));
				$('#content-province').hide();
				$('#content-city').hide();
				$('#content-county').show();
				controlCountyBoxNav('县');
			}else if(num == 3){
				$(".county_box").hide();
			}
		}
	});
}

function buildXzqhHtml(data, num){
	var html = '';
	if(data == null || data == '' || typeof(data) == 'undefined'){
		return html;
	}
	html += '<ul>';
	for(var i = 0; i < data.length; i++){
		var name = "'" + data[i].name + "'";
		html += '<li><a href="javascript:void(0)" onclick="getXzqhList('+data[i].id+','+name+','+num+')">'+data[i].name+'</a></li>';
	}
	html += '</ul>';
	return html;
}

function controlCountyBoxNav(text){
	$('.county_box_nav td').removeClass('active');
	$('.county_box_nav td').each(function(e){
		if($(this).text() == text){
			$(this).addClass('active');
		}
	});
}