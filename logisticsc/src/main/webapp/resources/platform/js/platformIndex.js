$(function(){
	startAjaxProvince();
	endAjaxProvince();
	getUserCompany();
	getHotLine();
	getRecentlyOrder();
	getUserCount();
	getHomeOrderCity();
	getPartner();
	companyNews();
	infoNews();
	$('#demo3').slideBox({
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'linear',//swing,linear//滚动特效
		delay : 5,//滚动延迟时间，单位：秒
		hideClickBar : false,//不自动隐藏点选按键
			clickBarRadius : 10
	});
	//查询
	jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
		$(tab_conbox).find("li").hide();
	$(tabtit).find("li:first").addClass("cur").show(); 
	$(tab_conbox).find("li:first").show();
	$(tabtit).find("li").bind(shijian,function(){
	  $(this).addClass("cur").siblings("li").removeClass("cur"); 
		var activeindex = $(tabtit).find("li").index(this);
			$(tab_conbox).children().eq(activeindex).show().siblings().hide();
			return false;
		});
	};
	/*调用方法如下：*/
	$.jqtab(".banner-top",".banner-bot","click");
	$(".con1-zxgl").mouseenter(function(){
	$(this).addClass("biankuang").siblings().removeClass("biankuang");
	})
	var oTop = document.getElementById("zhiding");
	var screenw = document.documentElement.clientWidth || document.body.clientWidth;
	var screenh = document.documentElement.clientHeight || document.body.clientHeight;
	oTop.style.left = screenw - oTop.offsetWidth +"px";
	oTop.style.top = screenh - oTop.offsetHeight + "px";
	window.onscroll = function(){
		var scrolltop = document.documentElement.scrollTop || document.body.scrollTop;
	    oTop.style.top = screenh - oTop.offsetHeight + scrolltop +"px";
	    }
	    oTop.onclick = function(){
	    	document.documentElement.scrollTop = document.body.scrollTop =0;
	   }
	})
//热门城市	
function getHomeOrderCity() {
	$.ajax({
		url:"/logisticsc/homeCenter/getHomeOrderCity.shtml",
		dataType:"json",
		type:"post",
		success:function(data){
			var data = data.data;
			var top="<ul>";
			var body="";	
			if(null == data || '' == data || 'undefined' == data || data.length <= 0) {
				body+="<li style='color:red;' colspan='16'>没有热门城市</li>"
					+"</ul>";
				var company = top+body;
				$("#getHomeOrderCity").html(company);
				return false;
			}
			for (var i = 0; i < data.length; i++) {
				body+="<li><a href='/logisticsc/deliverGoods/deliverGoods.shtml?startProvince="+data[i].startProvince+"&startCity="+data[i].startCity+"'>"+data[i].startCityValue+"</a></li>";
			}
			var max ="</ul>";
			var company = top+body+max;
			$("#getHomeOrderCity").html(company);
		},
		error:function(error){
			var body = '';
			body+="<li style='color:red;' colspan='16'>没有热门城市</li>"
				+"</ul>";
			var company = top+body;
			$("#getHomeOrderCity").html(company);
		}
	});
}
//获取最近下单记录
	function getRecentlyOrder(){
		$.ajax({
			url:"/logisticsc/homeCenter/getRecentlyOrd.shtml",
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.result) {
					var data = data.rows,
						li = '',
						lstRncOrd = $("#last_order ul");
					for (var i = 0; i < data.length; i++) {
						var order = data[i],
							substr = order.platformUser.loginName.substring(0,7)+"****";
						li = "<li>会员："+substr+"，承运方："+order.platformUserCompany.companyName+order.lineInfo.startOutletsName+"，提交订单成功</li>";
						lstRncOrd.append(li);
					}
				}
			},
			error:function(error){
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html("系统错误");
			}
		});
	}
function getUserCount() {
	$.ajax({
		url:"/logisticsc/homeCenter/getUserCount.shtml",
		dataType:"json",
		type:"post",
		success:function(data){
				date = data.data;
				$("#finalPriceCount").text(date.finalPriceCount);
				$("#orderCount").text(date.orderCount);
				$("#userCompanyCount").text(date.userCompanyCount);
				$("#userProviderCount").text(date.userProviderCount);
		},
		error:function(error){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}
	});
}
//推荐物流商
function getUserCompany() {
	$.ajax({
		url:"/logisticsc/homeCenter/getUserCompany.shtml",
		dataType:"json",
		type:"post",
		success:function(dataResult){
			var data = dataResult.rows;
			var top="<ul>";
			var body="";	
			if(null == data || '' == data || 'undefined' == data || data.length <= 0) {
				body+="<li style='color:red;' colspan='16'>没有推荐物流商</li>"
					+"</ul>";
				var company = top+body;
				$("#getUserCompany").html(company);
				return false;
			}
			for (var i = 0; i < data.length; i++) {
				body+="<li><a href='/logisticsc/deliverGoods/deliverGoods.shtml?companyNames="+data[i].companyName+"'>"+data[i].companyName+"</a></li>";
			}
			var max ="</ul>";
			var company = top+body+max;
			$("#getUserCompany").html(company);
		},
		error:function(error){
			var body = '';
			body+="<li style='color:red;' colspan='16'>没有推荐物流商</li>"
				+"</ul>";
			var company = top+body;
			$("#getUserCompany").html(company);
		}
	});
}
//获取 热门线路
function getHotLine () {
	$.ajax({
		url:"/logisticsc/homeCenter/getHotLine.shtml",
		dataType:"json",
		type:"post",
		success:function(dataResult){
			var data = dataResult.rows,
				grid_hls = $("#hot_line_s");
			var body='';
			if(null == data || '' == data || 'undefined' == data || data.length <= 0) {
				body+="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				grid_hls.html(body);
				return false;
			}
			for (var i = 0; i < data.length; i++) {
				var date = data[i];
				var wid="";
				if(1 == date.evaluateLevel)
				{
					wid =28;
				}
				else if(2 == date.evaluateLevel)
				{
						wid =56;
				}
				else if(3 == date.evaluateLevel){
						wid =84;
				}
				else if(4==date.evaluateLevel){
						wid =112;
				}
				else if(5==date.evaluateLevel){
						wid =140;
				}
				tr = '<tr><td><img src="/logisticsc/resources/platform/img/qq.png">'+date.platformUserCompany.companyName+'</td>'+
					"<td>从："+date.lineInfo.startOutletsObj.provinceValue+'-'+date.lineInfo.startOutletsObj.cityValue+"</br>到："+date.lineInfo.endOutletsObj.provinceValue+'-'+date.lineInfo.endOutletsObj.cityValue+"</td>"+
					"<td style='position:relative;'><a href='#' onmouseover='display("+i+")' onmouseout='disappear("+i+")'>重货：￥"+date.lineInfo.heavyCargoPriceLow+"/吨</a><div id='box"+i+"' class='boxs' onmouseover='display()' onmouseout='disappear()'><span><1吨&nbsp;&nbsp;￥"+date.lineInfo.heavyCargoPriceLow+"/吨</span><br/><span>1-3吨&nbsp;&nbsp;￥"+date.lineInfo.heavyCargoPriceMid+"/吨</span><br/><span>>3吨&nbsp;&nbsp;￥"+date.lineInfo.heavyCargoPriceHigh+"/吨</span></div><br/>" +
					"<a href='#' onmouseover='displays("+i+")' onmouseout='disappears("+i+")'>泡货：￥"+date.lineInfo.bulkyCargoPriceLow+"/立方</a><div id='boxs"+i+"' class='boxs' onmouseover='display()' onmouseout='disappear()'><span>0-3/立方&nbsp;&nbsp;￥"+date.lineInfo.bulkyCargoPriceLow+"/立方</span><br/><span>3-10/立方&nbsp;&nbsp;￥"+date.lineInfo.bulkyCargoPriceMid+"/立方</span><br/><span>>10/立方&nbsp;&nbsp;￥"+date.lineInfo.bulkyCargoPriceHigh+"/立方</span></div></br/>"+
					"最低价：￥"+date.lineInfo.lowestPrice+"/票</td>"+
					"<td><div class='cont'><div class='nr'><b rate='1' class='xx'></b><b rate='2' class='xx'></b><b rate='3' class='xx'></b><b rate='4' class='xx'></b><b rate='5' class='xx'></b><div class='star' style='width: "+wid+"px;'></div></div></div></td>"+						
					"<td><button class='fahuo' onclick=toPlaceOrderPage("+date.lineInfo.id+")>在线发货</button><button class='fahuo ju-top' onclick='minenCollection("+date.lineInfo.id+")'>收藏</button></td></tr>";
				grid_hls.append(tr);
			}
		},
		error:function(error){
			var body = '';
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";	
			$("#grid_hls").html(body);
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
// 跳转到下单页面
function toPlaceOrderPage(lineId){
	if($("#userNames").val() == null || $("#userNames").val() == ""){
		qingkomg();
		$("#login").modal({show : true});
	}else{
		window.location.href = "/logisticsc/deliverGoods/placeOrder.shtml?lineId="+lineId;
	}
}
//我的收藏
function minenCollection(lineId){
	if($("#userNames").val() == null || $("#userNames").val() == ""){
		qingkomg();
		$("#login").modal({show : true});
	}else{
		 $.ajax({
		       url :"/logisticsc/deliverGoods/doCollectionLine.shtml",
		       data:{"lineId":lineId},
		       type : 'POST',
		       dataType : 'json',
		       success:function(data){
		      	 if(data.result==true){
		      		$("#successModal").modal('show');
					$("#successModalMsgs").html(data.msg);
		         }else{
		        	 $("#errorModal").modal('show');
						$("#errorModalMsgs").html(data.msg);
		         }
			 },error:function(data){
				 $("#errorModal").modal('show');
					$("#errorModalMsgs").html("系统错误!");
			}
		})
	}
}
//我要发货
function deliverGoods(){
	var startProvince = $("#start_province_outlets").val()?$("#start_province_outlets").val():'',
		startCity = $("#start_city_outlets").val()?$("#start_city_outlets").val():'',
		startCounty = $("#start_county_outlets").val()?$("#start_county_outlets").val():'',
		endProvince = $("#end_province_outlets").val()?$("#end_province_outlets").val():'',
		endCity = $("#end_city_outlets").val()?$("#end_city_outlets").val():'',
		endCounty = $("#end_county_outlets").val()?$("#end_county_outlets").val():'';
		window.location.href="/logisticsc/deliverGoods/deliverGoods.shtml?startProvince="+startProvince+
		"&startCity="+startCity+"&startCounty="+startCounty+"&endProvince="+endProvince+"&endCity="+endCity+
		"&endCounty="+endCounty;
	
}
function wuliushang() {
	var wuliushangmingcheng = $("#wuliushangmingcheng").val();
	window.location.href=encodeURI("/logisticsc/deliverGoods/deliverGoods.shtml?companyNames="+wuliushangmingcheng);
}
//订单跟踪
function dingdangenzong() {
	var dingdanhao = $("#dingdanhao").val();
	window.location.href="/logisticsc/orderTracking/toorderorderTracking.shtml?condition="+dingdanhao;
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



//请求验证码
function myReload(){  
    document.getElementById("createCheckCode").src=document.getElementById("createCheckCode").src + "?nocache="+new Date().getTime();  
}
//页面加载
$(function(){
	 // 表单回车事件
    $('#theForm').keydown(function(e){
        if( e.keyCode == 13 ){
        	login();
        }
    });
});
function qingkomg() {
	$("#username").val("");
	$("#password").val("");
	$("#checkCode").val("");
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
            	window.location = "/logisticsc/platform-index.jsp";
            } else {
               $("#promptInfo").text(data.msg);
           		myReload();
            }
        }
    });
}
function getPartner() {
	$.ajax({
        url : "/logisticsc/homeCenter/getPartner.shtml",
        type : 'POST',
        dataType : 'json',
        success:function(data){
            if (data.result) {
            	date=data.data;
            	var body="<ul>";
            	for(var i=0; i<date.length; i++) {	
            		body+="<li><a href='"+date[i].partnerUrl+"'><img src='/logisticsc/img/retrive.shtml?resource="+date[i].pictureUrl+"'/></a></li>"
            	}
            	body+="</ul>"
            }
            $("#getPartner").html(body);
        },
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}	
    });
	
}
function companyNews() {
	$.ajax({
        url : "/logisticsc/homeCenter/getNews.shtml",
        type : 'POST',
        data :{newsType:0},
        dataType : 'json',
        success:function(data){
            if (data.result) {
            	date=data.data;
            	var body="";
            	for(var i=0; i<date.length; i++) {
            		var a =1+i;
            		body+="<tr><td class='td-img'>"
            			+"<a href='/logisticsc/homeCenter/getNewsInfo.shtml?id="+date[i].id+"'><img src='/logisticsc/img/retrive.shtml?resource="+date[i].titlePicture+"'/></a>"
            			+"</td>"
            			+"<td class='num-width'>"
            			+"<a class='num'>"+a+"</a>"
            			+"</td>"
            			+"<td>"
            			+"<a href='/logisticsc/homeCenter/getNewsInfo.shtml?id="+date[i].id+"'>"+date[i].newsTitle+"</a><br />"
            			+"<a  class='xiaozi'>"+formartDate(date[i].newsTime)+"</a>"
            			+"</td>"
            			+"</tr>";
            		
            	}
            	 $("#companyNews").html(body);
            }
        },
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
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
function infoNews() {
	$.ajax({
        url : "/logisticsc/homeCenter/getNews.shtml",
        type : 'POST',
        data :{newsType:1},
        dataType : 'json',
        success:function(data){
            if (data.result) {
            	date=data.data;
            	var body="";
            	for(var i=0; i<date.length; i++) {
            		var a =1+i;
            		body+="<tr class='tr-top'>"
            			+"<td class='num-width'>"
            			+"<a class='num'>"+a+"</a>"
            			+"</td>"
            			+"<td>"
            			+"<a href='/logisticsc/homeCenter/getNewsInfo.shtml?id="+date[i].id+"'>"+date[i].newsTitle+"</a>"
            			+"<a class='xiaozi'>"+formartDate(date[i].newsTime)+"</a>"
            			+"</td>"
            			+"</tr>";
            	}
            	 $("#infoNews").html(body);
            }
        },
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}	
    });
}