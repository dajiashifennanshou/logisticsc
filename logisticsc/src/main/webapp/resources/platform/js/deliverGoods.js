var map;
var xzqh ;
var ids,startCountys,endCountys,dituTypes;
$(document).ready(function(){
	startAjaxProvince();
	endAjaxProvince();
	//getListorderDeliver();
	xzqh = JSON.parse($("#xzqh").val()); 
	setProvinceAndCity();
	//查询我要发货
	$("#chaxun").click(function(){
		$("#companyName").val("");
		getListorderDeliver();
	});
	$('#myTab a:first').tab('show');//初始化显示哪个tab 
    $('#myTab a').click(function (e) { 
      e.preventDefault();//阻止a链接的跳转行为 
      $(this).tab('show');//显示当前选中的链接及关联的content 
    })
    loadBMap();
    getPlatInfo();
    getListorderDeliver();
})

// 设置 首页跳转传入的 省、市编号
function setProvinceAndCity(){
	$('#startProvince').val(xzqh.startProvince);
	$('#startCity').val(xzqh.startCity);
	$('#startCounty').val(xzqh.startCounty);
	$('#targetProvince').val(xzqh.endProvince);
	$('#targetCity').val(xzqh.endCity);
	$('#targetCounty').val(xzqh.endCounty);
}

function getPlatInfo(){
	$.ajax({
		url:'/logisticsc/deliverGoods/getPlatInfo.shtml',
		type:'post',
		success:function(result){
			if(result.result){
				$("#pub_info").html(result.data[0].content)
			}
		}
	})
	
}
function pingtaituijian(){
	 type="1";
	 getListorderDeliver();
}
function xingji(){
	 type="2";
	 getListorderDeliver();
}
function shixiao(){
	 type="3";
	 getListorderDeliver();
}
function chengyunpiaoshu(){
	 type="4";
	 getListorderDeliver();
}
function zhonghuojia(){
	 type="5";
	 getListorderDeliver();
}
function paohuojia(){
	 type="6";
	 getListorderDeliver();
}
function qiyunjia(){
	 type="7";
	 getListorderDeliver();
}
var type="";
//我要发货
function getListorderDeliver () {
	var paramForm = JSON.parse($("#param_form").val());
	var companyName =$("#companyName").val();
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	conditionType =4;
	
	if(type == 1){
		conditionType = 1;
	}else if(type == 2){
		conditionType = 2;
	}else if(type == 3){
		conditionType = 3;
	}else if(type == 4){
		conditionType = 4;
	}else if(type == 5){
		conditionType = 5;
	}else if(type == 6){
		conditionType = 6;
	}else if(type == 7){
		conditionType = 7;
	}else {
		conditionType =0;
	}
	
	var isStartCounty = $('#isStartCounty').get(0).checked;
	var isEndCounty = $('#isEndCounty').get(0).checked;
	paramForm.startProvince = $("#startProvince").val();
	paramForm.startCity = $("#startCity").val();
	if(isStartCounty){
		paramForm.startCounty = $("#startCounty").val();
	}else{
		paramForm.startCounty = null;
	}
	paramForm.endProvince = $("#targetProvince").val();
	paramForm.endCity = $("#targetCity").val();
	if(isEndCounty){
		paramForm.endCounty = $("#targetCounty").val();
	}else{
		paramForm.endCounty = null;
	}
	paramForm.heavyCargoPriceLow = $("#zhonghuo").val();
	paramForm.bulkyCargoPriceLow = $("#paohuo").val();
	
	paramForm.conditionType =conditionType;
	paramForm.companyName =companyName;
	paramForm.pageIndex = pageindex;
	$.ajax({
		url:"/logisticsc/deliverGoods/getListorderDeliver.shtml",
		data:paramForm,
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
				$("#getListorderDeliver").html(lineMoney);
				$("#pageList").html("");
				return false;
			}
			for (var i = 0; i < date.length; i++) {
				if(date[i].evaluateLevel == null ){
					var evaluateLevel = 0;
				}else{
					var evaluateLevel=date[i].evaluateLevel*20;
				}
				if(!date[i].companyId && date[i].companyId == null){
					var qq="";
					var contacts1Mobile = "";
					if(null == date[i].qq || '' == date[i].qq || 'undefined' == date[i].qq || date[i].qq.length <= 0){
						qq = "";
					}else{
						qq = "<br/>"+"QQ:<span>"+date[i].qq+"</span>";
					}
					if(null == date[i].contacts1Mobile || '' == date[i].contacts1Mobile || 'undefined' == date[i].contacts1Mobile || date[i].contacts1Mobile.length <= 0){
						contacts1Mobile="";
					}else{
						contacts1Mobile ="<br/>"+"Phone:<span>"+date[i].contacts1Mobile+"</span>";
					}
					body+="<tr>"
						+"<td><span>"+date[i].companyName+"</span>"+qq+contacts1Mobile+"</td>"
						+"<td>"+"从:"+date[i].startProvince+"-"+date[i].startCity+"-"+date[i].startCounty+"<br/>"+
						"到:"+date[i].endProvince+"-"+date[i].endCity+"-"+date[i].endCounty+"</td>"
						+"<td>时效:"+date[i].timeLong+"小时"+"<br/>服务类型:"+date[i].serverType+"<br/>运输方式:"+date[i].transportMode+"</td>"
						+"<td style='position:relative;'><a href='#' onmouseover='display("+i+")' onmouseout='disappear("+i+")'>"+"重货："+date[i].heavyCargoPriceLow+"/吨"+"</a><div id='box"+i+"' class='boxs' onmouseover='display()' onmouseout='disappear()'><span><1吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceLow+"/吨</span><br/><span>1-3吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceMid+"/吨</span><br/><span>>3吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceHigh+"/吨</span></div><br/><a href='#' onmouseover='displays("+i+")' onmouseout='disappears("+i+")'>"+"泡货："+date[i].bulkyCargoPriceLow+"/立方米"+"</a><div id='boxs"+i+"' class='boxs' onmouseover='display()' onmouseout='disappear()'><span>0-3/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceLow+"/立方</span><br/><span>3-10/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceMid+"/立方</span><br/><span>>10/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceHigh+"/立方</span></div><br/>"+"最低价："+"￥"+date[i].lowestPrice+"/票"+"</td>"
						+"<td>"+"已承运"+date[i].countOrder+"/票"+"<br/>"+"已有"+date[i].countOrderEvaluation+"条评价"+"</td>"
						/*+"<td>"+"￥30000"+"</td>"*/
						+"<td>"+evaluateLevel+"%</td>"
						+"<td>"+"<input type='button' class='btn btn-info' value='下单' disabled><br/><input type='button' class='btn btn-info' id='shoucang' value='收藏' disabled>"+"</td>"
						+"</tr>";
				}else{
					var qq="";
					var contacts1Mobile = "";
					if(null == date[i].qq || '' == date[i].qq || 'undefined' == date[i].qq || date[i].qq.length <= 0){
						qq = "";
					}else{
						qq = "<br/>"+"QQ:<a target='_blank' href='http://wpa.qq.com/msgrd?v=3&uin="+date[i].qq+"&site=qq&menu=yes'>"+date[i].qq+"</a>";
					}
					if(null == date[i].contacts1Mobile || '' == date[i].contacts1Mobile || 'undefined' == date[i].contacts1Mobile || date[i].contacts1Mobile.length <= 0){
						contacts1Mobile="";
					}else{
						contacts1Mobile ="<br/>"+"Phone:<span>"+date[i].contacts1Mobile+"</span>";
					}
					body+="<tr>"
						+"<td><a href='javascript:javascript:void(0);' onclick='tiaozhuanwuliushang("+date[i].companyId+")'>"+date[i].companyName+"</a>"+qq+contacts1Mobile+"</td>"
						+"<td>"+"从:"+date[i].startProvince+"-"+date[i].startCity+"-"+date[i].startCounty+"<span onclick=xianshiditu("+date[i].companyId+",'"+date[i].startCounty+"','"+date[i].endCounty+"',"+1+")><a href='javascript:void(0)'>(查看网点)</a></span><br/>"+
						"到:"+date[i].endProvince+"-"+date[i].endCity+"-"+date[i].endCounty+"<span onclick=xianshiditu("+date[i].companyId+",'"+date[i].startCounty+"','"+date[i].endCounty+"',"+2+")><a href='javascript:void(0)'>(查看网点)</a></span></td>"
						+"<td>时效:"+date[i].timeLong+"小时"+"<br/>服务类型:"+date[i].serverType+"<br/>运输方式:"+date[i].transportMode+"</td>"
						+"<td style='position:relative;'><a href='#' onmouseover='display("+i+")' onmouseout='disappear("+i+")'>"+"重货："+date[i].heavyCargoPriceLow+"/吨"+"</a><div id='box"+i+"' class='boxs' onmouseover='display()' onmouseout='disappear()'><span><1吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceLow+"/吨</span><br/><span>1-3吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceMid+"/吨</span><br/><span>>3吨&nbsp;&nbsp;￥"+date[i].heavyCargoPriceHigh+"/吨</span></div><br/><a href='#' onmouseover='displays("+i+")' onmouseout='disappears("+i+")'>"+"泡货："+date[i].bulkyCargoPriceLow+"/立方米"+"</a><div id='boxs"+i+"' class='boxs' onmouseover='display()' onmouseout='disappear()'><span>0-3/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceLow+"/立方</span><br/><span>3-10/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceMid+"/立方</span><br/><span>>10/立方&nbsp;&nbsp;￥"+date[i].bulkyCargoPriceHigh+"/立方</span></div><br/>"+"最低价："+"￥"+date[i].lowestPrice+"/票"+"</td>"
						+"<td>"+"已承运"+date[i].countOrder+"/票"+"<br/>"+"已有"+date[i].countOrderEvaluation+"条评价"+"</td>"
						/*+"<td>"+"￥30000"+"</td>"*/
						+"<td>"+evaluateLevel+"%</td>"
						+"<td>"+"<input type='button' class='btn btn-info' value='下单' onclick='toPlaceOrderPage("+date[i].id+")'><br/><input type='button'onclick='minenCollection("+date[i].id+")' class='btn btn-info' id='shoucang' value='收藏'>"+"</td>"
						+"</tr>";
				}
			}
			lineMoney=body;
			$("#getListorderDeliver").html(lineMoney);
			var foot="<div style='margin-top: 10px; float: right;'>"+
			"<input type='hidden' class='currentPage'/>"+
			"<div id='videoDiv' class='page'></div></div>";
			$("#pageList").html(foot);
			setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getListorderDeliver);
		},
		error:function(error){
			body+="<tr>"
				+"<td style='color:red;' colspan='16' >没有数据</td>";
			+"</tr>";	
			var lineMoney=body;
			$("#getListorderDeliver").html(lineMoney);
			$("#pageList").html("");
		}
	});
}
function tiaozhuanwuliushang(companyId) {
	var name=new Array(); 
	var value=new Array();
	name[0]="companyId";
	value[0]=companyId;
	buildForm("/logisticsc/company/toorderPersonalcompany.shtml",name,value);
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
function qishidi(){
	dituTypes=1;
	wangdianditu(ids,startCountys,dituTypes);
}
function mididi(){
	dituTypes=2;
	wangdianditu(ids,endCountys,dituTypes);
}
function xianshiditu(id,startCounty,endCounty,dituType){
	ids=id;
	startCountys = startCounty;
	endCountys=endCounty;
	dituTypes =dituType;
	if(dituTypes == 1){
		qishidi();
	}else{
		mididi();
	}
	
	
}
// 验证查询 条件
function validateSearchCondition(){
	var startCounty = $("#startCounty").val();
	var endCounty = $("#targetCounty").val();
	if(startCounty == null || startCounty == ''){
		$("#promptModal").modal('show');
		$("#promptModalMsgs").html("起始地需选择到区");
		return false;
	}
	if(endCounty == null || endCounty == ''){
		$("#promptModal").modal('show');
		$("#promptModalMsgs").html("目的地需选择到区");
		return false;
	}
	return true;
}

var myGeo = new BMap.Geocoder();
function wangdianditu(id,name,dituTypes){
	$.ajax({
		url:"/logisticsc/orderCenter/getOultesInfo.shtml",
		data:{companyId:id,name:name},
		dataType:"json",
		type:"post",
		success:function(dataResult){
			if(dataResult.result ==true){
				map.enableScrollWheelZoom();
				map.enableContinuousZoom(); 
				var data = dataResult.data;
				for (var i = 0; i < data.length; i++) {
					 var name = data[i].name;
					 var mobile=data[i].mobile;
					 var phone =data[i].phone;
					 var address=data[i].provinceValue+data[i].cityValue+data[i].countyValue+data[i].address;
					 geocodeSearch(address, name, mobile, phone)
				}
				if(dituTypes == 1){
					//起始地
					var div2 = document.getElementById('mudiddi'); 
					div2.setAttribute("class", "active"); 
					$("#mudiddi").removeClass("active"); 
					var div1 = document.getElementById('qiishdi'); 
					div1.setAttribute("class", "active"); 
				}else if(dituTypes ==2){
					//目的地
					var div1 = document.getElementById('qiishdi'); 
					div1.setAttribute("class", "active"); 
					$("#qiishdi").removeClass("active");
					var div2 = document.getElementById('mudiddi'); 
					div2.setAttribute("class", "active"); 
				}
				$("#wangdianditu").modal({show : true});
				
			}
		},error:function(error){
		}
	});
}

function geocodeSearch(add,name,mobile,phone){
	myGeo.getPoint(add, function(point){
		if (point) {
			var address = new BMap.Point(point.lng, point.lat);
			addMarker(address,name,mobile,phone,add);
		}
	});
}

//创建标注
function addMarker(point, name, mobile, phone, address){
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);
	map.centerAndZoom(point, 13);
	map.panTo(point);
	var infoWindow = new BMap.InfoWindow("网点名称："+name+"<br/>电话："+mobile+"/"+phone+"<br/>地址："+address+"");
	marker.addEventListener("click", function(){     
		map.openInfoWindow(infoWindow,point);
	})
}

//加载百度地图
function loadBMap(){
	map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
}
// 跳转到下单页面
function toPlaceOrderPage(lineId){
	/*if(!validateSearchCondition()){ // 验证 查询条件
	return;
	}*/
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
		/*$('#order_lineId').val(lineId);
		$('#order_startProvince').val(startProvince);
		$('#order_startCity').val(startCity);
		$('#order_startCounty').val(startCounty);
		$('#order_endProvince').val(endProvince);
		$('#order_endCity').val(endCity);
		$('#order_endCounty').val(endCounty);
		$('#toPlaceOrderForm').submit();*/
		window.location.href = '/logisticsc/deliverGoods/placeOrder.shtml?lineId=' + lineId
		+ '&startProvince=' + startProvince + '&startCity=' + startCity + '&startCounty=' + startCounty
		+ '&endProvince=' + endProvince + '&endCity=' + endCity + '&endCounty=' + endCounty;
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
			  					$("#successModalMsgs").html(data.msg);
			  		      		getListorderDeliver();
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
			 },error:function(data){
				 $("#errorModal").modal('show');
				 $("#errorModalMsgs").html("系统错误!");
			}
		})
	}	
}
var start_input="";
var target_input ="";
//起始地省
function startAjaxProvince(){
	var getXzqhInfo="";
	 $.ajax({
     url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
     type : 'POST',
     dataType : 'json',
     success:function(data){
    	 if(data.result==true){
    		 var data = data.data,
    		 	startProvince = xzqh.startProvince,
    		 	selected = null;
    		$("#start_province_outlets").append("<option value=''>请选择</option>");
    		 for(var i=0; i<data.length; i++) {	
    			if(startProvince==data[i].id){
    				/*getXzqhInfo += "<option value='"+data[i].id+"' selected>"+data[i].name+"</option>";*/
    				start_input = data[i].name;
    				//$("#startProvince").val(data[i].id);
    				startAjaxCity();
    			}else{
    				getXzqhInfo += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
    			}
 			}
 		$("#start_province_outlets").append(getXzqhInfo);
       }
		},error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误!");	
		}
	 })
}
//起始地市
function startAjaxCity(){
	var province=$("#start_province_outlets").val(),
		tmpPro = xzqh.startProvince,
		data = {};
	if(tmpPro&&tmpPro!=''){
		data = {pid:tmpPro};
		xzqh.startProvince = '';
	}else{
		data={pid:province};
	}
	$("#start_city_outlets").empty();
	$("#start_county_outlets").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				var startCity = xzqh.startCity;
				$("#start_city_outlets").append("<option value=''>请选择</option>"); 
				$("#start_county_outlets").append("<option value=''>请选择</option>"); 
				$.each(data.data,function(i){
					if(startCity==data.data[i].id){
						/*$("#start_city_outlets").append("<option value='"+data.data[i].id+"' selected>"+data.data[i].name+"</option>");*/
						start_input +="-"+data.data[i].name;
						//$("#startCity").val(data.data[i].id);
						/*xzqh.startCity = '';*/
						startAjaxCounty();
					}else{
						$("#start_city_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
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
//起始地县
function startAjaxCounty(){
	var city=$("#start_city_outlets").val(),
		tmpCity = xzqh.startCity;
	var data;
	if(tmpCity&&tmpCity!=''){
		data={pid:tmpCity};
		xzqh.startCity = '';
	}else{
		data={pid:city};
	}
	$("#start_county_outlets").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#start_county_outlets").append("<option value=''>请选择</option>");
				var startCounty = xzqh.startCounty;
				$.each(data.data,function(i){
					if(startCounty == data.data[i].id){
						/*$("#start_county_outlets").append("<option value='"+data.data[i].id+"' selected>"+data.data[i].name+"</option>");*/
						/*xzqh.startCounty = '';*/
						start_input +="-"+data.data[i].name;
						$("#start_input").val(start_input);
						//$("#startCounty").val(data.data[i].id);
					}else{
						$("#start_county_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
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
//目的地省
function endAjaxProvince(){
	var getXzqhInfo="";
  	 $.ajax({
       url :"/logisticsc/orderCenter/getXzqhInfo.shtml",
       type : 'POST',
       dataType : 'json',
       success:function(data){
      	 if(data.result==true){
      		 var data = data.data,
      		 	endProvince = xzqh.endProvince;
      		$("#end_province_outlets").append("<option value=''>请选择</option>");
      		 for(var i=0; i<data.length; i++) {	
      			 if(endProvince==data[i].id){
      			/*	getXzqhInfo += "<option value='"+data[i].id+"' selected>"+data[i].name+"</option>"; */
      				target_input = data[i].name;
      				//$("#targetProvince").val(data[i].id);
      				endAjaxCity();
      			 }else{
      				getXzqhInfo += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
      			 }
   			}
   		$("#end_province_outlets").append(getXzqhInfo);
         }
	 },error:function(data){
		 $("#errorModal").modal('show');
		 $("#errorModalMsgs").html("系统错误!");
	}
})
}
//目的地市
function endAjaxCity(){
	var province=$("#end_province_outlets").val(),
		tmpPro = xzqh.endProvince,
		data = {};
	if(tmpPro&&tmpPro!=''){
		data = {pid:tmpPro};
		xzqh.endProvince = '';
	}else{
		data={pid:province};
	}
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
				var endCity = xzqh.endCity;
				$.each(data.data,function(i){
					if(endCity == data.data[i].id){
						/*$("#end_city_outlets").append("<option value='"+data.data[i].id+"' selected>"+data.data[i].name+"</option>");
						xzqh.endCity = '';*/
						target_input +="-"+data.data[i].name;
						//$("#targetCity").val(data.data[i].id);
						endAjaxCounty();
					}else{
						$("#end_city_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
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
//目的地县
function endAjaxCounty(){
	var city=$("#end_city_outlets").val(),
		tmpCity = xzqh.endCity,
		data = {};
	var data={pid:city};
	if(tmpCity&&tmpCity!=''){
		data={pid:tmpCity};
		tmpCity = '';
	}else{
		data={pid:city};
	}
	$("#end_county_outlets").empty();
	$.ajax({
		url:"/logisticsc/tms/outlets/ajax/xzqh.shtml",
		data:data,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result==true){
				$("#end_county_outlets").append("<option value=''>请选择</option>"); 
				var endCounty = xzqh.endCounty;
				$.each(data.data,function(i){
					if(data.data[i].id == endCounty){
						/*$("#end_county_outlets").append("<option value='"+data.data[i].id+"' selected>"+data.data[i].name+"</option>");*/
						target_input +="-"+data.data[i].name;
						$("#target_input").val(target_input);
						//$("#targetCounty").val(data.data[i].id);
					}else{
						$("#end_county_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
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