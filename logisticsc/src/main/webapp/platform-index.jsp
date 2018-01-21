<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico" /> 
<title>货运交易系统</title>
<script src="/logisticsc/resources/platform/jquery/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/logisticsc/resources/platform/css/index2.css" />
<link rel="stylesheet" href="/logisticsc/resources/platform/css/public.css" />
<!-- bootstrap-->
<script src="/logisticsc/resources/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="/logisticsc/resources/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="/logisticsc/resources/platform/js/formSubmit.js"></script>
</head>
<script type="text/javascript">
$(function(){
	startAjaxProvince();
	endAjaxProvince();
});
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
/**********************城市**************************/
	$(function(){
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
		$('.box-content').find("li").show();
	})
	//查询物流商
	function wuliushang() {
	var wuliushangmingcheng = $("#wuliushangmingcheng").val();
	//window.location.href=encodeURI("/logisticsc/deliverGoods/deliverGoods.shtml?companyNames="+wuliushangmingcheng);
		/* $('#companyNames').val(wuliushangmingcheng);
		$('#toForm').submit(); */
		var name=new Array(); 
		var value=new Array();
		name[0]="companyNames";
		value[0]=wuliushangmingcheng;
		buildForm("/logisticsc/deliverGoods/deliverGoods.shtml?chooseid='fahuo'",name,value);
	}
	
	//订单跟踪
	function dingdangenzong() {
		var dingdanhao = $("#dingdanhao").val();
		var name=new Array(); 
		var value=new Array();
		name[0]="condition";
		value[0]=dingdanhao;
		buildForm("/logisticsc/orderTracking/toorderorderTracking.shtml?chooseid='genzhong'",name,value);
	}
	//我要发货
	function deliverGoods(){
		var startProvince = $("#startProvince").val()?$("#startProvince").val():'',
			startCity = $("#startCity").val()?$("#startCity").val():'',
			startCounty = $("#startCounty").val()?$("#startCounty").val():'',
			endProvince = $("#targetProvince").val()?$("#targetProvince").val():'',
			endCity = $("#targetCity").val()?$("#targetCity").val():'',
			endCounty = $("#targetCounty").val()?$("#targetCounty").val():'';
			var name=new Array(); 
			var value=new Array();
			name[0]="startProvince";
			value[0]=startProvince;
			name[1]="startCity";
			value[1]=startCity;
			name[2]="startCounty";
			value[2]=startCounty;
			name[3]="endProvince";
			value[3]=endProvince;
			name[4]="endCity";
			value[4]=endCity;
			name[5]="endCounty";
			value[5]=endCounty;
			buildForm("/logisticsc/deliverGoods/deliverGoods.shtml?chooseid='fahuo'",name,value);
			
			
	/* 		window.location.href="/logisticsc/deliverGoods/deliverGoods.shtml?startProvince="+startProvince+
			"&startCity="+startCity+"&startCounty="+startCounty+"&endProvince="+endProvince+"&endCity="+endCity+
			"&endCounty="+endCounty; */
		
	}
	function helpjiaruwm() {
		var name=new Array(); 
		var value=new Array();
		name[0]="name";
		value[0]="contactUs";
		buildForm("/logisticsc/helpCenter/toHelpCenter.shtml",name,value);
	}
	function helpguanyu() {
		var name=new Array(); 
		var value=new Array();
		name[0]="name";
		value[0]="aboutUs";
		buildForm("/logisticsc/helpCenter/toHelpCenter.shtml",name,value);
	}
	//
	function helpmanual() {
		var name=new Array(); 
		var value=new Array();
		name[0]="name";
		value[0]="manual";
		buildForm("/logisticsc/helpCenter/toHelpCenter.shtml",name,value);
	}
</script>
<body>
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #666 !important;">温馨提示！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #666!important;font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
<div class="index2_top" >
		<div class="index2_con">
			<!--  
			<div class="index2_phone">
				<img src="/logisticsc/resources/platform/img/index2_top_phone.png" />
				<p>18000510004</p>
			</div>
			-->
			<!--  
			<c:if test="${user_session.loginName == null }">
				<a href="/logisticsc/user/jumpreGister.shtml" class="f">免费注册</a>
			</c:if>
			-->
			<c:if test="${user_session.loginName != null }">
				<a href="/logisticsc/user/outUser.shtml?" class="index_tuichu">退出</a>
			</c:if>
			<!--  
			<c:if test="${user_session.loginName == null }">
				<a href="/logisticsc/platform-login.jsp" class="f">请登陆</a>
			</c:if>
			-->
			<c:if test="${user_session.loginName != null }">
				<a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" class="f">${user_session.loginName}</a>
			</c:if>
			<input type="hidden" id="userName" value="${user_session.loginName}"/>
			<!--  
			<a href="/logisticsc/yc-login.yc" style="color: rgb(255, 102, 0);display:block;text-decoration: none" target="_blank">云仓管理系统</a>
			<div id = 'go_logisticsc'>
				<a href="javascript:void(0)" style="color: rgb(255, 102, 0);display:block;text-decoration: none;" >专线营运系统</a>
			</div>
			-->
		</div>
		</div>
		<div class="index2-nr">
			<div class="index2_logo">
				<img src="/logisticsc/resources/platform/img/index2_logoimg.png" />
			</div>
			<div class="index2_hezi">
				<div class="banner-con">
					<ul class="banner-top">
						<li>我要发货</li>
						<li>物流商查询</li>
						<li>订单跟踪</li>
					</ul>
					<ul class="banner-bot">
						<li class="bot1">
							<form id="deliver_goods">
								<input type="hidden" id="startProvince">
								<input type="hidden" id="startCity">
								<input type="hidden" id="startCounty">
								<input type="hidden" id="targetProvince">
								<input type="hidden" id="targetCity">
								<input type="hidden" id="targetCounty">
								<div class="search_box">
									<div class="search_box_info">
										<div>
											<label>始发地：</label>
											<input type="text" id="start_input" class="choice-county" style="margin: 7px 0px;">
										</div>
										<div>
											<label>目的地：</label>
											<input type="text" id="target_input" class="choice-county" style="margin: 7px 0px;">
										</div>
									</div>
									<div class="search_box_button">
										<button type="button" onclick="deliverGoods()">查询</button>
									</div>
									<div class="county_box" style="display: none;">
										<div class="county_box_nav">
											<table>
												<tr>
													<td class="active">省</td>
													<td>市</td>
													<td>县</td>
												</tr>
											</table>
											<div class="box-content" id="content-province">
											</div>
											<div class="box-content" id="content-city" style="display: none;">
											</div>
											<div class="box-content" id="content-county" style="display: none;">
											</div>
										</div>
									</div>
								</div>
								
							</form>
						</li>
						<li class="bot1 bot2">
							<form>
								<div class="search_box">
									<div class="search_box_info">
										<div style="margin-top: 27px;">
											<label>物流商：</label>
											<input type="text" id="wuliushangmingcheng">
										</div>
									</div>
									<div class="search_box_button">
										<button type="button" onclick="wuliushang()">查询</button>
									</div>
								</div>
							</form>
									
						</li>
						<li class="bot1 bot2">
							<form>
								<div class="search_box">
									<div class="search_box_info">
										<div style="margin-top: 27px;">
											<label>订单号/运单号：</label>
											<input type="text" id="dingdanhao" style="width: 214px;">
										</div>
									</div>
									<div class="search_box_button">
										<button type="button" onclick="dingdangenzong()">查询</button>
									</div>
								</div>
							</form>
						</li>
					</ul>
				</div>
				<div class="index2-pic">
					<ul>
						<li class="li-left0">
							<a href="<%=request.getContextPath()%>/homeCenter/topInsurance.shtml">
							<img src="/logisticsc/resources/platform/img/index2-icon-1.png" />
							</a> 
						</li>
						<li>
							 <a href="#">
								<img src="/logisticsc/resources/platform/img/index2-icon-2.png" />
							</a> 
						</li>
						<li>
							<a href="#">
							<!--<a href="javascript:javascript:void(0);" onclick="helpmanual()">-->
								<img src="/logisticsc/resources/platform/img/index2-icon-3.png" />
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--  
		<div style = "margin-right:130px;padding:17px;">
				<div style = "float:right;width:20%">
				<a href="/logisticsc/yc-login.yc" style="color: rgb(255, 102, 0);display:block;text-decoration: none;font-size:19px" target="_blank">云仓管理系统</a>
		</div>
			<div style = "float:right;width:20%" id = 'go_logisticsc'>
				<a href="javascript:void(0)" style="color: rgb(255, 102, 0);display:block;text-decoration: none;font-size:19px" >专线运营系统</a>
				<input type="hidden" id="userName" value="${user_session.loginName}"/>
			</div>
			<div style = "float:right;width:20%">
			<c:if test="${user_session.loginName == null }">
				<a href="/logisticsc/platform-login.jsp" style="color: rgb(255, 102, 0);display:block;text-decoration: none;font-size:19px;" >货运交易系统</a>
			</c:if>
			<c:if test="${user_session.loginName != null }">
			<a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" style="color: rgb(255, 102, 0);display:block;text-decoration: none;font-size:19px;">货运交易系统</a>
			</c:if>
		</div>
		</div>
		-->
		<div class="index2_bot">
			<div class="bot-con">
			<center> © Copyright 2013-2017 中工储运  版权所有 |&nbsp; 粤ICP备15048227号-1</center>
				<div class="bot_left f">
					<ul>
					<!--  
						<li >
							<a href="javascript:javascript:void(0);" onclick="helpguanyu()">关于中工&nbsp;&nbsp;&nbsp;&nbsp;|</a>
							<a href="javascript:javascript:void(0);" onclick="helpjiaruwm()">&nbsp;&nbsp;&nbsp;&nbsp;加入我们</a><br />
						</li>
						<li>
							全国服务热线：18000510004
						</li>
						<li>
							 © Copyright 2013-2017 中工储运  版权所有 |&nbsp; 粤ICP备15048227号-1
						</li>
					-->
					</ul>
				</div>
				<!--  
				<div class="bot_right r">
					<div class="r-left f">
						<span class="rl">
							扫一扫<br />
							访问官网
						</span>
					</div>
					<img src="/logisticsc/resources/platform/img/icon-weixin.png" />
				</div>
				-->
			</div>
		</div>
	<!-- 	<form id="toForm" action="/logisticsc/deliverGoods/deliverGoods.shtml" method="post">
			<input type="hidden" name="companyNames" id="companyNames">
			<in>
		</form> -->
	<script type="text/javascript">
	
	var choice_flag = null;
	$('#start_input').click(function(e){
		$('.county_box').css('top','45px');
		choice_flag = 1;
		if($('.county_box').css('display') == 'none'){
			$('.county_box').toggle();
	        e.stopPropagation();
	        getXzqhList(100000, '' ,0);
		}
	});
	$('#target_input').click(function(e){
		$('.county_box').css('top','85px');
		choice_flag = 2;
		if($('.county_box').css('display') == 'none'){
			$('.county_box').toggle();
			
	        e.stopPropagation();
	        getXzqhList(100000, '' ,0);
		}
	});
	$(document).click(function() {
        $(".county_box").hide();
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
	$(function(){
		
	})
	
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
			url : '<%=request.getContextPath()%>/xzqh/getxzqhinfo.shtml',
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
	$("#go_logisticsc").click(function(){
		$.ajax({
			url:'/logisticsc/tms/login.shtml',
			type:'post',
			success:function(data){
				if(data.result){
					window.location.href="/logisticsc/tms/index.shtml";
				}else{
					if(data.msg == '请先登录'){
						window.location.href="/logisticsc/liner-login.jsp";
					}else{
						$("#promptModal").modal('show');
						$("#promptModalMsgs").html(data.msg);
					}
				}
			},
		})
	})
	</script>
</body>
</html>